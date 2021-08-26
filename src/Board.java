import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;
public class Board {
    private int[][] puzzle;
    private Options options;
    private GeneratePuzzle puzzleGenerator;
    public Board(Options options){
        this.options = options;
        puzzleGenerator = new GeneratePuzzle(options.getDifficulty());
        puzzle = puzzleGenerator.getPuzzle();
    }
    public void createLabels(JPanel panel){
        JLabel[][] label = new JLabel[9][9];
        for (int i = 0; i < label.length; i++){
            for (int j = 0; j < label[i].length; j++){
                final int x_coord = i;
                final int y_coord = j;
                if (puzzle[i][j] != 0){
                    label[i][j] = new JLabel(String.valueOf(puzzle[i][j]));
                } else {
                    label[i][j] = new JLabel();
                    label[i][j].addKeyListener(new KeyAdapter(){
                        public void keyPressed(KeyEvent e){
                            int key = e.getKeyCode();
                            if (key > 48 && key < 60){
                                if (options.getHighlighting()){
                                    if (checkMove(key-48,x_coord,y_coord)){
                                        label[x_coord][y_coord].setText(String.valueOf(key-48));
                                        puzzle[x_coord][y_coord] = key-48;
                                        label[x_coord][y_coord].setForeground(Color.blue);
                                            
                                    } else {
                                        label[x_coord][y_coord].setText(String.valueOf(key-48));
                                        label[x_coord][y_coord].setForeground(Color.red);
                                    }
                                } else {
                                    label[x_coord][y_coord].setText(String.valueOf(key-48));
                                    puzzle[x_coord][y_coord] = key-48;
                                    label[x_coord][y_coord].setForeground(Color.blue);
                                }
                                
                            }
                            if (key == 8){
                                label[x_coord][y_coord].setText("");
                                puzzle[x_coord][y_coord] = 0;
                            }
                        }
                            
                    });
                    label[i][j].addMouseListener(new MouseInputAdapter(){
                        @Override
                        public void mousePressed(MouseEvent e) {
                            label[x_coord][y_coord].requestFocus();  
                        }
                    });
                }
                label[i][j].setFocusable(true);
                label[i][j].setFont(new Font("Comic Sans MS",Font.BOLD,24));
                label[i][j].setHorizontalAlignment(JLabel.CENTER);
                label[i][j].setBounds(22+(i*61)+(i*5),22+(j*61)+(j*5),61,61);
                panel.add(label[i][j]);
            }
        }
    }
    private boolean checkRow(int val, int index){
        for (int i = 0; i < puzzle.length; i++){
            if (puzzle[i][index] == val){
               return false;
            }
        }
        return true;
    }
    private boolean checkColumn(int val, int index){
        for (int i = 0; i < puzzle.length; i++){
            if (puzzle[index][i] == val){
              return false;
            }
        }
        return true;
    }
    private boolean checkBox(int val, int x_coord, int y_coord){
        
        int max_x = x_coord < 3 ? 3 : x_coord < 6 ? 6 : x_coord < 9 ? 9 : 0;
        int max_y = y_coord < 3 ? 3 : y_coord < 6 ? 6 : y_coord < 9 ? 9 : 0;

        int min_x = x_coord >= 6 ? 6 : x_coord >= 3 ? 3 : x_coord >= 0 ? 0 : 9;
        int min_y = y_coord >= 6 ? 6 : y_coord >= 3 ? 3 : y_coord >= 0 ? 0 : 9;

        for (int i = min_x; i < max_x; i++){
            for (int j = min_y; j < max_y; j++){
                if (puzzle[i][j] == val){
                  return false;            
               }
            }
        }
        return true;
    }
    private boolean checkMove(int val, int x_coord, int y_coord){
        return (checkRow(val,y_coord) && checkColumn(val,x_coord) && checkBox(val,x_coord,y_coord));
    }
    
}
