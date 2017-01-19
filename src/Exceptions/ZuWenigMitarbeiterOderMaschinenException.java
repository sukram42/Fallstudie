package Exceptions;

/**
 * Exception, die ausgelöst wird, wenn für einen Produktionsauftrag nicht genügend Mitarbeiter und/oder Maschinen (für dieses Produkt) vorhanden sind
 * Created by lucadommes on 19.01.2017.
 */
public class ZuWenigMitarbeiterOderMaschinenException extends Exception {

    public ZuWenigMitarbeiterOderMaschinenException(String produkt) {
        super("Es sind nicht genügend Mitarbeiter und/oder Maschinen zur Produktion von \"" + produkt + "\" vorhanden!");
    }

}
