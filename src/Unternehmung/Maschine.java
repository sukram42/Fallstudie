package Unternehmung;

/**
 * repräsentiert eine Maschine
 * Created by lucadommes on 07.01.2017.
 */
public class Maschine {

    private String klasse; // etwa 1, 2, 3 -> unterschiedliche Kapazität, Anschaffungskosten, Halbwertszeit
    private int kapazität; // kann maximal so viele Produkte pro Jahr produzieren
    private int anschaffungskst;
    private int anzahl; // Anzahl der Maschinen einer Klasse
    // TODO Halbwertszeit implementieren -> Maschine geht nach x Jahren kapputt
    // TODO Alter und Wiederverkaufswert der Maschine implementieren


    public Maschine(String klasse, int kapazität, int anschaffungskst, int anzahl) {
        this.klasse = klasse;
        this.kapazität = kapazität;
        this.anschaffungskst = anschaffungskst;
        this.anzahl = anzahl;
    }



    // Getter und Setter:
    public String getKlasse() {
        return klasse;
    }

    public int getKapazität() {
        return kapazität;
    }

    public int getAnschaffungskst() {
        return anschaffungskst;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}
