public class Options {
    private int difficulty;
    private boolean highlighting;
    public Options(int difficulty,boolean highlighting){
        this.difficulty = difficulty;
        this.highlighting = highlighting;
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
    public int getDifficulty(){
        return difficulty;
    }
    public boolean getHighlighting(){
        return highlighting;
    }
    
}
