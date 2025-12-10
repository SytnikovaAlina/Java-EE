package domain;

/**
* Класс данных о факультетах
*/

public class Facultet {
    private Long id;
    private String name;
    private String facname;
    private String telephone;
    
	private Long idR;
	private Role role;
    
    // Конструкторы
	public Facultet(){}
	public Facultet(String name, String facname, String telephone, Role role) {
		this.name = name;
		this.facname = facname;
		this.telephone = telephone;
		this.role = role;
	}
	public Facultet(String name, String facname, String telephone, Long idR, Role role) {
		this.name = name;
		this.facname = facname;
		this.telephone = telephone;
		this.idR = idR;
		this.role = role;
	}
	
	public Facultet(Long id, String name, String facname, String telephone, Long idR, Role role) {
		this.id = id;
		this.name = name;
		this.facname = facname;
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
    
    
    public String getFacname() {
        return facname;
    }
    public void setFacname(String facname) {
        this.facname = facname;
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
	
	public void setIdR(Long idR) {
		this.idR = idR;
	}
    
    
    @Override
    public String toString() {
    	return  "Id = " + id +
			", Факультет - " + facname +
			", ФИО - " + name +
			", Телефоне = " + telephone +
			", Должность = " + getRole() +
			"}";
    }

}