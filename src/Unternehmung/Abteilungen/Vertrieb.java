package Unternehmung.Abteilungen;

import Exceptions.BankruptException;
import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.*;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Objekte.Ausschreibung;
import Unternehmung.Objekte.Produkt;
import Unternehmung.Objekte.Produktlinie;
import Unternehmung.Objekte.Vertrag;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Abteilung, die für Vertrieb zuständig ist
 * Created by lucadommes on 30.12.2016.
 */
public class Vertrieb extends Abteilung {

    private Produktion produktion;
    private Unternehmen unternehmen;
    private int verkaufteProdukte; // Anzahl verkaufter Produkte im vergangenen Monat (für Berechnung des Marktanteils)
    private ArrayList<Ausschreibung> opportunities = new ArrayList<>();
    private CopyOnWriteArrayList<Vertrag> accounts = new CopyOnWriteArrayList<>();


    public Vertrieb(Unternehmen unternehmen, Kennzahlensammlung kennzahlensammlung, Abteilung produktion) {
        super("Vertrieb",kennzahlensammlung);
        this.unternehmen = unternehmen;
        this.produktion = (Produktion) produktion;
        // Erster Kunde, um schneller in das Spiel hinein zu kommen:
        Vertrag ersterVertrag = new Vertrag(new Produktlinie(new Produkt("Rucksack", 'C'), 100), "FirstCustomer AG", 3);
        ersterVertrag.setStrafe(0);
        this.accounts.add(ersterVertrag);
    }

    /**
     * speichert ein Ausschreibungsobjekt mit dem index, in dem es in Game.ausschreibungen gespeichert ist ab (sofern genug Mitarbeiter vorhanden -> ein Mitarbeiter pro Bewerbung nötig)
     * @param ausschreibung aus der Liste in der Klasse Game
     */
    public void bewerben(Ausschreibung ausschreibung) throws ZuWenigMitarbeiterException{
        if (this.mitarbeiter.size() > opportunities.size()) {
            ausschreibung.getBewerber().add(this.unternehmen);
            opportunities.add(ausschreibung);
        } else {
            throw new ZuWenigMitarbeiterException("Vertrieb");
        }
    }

    /**
     * Verkauf von Produkten: Lagerbestand verringern, Umsatz erhöhen
     * ruft vertragBrechen() auf, falls nicht genügend Produkte im Lager vorhanden sind
     */
    private  void produkteVerkaufen(){
        float umsatz = 0;
        int verkaufteProdukte = 0;
        for (Vertrag vertrag : accounts){
            boolean vertragErfüllt = false;
            int menge = vertrag.getProduktlinie().getMenge();
            String id = vertrag.getProduktlinie().getId();
            // Produkt im Lager finden:
            for (Produktlinie bestand : this.produktion.getLager()) {
                if (bestand.getId().equals(id)) {
                    // Prüfen, ob Menge ausreichend ist:
                    if (menge <= bestand.getMenge()) {
                        // Menge verringern, Umsatz addieren:
                        bestand.setMenge(bestand.getMenge() - menge);
                        umsatz += vertrag.getPreis() * vertrag.getProduktlinie().getMenge();
                        verkaufteProdukte += vertrag.getProduktlinie().getMenge();
                        vertragErfüllt = true;
                    } else {
                        // alle vorhandenen Produkte verkaufen:
                        umsatz += bestand.getMenge() * vertrag.getPreis();
                        verkaufteProdukte += bestand.getMenge();
                        this.produktion.getLager().remove(bestand);
                    }
                }
            }
            if (!vertragErfüllt){
                this.vertragBrechen(vertrag);
            }
        }
        this.verkaufteProdukte = verkaufteProdukte;
        try {
            this.kennzahlensammlung.getGuv().addUmsatz(umsatz);
            this.kennzahlensammlung.getBilanz().liquiditaetAnpassen(umsatz);
        } catch (BankruptException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vertragsbruch: Strafe zahlen (Liquidität verringern, Aufwendung verbuchen), Vertrag wird gekündigt (gelöscht)
     * @param vertrag, der nicht eingehalten wurde
     */
    private  void vertragBrechen(Vertrag vertrag){
        try {
            this.kennzahlensammlung.getBilanz().liquiditaetAnpassen(- vertrag.getStrafe());
            this.kennzahlensammlung.getGuv().addGeleisteterSchandsersatz(vertrag.getStrafe());
            this.accounts.remove(vertrag);
        } catch (BankruptException e){
            e.printStackTrace();
        }
    }

   @Override
    public  void update() {
        if (Game.getCalendar().get(Calendar.DAY_OF_MONTH) == Game.getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH)){
            this.produkteVerkaufen();
            for (Vertrag vertrag : this.accounts){
                if (Game.getCalendar().equals(vertrag.getEnd())){
                    this.accounts.remove(vertrag);
                }
            }
        }
    }


    // Getter und Setter:
    public Map<String, Integer> getAccountsAsMap(){
        Map<String, Integer> accountMap = new HashMap<String, Integer>();
        for (Vertrag vertrag : this.accounts){
            String produktID = vertrag.getProduktlinie().getId();
            if (accountMap.get(produktID) == null){
                accountMap.put(produktID, vertrag.getProduktlinie().getMenge());
            } else {
                int neueAnzahl = accountMap.get(produktID) + vertrag.getProduktlinie().getMenge();
                accountMap.put(produktID, neueAnzahl);
            }
        }
        return accountMap;
    }

    public int getVerkaufteProdukte() {
        return verkaufteProdukte;
    }

    public ArrayList<Ausschreibung> getOpportunities() {
        return opportunities;
    }

    public CopyOnWriteArrayList<Vertrag> getAccounts() {
        return accounts;
    }


}
