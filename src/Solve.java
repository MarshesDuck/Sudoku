import java.util.*;


public class Solve{

    private int[][] puzzle;
    private boolean finished;
    private Stack<Tuple> moves;
    private int gridCount;

    public Solve(Board board){

        puzzle = board.getPuzzle();
        moves = new Stack<Tuple>();
        gridCount = freeSquares();
        moves.push(new Tuple(0,0,new ArrayList<Integer>()));
        finished = false;
        backtrack();
    }
    private void backtrack(){

        if (gridCount == 0){
            finished = true;
        } else {
            Tuple candidates = nextMove(moves.peek().x_coord,moves.peek().y_coord);
            for (int i = 0; i < candidates.value.size(); i++){
                makeMove(candidates, candidates.value.get(i));
                backtrack();
                if (finished) return;
                unmakeMove();
            }
        }
    }
    private Tuple nextMove(int x_coord, int y_coord){
        ArrayList<Integer> candidates = new ArrayList<>();
        int smallest = 9;
        int x = 0;
        int y = 0;
        for (int i = 0; i < puzzle.length; i++){
            for (int j = 0; j < puzzle.length; j++){
                ArrayList<Integer> possibilities = possibilities(i,j);
                if (puzzle[i][j] == 0 && possibilities.size() < smallest){
                    x = i;
                    y = j;
                    smallest = possibilities.size();
                    candidates = possibilities;
                }

            }
        }
        return new Tuple(x,y,candidates);
    }
    private void makeMove(Tuple move, int val){
        puzzle[move.x_coord][move.y_coord] = val;
        moves.push(move);
        gridCount--;
    }
    private void unmakeMove(){
        puzzle[moves.peek().x_coord][moves.peek().y_coord] = 0;
         moves.pop();
         gridCount++;

    }
    private ArrayList<Integer> possibilities(int x_coord, int y_coord){
        ArrayList<Integer> possibilities = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++){
            if(checkMove(i,x_coord,y_coord)){
                possibilities.add(i);
            }
        }
        return possibilities;
    }
    private boolean checkMove(int value, int x_coord, int y_coord){
        for (int i = 0; i < puzzle.length; i++){
            if (puzzle[i][y_coord] == value){
                return false;
            }
        }
        for (int i = 0; i < puzzle.length; i++){
            if (puzzle[x_coord][i] == value){
                return false;
            }
        }
        int max_x = x_coord < 3 ? 3 : x_coord < 6 ? 6 : 9;
        int max_y = y_coord < 3 ? 3 : y_coord < 6 ? 6 : 9;

        int min_x = x_coord >= 6 ? 6 : x_coord >= 3 ? 3 : 0;
        int min_y = y_coord >= 6 ? 6 : y_coord >= 3 ? 3 : 0;

        for (int i = min_x; i < max_x; i++){
            for (int j = min_y; j < max_y; j++){
                if (puzzle[i][j] == value){
                  return false;            
               }
            }
        }
        return true;
    }
    private int freeSquares(){
        int freeSquares = 0;
        for (int i = 0; i < puzzle.length; i++){
            for (int j = 0; j < puzzle.length; j++){
                if (puzzle[i][j] == 0){
                    freeSquares++;
                }
            }
        }
        return freeSquares;
    }
    private class Tuple {
        private int x_coord;
        private int y_coord;
        private ArrayList<Integer> value;
        public Tuple(int x_coord, int y_coord, ArrayList<Integer> value){
            this.x_coord = x_coord;
            this.y_coord = y_coord;
            this.value = value;
        }
        
    }
}
