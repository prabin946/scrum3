package model;

public enum Genre {


    ROMANCE(0, "romance"),
    ACTION(1, "action"),
    SCI_FI(2, "sci_fi"),
    WAR(3, "war"),
    MUSICAL(4, "musical"),
    BIOPIC(5, "biopic");


    private int id;
    private String name;

    Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
