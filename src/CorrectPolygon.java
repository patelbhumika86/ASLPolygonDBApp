import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CorrectPolygon {
	
	ArrayList<String> coordinateList = new ArrayList<String>(); 
	Set<Integer> vertices = new HashSet<Integer>();
	
	static void generatePolygonFile(String filePath, boolean addTerminationChar) throws IOException {
		CorrectPolygon obj = new CorrectPolygon();
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}

		GeneratePolygonCSV.deleteOldFile();
		String st = new String();
//		StringBuffer metadata = new StringBuffer();
		while((st = br.readLine()) != null){
			
			if(st.length()!=0 && st.charAt(0)=='v'){
				obj.storeCoordinates(st);
			}
			else if(st.length()!=0 && st.charAt(0)=='f'){
				obj.storeVertices(st);
				
			}
			else if(st.length()!=0 && st.charAt(0)=='o'){
				
				if(!obj.vertices.isEmpty()){
					int firstVertex = obj.vertices.iterator().next();
					String firstCoord = obj.coordinateList.get(firstVertex);
				StringBuffer polygon = new StringBuffer("POLYGON ((");
				for(Integer v : obj.vertices){
					polygon.append(obj.coordinateList.get(v)+',');
				}
							
//				polygon.deleteCharAt(polygon.length()-1);
				polygon.append(firstCoord);
				polygon.append("))");
//				System.out.println(polygon);
				GeneratePolygonCSV.writeFile(polygon);
				
				//create arrayList for coordinates of this object
				obj.coordinateList = new ArrayList<String>();		
				obj.vertices = new HashSet<Integer>();
				}
			}
		}
		//write last record
				StringBuffer polygon = new StringBuffer("POLYGON ((");
				int firstVertex = obj.vertices.iterator().next();
				String firstCoord = obj.coordinateList.get(firstVertex);
				for(Integer v : obj.vertices){
					polygon.append(obj.coordinateList.get(v)+',');
				}
//				polygon.deleteCharAt(polygon.length()-1);
				polygon.append(firstCoord);
				polygon.append("))");
//				System.out.println(polygon);
				GeneratePolygonCSV.writeFile(polygon);
				
				GeneratePolygonCSV.writeFile(new StringBuffer("\\."));
				try {
					WriteToDB.writeToTable();
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				br.close();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		CorrectPolygon obj = new CorrectPolygon();
		
		String fileName = "/Users/bhumi/Documents/Capstone/testFiles/MyMeshes0732.txt";
		File file = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String st = new String();
		while((st = br.readLine()) != null){
			
			if(st.length()!=0 && st.charAt(0)=='v'){
				obj.storeCoordinates(st);
			}
			else if(st.length()!=0 && st.charAt(0)=='f'){
				obj.storeVertices(st);
				
			}
			else if(st.length()!=0 && st.charAt(0)=='o'){
				
				if(!obj.vertices.isEmpty()){
					int firstVertex = obj.vertices.iterator().next();
					String firstCoord = obj.coordinateList.get(firstVertex);
				StringBuffer polygon = new StringBuffer("POLYGON ((");
				for(Integer v : obj.vertices){
					polygon.append(obj.coordinateList.get(v)+',');
				}
				
				
//				polygon.deleteCharAt(polygon.length()-1);
				polygon.append(firstCoord);
				polygon.append("))");
//				System.out.println(polygon);
				GeneratePolygonCSV.writeFile(polygon);
				
				//create arrayList for coordinates of this object
				obj.coordinateList = new ArrayList<String>();		
				obj.vertices = new HashSet<Integer>();
				}
			}
		}
		//write last record
		StringBuffer polygon = new StringBuffer("POLYGON ((");
		int firstVertex = obj.vertices.iterator().next();
		String firstCoord = obj.coordinateList.get(firstVertex);
		for(Integer v : obj.vertices){
			polygon.append(obj.coordinateList.get(v)+',');
		}
//		polygon.deleteCharAt(polygon.length()-1);
		polygon.append(firstCoord);
		polygon.append("))");
//		System.out.println(polygon);
		GeneratePolygonCSV.writeFile(polygon);
		
		GeneratePolygonCSV.writeFile(new StringBuffer("\\."));
		WriteToDB.writeToTable();

		br.close();
	}

	private void storeVertices(String st){
		String s = st.substring(2);// ignore f
		String[] verticesArray = s.split(" ");
		
		String[] arr0 = verticesArray[0].split("//");// take the first and
		// ignore 2nd
		String[] arr1 = verticesArray[1].split("//");// take the first and
		// ignore 2nd
		String[] arr2 = verticesArray[2].split("//");// take the first and
		// ignore 2nd

		vertices.add(Integer.parseInt(arr0[0]));
		vertices.add(Integer.parseInt(arr1[0]));
		vertices.add(Integer.parseInt(arr2[0]));	
		
	}
	
	private void storeCoordinates(String st) {
		String s= st.substring(2);
		String s1= s.trim();
		coordinateList.add(s1);
	}
	
}
