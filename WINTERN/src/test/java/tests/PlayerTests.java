package tests;

import betting_data.Player;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;


class PlayerTests {
    Player test = new Player("TEST","TEST", "TEST","TEST");
    @Test
    void onLose() {
       test.deposit(100);
       test.onLose(50);

       int expectedBalance = 50;
       int actualBalance = (int) test.getBalance();
       assertEquals(expectedBalance, actualBalance);

    }

    @Test
    void winRate() {
       test.deposit(100);
        test.onWin();
        test.placeBet(50, "TEST");
        test.placeBet(25, "TEST");
        test.placeBet(10, "TEST");

        BigDecimal expectedWinRate = BigDecimal.valueOf(0.33);
        BigDecimal actualWinRate = test.winRate();

        assertEquals(expectedWinRate,actualWinRate);

    }

    @Test
    void deposit() {

        int expectedDeposit = 200;
        int actualDeposit = test.deposit(200);

        assertEquals(expectedDeposit, actualDeposit);
    }

    @Test
    void withdraw() {

        test.deposit(100);
        test.withdraw(50);

        int expectedBalance = 50;
        boolean expectedIsLegitimate = true;

        int actualBalance = (int) test.getBalance();
        boolean actualIsLegitimate = test.isLegitimatePlayer();

        assertEquals(expectedIsLegitimate,actualIsLegitimate);
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void placeBet() {

        test.deposit(100);
        test.placeBet(500,"TEST");

        int expectedBalance = 100;
        int expectedPlacedBets = 0;
        boolean expectedIsLegitimate = false;

        int actualBalance = (int) test.getBalance();
        int actualPlacedBets = test.getPlacedBets();
        boolean actualIsLegitimate = test.isLegitimatePlayer();

        assertEquals(expectedBalance, actualBalance);
        assertEquals(expectedPlacedBets,actualPlacedBets);
        assertEquals(expectedIsLegitimate,actualIsLegitimate);


    }

    @Test
    void onWin() {
        test.onWin();

        int expectedWonBets = 1;
        int actualWonBets = test.getWonBets();

        assertEquals(expectedWonBets, actualWonBets);
    }
}