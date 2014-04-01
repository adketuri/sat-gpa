import java.util.ArrayList;


public class SatGpa {

	public static ArrayList<Student> best = new ArrayList<Student>();

	public static void main(String[] args){
		ArrayList<Student> result = new ArrayList<Student>();
		ArrayList<Student> students = StudentReader.read();
		
		for (int i = 0; i < students.size(); i++){
			System.out.println("STARTING WITH STUDENT: " + students.get(i));
			result.add(students.get(i));
			findStudentWithBetterGpaAndWorseSat(students.get(i), students, result, "   ");
			result.clear();
			students.clear();
			students = StudentReader.read();
		}
		System.out.println("As students' SAT scores increase, their GPA drops:");
		
		for (Student s : best){
			System.out.println(s);
		}
		System.out.println("Conclusion: SAT exams are a sham.");
	}

	private static void findStudentWithBetterGpaAndWorseSat(Student s1, ArrayList<Student> students, ArrayList<Student> result, String tab) {
		
		for (int i = 0; i < students.size(); i++){
			final Student s2 = students.get(i);
			if (s1 != s2 && s2.hasBetterSatAndWorseGpa(s1)){
				System.out.println(tab + s2 + " fulfills requirement for " + s1);
				
				// update our pending result list...
				students.remove(s2);
				result.add(s2);
				
				// determine if it's longer than our best so far
				if (result.size() > best.size()){
					best.clear();
					best.addAll(result);
					
				}
				
				// recursively search for subsequent students that fulfill our requirement
				findStudentWithBetterGpaAndWorseSat(s2, students, result, tab + "   ");
				
				// reset list back to its previous state
				students.add(i, s2);
				result.remove(s2);

			} else {
				System.out.println(tab + s2 + " does not fulfill requirement for " + s1);

			}
		}
		System.out.println(tab + "=====");
	}
}
