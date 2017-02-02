package Unternehmung;

import Rules.Game;

import java.util.Calendar;

/**
 * Klasse die eine Produktlinie repräsentiert
 * Created by lucadommes on 11.01.2017.
 */
public class Produktlinie {

    private String id; // zur Identifikation der Produktlinie in der Map in der Klasse Produktion
                        // setzt sich zusammen aus Produktname und Qualitätsstufe
    private Produkt produkt; // zu produzierendes Produkt
    private int menge; // in der Produktion: zu produzierende Menge pro Tag (angegeben vom User pro Monat) | im Lager: vorhandene Produkte
    private int laufzeit; // Anzahl der Perioden in denen das Produkt produziert werden soll
    private Calendar beginn;
    private Calendar end;

    /**
     * Konstruktor, der beim Erstellen eines neuen Produktionsauftrags verwendet wird
     * @param produkt zu produzierendes Produkt
     * @param menge Produktionsmenge pro Monat
     * @param laufzeit des Auftrags in timer counts (wird dann bei jedem timer count runter gesetzt)
     */
    public Produktlinie(Produkt produkt, int menge, int laufzeit){
        this.produkt = produkt;
        this.menge = menge / Game.getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
        this.laufzeit = laufzeit;
        this.beginn = (Calendar) Game.getCalendar().clone();
        this.end = (Calendar) this.beginn.clone();
        this.end.add(Calendar.DAY_OF_MONTH, laufzeit);
        this.id = produkt.getName() + produkt.getQualitaetsstufe();
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
        this.id = produkt.getName() + produkt.getQualitaetsstufe();
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

    public Calendar getBeginn() {
        return beginn;
    }

    public Calendar getEnd() {
        return end;
    }
}
