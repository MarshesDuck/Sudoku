import java.io.*;
import java.net.*;
import org.json.*;
public class GeneratePuzzle {
    private final int SIZE = 9;
    private int[][] puzzle = new int[SIZE][SIZE];
    public GeneratePuzzle(int level){
        newPuzzle(level);
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
    public int[][] newPuzzle(int level){
        String FORMAT = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";
        String url = String.format(FORMAT,SIZE,level);
        String response = sendGet(url);
        puzzle = new int[SIZE][SIZE];

        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("squares");

        for (int i = 0; i < arr.length(); i++){
            JSONObject curr = arr.getJSONObject(i);
            puzzle[curr.getInt("x")][curr.getInt("y")] = curr.getInt("value");
        }
        return puzzle;
    }
    public int[][] getPuzzle(){
        return puzzle;
    }
}
