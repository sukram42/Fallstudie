package Rules;

import Unternehmung.Unternehmen;

/**
 * Hilfsklasse, um z.B. den Gewinner eines Spiels festzulegen
 * Created by lucadommes on 08.01.2017.
 */
public class Game {

    private int spieler;
    private Unternehmen[] companies;
    private Unternehmen unternehmen1;
    private Unternehmen unternehmen2;
    private Unternehmen unternehmen3;
    private Unternehmen unternehmen4;

    /**
     * Konstruktor für ein Spiel mit 2 Spielern
     * @param unternehmen1
     * @param unternehmen2
     */
    public Game(Unternehmen unternehmen1, Unternehmen unternehmen2) {
        this.spieler = 2;
        this.unternehmen1 = unternehmen1;
        this.unternehmen2 = unternehmen2;
        companies = new Unternehmen[spieler];
        companies[0] = unternehmen1;
        companies[1] = unternehmen2;
    }

    public Unternehmen gewinnerErmitteln(){
        int u1 = 0; // Punktekonto von unternehmen1
        int u2 = 0; // Punktekonto von unternehmen2
        Unternehmen gewinner = null;
        if(this.spieler == 2){
            // Gewinn vergleichen:
            if(unternehmen1.getKennzahlen().getGewinn() > unternehmen2.getKennzahlen().getGewinn()){
                u1++;
            }else if (unternehmen1.getKennzahlen().getGewinn() < unternehmen2.getKennzahlen().getGewinn()) {
                u2++;
            }
            // Bekanntheitsgrad vergleichen:
            if(unternehmen1.getKennzahlen().getBekanntheitsgrad() > unternehmen2.getKennzahlen().getBekanntheitsgrad()){
                u1++;
            } else if(unternehmen1.getKennzahlen().getBekanntheitsgrad() > unternehmen2.getKennzahlen().getBekanntheitsgrad()){
                u2++;
            }
            // Verkaufsrate vergleichen:
            if (unternehmen1.getKennzahlen().getVerkaufsrate() > unternehmen2.getKennzahlen().getVerkaufsrate()){
                u1++;
            }else if (unternehmen1.getKennzahlen().getVerkaufsrate() < unternehmen2.getKennzahlen().getVerkaufsrate()){
                u2++;
            }
            // Punkte vergleichen um Gewinner zu ermitteln:
            if(u1 > u2){
                gewinner = unternehmen1;
            }else if(u1 < u2){
                gewinner = unternehmen2;
            }
        }
        return gewinner;
    }

}






















