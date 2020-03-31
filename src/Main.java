import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		main.printSchools();
		main.addNewData();
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
		uj.setId(0);
		uj.setName("UJ");
		uj.setAddress("ul. Go³êbia 24");
		
		SchoolClass hist = new SchoolClass();
		hist.setId(0);
		hist.setProfile("historia");
		hist.setCurrentYear(2);
		hist.setStartYear(2015);
		
		SchoolClass biol = new SchoolClass();
		biol.setId(0);
		biol.setProfile("biologia");
		biol.setCurrentYear(4);
		biol.setStartYear(2013);
	
		Student s1 = new Student();
		s1.setId(0);
		s1.setName("Anna");
		s1.setSurname("Baran");
		s1.setPesel("93451312354");
		
		Student s2 = new Student();
		s2.setId(0);
		s2.setName("Jerzy");
		s2.setSurname("Kowalik");
		s2.setPesel("93851212551");
		
		Student s3 = new Student();
		s3.setId(0);
		s3.setName("Alina");
		s3.setSurname("Bêben");
		s3.setPesel("91871612853");
		
		Set<Student> histStudents = new HashSet<Student>();
		histStudents.add(s1);
		histStudents.add(s2);
		hist.setStudents(histStudents);
		
		Set<Student> biolStudents = new HashSet<Student>();
		biolStudents.add(s3);
		biol.setStudents(biolStudents);
		
		Set<SchoolClass> ujClasses = new HashSet<SchoolClass>();
		ujClasses.add(hist);
		ujClasses.add(biol);
		
		uj.setClasses(ujClasses);
		
		Transaction transaction = session.beginTransaction();
		session.save(uj);
		transaction.commit();
	}
}
