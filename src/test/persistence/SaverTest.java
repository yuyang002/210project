package persistence;

import model.Account;
import model.GiftCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SaverTest {
    Account account;

    @BeforeEach
    public void setup() {
        account = new Account("wyy", 0);
        account.clearFile();
    }

    @Test
    public void testSaveLoad() {
        GiftCard familyCard = new GiftCard("Family", 1000);
        GiftCard springCard = new GiftCard("Spring", 400);
        account.addGiftCard(familyCard);
        account.addGiftCard(springCard);
        Saver.save(account);
        try {
            List<String> lines = Files.readAllLines(Paths.get(Account.PATH));
            String actualString = lines.get(0); // there is only 1 line in the file
            String expectedString = "[{\"balance\":1000,\"name\":\"Family\"},{\"balance\":400,\"name\":\"Spring\"}]";
            assertEquals(expectedString, actualString);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
        account = new Account("wyy", 0);
        Loader.load(account);
        assertTrue(account.getGiftCards().get(0).giftCardEqual(familyCard));
        assertTrue(account.getGiftCards().get(1).giftCardEqual(springCard));
    }

}

