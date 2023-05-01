import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
	
		Scanner scanner = new Scanner(System.in);
		int choice;
		FilmList filmList = new FilmList();
	
        do {
		System.out.println("1. Pridat film");
        System.out.println("2. Upravit film");
        System.out.println("3. Smazat film");
        System.out.println("4. Pridat hodnoceni filmu");
        System.out.println("5. Vypsat filmy");
        System.out.println("6. Vyhledat film podle nazvu");
        System.out.println("7. Vyhledat film podle herce nebo animatora");
        System.out.println("8. Vypsat herce nebo animatory ucinkujici ve vice filmech");
        System.out.println("9. Ulozit do souboru");
        System.out.println("10. Nacist ze souboru");
        System.out.println("11. Ukoncit");
        System.out.print("Zvolte moznost: ");
        
        choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
            	try{
                    System.out.println("Zadejte typ filmu: (1 - Hrany, 2 - Animovany): ");
                    int typ = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Zadejte nazev filmu: ");
                    String nazev = scanner.nextLine();

                    System.out.println("Zadejte rezisera: ");
                    String reziser = scanner.nextLine();

                    System.out.println("Zadejte rok: ");
                    int rok = scanner.nextInt();
                    scanner.nextLine();

                    if (typ == 1) {
                        System.out.println("Zadejte herce (oddelene carkou): ");
                        String actorsInput = scanner.nextLine();
                        List<String> herci = Arrays.asList(actorsInput.split(","));
                        filmList.pridejFilm(new FilmHrany(nazev, reziser, rok, herci));
                    } else if (typ == 2) {
                        System.out.println("Zadejte animatory (oddelene carkou): ");
                        String animatorsInput = scanner.nextLine();
                        List<String> animatori = Arrays.asList(animatorsInput.split(","));

                        System.out.println("Zadejte doporuceny vek: ");
                        int vek = scanner.nextInt();
                        scanner.nextLine();

                        filmList.pridejFilm(new FilmAnimovany(nazev, reziser, rok, animatori, vek));
                    } else {
                        System.out.println("Spatny typ filmu.");
                    } } catch (Exception e) {
                        System.out.println("Chyba pri pridani filmu: " + e.getMessage());
                    }
                    break;
            case 2:
            	try {
                    System.out.println("Zadejte nazev filmu, ktery chcete upravit: ");
                    String nazev = scanner.nextLine();

                    System.out.println("Zadejte novy nazev filmu: ");
                    String novyNazev = scanner.nextLine();               

                    System.out.println("Zadejte noveho rezisera: ");
                    String reziser = scanner.nextLine();

                    System.out.println("Zadejte novy rok: ");
                    int rok = scanner.nextInt();

                    scanner.nextLine();
                    String newActorsOrAnimatorsInput = "";
                    int recommendedAge = -1;
                    if(filmList.typ(nazev) == "Animovany"){
                        System.out.println("Zadejte animatory (oddelene carkou): ");
                        newActorsOrAnimatorsInput = scanner.nextLine();

                        System.out.println("Zadejte doporuceny vek: ");
                        recommendedAge =  scanner.nextInt();

                    }else{
                        System.out.println("Zadejte herce (oddelene carkou): ");
                        newActorsOrAnimatorsInput = scanner.nextLine();
                    }
 
                    List<String> newActorsOrAnimators = Arrays.asList(newActorsOrAnimatorsInput.split(",\\s*"));
                    filmList.upravFilm(nazev, novyNazev, reziser, rok, newActorsOrAnimators,recommendedAge);

                } catch (Exception e) {
                    System.out.println("Chyba pri uprave filmu: " + e.getMessage());
                }
                    break;
                    
            case 3:
                try {
                    System.out.println("Zadejte nazev filmu, ktery chcete smazat: ");
                    String nazev = scanner.nextLine();

                    filmList.smazFilm(nazev);
                    System.out.println("Film smazan.");
                  
                } catch (Exception e) {
                    System.out.println("Chyba pri mazani filmu." + e.getMessage());
                }
                    break;
            case 4:
                System.out.println("Zadejte nazev filmu:");
                String nazev = scanner.nextLine();

                System.out.println("Zadejte hodnoceni:");
                int hodnoceni = scanner.nextInt();
                scanner.nextLine();

                filmList.pridejHodnoceni(nazev, hodnoceni);
            case 5:
               
                filmList.vypisFilmy();
                break;
            case 6:
                System.out.println("Zadejte nazev filmu:");
                String nazev1 = scanner.nextLine();
                filmList.vyhledejFilm(nazev1);
                break;
            case 7:
                System.out.println("Zadejte jmeno herce/animatora:");
                String nazev2 = scanner.nextLine();
                filmList.vyhledejHerceAnimatora(nazev2);
                break;
            case 8:
                filmList.vypisHerceAnimatoryZViceFilmu();
                break;
            case 9:
                System.out.println("Zadejte nazev filmu k ulozeni:");
                    String nazev3 = scanner.nextLine();
                    filmList.ulozDoSouboru(nazev3);
                    
                    break;
                case 10:
                filmList.nactiZeSouboru();
                break;
	}
      
		
		} while (choice != 11);
	}
}