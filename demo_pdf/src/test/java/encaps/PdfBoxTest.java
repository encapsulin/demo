package encaps;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

class PdfBoxTest {

	@Test
	void test() throws Exception {
		// fail("Not yet implemented");
		System.out.println("-=START=-");
		testPdfBox();
		System.out.println("-=FINISH=-");
	}

	void testPdfBox() throws Exception {
		File pathToFile = new File("temp/198064.pdf");
		PDDocument document = PDDocument.load(pathToFile);
		PDFTextStripper s = new PDFTextStripper();
		String content = s.getText(document);
		System.out.println(content);
	}
}
