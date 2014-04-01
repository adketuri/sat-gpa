import java.util.ArrayList;


public class SatGpa {

	public static void main(String[] args){
		ArrayList<Student> students = StudentReader.read();
		
		students.get(0).display();
	}
}
