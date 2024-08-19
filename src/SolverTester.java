import java.util.Arrays;

public class SolverTester {
    public static void main (String[] args){

        GeneratePuzzle puzzleGenerator = new GeneratePuzzle();

        long startTime = System.currentTimeMillis(); // Record the start time
        long duration = 60000; // Duration to run the loop in milliseconds (60,000 ms = 1 minute)

        int puzzlesSolved = 0;

        while (true) {
            long currentTime = System.currentTimeMillis(); // Get the current time
            long elapsedTime = currentTime - startTime; // Calculate the elapsed time

            if (elapsedTime >= duration) { // Check if the elapsed time has reached the duration
                break; // Exit the loop
            }

            puzzleGenerator.newPuzzle(3);
            int[][] puzzle = puzzleGenerator.getPuzzle();

            System.out.println(Arrays.deepToString(puzzle));

            Board board = new Board(puzzle, new Options(3,false, false));

            Solve solved = new Solve(board);

            puzzlesSolved++;

            System.out.println(Arrays.deepToString(puzzle));

            System.out.println("Puzzles solved: " + puzzlesSolved);


        }

        System.out.println("Loop stopped after 1 minute.");
        System.out.println("Solver solved " + puzzlesSolved + " puzzles.");
    }

}
