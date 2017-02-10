package Unternehmung.Objekte;

/**
 * eine Halle kann eintweder eine Lager- oder eine Produktionshalle sein
 * Created by lucadommes on 11.01.2017.
 */
public class Halle {

    private String halle; // Art der Halle (Lager- oder Produktionshalle)
    private int groesse; // 1, 2 oder 3
    private int kapazitaet; // abhängig von der Größe (Maschinenkapazität oder Lagerfläche)
    private float preis; // Kaufpreis, abhängig von der Größe

    public Halle(String halle, int groesse) {
        this.halle = halle;
        this.groesse = groesse;
        findPreisUndKapazitaet(halle, groesse);
    }

    private void findPreisUndKapazitaet(String halle, int groesse){
        switch (halle){
            case "Lagerhalle": // wie viele Produkte passen in das Lager?
                switch (groesse){
                    case 1:
                        this.preis = 10000;
                        this.kapazitaet = 2000;
                        break;
                    case 2:
                        this.preis = 20000;
                        this.kapazitaet = 5000;
                        break;
                    case 3:
                        this.preis = 38000;
                        this.kapazitaet = 10000;
                        break;
                }
                break;
            case "Produktionshalle": // wie viele Maschinen passen in die Halle?
                switch (groesse){
                    case 1:
                        this.preis = 30000;
                        this.kapazitaet = 20;
                        break;
                    case 2:
                        this.preis = 65000;
                        this.kapazitaet = 50;
                        break;
                    case 3:
                        this.preis = 120000;
                        this.kapazitaet = 100;
                        break;
                }
                break;
        }
    }

    // Getter und Setter:
    public int getGroesse() {
        return groesse;
    }

    public int getKapazitaet() {
        return kapazitaet;
    }

    public float getPreis() {
        return preis;
    }
}
