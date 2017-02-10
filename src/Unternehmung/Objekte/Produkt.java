package Unternehmung.Objekte;

/**
 * Klasse, die ein Produkt repräsentiert
 * Created by lucadommes on 03.01.2017.
 */
public class Produkt {

    private String name; // Produktbezeichnung ("Rucksack", "Rucksacktech", "Duffel" oder "Reisetasche")
    private char qualitaetsstufe; // A-, B- oder C-Produkt
    private double herstellkosten; // Herstellkosten eines Produktes
    private double forschungsbonus; // kann zwischen 0 und 0,25 sein (-> bis zu 25% niedrigere Herstellkosten)

    /**
     * Konstruktor zum Erstellen eines Produktes (Herstellkosten werden generiert) in der Produktion
     * @param name Produktname: "Rucksack", "Rucksacktech", "Duffel" oder "Reisetasche"
     * @param qualitaetsstufe A, B oder C
     * @param forschungsbonus wird in Produktion in der Liste nachgesehen
     */
    public Produkt(String name, char qualitaetsstufe, double forschungsbonus) {
        this.name = name;
        this.qualitaetsstufe = qualitaetsstufe;
        this.forschungsbonus = forschungsbonus;
        this.herstellkosten = findHerstellkosten(name, qualitaetsstufe) * forschungsbonus;
    }

    /**
     * Konstrukor zum Erstellen eines Produktes in Ausschreibungen
     * @param name Produktname: "Rucksack", "Rucksacktech", "Duffel" oder "Reisetasche"
     * @param qualitaetsstufe A, B oder C
     */
    public Produkt(String name, char qualitaetsstufe) {
        this.name = name;
        this.qualitaetsstufe = qualitaetsstufe;
        this.herstellkosten = this.findHerstellkosten(name, qualitaetsstufe);
    }

    /**
     * Methode zum Bestimmen der Herstellkosten basierend auf
     * @param name dem Produkt (z.B. Rucksack) und
     * @param qualitaetsstufe der Qualitätsstufe (z.B. A-Produkt)
     * @return Herstellkosten des Produktes
     */

    private double findHerstellkosten(String name, char qualitaetsstufe){
        switch (name) {
            case "Rucksack":
                switch (qualitaetsstufe){
                    case 'A': return 70;
                    case 'B': return 20;
                    case 'C': return 5;
                }
            case "Rucksacktech":
                switch (qualitaetsstufe){
                    case 'A': return 200;
                    case 'B': return 85;
                    case 'C': return 25;
                }
            case "Duffel":
                switch (qualitaetsstufe){
                    case 'A': return 100;
                    case 'B': return 30;
                    case 'C': return 15;
                }
            case "Reisetasche":
                switch (qualitaetsstufe){
                    case 'A': return 100;
                    case 'B': return 45;
                    case 'C': return 30;
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

    public char getQualitaetsstufe() {
        return qualitaetsstufe;
    }

    public void setQualitaetsstufe(char qualitaetsstufe) {
        this.qualitaetsstufe = qualitaetsstufe;
    }

    public double getHerstellkosten() {
        return herstellkosten;
    }

    public void setHerstellkosten(double herstellkosten) {
        this.herstellkosten = herstellkosten;
    }

    public double getForschungsbonus() {
        return forschungsbonus;
    }

    public void setForschungsbonus(double forschungsbonus) {
        this.forschungsbonus = forschungsbonus;
    }
}
