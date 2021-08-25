import javax.swing.*;
import java.io.IOException;

public class mainMenu extends JFrame {
    JButton startButton;
    JButton optionButton;
    public mainMenu(){
        ImageImplement panel = new ImageImplement(new ImageIcon("assets/titleScreen.png").getImage());
        
        
        startButton = new JButton(new ImageIcon("assets/startButton.png"));
        optionButton = new JButton(new ImageIcon("assets/optionButton.png"));


        startButton.setBounds(443,355,130,50);
        optionButton.setBounds(437,420,150,50);
  
        add(startButton);
        add(optionButton);

        add(panel);
        setSize(640,665);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main (String[] args) throws IOException {
        new mainMenu();
    }
}
