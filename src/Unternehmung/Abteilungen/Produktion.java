package Unternehmung.Abteilungen;

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
    private Map<String, Produktlinie> aufträge = new HashMap<String, Produktlinie>(); // Produktionsaufträge
    private Map<String, Produktlinie> lager = new HashMap<String, Produktlinie>(); // Lagerbestand
    private Map<String, Double> produktlinien = new HashMap<String, Double>(); // Verzeichnis über alle je produzierten Produktlinien, um zu prüfen, ob an einer Produktlinie bereits geforscht wurde oder nicht

    /**
     * Konstruktor, zum Erstellen der Abteilung Produktion
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Herstellkosten laufend fortzuschreiben (siehe produzieren())
     */
    public Produktion(Kennzahlensammlung kennzahlensammlung) {
        super("Produktion" , kennzahlensammlung);
    }

    /**
     * Methode zum Erstellen eines Produktionsauftrags einer neuen oder bestehenden Produktlinie
     * @param name Produkt (z.B. Rucksack)
     * @param qualitätsstufe A, B oder C
     * @param menge Größe des Auftrags -> so viele Produkte sollen in...
     * @param laufzeit ...so viel Zeit produziert werden (in (Spiel-)Wochen
     */
    public void produzieren(String name, char qualitätsstufe, int menge, int laufzeit){
        Produktlinie produktlinie = new Produktlinie(new Produkt(name, qualitätsstufe), menge, laufzeit);
        // prüfen, ob genügend Mitarbeiter, Maschinen und Liquidität vorhanden ist:
        if (menge <= getMaxProdMenge() && this.kennzahlensammlung.liquiditätVorhanden(produktlinie.getProdukt().getHerstellkosten(), "herstellkosten")){
            // Herstellkosten, falls an dem Produkt bereits geforscht wurde, senken:
            double forschungsbonus = getForschungsbonusById(produktlinie.getId());
            produktlinie.getProdukt().setHerstellkosten(produktlinie.getProdukt().getHerstellkosten() * forschungsbonus);
            // Produktion in Auftrag geben:
            if (this.aufträge.get(produktlinie.getId()) == null){
                this.aufträge.put(produktlinie.getId(), produktlinie);
                System.out.println("Neue Produktlinie (" + produktlinie.getId() + ") in Auftrag gegeben.");
            } else {
                int bisherigeMenge = this.aufträge.get(produktlinie.getId()).getMenge();
                this.aufträge.get(produktlinie.getId()).setMenge(bisherigeMenge + menge);
                System.out.println("Auftrag für Produktlinie " + produktlinie.getId() + " aufgestockt.");
            }
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
        // prüfen, ob genügend Liquidität vorhanden (falls ja Maschine von oben und alle weiteren zum Maschinenpark hinzufügen):
        if (this.kennzahlensammlung.liquiditätVorhanden(anschaffungskst * anzahl, "sonstige Kosten")) {
            // prüfen, ob genügend Fläche (= Produktionshalle) für neue Maschine(n) vorhanden ist:
            if (getFreienMaschinenPlatz() >= anzahl) {
                maschinen.add(m);
                for (int i = 1; i < anzahl; i++) {
                    Maschine n = new Maschine(klasse);
                    maschinen.add(n);
                }
                System.out.println(anzahl + " Maschine(n) der Klasse " + klasse + " gekauft, Kapazität: " + m.getKapazität() +
                        " Stück pro Jahr, Anschaffungskosten: " + anschaffungskst + " €");
            } else {
                System.out.println("Nicht genügend Maschinenstellplätze vorhanden!");
            }
        } else {
            System.out.println("Nicht genügend Liquidität vorhanden!");
        }
    }

    public void produktionshalleKaufen(int größe){
        Halle halle = new Halle("Produktionshalle", größe);
        if (this.kennzahlensammlung.liquiditätVorhanden(halle.getPreis(), "sonstige Kosten")){
            this.produktionshallen.add(halle);
            System.out.println("Produktionshalle der Größe " + größe + " für " + halle.getPreis() + " € gekauft.");
        } else {
            System.out.println("Nicht genügend Liquidität vorhanden!");
        }
    }

    public void lagerhalleKaufen(int größe){
        Halle halle = new Halle("Produktionshalle", größe);
        if (this.kennzahlensammlung.liquiditätVorhanden(halle.getPreis(), "sonstige Kosten")){
            this.lagerhallen.add(halle);
            System.out.println("Lagerhalle der Größe " + größe + " für " + halle.getPreis() + " € gekauft.");
        } else {
            System.out.println("Nicht genügend Liquidität vorhanden!");
        }
    }

    /**
     * Methode, die von Game.run() beim Erhöhen des Timers ausgeführt wird
     */
    public void update(){
        this.kennzahlensammlung.addSonstigeKosten(getTaeglicheEnergiekosten());
        this.kennzahlensammlung.addHerstellkosten(getTaeglicheHerstellkosten());
        produkteFertigstellen();
    }



    // sämtliche Hilfsmethoden:
    /**
     * wird bei jedem timer count ausgeführt und legt die pro timer count produzierten Produkte im Lager ab
     */
    private void produkteFertigstellen () {
        for (Map.Entry<String, Produktlinie> auftrag : aufträge.entrySet()){
            String id = auftrag.getValue().getId();
            int laufzeit = auftrag.getValue().getLaufzeit();
            if (auftrag.getValue().getMenge() <= getFreienLagerPlatz()) { // prüfen, ob genügend Lagerplatz vorhanden ist, falls nicht gehen die Produkte verloren
                if (lager.get(id) == null) { // falls Produktlinie noch nicht im Lager vorhanden
                    // erstelle neue Produktlinie in Lager:
                    Produktlinie neuePL = new Produktlinie(auftrag.getValue().getProdukt(), auftrag.getValue().getMenge());
                    lager.put(id, neuePL);
                    // Laufzeit herunter setzen:
                    auftrag.getValue().setLaufzeit(laufzeit - 1);
                } else { // falls Produktlinie im Lager vorhanden Menge hochsetzen:
                    lager.get(id).setMenge(lager.get(id).getMenge() + auftrag.getValue().getMenge());
                    // Laufzeit herunter setzen:
                    auftrag.getValue().setLaufzeit(laufzeit - 1);
                }
                if (laufzeit == 0) {
                    aufträge.remove(id);
                }
            } else {
                System.out.println("Nicht genügend Lagerfläche vorhanden. Die Produkte gehen verloren.");
            }
        }
    }

    /**
     * Methode zum Ermitteln des Forschungsstatus und somit -bonus eines Produkts / einer Produktlinie
     * falls keine Produktlinie gefunden wird bedeutet dies, dass diese Kombination noch nicht produziert wurde, es wir ein neuer Eintrag in die Map produktlinien gemacht
     * @param id Kombination aus Produktname und Qualitätsstufe
     * @return double Forschungsbonus zu o.g. Produktlinie
     */
    public double getForschungsbonusById(String id){
        for (Map.Entry<String, Double> produktlinie : this.produktlinien.entrySet()){
            if (produktlinie.getKey().startsWith(id)){
                return produktlinie.getValue();
            }
        }
        this.produktlinien.put(id, 1.0);
        return 1.0;
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
        for (Map.Entry<String, Produktlinie> produktlinie : aufträge.entrySet()){
            herstellkosten += produktlinie.getValue().getMenge() * produktlinie.getValue().getProdukt().getHerstellkosten();
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
        for (Map.Entry<String, Produktlinie> produktlinie : lager.entrySet()){
            produkte += produktlinie.getValue().getMenge();
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



    // Getter und Setter:
    public Map<String, Double> getProduktlinien() {
        return produktlinien;
    }
}
