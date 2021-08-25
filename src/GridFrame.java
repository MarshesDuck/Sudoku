import javax.swing.*;
import java.awt.*;
public class GridFrame extends JPanel{
    JLabel[][] label;
    GeneratePuzzle puzzle;
    int[][] puzz;
    GridFrame(){
        ImageImplement panel = new ImageImplement(new ImageIcon("assets/board.png").getImage());
        add(panel);
        puzzle = new GeneratePuzzle();
        puzzle.newPuzzle(1);
        puzz = puzzle.getPuzzle();

        label = new JLabel[9][9];
        for (int i = 0; i < label.length; i++){
            for (int j = 0; j < label[i].length; j++){
                if (puzz[i][j] != 0){
                    label[i][j] = new JLabel(String.valueOf(puzz[i][j]));
                } else {
                    label[i][j] = new JLabel();
                }
                //label[i][j].setOpaque(true);
                label[i][j].setFont(new Font("Comic Sans MS",Font.BOLD,24));
                label[i][j].setHorizontalAlignment(JLabel.CENTER);
                label[i][j].setBounds(22+(i*61)+(i*5),22+(j*61)+(j*5),61,61);
                panel.add(label[i][j]);
            }
        }
        //textField = new JTextArea();
        //textField.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        //textField.setBackground(null);
        //textField.setVisible(true);
    
        //textField.getCaret().setVisible(false);
        //textField.setCaretColor(getBackground());
        setSize(640,660);
        setLayout(null);
        setVisible(true);

    }
}
