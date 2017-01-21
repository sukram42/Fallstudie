package Unternehmung.Abteilungen;

import Exceptions.ZuWenigMitarbeiterException;
import Rules.Game;
import Unternehmung.*;

import java.util.*;

/**
 * Abteilung, die für Vertrieb zuständig ist
 * Created by lucadommes on 30.12.2016.
 */
public class Vertrieb extends Abteilung {

    private Map<Integer, Ausschreibung> opportunities;
    private List<Vertrag> accounts;


    public Vertrieb(Kennzahlensammlung kennzahlensammlung) {
        super("Vertrieb",kennzahlensammlung);
        opportunities = new HashMap<>();
        accounts = new ArrayList<>();
    }

    /**
     * speichert ein Ausschreibungsobjekt mit dem index, in dem es in Game.ausschreibungen gespeichert ist ab (sofern genug Mitarbeiter vorhanden -> ein Mitarbeiter pro Bewerbung nötig)
     * @param index unter dem die Ausschreibung in der ArrayList ausschreibungen in der Klasse Game abgelegt ist
     */
    public void bewerben(int index) throws ZuWenigMitarbeiterException{
        if (this.mitarbeiter.size() > opportunities.size()) {
            ArrayList<Ausschreibung> ausschreibungen = Game.getAusschreibungen();
            opportunities.put(index, ausschreibungen.get(index));
        } else {
            throw new ZuWenigMitarbeiterException("Vertrieb");
        }
    }

    /**
     * wird aufgerufen von Game.updateAusschreibungen, wenn das Unternehmen den Zuschlag bekommt
     * @param index unter dem die Ausschreibung in der Game.ausschreibungen und in opportunities abgelegt ist
     */
    public void zuschlagBekommen(int index){
        accounts.add(opportunities.get(index).getVertrag());
    }

   @Override
    public void update() {

    }


    // Getter und Setter:
    public Map<Integer, Ausschreibung> getOpportunities() {
        return opportunities;
    }

    public List<Vertrag> getAccounts() {
        return accounts;
    }

}