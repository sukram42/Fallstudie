package Rules;

import Unternehmung.Abteilungen.Vertrieb;
import Unternehmung.Ausschreibung;
import Unternehmung.Unternehmen;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Hilfsklasse, um z.B. den Gewinner eines Spiels festzulegen
 * Created by lucadommes on 08.01.2017.
 */
public class Game extends TimerTask {

    private static final int COUNTER_INTERVALL = 3 * 1000;//16*1000*60;//16 Minuten
    private static long counter = 0;

    private static Calendar gameCalendar = new GregorianCalendar(2010, 1, 1);

    private static ArrayList<Unternehmen> companies = new ArrayList<>();
    private static ArrayList<Ausschreibung> ausschreibungen = new ArrayList<Ausschreibung>();

    /**
     * Konstruktor für ein Spiel mit 2 Spielern
     */
    public Game() {

        System.out.println("Ein Neues Spiel wird erstellt");

        Timer timer = new Timer();
        timer.schedule(this, 0, COUNTER_INTERVALL);
    }

    public static ArrayList<Unternehmen> getCompanies() {
        return companies;
    }

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
    public static ArrayList<Ausschreibung> getAusschreibungen() {
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

        if ((getCalendar().get(Calendar.MONTH) == Calendar.DECEMBER) && getCalendar().get(Calendar.DAY_OF_MONTH) == 30) {
            for (Unternehmen u : companies) {
                u.updateYearly();
            }
        }

        updateAusschreibungen();

    }

    /**
     * legt am ersten Tag jedes Monats fest, wer den Zuschlag bekommt, löscht dann alle Ausschreibungen und generiert neue
     */
    private void updateAusschreibungen() {
        if (Game.getCalendar().get(Calendar.DAY_OF_MONTH) == 1) {
            // Entscheidung über Zuschlag basierend auf der Kennzahl der Verkaufswahrscheinlichkeit:
            for (int i = 0; i < ausschreibungen.size(); i++) {
                // eine Map mit allen Unternehmen, die sich auf die Ausschreibung beworben haben erstellen:
                Map<Unternehmen, Float> bewerber = new HashMap<Unternehmen, Float>();
                for (Unternehmen unternehmen : companies) {
                    Vertrieb vertrieb = (Vertrieb) unternehmen.getAbteilung("vertrieb");
                    if (vertrieb.getOpportunities().get(i) != null) {
                        bewerber.put(unternehmen, unternehmen.getKennzahlensammlung().getWeicheKennzahl("verkaufswahrscheinlichkeit").getWert());
                    }
                }
                // das Unternehmen mit der höchsten Verkaufswahrscheinlichkeit finden
                float max = -1;
                for (Map.Entry<Unternehmen, Float> b : bewerber.entrySet()) {
                    if (b.getValue() > max) {
                        max = b.getValue();
                    }
                }
                // Zuschlag geben:
                for (Map.Entry<Unternehmen, Float> b : bewerber.entrySet()) {
                    if (b.getValue() == max) {
                        Vertrieb vertrieb = (Vertrieb) b.getKey().getAbteilung("vertrieb");
                        vertrieb.zuschlagBekommen(i);
                    }
                }
            }
            // alte Ausschreibugnen löschen:
            for (int i = 0; i < ausschreibungen.size(); i++) {
                ausschreibungen.remove(i);
            }
            // neue Ausschreibungen generieren:
            Random random = new Random();
            int anzahlAusschreibungen = random.nextInt(10) + 8;
            for (int i = 1; i <= anzahlAusschreibungen; i++) {
                ausschreibungen.add(new Ausschreibung());
            }
        }
    }

    public void updateCounter() {
        gameCalendar.add(Calendar.DAY_OF_MONTH, 1);
    }

}






















