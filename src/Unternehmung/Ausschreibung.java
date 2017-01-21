package Unternehmung;

import Rules.Game;

import java.util.Calendar;
import java.util.Random;

/**
 * jeden ersten Tag des Monats werden Ausschreibungen generiert
 * am Ende des Monats fällt die Entscheidung, wer den Zuschlag bekommt
 * geliefert werden muss bis Ende des darauf folgenden Monats (bzw. ab dem darauf folgenden Monat)
 * Created by oehlersj on 19.01.2017.
 */
public class Ausschreibung {
    private static final String[] produkte = {"Rucksack", "Rucksacktech", "Duffel", "Reisetasche"};
    private static final char[] qualitaetsstufen = {'A', 'B', 'C'};
    private static final String[] kunden = {""}; // TODO Array mit (fiktiven oder echten?) Kundennamen füllen
    private Vertrag vertrag;
    private Calendar entscheidung;

    public Ausschreibung() {
        this.entscheidung = Game.getCalendar();
        this.entscheidung.add(Calendar.MONTH, 1);
        this.vertrag = this.generateRandomVertrag();
    }

    /**
     * @return Vertrag mit zufällig generierten Werten
     */
    private Vertrag generateRandomVertrag(){
        Random random = new Random();
        float preis = 50; // TODO Preise basierend auf Kennzahlen und Produkt generieren
        String kunde = kunden[random.nextInt(kunden.length - 1)];
        int laufzeit = random.nextInt(59) + 1; // generiert zufällige Laufzeit zwischen 1 (= einmaliger Auftrag) und 60 Monaten (= 5 Jahre)
        return new Vertrag(this.generateRandomProduktlinie(), preis, kunde, laufzeit);
    }

    /**
     * @return Produktlinie mit zufällig generierten Werten
     */
    private Produktlinie generateRandomProduktlinie(){
        // TODO ist die Gefahr hier zu groß, dass Produkte, die ein Spieler produziert hat überhaupt nicht nachgefragt werden?
        Random random = new Random();
        String name = produkte[random.nextInt(3)];
        char qualitaetsstufe = qualitaetsstufen[random.nextInt(2)];
        int menge = random.nextInt(500) + 25; // TODO Größe der Ausschreibungen an durchschnittlicher Produktionsmenge festmachen!
        return new Produktlinie(new Produkt(name, qualitaetsstufe), menge);
    }


    //Getter und Setter:
    public Vertrag getVertrag() {
        return vertrag;
    }
}
