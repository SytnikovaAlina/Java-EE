package domain;

/**
 * Класс данных о ролях
 */
public class Role {
    private Long id;
    private String name;
    
    // Конструкторы
    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    // Геттеры и сеттеры
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

    @Override
    public String toString() {
        return "Роль {" + "Id = " + id + ", Название = " + name + "}";
    }
}
