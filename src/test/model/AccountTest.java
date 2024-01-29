package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
    private Account testAccount1;
    private Account testAccount2;
    private GiftCard testGiftCard1;
    private GiftCard testGiftCard2;
    private GiftCard testGiftCard3;

    @BeforeEach
    void runBefore() {
        testAccount1 = new Account("wyy1", 0);
        testAccount2 = new Account("wyy2", 100.0);
        testGiftCard1 = new GiftCard("Christmas", 100.0);
        testGiftCard2 = new GiftCard("New Year", 600.0);
        testGiftCard3 = new GiftCard("Mayday", 0);
    }

    @Test
    void testConstructor() {
        assertEquals("wyy1", testAccount1.getName());
        assertEquals("wyy2", testAccount2.getName());
        assertEquals(0, testAccount1.getBalance());
        assertEquals(100.0, testAccount2.getBalance());
        assertEquals(0, testAccount1.getGiftCards().size());
        assertEquals(0, testAccount2.getGiftCards().size());
    }

    @Test
    void testAddGiftCard() {
        testAccount1.addGiftCard(testGiftCard1);
        assertEquals(1, testAccount1.getGiftCards().size());
        assertEquals(testGiftCard1, testAccount1.getGiftCards().get(0));
        testAccount1.addGiftCard(testGiftCard2);
        assertEquals(2, testAccount1.getGiftCards().size());
        assertEquals(testGiftCard2, testAccount1.getGiftCards().get(1));
    }
    @Test
    void testAddSameGiftCard() {
        testAccount1.addGiftCard(testGiftCard1);
        assertEquals(1, testAccount1.getGiftCards().size());
        assertEquals(testGiftCard1, testAccount1.getGiftCards().get(0));
        testAccount1.addGiftCard(testGiftCard2);
        assertEquals(2, testAccount1.getGiftCards().size());
        assertEquals(testGiftCard2, testAccount1.getGiftCards().get(1));
        testAccount1.addGiftCard(testGiftCard2);
        assertEquals(2, testAccount1.getGiftCards().size());
    }

    @Test
    void testRemoveGiftCard() {
        testAccount1.addGiftCard(testGiftCard1);
        assertEquals(1, testAccount1.getGiftCards().size());
        assertEquals(testGiftCard1, testAccount1.getGiftCards().get(0));
        testAccount1.addGiftCard(testGiftCard2);
        assertEquals(2, testAccount1.getGiftCards().size());
        assertEquals(testGiftCard2, testAccount1.getGiftCards().get(1));
        assertEquals(700, testAccount1.getBalance());
        testAccount1.removeGiftCard("Christmas");
        assertEquals(1, testAccount1.getGiftCards().size());
        assertEquals(testGiftCard2, testAccount1.getGiftCards().get(0));
        assertEquals(600, testAccount1.getBalance());
        testAccount1.removeGiftCard("New Year");
        assertEquals(0, testAccount1.getGiftCards().size());
        assertEquals(0, testAccount1.getBalance());
    }

    @Test
    void testTotalBalance() {
        testAccount1.addGiftCard(testGiftCard1);
        assertEquals(100, testAccount1.totalBalance());
        testAccount1.addGiftCard(testGiftCard2);
        assertEquals(700, testAccount1.totalBalance());
        testAccount1.removeGiftCard("Christmas");
        assertEquals(600, testAccount1.totalBalance());
    }

    @Test
    void testRemoveInvalidGiftCard() {
        testAccount1.addGiftCard(testGiftCard1);
        testAccount1.addGiftCard(testGiftCard2);
        testAccount1.addGiftCard(testGiftCard3);
        assertEquals(testGiftCard1, testAccount1.getGiftCards().get(0));
        assertEquals(testGiftCard2, testAccount1.getGiftCards().get(1));
        assertEquals(testGiftCard3, testAccount1.getGiftCards().get(2));
        assertEquals(3, testAccount1.getGiftCards().size());
        testAccount1.removeInvalidGiftCards();
        assertEquals(testGiftCard1, testAccount1.getGiftCards().get(0));
        assertEquals(testGiftCard2, testAccount1.getGiftCards().get(1));
        assertEquals(2, testAccount1.getGiftCards().size());
    }

    @Test
    void testGiftCardWithLeastBalance() {
        testAccount1.addGiftCard(testGiftCard1);
        testAccount1.addGiftCard(testGiftCard2);
        testAccount1.addGiftCard(testGiftCard3);
        assertEquals(testGiftCard3, testAccount1.giftCardWithLeastBalance());
        testAccount1.removeGiftCard("Mayday");
        assertEquals(testGiftCard1, testAccount1.giftCardWithLeastBalance());
        testAccount1.removeGiftCard("Christmas");
        assertEquals(testGiftCard2, testAccount1.giftCardWithLeastBalance());
    }

    @Test
    void testPayForTheBillUsingOneCard() {
        testAccount1.addGiftCard(testGiftCard1);
        testAccount1.addGiftCard(testGiftCard2);
        testAccount1.payForTheBill(40);
        assertEquals(2, testAccount1.getGiftCards().size());
        assertEquals(600, testGiftCard2.getBalance());
        assertEquals(60, testGiftCard1.getBalance());
    }

    @Test
    void testPayForTheBillUsingOutOneCard() {
        testAccount1.addGiftCard(testGiftCard1);
        testAccount1.addGiftCard(testGiftCard2);
        testAccount1.payForTheBill(100);
        assertEquals(1, testAccount1.getGiftCards().size());
        assertEquals(600, testGiftCard2.getBalance());
        assertEquals(0, testGiftCard1.getBalance());
    }

    @Test
    void testPayForTheBillUsingMultipleCards() {
        testAccount1.addGiftCard(testGiftCard1);
        testAccount1.addGiftCard(testGiftCard2);
        testAccount1.payForTheBill(500);
        assertEquals(1, testAccount1.getGiftCards().size());
        assertEquals(0, testGiftCard1.getBalance());
        assertEquals(200, testGiftCard2.getBalance());
    }

    @Test
    void testPrintGiftCards() {
        testAccount1.addGiftCard(testGiftCard1);
        testAccount1.printGiftCards();
        testAccount1.addGiftCard(testGiftCard2);
        testAccount1.printGiftCards();
        testAccount1.addGiftCard(testGiftCard3);
        testAccount1.printGiftCards();
    }
}