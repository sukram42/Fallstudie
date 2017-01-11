package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlensammlung;

/**
 * Klasse, die die Abteilung Marketing repräsentiert
 * Created by lucadommes on 29.12.2016.
 */
public class Marketing extends Abteilung {

    /**
     * Konstruktor, zum Erstellen der Abteilung Marketing
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Kennzahlensammlung laufend fortzuschreiben / zu berechnen
     */
    public Marketing(Kennzahlensammlung kennzahlensammlung) {
        super(kennzahlensammlung);
    }

    /**
     * Werbekampagne, die jeweils für ein Jahr (oder Quartal?) läuft
     * @param kampagne Art der Kampagne, etwa Plakate, Print, Radio oder TV (unterschiedlich teuer und unterschiedlich effektiv)
     */
    public void marketingKampagne(String kampagne){
        double impact = 0;
        int kosten = 0;
        switch (kampagne){
            case "Plakate":
                impact = 0.1;
                kosten = 1000;
                break;
            case "Print":
                impact = 0.2;
                kosten = 2500;
                break;
            case "Radio":
                impact = 0.3;
                kosten = 5000;
                break;
            case "TV":
                impact = 0.4;
                kosten = 10000;
                break;
        }
        if (this.kennzahlensammlung.liquiditätVorhanden(kosten, "sonstige Kosten")){
            this.kennzahlensammlung.setBekanntheitsgrad(this.kennzahlensammlung.getBekanntheitsgrad() + impact);
            System.out.println("Marketingkampagne \"" + kampagne + "\" gestartet. Kosten: " + kosten + " €, Bekanntheitsgrad steigt um " + impact);
        }
    }

    /**
     * Methode zur Durchführung von Marktforschung. Hierdurch können z.B. besser Zielgruppen angesprochen werden,
     * wodurch sich Produkte besser verkaufen lassen (Absatzrate steigt)
     * @param umfang drei verschiedene "Größen" mit unterschiedlichen Kosten und Effektivität
     */
    public void marktforschung(int umfang){
        double impact = 0;
        int kosten = 0;
        switch (umfang){
            case 1:
                impact = 0.1;
                kosten = 2500;
                break;
            case 2:
                impact = 0.21;
                kosten = 5000;
                break;
            case 3:
                impact = 0.43;
                kosten = 10000;
                break;
        }
        if (this.kennzahlensammlung.liquiditätVorhanden(kosten, "sonstige Kosten")) {
            this.kennzahlensammlung.setAbsatzrate(this.kennzahlensammlung.getAbsatzrate() + impact);
            System.out.println("Marktforschung durchgeführt: Kosten " + kosten + " €, Erhöhung der Verkaufsrate um " + impact);
        }else {
            System.out.println("Nicht genügend Liquidität vorhanden!");
        }
    }

}
