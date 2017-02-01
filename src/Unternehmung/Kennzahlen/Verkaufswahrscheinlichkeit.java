package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;

import java.util.Map;

/**
 * Wahrscheinlichkeit, Deals für sich zu gewinnen
 * setzt sich zusammen aus Image und Bekanntheitsgrad
 * Created by lucadommes on 20.01.2017.
 */
public class Verkaufswahrscheinlichkeit extends Kennzahl{

    public Verkaufswahrscheinlichkeit(Unternehmen unternehmen){
        super(unternehmen);
        this.berechnen();
    }

    @Override
    public float berechnen(){
        Map<String, Kennzahl> weicheKennzahlen = unternehmen.getKennzahlensammlung().getWeicheKennzahlen();
        this.setBasiswert(weicheKennzahlen.get("image").getWert() * 0.5f +
                this.unternehmen.getKennzahlensammlung().getWeicheKennzahl("bekanntheitsgrad").getWert() * 0.5f);
        return super.berechnen();
    }

}
