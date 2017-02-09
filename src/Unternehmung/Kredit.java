package Unternehmung;

import Rules.Game;

import java.util.Calendar;

/**
 * Tilgungskredit, der über die Abteilung Finanzen aufgenommen werden kann
 * Created by lucadommes on 16.01.2017.
 */
public class Kredit {

    private int laufzeit;
    private int hoehe;
    private double restwert;
    private double zinssatz;
    private double zinsen;
    private double tilgung;
    private double annuitaet;
    private Calendar beginn;
    private Calendar end;

    /**
     * Spieler wählt Laufzeit und Höhe des Kredits aus, der Rest wird vorgegeben
     * @param hoehe in €
     * @param laufzeit in Timer Counts
     * @param zinssatz basierend auf dem neuen Verschuldungsgrad (siehe Finanzen.java)
     */
    public Kredit(int hoehe, int laufzeit, double zinssatz) {
        this.hoehe = hoehe;
        this.laufzeit = laufzeit;
        this.zinssatz = zinssatz;
        this.restwert = hoehe;
        this.zinsen = zinssatz * this.restwert;
        this.tilgung = this.hoehe / this.laufzeit;
        this.annuitaet = this.tilgung + this.zinsen;
        this.beginn = (Calendar) Game.getCalendar().clone();
        this.end = (Calendar) this.beginn.clone();
        this.end.add(Calendar.DAY_OF_MONTH, laufzeit);
    }

    public void update() {
        this.restwert -= this.tilgung;
        this.zinsen = this.zinssatz * this.restwert;
        this.annuitaet = this.tilgung + this.zinsen;
    }



    // Getter und Setter:
    public int getLaufzeit() {
        return laufzeit;
    }

    public void setLaufzeit(int laufzeit) {
        this.laufzeit = laufzeit;
    }

    public int getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public double getRestwert() {
        return restwert;
    }

    public void setRestwert(double restwert) {
        this.restwert = restwert;
    }

    public double getZinssatz() {
        return zinssatz;
    }

    public void setZinssatz(double zinssatz) {
        this.zinssatz = zinssatz;
    }

    public double getTilgung() {
        return tilgung;
    }

    public void setTilgung(double tilgung) {
        this.tilgung = tilgung;
    }

    public double getAnnuitaet() {
        return annuitaet;
    }

    public void setAnnuitaet(double annuitaet) {
        this.annuitaet = annuitaet;
    }

    public double getZinsen() {
        return zinsen;
    }

    public Calendar getBeginn() {
        return beginn;
    }

    public Calendar getEnd() {
        return end;
    }
}
