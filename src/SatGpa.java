import java.util.ArrayList;


public class SatGpa {

	private static ArrayList<Student> best = new ArrayList<Student>();
	private static boolean debug = false;
	
	public static void main(String[] args){
		
		String filename = "";
		if (args == null || args.length == 0){
			System.out.println("Usage: java SatGpa <file> [-d]");
			System.exit(0);
		} else {
			filename = args[0];
			if (args.length > 1){
				if (args[1].equals("-d")){
					debug = true;
				}
			}
		}
		ArrayList<Student> currentBest = new ArrayList<Student>();
		ArrayList<Student> availableStudents = new ArrayList<Student>();
		ArrayList<Student> studentDictionary = StudentReader.read(filename);

		for (int i = 0; i < studentDictionary.size(); i++){
			
			// start off with a fresh list of all students and a single entry added to result
			availableStudents.clear();
			availableStudents.addAll(studentDictionary);
			
			if (debug) System.out.println("STARTING WITH STUDENT: " + availableStudents.get(i));
			
			currentBest.add(availableStudents.get(i));
			findStudentWithBetterGpaAndWorseSat(availableStudents.get(i), availableStudents, currentBest, "   ");
			currentBest.clear();
			
		}
		System.out.println("As students' SAT scores increase, their GPA drops:");
		
		for (Student s : best){
			System.out.println(s);
		}
		System.out.println("Conclusion: SAT exams are a sham.");
	}

	/**
	 *  iterate through all other students in search for one with a better SAT/worse GPA
	 * @param s1 - student with which we are comparing to all other students
	 * @param students - available students to pick from
	 * @param result - list of students in current "streak"
	 * @param tab - a space character, for aid in debugging
	 */
	private static void findStudentWithBetterGpaAndWorseSat(Student s1, ArrayList<Student> students, ArrayList<Student> result, String tab) {
		
		for (int i = 0; i < students.size(); i++){
			final Student s2 = students.get(i);
			if (s1 != s2 && s2.hasBetterSatAndWorseGpa(s1)){
				if (debug) System.out.println(tab + s2 + " fulfills requirement for " + s1);
				
				// update our pending result list...
				students.remove(s2);
				result.add(s2);
				
				// determine if it's longer than our best so far
				if (result.size() > best.size()){
					if (debug) System.out.println(tab + "Found new best! Size: " + best.size());
					// if so, our new best becomes the current result
					best.clear();
					best.addAll(result);
					
				}
				
				// recursively search for subsequent students that fulfill our requirement
				findStudentWithBetterGpaAndWorseSat(s2, students, result, tab + "   ");
				
				// reset list back to its previous state
				students.add(i, s2);
				result.remove(s2);

			} else {
				if (debug) System.out.println(tab + s2 + " does not fulfill requirement for " + s1);

			}
		}
	}
}
