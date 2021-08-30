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

    private JMenuBar menuBar;

    private JMenuItem newMenu;
    private JMenuItem optionsMenu;
    private JMenuItem mainMenu;
    private JMenuItem resumeMenu;
    private JMenuItem solveMenu;

    private BGMPlayer player;
    private String audioFile = "Music/BGM.wav";

    public GameFrame(){

        menuBar = new MenuBar();

        main = new MainPanel();


        optionsFrame = new OptionsPanel();
        options = new Options(1,false,false);
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
        if (options.getMusic()){
            player.play(audioFile);
        }

    }
    public void Listeners(){

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
        solveMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                getContentPane().removeAll();
                board.solveBoard();
                GridPanel boardFrame = new GridPanel(new Board(board.getPuzzle(),options));
                add(boardFrame);
                repaint();
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

        // a difficulty label for the options screen
        optionsFrame.diff_label.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                options.setDifficulty((options.getDifficulty()+1)%4);
                optionsChanged = true;
                optionsFrame.diff_label.setText("Difficulty : "+options.getDifficulty());
            }
        });
        // a highlight label for the options screen
        optionsFrame.high_label.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                options.setHighlighting(!options.getHighlighting());
                String value = options.getHighlighting() ? "ON" : "OFF";
                optionsFrame.high_label.setText("Highlighting : "+value);
            }
        });
        // a back label for the options screen
        optionsFrame.back_label.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                refreshBoard(false);
            }
        });
        optionsFrame.music_label.addMouseListener(new MouseInputAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                options.setMusic(!options.getMusic());
                String value = "";
                if (options.getMusic()){
                    player.play(audioFile);
                    value = "ON";

                } else {
                    player.close();
                    value = "OFF";
                }
                optionsFrame.music_label.setText("Music : "+value);
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
        add(optionsFrame.back_label);
        add(optionsFrame.high_label);
        add(optionsFrame.diff_label);
        add(optionsFrame.music_label);
        add(optionsFrame);
        repaint();
    }
    public class MainPanel extends JPanel {
        private JButton startButton;
        private JButton optionButton;
        private ImageImplement mainImage;
        public MainPanel(){
            mainImage = new ImageImplement(new ImageIcon("assets/titleScreen.png").getImage());
            
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
    
            add(mainImage);
    
            setSize(BOARD_WIDTH,BOARD_HEIGHT);
            setLayout(null);
            setVisible(true);
    
        }
    }
    public class GridPanel extends JPanel{
        private ImageImplement boardImage;
        GridPanel(Board board){
            boardImage = new ImageImplement(new ImageIcon("assets/board.png").getImage());
            add(boardImage);
            board.createLabels(boardImage);
            setSize(640,660);
            setLayout(null);
            setVisible(true);
        }
    }
    public class OptionsPanel extends JPanel{

        private ImageImplement optionsImage;

        private JLabel back_label;
        private JLabel diff_label;
        private JLabel high_label;
        private JLabel music_label;

        public OptionsPanel(){
            optionsImage = new ImageImplement(new ImageIcon("assets/optionScreen.png").getImage());
            
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
            
            add(optionsImage);
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
            solveMenu = new JMenuItem("solve");

            menu.add(resumeMenu);
            menu.add(newMenu);
            menu.add(optionsMenu);
            menu.add(solveMenu);
            menu.add(mainMenu);
        }

    }
    public static void main(String[] args) {
        new GameFrame();
    }
}
