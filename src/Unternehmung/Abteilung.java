package Unternehmung;

import Exceptions.ZuWenigMitarbeiterException;
import Unternehmung.Kennzahlen.Kennzahlensammlung;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

public class Abteilung {

    protected transient ArrayList<Mitarbeiter> mitarbeiter = new ArrayList<>();
    protected transient Kennzahlensammlung kennzahlensammlung;
    protected transient float aktKosten = 0;
    private String name;

    public Abteilung(String name, Kennzahlensammlung kennzahlensammlung) {
        this.kennzahlensammlung = kennzahlensammlung;
        this.name = name;
    }

    public void addMitarbeiter(int anzahl, int gehalt) throws ZuWenigMitarbeiterException {

        if (this.name.equals("Human-Resources") || this.kennzahlensammlung.getMaxNeueMitarbeiter() >= anzahl) { // genügend HR-Mitarbeiter? (einer zuständig für 10 (Nicht-HR)Mitarbeiter)

            String erg = "";
            String content;

//		if (this.kennzahlensammlung.liquiditätVorhanden(gehalt * anzahl, "gehälter")) {

            try {
                URL url = new URL("https://randomuser.me/api/?results=" + anzahl + "&inc=name,picture,gender,location");
                URLConnection connection = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((content = reader.readLine()) != null) {
                    erg += content;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, Object> myMap = new Gson().fromJson(erg, type);

            JsonObject object = new Gson().fromJson(erg, JsonObject.class);
            JsonArray array = object.getAsJsonArray("results");

            for (int i = 0; i < array.size(); i++) {
                JsonObject name = (JsonObject) array.get(i).getAsJsonObject().get("name");
                JsonObject address = (JsonObject) array.get(i).getAsJsonObject().get("location");
                JsonObject picture = (JsonObject) array.get(i).getAsJsonObject().get("picture");

                Mitarbeiter m = new Mitarbeiter(name.get("last").getAsString(), name.get("first").getAsString(),
                        address.get("street").getAsString() + " " + address.get("city").getAsString(),
                        picture.get("medium").getAsString(), 'w', gehalt);
                m.setDepartment(this);

                mitarbeiter.add(m);

                if (this.name.equals("Human-Resources")) {
                    this.kennzahlensammlung.setMaxNeueMitarbeiter(this.kennzahlensammlung.getMaxNeueMitarbeiter() + 9); // ein HR-Mitarbeiter ist für 10 Mitarbeiter verantwortlich
                } else {
                    this.kennzahlensammlung.setMaxNeueMitarbeiter(this.kennzahlensammlung.getMaxNeueMitarbeiter() - 1);
                }

            }
//		}else{
//			System.out.println("Nicht genügend Liquidität vorhanden!");
//		}
            this.kennzahlensammlung.berechnen();

        } else {
            throw new ZuWenigMitarbeiterException("HR");
        }
    }


    public ArrayList<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public int getMitarbeiterAnzahl() {
        return mitarbeiter.size();
    }


    public void update() {
    }

    /**
     * @return Gibt die aktuellen Kosten der Abteilung zurück;
     */
    public float getKosten() {
        return aktKosten;
    }

    /**
     * Berechnet die Mitarbeiterkosten
     *
     * @return Mitarbeiterkosten
     */
    public float getMitarbeiterKosten() {
        float mitarbeiterKosten = 0;
        for (Mitarbeiter m : mitarbeiter) {
            mitarbeiterKosten += m.getGehalt();
        }

        return mitarbeiterKosten;
    }

    public String getName() {
        return name;
    }
}