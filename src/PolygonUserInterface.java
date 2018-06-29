import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;

import org.postgis.PGbox3d;
import org.postgis.Point;

public class PolygonUserInterface implements ActionListener {
	long noOfObjsInserted = 0;
	String inputFilePath = "";

	private JLabel lblFilePath;
	private JLabel lblQueryLLB;
	private JLabel lblQueryURT;
	private JLabel lblSaveDeleteOutput;
	private JLabel lblSaveDeleteTime;
	private JLabel lblQueryOutput;
	private JLabel lblQueryTotalTime;
	private JLabel lblQueryFetchTime;
	private JLabel lblCompareOP;

	private JButton btnSaveObjects;
	private JButton btnDeleteObjects;
	private JButton btnFindIntersectingObjects;
	private JButton btnTutorial = new JButton("Tutorial");

	private JTextField textfieldFilePath;

	private JTextField textfieldLLBX;// lower left bottom corner of the box as a
										// Point object
	private JTextField textfieldLLBY;
	private JTextField textfieldLLBZ;
	private JTextField textfieldURTX;
	private JTextField textfieldURTY;
	private JTextField textfieldURTZ;
	

	PolygonUserInterface() {
		JFrame f = new JFrame("Database Interface");
		
		btnSaveObjects = new JButton("Save Objects");
		btnSaveObjects.setBounds(208, 144, 140, 40);
		
		lblFilePath = new JLabel();
		lblFilePath.setText("Enter file path :");
		lblFilePath.setBounds(36, 102, 100, 30);
		
		lblSaveDeleteOutput = new JLabel();
		lblSaveDeleteOutput.setBounds(50, 183, 450, 30);
		
		textfieldFilePath = new JTextField();
		textfieldFilePath.setText("/Users/bhumi/Documents/Capstone/inputFiles/MyMeshes0352.txt");
		textfieldFilePath.setBounds(161, 102, 508, 30);

		// for query
		btnFindIntersectingObjects = new JButton("Find Intersecting Objects");
		btnFindIntersectingObjects.setBounds(268, 426, 236, 40);
		
		lblQueryLLB = new JLabel();
		lblQueryLLB.setText("Enter Lower Left Bottom of BB :");
		lblQueryLLB.setBounds(36, 312, 195, 48);

		lblQueryURT = new JLabel();
		lblQueryURT.setText("Enter Upper Right Top of BB :");
		lblQueryURT.setBounds(36, 365, 195, 40);

		// empty label which will show event after button clicked
		lblQueryOutput = new JLabel();
		lblQueryOutput.setBounds(50, 478, 673, 32);
		
		textfieldLLBX = new JTextField();
		textfieldLLBX.setText("1");
		textfieldLLBX.setBounds(228, 322, 71, 30);

		// add to frame
		f.getContentPane().add(lblSaveDeleteOutput);
		f.getContentPane().add(textfieldFilePath);
		f.getContentPane().add(lblFilePath);
		f.getContentPane().add(btnSaveObjects);
		f.getContentPane().add(lblQueryLLB);
		f.getContentPane().add(textfieldLLBX);
		f.getContentPane().add(lblQueryURT);
		f.getContentPane().add(btnFindIntersectingObjects);
		f.getContentPane().add(lblQueryOutput);

		f.setSize(787, 641);
		f.getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 242, 792, 12);
		f.getContentPane().add(separator);

		textfieldLLBY = new JTextField();
		textfieldLLBY.setText("2");
		textfieldLLBY.setBounds(311, 322, 71, 30);
		f.getContentPane().add(textfieldLLBY);

		textfieldLLBZ = new JTextField();
		textfieldLLBZ.setText("3");
		textfieldLLBZ.setBounds(394, 322, 71, 30);
		f.getContentPane().add(textfieldLLBZ);

		JLabel lblX = new JLabel("X");
		lblX.setBounds(245, 306, 24, 16);
		f.getContentPane().add(lblX);

		JLabel lblY = new JLabel("Y");
		lblY.setBounds(339, 306, 32, 16);
		f.getContentPane().add(lblY);

		JLabel lblZ = new JLabel("Z");
		lblZ.setBounds(426, 306, 32, 16);
		f.getContentPane().add(lblZ);

		textfieldURTX = new JTextField();
		textfieldURTX.setText("7");
		textfieldURTX.setBounds(228, 372, 74, 30);
		f.getContentPane().add(textfieldURTX);

		textfieldURTY = new JTextField();
		textfieldURTY.setText("8");
		textfieldURTY.setBounds(311, 372, 71, 30);
		f.getContentPane().add(textfieldURTY);

		textfieldURTZ = new JTextField();
		textfieldURTZ.setText("9");
		textfieldURTZ.setBounds(394, 372, 71, 30);
		f.getContentPane().add(textfieldURTZ);

		JLabel lblNewLabel = new JLabel("Find Intersecting Objects with Bounding Box");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setBounds(32, 266, 414, 34);
		f.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Store/ Delete Objects");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel_1.setBounds(32, 51, 290, 29);
		f.getContentPane().add(lblNewLabel_1);

		btnDeleteObjects = new JButton("Delete Objects");
		btnDeleteObjects.setBounds(426, 144, 140, 40);
		f.getContentPane().add(btnDeleteObjects);

