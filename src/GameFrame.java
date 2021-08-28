import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameFrame extends JFrame{

    private GridPanel boardFrame;
    private Board board;
    private JLabel[][] labels;
    
    private MainPanel main;
    
    private OptionsPanel optionsFrame;
    private Options options;
    private boolean optionsChanged;

    private GeneratePuzzle puzzleGenerator;
    private int[][] puzzle;
    
    private final int BOARD_WIDTH = 630;
    private final int BOARD_HEIGHT = 680;

    private JLabel back_label;
    private JLabel diff_label;
    private JLabel high_label;
    private JLabel music_label;

    private JMenuBar menuBar;
    private JMenuItem newMenu;
    private JMenuItem optionsMenu;
    private JMenuItem mainMenu;
    private JMenuItem resumeMenu;

    private BGMPlayer player;

    
    public GameFrame(){

        menuBar = new MenuBar();

        main = new MainPanel();

        optionsFrame = new OptionsPanel();
        options = new Options(1,false,true);
        optionsChanged = false;

        puzzleGenerator = new GeneratePuzzle();

        player = new BGMPlayer();

        setJMenuBar(menuBar);
        add(main);
        setSize(BOARD_WIDTH,BOARD_HEIGHT);
        Listeners();
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        music();

    }
    public void Listeners(){
        main.startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshBoard(false);
            }
        });
        main.optionButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                addOptions();
            }
        });
        resumeMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                refreshBoard(false);
            }
        });
        newMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                refreshBoard(true);
            }
        });
        optionsMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                addOptions();
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
                optionsChanged = true;
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
                refreshBoard(false);
            }
        });
        music_label.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                options.setMusic(!options.getMusic());
                player.playCompleted = true;
                String value = options.getMusic() ? "ON" : "OFF";
                music_label.setText("Music : "+value);
            }
        });
    }
    public void refreshBoard(boolean veryNew){
        getContentPane().removeAll();
        if (board == null || optionsChanged || veryNew){
            puzzle = puzzleGenerator.newPuzzle(options.getDifficulty());
            board = new Board(puzzle,options);
            boardFrame = new GridPanel(board);
            labels = board.getLabels();
            optionsChanged = false;
            add(boardFrame);
        } else {
            board.repaintLabels(labels);
            add(boardFrame);
        }
        repaint();
    }
    public void addOptions(){
        getContentPane().removeAll();
        add(back_label);
        add(high_label);
        add(diff_label);
        add(music_label);
        add(optionsFrame);
        repaint();
    }
    public void music (){
        player.play("Music/BGM.wav");
        while (options.getMusic()){
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
            
            back_label = new JLabel("Back to game");
            diff_label = new JLabel("Difficulty : 1");
            high_label = new JLabel("Highlighting : OFF");
            music_label = new JLabel("Music : ON");
            
    
            diff_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));
            high_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));
            back_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));
            music_label.setFont(new Font("Comic Sans MS",Font.BOLD,24));
    
            diff_label.setBounds(150,200,300,50);
            high_label.setBounds(150,250,300,50);
            back_label.setBounds(150,400,300,50);
            music_label.setBounds(150,300,300,50);
            
            add(panel);
            setSize(BOARD_WIDTH,BOARD_HEIGHT);
            setLayout(null);
            setVisible(true);
        }   
    }
    public class MenuBar extends JMenuBar{
        public MenuBar(){
            JMenu menu = new JMenu("Game Menu");
            add(menu);

            resumeMenu = new JMenuItem("resume");
            newMenu = new JMenuItem("new");
            optionsMenu = new JMenuItem("options");
            mainMenu = new JMenuItem("return");

            menu.add(resumeMenu);
            menu.add(newMenu);
            menu.add(optionsMenu);
            menu.add(mainMenu);
        }

    }
    public static void main(String[] args) {
        new GameFrame();
    }
}
