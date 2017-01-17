package Unternehmung;

import Exceptions.BankruptException;
import Unternehmung.Kennzahlen.Bilanz;
import Unternehmung.Kennzahlen.Kennzahl;
import Unternehmung.Kennzahlen.Mitarbeiterzufriedenheit;

import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse beinhaltet alle Kennzahlensammlung eines Unternehmens
 * diese werden unterschieden in "weiche" und faktische Kennzahlensammlung
 * zur Berechnung weicher Kennzahlensammlung wird die Klasse "Kennzahl" genutzt, faktische Kennzahlensammlung werden in double-Werten gespeichert
 * Created by lucadommes on 02.01.2017.
 */
public class Kennzahlensammlung {

    private transient Unternehmen unternehmen;
    // "weiche" Kennzahlensammlung:

    private Map<String,  Kennzahl> weicheKennzahlen = new HashMap<>();

    // faktische Kennzahlensammlung:
    private double marktanteil;
    private double ausschussrate;
    private double reklamationsrate;
    private double bekanntheitsgrad;
    private double absatzrate; // Wahrscheinlichkeit, alle seine Produkte zu verkaufen (abhängig von Maßnahmen und zufällige Ereignisse wie z.B. Konjunktur, Werbekampagnen etc.)
    private float liquideMittel;

    private Bilanz bilanz = new Bilanz(unternehmen);

    /**
     * Konstruktor zum Erstellen einen Kennzahlenobjekts eines Unternehmens (wird im Unternehmenskonstruktor aufgerufen)
     * @param eigenkapital muss bei Gründung des Unternehmens definiert werden
     */
    public Kennzahlensammlung(Unternehmen unternehmen,float eigenkapital) {
        // TODO alle Defaultwerte definieren (zumindest solche, die nicht 0 sein sollen)
        this.getBilanz().setEigenkapital(eigenkapital);
        this.absatzrate = 0.2;
        this.liquideMittel = eigenkapital;
        this.unternehmen = unternehmen;

        weicheKennzahlen.put("mitarbeiterzufriedenheit",new Mitarbeiterzufriedenheit(unternehmen));
        weicheKennzahlen.put("bekanntheitsheitsgrad",new Kennzahl(unternehmen));
        weicheKennzahlen.put("image",new Kennzahl(unternehmen));
    }

    // Berechnungen:
    public void berechnen()
    {
        for(Kennzahl kennzahl  : weicheKennzahlen.values())
        {
            kennzahl.berechnen();
        }
        verkaufsrateBerechnen();
        bilanz.berechnen();

    }

    public void update()
    {
        berechnen();
        bilanz.getGuv().importAufwandUndErlös(); // GuV updaten
        try {
            this.liquiditätAnpassen(bilanz.getGuv().getTaeglicheLiquiditätsveränderung());
        } catch (BankruptException e){
            e.printStackTrace();
        }
    }

    /**
     * Methode, die die Verkaufsrate berechnet (beeinflusst von Bekanntheitsgrad, Image, Kundenzufriedenheit, Mitarbeiterzufriedenheit, ...)
     * // TODO weitere Kennzahlensammlung mit einrechnen
     */
    public void verkaufsrateBerechnen(){
        double verkaufsrate = this.getBekanntheitsgrad();
        if(verkaufsrate >= 1){
            this.setAbsatzrate(1);
        }else {
            this.setAbsatzrate(verkaufsrate);
        }
    }

    /**
     * passt zu jedem Timer Count die liquidität entsprechend an (wirft bei Zahlungsunfähigkeit eine BankruptException)
     * wird außerdem aufgerufen, wenn einmalige Liquiditätsveränderungen statt finden (z.B. Kauf einer Maschine oder einmaliger Umsatzerlös)
     * @param liquiditätsVeränderung berechnet von GuV.getTaeglicheLiquiditätsveränderung
     */
    public void liquiditätAnpassen(float liquiditätsVeränderung) throws BankruptException {
        if (this.liquideMittel * -1 <= liquiditätsVeränderung){
            this.setLiquideMittel(this.liquideMittel + liquiditätsVeränderung);
        } else {
            throw new BankruptException(unternehmen);
        }
    }

    public Kennzahl getWeicheKennzahl(String kennzahl){
            return weicheKennzahlen.containsKey(kennzahl)?weicheKennzahlen.get(kennzahl):null;
    }


    // Getter und Setter:
    public double getBekanntheitsgrad() {
        return bekanntheitsgrad;
    }

    public void setBekanntheitsgrad(double bekanntheitsgrad) {
        if (bekanntheitsgrad >= 1){
            this.bekanntheitsgrad = 1;
            this.verkaufsrateBerechnen();
        }else {
            this.bekanntheitsgrad = bekanntheitsgrad;
            this.verkaufsrateBerechnen();
        }
    }

    public  Kennzahl getMitarbeiterzufriedenheit() {
        return weicheKennzahlen.get("mitarbeiterzufriedenheit");
    }

//    public void setMitarbeiterzufriedenheit( Kennzahl mitarbeiterzufriedenheit) {
//        this.mitarbeiterzufriedenheit = mitarbeiterzufriedenheit;
//    }

//    public  Kennzahl getKundenzufriedenheit() {
//        return kundenzufriedenheit;
//    }
//
//    public void setKundenzufriedenheit( Kennzahl kundenzufriedenheit) {
//        this.kundenzufriedenheit = kundenzufriedenheit;
//    }

//    public  Kennzahl getImage() {
//        return image;
//    }

//    public void setImage( Kennzahl image) {
//        this.image = image;
//    }

    public double getMarktanteil() {
        return marktanteil;
    }

    public void setMarktanteil(double marktanteil) {
        this.marktanteil = marktanteil;
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

    public double getAbsatzrate() {
        return absatzrate;
    }

    public void setAbsatzrate(double absatzrate) {
        if(absatzrate >= 1){
            this.absatzrate = 1;
        }else {
            this.absatzrate = absatzrate;
        }
    }

    public double getLiquideMittel() {
        return liquideMittel;
    }

    public void setLiquideMittel(float liquideMittel) {
        this.liquideMittel = liquideMittel;
    }

    public Bilanz getBilanz() {
        return bilanz;
    }
}
