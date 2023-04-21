import java.util.ArrayList;
import java.util.List;

//ascii  1040-1071 (А-Я)
//ascii  1072-1103 (а-я)

public class WordRussianGame extends AbstractGame{

    @Override
    List<String> generateCharList() {
        List<String> charList = new ArrayList<>();
        for(int i = 1072; i < 1104; i++){
            charList.add(String.valueOf((char)i));
        }
        return charList;
    }

    @Override
    boolean checkInput(String value) {
        for (int i = 0; i < value.length(); i++) {
            char check = Character.toLowerCase(value.charAt(i));
            System.out.println("check " + check);
            if ((int)check < 1072 || (int)check > 1103) {
                System.out.println("\nТы должен вводить русские буквы!");
                return false;
            }
        }
        return true;
    }
}