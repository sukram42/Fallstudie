package Unternehmung;

/**
 * Diese Klasse beinhaltet alle Kennzahlen eines Unternehmens
 * diese werden unterschieden in "weiche" und faktische Kennzahlen
 * zur Berechnung weicher Kennzahlen wird die Klasse "Kennzahl" genutzt, faktische Kennzahlen werden in int-Werten gespeichert
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
    private double gewinn;
    private double herstellkosten;
    private double ausschussrate;
    private double reklamationsrate;

}
