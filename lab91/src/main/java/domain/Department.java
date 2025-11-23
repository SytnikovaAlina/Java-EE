package domain;

/**
* Класс данных о кафедрах
*/

public class Department {
	
	private Long id;
	private String name;
	private String shortname;
	private String head;
	private String telephone;
	
	private Long idF;
	private Facultet fac;
	
	// Конструкторы
	public Department(){}
	public Department(String name, String shortname, String head, String telephone, Facultet fac) {
		this.name = name;
		this.shortname = shortname;
		this.head = head;
		this.telephone = telephone;
		this.fac = fac;
	}
	public Department(String name, String shortname, String head, String telephone, Long idF, Facultet fac) {
		this.name = name;
		this.shortname = shortname;
		this.head = head;
		this.telephone = telephone;
		this.idF = idF;
		this.fac = fac;
	}
	
	public Department(Long id, String name, String shortname, String head, String telephone, Long idF, Facultet fac) {
		this.id = id;
		this.name = name;
		this.shortname = shortname;
		this.head = head;
		this.telephone = telephone;
		this.idF = idF;
		this.fac = fac;
	}
	
	
	// геттеры и сеттеры
	
	public Facultet fac() {
		return fac;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	
	
	
	public String getTel() {
		return telephone;
	}
	public void setTel(String telephone) {
		this.telephone = telephone;
	}

	
	public String getFacultet() {
		return fac.getName();
	}

	public void setFacultet(Facultet fac) {
		this.fac = fac;
	}
	
	public Long getIdF() {
		return idF;
	}
	
	public void setIdF(Long idF) {
		this.idF = idF;
	}
	
	
	@Override
	public String toString() {
		
		return "Кафедра {" + "Id = " + id +
				", Название - " + name +
				", Аббревиатура - " + shortname +
				", Заведующий = " + head +
				", Телефоне = " + telephone +
				", Факультет = " + getFacultet() +
				"}";
	}

}





