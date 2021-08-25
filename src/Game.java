import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Game extends JFrame{
    mainMenu main;
    GridFrame board;
    public Game(){
        main = new mainMenu();
        board = new GridFrame();
        add(main);
        setSize(640,665);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        main.startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(main);
                add(board);
                repaint();
            }
        });

        BGMPlayer player = new BGMPlayer();
        player.play("Music/BGM.wav");
        boolean running = true;
        while (running){
            if (player.playCompleted){
                player.playCompleted = false;
                player.play("Music/BGM.wav");
            }
        }
    }
    public static void main(String[] args) {
        new Game();
    }
}
