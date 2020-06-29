/**
 * Copyright (C) 2015, GIAYBAC
 *
 * Released under the MIT license
 */
package com.giaybac.traprange.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.stream.IntStream;

//import org.junit.Test;
import org.junit.jupiter.api.Test;

import com.giaybac.traprange.PDFTableExtractor;
import com.giaybac.traprange.entity.Table;

/**
 *
 * @author Tho Mar 22, 2015 5:36:40 PM
 */
public class TestExtractor {
	@Test
	public void test() throws IOException {
//        PropertyConfigurator.configure(TestExtractor.class.getResource("/com/giaybac/traprange/log4j.properties"));

//        String homeDirectory = System.getProperty("user.dir");

//        String sourceDirectory = Paths.get(homeDirectory, "_Docs").toString();
//        String resultDirectory = Paths.get(homeDirectory, "_Docs", "result").toString();

		String src = "temp/198064.pdf";
		{
			PDFTableExtractor extractor = (new PDFTableExtractor()).setSource(src);

			extractor.exceptLine(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 });
			extractor.exceptLine(IntStream.range(-11, 0).toArray());

			List<Table> tables = extractor.extract();
			try (Writer writer = new OutputStreamWriter(new FileOutputStream(src + ".html"), "UTF-8")) {
				for (Table table : tables) {
					writer.write("Page: " + (table.getPageIdx() + 1) + "\n");
					writer.write(table.toHtml());
					System.out.println(table.toHtml());
				}
			}
			
			try (Writer writer = new OutputStreamWriter(new FileOutputStream(src + ".csv"), "UTF-8")) {
				for (Table table : tables) {
					writer.write("Page: " + (table.getPageIdx() + 1) + "\n");
					writer.write(table.toString());
					System.out.println(table.toString());
				}
			}			
		}
	}
}
