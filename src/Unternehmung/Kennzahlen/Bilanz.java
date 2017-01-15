package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;

/**
 * Created by oehlersj on 13.01.2017.
 */
public class Bilanz {

    private transient GuV guv;

    //Aktiva
    private double TaMaschWert;
    private double GebäudeWert;
    private double FEWert;
    private double liquideMittel;

    //Passiva
    private double eigenkapital;
    private double fremdkapital;


    public Bilanz(Unternehmen unternehmen) {

        this.guv = new GuV(unternehmen);
        this.eigenkapital = 100000;
        this.liquideMittel=100000;

        //muss am Ende jeder Periode ausgeführt werden

       //eigentlich wird beim richtigen Bilanzabschluss Jahresüberschuss mit EK verrechnet, machen wir aber sowieso durchgehend?
        // addEigenkapital(this.guv.jahresUeberschuss);
        // addLiquideMittel(this.guv.jahresUeberschuss);


    }

    public void berechnen()
    {
        this.guv.jahresUeberschussBerechnen();
    }

    public void addEigenkapital(double jahresueberschuss){
        this.setEigenkapital(this.eigenkapital + jahresueberschuss);
    }

    public void addLiquideMittel(double jahresueberschuss){
        this.setLiquideMittel(this.liquideMittel + jahresueberschuss);
    }



    public GuV getGuv() {
        return guv;
    }

    public void setGuv(GuV guv) {
        this.guv = guv;
    }

    public double getTaMaschWert() {
        return TaMaschWert;
    }

    public void setTaMaschWert(double taMaschWert) {
        TaMaschWert = taMaschWert;
    }

    public double getGebäudeWert() {
        return GebäudeWert;
    }

    public void setGebäudeWert(double gebäudeWert) {
        GebäudeWert = gebäudeWert;
    }

    public double getFEWert() {
        return FEWert;
    }

    public void setFEWert(double FEWert) {
        this.FEWert = FEWert;
    }

    public double getLiquideMittel() {
        return liquideMittel;
    }

    public void setLiquideMittel(double liquideMittel) {
        this.liquideMittel = liquideMittel;
    }

    public double getEigenkapital() {
        return eigenkapital;
    }

    public void setEigenkapital(double eigenkapital) {
        this.eigenkapital = eigenkapital;
    }

    public double getFremdkapital() {
        return fremdkapital;
    }

    public void setFremdkapital(double fremdkapital) {
        this.fremdkapital = fremdkapital;
    }
}
