package domain;

/**
* Класс данных о кафедрах
*/

public class Department {
	
	private Long id;
	private String name;
	private String depname;
	private String telephone;
	
	private Long idR;
	private Role role;
	
	// Конструкторы
	public Department(){}
	public Department(String name, String depname, String telephone, Role role) {
		this.name = name;
		this.depname = depname;
		this.telephone = telephone;
		this.role = role;
	}
	public Department(String name, String depname, String telephone, Long idR, Role role) {
		this.name = name;
		this.depname = depname;
		this.telephone = telephone;
		this.idR = idR;
		this.role = role;
	}
	
	public Department(Long id, String name, String depname, String telephone, Long idR, Role role) {
		this.id = id;
		this.name = name;
		this.depname = depname;
		this.telephone = telephone;
		this.idR = idR;
		this.role = role;
	}
	
	
	// геттеры и сеттеры
	
	public Role role() {
		return role;
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
	
	
	public String getDepname() {
		return depname;
	}
	public void setShortname(String depname) {
		this.depname = depname;
	}
	
	
	public String getTel() {
		return telephone;
	}
	public void setTel(String telephone) {
		this.telephone = telephone;
	}

	
    public String getRole() {
		return role.getName();
	}

	public void setRole(Role rol) {
		this.role = rol;
	}
	
	public Long getIdR() {
		return idR;
	}
	
	public void setIdF(Long idR) {
		this.idR = idR;
	}
	
	
	@Override
	public String toString() {
		
		return  "Id = " + id +
				", Кафедра - " + depname +
				", ФИО - " + name +
				", Телефоне = " + telephone +
				", Должность = " + getRole() +
				"}";
	}

}





