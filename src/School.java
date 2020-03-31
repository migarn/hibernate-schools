import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="schools")
public class School {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@Column
	private String address;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="school_id")
	private Set<SchoolClass> classes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public Set<SchoolClass> getClasses() {
		return classes;
	}
	
	public void setClasses(Set<SchoolClass> classes) {
		this.classes = classes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return "School: " + getName();
	}
}
