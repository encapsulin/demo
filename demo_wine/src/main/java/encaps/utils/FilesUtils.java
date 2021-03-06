package encaps.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesUtils {

	public static void main(String[] args) {
//		FilesUtils 

	}
	
	public static void save(String str) {
		String fn = String.valueOf(System.currentTimeMillis());
		fn = String.format("log/%s.html",fn);
		System.out.println(fn);
		Path path = Paths.get(fn);
		try (BufferedWriter writer = Files.newBufferedWriter(path)){
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public static String read(String fn) {
		System.out.println(fn);
		Path path = Paths.get(fn);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = Files.newBufferedReader(path)){
			String str;
			while( (str = reader.readLine()) != null)
				sb.append(str);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return sb.toString();
	}	
	
	public static void append(String fileName, String str)
			  throws Exception {
			 
			    FileWriter fw = new FileWriter(fileName, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    bw.write(str);
//			    bw.newLine();
			    bw.close();
	}
	
	public static void write(String fileName, String str)
			  throws Exception {
			 
			    FileWriter fw = new FileWriter(fileName, false);
			    BufferedWriter bw = new BufferedWriter(fw);
			    bw.write(str);
//			    bw.newLine();
			    bw.close();
	}	
}
