package Unternehmung.Kennzahlen;

import Exceptions.BankruptException;
import Exceptions.ZuWenigCashException;
import Unternehmung.Unternehmen;

/**
 * Created by oehlersj on 13.01.2017.
 */
public class Bilanz {

    private final transient Unternehmen unternehmen;
    //Aktiva
    private float taMaschWert;
    private float gebaeudeWert;
    private float feWert;
    private float liquideMittel;

    //Passiva
    private float eigenkapital;
    private float fremdkapital;

    private float summeAktiva;
    private float summePassiva;

    public Bilanz(Unternehmen unternehmen) {
        this.eigenkapital = 100000;
        this.liquideMittel = 100000;
        this.unternehmen = unternehmen;

    }

    //muss am Ende jedes Jahres ausgeführt werden
    public void berechnen() {
        // eigenkapitalAnpassen(this.guv.jahresabschluss()); -> verschoben in Kennzahlensammlung, weil dort nun die GuV liegt
    }

    /**
     * passt zu jedem Timer Count die liquidität entsprechend an (wirft bei Zahlungsunfähigkeit eine BankruptException)
     * wird außerdem aufgerufen, wenn einmalige Liquiditätsveränderungen statt finden (z.B. Kauf einer Maschine oder einmaliger Umsatzerlös)
     *
     * @param liquiditätsVeränderung berechnet von GuV.getTaeglicheLiquiditaetsveraenderung
     */
    public void liquiditaetAnpassen(float liquiditätsVeränderung) throws BankruptException {
        if (this.liquideMittel * -1 <= liquiditätsVeränderung) {
            this.setLiquideMittel(this.liquideMittel + liquiditätsVeränderung);
        } else {
            throw new BankruptException(unternehmen);
        }
    }

    /**
     * wird von Methoden wie maschineKaufen ausgegeben und prüft, ob genügend Liquidität vorhanden ist und wirft ggf. eine ZuWenigCashException
     * wird nur von Methoden aufgerufen, durch die bei unzureichender Liquidität nicht eine BankruptException geworfen werden soll
     *
     * @param kosten der Investition
     * @return true, wenn genügend Cash vorhanden ist
     */
    public boolean liquiditaetAusreichend(float kosten) throws ZuWenigCashException {
        if (this.liquideMittel >= kosten) {
            return true;
        } else {
            throw new ZuWenigCashException();
        }
    }

    public void eigenkapitalAnpassen(float veränderung) {
        this.eigenkapital += veränderung;
    }

    public void fremdkapitalAnpassen(float veränderung) {
        this.fremdkapital += veränderung;
    }

    public void addTAMasch(float wert) {
        this.taMaschWert += wert;
    }

    public void addGebäude(float wert) {
        this.gebaeudeWert += wert;
    }


    public float getTaMaschWert() {
        return taMaschWert;
    }

    public void setTaMaschWert(float taMaschWert) {
        this.taMaschWert = taMaschWert;
    }

    public float getGebaeudeWert() {
        return gebaeudeWert;
    }

    public void setGebaeudeWert(float gebaeudeWert) {
        this.gebaeudeWert = gebaeudeWert;
    }

    public float getFeWert() {
        return feWert;
    }

    public void setFeWert(float feWert) {
        this.feWert = feWert;
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

    /**
     * Methode fürs Interface: setzt die Summen der Bilanzseiten, so dass man sie auf dem Interface anzeigen lassen kann.
     */
    public void setSummen() {
        this.summeAktiva = gebaeudeWert + feWert + liquideMittel + taMaschWert;
        this.summePassiva = eigenkapital + fremdkapital;
    }
}
