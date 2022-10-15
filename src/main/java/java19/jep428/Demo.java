package java19.jep428;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import jdk.incubator.concurrent.StructuredTaskScope;

public class Demo {

	String[] handle() throws ExecutionException, InterruptedException {
		try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
			Future<String> user = scope.fork(() -> "user");
			Future<String> order = scope.fork(() -> "order");
			scope.join();
			scope.throwIfFailed();

			return new String[] {user.resultNow(), order.resultNow()};
		}
	}

	String[] handle2() throws ExecutionException, InterruptedException {

		try (var executorService = Executors.newCachedThreadPool()) {
			Future<String> user = executorService.submit(() -> "user");
			Future<String> order = executorService.submit(() -> "order");
			return new String[] {user.get(), order.get()};
		}

	}
}