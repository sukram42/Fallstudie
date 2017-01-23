package Exceptions;

/**
 * Exception, die geworfen wird, wenn mehr Produkte fertiggestellt werden, als Lagerplatz vorhanden ist
 * Created by lucadommes on 23.01.2017.
 */
public class LagerVollException extends Exception{

    public LagerVollException(){
        super("Es ist nicht genügend Lagerplatz vorhanden!");
    }

}
