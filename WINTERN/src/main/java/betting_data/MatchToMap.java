package betting_data;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MatchToMap {

    private final Map<String, Match> matchesMap = new HashMap<>();

    MatchToMap(){

        mapMatches();
    }

    private void mapMatches() {
        Reader reader = new Reader();
        for(String line : reader.getReadMatches()){
            String[] MatchData = line.split(",");
            String matchId = MatchData[0];
            double rateA = Double.parseDouble(MatchData[1]);
            double rateB = Double.parseDouble(MatchData[2]);
            String result = MatchData[3];

            Match match = new Match(matchId,rateA, rateB, result);
            matchesMap.put(matchId,match);
        }
    }

}


