package Unternehmung.Objekte;

import Rules.Game;

import java.util.Calendar;

/**
 * Marktforschungsprojekt
 * Created by lucadommes on 15.01.2017.
 */
public class Marktforschung {

    private int umfang; // drei verschieden Stufen
    private float kosten; // pro Tag, je nach umfang (werden pro timer count gezahlt)
    private float impact; // je nach umfang (wird am Ende der dauer verrechnet)
    private int noetigeMitarbeiter;
    private Calendar beginn;
    private Calendar end;

    public Marktforschung(int umfang) {
        this.beginn = (Calendar) Game.getCalendar().clone();
        this.umfang = umfang;
        setParamsByUmfang(umfang);
    }

    /**
     * @param umfang Auf Basis des Umfangs werden impact, kosten (und dauer) gesetzt
     */
    private void setParamsByUmfang(int umfang){
        float impact = 0;
        int kosten = 0;
        int dauer = 0;
        int noetigeMitarbeiter = 0;
        switch (umfang){
            case 1:
                impact = 0.05f;
                kosten = 50; // pro Tag -> 4500 insgesamt (sind bei jedem Umfang gleich, ein größerer Umfang lohnt sich dadurch, dass der impact am Ende größer ist)
                dauer = 90; // -> ein Quartal
                noetigeMitarbeiter = 1;
                break;
            case 2:
                impact = 0.12f;
                kosten = 50;
                dauer = 180;
                noetigeMitarbeiter = 3;
                break;
            case 3:
                impact = 0.30f;
                kosten = 50;
                dauer = 270;
                noetigeMitarbeiter = 6;
                break;
        }
        this.impact = impact;
        this.kosten = kosten;
        this.end = (Calendar) this.beginn.clone();
        this.end.add(Calendar.DAY_OF_MONTH, dauer);
        this.noetigeMitarbeiter = noetigeMitarbeiter;
    }


    // Getter und Setter:
    public int getUmfang() {
        return umfang;
    }

    public void setUmfang(int umfang) {
        this.umfang = umfang;
    }

    public float getKosten() {
        return kosten;
    }

    public void setKosten(float kosten) {
        this.kosten = kosten;
    }

    public float getImpact() {
        return impact;
    }

    public void setImpact(float impact) {
        this.impact = impact;
    }

    public int getNoetigeMitarbeiter() {
        return noetigeMitarbeiter;
    }

    public Calendar getBeginn() {
        return beginn;
    }

    public Calendar getEnd() {
        return end;
    }
}
