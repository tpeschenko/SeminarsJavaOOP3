import java.util.ArrayList;
import java.util.List;

public class NumberGame extends AbstractGame{


    @Override
    List<String> generateCharList() {
        List<String> charList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            charList.add(String.valueOf(i));
        }
        return charList;
    }

    @Override
    boolean checkInput(String value) {
        try {
            int input = Integer.parseInt(value);
            return true;
        }
        catch (Exception e){
            System.out.println("\nТы должен вводить цифры!");
            return false;
        }
    }


}