package Unternehmung.Abteilungen.SozialeProjekte;

import Unternehmung.Abteilungen.HR;
import Unternehmung.Unternehmen;

/**
 * Created by boebel on 14.01.2017.
 */
public class SozialProjekt {
    protected String name;
    protected float impact;
    protected boolean active = false;
    protected float einmaligKosten;
    protected float laufendeKosten;

    protected transient Unternehmen unternehmen;


    //Kosten welche diesem Tag abgehen
    protected float aktuelleKosten;

    public SozialProjekt(String name, float einmaligkosten,float laufendeKosten, float impact, Unternehmen unternehmen)
    {
        this.name = name;
        this.impact = impact;
        this.einmaligKosten = einmaligkosten;
        this.laufendeKosten = laufendeKosten;
        this.unternehmen = unternehmen;

    }

    /**
     * Projekt wird aktiviert:
     * Einmaligen Kosten werden in aktuelle Kosten gelegt.
     * Das Projekt wird auf Aktiv gestellt.
     */
    public void start()
    {
        active = true;
        aktuelleKosten = einmaligKosten;
        //Impact in die Mitarbeiterzufriedenheit schreiben
        unternehmen.getKennzahlensammlung().getMitarbeiterzufriedenheit().addModifier(impact);
    }

    /**
     * Stoppt ein soziales Projekt
     */
    public void stop()
    {
        active = false;
        unternehmen.getKennzahlensammlung().getMitarbeiterzufriedenheit().addModifier(impact*(-1f));
    }

    /**
     * Es werden den aktellenKosten die laufendenKosten addiert.
     */
    public void update()
    {
        if(active)
        {
            aktuelleKosten += laufendeKosten;
        }
    }

    /**
     * Die Aktuellen Kosten werden auf 0 gesetzt
     */
    public void resetAktKosten()
    {
        aktuelleKosten = 0;
    }

    /**
     *
     * @return Gibt den Einfluss auf die Mitarbeiterzufriedenheit wieder
     */
    public float getImpact()
    {
        return impact;
    }

    /**
     * Gibt die aktuellen Kosten zurück
     * @return
     */
    public float getAktKosten()
    {
        return aktuelleKosten;
    }

    public boolean isActive()
    {
        return active;
    }

    public String getName()
    {
        return name;
    }
}
