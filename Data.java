public class Data {
    private String configuration;
    private int score;


     // config is string representation of the board configuration.
     // score is score for with configuration.
    public Data(String config, int score) {
        this.configuration = config;
        this.score = score;
    }

 
     // this returns string representation of the board configuration.
    public String getConfiguration() {
        return configuration;
    }


     // this returns the score for this configuration.
    public int getScore() {
        return score;
    }
}
