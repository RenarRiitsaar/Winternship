package betting_data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer{

    Writer() {
        writeResult();

    }

    private void writeResult() {
        ProcessOperation op = new ProcessOperation();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/betting_data/result.txt", true))) {
            writer.write(op.getLegitimatePlayers() +"\n");
            writer.write(op.getIllegitimatePlayers() +"\n");
            writer.write(String.valueOf(op.getCasinoBalance()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

