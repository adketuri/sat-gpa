import java.util.ArrayList;

/**
 * Main class for application. Run with:
 * 	 java SatGpa <file> [-d]
 * @author Andrew Keturi
 */

public class SatGpa {

	private static ArrayList<Student> best = new ArrayList<Student>();
	private static boolean debug = false;

	public static void main(String[] args){

		String filename = "";
		if (args == null || args.length == 0){
			System.out.println("Usage: java -jar SatGpa.jar <file> [-d]");
			System.exit(0);
		} else {
			filename = args[0];
			if (args.length > 1){
				if (args[1].equals("-d")){
					debug = true;
				}
			}
		}
		final ArrayList<Student> localBest = new ArrayList<Student>();
		final ArrayList<Student> availableStudents = new ArrayList<Student>();
		final ArrayList<Student> studentDictionary = StudentReader.read(filename);

		// for each student, we will check if subsequent students have a greater SAT and lower GPA
		for (int i = 0; i < studentDictionary.size(); i++){

			// start off with a fresh list of all students and a single entry added to result
			availableStudents.clear();
			availableStudents.addAll(studentDictionary);

			if (debug) System.out.println("STARTING WITH STUDENT: " + availableStudents.get(i));

			localBest.add(availableStudents.get(i));
			findStudentWithBetterGpaAndWorseSat(availableStudents.get(i), availableStudents, localBest, "   ");
			localBest.clear();

		}

		if (best.size() > 1){
			System.out.println("As students' SAT scores increase, their GPA drops:");

			for (Student s : best){
				System.out.println(s);
			}
			System.out.println("Conclusion: SAT exams are a sham.");
		} else {
			System.out.println("No negative correlation for this data set!");
			System.out.println("Perhaps there aren't enough entries or our hypothesis is incorrect.");
		}
	}

	/**
	 *  iterate through all other students in search for one with a better SAT/worse GPA
	 * @param s1 - student with which we are comparing to all other students
	 * @param availableStudents - available students to pick from
	 * @param localBest - list of students in current "streak"
	 * @param tab - a space character, for aid in debugging
	 */
	private static void findStudentWithBetterGpaAndWorseSat(Student s1, ArrayList<Student> availableStudents, ArrayList<Student> localBest, String tab) {

		for (int i = 0; i < availableStudents.size(); i++){
			final Student s2 = availableStudents.get(i);
			if (s1 != s2 && s2.hasBetterSatAndWorseGpa(s1)){
				if (debug) System.out.println(tab + s2 + " fulfills requirement for " + s1);

				// update our pending best list...
				availableStudents.remove(s2);
				localBest.add(s2);

				// determine if it's longer than our best so far
				if (localBest.size() > best.size()){
					if (debug) System.out.println(tab + "Found new best! Size: " + best.size());

					// if so, our new best becomes the local best
					best.clear();
					best.addAll(localBest);

				}

				// recursively search for subsequent students that fulfill our requirement
				findStudentWithBetterGpaAndWorseSat(s2, availableStudents, localBest, tab + "   ");

				// reset list back to its previous state
				availableStudents.add(i, s2);
				localBest.remove(s2);

			} else {
				if (debug) System.out.println(tab + s2 + " does not fulfill requirement for " + s1);

			}
		}
	}
}
