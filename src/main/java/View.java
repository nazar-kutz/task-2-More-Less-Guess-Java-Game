public class View {
    public static final String INPUT_INT_VAL = "Input an integer value ( %d ; %d ): ";
    public static final String MORE_MESSAGE = "more!";
    public static final String LESS_MESSAGE = "less!";
    public static final String YOU_WIN = "Congratulate, You guessed!;) ";
    public static final String THIS_NUMBER_IS = "This number is ";
    public static final String WRONG_INPUT = "Wrong input! Repeat, please! ";
    public static final String YOUR_ATTEMPTS = "Your attempts: ";
    public static final String HELLO_GUESS_NUMBER = "Hello! please, guess the number I've given:). ";
    public static final String NO = "No, ";
    public static final String YOUR_STEP_NUMBER = "Attempt №%d. ";

    public void printMessage(String message){
        System.out.print(message);
    }

    public void printlnMessage(String message){
        System.out.println(message);
    }

    public void newLine(){
        System.out.println();
    }
}
