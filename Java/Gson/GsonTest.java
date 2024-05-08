package Java.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonTest {
    
    public static void main(String[] args) {
        Gson gson = new Gson();

        try {
            String filePath = "test.json";
            Reader reader;

            // json 파일 읽기
            reader = new FileReader(filePath);

            // gson 사용
            JsonObject obj = gson.fromJson(reader, JsonObject.class);
            
            // int값 추출
            int intValue = obj.get("num").getAsInt();
            System.out.println("num: " + intValue);

            // list 추출
            JsonArray list = obj.get("list").getAsJsonArray();
            for (int i=0; i<list.size(); i++) {
                // object 추출
                JsonObject e = list.get(i).getAsJsonObject();
                
                // string값 추출
                String filed1 = e.get("field1").getAsString();
                String field2 = e.get("field2").getAsString();

                System.out.println("field1: " + filed1 + ", field2: " + field2);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
