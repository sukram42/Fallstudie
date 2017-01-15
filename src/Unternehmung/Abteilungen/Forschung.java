package Unternehmung.Abteilungen;

import Rules.Game;
import Unternehmung.*;

import java.util.ArrayList;

/**
 * Created by D064018 on 11.01.2017.
 */

/* ich muss von hier an die Produkreihen gelangen
   max
 */

public class Forschung extends Abteilung{

    private ArrayList<Produktlinie> verfügbareProdukte = new ArrayList<>(); //um bereits beforschte Produkte reduzieren
    private ArrayList<Produktlinie> beforschteProdukte = new ArrayList<>();
    private int verfügbareMitarbeiter;
    private ArrayList<Forschungsprojekt> projekte = new ArrayList<>();

    public Forschung(Kennzahlensammlung kennzahlensammlung) {
          super("Forschung",kennzahlensammlung);
    }

    public void starteProjekt(Produktlinie forschungsobjekt, int mitarbeiterAnzahl, long dauer, boolean herstellkosten, Kennzahlensammlung kennzahlensammlung){
        if((verfügbareMitarbeiter -= mitarbeiterAnzahl) >= 0) { //Überprüfung, ob es genügend Mitarbeiter gibt
            verfügbareMitarbeiter -= mitarbeiterAnzahl;
            Forschungsprojekt forschungsprojekt = new Forschungsprojekt(forschungsobjekt, mitarbeiterAnzahl, dauer, herstellkosten);
            beforschteProdukte.add(forschungsobjekt);
            projekte.add(forschungsprojekt);
        }else{
            //Werfe Fehler: Nicht genügend Mitarbeiter vorhanden
        }
            }

public ArrayList<Forschungsprojekt> getProjekte(){
        return projekte;
}

    public void forschungsprojektAbbrechen(Forschungsprojekt forschungsprojekt){
        if(projekte.contains(forschungsprojekt)) {
            forschungsprojekt.abbrechen();
            beforschteProdukte.remove(forschungsprojekt.getForschungsobjekt());
            verfügbareMitarbeiter += forschungsprojekt.getMitarbeiterAnzahl();
            projekte.remove(forschungsprojekt);
        }
    }

    public void forschungsprojektAbschließen(Forschungsprojekt forschungsprojekt){
        if(projekte.contains(forschungsprojekt)) {
            forschungsprojekt.abschließen();
            beforschteProdukte.remove(forschungsprojekt.getForschungsobjekt());
            verfügbareMitarbeiter += forschungsprojekt.getMitarbeiterAnzahl();
            projekte.remove(forschungsprojekt);
        }
                }

public ArrayList<Produktlinie> getVerfügbareProdukte() {

        //Produkte, an denen bereits geforscht wird, aussondern
       // verfügbareProdukte = Produktreihe.getProduktreihen().getProdukt();
        for(Produktlinie produkt : beforschteProdukte){
        verfügbareProdukte.remove(produkt);
        }
        return verfügbareProdukte;
}


public void update() {
    for (Forschungsprojekt projekt : projekte) {
        if ((projekt.getBeginn() + projekt.getDauer()) <= Game.getTime()) { //Wurde das Projekt abgeschlossen?
            projekt.abschließen();
        }
    }

}
}
