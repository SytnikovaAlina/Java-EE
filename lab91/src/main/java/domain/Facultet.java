package domain;

/**
* Класс данных о факультетах
*/

public class Facultet {
    private Long id;
    private String name;
    private String shortname;
    private String head;
    private String telephone;
    
    // Конструкторы
    public Facultet(){}
    public Facultet(String name, String shortname, String head, String telephone) {
        this.name = name;
        this.shortname = shortname;
        this.head = head;
        this.telephone = telephone;
    }
    public Facultet(String name, String shortname, String head, String telephone, Long id) {
        this.name = name;
        this.shortname = shortname;
        this.head = head;
        this.telephone = telephone;
        this.id = id;
    }
    
    // геттеры и сеттеры
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

    
    
    @Override
    public String toString() {
        return "Факультет {" + "Id = " + id + ", Название = " + name + ", Аббревиатура = " + shortname + 
                ", Глава = " + head +", Телефон = " + telephone +"}";
    }

}