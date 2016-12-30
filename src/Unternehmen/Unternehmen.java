package Unternehmen;

public class Unternehmen {
	// Unternehmenseigenschaften
	private String passwort;
	private String name;
	private float fremdkapital;
	private float eigenkapital;
	// Abteilungen:
	private HR hr = new HR();
	private Marketing marketing = new Marketing();
	private Finanzen finanzen = new Finanzen();
	private FuE FuE = new FuE();
	private Produktion produktion = new Produktion();
	private SozialeLeistungen sozialeLeistungen = new SozialeLeistungen();
	private Vertrieb vertrieb = new Vertrieb();
	// Kennzahlen:
	private Kennzahl bekanntheitsgrad = new Kennzahl();
	private Kennzahl mitarbeiterzufriedenheit = new Kennzahl();
	private Kennzahl kundenzufriedenheit = new Kennzahl();

	
	public Unternehmen( String name,String passwort, float fremdkapital, float eigenkapital) {
		super();
		this.passwort = passwort;
		this.name = name;
		this.fremdkapital = fremdkapital;
		this.eigenkapital = eigenkapital;
		//Kennzahl Bekanntheitsgrad = new Kennzahl();
	}

	
	public Unternehmen(String name, String passwort, float fremdkapital) {
		this(name,passwort,fremdkapital,10000);
	}

	// Getter und Setter:
	// Unternehmenseigenschaften:
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getFremdkapital() {
		return fremdkapital;
	}
	public void setFremdkapital(float fremdkapital) {
		this.fremdkapital = fremdkapital;
	}
	public float getEigenkapital() {
		return eigenkapital;
	}
	public void setEigenkapital(float eigenkapital) {
		this.eigenkapital = eigenkapital;
	}
	public Abteilung getDepartment(String department) {
		if("HR".equalsIgnoreCase(department))return hr; // TODO durch die "Abteilungs-Getter" ist diese Funktion überflüssig, oder?
		return null;
	}
	// Abteilungen:
	// TODO Setter überflüssig?!
    public Marketing getMarketing() {
        return marketing;
    }
    public void setMarketing(Marketing marketing) {
        this.marketing = marketing;
    }
	public HR getHr() {
		return hr;
	}
	public void setHr(HR hr) {
		this.hr = hr;
	}
	public Finanzen getFinanzen() {
		return finanzen;
	}
	public void setFinanzen(Finanzen finanzen) {
		this.finanzen = finanzen;
	}
	public FuE getFuE() {
		return FuE;
	}
	public void setFuE(FuE fuE) {
		FuE = fuE;
	}
	public Produktion getProduktion() {
		return produktion;
	}
	public void setProduktion(Produktion produktion) {
		this.produktion = produktion;
	}
	public SozialeLeistungen getSozialeLeistungen() {
		return sozialeLeistungen;
	}
	public void setSozialeLeistungen(SozialeLeistungen sozialeLeistungen) {
		this.sozialeLeistungen = sozialeLeistungen;
	}
	public Vertrieb getVertrieb() {
		return vertrieb;
	}
	public void setVertrieb(Vertrieb vertrieb) {
		this.vertrieb = vertrieb;
	}

	// Kennzahlen:
	// TODO werden Setter überhaupt benötigt, wird deren Aufgabe nicht von Kennzahl.wertBerechnen() übernommen?
	public Kennzahl getBekanntheitsgrad() {
        return bekanntheitsgrad;
    }
    public void setBekanntheitsgrad(Kennzahl bekanntheitsgrad) {
        this.bekanntheitsgrad = bekanntheitsgrad;
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
}
