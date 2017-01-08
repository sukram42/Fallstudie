package Unternehmung;

/**
 * Diese Klasse beinhaltet alle Kennzahlen eines Unternehmens
 * diese werden unterschieden in "weiche" und faktische Kennzahlen
 * zur Berechnung weicher Kennzahlen wird die Klasse "Kennzahl" genutzt, faktische Kennzahlen werden in double-Werten gespeichert
 * Created by lucadommes on 02.01.2017.
 */
public class Kennzahlen {

    // "weiche" Kennzahlen:
    //private Kennzahl bekanntheitsgrad = new Kennzahl(); // TODO als Kennzahl (statt double) implementieren?!
    private Kennzahl mitarbeiterzufriedenheit = new Kennzahl();
    private Kennzahl kundenzufriedenheit = new Kennzahl();
    private Kennzahl image = new Kennzahl(); // soll sich aus Mitarbeiterzufriedenheit, Reklamationsrate und Kundenzufriedenheit berechnen

    // faktische Kennzahlen:
    private double marktanteil;
    private double umsatz; // wird laufend fortgeschrieben (siehe unten addUmsatz())
    private double herstellkosten; // wird laufend fortgeschrieben (siehe unten addHerstellkosten())
    private double sonstigeKosten; // wird laufend fortgeschrieben (siehe unten addSonstigeKosten)
    private int gehälter; // wird laufend fortgeschrieben (siehe unten addGehälter)
            // TODO Fortzahlung der Gehälter im nächsten Geschäftsjahr implementieren
    private double gewinn; // wird bei Änderungen von Umsatz oder Kosten automatisch aktualisiert (siehe unten gewinnBerechnen())
    private double ausschussrate;
    private double reklamationsrate;
    private double fremdkapital;
    private double eigenkapital;
    private double bekanntheitsgrad;
    private double verkaufsrate; // Wahrscheinlichkeit, alle seine Produkte zu verkaufen (abhängig von Maßnahmen und zufällige Ereignisse wie z.B. Konjunktur, Werbekampagnen etc.)

    /**
     * Konstruktor zum Erstellen einen Kennzahlenobjekts eines Unternehmens (wird im Unternehmenskonstruktor aufgerufen)
     * @param eigenkapital muss bei Gründung des Unternehmens definiert werden
     * @param fremdkapital muss bei Gründung des Unternehmens definiert werden
     */
    public Kennzahlen(double eigenkapital, double fremdkapital) {
        // TODO alle Defaultwerte definieren (zumindest solche, die nicht 0 sein sollen)
        this.eigenkapital = eigenkapital;
        this.fremdkapital = fremdkapital;
        this.verkaufsrate = 0.2;
    }

    // Berechnungen:
    /**
     * Methode, die den Gewinn (Jahresüberschuss) berechnet. Wird von addX() ausgeführt (siehe unten)
     */
    public void gewinnBerechnen(){
        this.setGewinn(this.umsatz - (this.herstellkosten + this.sonstigeKosten + this.gehälter));
    }

    /**
     * Methode, die die Verkaufsrate berechnet (beeinflusst von Bekanntheitsgrad, Image, Kundenzufriedenheit, Mitarbeiterzufriedenheit, ...)
     * // TODO weitere Kennzahlen mit einrechnen
     */
    public void verkaufsrateBerechnen(){
        double verkaufsrate = this.getBekanntheitsgrad();
        if(verkaufsrate >= 1){
            this.setVerkaufsrate(1);
        }else {
            this.setVerkaufsrate(verkaufsrate);
        }
    }


    // laufende Fortschreibung von Kennzahlen:
    /**
     * Funktion, die Herstellkosten fortschreibt. Wird aufgerufen von Produktion.produzieren()
     * @param kosten gesamte Herstellkosten der Neuproduktion, sprich Herstellungskosten pro Stück * Menge
     */
    public void addHerstellkosten(double kosten){
        this.setHerstellkosten(this.herstellkosten + kosten);
        this.gewinnBerechnen();
    }

    /**
     * Funktion, die sonstigeKosten fortschreibt. Muss von allen Funktion aufgerufen werden, von denen "Geld ausgegeben wird"
     * @param kosten Kosten z.B. einer Werbekampagne
     */
    public void addSonstigeKosten(double kosten){
        this.setSonstigeKosten(this.herstellkosten + kosten);
        this.gewinnBerechnen();
    }

