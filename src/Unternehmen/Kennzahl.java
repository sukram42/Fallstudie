package Unternehmen;

/**
 * Klasse, die eine Kennzahl darstellt
 * für jede Kennzahl wird ein Objekt dieser Klasse erstellt
 * Created by lucadommes on 29.12.2016.
 */
public class Kennzahl {

    private float basiswert; // Grundwert zur Berechnung (z.B. Vergleich mit Industriedurchschnitt
    private float modifier; // Variable zum Erhöhen (z.B. durch Investition) oder Verringern (z.B. durch längere Zeit nichts tun) der Kennzahl
    private float wert; // finaler Wert der Kennzahl

    /**
     * Standardkonstruktor
     */
    public Kennzahl(){
        // TODO Defaultwerte definieren ?! (z.B. 0?)
        this.setBasiswert((float) 0.01);
        this.setModifier(0);
        wertBerechnen(this.getBasiswert(), this.getModifier());
    }

    /**
     * Konstruktor, der bereits alle Werte definiert
     * @param basiswert siehe oben
     * @param modifier siehe oben
     */
    public Kennzahl(float basiswert, float modifier){
        this.basiswert = basiswert;
        this.modifier = modifier;
        wertBerechnen(basiswert, modifier);
    }

    /**
     * Funktion zur Neuberechnung des finalen Wertes der Kennzahl
     * soll bei Bedarf von Subklassen überschrieben werden
     * @param basiswert siehe oben
     * @param modifier siehe oben
     * @return finaler Wert der Kennzahl
     */
    public void wertBerechnen(float basiswert, float modifier){
        this.setBasiswert(basiswert);
        this.setModifier(modifier);
        if (basiswert * modifier >= 1) {
            this.setWert((float) 1);
        }
        this.setWert(basiswert * modifier);
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

    public void setModifier(float modifier) {
        this.modifier = modifier;
    }

    public float getWert() {
        return wert;
    }

    public void setWert(float wert) {
        this.wert = wert;
    }
}
