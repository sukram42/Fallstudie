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

    private Map<String, Produkt> verkaufteProdukte = new HashMap<String, Produkt>(); // enthält alle bereits verkauften Produkte (relevant für Umsatzberechnung!)

    /**
     * Funktion zum Verkaufen von Produkten
     * verringert Bestand im Lager (Map produzierteProdukte in Klasse Produktion), erhöht den Bestand in der Map verkaufteProdukte und schreibt Umsatzzahlen fort
     * @param produktion Produktions-Objekt, um Zugriff auf das Lager (Map produzierteProdukte) zu haben
     * @param name Produktbezeichnung (z.B. Rucksack)
     * @param preis Verkaufspreis eines Produkts
     * @param anzahl Anzahl der verkauften / zu verkaufenden Produkte
     * @param kennzahlen Kennzahlen-Objekt zur laufenden Fortschreibung des Umsatzes
     */
    public void verkaufen(Produktion produktion, String name, double preis, int anzahl, Kennzahlen kennzahlen) {
        // Herstellkosten des Produktes herausfinden:
        Map<String, Produkt> produzierteProdukte = produktion.getProduzierteProdukte();
        Produkt lager = produzierteProdukte.get(name);
        double herstellkosten = lager.getHerstellkosten();
        // Produkt "verkaufen" (sprich aus Lager (Klasse Produktion) entfernen und zu Map verkaufteProdukte hinzufügen):
        produktion.bestandVerändern(name, anzahl);
        Produkt verkauft = new Produkt(name, anzahl, herstellkosten, preis);
        verkaufteProdukte.put(name, verkauft);
        // erwirtschafteten Umsatz weitergeben (laufende Fortschreibung):
        kennzahlen.addUmsatz(preis * anzahl);
    }
}