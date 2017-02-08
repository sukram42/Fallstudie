package Unternehmung;

import Exceptions.BankruptException;
import Exceptions.ZuWenigCashException;

/**
 * repräsentiert eine Maschine
 * Created by lucadommes on 07.01.2017.
 */
public class Maschine {

    private int klasse; // etwa 1, 2, 3 -> unterschiedliche Kapazität, Anschaffungskosten, Halbwertszeit
    private int kapazitaet; // kann maximal so viele Produkte pro Monat produzieren. abhängig von klasse
    private int anschaffungskst; // abhängig von klasse
    private float energiekosten; // pro Tag, bei jeder Maschinenklasse gleich
    private double status; // sinkt jeden Monat um X, kann durch Reparaturen wieder hochgesetzt werden
    private String produkt; // das Produkt, das mit dieser Maschine produziert wird ("Rucksack", "Rucksacktech", "Duffel" oder "Reisetasche")
    private static final float ENERGIEKOSTEN = 100;

    /**
     * Maschinen-Konstruktor
     * @param produkt Produktname: "Rucksack", "Rucksacktech", "Duffel" oder "Reisetasche"
     * @param klasse 1, 2 oder 3 (unterschiedliche Ausbringungsmengen und Anschaffungskosten)
     */
    public Maschine(String produkt, int klasse) {
        this.produkt = produkt;
        this.klasse = klasse;
        this.energiekosten = ENERGIEKOSTEN; // <- TODO realistischen / zum Spiel passenden Wert für Energiekosten einsetzen
        this.status = 1;
        findKapazitätUndAnschaffungskst(klasse);
    }

    /**
     * Methode zum reparieren einer Maschine (setzt status wieder auf 1)
     * @param kennzahlensammlung Ausgangsstatus der Maschine, um Kosten zu berechnen
     */
    public void reparieren(Kennzahlensammlung kennzahlensammlung){
        double currentStatus = this.status; // Ausgangsstatus der Maschine
        float kosten = 0;
        try {
            // TODO realistisch / zum Spiel passende Werte einsetzen
            if (currentStatus < 0.1) {
                kosten = 1500;
            } else if (currentStatus < 0.2) {
                kosten = 1200;
            } else if (currentStatus < 0.3) {
                kosten = 1000;
            } else if (currentStatus < 0.4) {
                kosten = 900;
            } else if (currentStatus < 0.5) {
                kosten = 800;
            } else if (currentStatus < 0.6) {
                kosten = 700;
            } else if (currentStatus < 0.7) {
                kosten = 600;
            } else if (currentStatus < 0.8) {
                kosten = 500;
            } else if (currentStatus < 0.9) {
                kosten = 400;
            } else if (currentStatus < 1) {
                kosten = 300;
            }
            if (kennzahlensammlung.getBilanz().liquiditaetAusreichend(kosten)) {
                kennzahlensammlung.getGuv().addFremdinstandhaltung(kosten);
                kennzahlensammlung.getBilanz().liquiditaetAnpassen(-kosten);
                this.status = 1; // nach Reparatur wieder 100%
            }
        } catch (ZuWenigCashException | BankruptException e) {
            e.printStackTrace();
        }
    }

    private void findKapazitätUndAnschaffungskst(int klasse){
        switch (klasse){
            // TODO realistisch / zum Spiel passende Werte einsetzen
            case 1:
                this.kapazitaet = 300;
                this.anschaffungskst = 7500;
                break;
            case 2:
                this.kapazitaet = 600;
                this.anschaffungskst = 15000;
                break;
            case 3:
                this.kapazitaet = 1200;
                this.anschaffungskst = 30000;
                break;
        }
    }

    /**
     * setzt Maschinenstatus bei jedem Timer Count herunter
     */
    public void statusUndEnergiekstRuntersetzen(){
        if (this.status > 0) {
            this.status -= 0.000962f; // jede Maschine geht 0.0962% pro Tag kaputt, dadurch ist sie (ohne Instandhaltung) nach 3 Jahren bei 0%
            this.energiekosten = (float) (1 + (1 - this.status)) * ENERGIEKOSTEN; // Energiekosten steigen äquivalent
        }
    }

    // Getter und Setter:
    public int getKlasse() {
        return klasse;
    }

    public int getKapazitaet() {
        return kapazitaet;
    }

    public int getAnschaffungskst() {
        return anschaffungskst;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public float getEnergiekosten() {
        return energiekosten;
    }

    public String getProdukt() {
        return produkt;
    }
}
