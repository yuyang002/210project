package persistence;

import model.Account;
import model.GiftCard;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

// Saver class represents all the giftCards we added in our account

public class Saver {

    //EFFECTS: save all giftCards we added in the gift card list in the account,
    // and catch IOExpection if an error occurs. We convert each gift card in the current account
    // to JSONObject with its name and balance, then put all of them in JSONArray

    public static void save(Account account) {
        try {
            PrintWriter writer = new PrintWriter(Account.PATH,"UTF-8");
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            for (GiftCard gc : account.getGiftCards()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", gc.getName());
                jsonObject.put("balance", gc.getBalance());
                jsonObjects.add(jsonObject);
            }
            JSONArray jsonArray = new JSONArray(jsonObjects);
            writer.println(jsonArray.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Encountered IOException while saving gift card list in the account.");
        }
    }
}

