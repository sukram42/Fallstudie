package Unternehmung;

/**
 * eine Halle kann eintweder eine Lager- oder eine Produktionshalle sein
 * Created by lucadommes on 11.01.2017.
 */
public class Halle {

    private String halle; // Art der Halle (Lager- oder Produktionshalle)
    private int größe; // 1, 2 oder 3
    private int kapazität; // abhängig von der Größe (Maschinenkapazität oder Lagerfläche)
    private float preis; // Kaufpreis, abhängig von der Größe

    public Halle(String halle, int größe) {
        this.halle = halle;
        this.größe = größe;
        findPreisUndKapazität(halle, größe);
    }

    private void findPreisUndKapazität(String halle, int größe){
        switch (halle){
            // TODO realistische / zum Spiel passende Zahlen einsetzen
            case "Lagerhalle": // wie viele Produkte passen in das Lager?
                switch (größe){
                    case 1:
                        this.preis = 10000;
                        this.kapazität = 2000;
                        break;
                    case 2:
                        this.preis = 20000;
                        this.kapazität = 5000;
                        break;
                    case 3:
                        this.preis = 38000;
                        this.kapazität = 10000;
                        break;
                }
                break;
            case "Produktionshalle": // wie viele Maschinen passen in die Halle?
                switch (größe){
                    case 1:
                        this.preis = 30000;
                        this.kapazität = 20;
                        break;
                    case 2:
                        this.preis = 65000;
                        this.kapazität = 50;
                        break;
                    case 3:
                        this.preis = 120000;
                        this.kapazität = 100;
                        break;
                }
                break;
        }
    }

    // Getter und Setter:
    public int getGröße() {
        return größe;
    }

    public int getKapazität() {
        return kapazität;
    }

    public float getPreis() {
        return preis;
    }
}
