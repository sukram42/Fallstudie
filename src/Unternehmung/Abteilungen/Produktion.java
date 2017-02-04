package Unternehmung.Abteilungen;

import Exceptions.*;
import Rules.Game;
import Unternehmung.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.round;

/**
 * Klasse, die die Produktionsabteilung repräsentiert
 * hier werden Maschinen, Produktions- und Lagerhallen gekauft und Produkte produziert
 * Created by lucadommes on 29.12.2016.
 */
public class Produktion extends Abteilung {

    private ArrayList<Maschine> maschinen = new ArrayList<Maschine>(); // Maschinenpark
    private ArrayList<Halle> produktionshallen = new ArrayList<Halle>();
    private ArrayList<Halle> lagerhallen = new ArrayList<Halle>();

    private ArrayList<Produktlinie> aufträge = new ArrayList<Produktlinie>(); // Produktionsaufträge
    private ArrayList<Produktlinie> lager = new ArrayList<Produktlinie>(); // Lagerbestand
    private Map<String, Double> forschungsboni = new HashMap<String, Double>(); // Verzeichnis über alle Produktlinien und ihrer Forschungsboni

    /**
     * Konstruktor, zum Erstellen der Abteilung Produktion
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Herstellkosten laufend fortzuschreiben (siehe produzieren())
     */
    public Produktion(Kennzahlensammlung kennzahlensammlung) {
        super("Produktion" , kennzahlensammlung);
        initForschungsboni();
    }

    /**
     * Methode zum Erstellen eines Produktionsauftrags
     * @param name Produkt (z.B. Rucksack)
     * @param qualitätsstufe A, B oder C
     * @param menge Größe des Auftrags -> so viele Produkte sollen monatlich in...
     * @param laufzeit ...so viel Zeit produziert werden (in (Spiel-)Wochen
     */
    public void produzieren(String name, char qualitätsstufe, int menge, int laufzeit) throws ZuWenigMitarbeiterOderMaschinenException{
        Produktlinie produktlinie = new Produktlinie(
                new Produkt(name, qualitätsstufe, this.getForschungsbonusById(name + qualitätsstufe)), menge, laufzeit);
        // prüfen, ob genügend Mitarbeiter, Maschinen und Liquidität vorhanden ist:
        if (menge <= this.getMaxProdMenge(name)){
            // Produktion in Auftrag geben:
            /* // Möglichkeit, die Menge eines bestehenden Auftrages zu erhöhen (Alternative: jedes mal einen neuen Auftrag erstellen (siehe unten))
            for (Produktlinie auftrag : this.aufträge){
                if (auftrag.getId().equals(produktlinie.getId())) {
                    int bisherigeMenge = auftrag.getMenge();
                    auftrag.setMenge(bisherigeMenge + menge);
                    System.out.println("Auftrag für Produktlinie " + produktlinie.getId() + " aufgestockt.");
                    return;
                }
            }
            */
            aufträge.add(produktlinie);
            System.out.println("Neue Produktlinie (" + produktlinie.getId() + ") in Auftrag gegeben.");
        } else {
            throw new ZuWenigMitarbeiterOderMaschinenException(name);
        }
    }

