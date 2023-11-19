package betting_data;

import lombok.Getter;

@Getter
public class Match {

    private final String matchId;
    private final double aRate;
    private final double bRate;
    private final String result;

    Match(String matchId, double aRate, double bRate, String result) {
        this.matchId = matchId;
        this.aRate = aRate;
        this.bRate = bRate;
        this.result = result;
    }

}
