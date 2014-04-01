
public class Student implements Comparable<Student> {

	private float gpa;
	private int sat;
	
	public Student(float gpa, int sat){
		this.setGpa(gpa);
		this.setSat(sat);
	}

	@Override
	public int compareTo(Student s) {
		return 0;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public int getSat() {
		return sat;
	}

	public void setSat(int sat) {
		this.sat = sat;
	}

	public void display() {
		System.out.println("SAT Score: " + getSat() + " GPA: " + getGpa());
		
	}
}
