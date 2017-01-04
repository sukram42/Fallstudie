package Unternehmung;

/**
 * Klasse, die ein Produkt repräsentiert
 * Created by lucadommes on 03.01.2017.
 */
public class Produkt {

    private String name; // Produktbezeichnung (z.B. Rucksack)
    private int anzahl; // wie oft dieses Produkt im Lager vorhanden ist
    private double herstellkosten; // Herstellkosten eines Produktes
    private double preis; // Verkaufspreis

    /**
     * Konstruktor, der beim Produzieren verwendet wird (noch kein Preis benötigt / vorhanden)
     * @param name Produktname (z.B. Rucksack?!)
     * @param anzahl der herzustellenden Stücke
     * @param herstellkosten eines Produktes
     */
    public Produkt(String name, int anzahl, double herstellkosten) {
        this.name = name;
        this.anzahl = anzahl;
        this.herstellkosten = herstellkosten;
    }

    /**
     * Konstruktor, der beim Verkaufen eines Produktes verwendet wird
     * @param name Produktname (z.B. Rucksack?!)
     * @param anzahl der herzustellenden Stücke
     * @param herstellkosten eines Produktes
     * @param preis Verkaufspreis
     */
    public Produkt(String name, int anzahl, double herstellkosten, double preis) {
        this.name = name;
        this.anzahl = anzahl;
        this.herstellkosten = herstellkosten;
        this.preis = preis;
    }

    // Getter und Setter:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public double getHerstellkosten() {
        return herstellkosten;
    }

    public void setHerstellkosten(double herstellkosten) {
        this.herstellkosten = herstellkosten;
    }
}