    /**
     * Funktion zum Kaufen von Maschinen (die Anschaffungskosten werden direkt weitergegeben)
     * @param produkt das Produkt, das mit der Maschine erzeugt werden soll
     * @param klasse 1, 2 oder 3
     * @param anzahl Anzahl der zu kaufenden Maschinen
     */
    public void maschinenKaufen(String produkt, int klasse, int anzahl) throws ZuWenigMaschinenstellplatzException {
        Maschine m = new Maschine(produkt, klasse); // eine Maschine erstellen, um Anschaffungskosten zu erfahren
        int anschaffungskst = m.getAnschaffungskst();
        // prüfen, ob genügend Fläche (= Produktionshalle) für neue Maschine(n) vorhanden ist:
        if (getFreienProduktionshallenPlatz() >= anzahl) {
            try {
                if (kennzahlensammlung.getBilanz().liquiditaetAusreichend(m.getAnschaffungskst())) {
                    kennzahlensammlung.getBilanz().liquiditaetAnpassen(-1 * (anschaffungskst * anzahl));
                    kennzahlensammlung.getBilanz().addTAMasch(anschaffungskst * anzahl);
                    maschinen.add(m);
                    for (int i = 1; i < anzahl; i++) {
                        Maschine n = new Maschine(produkt, klasse);
                        maschinen.add(n);
                    }
                    System.out.println(anzahl + " Maschine(n) der Klasse " + klasse + " gekauft, Kapazität: " + m.getKapazitaet() +
                            " Stück pro Jahr, Anschaffungskosten: " + anschaffungskst + " €");
                }
            } catch (ZuWenigCashException | BankruptException e) {
                e.printStackTrace();
            }
        } else {
            throw new ZuWenigMaschinenstellplatzException();
        }
    }

    /**
     * Maschine wird zu den halben Anschaffungskosten, multipliziert mit dem Reparaturstatus, verkauft
     * @param maschine, die zu verkaufen ist
     */
    public void maschineVerkaufen(Maschine maschine){
        try {
            float wiederverkaufswert = (maschine.getAnschaffungskst() / 2) * (float) maschine.getStatus();
            this.maschinen.remove(maschine);
            this.kennzahlensammlung.getBilanz().liquiditaetAnpassen(wiederverkaufswert);
            this.kennzahlensammlung.getBilanz().addTAMasch(- maschine.getAnschaffungskst());
            this.kennzahlensammlung.getGuv().addUmsatz(wiederverkaufswert);
        } catch (BankruptException e){
            e.printStackTrace();
        }
    }

    public void produktionshalleKaufen(int größe){
        Halle halle = new Halle("Produktionshalle", größe);
        try {
            if (kennzahlensammlung.getBilanz().liquiditaetAusreichend(halle.getPreis())) {
                kennzahlensammlung.getBilanz().liquiditaetAnpassen((-1f) * halle.getPreis());
                kennzahlensammlung.getBilanz().addGebäude(halle.getPreis());
                this.produktionshallen.add(halle);
                System.out.println("Produktionshalle der Größe " + größe + " für " + halle.getPreis() + " € gekauft.");
            }
        } catch (ZuWenigCashException | BankruptException e) {
            e.printStackTrace();
        }
    }

