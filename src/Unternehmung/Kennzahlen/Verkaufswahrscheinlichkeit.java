package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;

/**
 * Wahrscheinlichkeit, Deals für sich zu gewinnen
 * Created by lucadommes on 20.01.2017.
 */
public class Verkaufswahrscheinlichkeit extends Kennzahl{

    public Verkaufswahrscheinlichkeit(Unternehmen unternehmen){
        super(unternehmen);
        this.setBasiswert(unternehmen.getKennzahlensammlung().getWeicheKennzahl("image").getWert());
        // TODO genaue Berechnung / Zusammensetzung dieser Kennzahl
    }

    @Override
    public float berechnen(){
        this.setBasiswert(unternehmen.getKennzahlensammlung().getWeicheKennzahl("image").getWert());
        super.berechnen();
        return super.getWert();
    }

}
