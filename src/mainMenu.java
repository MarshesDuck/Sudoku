import javax.swing.*;
public class mainMenu extends JPanel {
    JButton startButton;
    JButton optionButton;
    public mainMenu(){
        ImageImplement panel = new ImageImplement(new ImageIcon("assets/titleScreen.png").getImage());
        
        startButton = new JButton();
        optionButton = new JButton();

        startButton.setBounds(443,355,130,50);
        optionButton.setBounds(437,420,150,50);
        
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        optionButton.setOpaque(false);
        optionButton.setContentAreaFilled(false);
        optionButton.setBorderPainted(false);
  
        add(startButton);
        add(optionButton);

        add(panel);

        setSize(640,665);
        setLayout(null);
        setVisible(true);

    }
}
