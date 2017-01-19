package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Produkt;
import Unternehmung.Vertrag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abteilung, die für Vertrieb zuständig ist
 * die Map verkaufteProdukte enthält alle bereits verkauften Produkte (relevant für Umsatzberechnung!)
 * Created by lucadommes on 30.12.2016.
 */
public class Vertrieb extends Abteilung {
    public List<Vertrag> opportunities;
    public List<Vertrag> accounts;


    public Vertrieb(Kennzahlensammlung kennzahlensammlung) {

        super("Vertrieb",kennzahlensammlung);
        opportunities = new ArrayList<>();
        accounts = new ArrayList<>();
    }

   @Override
    public void update() {

    }

    // TODO implement this



}