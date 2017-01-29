package Unternehmung;

import java.util.ArrayList;
import java.util.Random;

/**
 * jeden ersten Tag des Monats werden Ausschreibungen generiert
 * am Ende des Monats fällt die Entscheidung, wer den Zuschlag bekommt
 * geliefert werden muss bis Ende des darauf folgenden Monats (bzw. ab dem darauf folgenden Monat)
 * Created by oehlersj on 19.01.2017.
 */
public class Ausschreibung {
    private static final transient String[] produkte = {"Rucksack", "Rucksacktech", "Duffel", "Reisetasche"};
    private static final transient char[] qualitaetsstufen = {'A', 'B', 'C'};
    private static final transient String[] kunden = {"SchickerRucksackAG","Outdoorjoh.com","BergNeelSen","BergeUndSo","BergDoktor.com","Berglises Hausladen","My Litty Moutain","AdventureTime","LaBoutiqueDeLaSporte"}; // TODO Array mit (fiktiven oder echten?) Kundennamen füllen
    private Vertrag vertrag;
    private ArrayList<Unternehmen> bewerber = new ArrayList<>();

    public Ausschreibung() {
        this.vertrag = this.generateRandomVertrag();
    }

    /**
     * @return Vertrag mit zufällig generierten Werten
     */
    private Vertrag generateRandomVertrag(){
        Random random = new Random();
        float preis = 50; // TODO Preise basierend auf Kennzahlen und Produkt generieren
        String kunde = kunden[random.nextInt(kunden.length)];
        int laufzeit = random.nextInt(59) + 1; // generiert zufällige Laufzeit zwischen 1 (= einmaliger Auftrag) und 60 Monaten (= 5 Jahre)
        return new Vertrag(this.generateRandomProduktlinie(), preis, kunde, laufzeit);
    }

    /**
     * @return Produktlinie mit zufällig generierten Werten
     */
    private Produktlinie generateRandomProduktlinie(){
        // TODO ist die Gefahr hier zu groß, dass Produkte, die ein Spieler produziert hat überhaupt nicht nachgefragt werden?
        Random random = new Random();
        String name = produkte[random.nextInt(4)];
        char qualitaetsstufe = qualitaetsstufen[random.nextInt(3)];
        int menge = random.nextInt(500) + 25; // TODO Größe der Ausschreibungen an durchschnittlicher Produktionsmenge festmachen!
        return new Produktlinie(new Produkt(name, qualitaetsstufe), menge);
    }


    //Getter und Setter:
    public Vertrag getVertrag() {
        return vertrag;
    }

    public ArrayList<Unternehmen> getBewerber() {
        return bewerber;
    }
}
