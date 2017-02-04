package Exceptions;

import Unternehmung.Unternehmen;

/**
 * Exception, die geworfen wird, wenn der Spieler seinen Zahlungsverpflichtungen nicht mehr gerecht werden kann
 * Created by lucadommes on 15.01.2017.
 */
public class BankruptException extends Exception{

    public BankruptException(Unternehmen company){
        super("GAME OVER: Das Unternehmen ist zahlungsunfähig!");
        company.getKennzahlensammlung().setBankrupt();
    }

}
