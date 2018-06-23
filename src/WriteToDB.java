
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

public class WriteToDB {

	public static void writeToTable() throws ClassNotFoundException, SQLException, IOException{
		String dbURL = "jdbc:postgresql://localhost:5432/asl";
		String user = "postgres";
		String password = "password";
		String file = "/Users/bhumi/Documents/Capstone/Testfiles/" + "opint1.txt";
        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection(dbURL, user, password);

        System.err.println("Copying text data rows from stdin");

        CopyManager copyManager = new CopyManager((BaseConnection) con);
        File fileDir = new File(file);
        Reader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));

        copyManager.copyIn("COPY polygontable(geom) FROM STDIN", in );

        System.err.println("Done.");
	}
}
