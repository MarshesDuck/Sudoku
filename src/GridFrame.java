import javax.swing.*;
import java.awt.*;
public class GridFrame extends JPanel{
    JLabel[][] label;
    GeneratePuzzle puzzleGenerator;
    ImageImplement panel;
    int[][] puzzle;
    GridFrame(){
        panel = new ImageImplement(new ImageIcon("assets/board.png").getImage());
        add(panel);


        puzzleGenerator = new GeneratePuzzle(1);
        puzzle = puzzleGenerator.getPuzzle();

        createLabels();
        setSize(640,660);
        setLayout(null);
        setVisible(true);

    }
    private void createLabels(){
        label = new JLabel[9][9];
        for (int i = 0; i < label.length; i++){
            for (int j = 0; j < label[i].length; j++){
                if (puzzle[i][j] != 0){
                    label[i][j] = new JLabel(String.valueOf(puzzle[i][j]));
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
    }
}
