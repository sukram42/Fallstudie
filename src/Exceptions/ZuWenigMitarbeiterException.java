package Exceptions;

/**
 * Exception, die ausgelöst wird, wenn für eine Aktion zu wenige Mitarbeiter in der entsprechenden Abteilung vorhanden sind
 * Created by lucadommes on 19.01.2017.
 */
public class ZuWenigMitarbeiterException extends Exception{

    public  ZuWenigMitarbeiterException(String abteilung){
        super("In der Abteilung " + abteilung + " sind nicht genügend Mitarbeiter für diese Aktion vorhanden!");
    }

}
