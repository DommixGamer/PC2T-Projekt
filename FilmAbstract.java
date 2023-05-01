
abstract class FilmAbstract implements Film {
	private String nazev;
    private String reziser;
    private int rok;

    public FilmAbstract(String nazev, String reziser, int rok) {
        this.nazev = nazev;
        this.reziser = reziser;
        this.rok = rok;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getReziser() {
        return reziser;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }
    
}
