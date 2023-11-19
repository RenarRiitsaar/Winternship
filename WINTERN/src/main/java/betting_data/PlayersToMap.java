package betting_data;

import lombok.Getter;

import java.util.*;

@Getter
public class PlayersToMap {

    private final Map<String, Player> playerMap;



    PlayersToMap() {
        this.playerMap = new HashMap<>();
        putPlayersToMap();

    }

    private void putPlayersToMap() {
        Reader reader = new Reader();
        for(String line : reader.getReadPlayers()){
            String[] playerData = line.split(",");
            String playerId = playerData[0];
            String operation = playerData[1];
            String matchId  = playerData.length > 2 ? playerData[2]: null;
            String betSide = playerData.length > 4 ? playerData[4]: null;

            Player player = new Player(playerId,operation,matchId,betSide);
            playerMap.put(playerId, player);

        }
    }

}



