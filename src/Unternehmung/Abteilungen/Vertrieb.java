package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
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

    public void verkaufen(Produktion produktion, String name, double preis, int anzahl) {
        // Herstellkosten des Produktes herausfinden:
        Map<String, Produkt> produzierteProdukte = produktion.getProduzierteProdukte();
        Produkt lager = produzierteProdukte.get(name);
        double herstellkosten = lager.getHerstellkosten();
        // Produkt "verkaufen" (sprich aus Lager (Klasse Produktion) entfernen und zu Map verkaufteProdukte hinzufügen):
        produktion.bestandVerändern(name, anzahl);
        Produkt verkauft = new Produkt(name, anzahl, herstellkosten, preis);
        verkaufteProdukte.put(name, verkauft);
    }

}