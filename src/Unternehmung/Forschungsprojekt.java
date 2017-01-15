package Unternehmung;

import Rules.Game;

/**
 * Created by D064018 on 11.01.2017.
 */
public class Forschungsprojekt {

    public final double abbruchFaktor = 0.7;

    private int mitarbeiterAnzahl;
    private Produktlinie forschungsobjekt;
    private long dauer;
    private long beginn;
    private boolean herstellkosten; //Indikator, ob Kostenreduzierung oder Image gewählt wurde
    //Es kann immer nur an max. einer Eigenschaft geforscht werden, da sich die Entwicklungen ansonsten in die Quere kommen und 2 unterschiedliche
    // Produkte herauskommen würden.

    //Effektivität über Mitarbeiterzufriedenheit

    public Forschungsprojekt(Produktlinie forschungsobjekt, int mitarbeiterAnzahl, long dauer, boolean herstellkosten){
    beginn = Game.getTime();
    this.mitarbeiterAnzahl = mitarbeiterAnzahl;
    this.forschungsobjekt = forschungsobjekt;
    this.dauer = dauer;
    }

    //  this.kennzahlensammlung.setBekanntheitsgrad(this.kennzahlensammlung.getBekanntheitsgrad() + impact);

    public void abbrechen(){
        if(herstellkosten){
            double bonus = forschungsobjekt.getForschungsbonus();
          //  forschungsbonus = abbruchFaktor * (Game.getTime() - beginn) * mitarbeiterAnzahl * mitarbeiterZufriedenheit * irgendeinFaktor;
         //  this.kennzahlensammlung.
            forschungsobjekt.setForschungsbonus(bonus);

        }else{
            //Kundenzufriedenheit erhöhen

            //abbruchFaktor * (Game.getTime() - beginn) * mitarbeiterAnzahl * irgendeinFaktor;
        }
    }

    public void abschließen(){
        if(herstellkosten){
            double bonus = forschungsobjekt.getForschungsbonus();
            //  produktKosten = produktKosten * (Game.getTime() - beginn) * mitarbeiterAnzahl * irgendeinFaktor;
            // forschungsobjekt.setHerstellkosten(produktKosten);
            forschungsobjekt.setForschungsbonus(bonus);
        }else{
        //Kundenzufriedenheit erhöhen

           // (Game.getTime() - beginn) * mitarbeiterAnzahl * irgendeinFaktor;
        }
    }

    public long getDauer() {
        return dauer;
    }

    public long getBeginn() {
        return beginn;
    }

    public Produktlinie getForschungsobjekt() {
        return forschungsobjekt;
    }

    public int getMitarbeiterAnzahl(){
        return mitarbeiterAnzahl;
    }
}