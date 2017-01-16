package Unternehmung;

import Rules.Game;

/**
 * Tilgungskredit, der über die Abteilung Finanzen aufgenommen werden kann
 * Created by lucadommes on 16.01.2017.
 */
public class Kredit {

    int laufzeit;
    int hoehe;
    double restwert;
    double zinssatz;
    double zinsen;
    double tilgung;
    double annuität;
    long beginn;

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
        this.annuität = this.tilgung + this.zinsen;
        this.beginn = Game.getTime();
    }

    public void update() {
        this.restwert -= this.tilgung;
        this.zinsen = this.zinssatz * this.restwert;
        this.annuität = this.tilgung + this.zinsen;
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

    public double getAnnuität() {
        return annuität;
    }

    public void setAnnuität(double annuität) {
        this.annuität = annuität;
    }

    public double getZinsen() {
        return zinsen;
    }

    public long getBeginn() {
        return beginn;
    }
}
