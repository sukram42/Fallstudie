package Unternehmung;

import Rules.Game;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by oehlersj on 19.01.2017.
 */
public class Ausschreibung {
    private Vertrag vertrag;
    private Date eingDatum;
    private Date auswahlDatum;
    private Calendar tmp;

    public List<Vertrag> vertraege;

    public Ausschreibung(Vertrag vertrag) {
        tmp = Game.getCalendar();
        this.vertrag = vertrag;
        this.eingDatum = tmp.getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(Game.getCalendar().getTime());
        c.add(Calendar.DATE,1);
        this.auswahlDatum = c.getTime();
        vertraege = new ArrayList<>();
    }
}
