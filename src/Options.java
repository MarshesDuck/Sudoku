public class Options {
    private int difficulty;
    private boolean highlighting;
    private boolean musicOn;
    public Options(int difficulty,boolean highlighting, boolean musicOn){
        this.difficulty = difficulty;
        this.highlighting = highlighting;
        this.musicOn = musicOn;
    }
    public void setDifficulty(int difficulty){
        if (difficulty == 0){
            difficulty++;
        }
        this.difficulty = difficulty;
    }
    public void setHighlighting(boolean highlighting){
        this.highlighting = highlighting;
    }
    public void setMusic(boolean musicOn){
        this.musicOn = musicOn;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public boolean getHighlighting(){
        return highlighting;
    }
    public boolean getMusic(){
        return musicOn;
    }
    
}
