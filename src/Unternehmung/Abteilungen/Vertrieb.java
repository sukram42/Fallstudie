package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlen;
import Unternehmung.Produkt;

import java.util.HashMap;
import java.util.Map;

/**
 * Abteilung, die für Vertrieb zuständig ist
 * die Map verkaufteProdukte enthält alle bereits verkauften Produkte (relevant für Umsatzberechnung!)
 * Created by lucadommes on 30.12.2016.
 */
public class Vertrieb extends Abteilung {

    private Map<String, Produkt> verkaufteProdukte = new HashMap<String, Produkt>(); // enthält alle bereits verkauften Produkte
    private Produktion produktion; // Produktionsobjekt, um auf "Lager" zugreifen zu können

    /**
     * Konstruktor, zum Erstellen der Abteilung Vertrieb
     * @param kennzahlen Kennzahlenobjekt wird später benötigt, um Umsatz laufend fortzuschreiben (siehe verkaufen())
     */
    public Vertrieb(Kennzahlen kennzahlen, Produktion produktion) {
        super(kennzahlen);
        this.produktion = produktion;
    }

    /**
     * Funktion zum Verkaufen von Produkten
     * verringert Bestand im Lager (Map produzierteProdukte in Klasse Produktion), erhöht den Bestand in der Map verkaufteProdukte und schreibt Umsatzzahlen fort
     * @param name Produktbezeichnung (z.B. Rucksack)
     * @param preis Verkaufspreis eines Produkts
     */
    public void verkaufen(String name, double preis) {
        // Herstellkosten des Produktes herausfinden:
        Map<String, Produkt> produzierteProdukte = this.produktion.getProduzierteProdukte();
        Produkt lager = produzierteProdukte.get(name);
        double herstellkosten = lager.getHerstellkosten();
        int vorhanden = produzierteProdukte.get(name).getAnzahl();
        // Produkt "verkaufen" (sprich aus Lager (Klasse Produktion) entfernen und zu Map verkaufteProdukte hinzufügen):
        int anzahl = (int) (vorhanden * this.kennzahlen.getAbsatzrate()); // Anzahl der Produkte, die abgesetzt werden, durch Verkaufsrate berechnen
        produktion.bestandVerändern(name, anzahl);
        Produkt verkauft = new Produkt(name, anzahl, herstellkosten, preis);
        verkaufteProdukte.put(name, verkauft);
        // erwirtschafteten Umsatz weitergeben (laufende Fortschreibung):
        super.kennzahlen.addUmsatz(preis * anzahl);
        System.out.println(anzahl + " mal Produkt \"" + name + "\" () verkauft!");
        System.out.println(anzahl + " von " + vorhanden + " Produkten (" + name + ") verkauft.");
    }
}