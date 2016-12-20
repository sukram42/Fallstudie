package Unternehmen;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

public class Abteilung {

	ArrayList<Mitarbeiter> mitarbeiter = new ArrayList<>();

	public void addMitarbeiter(int anzahl) {

		String erg = "";
		String content;

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

		// preparing MongoDB for insertion:
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("Fallstudie");
        MongoCollection<Document> coll = db.getCollection("mitarbeiter");

		for (int i = 0; i < array.size(); i++) {
			JsonObject name = (JsonObject) array.get(i).getAsJsonObject().get("name");
			JsonObject address = (JsonObject) array.get(i).getAsJsonObject().get("location");
			JsonObject picture = (JsonObject) array.get(i).getAsJsonObject().get("picture");

			Mitarbeiter m = new Mitarbeiter(name.get("last").getAsString(), name.get("first").getAsString(),
					address.get("street").getAsString() + " " + address.get("city").getAsString(),
					picture.get("medium").getAsString(), 'w');
			System.out.println(m.getVorname() + " " + m.getName());

			// inserting m into the MongoDB:
            Document doc = new Document("name", m.getName())
                    .append("vorname", m.getVorname())
                    .append("adresse", m.getAdresse())
                    .append("imagelink", m.getImagelink())
                    .append("gender", m.getGender());
            coll.insertOne(doc);

			mitarbeiter.add(m);

		}
	}

	public ArrayList<Mitarbeiter> getMitarbeiter() {
		return mitarbeiter;
	}
}
