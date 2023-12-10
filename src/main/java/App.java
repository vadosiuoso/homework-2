import java.util.Scanner;



public class App {
    private static final char[] BOX = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    private static final int[][] WINNING_COMBINATIONS = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Columns
            {0, 4, 8}, {2, 4, 6}               // Diagonals
    };
    public static void main(String[] args) {
        startGame();
    }
    public static void startGame() {
        logger("Enter box number to select. Enjoy!\n");
        view();
        boolean boxEmpty = false;
        while (true) {
            if(!boxEmpty){
                for(byte i = 0; i < 9; i++)
                    BOX[i] = ' ';
                boxEmpty = true;
            }
            logger("You are next!\n\n");

            customerMove();
            if(checkWinner('X'))
                break;
            if(!isBoxAvailable())
                break;
            logger("The computer makes the next move!");
            botMove();
            if(checkWinner('0'))
                break;
        }
    }
    private static void botMove(){
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (BOX[rand - 1] != 'X' && BOX[rand - 1] != 'O') {
                BOX[rand - 1] = 'O';
                view();
                break;
            }
        }
    }
    private static void customerMove() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            byte input;
            input = scan.nextByte();
            if (isValidInput(input)) {
                if (BOX[input - 1] == 'X' || BOX[input - 1] == 'O')
                    logger("That one is already in use. Enter another.");
                else {
                    BOX[input - 1] = 'X';
                    view();
                    return;
                }
            } else
                logger("Invalid input. Enter again.");
        }
    }
    private static boolean isValidInput(byte input){
        return input > 0 && input < 10;
    }
    private static boolean isBoxAvailable(){
        for (byte i = 0; i < 9; i++) {
            if (BOX[i] != 'X' && BOX[i] != 'O') {
                return true;
            }
        }
        logger("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        return false;
    }
    private static boolean checkWinner(char symbol) {
        for (int[] combination : WINNING_COMBINATIONS) {
            if (BOX[combination[0]] == symbol && BOX[combination[1]] == symbol && BOX[combination[2]] == symbol) {
                logger(symbol == 'X' ? "You won the game!\n" : "You lost the game!\n");
                logger("Created by Shreyas Saha. Thanks for playing!");
                return true;
            }
        }

        return false;
    }
    private static void view() {
        logger("\n\n " + BOX[0] + " | " + BOX[1] + " | " + BOX[2] + " ");
        logger("-----------");
        logger(" " + BOX[3] + " | " + BOX[4] + " | " + BOX[5] + " ");
        logger("-----------");
        logger(" " + BOX[6] + " | " + BOX[7] + " | " + BOX[8] + " \n");
    }
    private static void logger(String text){
        System.out.println(text);
    }
}