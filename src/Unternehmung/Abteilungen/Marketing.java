package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahl;
import Unternehmung.Unternehmen;

/**
 * Klasse, die die Abteilung Marketing repräsentiert
 * Created by lucadommes on 29.12.2016.
 */
public class Marketing extends Abteilung {

    public static void marketingKampagne(Unternehmen unternehmen, int dauer, int kosten){
        // Bekanntheitsgrad erhöhen:
        //Kennzahl bekanntheitsgrad = unternehmen.getBekanntheitsgrad();
        //bekanntheitsgrad.wertBerechnen(bekanntheitsgrad.getBasiswert(), bekanntheitsgrad.getModifier() + (float) 0.5);
        // TODO EK verringern (um kosten)
        // TODO Verkaufszahlen steigen
    }

    public static void marktforschung(int kosten){
        // TODO EK verringern (um kosten)
        // TODO Verkaufszahlen steigen
    }

}
