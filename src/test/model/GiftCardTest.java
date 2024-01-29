package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GiftCardTest {
    private GiftCard testGiftCard;
    private GiftCard testGiftCard1;
    private GiftCard testGiftCard2;
    private GiftCard testGiftCard3;

    @BeforeEach
    void runBefore() {
        testGiftCard = new GiftCard("New Year", 100.0);
        testGiftCard1 = new GiftCard("New Year", 100.0);
        testGiftCard2 = new GiftCard("New Year", 200.0);
        testGiftCard3 = new GiftCard("Family", 100.0);
    }

    @Test
    void testConstructor() {
        assertEquals("New Year", testGiftCard.getName());
        assertEquals(100.0, testGiftCard.getBalance());
    }

    @Test
    void testPayBill() {
        testGiftCard.payBill(10.0);
        assertEquals(90.0, testGiftCard.getBalance());
    }

    @Test
    void testPayBillMultipleTimes() {
        testGiftCard.payBill(10.0);
        assertEquals(90.0, testGiftCard.getBalance());
        testGiftCard.payBill(60.0);
        assertEquals(30.0, testGiftCard.getBalance());
    }

    @Test
    void testGiftCardEqual() {
        assertTrue(testGiftCard.giftCardEqual(testGiftCard1));
        assertFalse(testGiftCard.giftCardEqual(testGiftCard2));
        assertFalse(testGiftCard.giftCardEqual(testGiftCard3));
    }

}
