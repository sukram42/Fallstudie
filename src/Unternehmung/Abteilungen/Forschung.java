package Unternehmung.Abteilungen;

import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Abteilung;
import Unternehmung.Objekte.Forschungsprojekt;
import Unternehmung.Kennzahlensammlung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by D064018 on 11.01.2017.
 */

public class Forschung extends Abteilung {

    private Produktion produktion;

    private Map<String, Float> imageBoni = new HashMap<>();
    private List<String> verfügbareProdukte; //um bereits beforschte Produkte reduzieren
    private List<String> beforschteProdukte = new ArrayList<>();
    private List<Forschungsprojekt> abgeschlosseneForschungen = new ArrayList<>();
    private int beschäftigteMitarbeiter;
    private List<Forschungsprojekt> projekte = new ArrayList<>();

    public Forschung(Kennzahlensammlung kennzahlensammlung, Abteilung produktion) {
        super("Forschung", kennzahlensammlung);
        initImageBoni();
        verfügbareProdukte = new ArrayList<>(imageBoni.keySet());
        this.produktion = (Produktion) produktion;
    }

    private void initImageBoni() {
        this.imageBoni.put("RucksackA", 0f);
        this.imageBoni.put("RucksackB", 0f);
        this.imageBoni.put("RucksackC", 0f);
        this.imageBoni.put("RucksacktechA", 0f);
        this.imageBoni.put("RucksacktechB", 0f);
        this.imageBoni.put("RucksacktechC", 0f);
        this.imageBoni.put("DuffelA", 0f);
        this.imageBoni.put("DuffelB", 0f);
        this.imageBoni.put("DuffelC", 0f);
        this.imageBoni.put("ReisetascheA", 0f);
        this.imageBoni.put("ReisetascheB", 0f);
        this.imageBoni.put("ReisetascheC", 0f);
    }

    public void setImagebonus(String id, float zusätzlicherImagebonus) {
        for (Map.Entry<String, Float> Imagebonus : this.imageBoni.entrySet()) {
            if (Imagebonus.getKey().equals(id)) {
                if (Imagebonus.getValue() + zusätzlicherImagebonus > 0.083f) { //Der neue Bonus würde den maximal Bonus übersteigen
                    this.kennzahlensammlung.getWeicheKennzahl("kundenzufriedenheit").addModifier(0.083f - Imagebonus.getValue());
                    Imagebonus.setValue(0.083f);
                } else {
                    Imagebonus.setValue(Imagebonus.getValue() + zusätzlicherImagebonus);
                    this.kennzahlensammlung.getWeicheKennzahl("kundenzufriedenheit").addModifier(zusätzlicherImagebonus);
                }
            }
        }
    }

    public void setForschungsbonus(String id, double zusätzlicherForschungsbonus) {
        for (Map.Entry<String, Double> Produktionsbonus : produktion.getForschungsboni().entrySet()) {
            if (Produktionsbonus.getKey().equals(id)) {
                if (Produktionsbonus.getValue() - zusätzlicherForschungsbonus < 0.75) { //Der neue Bonus würde den maximal Bonus übersteigen
                    Produktionsbonus.setValue(0.75);
                } else {
                    Produktionsbonus.setValue(Produktionsbonus.getValue() - zusätzlicherForschungsbonus);
                }
            }
        }
    }

    public float getImagebonusById(String id) {
        for (Map.Entry<String, Float> imageBoni : this.imageBoni.entrySet()) {
            if (imageBoni.getKey().equals(id)) {
                return imageBoni.getValue();
            }
        }
        return 0;
    }

    public void starteProjekt(String forschungsobjekt,
                              int mitarbeiterAnzahl, int dauer, boolean herstellkosten) throws ZuWenigMitarbeiterException {
        if ((beschäftigteMitarbeiter + mitarbeiterAnzahl) <= this.getMitarbeiterAnzahl()) { //Überprüfung, ob es genügend Mitarbeiter gibt
            beschäftigteMitarbeiter = +mitarbeiterAnzahl;
            Forschungsprojekt forschungsprojekt = new Forschungsprojekt(kennzahlensammlung,this, forschungsobjekt, mitarbeiterAnzahl, dauer, herstellkosten);
            beforschteProdukte.add(forschungsobjekt);
            projekte.add(forschungsprojekt);
        } else {
            throw new ZuWenigMitarbeiterException("Forschung");
        }
    }

    public List<Forschungsprojekt> getProjekte() {
        return projekte;
    }

    public void forschungsprojektAbbrechen(Forschungsprojekt forschungsprojekt) {
        if (projekte.contains(forschungsprojekt)) {
            forschungsprojekt.abbrechen();
            beforschteProdukte.remove(forschungsprojekt.getForschungsobjekt());
            beschäftigteMitarbeiter -= forschungsprojekt.getMitarbeiterAnzahl();
            projekte.remove(forschungsprojekt);
        }
    }

    public void forschungsprojektAbschließen(Forschungsprojekt forschungsprojekt) {
        if (projekte.contains(forschungsprojekt)) {
            forschungsprojekt.abschließen();
            beforschteProdukte.remove(forschungsprojekt.getForschungsobjekt());
            beschäftigteMitarbeiter -= forschungsprojekt.getMitarbeiterAnzahl();
            abgeschlosseneForschungen.add(forschungsprojekt);
            projekte.remove(forschungsprojekt);
        }
    }

    public List<String> getVerfügbareProdukte() {
        //Produkte, an denen bereits geforscht wird, aussondern
        //Gibt es besondere Produkt Objekte, oder genügt der der Namens-String
        for (String produkt : beforschteProdukte) {
            verfügbareProdukte.remove(produkt);
        }
        return verfügbareProdukte;
    }

    public void update() {
        if (beschäftigteMitarbeiter > this.getMitarbeiterAnzahl()) {
            int i = beschäftigteMitarbeiter - this.getMitarbeiterAnzahl();
            for (int x = i; x > 0; x--) {
                Forschungsprojekt projekt = this.getProjekte().get(this.projekte.size() - 1);
                projekt.feuereMitarbeiter();
                this.beschäftigteMitarbeiter -= 1;
                if (projekt.getMitarbeiterAnzahl() == 0) {
                    projekt.abschließen();
                }
            }
        }
        for (Forschungsprojekt projekt : this.projekte) {

            if (projekt.getEnde().getTime().equals(Game.getCalendar().getTime())) {
                forschungsprojektAbschließen(projekt);
            }
        }

    }

    public List getAbgeschlosseneForschungen()
    {
        return abgeschlosseneForschungen;
    }
}