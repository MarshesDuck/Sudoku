import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;
public class Board {
    private int[][] puzzle;
    private JLabel[][] label;
    private Options options;
    public Board(int[][] puzzle,Options options){
        this.options = options;
        this.puzzle = puzzle;
    }
    public void repaintLabels(JLabel[][] labels){
        for (int i = 0; i < labels.length; i++){
            for (int j = 0; j < labels.length; j++){
                if (labels[i][j] != null && puzzle[i][j] == 0){
                    label[i][j].setText(label[i][j].getText());
                    if (options.getHighlighting()){
                        if (checkMove(labels[i][j].getText(),i,j)){
                            label[i][j].setForeground(Color.blue);
                        } else {
                            label[i][j].setForeground(Color.red);
                        }
                    } else {
                        label[i][j].setForeground(Color.blue);
                    }
                }
            }
        }
    }
    public void createLabels(JPanel panel){
        label = new JLabel[9][9];
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
                                label[x_coord][y_coord].setText(String.valueOf(key-48));
                                repaintLabels(label);
                                
                            }
                            if (key == 8){
                                label[x_coord][y_coord].setText("");
                                repaintLabels(label);
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
    private boolean checkRow(String val, int x_coord, int y_coord){
        for (int i = 0; i < puzzle.length; i++){
            if (i != x_coord && label[i][y_coord].getText().equals(val)){
               return false;
            }
        }
        return true;
    }
    private boolean checkColumn(String val, int x_coord, int y_coord){
        for (int i = 0; i < puzzle.length; i++){
            if (i != y_coord && label[x_coord][i].getText().equals(val)){
              return false;
            }
        }
        return true;
    }
    private boolean checkBox(String val, int x_coord, int y_coord){
        
        int max_x = x_coord < 3 ? 3 : x_coord < 6 ? 6 : 9;
        int max_y = y_coord < 3 ? 3 : y_coord < 6 ? 6 : 9;

        int min_x = x_coord >= 6 ? 6 : x_coord >= 3 ? 3 : 0;
        int min_y = y_coord >= 6 ? 6 : y_coord >= 3 ? 3 : 0;

        for (int i = min_x; i < max_x; i++){
            for (int j = min_y; j < max_y; j++){
                if (i != x_coord && j != y_coord && label[i][j].getText().equals(val)){
                  return false;            
               }
            }
        }
        return true;
    }
    private boolean checkMove(String val, int x_coord, int y_coord){
        return (checkRow(val,x_coord,y_coord) && checkColumn(val,x_coord,y_coord) && checkBox(val,x_coord,y_coord));
    }
    public JLabel[][] getLabels(){
        return label;
    }
    
}
