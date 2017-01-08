package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlen;

/**
 * Klasse, die die Abteilung Marketing repräsentiert
 * Created by lucadommes on 29.12.2016.
 */
public class Marketing extends Abteilung {

    /**
     * Konstruktor, zum Erstellen der Abteilung Marketing
     * @param kennzahlen Kennzahlenobjekt wird später benötigt, um Kennzahlen laufend fortzuschreiben / zu berechnen
     */
    public Marketing(Kennzahlen kennzahlen) {
        super(kennzahlen);
    }

    /**
     * Werbekampagne, die jeweils für ein Jahr (oder Quartal?) läuft
     * @param kampagne Art der Kampagne, etwa Plakate, Print, Radio oder TV (unterschiedlich teuer und unterschiedlich effektiv)
     */
    public void marketingKampagne(String kampagne){
        // Bekanntheitsgrad erhöhen:
        switch (kampagne){
            case "Plakate":
                this.kennzahlen.setBekanntheitsgrad(this.kennzahlen.getBekanntheitsgrad() + 0.1);
                this.kennzahlen.addSonstigeKosten(1000);
                System.out.println("Werbeplakate aufgehängt: Bekanntheitsgrad um 10% gestiegen, Kosten: 1000€");
                break;
            case "Print":
                this.kennzahlen.setBekanntheitsgrad(this.kennzahlen.getBekanntheitsgrad() + 0.2);
                this.kennzahlen.addSonstigeKosten(2500);
                System.out.println("Werbung in Printmedien geschaltet: Bekanntheitsgrad um 20% gestiegen, Kosten: 2500€");
                break;
            case "Radio":
                this.kennzahlen.setBekanntheitsgrad(this.kennzahlen.getBekanntheitsgrad() + 0.3);
                this.kennzahlen.addSonstigeKosten(5000);
                System.out.println("Radiospot: Bekanntheitsgrad um 30% gestiegen, Kosten: 5000€");
                break;
            case "TV":
                this.kennzahlen.setBekanntheitsgrad(this.kennzahlen.getBekanntheitsgrad() + 0.4);
                this.kennzahlen.addSonstigeKosten(10000);
                System.out.println("TV-Spot: Bekanntheitsgrad um 40% gestiegen, Kosten: 10000€");
                break;
        }
    }

    /**
     * Methode zur Durchführung von Marktforschung
     * @param umfang drei verschiedene "Größen" mit unterschiedlichen Kosten und Effektivität
     */
    public void marktforschung(int umfang){
        switch (umfang){
            case 1:
                this.kennzahlen.setVerkaufsrate(this.kennzahlen.getVerkaufsrate() + 0.1);
                this.kennzahlen.addSonstigeKosten(2500);
                System.out.println("Marktforschung durchgeführt: Kosten 2500 €, Erhöhung der Verkaufsrate um 10%");
                break;
            case 2:
                this.kennzahlen.setVerkaufsrate(this.kennzahlen.getVerkaufsrate() + 0.21);
                this.kennzahlen.addSonstigeKosten(5000);
                System.out.println("Marktforschung durchgeführt: Kosten 5000 €, Erhöhung der Verkaufsrate um 21%");
                break;
            case 3:
                this.kennzahlen.setVerkaufsrate(this.kennzahlen.getVerkaufsrate() + 0.43);
                this.kennzahlen.addSonstigeKosten(10000);
                System.out.println("Marktforschung durchgeführt: Kosten 10000 €, Erhöhung der Verkaufsrate um 43%");
                break;
        }
    }

}
