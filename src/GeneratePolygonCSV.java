import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GeneratePolygonCSV {
	public static String outputFileName = "/Users/bhumi/Documents/Capstone/Testfiles/" + "opint1.txt";
	static String ErrorLog = "/Users/bhumi/Documents/Capstone/Testfiles/" + "Err1.txt";

	public static void writeFile(StringBuffer polygon) {
		if (polygon.length() != 0) {

			try (FileWriter fw = new FileWriter(outputFileName, true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
				out.println(polygon);

			} catch (IOException e) {
				System.err.println(e.getMessage());
				// exception handling left
			}
		}
	}

	public static void logError(String invalidTriangle) {
		try (FileWriter fw = new FileWriter(ErrorLog, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(invalidTriangle);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteOldFile() {
		File file = new File(outputFileName);
		 if (file.exists()) {
		     file.delete();
		     }
	}

}
