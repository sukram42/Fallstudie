package Unternehmung;

import Rules.Game;

/**
 * Created by D064018 on 11.01.2017.
 */
public class Forschungsprojekt {

    /*
    Je weiter das Forschungsprojekt forangeschritten ist, desto effektiver können die Mitarbeiter darin arbeiten.
    Deshalb ist der Malus, verglichen zur übrigen Zeit, höher, wenn man gegen Ende abbricht, als wenn man es am Anfang macht.
     */


    public final double abbruchFaktor = 0.7;

    private int mitarbeiterAnzahl;
        private Produkt forschungsobjekt;
    private long dauer;
    private long beginn;
    private boolean herstellkosten; //Indikator, ob Kostenreduzierung oder Image gewählt wurde
    //Es kann immer nur an max. einer Eigenschaft geforscht werden, da sich die Entwicklungen ansonsten in die Quere kommen und 2 unterschiedliche
    // Produkte herauskommen würden.

    //Effektivität über Mitarbeiterzufriedenheit

    public Forschungsprojekt(Produkt forschungsobjekt, int mitarbeiterAnzahl, long dauer, boolean herstellkosten){
    beginn = Game.getTime();
    this.mitarbeiterAnzahl = mitarbeiterAnzahl;
    this.forschungsobjekt = forschungsobjekt;
    this.dauer = dauer;
    }

    //  this.kennzahlensammlung.setBekanntheitsgrad(this.kennzahlensammlung.getBekanntheitsgrad() + impact);


    public void abbrechen(){
        //time
        if(herstellkosten){
            double produktKosten = forschungsobjekt.getHerstellkosten();
          //  produktKosten = produktKosten * abbruchFaktor * (Game.getTime() - beginn) * mitarbeiterAnzahl * irgendeinFaktor;
          //  this.kennzahlensammlung.
            forschungsobjekt.setHerstellkosten(produktKosten);
        }else{
            //Kundenzufriedenheit erhöhen

            //abbruchFaktor * (Game.getTime() - beginn) * mitarbeiterAnzahl * irgendeinFaktor;
        }
    }

    public void abschließen(){
        if(herstellkosten){
            double produktKosten = forschungsobjekt.getHerstellkosten();
            //  produktKosten = produktKosten * (Game.getTime() - beginn) * mitarbeiterAnzahl * irgendeinFaktor;
            forschungsobjekt.setHerstellkosten(produktKosten);
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

    public Produkt getForschungsobjekt() {
        return forschungsobjekt;
    }

    public int getMitarbeiterAnzahl(){
        return mitarbeiterAnzahl;
    }
}