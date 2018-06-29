import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PolygonPreprocessing {

	ArrayList<String> coordinateList = new ArrayList<String>(); 
	Set<Integer> polygonVertices = new HashSet<Integer>();

	static void generatePolygonFile(String filePath, boolean addTerminationChar) throws IOException {
		PolygonPreprocessing obj = new PolygonPreprocessing();
		//		filePath = "/Users/bhumi/Documents/Capstone/testFiles/polycarsmesh.txt";
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		
		GeneratePolygonCSV.deleteOldFile();

		String st = new String();
		StringBuffer metadata = new StringBuffer();
		
		while((st = br.readLine()) != null){

			if(st.length()!=0 && st.charAt(0)=='v'){
				obj.storeCoordinates(st);
			}
			else if(st.length()!=0 && st.charAt(0)=='f'){
				obj.storeVertices(st);

			}
			else if(st.length()!=0 && st.charAt(0)=='o'){
				//form polygon from vertices
				if(!obj.polygonVertices.isEmpty()){
					int firstVertex = obj.polygonVertices.iterator().next();
					String firstCoord = obj.coordinateList.get(firstVertex);//get coord val of 1st vertex
					StringBuffer polygon = new StringBuffer("POLYGON ((");
					for(Integer v : obj.polygonVertices){
						polygon.append(obj.coordinateList.get(v)+',');
					}
					polygon.append(firstCoord);
					polygon.append("))");

					GeneratePolygonCSV.writeFile(polygon, metadata);

					//create arrayList for coordinates of this object
					obj.coordinateList = new ArrayList<String>();		
					obj.polygonVertices = new HashSet<Integer>();
					metadata = new StringBuffer();
				}
			}
			
			metadata.append(st + "\\n");
		}
		//write last record
		StringBuffer polygon = new StringBuffer("POLYGON ((");
		int firstVertex = obj.polygonVertices.iterator().next();
		String firstCoord = obj.coordinateList.get(firstVertex);
		for(Integer v : obj.polygonVertices){
			polygon.append(obj.coordinateList.get(v)+',');
		}
		polygon.append(firstCoord);
		polygon.append("))");

		GeneratePolygonCSV.writeFile(polygon, metadata);
		
		if (addTerminationChar) {
			GeneratePolygonCSV.addFileTermination("\\.");
		}

		br.close();
	}

	private void storeVertices(String st){
		String s = st.substring(2);// ignore f
		String[] verticesArray = s.split(" ");

		String[] arr0 = verticesArray[0].split("//");// take the first and ignore 2nd
		String[] arr1 = verticesArray[1].split("//");// take the first and ignore 2nd
		String[] arr2 = verticesArray[2].split("//");// take the first and ignore 2nd

		polygonVertices.add(Integer.parseInt(arr0[0]));
		polygonVertices.add(Integer.parseInt(arr1[0]));
		polygonVertices.add(Integer.parseInt(arr2[0]));	

	}

	private void storeCoordinates(String st) {
		String s= st.substring(2);
		String s1= s.trim();
		coordinateList.add(s1);
	}

}
