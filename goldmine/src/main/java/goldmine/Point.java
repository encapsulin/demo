package goldmine;

public class Point {
	public Integer col;
	public Integer row;
	public Integer value = 0;

	@Override
	public String toString() {
		return "(" + col + "," + row + ")=" + value;
	}
}
