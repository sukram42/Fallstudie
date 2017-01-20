package Rules;

import Unternehmung.Unternehmen;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Hilfsklasse, um z.B. den Gewinner eines Spiels festzulegen
 * Created by lucadommes on 08.01.2017.
 */
public class Game extends TimerTask{

    private static final int COUNTER_INTERVALL = 30*1000;//16*1000*60;//16 Minuten
    private static long counter = 0;

    private static Calendar gameCalendar = new GregorianCalendar(2010,1,1);

    private static int day,mounth,quarter,year = 1;

    private static ArrayList<Unternehmen> companies = new ArrayList<>();

    /**
     * Konstruktor für ein Spiel mit 2 Spielern
     */
    public Game(){

        System.out.println("Ein Neues Spiel wird erstellt");

        Timer timer = new Timer();
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
//            if (unternehmen1.getKennzahlensammlung().getVerkaufswahrscheinlichkeit() > unternehmen2.getKennzahlensammlung().getVerkaufswahrscheinlichkeit()){
//                u1++;
//            }else if (unternehmen1.getKennzahlensammlung().getVerkaufswahrscheinlichkeit() < unternehmen2.getKennzahlensammlung().getVerkaufswahrscheinlichkeit()){
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
     * Methode zum Zurückgeben des aktuellen Timervalues
     * @return Counter value
     */
    public static long getTime()
    {
        return counter;
    }

    public static String getTimeString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return sdf.format(gameCalendar.getTime());
    }

    public static Calendar getCalendar()
    {
        return gameCalendar;
    }

    public static void main(String[] args){
        new Game();
    }

    /**
     * Wird nach jedem Zyklus ausgeführt.
     */
    @Override
    public void run() {
        counter++;
        updateCounter();

        for(Unternehmen u: companies)
        {
            u.update();
            u.getKennzahlensammlung().berechnen();
        }

        if((getCalendar().get(Calendar.MONTH) == Calendar.DECEMBER ) && getCalendar().get(Calendar.DAY_OF_MONTH)== 30)
        {
            for(Unternehmen u: companies)
            {
                u.updateYearly();
            }
        }

    }

    public void updateCounter() {
        gameCalendar.add(Calendar.DAY_OF_MONTH, 1);
    }

}






















