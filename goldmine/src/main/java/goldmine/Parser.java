package goldmine;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

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


}
