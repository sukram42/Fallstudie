package Unternehmung.Abteilungen;

import Rules.Game;
import Unternehmung.Abteilung;
import Unternehmung.Forschungsprojekt;
import Unternehmung.Kennzahlensammlung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by D064018 on 11.01.2017.
 */

public class Forschung extends Abteilung{

    private Map<String, Double> imageBoni = new HashMap<>();
    private ArrayList<String> verfügbareProdukte; //um bereits beforschte Produkte reduzieren
    private ArrayList<String> beforschteProdukte = new ArrayList<>();
    private int beschäftigteMitarbeiter;
    private ArrayList<Forschungsprojekt> projekte = new ArrayList<>();
    private Produktion produktion;

    public Forschung(Kennzahlensammlung kennzahlensammlung, Abteilung produktion) {
          super("Forschung",kennzahlensammlung);
          initImageBoni();
          verfügbareProdukte = new ArrayList<>(imageBoni.keySet());
          this.produktion = (Produktion)produktion;
    }

    private void initImageBoni(){
        this.imageBoni.put("RucksackA",(double) 0);
        this.imageBoni.put("RucksackB",(double) 0);
        this.imageBoni.put("RucksackC",(double) 0);
        this.imageBoni.put("RucksacktechA",(double) 0);
        this.imageBoni.put("RucksacktechB",(double) 0);
        this.imageBoni.put("RucksacktechC",(double) 0);
        this.imageBoni.put("DuffelA",(double) 0);
        this.imageBoni.put("DuffelB",(double) 0);
        this.imageBoni.put("DuffelC",(double) 0);
        this.imageBoni.put("ReisetascheA",(double) 0);
        this.imageBoni.put("ReisetascheB",(double) 0);
        this.imageBoni.put("ReisetascheC",(double) 0);
    }

    public void setImagebonus (String id, double neuerImagebonus){
        for (Map.Entry<String, Double> alterImagebonus : this.imageBoni.entrySet()){
            if (alterImagebonus.getKey().equals(id)){
                alterImagebonus.setValue(neuerImagebonus);
            }
        }
    }

    public double getImagebonusById(String id) {
        for (Map.Entry<String, Double> imageBoni : this.imageBoni.entrySet()) {
            if (imageBoni.getKey().equals(id)) {
                return imageBoni.getValue();
            }
        }
        return 0;
    }

    public void starteProjekt(Produktion produktion, Forschung forschung, String forschungsobjekt, int mitarbeiterAnzahl, long dauer, boolean herstellkosten, Kennzahlensammlung kennzahlensammlung){
        if((beschäftigteMitarbeiter + mitarbeiterAnzahl) <= this.getMitarbeiterAnzahl()) { //Überprüfung, ob es genügend Mitarbeiter gibt
            beschäftigteMitarbeiter =+ mitarbeiterAnzahl;
            Forschungsprojekt forschungsprojekt = new Forschungsprojekt(produktion, forschung, forschungsobjekt, mitarbeiterAnzahl, dauer, herstellkosten);
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
            beschäftigteMitarbeiter -= forschungsprojekt.getMitarbeiterAnzahl();
            projekte.remove(forschungsprojekt);
        }
    }

    public void forschungsprojektAbschließen(Forschungsprojekt forschungsprojekt){
        if(projekte.contains(forschungsprojekt)) {
            forschungsprojekt.abschließen();
            beforschteProdukte.remove(forschungsprojekt.getForschungsobjekt());
            beschäftigteMitarbeiter -= forschungsprojekt.getMitarbeiterAnzahl();
            projekte.remove(forschungsprojekt);
        }
                }

public ArrayList<String> getVerfügbareProdukte() {
        //Produkte, an denen bereits geforscht wird, aussondern
        for(String produkt : beforschteProdukte){
        verfügbareProdukte.remove(produkt);
        }
        return verfügbareProdukte;
}

public void addMitarbeiter(){

}

public void update() {
    for (Forschungsprojekt projekt : projekte) {
        if ((projekt.getBeginn() + projekt.getDauer()) <= Game.getTime()) { //Wurde das Projekt abgeschlossen?
            projekt.abschließen();
        }
    }

}
}
