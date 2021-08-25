import javax.swing.*;
public class GridFrame extends JPanel{
    GridFrame(){
        ImageImplement panel = new ImageImplement(new ImageIcon("assets/board.png").getImage());
        add(panel);
        setSize(640,665);
        setLayout(null);
        setVisible(true);

    }
}
