package Exceptions;

import Unternehmung.Unternehmen;

import java.util.HashMap;
import java.util.Map;

/**
 * wird geworefen, wenn ein Unternehmen sich beworben hat, den Zuschlag jedoch nicht bekommen hat
 * Created by lucadommes on 11.02.2017.
 */
public class ZuschlagNichtBekommenException extends Exception{

    private static Map<Unternehmen,Integer> count = new HashMap<>();

    public ZuschlagNichtBekommenException(Unternehmen unternehmen) {
        count.put(unternehmen,1);
    }

    public static int getCount(Unternehmen unternehmen)
    {
        if(count.containsKey(unternehmen))
            return count.remove(unternehmen);
        else return 0;
    }

}
