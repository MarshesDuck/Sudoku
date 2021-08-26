import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
public class GridFrame extends JPanel{
    private JLabel[][] label;
    private GeneratePuzzle puzzleGenerator;
    private ImageImplement panel;
    private int[][] puzzle;
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
                final int x_coord = i;
                final int y_coord = j;
                if (puzzle[i][j] != 0){
                    label[i][j] = new JLabel(String.valueOf(puzzle[i][j]));
                } else {
                    label[i][j] = new JLabel();
                    label[i][j].addKeyListener(new KeyAdapter(){
                        public void keyPressed(KeyEvent e){
                            //System.out.println("Pressed key "+e.getKeyCode());
                            int key = e.getKeyCode();
                            if (key > 48 && key < 60){
                                label[x_coord][y_coord].setText(String.valueOf(key-48));
                            }
                            if (key == 8){
                                label[x_coord][y_coord].setText("");
                            }
                            label[x_coord][y_coord].setForeground(Color.blue);
                        }

                    });
                    label[i][j].addMouseListener(new MouseInputAdapter(){
                        @Override
                        public void mousePressed(MouseEvent e) {
                            label[x_coord][y_coord].requestFocus();  
                        }
                    });
                }
                //label[i][j].setOpaque(true);
                label[i][j].setFocusable(true);
                label[i][j].setFont(new Font("Comic Sans MS",Font.BOLD,24));
                label[i][j].setHorizontalAlignment(JLabel.CENTER);
                label[i][j].setBounds(22+(i*61)+(i*5),22+(j*61)+(j*5),61,61);
                panel.add(label[i][j]);
            }
        }
    }
}
