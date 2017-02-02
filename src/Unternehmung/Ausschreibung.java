package Unternehmung;

import Rules.Game;

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
    private static final transient String[] kunden = {"SchickerRucksackAG","Outdoorjoh.com","BergNeelSen","BergeUndSo",
            "BergDoktor.com","Berglises Hausladen","My Litty Moutain","AdventureTime","LaBoutiqueDeLaSporte",
            "EA Sports", "Bags & more", "Climbers Equipment Inc.", "Wack Jolfskin", "Gear4Fun", "Travelbuddy GmbH", "TielschSports"};
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
        String kunde = kunden[random.nextInt(kunden.length)];
        int laufzeit = random.nextInt(59) + 1; // generiert zufällige Laufzeit zwischen 1 (= einmaliger Auftrag) und 60 Monaten (= 5 Jahre)
        return new Vertrag(this.generateRandomProduktlinie(), kunde, laufzeit);
    }

    /**
     * @return Produktlinie mit zufällig generierten Werten
     */
    private Produktlinie generateRandomProduktlinie(){
        Random random = new Random();
        String name = produkte[random.nextInt(4)];
        char qualitaetsstufe = qualitaetsstufen[random.nextInt(3)];
        // durchschnittliche Produktionsmenge als Maßstab für Interval, in dem die Ausschreibungen generiert werden:
        int durchschnittlicheProduktionsmenge = 0;
        int menge;
        if (Game.getCompanies().size() != 0) {
            durchschnittlicheProduktionsmenge = Game.getGesamtabsatz() / Game.getCompanies().size();
        }
        if (durchschnittlicheProduktionsmenge <= 400) {
            menge = random.nextInt(150) + 50; // Anfangsintervall = [50; 200]
        } else {
            // Intervall = [200; x]:
            int intervallBeginn = Math.round(durchschnittlicheProduktionsmenge - durchschnittlicheProduktionsmenge * 0.5f);
            int intervallEnde = Math.round(durchschnittlicheProduktionsmenge + durchschnittlicheProduktionsmenge * 0.5f);
            menge = random.nextInt(intervallEnde) + intervallBeginn;
        }
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
