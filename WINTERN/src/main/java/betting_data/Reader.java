package betting_data;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Getter
public class Reader {

    private List<String> readPlayers;
    private List<String> readMatches;

    Reader() {
        readPlayerData();

       readMatchesData();
    }

    public void readMatchesData() {
        readMatches = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/match_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                readMatches.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void readPlayerData() {
        readPlayers = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/player_data.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                readPlayers.add(line);
            }
        }   catch(IOException e){
            e.printStackTrace();
        }
    }
}
