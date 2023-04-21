import java.util.ArrayList;
import java.util.List;

//ascii  65-90  (A-Z)
//ascii  97-122 (a-z)

public class WordGame extends AbstractGame{
    @Override
    List<String> generateCharList() {
        List<String> charList = new ArrayList<>();
        for(int i = 97; i < 123; i++){
            charList.add(String.valueOf((char)i));
        }
        return charList;
    }

    @Override
    boolean checkInput(String value) {
        for (int i = 0; i < value.length(); i++) {
            char check = Character.toLowerCase(value.charAt(i));
            System.out.println("check " + check);
            if ((int)check < 97 || (int)check > 122) {
                System.out.println("\nТы должен вводить английские буквы!");
                return false;
            }
        }
        return true;
    }
}
