package Unternehmung;

/**
 * Marketingkampagne zum Steigern des Bekanntheitsgrads
 * Created by lucadommes on 15.01.2017.
 */
public class Marketingkampagne {

    String art; // Social Media, Print, Radio oder TV
    double impact; // Einfluss auf Bekanntheitsgrad (pro Tag / timer count)
    float kosten; // pro Tag (timer count)
    int laufzeit; // in Tagen (timer counts)

    public Marketingkampagne(String art, int laufzeit) {
        this.art = art;
        this.laufzeit = laufzeit;
        setKostenUndImpactByArt(art);
    }

    /**
     * @param art Auf Basis der Art der Marketingkampagne werden impact und kosten gesetzt
     */
    private void setKostenUndImpactByArt(String art){
        switch (art){
            case "Social Media":
                this.impact = 0.001;
                this.kosten = 10;
                break;
            case "Print":
                this.impact = 0.002;
                this.kosten = 50;
                break;
            case "Radio":
                this.impact = 0.004;
                this.kosten = 100;
                break;
            case "TV":
                this.impact = 0.02;
                this.kosten = 500;
                break;
        }
    }


    // Getter and Setter:
    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public double getImpact() {
        return impact;
    }

    public void setImpact(double impact) {
        this.impact = impact;
    }

    public float getKosten() {
        return kosten;
    }

    public void setKosten(float kosten) {
        this.kosten = kosten;
    }

    public int getLaufzeit() {
        return laufzeit;
    }

    public void setLaufzeit(int laufzeit) {
        this.laufzeit = laufzeit;
    }
}
