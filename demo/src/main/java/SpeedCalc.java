
import java.util.*;
import java.util.stream.Stream;

public class SpeedCalc {

	public static void main(String[] args) {
		System.out.println("-=START=-");
		SpeedCalc wdc = new SpeedCalc();
		List<Integer> data = new ArrayList<>();
		System.out.println("populate");
		for (int i = 0; i < 10000; i++)
			data.add(i);

		System.out.println("\n\n######################## calculate 1");
		double d = 0;
		long lStart = System.currentTimeMillis();
		d = wdc.process(data, 1);
		long lStop = System.currentTimeMillis();
		System.out.printf("\nStream: %f", d);
		System.out.printf("\n%d diff %d = %d ms, %d sec", lStart, lStop, lStop - lStart, (lStop - lStart) / 1000);

		lStart = System.currentTimeMillis();
		d = wdc.process(data, 2);
		lStop = System.currentTimeMillis();
		System.out.printf("\nParall: %f", d);
		System.out.printf("\n%d diff %d = %d ms, %d sec", lStart, lStop, lStop - lStart, (lStop - lStart) / 1000);

		System.out.println("\n\n######################## calculate 2");
		d = 0;
		lStart = System.currentTimeMillis();
		d = wdc.process2(data, 1);
		lStop = System.currentTimeMillis();
		System.out.printf("\nStream: %f", d);
		System.out.printf("\n%d diff %d = %d ms, %d sec", lStart, lStop, lStop - lStart, (lStop - lStart) / 1000);

		lStart = System.currentTimeMillis();
		d = wdc.process2(data, 2);
		lStop = System.currentTimeMillis();
		System.out.printf("\nParall: %f", d);
		System.out.printf("\n%d diff %d = %d ms, %d sec", lStart, lStop, lStop - lStart, (lStop - lStart) / 1000);

		System.out.println("\n\n-=FINISH=-");

		// test();
	}

	static void test() {
//		Stream<String> stream = Stream.of("a","b","c","d");
		List<String> list = new ArrayList<>();
		for (char c = '0'; c <= 'z'; c++)
			list.add(String.valueOf(c));
		String str = "";

		str = list.stream().reduce("", (a, b) -> a + b);
		System.out.println(str);

		str = list.parallelStream().reduce("", (a, b) -> a + b);
		System.out.println(str);
	}

	private double process(List<Integer> data, int i) {
		if (i == 1)
			return data.stream().map(a -> process(a)).count();// reduce((a,b)->a+b).orElseThrow();
		else
			return data.parallelStream().map(a -> process(a)).count();
	}

	double process(int i) {
		double d = 1;
		try {
			// Thread.sleep(10);
			for (int j = 0; j < 1000; j++)
				d *= Math.PI * Math.random() * Math.cos(0.1) + System.currentTimeMillis();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return d;
	}

	private double process2(List<Integer> data, int i) {
		if (i == 1)
			return data.stream().map(a -> process2(a)).reduce((a, b) -> a + b).orElse(0.0);
		else
			return data.parallelStream().map(a -> process2(a)).reduce((a, b) -> a + b).orElse(0.0);
	}
	
	double process2(int i) {
		double d = 1;

		for (int j = 0; j < i; j++)
			d += Math.PI * Math.cos(0.1);

		return d;
	}

}
