package Unternehmung;

public class Mitarbeiter {

	private String name;
	private String vorname;
	private String adresse;
	private String imagelink;
	private Abteilung department;
	private char gender;
	private int gehalt; // Jahresgehalt
	
	
	
	public String getName() {
		return name;
	}
	
	public Mitarbeiter(String name, String vorname, String adresse, String imagelink, char gender, int gehalt) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.adresse = adresse;
		this.imagelink = imagelink;
		this.gender = gender;
		this.gehalt = gehalt;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getImagelink() {
		return imagelink;
	}
	public void setImagelink(String imagelink) {
		this.imagelink = imagelink;
	}
	public Abteilung getDepartment() {
		return department;
	}
	public void setDepartment(Abteilung department) {
		this.department = department;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
    public int getGehalt() {
        return gehalt;
    }
    public void setGehalt(int gehalt) {
        this.gehalt = gehalt;
    }
}