    /**
     * Funktion, die Gehälter fortschreibt. Wird von Abteilung.addMitarbeiter() aufgerufen
     * @param gehalt = Anzahl neu eingestellter Mitarbeiter * Jahresgehalt pro Mitarbeiter
     */
    public void addGehälter(int gehalt){
        this.setGehälter(this.gehälter + gehalt);
        this.gewinnBerechnen();
    }

    /**
     * Funktion, die Umsatz fortschreibt. Wird von Vertrieb.verkaufen() aufgerufen
     * @param umsatz erwirtschafteter Umsatz, sprich Verkaufspreis * Menge
     */
    public void addUmsatz(double umsatz){
        this.setUmsatz(this.umsatz + umsatz);
        this.gewinnBerechnen();
    }


    // Getter und Setter:
    /*
    public Kennzahl getBekanntheitsgrad() {
        return bekanntheitsgrad;
    }

    public void setBekanntheitsgrad(Kennzahl bekanntheitsgrad) {
        this.bekanntheitsgrad = bekanntheitsgrad;
    }
    */

    public double getBekanntheitsgrad() {
        return bekanntheitsgrad;
    }

    public void setBekanntheitsgrad(double bekanntheitsgrad) {
        if (bekanntheitsgrad >= 1){
            this.bekanntheitsgrad = 1;
            this.verkaufsrateBerechnen();
        }else {
            this.bekanntheitsgrad = bekanntheitsgrad;
            this.verkaufsrateBerechnen();
        }
    }

    public Kennzahl getMitarbeiterzufriedenheit() {
        return mitarbeiterzufriedenheit;
    }

    public void setMitarbeiterzufriedenheit(Kennzahl mitarbeiterzufriedenheit) {
        this.mitarbeiterzufriedenheit = mitarbeiterzufriedenheit;
    }

    public Kennzahl getKundenzufriedenheit() {
        return kundenzufriedenheit;
    }

    public void setKundenzufriedenheit(Kennzahl kundenzufriedenheit) {
        this.kundenzufriedenheit = kundenzufriedenheit;
    }

    public Kennzahl getImage() {
        return image;
    }

    public void setImage(Kennzahl image) {
        this.image = image;
    }

    public double getMarktanteil() {
        return marktanteil;
    }

    public void setMarktanteil(double marktanteil) {
        this.marktanteil = marktanteil;
    }

    public double getUmsatz() {
        return umsatz;
    }

    public void setUmsatz(double umsatz) {
        this.umsatz = umsatz;
    }

    public double getHerstellkosten() {
        return herstellkosten;
    }

    public void setHerstellkosten(double herstellkosten) {
        this.herstellkosten = herstellkosten;
    }

    public double getSonstigeKosten() {
        return sonstigeKosten;
    }

    public void setSonstigeKosten(double sonstigeKosten) {
        this.sonstigeKosten = sonstigeKosten;
    }

    public int getGehälter() {
        return gehälter;
    }

    public void setGehälter(int gehälter) {
        this.gehälter = gehälter;
    }

    public double getGewinn() {
        return gewinn;
    }

    public void setGewinn(double gewinn) {
        this.gewinn = gewinn;
    }

    public double getAusschussrate() {
        return ausschussrate;
    }

    public void setAusschussrate(double ausschussrate) {
        this.ausschussrate = ausschussrate;
    }

    public double getReklamationsrate() {
        return reklamationsrate;
    }

    public void setReklamationsrate(double reklamationsrate) {
        this.reklamationsrate = reklamationsrate;
    }

    public double getFremdkapital() {
        return fremdkapital;
    }

    public void setFremdkapital(double fremdkapital) {
        this.fremdkapital = fremdkapital;
    }

    public double getEigenkapital() {
        return eigenkapital;
    }

    public void setEigenkapital(double eigenkapital) {
        this.eigenkapital = eigenkapital;
    }

    public double getVerkaufsrate() {
        return verkaufsrate;
    }

    public void setVerkaufsrate(double verkaufsrate) {
        if(verkaufsrate >= 1){
            this.verkaufsrate = 1;
        }else {
            this.verkaufsrate = verkaufsrate;
        }
    }
}
