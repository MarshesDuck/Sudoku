import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class mainMenu extends JFrame {

    public static void main (String[] args) throws IOException {
        new mainMenu().start();
    }

    public void start() throws IOException {

        ImageImplement panel = new ImageImplement(new ImageIcon("assets/titleScreen.png").getImage());
        add(panel);
        setTitle("Sudoku Without Friends");
        setIconImage(ImageIO.read(new File("assets/icon.png")));
        setVisible(true);
        setSize(640,665);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
}
