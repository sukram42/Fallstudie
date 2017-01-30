package Rules;

import Unternehmung.Abteilungen.Vertrieb;
import Unternehmung.Ausschreibung;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Hilfsklasse, um z.B. den Gewinner eines Spiels festzulegen
 * Created by lucadommes on 08.01.2017.
 */
public class Game extends TimerTask {

    private static final int COUNTER_INTERVALL = 5 * 1000;//16*1000*60;//16 Minuten
    private static long counter = 0;

    private static Calendar gameCalendar = new GregorianCalendar(2010, 1, 1);

    private static ArrayList<Unternehmen> companies = new ArrayList<>();
    private static List<Ausschreibung> ausschreibungen = new CopyOnWriteArrayList<>();

    /**
     * Konstruktor für ein Spiel mit 2 Spielern
     */
    public Game() {

        System.out.println("Ein Neues Spiel wird erstellt");

        Timer timer = new Timer();
        timer.schedule(this, 0, COUNTER_INTERVALL);

        updateAusschreibungen();
    }

    public static ArrayList<Unternehmen> getCompanies() {
        return companies;
    }
    //Warum keine "addCompanies" Bennenung ? :D

    public static Unternehmen getUnternehmenByName(String name) {
        for (Unternehmen u : companies) {
            if (name.equals(u.getName()))
                return u;
        }
        return null;
    }

    /**
     * Methode zum Zurückgeben des aktuellen Timervalues
     *
     * @return Counter value
     */
    public static long getTime() {
        return counter;
    }

    public static String getTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return sdf.format(gameCalendar.getTime());
    }

    public static Calendar getCalendar() {
        return gameCalendar;
    }

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println("AUSSCHREIBUNGEN:__" + new Gson().toJson(game.getAusschreibungen()));
    }

    // Getter und Setter:
    public static List<Ausschreibung> getAusschreibungen() {
        return ausschreibungen;
    }

    /**
     * Wird nach jedem Zyklus ausgeführt.
     */
    @Override
    public void run() {
        counter++;
        updateCounter();

        for (Unternehmen u : companies) {
            u.update();
            u.getKennzahlensammlung().update();
        }
        this.updateMarktanteile();
        if ((getCalendar().get(Calendar.MONTH) == Calendar.DECEMBER) && getCalendar().get(Calendar.DAY_OF_MONTH) == 30) {
            for (Unternehmen u : companies) {
                u.updateYearly();
            }
        }

        updateAusschreibungen();

    }

    /**
     * legt am ersten Tag jedes Monats fest, wer den Zuschlag bekommt, löscht dann alle Opportunities und Ausschreibungen und generiert neue Ausschreibungen
     * das Unternehmen, dass als erstes ein Angebot abgegeben hat bekommt den Zuschlag, wenn ein zufälliger Float zwischen 0 und der Verkafuswahrscheinlichkeit liegt:
     */
    private void updateAusschreibungen() {
        // TODO gewährleisten, dass man am Anfang auch mal einen Zuschlag bekommt, wo die Verkaufswahrscheinlichkeit noch sehr gering ist!!
        if (Game.getCalendar().get(Calendar.DAY_OF_MONTH) == 1) {
            // Entscheidung über Zuschlag basierend auf der Kennzahl der Verkaufswahrscheinlichkeit:
            for (Ausschreibung ausschreibung : ausschreibungen) {
                if(ausschreibung.getBewerber() != null) {
                    Unternehmen gewinner = null;
                    boolean gewinnerGefunden = false;
                    // Gewinner der Ausschreibung ermitteln:
                    for (Unternehmen unternehmen : ausschreibung.getBewerber()) {
                        Random random = new Random();
                        float randomFloat = random.nextFloat();
                        // das Unternehmen, dass als erstes ein Angebot abgegeben hat bekommt den Zuschlag, wenn ein zufälliger Float zwischen 0 und der Verkafuswahrscheinlichkeit liegt:
                        if (randomFloat < unternehmen.getKennzahlensammlung().getWeicheKennzahl("verkaufswahrscheinlichkeit").getWert()) {
                            gewinner = unternehmen;
                            gewinnerGefunden = true;
                            break;
                        }
                    }
                    // Zuschlag geben:
                    if (gewinnerGefunden) {
                        Vertrieb vertrieb = (Vertrieb) gewinner.getAbteilung("vertrieb");
                        vertrieb.getAccounts().add(ausschreibung.getVertrag());
                    }
                }
            }
            // Opportunities bei allen Unternehmen löschen:
            for (Unternehmen unternehmen : companies){
                Vertrieb vertrieb = (Vertrieb) unternehmen.getAbteilung("vertrieb");
                vertrieb.getOpportunities().clear();
            }
            // alte Ausschreibugnen löschen:
            ausschreibungen.clear();
            // neue zufällige Ausschreibungen generieren:
            Random random = new Random();
            int anzahlAusschreibungen = random.nextInt(10) + 8;
            for (int i = 1; i <= anzahlAusschreibungen; i++) {
                ausschreibungen.add(new Ausschreibung());
            }
        }
    }

    /**
     * berechnet und setzt bei jedem Timer Intervall den absoluten mengenmäßigen Marktanteil für jedes Unternehmen
     */
    private void updateMarktanteile(){
        int gesamtabsatz = 0;
        for (Unternehmen unternehmen : companies){
            Vertrieb vertrieb = (Vertrieb) unternehmen.getAbteilung("vertrieb");
            gesamtabsatz += vertrieb.getVerkaufteProdukte();
        }
        if (gesamtabsatz > 0) {
            for (Unternehmen unternehmen : companies) {
                Vertrieb vertrieb = (Vertrieb) unternehmen.getAbteilung("vertrieb");
                unternehmen.getKennzahlensammlung().setMarktanteil(vertrieb.getVerkaufteProdukte() / gesamtabsatz);
            }
        } else {
            for (Unternehmen unternehmen : companies){
                unternehmen.getKennzahlensammlung().setMarktanteil(0);
            }
        }
    }

    /**
     * wird aufgerufen von updateAusschreibungen()
     * @param bewerber ArrayList mit den Bewerbern für eine Ausschreibung
     * @return Bewerber mit der höchsten Verkaufswahrscheinlichkeit
     */
    private Unternehmen getHoechsteVerkaufswahrscheinlichkeit(ArrayList<Unternehmen> bewerber){
        float max = 0;
        Unternehmen besterBewerber = null;
        for (Unternehmen unternehmen : bewerber) {
            if (unternehmen.getKennzahlensammlung().getWeicheKennzahl("verkaufswahrscheinlichkeit").getWert() > max) {
                max = unternehmen.getKennzahlensammlung().getWeicheKennzahl("verkaufswahrscheinlichkeit").getWert();
                besterBewerber = unternehmen;
            }
        }
        return besterBewerber;
    }

    public void updateCounter() {
        gameCalendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(gameCalendar.getTime().toString());
    }

}






















