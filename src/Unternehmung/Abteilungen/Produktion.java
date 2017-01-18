package Unternehmung.Abteilungen;

import Exceptions.BankruptException;
import Unternehmung.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasse, die die Produktionsabteilung repräsentiert
 * hier werden Maschinen, Produktions- und Lagerhallen gekauft und Produkte produziert
 * Created by lucadommes on 29.12.2016.
 */
public class Produktion extends Abteilung {

    private ArrayList<Maschine> maschinen = new ArrayList<Maschine>(); // Maschinenpark
    private ArrayList<Halle> produktionshallen = new ArrayList<Halle>();
    private ArrayList<Halle> lagerhallen = new ArrayList<Halle>();
    //private Map<String, Produktlinie> aufträge = new HashMap<String, Produktlinie>(); // Produktionsaufträge
    //private Map<String, Produktlinie> lager = new HashMap<String, Produktlinie>(); // Lagerbestand
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
     * Methode zum Erstellen eines Produktionsauftrags einer neuen oder bestehenden Produktlinie
     * @param name Produkt (z.B. Rucksack)
     * @param qualitätsstufe A, B oder C
     * @param menge Größe des Auftrags -> so viele Produkte sollen in...
     * @param laufzeit ...so viel Zeit produziert werden (in (Spiel-)Wochen
     */
    public void produzieren(String name, char qualitätsstufe, int menge, int laufzeit){
        Produktlinie produktlinie = new Produktlinie(
                new Produkt(name, qualitätsstufe, this.getForschungsbonusById(name + qualitätsstufe)), menge, laufzeit);
        // prüfen, ob genügend Mitarbeiter, Maschinen und Liquidität vorhanden ist:
        if (menge <= getMaxProdMenge()){
            // Produktion in Auftrag geben:
            for (Produktlinie auftrag : this.aufträge){
                if (auftrag.getId().equals(produktlinie.getId())) {
                    int bisherigeMenge = auftrag.getMenge();
                    auftrag.setMenge(bisherigeMenge + menge);
                    System.out.println("Auftrag für Produktlinie " + produktlinie.getId() + " aufgestockt.");
                    return;
                }
            }
            aufträge.add(produktlinie);
            System.out.println("Neue Produktlinie (" + produktlinie.getId() + ") in Auftrag gegeben.");
        } else {
            System.out.println("Nicht genügend Maschinen und/oder Mitarbeiter zum Produzieren vorhanden!");
        }
    }

    /**
     * Funktion zum Kaufen von Maschinen (die Anschaffungskosten werden direkt weitergegeben (siehe addSonstigeKosten()))
     * @param klasse 1, 2 oder 3
     * @param anzahl Anzahl der zu kaufenden Maschinen
     */
    public void maschinenKaufen(int klasse, int anzahl) {
        Maschine m = new Maschine(klasse); // eine Maschine erstellen, um Anschaffungskosten zu erfahren
        int anschaffungskst = m.getAnschaffungskst();
        // prüfen, ob genügend Fläche (= Produktionshalle) für neue Maschine(n) vorhanden ist:
        if (getFreienMaschinenPlatz() >= anzahl) {
            try {
                kennzahlensammlung.liquiditätAnpassen(anschaffungskst * anzahl);
                maschinen.add(m);
                for (int i = 1; i < anzahl; i++) {
                    Maschine n = new Maschine(klasse);
                    maschinen.add(n);
                }
                System.out.println(anzahl + " Maschine(n) der Klasse " + klasse + " gekauft, Kapazität: " + m.getKapazität() +
                        " Stück pro Jahr, Anschaffungskosten: " + anschaffungskst + " €");
            } catch (BankruptException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nicht genügend Maschinenstellplätze vorhanden!");
        }
    }

    public void produktionshalleKaufen(int größe){
        Halle halle = new Halle("Produktionshalle", größe);
        try{
            kennzahlensammlung.liquiditätAnpassen((float) halle.getPreis());
            this.produktionshallen.add(halle);
            System.out.println("Produktionshalle der Größe " + größe + " für " + halle.getPreis() + " € gekauft.");
        } catch (BankruptException e) {
            e.printStackTrace();
        }
    }

    public void lagerhalleKaufen(int größe){
        Halle halle = new Halle("Produktionshalle", größe);
        try{
            kennzahlensammlung.liquiditätAnpassen((float) halle.getPreis());
            this.lagerhallen.add(halle);
            System.out.println("Lagerhalle der Größe " + größe + " für " + halle.getPreis() + " € gekauft.");
        } catch (BankruptException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode, die von Game.run() beim Erhöhen des Timers ausgeführt wird
     */
    @Override
    public void update(){
        try {
            this.kennzahlensammlung.liquiditätAnpassen(getTaeglicheEnergiekosten() + getTaeglicheHerstellkosten());
        } catch (BankruptException e){
            e.printStackTrace();
        }
        produkteFertigstellen();
        updateForschungsboni();
    }



    // sämtliche Hilfsmethoden:
    /**
     * wird bei jedem timer count ausgeführt und legt die pro timer count produzierten Produkte im Lager ab
     */
    private void produkteFertigstellen () {
        for (Produktlinie auftrag : this.aufträge){
            if (auftrag.getMenge() <= this.getFreienLagerPlatz()){ // genügend Lagerplatz verfügbar?
                Produktlinie produktlinie = new Produktlinie(auftrag.getProdukt(), auftrag.getMenge()); // neue Produktlinie
                for (Produktlinie bestand : this.lager) {
                    // falls Produkte mit derselben id und herstellkosten schon vorhanden ist wird die Menge hochgesetzt:
                    if (produktlinie.getId().equals(bestand.getId()) &&
                            (produktlinie.getProdukt().getHerstellkosten() == bestand.getProdukt().getHerstellkosten())){
                        bestand.setMenge(bestand.getMenge() + auftrag.getMenge());
                        break;
                    } else { // ansonsten wird die Produktlinie als neuer Posten im Lager hinzugefügt:
                        this.lager.add(produktlinie);
                    }
                }
            } else {
                System.out.println("Nicht genügend Lagerfläche vorhanden. Die Produkte gehen verloren.");
            }
            auftrag.setLaufzeit(auftrag.getLaufzeit() - 1); // Laufzeit herunter setzen
            if (auftrag.getLaufzeit() == 0){ // falls Laufzeit == 0 Auftrag beenden
                this.aufträge.remove(auftrag);
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
            herstellkosten += auftrag.getProdukt().getHerstellkosten();
        }
        return herstellkosten;
    }

    /**
     * Methode, die die maximal zu produzierende Menge berechnet
     * @return MIN(Maschinenkapazität, Mitarbeiterkapazität)
     */
    public int getMaxProdMenge(){
        int maschKapazität = 0;
        int mitarbeiterKapazität = 0;
        for (int i = 0; i < this.maschinen.size(); i++){
            maschKapazität += this.maschinen.get(i).getKapazität();
        }
        for (int i = 0; i < this.getMitarbeiter().size(); i++){
            mitarbeiterKapazität += this.getMitarbeiter().get(i).getProdLeistung();
        }
        if (maschKapazität >= mitarbeiterKapazität){
            return maschKapazität;
        }
        return mitarbeiterKapazität;
    }

    /**
     * ermittelt verfügbaren Lagerplatz
     * @return Anzahl an Produkten, die noch in das/die Lager passen
     */
    public int getFreienLagerPlatz(){
        int lagerplatz = 0;
        int produkte = 0;
        for (Halle halle : lagerhallen){
            lagerplatz += halle.getKapazität();
        }
        for (Produktlinie bestand : this.lager){
            produkte += bestand.getMenge();
        }
        return lagerplatz - produkte;
    }

    /**
     * @return Anzahl verfügbare Stellplätze für Maschinen
     */
    public int getFreienMaschinenPlatz(){
        int maschinenPlaetze = 0;
        for (Halle halle : this.produktionshallen){
            maschinenPlaetze += halle.getKapazität();
        }
        return maschinenPlaetze - this.maschinen.size();
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
        this.forschungsboni.put("TascheA",(double) 1);
        this.forschungsboni.put("TascheB",(double) 1);
        this.forschungsboni.put("TascheC",(double) 1);
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
}
