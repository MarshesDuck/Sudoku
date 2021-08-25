import java.io.*;
import java.net.*;
import org.json.*;
public class GeneratePuzzle {
    private String response;
    private int[][] puzzle;
    public GeneratePuzzle(int level){
        String FORMAT = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";
        String url = String.format(FORMAT,9,level);
        response = sendGet(url);
        puzzle = new int[9][9];
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
    public void newPuzzle(int size, int level){
        String FORMAT = "http://www.cs.utep.edu/cheon/ws/sudoku/new/?size=%d&level=%d";
        String url = String.format(FORMAT,size,level);
        response = sendGet(url);

        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("squares");

        for (int i = 0; i < arr.length(); i++){
            JSONObject curr = arr.getJSONObject(i);
            puzzle[curr.getInt("x")][curr.getInt("y")] = curr.getInt("value");
        }
    }
    public int[][] getPuzzle(){
        return puzzle;
    }
    public static void main(String[] args) {
        GeneratePuzzle puzzle = new GeneratePuzzle(1);
        puzzle.newPuzzle(9, 1);
        for (int i = 0; i < puzzle.puzzle.length; i++){
            for (int j = 0; j < puzzle.puzzle[i].length; j++){
                System.out.print(puzzle.puzzle[i][j]+" ");
            }
            System.out.println();
        }
    }
}
