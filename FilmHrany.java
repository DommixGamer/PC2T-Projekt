import java.util.List;

class FilmHrany extends FilmAbstract {
    private List<String> herci;
    private int hvezdy;

    public FilmHrany(String nazev, String reziser, int rok, List<String> herci) {
        super(nazev, reziser, rok);
        this.herci = herci;
    }

    public List<String> getHerci() {
        return herci;
    }

    public void setHerci(List<String> herci) {
        this.herci = herci;
    }

    public int getHvezdy() {
        return hvezdy;
    } 
    public void setHvezdy(int hvezdy) {
    	this.hvezdy = hvezdy;
    }

    public String getHodnoceni() {
        return hvezdy + "/5 " ;
    }
}