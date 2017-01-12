package Rules;

import Unternehmung.Unternehmen;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Hilfsklasse, um z.B. den Gewinner eines Spiels festzulegen
 * Created by lucadommes on 08.01.2017.
 */
public class Game extends TimerTask{

    private final int COUNTER_INTERVALL = 6000;//1800000; //halbe Stunde

    private static long counter = 0;

    private static ArrayList<Unternehmen> companies = new ArrayList<>();

    /**
     * Konstruktor für ein Spiel mit 2 Spielern
     */
    public Game(){

        System.out.println("Ein Neues Spiel wird erstellt");

        Timer timer = new Timer(true);
        timer.schedule(this,0,COUNTER_INTERVALL);
    }

//    public Unternehmen gewinnerErmitteln(){
//        int u1 = 0; // Punktekonto von unternehmen1
//        int u2 = 0; // Punktekonto von unternehmen2
//        Unternehmen gewinner = null;
//        if(this.spieler == 2){
//            // Gewinn vergleichen:
//            if(unternehmen1.getKennzahlensammlung().getGewinn() > unternehmen2.getKennzahlensammlung().getGewinn()){
//                u1++;
//            }else if (unternehmen1.getKennzahlensammlung().getGewinn() < unternehmen2.getKennzahlensammlung().getGewinn()) {
//                u2++;
//            }
//            // Bekanntheitsgrad vergleichen:
//            if(unternehmen1.getKennzahlensammlung().getBekanntheitsgrad() > unternehmen2.getKennzahlensammlung().getBekanntheitsgrad()){
//                u1++;
//            } else if(unternehmen1.getKennzahlensammlung().getBekanntheitsgrad() < unternehmen2.getKennzahlensammlung().getBekanntheitsgrad()){
//                u2++;
//            }
//            // Verkaufsrate vergleichen:
//            if (unternehmen1.getKennzahlensammlung().getAbsatzrate() > unternehmen2.getKennzahlensammlung().getAbsatzrate()){
//                u1++;
//            }else if (unternehmen1.getKennzahlensammlung().getAbsatzrate() < unternehmen2.getKennzahlensammlung().getAbsatzrate()){
//                u2++;
//            }
//            // Punkte vergleichen um Gewinner zu ermitteln:
//            if(u1 > u2){
//                gewinner = unternehmen1;
//            }else if(u1 < u2){
//                gewinner = unternehmen2;
//            }
//        }
//        return gewinner;
//    }


    public static ArrayList<Unternehmen> getCompanies()
    {
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
     * Wird nach jedem Zyklus ausgeführt.
     */
    @Override
    public void run() {
        counter++;
        System.out.println("THE TIMER SAIS: " + counter);
    }

    /**
     * Methode zum Zurückgeben des aktuellen Timervalues
     * @return Counter value
     */
    public static long getTime()
    {
        return counter;
    }
}






















