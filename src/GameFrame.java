import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameFrame extends JFrame{
    GridPanel boardFrame;
    
    private MainPanel main;
    private JMenuBar menuBar;
    
    private OptionsPanel optionsFrame;
    private Options options;

    private GeneratePuzzle puzzleGenerator;
    private int[][] puzzle;
    
    private final int BOARD_WIDTH = 630;
    private final int BOARD_HEIGHT = 680;
    
    public GameFrame(){

        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game Menu");
        menuBar.add(menu);
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem optionsMenu = new JMenuItem("Options");
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        menu.add(newGame);
        menu.add(optionsMenu);
        menu.add(mainMenu);


        main = new MainPanel();
        optionsFrame = new OptionsPanel();
        options = new Options(1,false);

        puzzleGenerator = new GeneratePuzzle(options.getDifficulty());
        puzzle = puzzleGenerator.getPuzzle();

        JLabel back_label = new JLabel("Back");
        JLabel diff_label = new JLabel("Difficulty : 1");
        JLabel high_label = new JLabel("Highlighting : OFF");
        

        diff_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));
        high_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));
        back_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));

        diff_label.setBounds(150,200,300,50);
        high_label.setBounds(150,250,300,50);
        back_label.setBounds(150,400,300,50);

        setJMenuBar(menuBar);
        add(main);
        setSize(BOARD_WIDTH,BOARD_HEIGHT);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main.startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                boardFrame = new GridPanel(new Board(puzzle,options));
                add(boardFrame);
                repaint();
            }
        });
        main.optionButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                add(back_label);
                add(high_label);
                add(diff_label);
                add(optionsFrame);
                repaint();
            }
        });
        newGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                getContentPane().removeAll();
                puzzle = puzzleGenerator.newPuzzle(options.getDifficulty());
                boardFrame = new GridPanel(new Board(puzzle,options));
                add(boardFrame);
                repaint();
            }
        });
        optionsMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

            }
        });
        mainMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                getContentPane().removeAll();
                add(main);
                repaint();
            }
        });
        // a difficulty label for the options screen
        diff_label.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                options.setDifficulty((options.getDifficulty()+1)%4);
                puzzle = puzzleGenerator.newPuzzle(options.getDifficulty());
                diff_label.setText("Difficulty : "+options.getDifficulty());
            }
        });
        // a highlight label for the options screen
        high_label.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                options.setHighlighting(!options.getHighlighting());
                String value = options.getHighlighting() ? "ON" : "OFF";
                high_label.setText("Highlighting : "+value);
            }
        });
        // a back label for the options screen
        back_label.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                remove(optionsFrame);
                remove(back_label);
                remove(diff_label);
                remove(high_label);
                add(main);
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
    public class MainPanel extends JPanel {
        JButton startButton;
        JButton optionButton;
        public MainPanel(){
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
    
            setSize(BOARD_WIDTH,BOARD_HEIGHT);
            setLayout(null);
            setVisible(true);
    
        }
    }
    public class GridPanel extends JPanel{
        private ImageImplement panel;
        GridPanel(Board board){
            panel = new ImageImplement(new ImageIcon("assets/board.png").getImage());
            add(panel);
            board.createLabels(panel);
            setSize(640,660);
            setLayout(null);
            setVisible(true);
        }
    }
    public class OptionsPanel extends JPanel{
        private ImageImplement panel;
        public OptionsPanel(){
            panel = new ImageImplement(new ImageIcon("assets/optionScreen.png").getImage());
            add(panel);
            setSize(BOARD_WIDTH,BOARD_HEIGHT);
            setLayout(null);
            setVisible(true);
        }   
    }
    public static void main(String[] args) {
        new GameFrame();
    }
}
