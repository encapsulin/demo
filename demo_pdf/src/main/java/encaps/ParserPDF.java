package encaps;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ParserPDF {

	public static void main(String[] args) throws Exception {
		System.out.println("-=START=-");
		
//		File pathToFile = new File("temp/198064.pdf");
//		PDDocument document = PDDocument.load(pathToFile);
//		PDFTextStripper s = new PDFTextStripper();
//		String content = s.getText(document);
//		System.out.println(content);
		

		System.out.println(Arrays.toString(IntStream.range(0, 10).toArray()));
		System.out.println(Arrays.toString(IntStream.range(-10, 0).toArray()));
		System.out.println("-=FINISH=-");
	}

}
