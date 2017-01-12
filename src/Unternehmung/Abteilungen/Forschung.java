package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Forschungsprojekt;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Produkt;

import java.util.ArrayList;

/**
 * Created by D064018 on 11.01.2017.
 */

/* Methode für Markus´s Timer
for(Forschungsprojekt projekt : Projekte){
            if((projekt.getBeginn() + projekt.getDauer()) == Game.getTime()){
                projekt.abschließen();
            }

   ich muss von hier an die Produkreihen gelangen
 */

public class Forschung extends Abteilung{

    private ArrayList<Produkt> verfügbareProdukte = new ArrayList<>(); //um bereits beforschte Produkte reduzieren
    private ArrayList<Produkt> beforschteProdukte = new ArrayList<>();
    private int verfügbareMitarbeiter;
    private ArrayList<Forschungsprojekt> Projekte = new ArrayList<>();

    public Forschung(Kennzahlensammlung kennzahlensammlung) {
          super(kennzahlensammlung);
    }

    public void starteProjekt(Produkt forschungsobjekt, int mitarbeiterAnzahl, long dauer, boolean herstellkosten, Kennzahlensammlung kennzahlensammlung){
        verfügbareMitarbeiter -= mitarbeiterAnzahl; //Überprüfung, sodass verfügbareMitarbeiter nicht negativ wird
        Forschungsprojekt forschungsprojekt = new Forschungsprojekt(forschungsobjekt, mitarbeiterAnzahl, dauer, herstellkosten);
        beforschteProdukte.add(forschungsobjekt);
        Projekte.add(forschungsprojekt);
            }

public ArrayList<Forschungsprojekt> getProjekte(){
        return Projekte;
}

    public void forschungsprojektAbbrechen(Forschungsprojekt forschungsprojekt){
        if(Projekte.contains(forschungsprojekt)) {
            forschungsprojekt.abbrechen();
            beforschteProdukte.remove(forschungsprojekt.getForschungsobjekt());
            verfügbareMitarbeiter += forschungsprojekt.getMitarbeiterAnzahl();
            Projekte.remove(forschungsprojekt);
        }
    }

    public void forschungsprojektAbschließen(Forschungsprojekt forschungsprojekt){
        if(Projekte.contains(forschungsprojekt)) {
            forschungsprojekt.abschließen();
            beforschteProdukte.remove(forschungsprojekt.getForschungsobjekt());
            verfügbareMitarbeiter += forschungsprojekt.getMitarbeiterAnzahl();
            Projekte.remove(forschungsprojekt);
        }
                }

public ArrayList<Produkt> getVerfügbareProdukte() {

        //Produkte, an denen bereits geforscht wird, aussondern
       // verfügbareProdukte = Produktreihe.getProduktreihen();
        for(Produkt produkt : beforschteProdukte){
        verfügbareProdukte.remove(produkt);
        }
        return verfügbareProdukte;
}


}
