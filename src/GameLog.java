public class GameLog {
    int step;
    String userValue;
    Answer userAnswer;

    public GameLog(int step, String userValue, Answer userAnswer) {
        this.step = step;
        this.userValue = userValue;
        this.userAnswer = userAnswer;
    }

    public String getStringLog(){
        return String.format("На %d ходу (%s) %s", step, userValue, userAnswer);
    }
}