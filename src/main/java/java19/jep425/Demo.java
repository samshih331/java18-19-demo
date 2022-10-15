package java19.jep425;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Demo {

	public static void main(String[] args) {
		Demo.v();
		Demo.v2();

		ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

		Thread.startVirtualThread(() -> {
			// ...
		});

		Thread.ofVirtual().start(() -> {
			// ...
		});
	}

	public static void v() {
		long l = System.currentTimeMillis();
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			IntStream.range(0, 10_000).forEach(i -> executor.submit(() -> {
				Thread.sleep(Duration.ofSeconds(1));
				return i;
			}));
		}
		System.out.println(String.format("takes %s second", (System.currentTimeMillis() - l) / 1000));
	}

	public static void v2() {
		long l = System.currentTimeMillis();
		try (var executor = Executors.newCachedThreadPool()) {
			IntStream.range(0, 10_000).forEach(i -> executor.submit(() -> {
				Thread.sleep(Duration.ofSeconds(1));
				return i;
			}));
		}
		System.out.println(String.format("takes %s second", (System.currentTimeMillis() - l) / 1000));
	}
}
