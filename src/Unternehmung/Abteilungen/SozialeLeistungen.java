package Unternehmung.Abteilungen;

import Unternehmung.Abteilung;
import Unternehmung.Abteilungen.SozialeProjekte.SozialProjekt;
import Unternehmung.Abteilungen.SozialeProjekte.ZeitGeld;
import Unternehmung.Unternehmen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Klasse, die eine Abteilung repräsentiert, die für soziale Leistungen verantwortlich ist
 * Created by lucadommes on 29.12.2016.
 */
public class SozialeLeistungen extends Abteilung {

    private transient Unternehmen unternehmen;
    public List<SozialProjekt> projekte = new ArrayList<>();

    /**
     * Konstruktor, zum Erstellen der Abteilung für soziale Leistungen
     *
     * @param unternehmen Unternehmen als Informationsquelle.
     * */
    public SozialeLeistungen(Unternehmen unternehmen) {
        super("Soziale Leistungen", unternehmen.getKennzahlensammlung());
        this.unternehmen = unternehmen;
        initProjects();
    }

    public void initProjects() {
        HR hr = (HR)unternehmen.getAbteilung("hr");
        projekte.add(new SozialProjekt("kantine", 10000, 100, 0.2f,unternehmen ));
        projekte.add(new SozialProjekt("wifi", 10000, 100, 0.2f, unternehmen));
        projekte.add(new ZeitGeld("urlaubsgeld", (hr.getTotalGehalt() * 0.5f), 0.2f, Calendar.JULY, unternehmen));
        projekte.add(new ZeitGeld("weihnachtsgeld", (hr.getTotalGehalt() * 0.3f), 0.3f, Calendar.DECEMBER, unternehmen));
        projekte.add(new SozialProjekt("kindergarten", 10000, 100, 0.2f, unternehmen));
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
        getProjektByName(name).start();
    }
    public void stopProjekt(String name) {
        getProjektByName(name).stop();
    }

   public void changeProjectActivity(String name)
   {
       SozialProjekt sp =getProjektByName(name);
       if(sp.isActive())
       {
           stopProjekt(name);
       }else
       {
           startProjekt(name);
       }
   }


    public List<SozialProjekt> getProjects()
    {
        return projekte;
    }




    private SozialProjekt getProjektByName(String name)
    {
        if(name!=null) {
            return  projekte.stream().filter(s -> name.equals(s.getName()))
                                     .findFirst().get();
        }else return null;
    }




}
