package goldmine;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("start");

//		int[][] matrix0 = { { 1, 9, 2, 1 }, { 8, 3, 7, 8 }, { 4, 6, 5, 4 } };
//		show(matrix0);
//		solve(matrix0);

		int[][] matrix = readMatrixFromFile("matrix.txt");
		show(matrix);
		solve(matrix);
		
		int[][] matrix2 = readMatrixFromFile("matrix2.txt");
		show(matrix2);
		solve(matrix2);		

		System.out.println("stop");
	}

	public static int[][] readMatrixFromFile(String fn) {

		int[][] matrix = null;

		try {
			List<String> lines = Files.readAllLines(Paths.get(fn));
			int rows = lines.size();
			String regexSplit = "[^\\d]";
			int cols = lines.get(0).split(regexSplit).length;
			matrix = new int[rows][cols];

			int row = 0;
			for (String line : lines) {
				int col = 0;
				String[] values = line.split(regexSplit);
				for (String val : values)
					matrix[row][col++] = Integer.parseInt(val.trim());
				row++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		// int[][] matrix0 = { { 1, 9, 2, 1 }, { 8, 3, 7, 8 }, { 4, 6, 5, 4 } };
		if (matrix != null)
			return matrix;
		else
			return new int[][] { { 0 } };
	}

	public static void show(int[][] matrix) {
		for (int[] row : matrix) {
			for (int value : row)
				System.out.printf("%d ", value);
			System.out.println();
		}

	}

	public static void solve(int[][] matrix) {

		List<Point> list = new ArrayList<>();
		int cols = matrix[0].length;
		int rows = matrix.length;

		for (int col = 0; col < matrix[0].length; col++) {
			Point point = findMaxRowForColumn(matrix, col);
			list.add(point);
		}

		System.out.println(list);
	}

	public static Point findMaxRowForColumn(int[][] matrix, int col) {
		Point point = new Point();
		int iRow = 0;
		for (int[] row : matrix) {
			if (point.value < row[col]) {
				point.value = row[col];
				point.col = col;
				point.row = iRow;
			}
			iRow++;
		}
		return point;
	}
}

class Point {
	public int col = 0;
	public int row = 0;
	public int value = 0;

	@Override
	public String toString() {
		return "\nPoint [" + col + "," + row + "]:" + value;
	}
}
