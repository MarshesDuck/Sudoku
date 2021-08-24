import java.io.*;
import java.net.*;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
public class GeneratePuzzle {
    private String response;
    public GeneratePuzzle(int size, int level){
        String FORMAT = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";
        String url = String.format(FORMAT,size,level);
        response = sendGet(url);
    }
    private String sendGet(String urlString){
        HttpURLConnection con = null;
        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null){
                response.append(line);
            }
            return response.toString();
        } catch (IOException e){
            //pass
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }
    public String getResponse(){
        return response;
    }
    public void newPuzzle(int size, int level){
        String FORMAT = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";
        String url = String.format(FORMAT,size,level);
        response = sendGet(url);
    }
    public static void main(String[] args) {
        GeneratePuzzle puzzle = new GeneratePuzzle(4, 1);
        System.out.println(puzzle.response);
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(puzzle.response);
            JSONArray array = new JSONArray();
            array.add(obj);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        
    }
}
