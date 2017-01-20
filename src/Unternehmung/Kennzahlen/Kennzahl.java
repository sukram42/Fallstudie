package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;

/**
 * Klasse, die eine Kennzahl darstellt - hauptsächlich für "weiche" Kennzahlensammlung gedacht
 * für jede Kennzahl wird ein Objekt dieser Klasse erstellt
 * Created by lucadommes on 29.12.2016.
 */
public class Kennzahl {

    protected transient Unternehmen unternehmen;
    private float basiswert; // Grundwert zur Berechnung (z.B. Vergleich mit Industriedurchschnitt
    private float modifier; // Variable zum Erhöhen (z.B. durch Investition) oder Verringern (z.B. durch längere Zeit nichts tun) der Kennzahl
                            // bei (z.B. nichtmonetären) Kennzahlensammlung, die keinen modifier haben sollen diesen einfach auf 0 setzen
    private float wert; // finaler Wert der Kennzahl

    /**
     * Standardkonstruktor
     */
    public Kennzahl(Unternehmen unternehmen){
        System.out.println("UNTERNEHMEN : " + unternehmen  );
        this.unternehmen = unternehmen;
        this.basiswert = 0;
        this.modifier = 0;
    }

    /**
     * Konstruktor, der bereits alle Werte definiert
     * @param basiswert siehe oben
     * @param modifier siehe oben
     */
    public Kennzahl(float basiswert, float modifier){
        this.basiswert = basiswert;
        this.modifier = modifier;
        berechnen();
    }

    /**
     * Funktion zur Neuberechnung des finalen Wertes der Kennzahl
     * soll bei Bedarf von Subklassen überschrieben werden
     * @return finaler Wert der Kennzahl
     */
    public float berechnen(){

        if (basiswert + modifier >= 1) {
            wert = (float) 1;
        }
        wert  = (float)(basiswert + modifier);
        return getWert();
    }

    /**
     * setzt den Modifier um den Wert x hoch
     * @param x zu addierender Wert (z.B. impact einer Marketingkampagne)
     */
    public void addModifier(float x) {
        modifier += x;
    }



    // Getter und Setter:
    public float getBasiswert() {
        return basiswert;
    }

    public void setBasiswert(float basiswert) {
        this.basiswert = basiswert;
    }

    public float getModifier() {
        return modifier;
    }

    public float getWert() {
        return (float)(0.5*Math.tanh(4 * wert - 2) + 0.5);
    }

    public void setWert(float wert) {
        this.wert = wert;
    }

}