		lblQueryTotalTime = new JLabel();
		lblQueryTotalTime.setBounds(50, 567, 673, 32);
		f.getContentPane().add(lblQueryTotalTime);
		btnTutorial.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnTutorial.setBounds(664, 6, 117, 29);
		f.getContentPane().add(btnTutorial);

		lblSaveDeleteTime = new JLabel();
		lblSaveDeleteTime.setBounds(50, 213, 450, 30);
		f.getContentPane().add(lblSaveDeleteTime);
		
		lblQueryFetchTime = new JLabel();
		lblQueryFetchTime.setBounds(50, 523, 673, 32);
		f.getContentPane().add(lblQueryFetchTime);
		
		lblCompareOP = new JLabel("");
		lblCompareOP.setBounds(278, 464, 503, 23);
		f.getContentPane().add(lblCompareOP);
		f.setVisible(true);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// action listener
		btnSaveObjects.addActionListener(this);
		btnDeleteObjects.addActionListener(this);
		btnFindIntersectingObjects.addActionListener(this);
		btnTutorial.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		clearAllLabeles();
		inputFilePath = textfieldFilePath.getText();
		if (e.getSource().equals(btnTutorial)) {
			String tutorialMessage = "User can perform 3 operations\n"
					+ "1. Save - Enter the location of input file, and press";
			JOptionPane.showMessageDialog(null, tutorialMessage, "InfoBox: " + "Tutorial",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource().equals(btnSaveObjects)) {
			if (inputFilePath.isEmpty() || inputFilePath.length() == 0) {
				lblSaveDeleteOutput.setText("Please enter a file path for the input file of objects to be stored");
			} else {
				long startTime = System.nanoTime();
				try {
					PolygonPreprocessing.generatePolygonFile(inputFilePath, true);
				} catch (IOException e2) {
					lblSaveDeleteOutput.setText("Error: Problem occured during object conversion to TIN");
					System.err.println(e2.getMessage());
				}
				try {
					noOfObjsInserted = WriteToDB.writeToTable();
					long endTime = (System.nanoTime() - startTime) / 1000000;
					lblSaveDeleteTime.setText("Time taken = " + endTime + " millisecs");
					lblSaveDeleteOutput.setText( noOfObjsInserted + " Objects inserted into the database");
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					lblSaveDeleteOutput.setText("Error: Problem encountered during storing objects to database");
				}

			}
		}

//		} else if (e.getSource().equals(btnDeleteObjects)) {
////			clearAllLabeles();
//			if (inputFilePath.isEmpty() || inputFilePath.length() == 0) {
//				lblSaveDeleteOutput.setText("Please enter a file path for the input file of objects to be deleted");
//			} else {
//				long startTime = System.nanoTime();
//				try {
//					Preporcessing.generateTINFile(inputFilePath, false);
//				} catch (IOException e2) {
//					lblSaveDeleteOutput.setText("Error: Problem occured during object conversion to TIN");
//					System.err.println(e2.getMessage());
//				}
//				try {
//					int objsDeleted = DBInteraction.deleteObjsFromTable();
//					long endTime = (System.nanoTime() - startTime) / 1000000;
//					lblSaveDeleteTime.setText("Time taken = " + endTime + " millisecs");
//					lblSaveDeleteOutput.setText(objsDeleted + " Objects deleted from database");
//				} catch (ClassNotFoundException | SQLException | IOException e1) {
//					System.out.println(e1.getMessage());
//					lblSaveDeleteOutput.setText("Path or File not valid");
//				}
//			}
//		}

//		else if (e.getSource().equals(btnFindIntersectingObjects)) {
////			clearAllLabeles();
//			SpatialQuery q = new SpatialQuery();
//			PGbox3d inputBoundingBox = null;
//			try {
//
//				double x = Double.parseDouble(textfieldLLBX.getText());
//				double y = Double.parseDouble(textfieldLLBY.getText());
//				double z = Double.parseDouble(textfieldLLBZ.getText());
//
//				double x1 = Double.parseDouble(textfieldURTX.getText());
//				double y1 = Double.parseDouble(textfieldURTY.getText());
//				double z1 = Double.parseDouble(textfieldURTZ.getText());
//
//				inputBoundingBox = new PGbox3d(new Point(x, y, z), new Point(x1, y1, z1));
//
//				long startTime = System.nanoTime();
//				long onlyfetchTime = q.findIntersectingObjs(inputBoundingBox);
//				long endTime = (System.nanoTime() - startTime) / 1000000;
//				lblQueryFetchTime.setText("Time taken only for fetching results = " + onlyfetchTime + " millisecs");
//				lblQueryTotalTime.setText("Total Time taken including writing output file = " + endTime + " millisecs");
//				lblQueryOutput.setText("Intersecting objects are stored in file =" + SpatialQuery.queryOutputFile);
//			} catch (NumberFormatException ignore) {
//				lblQueryOutput.setText("Invalid Input - please enter valid values for bounding box");
//			} catch (ClassNotFoundException e1) {
//				lblQueryOutput.setText("Error finding the intersection");
//			}
//			
//		}
		
	}

	public void clearAllLabeles() {
		lblSaveDeleteOutput.setText("");
		lblQueryOutput.setText("");
		lblQueryFetchTime.setText("");
		lblQueryTotalTime.setText("");
		lblSaveDeleteTime.setText("");
		lblCompareOP.setText("");
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PolygonUserInterface();
			}
		});
	}
}