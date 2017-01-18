package Unternehmung.Abteilungen;

import Exceptions.BankruptException;
import Rules.Game;
import Unternehmung.Abteilung;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Kredit;

import java.util.ArrayList;

/**
 * Klasse, die die Abteilung Finanzen repräsentiert
 * Created by lucadommes on 29.12.2016.
 */
public class Finanzen extends Abteilung {

    ArrayList<Kredit> kredite = new ArrayList<Kredit>();

    /**
     * Konstruktor, zum Erstellen der Abteilung
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Kennzahlensammlung laufend fortzuschreiben / zu berechnen
     */
    public Finanzen(Kennzahlensammlung kennzahlensammlung) {
        super("Finanzen",kennzahlensammlung);
    }

    /**
     * berechnet den Zinssatz auf Basis des neuen Verschuldungsgrades (bis 200% möglich) und erstellt ein Kreditobjekt, falls Kreditwürdigkeit gegeben ist
     * @param hoehe vom Spieler gewählt
     * @param laufzeit vom Spieler gewählt
     */
    public void kreditAufnehmen(int hoehe, int laufzeit){
        double neuerVerschuldungsgrad = (kennzahlensammlung.getBilanz().getFremdkapital() + hoehe) /
                kennzahlensammlung.getBilanz().getEigenkapital();
        double zinssatz = 0;
        if (neuerVerschuldungsgrad <= 0.25){
            zinssatz = 0.02;
        } else if (neuerVerschuldungsgrad <= 0.5){
            zinssatz = 0.025;
        } else if (neuerVerschuldungsgrad <= 0.75){
            zinssatz = 0.03;
        } else if (neuerVerschuldungsgrad <= 1){
            zinssatz = 0.035;
        } else if (neuerVerschuldungsgrad <= 1.25){
            zinssatz = 0.04;
        } else if (neuerVerschuldungsgrad <= 1.5){
            zinssatz = 0.045;
        } else if (neuerVerschuldungsgrad <= 1.75){
            zinssatz = 0.05;
        } else if (neuerVerschuldungsgrad <= 2){
            zinssatz = 0.06;
        } else {
            System.out.println("Zu hoch verschuldet, es ist keine Kreditaufnahme mehr möglich!");
            return;
        }
        Kredit kredit = new Kredit(hoehe, laufzeit, zinssatz);
        kredite.add(kredit);
    }

    /**
     * rechnet für jeden Kredit einmal jährlich Zinsen und Tilgung ab
     */
    @Override
    public void update(){
        double tilgung = 0;
        double zinsen = 0;
        for (Kredit kredit : kredite){
            if (Game.getTime() == kredit.getBeginn() + 360) { // einmal jährlich
                kredit.update();
                tilgung += kredit.getTilgung();
                zinsen += kredit.getZinsen();
                if (kredit.getRestwert() <= 0){
                    kredite.remove(kredit);
                }
            }
        }
        try {
            // Zinsen an GuV weitergeben:
            this.kennzahlensammlung.getGuv().addZinsaufwendungen((float) zinsen);
            // liquide Mittel und FK anpassen:
            this.kennzahlensammlung.liquiditätAnpassen((float) -(tilgung + zinsen));
            this.kennzahlensammlung.getBilanz().setFremdkapital(this.kennzahlensammlung.getBilanz().getFremdkapital() - (float) tilgung);
        } catch (BankruptException e){
            e.printStackTrace();
        }
    }

}
