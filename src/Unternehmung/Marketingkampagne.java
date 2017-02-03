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
    private Calendar beginn;
    private Calendar end;

    public Marketingkampagne(String art, int laufzeit) {
        this.art = art;
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
                mk.impact = 0.001f;
                mk.kosten = 10;
                mk.noetigeMitarbeiter = 1;
                break;
            case "Print":
                mk.impact = 0.002f;
                mk.kosten = 50;
                mk.noetigeMitarbeiter = 2;
                break;
            case "Radio":
                mk.impact = 0.004f;
                mk.kosten = 100;
                mk.noetigeMitarbeiter = 4;
                break;
            case "TV":
                mk.impact = 0.02f;
                mk.kosten = 500;
                mk.noetigeMitarbeiter = 6;
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

    public void setArt(String art) {
        this.art = art;
    }

    public float getImpact() {
        return impact;
    }

    public void setImpact(float impact) {
        this.impact = impact;
    }

    public float getKosten() {
        return kosten;
    }

    public void setKosten(float kosten) {
        this.kosten = kosten;
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

    public void setEnd(Calendar end) {
        this.end = end;
    }
}
