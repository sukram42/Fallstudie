package Unternehmung;

/**
 * Marktforschungsprojekt
 * Created by lucadommes on 15.01.2017.
 */
public class Marktforschung {

    int umfang; // drei verschieden Stufen
    float kosten; // pro Tag, je nach umfang (werden pro timer count gezahlt)
    double impact; // je nach umfang (wird am Ende der dauer verrechnet)
    int dauer; // ja nach umfang

    public Marktforschung(int umfang) {
        this.umfang = umfang;
        setParamsByUmfang(umfang);
    }

    /**
     * @param umfang Auf Basis des Umfangs werden impact, kosten (und dauer) gesetzt
     */
    private void setParamsByUmfang(int umfang){
        switch (umfang){
            case 1:
                this.impact = 0.05;
                this.kosten = 50; // pro Tag -> 4500 insgesamt (sind bei jedem Umfang gleich, ein größerer Umfang lohnt sich dadurch, dass der impact am Ende größer ist)
                this.dauer = 90; // -> ein Quartal
                break;
            case 2:
                this.impact = 0.12;
                this.kosten = 50;
                this.dauer = 180;
                break;
            case 3:
                this.impact = 0.30;
                this.kosten = 50;
                this.dauer = 270;
                break;
        }
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

    public double getImpact() {
        return impact;
    }

    public void setImpact(double impact) {
        this.impact = impact;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }
}
