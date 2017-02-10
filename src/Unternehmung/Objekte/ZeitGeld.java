package Unternehmung.Objekte;

import Rules.Game;
import Unternehmung.Unternehmen;

import java.util.Calendar;

/**
 * Created by boebel on 14.01.2017.
 */
public class ZeitGeld extends SozialProjekt {


    private final int month;

    public ZeitGeld(String name, float laufendeKosten, float impact, int month, Unternehmen unternehmen) {
        super(name, 0, laufendeKosten, impact, unternehmen);
        this.month = month;
    }

    @Override
    public void update() {
        if (Game.getCalendar().get(Calendar.MONTH) == month) {
            System.out.println("HOHOHOHO ES GIBT " + laufendeKosten + " GELD");
            aktuelleKosten += laufendeKosten;
        }
    }
}
