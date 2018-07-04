package aman.com.beercraft;

public class Beer {

    int id;
    String name, ibu, style,abv, ounces;


    public Beer(int id, String ounces, String name, String ibu, String style, String abv) {
        this.id = id;
        this.ounces = ounces;
        this.name = name;
        this.ibu = ibu;
        this.style = style;
        this.abv = abv;
    }

    public Beer(){}

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", ounces=" + ounces +
                ", name='" + name + '\'' +
                ", ibu='" + ibu + '\'' +
                ", style='" + style + '\'' +
                ", abv=" + abv +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOunces() {
        return ounces;
    }

    public void setOunces(String ounces) {
        this.ounces = ounces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }
}
