package Java.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GsonUtil {
    
    private Gson gson = new Gson();
    
    public JsonObject readJsonFromFile(String filePath) throws FileNotFoundException {
        Reader reader;
        
        reader = new FileReader(filePath);
        
        JsonObject obj = gson.fromJson(reader, JsonObject.class);

        return obj;
    }
}