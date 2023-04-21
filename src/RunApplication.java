import java.util.Scanner;



public class RunApplication {
    public static void main(String[] args) {
        System.out.print("1-цифры\t");
        System.out.print("2-буквы EN\t");
        System.out.print("3-буквы RU\n");
        Scanner in = new Scanner(System.in);
        System.out.print("Выберите игру: ");
        int choise = in.nextInt();
        Game game = null;
        switch (choise){
            case 1: game = new NumberGame();
                break;
            case 2:game=new WordGame();
                break;
            case 3:game=new WordRussianGame();
                break;
            default:
                System.out.println("Нет такой игры");
                break;
        }

        game.start(4,2);
        System.out.println(game.getLog());
    }
}
