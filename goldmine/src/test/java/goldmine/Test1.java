package goldmine;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test1 {

	@Test
	public void test() {

		assert (Goldmine.solve("matrix.txt") == 12);

		assert (Goldmine.solve("matrix4.txt") == 83);

		assert (Goldmine.solve("matrix2.txt") == 32);

		assert (Goldmine.solve("matrix3.txt") == 16);

	}

}
