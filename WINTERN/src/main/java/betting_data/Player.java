package betting_data;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class Player {

    private final String playerId;
    private long balance;
    private int wonBets;
    private int placedBets;
    private String betSide;
    private boolean isLegitimatePlayer;
    private final String operation;
    private final String matchId;

    public Player(String playerId, String operation, String matchId, String betSide){
        this.playerId = playerId;
        this.operation = operation;
        this.matchId = matchId;
        this.betSide =betSide;
        this.balance = 0;
        this.wonBets = 0;
        this.placedBets = 0;
        this.isLegitimatePlayer = true;

    }
    public void onLose(int amount){
        balance -= amount;
    }
    public BigDecimal winRate(){
        if(placedBets == 0){
            return BigDecimal.ZERO;
        }
        return new BigDecimal(wonBets).divide
                (new BigDecimal(placedBets), 2, RoundingMode.HALF_UP);
    }
    public int deposit(int amount){
        balance += amount;
        return amount;
    }
    public void withdraw(int amount){
        if(balance > amount){
            balance -= amount;
            isLegitimatePlayer = true;
        }else{
             isLegitimatePlayer = false;
        }
    }
    public void placeBet(int amount, String betSide){
        this.betSide = betSide;
        if(balance >= amount){
            balance -= amount;
            placedBets++;
            isLegitimatePlayer = true;
        }else{
            isLegitimatePlayer = false;
        }
    }
    public void onWin(){
        wonBets++;
    }
}
