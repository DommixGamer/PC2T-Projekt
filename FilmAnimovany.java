import java.util.List;

class FilmAnimovany extends FilmAbstract {
	private List<String> animatori;
    private int vek;
    private int rating;
    
    public FilmAnimovany(String nazev, String reziser, int rok, List<String> animatori, int vek) {
        super(nazev, reziser, rok);
        this.animatori = animatori;
        this.vek = vek;
    }

    public List<String> getAnimatori() {
        return animatori;
    }

    public void setAnimatori(List<String> animatori) {
        this.animatori = animatori;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }

    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
        
    }

    public String getHodnoceni() {
        return rating + "/10 ";
    }
}
