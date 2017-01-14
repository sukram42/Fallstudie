package Unternehmung;

/**
 * Klasse die eine Produktlinie repräsentiert
 * Created by lucadommes on 11.01.2017.
 */
public class Produktlinie {

    private String id; // zur Identifikation der Produktlinie in der Map in der Klasse Produktion
                        // setzt sich zusammen aus Produktname und Qualitätsstufe
                        // bei Herabsetzung der Herstellkosten (durch Forschung) wird die id modifiziert
    private Produkt produkt; // zu produzierendes Produkt
    private int menge; // in der Produktion: zu produzierende Menge pro Periode | im Lager: vorhandene Produkte
    private int laufzeit; // Anzahl der Perioden in denen das Produkt produziert werden soll
    private double forschungsbonus; // kann zwischen 0 und 0,25 sein (-> bis zu 25% niedrigere Herstellkosten)

    /**
     * Konstruktor, der beim Erstellen eines neuen Produktionsauftrags verwendet wird
     * @param produkt zu produzierendes Produkt
     * @param menge Produktionsmenge pro Timer count
     * @param laufzeit des Auftrags in timer counts (wird dann bei jedem timer count runter gesetzt)
     */
    public Produktlinie(Produkt produkt, int menge, int laufzeit){
        this.produkt = produkt;
        this.menge = menge;
        this.laufzeit = laufzeit;
        this.id = produkt.getName() + produkt.getQualitätsstufe();
    }

    /**
     * Konstruktor, der beim Fertigstellen des Produktes verwendet wird -> zum "Abstellen" im Lager (keine Laufzeit mehr benötigt)
     * @param produkt fertiggestelltes Produkt
     * @param menge produzierte / im Lager vorhandene Menge
     */
    public Produktlinie(Produkt produkt, int menge) {
        this.id = id;
        this.produkt = produkt;
        this.menge = menge;
        this.id = produkt.getName() + produkt.getQualitätsstufe();
    }

    // Getter und Setter;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public int getLaufzeit() {
        return laufzeit;
    }

    public void setLaufzeit(int laufzeit) {
        this.laufzeit = laufzeit;
    }

    public double getForschungsbonus() {
        return forschungsbonus;
    }

    public void setForschungsbonus(double forschungsbonus) {
        this.forschungsbonus = forschungsbonus;
    }
}
