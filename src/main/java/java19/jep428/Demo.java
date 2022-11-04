package java19.jep428;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jdk.incubator.concurrent.StructuredTaskScope;

public class Demo {

	/*
	Error handling with short-circuiting — If either the user task or order task subtasks fail,
	the other is cancelled if it has not yet completed.
	(This is managed by the cancellation policy implemented by ShutdownOnFailure; other policies are possible).

	Cancellation propagation — If the thread running handle() is interrupted before or during the call to join(),
	both forks are cancelled automatically when the thread exits the scope.

	Clarity — The above code has a clear structure: Set up the subtasks,
	wait for them to either complete or be cancelled,
	and then decide whether to succeed (and process the results of the child tasks,
	which are already finished) or fail (and the subtasks are already finished, so there is nothing more to clean up).

	Observability — A thread dump, as described below, clearly displays the task hierarchy,
	with the threads running user task and task shown as children of the scope.
	 */
	String[] handle() throws ExecutionException, InterruptedException {
		try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
			Future<String> user = scope.fork(() -> "user");
			Future<String> order = scope.fork(() -> "order");
			scope.join();
			scope.throwIfFailed();

			return new String[] {user.resultNow(), order.resultNow()};
		}
	}

	/*
	If user task throws an exception then handle2() will throw an exception when calling user task,
	but order task will continue to run in its own thread.
	This is a thread leak which, at best, wastes resources; at worst, the order task thread will interfere with other tasks.

	If the thread executing handle2() is interrupted,
	the interruption will not propagate to the subtasks.
	Both the user task and order task threads will leak, continuing to run even after handle() has failed.

	If user task takes a long time to execute,
	but order task fails in the meantime,
	then handle2() will wait unnecessarily user task by blocking on user.get() rather than cancelling it.
	Only after user task completes and user.get() returns will order.get() throw an exception, causing handle() to fail.
	 */
	String[] handle2() throws ExecutionException, InterruptedException {

		try (var executorService = Executors.newCachedThreadPool()) {
			Future<String> user = executorService.submit(() -> "user");
			Future<String> order = executorService.submit(() -> "order");
			return new String[] {user.get(), order.get()};
		}

	}
}