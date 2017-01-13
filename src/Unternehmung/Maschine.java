package Unternehmung;

/**
 * repräsentiert eine Maschine
 * Created by lucadommes on 07.01.2017.
 */
public class Maschine {

    private int klasse; // etwa 1, 2, 3 -> unterschiedliche Kapazität, Anschaffungskosten, Halbwertszeit
    private int kapazität; // kann maximal so viele Produkte pro Monat produzieren. abhängig von klasse
    private int anschaffungskst; // abhängig von klasse
    private int energiekosten; // pro Monat, bei jeder Maschinenklasse gleich
    private double status; // sinkt jeden Monat um X, kann durch Reparaturen wieder hochgesetzt werden


    public Maschine(int klasse) {
        this.klasse = klasse;
        this.energiekosten = 500; // <- TODO realistischen / zum Spiel passenden Wert für Energiekosten einsetzen
        this.status = 1;
        findKapazitätUndAnschaffungskst(klasse);
    }

    /**
     * Methode zum reparieren einer Maschine (setzt status wieder auf 1)
     * @param currentStatus Ausgangsstatus der Maschine, um Kosten zu berechnen
     */
    public void reparieren(double currentStatus, Kennzahlensammlung kennzahlensammlung){
        this.status = 1;
        // TODO realistisch / zum Spiel passende Werte einsetzen
        if (currentStatus < 0.1){
            kennzahlensammlung.addSonstigeKosten(1400);
        } else if (currentStatus < 0.2){
            kennzahlensammlung.addHerstellkosten(1200);
        } else if (currentStatus < 0.3){
            kennzahlensammlung.addHerstellkosten(1000);
        } else if (currentStatus < 0.4){
            kennzahlensammlung.addHerstellkosten(900);
        } else if (currentStatus < 0.5){
            kennzahlensammlung.addHerstellkosten(800);
        } else if (currentStatus < 0.6){
            kennzahlensammlung.addHerstellkosten(700);
        } else if (currentStatus < 0.7){
            kennzahlensammlung.addHerstellkosten(600);
        } else if (currentStatus < 0.8){
            kennzahlensammlung.addHerstellkosten(500);
        } else if (currentStatus < 0.9){
            kennzahlensammlung.addHerstellkosten(400);
        } else if (currentStatus < 1){
            kennzahlensammlung.addHerstellkosten(300);
        }
    }

    private void findKapazitätUndAnschaffungskst(int klasse){
        switch (klasse){
            // TODO realistisch / zum Spiel passende Werte einsetzen
            case 1:
                this.kapazität = 300;
                this.anschaffungskst = 7500;
                break;
            case 2:
                this.kapazität = 600;
                this.anschaffungskst = 15000;
                break;
            case 3:
                this.kapazität = 1200;
                this.anschaffungskst = 30000;
                break;
        }
    }

    // Getter und Setter:
    public int getKlasse() {
        return klasse;
    }

    public int getKapazität() {
        return kapazität;
    }

    public int getAnschaffungskst() {
        return anschaffungskst;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }
}
