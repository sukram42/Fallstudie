package Unternehmung.Kennzahlen;

import Unternehmung.Unternehmen;

/**
 * Created by boebel on 17.01.2017.
 */
public class Produktqualität extends Kennzahl {

    public Produktqualität(Unternehmen unternehmen) {
        super(unternehmen);
    }

    @Override
    public float berechnen() {
//        setBasiswert();
        return super.berechnen();
    }
}
