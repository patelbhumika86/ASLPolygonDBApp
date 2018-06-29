import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PolygonGenerateCSV {
	public static String outputFileName = "/Users/bhumi/Documents/Capstone/Testfiles/" + "opPolygon.txt";
	static String ErrorLog = "/Users/bhumi/Documents/Capstone/Testfiles/" + "Err1.txt";
	
	public static void writeFile(StringBuffer polygon, StringBuffer metadata) {
		if (polygon.length() != 0) {

			try (FileWriter fw = new FileWriter(outputFileName, true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
					out.println(polygon+ "|" + metadata);//append meta data

			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public static void deleteOldFile() {
		File file = new File(outputFileName);
		 if (file.exists()) {
		     file.delete();
		     }
	}
	
	public static void addFileTermination(String string) {
		try (FileWriter fw = new FileWriter(outputFileName, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
		out.println(string);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
