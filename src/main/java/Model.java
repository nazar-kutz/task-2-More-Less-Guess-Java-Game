import java.util.ArrayList;

public class Model implements Global_consts{
    private static final int RAND_MAX = 100;    //max value for rand() (included)
    private int secretNumber;                   //secret number to guess
    private ArrayList<Integer> userAttempts = new ArrayList<Integer>();    //user's attempts list
    int min;                        //current min and max values of the interval (included)
    int max;

    public void setPrimaryBariers(int min, int max){
        if (max < min){
            int temp = min;
            min = max;
            max = temp;
        }

        if(max - min < MIN_INTERVAL_SIZE){
            throw new wrongRangeException();
        }

        this.min = min;
        this.max = max;
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber() {
        this.secretNumber = rand(min, max);
    }

    public ArrayList<Integer> getAttempts() {
        return userAttempts;
    }

    /**
     * this function generates a string of user's attempted elements
     * @return string of user's attempted elements
     */
    public String getAttemptsAsString(){
        StringBuilder sb = new StringBuilder();
        for (Integer attempts : userAttempts) {
            sb.append(attempts);
            sb.append(", ");
        }
        sb.delete(sb.lastIndexOf(","), sb.lastIndexOf(" ") + 1);
        return sb.toString();
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    /**
     * this method create a random integer value
     * from min to max (both aren't included)
     * @param min
     * @param max
     * @return a random integer number
     */
    private int rand(int min, int max){
        ++max;
        --min;
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    /**
     * method create a random integer value
     * from 0 to RAND_MAX (both included)
     * @return a random integer number
     */
    private int rand(){
        return (int) (Math.random() * (RAND_MAX + 1));
    }

    /**
     * add last user's attempt to user's attempts list
     * @param lastAttempt
     */
    private void addToAttempts(int lastAttempt){
        userAttempts.add(lastAttempt);
    }

    /**
     * this method checks the ratio of the entered user's number
     * and the secret number and
     * @param userAnswer
     */
    public GameState doMove(int userAnswer){
        //Equal
        if(userAnswer == secretNumber){            //if user's answer equals with given number
            addToAttempts(userAnswer);             //add user answer to user's attempts list
            return  GameState.GUESSED;             //user won!
        }

        //More
        else if(userAnswer > secretNumber){      //if user's answer more than given number
            max = userAnswer;                //set new value max (less for 1 than user inputed)
            addToAttempts(userAnswer);
            return GameState.MORE;
        }

        //Less
        else if(userAnswer < secretNumber){      //if user's answer less then given number
            min = userAnswer;                //set new value of min (more for 1 than user inputed)
            addToAttempts(userAnswer);
            return GameState.LESS;
        }
        return null;
    }

}
