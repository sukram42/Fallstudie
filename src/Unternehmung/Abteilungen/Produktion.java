package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlen;
import Unternehmung.Produkt;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasse, die die Produktionsabteilung repräsentiert
 * die Map produzierteProdukte kann als Lager angesehen werden
 * Created by lucadommes on 29.12.2016.
 */
public class Produktion extends Abteilung {

    private Map<String,Produkt> produzierteProdukte = new HashMap<String,Produkt>(); //kann als Lager angesehen werden
    private Kennzahlen kennzahlen; // Kennzahlenobjekt des Unternehmens benötigt, um Kennzahlen zu manipulieren

    /**
     * Konstruktor, zum Erstellen der Abteilung Produktion
     * @param kennzahlen Kennzahlenobjekt wird später benötigt, um Herstellkosten laufend fortzuschreiben (siehe produzieren())
     */
    public Produktion(Kennzahlen kennzahlen) {
        this.kennzahlen = kennzahlen;
    }

    /**
     * Funktion zum Produzieren von Produkten
     * @param name Bezeichnung des zu produzierenden Produkts (z.B. Rucksack)
     * @param anzahl Anzahl der zu produzierenden Produkte
     * @param herstellkosten des Produktes pro Stück // TODO feste Vorgabe je nach Produkt oder Auswahl durch den Spieler?
     */
    public void produzieren(String name, int anzahl, int herstellkosten){
        Produkt produkt = new Produkt(name, anzahl, herstellkosten);
        produzierteProdukte.put(produkt.getName(), produkt);
        this.kennzahlen.addHerstellkosten(herstellkosten * anzahl); // laufende Fortschreibung der gesamten Herstellkosten
    }

    /**
     * Funktion, die von verkaufen() (Klasse Vertrieb) zum Verändern des "Lager-"Bestandes (Map produzierteProdukte) aufgerufen wird
     * verändert vorhandene Anzahl des Produktes, falls alle Produkte verkauft werden löscht sie das Produkt aus der Map
     * @param name Produktbezeichnung (z.B. Rucksack)
     * @param anzahl Anzahl, um die der Bestand verringert werden soll
     */
    public void bestandVerändern(String name, int anzahl){
        Produkt produkt = produzierteProdukte.get(name);
        if(anzahl == produkt.getAnzahl()) {
            produzierteProdukte.remove(name);
        }else if(anzahl < produkt.getAnzahl()){
            produkt.setAnzahl(anzahl);
        }else{
            System.out.println("Es können nicht mehr Produkte verkauft werden als vorhanden");
        }
    }

    // Getter und Setter:
    public Map<String, Produkt> getProduzierteProdukte() {
        return produzierteProdukte;
    }
}
