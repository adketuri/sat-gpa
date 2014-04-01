
public class Student {

	private float gpa;
	private int sat;
	
	public Student(float gpa, int sat){
		this.setGpa(gpa);
		this.setSat(sat);
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

	@Override
	public String toString() {
		return "[SAT Score: " + getSat() + " GPA: " + getGpa() + "]";
		
	}

	// returns true if this student compared to s2 has > GPA and < SAT
	public boolean hasBetterSatAndWorseGpa(Student s2) {
		return getSat() > s2.getSat() && getGpa() < s2.getGpa();
	}
}
