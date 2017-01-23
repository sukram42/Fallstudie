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
        setParamsByArt(art);
    }

    /**
     * @param art Auf Basis der Art der Marketingkampagne werden impact und kosten gesetzt
     */
    private void setParamsByArt(String art){
        switch (art){
            case "Social Media":
                this.impact = 0.001f;
                this.kosten = 10;
                this.noetigeMitarbeiter = 1;
                break;
            case "Print":
                this.impact = 0.002f;
                this.kosten = 50;
                this.noetigeMitarbeiter = 2;
                break;
            case "Radio":
                this.impact = 0.004f;
                this.kosten = 100;
                this.noetigeMitarbeiter = 4;
                break;
            case "TV":
                this.impact = 0.02f;
                this.kosten = 500;
                this.noetigeMitarbeiter = 6;
                break;
        }
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
