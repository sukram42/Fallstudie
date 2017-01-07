package Unternehmung;

import Unternehmung.Abteilungen.Produktion;
import Unternehmung.Abteilungen.Vertrieb;

/**
 * Diese Klasse beinhaltet alle Kennzahlen eines Unternehmens
 * diese werden unterschieden in "weiche" und faktische Kennzahlen
 * zur Berechnung weicher Kennzahlen wird die Klasse "Kennzahl" genutzt, faktische Kennzahlen werden in double-Werten gespeichert
 * Created by lucadommes on 02.01.2017.
 */
public class Kennzahlen {

    // "weiche" Kennzahlen:
    private Kennzahl bekanntheitsgrad = new Kennzahl();
    private Kennzahl mitarbeiterzufriedenheit = new Kennzahl();
    private Kennzahl kundenzufriedenheit = new Kennzahl();
    private Kennzahl image = new Kennzahl(); // soll sich aus Mitarbeiterzufriedenheit, Reklamationsrate und Kundenzufriedenheit berechnen

    // faktische Kennzahlen:
    private double marktanteil;
    private double umsatz; // wird laufend fortgeschrieben (siehe unten addUmsatz())
    private double herstellkosten; // wird laufend fortgeschrieben (siehe unten addHerstellkosten())
    private double sonstigeKosten; // wird laufend fortgeschrieben (siehe unten addSonstigeKosten)
    private double gewinn; // wird bei Änderungen von Umsatz oder Kosten automatisch aktualisiert (siehe unten gewinnBerechnen())
    private double ausschussrate;
    private double reklamationsrate;
    private double fremdkapital;
    private double eigenkapital;

    /**
     * Konstruktor zum Erstellen einen Kennzahlenobjekts eines Unternehmens (wird im Unternehmenskonstruktor aufgerufen)
     * @param eigenkapital muss bei Gründung des Unternehmens definiert werden
     * @param fremdkapital muss bei Gründung des Unternehmens definiert werden
     */
    public Kennzahlen(double eigenkapital, double fremdkapital) {
        this.eigenkapital = eigenkapital;
        this.fremdkapital = fremdkapital;
    }

    // Berechnungen:
    /**
     * Funktion, die den Gewinn (Jahresüberschuss) berechnet. Wird von addX() ausgeführt (siehe unten)
     */
    public void gewinnBerechnen(){
        this.setGewinn(this.umsatz - (this.herstellkosten + this.sonstigeKosten));
    }


    // laufende Fortschreibung von Kennzahlen:
    /**
     * Funktion, die Herstellkosten fortschreibt. Wird aufgerufen von Produktion.produzieren()
     * @param kosten gesamte Herstellkosten der Neuproduktion, sprich Herstellungskosten pro Stück * Menge
     */
    public void addHerstellkosten(double kosten){
        this.setHerstellkosten(this.herstellkosten + kosten);
        this.gewinnBerechnen();
    }

    /**
     * Funktion, die sonstigeKosten fortschreibt. Muss von allen Funktion aufgerufen werden, von denen "Geld ausgegeben wird"
     * @param kosten Kosten z.B. einer Werbekampagne
     */
    public void addSonstigeKosten(double kosten){
        this.setSonstigeKosten(this.herstellkosten + kosten);
        this.gewinnBerechnen();
    }

    /**
     * Funktion, die Umsatz fortschreibt. Wird von Vertrieb.verkaufen() aufgerufen
     * @param umsatz erwirtschafteter Umsatz, sprich Verkaufspreis * Menge
     */
    public void addUmsatz(double umsatz){
        this.setUmsatz(this.umsatz + umsatz);
        this.gewinnBerechnen();
    }


    // Getter und Setter:
    public Kennzahl getBekanntheitsgrad() {
        return bekanntheitsgrad;
    }

    public void setBekanntheitsgrad(Kennzahl bekanntheitsgrad) {
        this.bekanntheitsgrad = bekanntheitsgrad;
    }

    public Kennzahl getMitarbeiterzufriedenheit() {
        return mitarbeiterzufriedenheit;
    }

    public void setMitarbeiterzufriedenheit(Kennzahl mitarbeiterzufriedenheit) {
        this.mitarbeiterzufriedenheit = mitarbeiterzufriedenheit;
    }

    public Kennzahl getKundenzufriedenheit() {
        return kundenzufriedenheit;
    }

    public void setKundenzufriedenheit(Kennzahl kundenzufriedenheit) {
        this.kundenzufriedenheit = kundenzufriedenheit;
    }

    public Kennzahl getImage() {
        return image;
    }

    public void setImage(Kennzahl image) {
        this.image = image;
    }

    public double getMarktanteil() {
        return marktanteil;
    }

    public void setMarktanteil(double marktanteil) {
        this.marktanteil = marktanteil;
    }

    public double getUmsatz() {
        return umsatz;
    }

    public void setUmsatz(double umsatz) {
        this.umsatz = umsatz;
    }

    public double getHerstellkosten() {
        return herstellkosten;
    }

    public void setHerstellkosten(double herstellkosten) {
        this.herstellkosten = herstellkosten;
    }

    public double getSonstigeKosten() {
        return sonstigeKosten;
    }

    public void setSonstigeKosten(double sonstigeKosten) {
        this.sonstigeKosten = sonstigeKosten;
    }

    public double getGewinn() {
        return gewinn;
    }

    public void setGewinn(double gewinn) {
        this.gewinn = gewinn;
    }

    public double getAusschussrate() {
        return ausschussrate;
    }

    public void setAusschussrate(double ausschussrate) {
        this.ausschussrate = ausschussrate;
    }

    public double getReklamationsrate() {
        return reklamationsrate;
    }

    public void setReklamationsrate(double reklamationsrate) {
        this.reklamationsrate = reklamationsrate;
    }

    public double getFremdkapital() {
        return fremdkapital;
    }

    public void setFremdkapital(double fremdkapital) {
        this.fremdkapital = fremdkapital;
    }

    public double getEigenkapital() {
        return eigenkapital;
    }

    public void setEigenkapital(double eigenkapital) {
        this.eigenkapital = eigenkapital;
    }
}
