package Rules;

import Unternehmung.Abteilungen.Vertrieb;
import Unternehmung.Objekte.Ausschreibung;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Klasse für Spiellogik und -steuerung
 * Created by lucadommes on 08.01.2017.
 */
public class Game extends TimerTask {

    private static final int COUNTER_INTERVALL =20 * 1000;//16*1000*60;//16 Minuten
    private static long counter = 0;

    private static Calendar gameCalendar = new GregorianCalendar(2010, 0, 1);

    private static ArrayList<Unternehmen> companies = new ArrayList<>();
    private static ArrayList<Unternehmen> companiesArchiv = new ArrayList<>();
    private static List<Ausschreibung> ausschreibungen = new CopyOnWriteArrayList<>();
    private static Map<Float, String> highscores = new HashMap<>();

    /**
     * Konstruktor für ein Spiel mit 2 Spielern
     */
    public Game() {
        System.out.println("Ein Neues Spiel wird erstellt");

        Timer timer = new Timer();
        timer.schedule(this, 0, COUNTER_INTERVALL);

        updateAusschreibungen();
    }

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println("AUSSCHREIBUNGEN:__" + new Gson().toJson(game.getAusschreibungen()));
    }


    /**
     * Wird nach jedem Zyklus ausgeführt.
     */
    @Override
    public void run() {
        counter++;
        updateCounter();

        try {
            for (Unternehmen u : companies) {
                u.update();
                bankruptTest(u);
            }
            this.updateMarktanteile();
            if ((getCalendar().get(Calendar.MONTH) == Calendar.DECEMBER) && getCalendar().get(Calendar.DAY_OF_MONTH) == 31) {
                for (Unternehmen u : companies) {
                    u.updateYearly();
                }
            }

            updateAusschreibungen();
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    /**
     * Testet ob ein Unternehmen pleite ist und setzt es in eine Archivliste
     *
     * @param u Das zu überprüfende Unternehmen
     */
    private void bankruptTest(Unternehmen u) {
        if (u.getKennzahlensammlung().isBankrupt()) {
//            companies.remove(u);
            companiesArchiv.add(u);
        }
    }

    /**
     * legt am ersten Tag jedes Monats fest, wer den Zuschlag bekommt, löscht dann alle Opportunities und Ausschreibungen und generiert neue Ausschreibungen
     * das Unternehmen, dass als erstes ein Angebot abgegeben hat bekommt den Zuschlag, wenn ein zufälliger Float zwischen 0 und der Verkafuswahrscheinlichkeit (oder 0.4, wenn Verkaufswahrscheinlichkeit niedriger als 0.4) liegt:
     */
    private void updateAusschreibungen() {
        if (Game.getCalendar().get(Calendar.DAY_OF_MONTH) == 1) {
            // Entscheidung über Zuschlag basierend auf der Kennzahl der Verkaufswahrscheinlichkeit:
            for (Ausschreibung ausschreibung : ausschreibungen) {
                if (ausschreibung.getBewerber() != null) {
                    Unternehmen gewinner = null;
                    boolean gewinnerGefunden = false;
                    // Gewinner der Ausschreibung ermitteln:
                    for (Unternehmen unternehmen : ausschreibung.getBewerber()) {
                        Random random = new Random();
                        float randomFloat = random.nextFloat();
                        float verkaufswahrscheinlichkeit = unternehmen.getKennzahlensammlung().getWeicheKennzahl("verkaufswahrscheinlichkeit").getWert();
                        // Verkaufswahrscheinlichkeit auf 0.4 setzten, falls sie geringer ist, sodass die Chance nicht zu gering ist
                        if (verkaufswahrscheinlichkeit > 0.4f) {
                            verkaufswahrscheinlichkeit = 0.4f;
                        }
                        // das Unternehmen, dass als erstes ein Angebot abgegeben hat bekommt den Zuschlag, wenn ein zufälliger Float zwischen 0 und der Verkafuswahrscheinlichkeit liegt:
                        if (randomFloat < verkaufswahrscheinlichkeit) {
                            gewinner = unternehmen;
                            gewinnerGefunden = true;
                            break;
                        }
                        // Werfen einer ZuschlangNichtBekommenException, falls Zuschlag nicht gegeben wurde:
                        Vertrieb vertrieb = (Vertrieb) unternehmen.getAbteilung("vertrieb");
                        vertrieb.throwZuschlagNichtBekommenException();
                    }
                    // Zuschlag geben:
                    if (gewinnerGefunden) {
                        Vertrieb vertrieb = (Vertrieb) gewinner.getAbteilung("vertrieb");
                        ausschreibung.getVertrag().setPreisByKennzahlen(gewinner.getKennzahlensammlung());
                        vertrieb.getAccounts().add(ausschreibung.getVertrag());
                    }
                }
            }
            // Opportunities bei allen Unternehmen löschen:
            for (Unternehmen unternehmen : companies) {
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
     * berechnet und setzt bei jedem Timer Count den absoluten mengenmäßigen Marktanteil für jedes Unternehmen
     */
    private void updateMarktanteile() {
        int gesamtabsatz = getGesamtabsatz();
        if (gesamtabsatz > 0) {
            for (Unternehmen unternehmen : companies) {
                Vertrieb vertrieb = (Vertrieb) unternehmen.getAbteilung("vertrieb");
                unternehmen.getKennzahlensammlung().setMarktanteil(vertrieb.getVerkaufteProdukte() / gesamtabsatz);
            }
        } else {
            for (Unternehmen unternehmen : companies) {
                unternehmen.getKennzahlensammlung().setMarktanteil(0);
            }
        }
    }

    private void updateCounter() {
        gameCalendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(gameCalendar.getTime().toString());
    }

    // Getter, Setter und Hilfsmethoden:
    /**
     * @return Highscores als sortierte TreeMap
     */
    public static Map<Float, String> getHighscoresAsTreeMap() {
        return new TreeMap<Float, String>(highscores);
    }

    public static Map<String, Double> getMarktanteile() {
        Map<String, Double> marktanteile = new HashMap<>();
        for (Unternehmen unternehmen : companies) {
            marktanteile.put(unternehmen.getName(), unternehmen.getKennzahlensammlung().getMarktanteil());
        }
        return marktanteile;
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
     * Ermitteln der gesamten Produktionsmenge aller Unternehmen
     */
    public static int getGesamtabsatz() {
        int gesamtabsatz = 0;
        for (Unternehmen unternehmen : companies) {
            Vertrieb vertrieb = (Vertrieb) unternehmen.getAbteilung("vertrieb");
            gesamtabsatz += vertrieb.getVerkaufteProdukte();
        }
        return gesamtabsatz;
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


    public static List<Ausschreibung> getAusschreibungen() {
        return ausschreibungen;
    }

    public static Map<Float, String> getHighscores() {
        return highscores;
    }
}
