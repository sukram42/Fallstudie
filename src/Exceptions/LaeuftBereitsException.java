package Exceptions;

/**
 * Exception, die geworfen wird, wenn eine Aktion ausgeführt wird, die bereits läuft und nur einmal zu einer Zeit laufen kann
 * Created by lucadommes on 23.01.2017.
 */
public class LaeuftBereitsException extends Exception{

    public LaeuftBereitsException(String aktion){
        super("Es kann nur eine " + aktion + " zu einer Zeit durchgeführt werden!");
    }

}
