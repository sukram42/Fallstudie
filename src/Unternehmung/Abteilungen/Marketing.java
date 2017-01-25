package Unternehmung.Abteilungen;

import Exceptions.LaeuftBereitsException;
import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.Abteilung;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Marketingkampagne;
import Unternehmung.Marktforschung;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasse, die die Abteilung Marketing repräsentiert, hier können Marketingkampagnen und Marktforschung durchgeführt werden
 * Created by lucadommes on 29.12.2016.
 */
public class Marketing extends Abteilung {

    Map<String, Marketingkampagne> kampagnen = new HashMap<String, Marketingkampagne>(); // <art, Marketingkampagne>
    Map<Integer, Marktforschung> mafos = new HashMap<Integer, Marktforschung>(); // <umfang, Marktforschung>

    /**
     * Konstruktor, zum Erstellen der Abteilung Marketing
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Kennzahlensammlung laufend fortzuschreiben / zu berechnen
     */
    public Marketing(Kennzahlensammlung kennzahlensammlung) {
        super("Marketing",kennzahlensammlung);
    }

    /**
     * startet eine Werbekampagne
     * @param art Art der Kampagne, etwa Plakate, Print, Radio oder TV (unterschiedlich teuer und unterschiedlich effektiv)
     * @param laufzeit in timer counts
     */
    public void marketingkampagneStarten(String art, int laufzeit) throws ZuWenigMitarbeiterException, LaeuftBereitsException{
        if (this.kampagnen.get(art) == null) {
            Marketingkampagne kampagne = new Marketingkampagne(art, laufzeit);
            if (kampagne.getNoetigeMitarbeiter() <= this.getMitarbeiter().size()) {
                this.kampagnen.put(art, kampagne);
                System.out.println("Marketingkampagne \"" + art + "\" gestartet. Kosten: " + kampagne.getKosten()
                        + " € pro Tag, Bekanntheitsgrad steigt täglich um " + kampagne.getImpact());
            } else {
                throw new ZuWenigMitarbeiterException("Marketing");
            }
        } else {
            throw new LaeuftBereitsException("Marketingkampagne");
        }
    }


    public void marketingkampagneAbbrechen(String art) {
        this.kampagnen.remove(art);
    }

    /**
     * Methode zur Durchführung von Marktforschung. Hierdurch können z.B. besser Zielgruppen angesprochen werden,
     * wodurch sich Produkte besser verkaufen lassen (Absatzrate steigt)
     * @param umfang drei verschiedene "Größen" mit unterschiedlichen Dauer und Effektivität
     */
    public void marktforschungStarten(int umfang) throws ZuWenigMitarbeiterException, LaeuftBereitsException{
        if (this.mafos.get(umfang) == null){
            Marktforschung mafo = new Marktforschung(umfang);
            if (mafo.getNoetigeMitarbeiter() <= this.getMitarbeiter().size()) {
                this.mafos.put(umfang, mafo);
            } else {
                throw new ZuWenigMitarbeiterException("Marketing");
            }
        } else {
            throw new LaeuftBereitsException("Marktforschung");
        }
    }

    /**
     * Bricht das Marktforschungsprojekt ab -> das Geld wurde umsonst ausgegeben, da Mafos erst ganz am Ende einen Effekt haben
     * @param umfang 1, 2 oder 3
     */
    public void marktforschungAbbrechen(int umfang){
        this.kampagnen.remove(umfang);
    }

    @Override
    public void update(){
        updateMarketingkampagnen();
        updateMafos();
    }

    @Override
    public float getKosten(){
        float taeglicheKosten = 0;
        for (Map.Entry<String, Marketingkampagne> kampagne : this.kampagnen.entrySet()) {
            taeglicheKosten += kampagne.getValue().getKosten();
        }
        for (Map.Entry<Integer, Marktforschung> mafo : this.mafos.entrySet()){
            taeglicheKosten += mafo.getValue().getKosten();
        }
        return taeglicheKosten;
    }

    // Hilfsmethoden:
    /**
     * zählt die verbleibende Laufzeit runter und löscht Projekt, wenn Laufzeit vorbei
     */
    private void updateMarketingkampagnen() {
        for (Map.Entry<String, Marketingkampagne> kampagne : this.kampagnen.entrySet()) {
            this.kennzahlensammlung.getBekanntheitsgrad().addModifier(kampagne.getValue().getImpact()); // impact weitergeben
            if (kampagne.getValue().getEnd().equals(Game.getCalendar())) {
                this.kampagnen.remove(kampagne.getKey());
            }
        }
    }

    /**
     * zählt die verbleibende Dauer runter und löscht Projekt, wenn Dauer vorbei
     */
    private void updateMafos(){
        for (Map.Entry<Integer, Marktforschung> mafo : this.mafos.entrySet()){
            if (mafo.getValue().getEnd().equals(Game.getCalendar())){
                this.kennzahlensammlung.getWeicheKennzahl("verkaufswahrscheinlichkeit").addModifier(mafo.getValue().getImpact());
                this.mafos.remove(mafo.getKey());
            }
        }
    }

    /**
     * @return Anzahl der Mitarbeiter, die für Marktforschung oder Marketingkampagnen verfügbar sind
     */
    public int getFreieMitarbeiter(){
        int beschaeftigteMitarbeiter = 0;
        for (Map.Entry<String, Marketingkampagne> kampagne : this.kampagnen.entrySet()){
            beschaeftigteMitarbeiter += kampagne.getValue().getNoetigeMitarbeiter();
        }
        for (Map.Entry<Integer, Marktforschung> mafo : this.mafos.entrySet()){
            beschaeftigteMitarbeiter += mafo.getValue().getNoetigeMitarbeiter();
        }
        return this.getMitarbeiter().size() - beschaeftigteMitarbeiter;
    }

}
