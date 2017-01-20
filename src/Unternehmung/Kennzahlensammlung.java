package Unternehmung;

import Exceptions.BankruptException;
import Unternehmung.Kennzahlen.*;
import Unternehmung.Kennzahlen.Bilanz;
import Unternehmung.Kennzahlen.GuV;
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
    private Bekanntheitsgrad bekanntheitsgrad;
    private Verkaufswahrscheinlichkeit verkaufswahrscheinlichkeit; // Wahrscheinlichkeit, Deals für sich zu gewinnen (abhängig von Maßnahmen
    private float liquideMittel;

    private Bilanz bilanz;
    private transient GuV guv;

    /**
     * Konstruktor zum Erstellen einen Kennzahlenobjekts eines Unternehmens (wird im Unternehmenskonstruktor aufgerufen)
     * @param eigenkapital muss bei Gründung des Unternehmens definiert werden
     */
    public Kennzahlensammlung(Unternehmen unternehmen,float eigenkapital) {
        // TODO alle Defaultwerte definieren (zumindest solche, die nicht 0 sein sollen)
        this.guv = new GuV(unternehmen);
        this.bilanz = new Bilanz(unternehmen);
        this.getBilanz().setEigenkapital(eigenkapital);
        this.liquideMittel = eigenkapital;
        this.unternehmen = unternehmen;
        this.bekanntheitsgrad = new Bekanntheitsgrad(unternehmen);
    }

    // Berechnungen:
    public void berechnen()
    {
        for(Kennzahl kennzahl  : weicheKennzahlen.values())
        {
            kennzahl.berechnen();
        }
        bilanz.berechnen();
    }

    public void update()
    {
        berechnen();
        this.guv.importAufwandUndErlös(); // GuV updaten
        try {
            this.liquiditätAnpassen(this.guv.getTaeglicheLiquiditätsveränderung());
        } catch (BankruptException e){
            e.printStackTrace();
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

    public void initWeicheKennzahlen(){
        weicheKennzahlen.put("mitarbeiterzufriedenheit",new Mitarbeiterzufriedenheit(unternehmen));
        weicheKennzahlen.put("kundenzufriedenheit",new Kundenzufriedenheit(unternehmen));
        weicheKennzahlen.put("image",new Image(unternehmen));
        weicheKennzahlen.put("produktqualität",new Produktqualität(unternehmen));
        weicheKennzahlen.put("verkaufswahrscheinlichkeit", new Verkaufswahrscheinlichkeit(unternehmen));
    }

    public Kennzahl getWeicheKennzahl(String kennzahl){
            return weicheKennzahlen.containsKey(kennzahl)?weicheKennzahlen.get(kennzahl):null;
    }


    // Getter und Setter:
    public Bekanntheitsgrad getBekanntheitsgrad() {
        return bekanntheitsgrad;
    }

    public  Kennzahl getMitarbeiterzufriedenheit() {
        return weicheKennzahlen.get("mitarbeiterzufriedenheit");
    }

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

    public Verkaufswahrscheinlichkeit getVerkaufswahrscheinlichkeit() {
        return verkaufswahrscheinlichkeit;
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

    public GuV getGuv() {
        return guv;
    }

    public void setGuv(GuV guv) {
        this.guv = guv;
    }
}
