package helloWorld;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloWorldApplicationTests {

	@Autowired
	ItemRepository repo;

	@Test
	void contextLoads() {
//		contextLoadsThreads();
//		save();
	}

	@Test
	void save() {
		Long l = System.currentTimeMillis();
		ItemEntity entity = new ItemEntity("ItemEntity." + l);
		repo.save(entity);
		System.out.println(entity);
	}

	@Test
	void get() {
		Optional<ItemEntity> opt = repo.findById(1);
		if (opt.isPresent())
			System.out.println(opt.get());
	}

	void contextLoadsSingleThread() {
		for (int i = 1; i <= 10000; i++) {
			Long l = System.currentTimeMillis();
			ItemEntity entity = new ItemEntity("ItemEntity." + i + "." + l);
			repo.save(entity);
			System.out.println(entity);
		}

	}

	void contextLoadsThreads() {
		ExecutorService service = Executors.newCachedThreadPool();

		int total = 10000;
		int threads = 20;
		int subthreads = total / threads;
		
		for (int i1 = 0; i1 < threads; i1++) {

				service.execute(() -> {
				for (int i = 1; i <= subthreads; i++) {
					Long l = System.currentTimeMillis();
					ItemEntity entity = new ItemEntity("ItemEntity." + l + new Random().nextInt());
					repo.save(entity);
					System.out.println(entity);
				}

			});
		}

		service.shutdown();

		try {
			if (!service.awaitTermination(80, TimeUnit.MINUTES)) {
				service.shutdownNow();
			}
		} catch (InterruptedException e) {
			service.shutdownNow();
		}

		System.out.println("Done");

	}

}
