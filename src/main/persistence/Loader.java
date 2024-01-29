package persistence;

import model.Account;
import model.GiftCard;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// Loader class represents all the gift cards we added and saved before in our account

public class Loader {

    // EFFECTS: loads gift card list in the account and returns it,
    //          catches IOException if an error occurs loading data from file.
    //          If there is a line in files, we load each element in the line with their name, and balance,
    //          then each of them in gift card list.

    public static void load(Account account) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(Account.PATH));
            if (lines.size() == 0) {
                return;
            }
            String jsonArrayString = lines.get(0); // there is only 1 line in the file
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                double balance = jsonObject.getDouble("balance");
                account.addGiftCard(new GiftCard(name, balance));
            }
        } catch (IOException e) {
            System.out.println("Encountered IOException while loading gift card list in the account.");
        }
    }
}

