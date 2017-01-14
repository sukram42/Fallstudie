package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Abteilungen.SozialeProjekte.ZeitGeld;
import Unternehmung.Kennzahlensammlung;
import Unternehmung.Abteilungen.SozialeProjekte.SozialProjekt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Klasse, die eine Abteilung repräsentiert, die für soziale Leistungen verantwortlich ist
 * Created by lucadommes on 29.12.2016.
 */
public class SozialeLeistungen extends Abteilung {

    private final HR hr;
    public List<SozialProjekt> projekte = new ArrayList<>();


    private transient Kennzahlensammlung kennzahlensammlung;


    /**
     * Konstruktor, zum Erstellen der Abteilung für soziale Leistungen
     *
     * @param kennzahlensammlung Kennzahlenobjekt wird später benötigt, um Kennzahlensammlung laufend fortzuschreiben / zu berechnen
     */
    public SozialeLeistungen(Kennzahlensammlung kennzahlensammlung, HR hr) {
        super("Soziale Leistungen", kennzahlensammlung);
        initProjects();
        this.hr = hr;
    }

    public void initProjects() {
        projekte.add(new SozialProjekt("kantine", 10000, 100, 0.2f, hr));
        projekte.add(new SozialProjekt("wifi", 10000, 100, 0.2f, hr));
        projekte.add(new ZeitGeld("urlaubsgeld", (hr.getTotalGehalt() * 0.5f), 0.2f, Calendar.JULY, hr));
        projekte.add(new ZeitGeld("weihnachtsgeld", (hr.getTotalGehalt() * 0.3f), 0.3f, Calendar.DECEMBER, hr));
        projekte.add(new SozialProjekt("kindergarten", 10000, 100, 0.2f, hr));

        // TODO Mitarbeiterzufriedenheit erhöhen
    }

    /**
     * Bei jedem countersignal werden die AktuellenKosten berechnet.
     * Wenn ein Projekt aktiv ist so wird deren Aktuelle Kosten drauf gerechnet
     */
    @Override
    public void update() {

        for (SozialProjekt p : projekte) {
            aktKosten += p.isActive() ? p.getAktKosten() : 0;
            p.update();
            //Aktuelle Kosten wieder resetten
            p.resetAktKosten();
        }
    }
    public void startProjekt(String name)
    {
        getProjektByName(name);
    }

    private SozialProjekt getProjektByName(String name)
    {
        if(name!=null) {
            return  projekte.stream().filter(s -> name.equals(s.getName()))
                                     .findFirst().get();
        }else return null;
    }



}
