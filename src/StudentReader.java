import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class StudentReader {

	private StudentReader(){

	}

	public static ArrayList<Student> read(String filename){
		
		ArrayList<Student> students = new ArrayList<>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(" ")){
					students.add(new Student(getGpaFromLine(line), getSatFromLine(line)));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

		return students;
	}

	// hacky helper function to parse the SAT from a given line
	private static int getSatFromLine(String line) {
		return Integer.parseInt(line.split(" ")[0]);
	}

	// hacky helper function to parse the GPA from a given line
	private static float getGpaFromLine(String line) {
		return Float.parseFloat(line.split(" ")[1]);
	}
}
