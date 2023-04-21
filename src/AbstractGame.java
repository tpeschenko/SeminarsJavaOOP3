import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;


//Абстрактный класс AbstractGame
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractGame implements Game{
    Integer sizeWord;
    String word;
    Integer maxTry;
    static int countTry;
    GameStatus gameStatus = GameStatus.OFF;
    GameLog[] log;


    @Override
    public GameStatus getGameStatus(){
        return gameStatus;
    }


    // Метод генерации слова
    private String generateWord() {
        List<String> charList = generateCharList();
        SecureRandom random = new SecureRandom();
        String res = "";
        for(int i = 0; i < sizeWord; i++){
            int index = random.nextInt(charList.size());
            res += charList.get(index);
        }
        return res;
    }

    // старт общий для всех игр
    @Override
    public void start(Integer sizeWord, Integer maxTry){
        this.maxTry = maxTry;
        this.sizeWord = sizeWord;
        word = generateWord();
        this.gameStatus = GameStatus.START;
        System.out.printf("Длина слова - %d.\n" +
                "Попыток у тебя %d .\n", sizeWord, maxTry);
        log = new GameLog[maxTry];
        beginGame();
    }

    @Override
    public Answer inputAnswer(String value) {
        int bull = 0;
        int cow = 0;
        for (int i = 0; i < value.length(); i++){
            if(word.contains(Character.toString(value.charAt(i)))){
                cow++;
            }
            if (word.charAt(i) == value.charAt(i)){
                bull++;
            }
        }
        countTry++;
        if(countTry >= maxTry){
            this.gameStatus = GameStatus.LOOSE;
        }
        if(bull == sizeWord){
            this.gameStatus = GameStatus.WIN;
        }

        Answer answer = new Answer(cow, bull, value); // быки, коровы, кол-во попыток(через статик - перемстим из Answer -> сюда)
        log[(countTry - 1)] = new GameLog(countTry, value, answer);
        return answer;
    }



    // абстрактный метод без реализации(не известно какая игра)
    abstract List<String> generateCharList();



    private void numsTry(){
        System.out.printf("Попыток осталось: %d.\n", maxTry - countTry);
    }

    private void beginGame(){
        Scanner in = new Scanner(System.in);
        while(getGameStatus().equals(GameStatus.START)) {
            System.out.println(String.format("%d попытка, ваш ход:", (countTry + 1)));
            String answer = in.next();
            if (check(answer)){
                Answer answerGame = inputAnswer(answer);
                numsTry();
                System.out.println(String.format("Найдено %d коров и %d быков\n", answerGame.getCows(), answerGame.getBulls()));
            }

        }
        System.out.println(getGameStatus());
        System.out.println("Вы пытались угадать: " + getWord() + "\n");
    }

    private boolean lengthCheck(String value){
        if (sizeWord == value.length())
            return true;
        else {
            System.out.println(String.format("\nКоличесто символов долнжо быть %d ", sizeWord));
            return false;
        }
    }

    @Override
    public String getLog(){
        StringBuilder sb = new StringBuilder();
        sb.append("История ходов:\n");
        for (GameLog l: log) {
            sb.append(l.getStringLog()).append("\n");
        }
        return sb.toString();
    }

    private boolean check(String value){
        return checkInput(value) && lengthCheck(value);
    }

    abstract boolean checkInput(String value);
}