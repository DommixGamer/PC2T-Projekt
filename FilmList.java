import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FilmList {
	private Map<String, FilmAbstract> filmy = new HashMap<>();

    public void pridejFilm(FilmAbstract film) {

        filmy.put(film.getNazev(), film);
    }
    public String typ(String typ){

        FilmAbstract film = filmy.get(typ);
        return ((film instanceof FilmAnimovany)? "Animovany" : "Hrany");

    }
    
    public void smazFilm(String nazev) {
        filmy.remove(nazev);
    }
    
    public void pridejHodnoceni(String nazev, int hodnoceni) {
        FilmAbstract film = filmy.get(nazev);

        if (film instanceof FilmAnimovany && hodnoceni > -1 && hodnoceni <11) {
                ((FilmAnimovany) film).setRating(hodnoceni);
            }
       
            
         else if (film instanceof FilmHrany && hodnoceni > -1 && hodnoceni <6) {
          
 
                ((FilmHrany) film).setHvezdy(hodnoceni);
            }
            
        }
    
    public void upravFilm(String nazev, String novyNazev, String reziser, int rok, List<String> herciAnimatori, int vek) {
        FilmAbstract film = filmy.get(nazev);
        
            film.setNazev(novyNazev);
            film.setReziser(reziser);
            film.setRok(rok);

            if (film instanceof FilmHrany && !herciAnimatori.get(0).isEmpty()) {
                ((FilmHrany) film).setHerci(herciAnimatori);
            } else if (film instanceof FilmAnimovany  && !herciAnimatori.get(0).isEmpty()) {
                ((FilmAnimovany) film).setAnimatori(herciAnimatori);
              
            }
            ((FilmAnimovany) film).setVek(vek);
        }
    
    
    public Film vyhledejFilm(String nazev){
        FilmAbstract film = filmy.get(nazev);
        List<String> herciAnimatori = (film instanceof FilmHrany) ? ((FilmHrany) film).getHerci() : ((FilmAnimovany) film).getAnimatori();
        int vek = (film instanceof FilmAnimovany) ? ((FilmAnimovany) film).getVek(): 0;
        String filmInfo="Nazev: "+film.getNazev() + ", Reziser: " + film.getReziser() + ", Rok: " + film.getRok() + ", Hodnoceni: "
        + film.getHodnoceni() + ", Herci/Animatori" + herciAnimatori + ((vek > 0) ? ", Doporuceny vek: " + vek : "");
        System.out.println(filmInfo);
        return film;
    }
    
    public void vypisFilmy() {
        for (FilmAbstract film : filmy.values()) {
            List<String> herciAnimatori = (film instanceof FilmHrany) ? ((FilmHrany) film).getHerci() : ((FilmAnimovany) film).getAnimatori();
            int vek = (film instanceof FilmAnimovany) ? ((FilmAnimovany) film).getVek(): 0;
            System.out.println("Nazev: "+film.getNazev() + ", Reziser: " + film.getReziser() + ", Rok: " + film.getRok() + ", Hodnoceni: "
             + film.getHodnoceni() + ", Herci/Animatori:" + herciAnimatori + ((vek > 0) ? ", Doporuceny vek: " + vek : ""));
        }
    }
    
    public void vyhledejHerceAnimatora(String nazev){
        for (FilmAbstract film : filmy.values()) {
            List<String> herciAnimatori = (film instanceof FilmHrany) ? ((FilmHrany) film).getHerci() : ((FilmAnimovany) film).getAnimatori();
            if(herciAnimatori.contains(nazev)){
                System.out.println("Nazev: "+film.getNazev() + ", Reziser: " + film.getReziser() + ", Rok: " + film.getRok() + ", Herci/animatori" + herciAnimatori);
            }
        }
    }
    
    public void ulozDoSouboru(String nazev) {
        String nazevSouboru = "Filmy.txt";
        File file = new File(nazevSouboru);

        try {
            if (file.createNewFile()) {
                System.out.println("Soubor byl úspěšně vytvořen.");
            } else {
                System.out.println("Soubor už existuje.");
            }
                try {
          FilmAbstract film = filmy.get(nazev);
        List<String> herciAnimatori = (film instanceof FilmHrany) ? ((FilmHrany) film).getHerci() : ((FilmAnimovany) film).getAnimatori();
        int vek = (film instanceof FilmAnimovany) ? ((FilmAnimovany) film).getVek(): 0;
     
                    FileWriter fileWriter = new FileWriter(nazevSouboru, false);
        
                    fileWriter.write("Nazev: "+film.getNazev() + ", Reziser: " + film.getReziser() + ", Rok: " + film.getRok() + ", Hodnoceni: "
                    + film.getHodnoceni() + ", Herci/Animatori" + herciAnimatori + ((vek > 0) ? ", Doporuceny vek: " + vek : ""));
                    fileWriter.close();
        
                   
                } catch (IOException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void nactiZeSouboru() {
        String nazevSouboru = "Filmy.txt";
        try {
            FileReader reader = new FileReader(nazevSouboru);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("File could not be read.");
            e.printStackTrace();
        }
        
    }
    
    public void vypisHerceAnimatoryZViceFilmu() {
        Map<String, List<String>> actorsOrAnimatorsMovies = new HashMap<>();
        for (FilmAbstract film : filmy.values()) {
            List<String> herciAnimatori = ((film instanceof FilmHrany) ? ((FilmHrany) film).getHerci() : ((FilmAnimovany) film).getAnimatori());
            for (String herec : herciAnimatori){
                actorsOrAnimatorsMovies.computeIfAbsent(herec, k -> new ArrayList<>()).add(film.getNazev());
            }
        }
        for(String herec : actorsOrAnimatorsMovies.keySet()){
            if(actorsOrAnimatorsMovies.get(herec).size() > 1){
                System.out.println(herec + " se objevil v: " + actorsOrAnimatorsMovies.get(herec));
            }
        }
      
    }
}
