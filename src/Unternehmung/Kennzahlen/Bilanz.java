package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;

/**
 * Created by oehlersj on 13.01.2017.
 */
public class Bilanz {

    //Aktiva
    private float TaMaschWert;
    private float GebäudeWert;
    private float FEWert;
    private float liquideMittel;

    //Passiva
    private float eigenkapital;
    private float fremdkapital;


    public Bilanz(Unternehmen unternehmen) {

        this.eigenkapital = 100000;
        this.liquideMittel=100000;





    }

    //muss am Ende jedes Jahres ausgeführt werden
    public void berechnen()
    {
       // addEigenkapital(this.guv.jahresUeberschussBerechnen()); -> verschoben in Kennzahlensammlung, weil dort nun die GuV liegt

    }

    public void addEigenkapital(float jahresueberschuss){
        this.setEigenkapital(this.eigenkapital + jahresueberschuss);
    }

   // public void addLiquideMittel(float jahresueberschuss){
     //   this.setLiquideMittel(this.liquideMittel + jahresueberschuss);
  //  }



    public float getTaMaschWert() {
        return TaMaschWert;
    }

    public void setTaMaschWert(float taMaschWert) {
        TaMaschWert = taMaschWert;
    }

    public float getGebäudeWert() {
        return GebäudeWert;
    }

    public void setGebäudeWert(float gebäudeWert) {
        GebäudeWert = gebäudeWert;
    }

    public float getFEWert() {
        return FEWert;
    }

    public void setFEWert(float FEWert) {
        this.FEWert = FEWert;
    }

    public float getLiquideMittel() {
        return liquideMittel;
    }

    public void setLiquideMittel(float liquideMittel) {
        this.liquideMittel = liquideMittel;
    }

    public float getEigenkapital() {
        return eigenkapital;
    }

    public void setEigenkapital(float eigenkapital) {
        this.eigenkapital = eigenkapital;
    }

    public float getFremdkapital() {
        return fremdkapital;
    }

    public void setFremdkapital(float fremdkapital) {
        this.fremdkapital = fremdkapital;
    }
}
