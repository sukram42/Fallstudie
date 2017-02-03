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
        this.setParamsByArt(art);
    }

    /**
     * @param art Auf Basis der Art der Marketingkampagne werden impact und kosten gesetzt
     */
    private void setParamsByArt(String art){
        switch (art){
            case "Social Media":
                this.impact = 0.0002f;
                this.kosten = 1000;
                this.noetigeMitarbeiter = 1;
                break;
            case "Print":
                this.impact = 0.002f;
                this.kosten = 10000;
                this.noetigeMitarbeiter = 3;
                break;
            case "Radio":
                this.impact = 0.015f;
                this.kosten = 60000;
                this.noetigeMitarbeiter = 4;
                break;
            case "TV":
                this.impact = 0.05f;
                this.kosten = 130000;
                this.noetigeMitarbeiter = 7;
                break;
        }
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
