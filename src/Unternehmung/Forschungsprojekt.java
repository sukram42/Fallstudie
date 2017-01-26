package Unternehmung;

import Rules.Game;
import Unternehmung.Abteilungen.Forschung;
import Unternehmung.Abteilungen.Produktion;

import java.util.Calendar;

/**
 * Created by D064018 on 11.01.2017.
 */
public class Forschungsprojekt {

    public final double abbruchFaktor = 0.7;

    private int mitarbeiterAnzahl;
    private String forschungsobjekt;
    private Calendar ende;
    private Calendar beginn;
    private boolean herstellkosten; //Indikator, ob Kostenreduzierung oder Image gewählt wurde
    //Es kann immer nur an max. einer Eigenschaft geforscht werden, da sich die Entwicklungen ansonsten in die Quere kommen und 2 unterschiedliche
    // Produkte herauskommen würden.
    private Produktion produktion;
    private Forschung forschung;

    //Effektivität über Mitarbeiterzufriedenheit

    public Forschungsprojekt(Produktion produktion, Forschung forschung, String forschungsobjekt, int mitarbeiterAnzahl, int laufzeit, boolean herstellkosten){
    beginn = Game.getCalendar();
    this.mitarbeiterAnzahl = mitarbeiterAnzahl;
    this.forschungsobjekt = forschungsobjekt;
    this.ende = this.beginn;
    this.ende.add(Calendar.DAY_OF_MONTH, laufzeit);
    this.herstellkosten = herstellkosten;
    this.produktion = produktion;
    this.forschung = forschung;
    }

    public void abbrechen(){
        if(herstellkosten){
            double bonus = produktion.getForschungsbonusById(forschungsobjekt);
            bonus += 1;
          //  bonus = abbruchFaktor * (Game.getTime() - beginn) * mitarbeiterAnzahl * mitarbeiterZufriedenheit * irgendeinFaktor;
            produktion.setForschungsbonus(forschungsobjekt, bonus);

        }else{
            double bonus = forschung.getImagebonusById(forschungsobjekt);
            bonus += 1;
            // abbruchFaktor * (Game.getTime() - beginn) * mitarbeiterAnzahl  * mitarbeiterZufriedenheit * irgendeinFaktor;
            forschung.setImagebonus(forschungsobjekt, bonus);
        }
    }

    public void abschließen(){
        if(herstellkosten){
            double bonus = produktion.getForschungsbonusById(forschungsobjekt);
            bonus += 2;
            //  bonus += (Game.getTime() - beginn) * mitarbeiterAnzahl * mitarbeiterZufriedenheit * irgendeinFaktor;
            produktion.setForschungsbonus(forschungsobjekt, bonus);
        }else{
            //Kundenzufriedenheit erhöhen
            double bonus = forschung.getImagebonusById(forschungsobjekt);
            bonus += 2;
            // (Game.getTime() - beginn) * mitarbeiterAnzahl * mitarbeiterZufriedenheit * irgendeinFaktor;
            forschung.setImagebonus(forschungsobjekt, bonus);
        }
    }

    public Calendar getBeginn() {
        return beginn;
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
}