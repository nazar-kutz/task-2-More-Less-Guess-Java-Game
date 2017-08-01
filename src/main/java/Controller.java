import java.util.Scanner;

public class Controller implements Global_consts {
    Model model = new Model();
    View view = new View();

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void runUserProcess(){
        //initialization
        model.setPrimaryBariers(PRIMARY_MIN_VAL, PRIMARY_MAX_VAL);  //set primary barriers
        model.setSecretNumber();            //random number creation
        view.printlnMessage(View.HELLO_GUESS_NUMBER);
        GameState gameState = GameState.START;   //state of the game
        int stepsNumber = 0;          //count iterations of the game
        Scanner scanner = new Scanner(System.in);
        //game process
        while(gameState != GameState.GUESSED) {      //while user didn't guess
            //game continue...
            view.printlnMessage(String.format(View.YOUR_STEP_NUMBER, ++stepsNumber));
            int userAnswer = getUserInput(scanner);        //get user's input
            gameState = model.doMove(userAnswer);   //do one iteration of the game
            showStatistics(gameState);
            view.newLine();
        }
    }

    /**
     * reads and checks an integer number for type matching
     * process will continue until number is valid
     * @return integer number
     */
    public int inputIntWithScanner(Scanner scanner) {
        view.printMessage(String.format(View.INPUT_INT_VAL, model.getMin(), model.getMax()));
        while (!scanner.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT + String.format(View.INPUT_INT_VAL, model.getMin(), model.getMax()));
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * this method gets an integer number from method inputIntWithScanner
     * and checks if the number is in the interval [min ; max]
     * process will continue until number is valid
     * @return integer number
     */
    public int getUserInput(Scanner scanner){
        int number = inputIntWithScanner(scanner);
        while (number <= model.getMin() || number >= model.getMax()){
            view.printMessage(View.WRONG_INPUT);
            number = inputIntWithScanner(scanner);
        }
        return number;
    }

    /**
     * method shows current game statistics
     * output depends of the state of the game
     * @param gameState
     */
    public void showStatistics(GameState gameState){
        //Equals
        if(gameState == GameState.GUESSED){
            //final output, Congratulation!
            view.printlnMessage(View.YOU_WIN + View.THIS_NUMBER_IS + model.getSecretNumber());
            view.printlnMessage(View.YOUR_ATTEMPTS);
            view.printlnMessage(model.getAttemptsAsString() + "!");
        }

        //More
        else if(gameState == GameState.MORE){   //if user's answer more than given number
            view.printlnMessage(View.NO + View.LESS_MESSAGE);
            view.printlnMessage(View.YOUR_ATTEMPTS);
            view.printlnMessage(model.getAttemptsAsString());
        }

        //Less
        else if(gameState == GameState.LESS){   //if user's answer less then given number
            view.printlnMessage(View.NO + View.MORE_MESSAGE);
            view.printlnMessage(View.YOUR_ATTEMPTS);
            view.printlnMessage(model.getAttemptsAsString());
        }
    }

}
