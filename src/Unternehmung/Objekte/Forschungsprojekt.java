package Unternehmung.Objekte;

import Rules.Game;
import Unternehmung.Abteilungen.Forschung;
import Unternehmung.Kennzahlensammlung;

import java.util.Calendar;

/**
 * Created by D064018 on 11.01.2017.
 */
public class Forschungsprojekt {

    private transient Forschung forschung;
    private transient Kennzahlensammlung kennzahlensammlung;

    private static float abbruchFaktor = 0.7f;
    private static float herstellungskostenFaktor = 0.0025f;
    private static float imageFaktor = 0.001245f;
    //bei einer Mitarbeiterzufriedenheit von 0.5 (Faktor 5) soll ein Mitarbeiter 6 Monate für jeden Bonus beschäftigt sein
    private int mitarbeiterAnzahl;
    private String forschungsobjekt;
    private Calendar ende;
    private long beginn;
    private boolean herstellkosten; //Indikator, ob Kostenreduzierung oder Image gewählt wurde
    //Es kann immer nur an max. einer Eigenschaft geforscht werden, da sich die Entwicklungen ansonsten in die Quere kommen und 2 unterschiedliche
    // Produkte herauskommen würden.


    //Effektivität über Mitarbeiterzufriedenheit

    public Forschungsprojekt(Kennzahlensammlung kennzahlensammlung,Forschung forschung, String forschungsobjekt, int mitarbeiterAnzahl, int laufzeit, boolean herstellkosten){
    beginn = Game.getTime();
    this.kennzahlensammlung = kennzahlensammlung;
    this.mitarbeiterAnzahl = mitarbeiterAnzahl;
    this.forschungsobjekt = forschungsobjekt;
    this.ende = (Calendar)Game.getCalendar().clone();
    this.ende.add(Calendar.DAY_OF_MONTH, laufzeit);
    this.herstellkosten = herstellkosten;
    this.forschung = forschung;
    }

    public void abbrechen() {
        float bonus;
        if (herstellkosten) {
                bonus  = abbruchFaktor * (Game.getTime() - beginn) * 10 * kennzahlensammlung.getMitarbeiterzufriedenheit().getWert() * mitarbeiterAnzahl * herstellungskostenFaktor;
            forschung.setForschungsbonus(forschungsobjekt, bonus);
        } else {
            bonus = abbruchFaktor * (Game.getTime() - beginn) * mitarbeiterAnzahl  * 10 * kennzahlensammlung.getMitarbeiterzufriedenheit().getWert() * imageFaktor;
            forschung.setImagebonus(forschungsobjekt, bonus);
        }
    }

    public void abschließen(){
        float bonus;
        if(herstellkosten){
            bonus  = (Game.getTime() - beginn)
                    * 10
                    * kennzahlensammlung.getMitarbeiterzufriedenheit().getWert()
                    * mitarbeiterAnzahl
                    * herstellungskostenFaktor;
            forschung.setForschungsbonus(forschungsobjekt, bonus);
        }else{
            bonus = (Game.getTime() - beginn) * mitarbeiterAnzahl  * 10 * kennzahlensammlung.getMitarbeiterzufriedenheit().getWert() * imageFaktor;
            forschung.setImagebonus(forschungsobjekt, bonus);
        }
    }

    public Calendar getEnde() {
        return ende;
    }

    public String getForschungsobjekt() {
        return forschungsobjekt;
    }

    public int getMitarbeiterAnzahl(){
        return mitarbeiterAnzahl;
    }

    public void feuereMitarbeiter(){
        this.mitarbeiterAnzahl -= 1;

    }
}