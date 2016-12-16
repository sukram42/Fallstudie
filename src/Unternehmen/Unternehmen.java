package Unternehmen;

public class Unternehmen {
	private String passwort;
	private String name;
	private float fremdkapital;
	private float eigenkapital;
	private HR hr = new HR();
	
	public Unternehmen( String name,String passwort, float fremdkapital, float eigenkapital) {
		super();
		this.passwort = passwort;
		this.name = name;
		this.fremdkapital = fremdkapital;
		this.eigenkapital = eigenkapital;
	}

	
	public Unternehmen(String name, String passwort, float fremdkapital) {
		this(name,passwort,fremdkapital,10000);
	}

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
	
	public Abteilung getDepartment(String department)
	{
		if("HR".equals(department))return hr;
		return null;
	}
	
}
