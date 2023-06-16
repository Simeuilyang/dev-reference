package Java;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFile {
    public void readFile(String fileName, String path) {
        String line = null;

        try {
            FileReader fr = new FileReader("./" + fileName);
            BufferedReader br = new BufferedReader(fr);

            do {
                line = br.readLine();
            } while(line.indexOf(path) < 0);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
