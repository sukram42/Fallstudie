package Exceptions;

/**
 * Exception die bei der Kreditaufnahme im Falle eines zu hohen Verschuldungsgrads geworfen wird
 * Created by lucadommes on 19.01.2017.
 */
public class ZuHochVerschuldetException extends Exception{

    public ZuHochVerschuldetException(){
        super("Zu hoch verschuldet, es ist keine Kreditaufnahme mehr möglich!");
    }

}
