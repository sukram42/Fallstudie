package Unternehmung;

/**
 * Klasse, die ein Produkt repräsentiert
 * Created by lucadommes on 03.01.2017.
 */
public class Produkt {

    private String name; // Produktbezeichnung (z.B. Rucksack)
    private char qualitätsstufe; // A-, B- oder C-Produkt
    private double herstellkosten; // Herstellkosten eines Produktes
    //private double preis; // Verkaufspreis

    /**
     * Konstruktor zum Erstellen eines noch nicht produzierten Produktes (also einer noch nicht vorhandenen Kombination von name und qualitätsstufe)
     * herstellkosten werden generiert
     * @param name Produktname (z.B. Rucksack?!)
     * @param qualitätsstufe A, B oder C
     */
    public Produkt(String name, char qualitätsstufe) {
        this.name = name;
        this.qualitätsstufe = qualitätsstufe;
        this.herstellkosten = findHerstellkosten(name, qualitätsstufe);
    }

    /**
     * Methode zum Bestimmen der Herstellkosten basierend auf
     * @param name dem Produkt (z.B. Rucksack) und
     * @param qualitätsstufe der Qualitätsstufe (z.B. A-Produkt)
     * @return Herstellkosten des Produktes
     */
    private double findHerstellkosten(String name, char qualitätsstufe){
        switch (name) {
            // TODO realistische / zum Spiel passende Zahlen einsetzen
            case "Rucksack":
                switch (qualitätsstufe){
                    case 'A': return 20;
                    case 'B': return 15;
                    case 'C': return 10;
                }
            case "Rucksack tech":
                switch (qualitätsstufe){
                    case 'A': return 330;
                    case 'B': return 20;
                    case 'C': return 15;
                }
            case "Duffel":
                switch (qualitätsstufe){
                    case 'A': return 25;
                    case 'B': return 20;
                    case 'C': return 15;
                }
            case "Reisetasche":
                switch (qualitätsstufe){
                    case 'A': return 35;
                    case 'B': return 30;
                    case 'C': return 20;
                }
        }
        return -1;
    }

    // Getter und Setter:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getQualitätsstufe() {
        return qualitätsstufe;
    }

    public void setQualitätsstufe(char qualitätsstufe) {
        this.qualitätsstufe = qualitätsstufe;
    }

    public double getHerstellkosten() {
        return herstellkosten;
    }

    public void setHerstellkosten(double herstellkosten) {
        this.herstellkosten = herstellkosten;
    }
}