    public void lagerhalleKaufen(int größe){
        Halle halle = new Halle("Lagerhalle", größe);
        try{
            if (kennzahlensammlung.getBilanz().liquiditaetAusreichend(halle.getPreis())) {
                kennzahlensammlung.getBilanz().liquiditaetAnpassen(-1f * halle.getPreis());
                kennzahlensammlung.getBilanz().addGebäude(halle.getPreis());
                this.lagerhallen.add(halle);
                System.out.println("Lagerhalle der Größe " + größe + " für " + halle.getPreis() + " € gekauft.");
            }
        } catch (ZuWenigCashException | BankruptException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode, die von Game.run() beim Erhöhen des Timers ausgeführt wird
     */
    @Override
    public void update(){
        try {
            produkteFertigstellen();
        } catch (LagerVollException e){
            e.printStackTrace();
        }
        updateForschungsboni();
        for (Maschine maschine : this.maschinen){
            maschine.statusRuntersetzen();
        }
        // Wert der Fertigen Erzeugnisse im Lager an Bilanz weiter geben
        float wert = 0;
        for (Produktlinie produktlinie : this.lager){
            wert += produktlinie.getMenge() * produktlinie.getProdukt().getHerstellkosten();
        }
        this.kennzahlensammlung.getBilanz().setFeWert(wert);
    }

    // sämtliche Hilfsmethoden:
    /**
     * wird bei jedem timer count ausgeführt und legt die pro timer count produzierten Produkte im Lager ab
     */
    private void produkteFertigstellen () throws LagerVollException {
        for (Produktlinie auftrag : this.aufträge){
            auftrag.setLaufzeit(auftrag.getLaufzeit() - 1);
            if (auftrag.getEnd().equals(Game.getCalendar())){ // falls Laufzeit == 0 Auftrag beenden
                this.aufträge.remove(auftrag);
            }
            Produktlinie produktlinie = new Produktlinie(auftrag.getProdukt(), round(auftrag.getMenge()/Game.getCalendar().getActualMaximum(Calendar.MONTH))); // neue Produktlinie
            if (auftrag.getMenge() <= this.getFreienLagerPlatz()){ // genügend Lagerplatz verfügbar?
                //Prüfen ob Lager leer ist:
                if(lager.isEmpty()) {
                    this.lager.add(produktlinie);
                } else {
                    for (Produktlinie bestand : this.lager) {
                        // falls Produkte mit derselben id und herstellkosten schon vorhanden ist wird die Menge hochgesetzt:
                        if (produktlinie.getId().equals(bestand.getId()) &&
                                (produktlinie.getProdukt().getHerstellkosten() == bestand.getProdukt().getHerstellkosten())) {
                            bestand.setMenge(bestand.getMenge() + auftrag.getMenge());
                        } else { // ansonsten wird die Produktlinie als neuer Posten im Lager hinzugefügt:
                            this.lager.add(produktlinie);
                        }
                    }
                }
            } else {
                // gleicher Code wie oben, nur mit maximal zu lagernder Menge, sodass Lager bis zum Anschlag gefüllt wird, bevor Produkte verloren gehen:
                if(lager.isEmpty()) {
                    this.lager.add(produktlinie);
                } else {
                    for (Produktlinie bestand : this.lager) {
                        // falls Produkte mit derselben id und herstellkosten schon vorhanden ist wird die Menge hochgesetzt:
                        if (produktlinie.getId().equals(bestand.getId()) &&
                                (produktlinie.getProdukt().getHerstellkosten() == bestand.getProdukt().getHerstellkosten())) {
                            bestand.setMenge(bestand.getMenge() + this.getFreienLagerPlatz());
                        } else { // ansonsten wird die Produktlinie als neuer Posten im Lager hinzugefügt:
                            produktlinie.setMenge(this.getFreienLagerPlatz());
                            this.lager.add(produktlinie);
                        }
                    }
                }
                throw new LagerVollException();
            }
        }
    }

    /**
     * Methode zum Ermitteln des Forschungsstatus und somit -bonus eines Produkts / einer Produktlinie
     * @param id Kombination aus Produktname und Qualitätsstufe
     * @return double Forschungsbonus zu o.g. Produktlinie
     */
    public double getForschungsbonusById(String id){
        for (Map.Entry<String, Double> forschungsbonus : this.forschungsboni.entrySet()){
            if (forschungsbonus.getKey().equals(id)){
                return forschungsbonus.getValue();
            }
        }
        return 1;
    }

    public float getTaeglicheEnergiekosten(){
        float energiekosten = 0;
        for (Maschine maschine : maschinen){
            energiekosten += maschine.getEnergiekosten();
        }
        return energiekosten;
    }

    public float getTaeglicheHerstellkosten(){
        float herstellkosten = 0;
        for (Produktlinie auftrag : this.aufträge){
            herstellkosten += auftrag.getProdukt().getHerstellkosten() * auftrag.getMenge();
        }
        return herstellkosten;
    }

    /**
     * berechnet die maximal zu produzierende Menge eines Produkts, also abhängig von der Anzahl der produktspezifischen Maschinen
     * @param produkt, das produziert werden soll
     * @return MIN(Maschinenkapazität, Mitarbeiterkapazität)
     */
    public int getMaxProdMenge(String produkt){
        int maschKapazität = 0;
        int mitarbeiterKapazität = 0;
        for (Maschine maschine : this.maschinen){
            if (maschine.getProdukt().equals(produkt)){
                maschKapazität += maschine.getKapazitaet();
            }
        }
        for (Mitarbeiter mitarbeiter : this.mitarbeiter){
            mitarbeiterKapazität += mitarbeiter.getProdLeistung();
        }
        if (maschKapazität <= mitarbeiterKapazität){
            return maschKapazität;
        }
        return mitarbeiterKapazität;
    }

    /**
     * ermittelt verfügbaren Lagerplatz
     * @return Anzahl an Produkten, die noch in das/die Lager passen
     */
    public int getFreienLagerPlatz(){
        int produkte = 0;
        for (Produktlinie bestand : this.lager){
            produkte += bestand.getMenge();
        }
        return getGesamtenLagerPlatz() - produkte;
    }
    public int getGesamtenLagerPlatz(){
        int lagerplatz = 0;
        for (Halle halle : lagerhallen){
            lagerplatz += halle.getKapazität();
        }
        return lagerplatz;
    }

    /**
     * @return Anzahl verfügbare Stellplätze für Maschinen
     */
    public int getFreienProduktionshallenPlatz(){
        return getGesamtenProduktionshallenPlatz() - this.maschinen.size();
    }
    public int getGesamtenProduktionshallenPlatz()
    {
        int maschinenPlaetze = 0;
        for (Halle halle : this.produktionshallen){
            maschinenPlaetze += halle.getKapazität();
        }
        return maschinenPlaetze;
    }

    /**
     * initialisiert Forschungsboni mit 1, wenn geforscht wird wird dieser entsprechend runtergesetzt
     */
    private void initForschungsboni(){
        this.forschungsboni.put("RucksackA",(double) 1);
        this.forschungsboni.put("RucksackB",(double) 1);
        this.forschungsboni.put("RucksackC",(double) 1);
        this.forschungsboni.put("RucksacktechA",(double) 1);
        this.forschungsboni.put("RucksacktechB",(double) 1);
        this.forschungsboni.put("RucksacktechC",(double) 1);
        this.forschungsboni.put("DuffelA",(double) 1);
        this.forschungsboni.put("DuffelB",(double) 1);
        this.forschungsboni.put("DuffelC",(double) 1);
        this.forschungsboni.put("ReisetascheA",(double) 1);
        this.forschungsboni.put("ReisetascheB",(double) 1);
        this.forschungsboni.put("ReisetascheC",(double) 1);
    }

    /**
     * Schnittstelle für Forschung: setzt einen neuen Forschungsbonus
     * @param id Produktbezeichnung
     * @param neuerForschungsbonus (durch Forschung) veränderter Forschungsbonus
     */
    public void setForschungsbonus (String id, double neuerForschungsbonus){
        for (Map.Entry<String, Double> alterForschungsbonus : this.forschungsboni.entrySet()){
            if (alterForschungsbonus.getKey().equals(id)){
                alterForschungsbonus.setValue(neuerForschungsbonus);
            }
        }
    }

    /**
     * prüft bei jedem Timer Count, ob Forschungsboni noch aktuell sind und passt sie ggf. an
     */
    private void updateForschungsboni(){
        for (Produktlinie auftrag : this.aufträge){
            double forschungsbonus = this.getForschungsbonusById(auftrag.getId());
            auftrag.getProdukt().setForschungsbonus(forschungsbonus);
            auftrag.getProdukt().setHerstellkosten(auftrag.getProdukt().getHerstellkosten() * forschungsbonus);
        }
    }


    // Getter und Setter:
    public Map<String, Double> getForschungsboni() {
        return forschungsboni;
    }

    public ArrayList<Produktlinie> getLager() {
        return lager;
    }

    public ArrayList<Produktlinie> getAufträge() {
        return aufträge;
    }

    public ArrayList<Maschine> getMaschinen(){return maschinen;};
}
