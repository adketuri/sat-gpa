import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Utility class to aid with file reading.
 * 
 * @author Andrew Keturi
 */

public class StudentReader {

	private StudentReader(){

	}

	/**
	 * reads from disk a list of students.
	 * 
	 * @param filename - file to read
	 * @return - an arrayList of students read from the file
	 */
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

	/**
	 *  hacky helper function to parse the SAT score from a given line
	 * @param line - line to parse
	 * @return - integer representation of the student's SAT score
	 */
	private static int getSatFromLine(String line) {
		return Integer.parseInt(line.split(" ")[0]);
	}

	/**
	 * another helperfunction to parse the GPA from a given line
	 * @param line - line to parse
	 * @return - floating point representation of the student's GPA
	 */
	private static float getGpaFromLine(String line) {
		return Float.parseFloat(line.split(" ")[1]);
	}
}
