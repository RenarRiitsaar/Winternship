package betting_data;
import lombok.Getter;

import java.util.*;

@Getter
public class ProcessOperation {

    private final Map<String,String> legitimatePlayers;
    private final Map<String, String> illegitimatePlayers;
    private long casinoBalance;



    ProcessOperation() {
        this.legitimatePlayers = new HashMap<>();
        this.illegitimatePlayers = new HashMap<>();
        processOperations();

    }

    private void processOperations() {
        Reader reader = new Reader();
        MatchToMap matchMap = new MatchToMap();
        PlayersToMap playerMap = new PlayersToMap();
        casinoBalance = 0;


        for (String line : reader.getReadPlayers()) {
            String[] players = line.split(",");
            String playerId = players[0];
            String operation = players[1];
            String matchId = players.length > 2 ? players[2] : null;
            int coins = Integer.parseInt(players[3]);
            String betSide = players.length > 4 ? players[4] : null;

            Player player = playerMap.getPlayerMap().get(playerId);
            Match match = matchMap.getMatchesMap().get(matchId);


            switch (operation) {
                case "DEPOSIT" -> player.deposit(coins);
                case "BET" -> bet(player, coins, betSide, match);
                case "WITHDRAW" -> withdraw(player, coins);

            }
        }
    }



    private void withdraw(Player player, int coins) {
        player.withdraw(coins);
        if (!player.isLegitimatePlayer()) {
            illegitimatePlayers.put(player.getPlayerId(), player.getOperation() + " " +
                    player.getMatchId() + " " + coins + " " + player.getBetSide());
        } else {
            legitimatePlayers.put(player.getPlayerId(), player.getBalance() + " " + player.winRate());
        }
    }

    private void bet(Player player, int coins, String betSide, Match match) {
        player.placeBet(coins, betSide);
        if (Objects.equals(match.getResult(), betSide)) {
            player.onWin();
            betWin(player, coins, match);
        } else {
            player.onLose(coins);
            casinoBalance += coins;
        }
        if (!player.isLegitimatePlayer()) {
            illegitimatePlayers.put(player.getPlayerId(), player.getOperation() + " " +
                    player.getMatchId() + " " + coins + " " + player.getBetSide());
        } else{
            legitimatePlayers.put(player.getPlayerId(), player.getBalance() + " " + player.winRate());
        }
    }

    private void betWin(Player player, int coins, Match match) {
        if(Objects.equals(match.getResult(), "A")){
            player.deposit((int) (coins * match.getARate()));
            casinoBalance -= (long) (coins * match.getARate());
        }
        if (Objects.equals(match.getResult(), "B")) {
            player.deposit((int) (coins * match.getBRate()));
            casinoBalance -= (long) (coins * match.getBRate());
        }
        if (Objects.equals(match.getResult(), "DRAW")) {
            player.deposit(coins);
            casinoBalance -= coins;
        }
    }
}

