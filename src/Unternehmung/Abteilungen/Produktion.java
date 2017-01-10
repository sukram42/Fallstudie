package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Maschine;
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
    private Map<String, Maschine> maschinen = new HashMap<String, Maschine>(); // Maschinenpark

    /**
     * Konstruktor, zum Erstellen der Abteilung Produktion
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Herstellkosten laufend fortzuschreiben (siehe produzieren())
     */
    public Produktion(Kennzahlensammlung kennzahlensammlung) {
        super(kennzahlensammlung);
    }

    /**
     * Funktion zum Kaufen einer Maschine
     * prüft, ob es bereits Maschinen dieser Klasse gibt und setzt dann entsprechend die Anzahl hoch bzw. fügt die Maschine neu hinzu
     * die Anschaffungskosten werden direkt weitergegeben (siehe addSonstigeKosten())
     * @param klasse 1, 2 oder 3
     * @param anzahl Anzahl der zu kaufenden Maschinen
     */
    public void maschinenKaufen(String klasse, int anzahl) {
        // TODO Betriebskosten der Maschinen implementieren ?!
        int kapazität = 0;
        int anschaffungskst = 0;

        switch (klasse) {
            case "1":
                kapazität = 500;
                anschaffungskst = 10000;
                break;
            case "2":
                kapazität = 1500;
                anschaffungskst = 20000;
                break;
            case "3":
                kapazität = 3000;
                anschaffungskst = 30000;
                break;
        }

        if (this.kennzahlensammlung.liquiditätVorhanden(anschaffungskst * anzahl, "sonstige Kosten")) {
            Maschine bestand = maschinen.get(klasse);
            if (bestand != null) {
                bestand.setAnzahl(bestand.getAnzahl() + anzahl);
            } else {
                Maschine m = new Maschine(klasse, kapazität, anschaffungskst, anzahl);
                maschinen.put(m.getKlasse(), m);
            }
            System.out.println(anzahl + " Maschine(n) der Klasse " + klasse + " gekauft, Kapazität: " + kapazität +
                    " Stück pro Jahr, Anschaffungskosten: " + anschaffungskst + " €");
        } else {
            System.out.println("Nicht genügend Liquidität vorhanden!");
        }
    }


    // TODO noch nicht fertig implementiert
    public void maschineVerkaufen(String klasse, int anzahl){
        // TODO Wiederverkaufswert implementieren -> sonstigenUmsatz verbuchen!
        Maschine m = maschinen.get(klasse);
        if (m.getAnzahl() > anzahl) {
            m.setAnzahl(m.getAnzahl() - anzahl);
        } else if(m.getAnzahl() == anzahl){
            maschinen.remove(klasse);
        } else if (m.getAnzahl() <= anzahl){
            System.out.println("ERROR: Es können nicht mehr Maschinen verkauft werden als vorhanden!");
        }
    }

    /**
     * Funktion zum Produzieren von Produkten
     * @param name Bezeichnung des zu produzierenden Produkts (z.B. Rucksack)
     * @param anzahl Anzahl der zu produzierenden Produkte
     * @param herstellkosten des Produktes pro Stück // TODO feste Vorgabe je nach Produkt oder Auswahl durch den Spieler?
     */
    public void produzieren(String name, int anzahl, int herstellkosten){
        // TODO anzahl = Kapazitätsgrenze ?!
        if(this.getMaschinenKapazität() >= anzahl) { // Prüfen, ob genügend Maschinenkapazität vorhanden ist
            if (this.kennzahlensammlung.liquiditätVorhanden(herstellkosten * anzahl, "herstellkosten")) {
                Produkt produkt = new Produkt(name, anzahl, herstellkosten);
                produzierteProdukte.put(produkt.getName(), produkt);
                System.out.println(anzahl + " Produkte (" + name + ") produziert.");
            } else {
                System.out.println("Nicht genügend Liquidität vorhanden!");
            }
        } else {
            System.out.println("Nicht genügend Maschinenkapazität vorhanden!");
        }
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

    public Map<String, Maschine> getMaschinen() {
        return maschinen;
    }

    /**
     * Methode zum Ermitteln der gesamten Kapazität aller im Maschinenpark vorhandener Maschinen
     * @return gesamte Kapazität aller Maschinen
     */
    public int getMaschinenKapazität(){
        int kapazität = 0;
        if(maschinen.get("1") != null){
            kapazität += maschinen.get("1").getKapazität() * maschinen.get("1").getAnzahl();
        }
        if(maschinen.get("2") != null){
            kapazität += maschinen.get("2").getKapazität() * maschinen.get("2").getAnzahl();
        }
        if(maschinen.get("3") != null){
            kapazität += maschinen.get("3").getKapazität() * maschinen.get("3").getAnzahl();
        }
        return kapazität;
    }
}
