import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		main.printSchools();
		main.close();
	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}

	private void printSchools() {
		Criteria crit = session.createCriteria(School.class);
		List<School> schools = crit.list();

		System.out.println("### Schools and classes");
		for (School school : schools) {
			System.out.println(school);
			for (SchoolClass schoolClass : school.getClasses()) {
				System.out.println(schoolClass + "\n    Start Year: " + schoolClass.getStartYear() + "\n    Current Year: " + schoolClass.getCurrentYear());
				System.out.println("    > Students:");
				for (Student student : schoolClass.getStudents()) {
					System.out.println("      " + student);
				}
			}
		}
	}
	
	private void addNewData() {
		School uj = new School();
		uj.setId(3L);
		uj.setName("UJ");
		uj.setAddress("ul. Go³êbia 24");
	
		
	}
}
