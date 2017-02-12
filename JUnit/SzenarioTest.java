import Exceptions.ZuWenigMaschinenstellplatzException;
import Exceptions.ZuWenigMitarbeiterException;
import Exceptions.ZuWenigMitarbeiterOderMaschinenException;
import Rules.Game;
import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Unternehmen;

/**
 * Created by D064018 on 12.02.2017.
 */
public class SzenarioTest {

    public static void main(String[] args) {
        Game game = new Game();

        // Unternehmen erstellen:
        Unternehmen unternehmen1 = new Unternehmen("Unternehmen 1", "kennwort", 100000);
        Unternehmen unternehmen2 = new Unternehmen("Unternehmen 1", "kennwort", 100000);

        game.getCompanies().add(unternehmen1);
        game.getCompanies().add(unternehmen2);

        //Manager, Hersteller und Vertriebler hinzufügen
        try {
            unternehmen1.getAbteilung("hr").addMitarbeiter(1, 2500);
            unternehmen1.getAbteilung("produktion").addMitarbeiter(3, 2500);
            unternehmen1.getAbteilung("vertrieb").addMitarbeiter(1, 2500);

            unternehmen2.getAbteilung("hr").addMitarbeiter(1, 2500);
            unternehmen2.getAbteilung("produktion").addMitarbeiter(3, 2500);
            unternehmen2.getAbteilung("vertrieb").addMitarbeiter(1, 2500);
        } catch (ZuWenigMitarbeiterException e) {
            e.printStackTrace();
        }

        //Sich auf Opportunities bewerben
        try{
//Game.getAusschreibungen().get(3);
        } catch (Exception e){

        }

        // Produktions- und Lagerhalle kaufen:
        Produktion produktion1 = (Produktion) unternehmen1.getAbteilung("produktion");
        produktion1.produktionshalleKaufen(1);
        produktion1.lagerhalleKaufen(1);

        Produktion produktion2 = (Produktion) unternehmen1.getAbteilung("produktion");
        produktion2.produktionshalleKaufen(1);
        produktion2.lagerhalleKaufen(1);

        // Maschinen kaufen:
        try {
            produktion1.maschinenKaufen("Rucksack", 1, 1);
            produktion2.maschinenKaufen("Rucksack", 1, 1);
        } catch (ZuWenigMaschinenstellplatzException e) {
            e.printStackTrace();
        }

        // Rucksäcke produzieren:
        try{
            produktion1.produzieren("Rucksack", 'C', 50, 3);
            produktion2.produzieren("Rucksack", 'C', 50, 3);
        } catch (ZuWenigMitarbeiterOderMaschinenException e){
            e.printStackTrace();
        }
    }
}
