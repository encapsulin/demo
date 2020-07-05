package encaps.utils;

import java.awt.Rectangle;
import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {

	public static void main(String[] args) throws Exception {
		ocr("log/screen.png");

	}
	
	public static String ocr(String fn) {
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
		tesseract.setLanguage("eng");
//		tesseract.setPageSegMode(1);
//		tesseract.setOcrEngineMode(1);
//		tesseract.setHocr(true);
		String text = "";
		try {
			text = tesseract.doOCR(new File(fn), new Rectangle(1200, 200));
		} catch (TesseractException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(text);
		return text;
	}

}
