package Unternehmung;

import Rules.Game;

import java.util.Calendar;

/**
 * Marketingkampagne zum Steigern des Bekanntheitsgrads
 * Created by lucadommes on 15.01.2017.
 */
public class Marketingkampagne {

    private String art; // Social Media, Print, Radio oder TV
    private float impact; // Einfluss auf Bekanntheitsgrad (pro Tag / timer count)
    private float kosten; // pro Tag (timer count)
    private int noetigeMitarbeiter;
    private int laufzeit; // in Tagen
    private Calendar beginn;
    private Calendar end;

    public Marketingkampagne(String art, int laufzeit) {
        this.art = art;
        this.laufzeit = laufzeit;
        this.beginn = (Calendar) Game.getCalendar().clone();
        this.end = (Calendar) this.beginn.clone();
        this.end.add(Calendar.DAY_OF_MONTH, laufzeit);
        setParamsByArt(this);
    }

    /**
     * @param mk Auf Basis der Art dieser Marketingkampagne werden impact und kosten in der angegebenen Marketingkampagne gesetzt
     */
    public static Marketingkampagne setParamsByArt(Marketingkampagne mk){
        if(mk == null)
            return null;
        switch (mk.getArt()){
            case "Social Media":
                mk.impact = 0.0002f;
                mk.kosten = 1000;
                mk.noetigeMitarbeiter = 1;
                break;
            case "Print":
                mk.impact = 0.002f;
                mk.kosten = 10000;
                mk.noetigeMitarbeiter = 3;
                break;
            case "Radio":
                mk.impact = 0.015f;
                mk.kosten = 60000;
                mk.noetigeMitarbeiter = 4;
                break;
            case "TV":
                mk.impact = 0.05f;
                mk.kosten = 130000;
                mk.noetigeMitarbeiter = 7;
                break;
        }
        return  mk;
    }
    public static Marketingkampagne setParamsByArt(String art){
        return Marketingkampagne.setParamsByArt(new Marketingkampagne(art,0));
    }

    // Getter and Setter:
    public String getArt() {
        return art;
    }

    public int getLaufzeit() {
        return laufzeit;
    }

    public float getImpact() {
        return impact;
    }

    public float getKosten() {
        return kosten;
    }

    public int getNoetigeMitarbeiter() {
        return noetigeMitarbeiter;
    }

    public Calendar getBeginn() {
        return beginn;
    }

    public Calendar getEnd() {
        return end;
    }
}
