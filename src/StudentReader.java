import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class StudentReader {

	private StudentReader(){

	}

	public static ArrayList<Student> read(){
		ArrayList<Student> students = new ArrayList<>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("input.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				students.add(new Student(getGpaFromLine(line), getSatFromLine(line)));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

		return students;
	}

	// super hacky helper function to parse the SAT from a given line
	private static int getSatFromLine(String line) {
		return Integer.parseInt(line.split(" ")[0]);
	}

	// super hacky helper function to parse the GPA from a given line
	private static float getGpaFromLine(String line) {
		return Float.parseFloat(line.split(" ")[1]);
	}
}
