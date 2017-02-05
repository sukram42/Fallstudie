package Unternehmung;

import Rules.Game;
import Unternehmung.Abteilungen.Forschung;

import java.util.Calendar;

/**
 * Created by D064018 on 11.01.2017.
 */
public class Forschungsprojekt {

    private static float abbruchFaktor = 0.7f;

    private int mitarbeiterAnzahl;
    private String forschungsobjekt;
    private Calendar ende;
    private long beginn;
    private boolean herstellkosten; //Indikator, ob Kostenreduzierung oder Image gewählt wurde
    //Es kann immer nur an max. einer Eigenschaft geforscht werden, da sich die Entwicklungen ansonsten in die Quere kommen und 2 unterschiedliche
    // Produkte herauskommen würden.
    private Forschung forschung;
    Kennzahlensammlung kennzahlensammlung;

    //Effektivität über Mitarbeiterzufriedenheit
    //Ansonsten soll das Gehalt der Mitarbeiter immer x sein

    public Forschungsprojekt(Kennzahlensammlung kennzahlensammlung, Forschung forschung, String forschungsobjekt, int mitarbeiterAnzahl, int laufzeit, boolean herstellkosten){
    beginn = Game.getTime();
    this.kennzahlensammlung = kennzahlensammlung;
    this.mitarbeiterAnzahl = mitarbeiterAnzahl;
    this.forschungsobjekt = forschungsobjekt;
    this.ende = Game.getCalendar();
    this.ende.add(Calendar.DAY_OF_MONTH, laufzeit);
    this.herstellkosten = herstellkosten;
    this.forschung = forschung;
    }

    public void abbrechen() {
        float bonus = 0;
        if (herstellkosten) {
            //    bonus  = abbruchFaktor * (Game.getTime() - beginn) * mitarbeiterAnzahl * irgendeinFaktor;
            forschung.setForschungsbonus(forschungsobjekt, bonus);
        } else {
            //bonus = abbruchFaktor * (Game.getTime() - beginn) * mitarbeiterAnzahl  * 1.5 * kennzahlensammlung.getMitarbeiterzufriedenheit() * irgendeinFaktor;
            forschung.setImagebonus(forschungsobjekt, bonus);
        }
    }

    public void abschließen(){
        float bonus = 0;
        if(herstellkosten){
            //    bonus  = (Game.getTime() - beginn) * mitarbeiterAnzahl * irgendeinFaktor;
            forschung.setForschungsbonus(forschungsobjekt, bonus);
        }else{
            //bonus = (Game.getTime() - beginn) * mitarbeiterAnzahl  * 1.5 * kennzahlensammlung.getMitarbeiterzufriedenheit() * irgendeinFaktor;
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
        this.mitarbeiterAnzahl =- 1;
    }
}