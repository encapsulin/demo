

import java.util.*;

public class SpeedCalc {

	public static void main(String[] args) {
		System.out.println("-=START=-");
		SpeedCalc wdc = new SpeedCalc();
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < 4000; i++)
			data.add(i);
		long lStart = System.currentTimeMillis();
		wdc.process(data, 1);
		long lTime = (System.currentTimeMillis() - lStart) / 1000;
		System.out.println(lTime);

		lStart = System.currentTimeMillis();
		wdc.process(data, 2);
		lTime = (System.currentTimeMillis() - lStart) / 1000;
		System.out.println(lTime);

		System.out.println("-=FINISH=-");
	}

	private void process(List<Integer> data, int i) {
		if (i == 1)
			data.stream().map(a -> process(a)).count();
		else
			data.parallelStream().map(a -> process(a)).count();
	}

	int process(int i) {
		try {
			// Thread.sleep(10);
			double d = 0;
			for (double j = 0; j < 5000000; j++)
				d *= Math.PI;
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return i + 1;
	}

}
