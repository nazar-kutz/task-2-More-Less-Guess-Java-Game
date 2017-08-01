import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class ModelTest {
    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new Model();
    }

    @After
    public void tearDown() throws Exception {
        model = null;
    }

    @org.junit.Test
    public void setPrimaryBariers() throws Exception {
        int minBarrier = 5;
        int maxBarrier = 25;
        model.setPrimaryBariers(minBarrier, maxBarrier);
        assertEquals(minBarrier, model.getMin());
        assertEquals(maxBarrier, model.getMax());
    }

    @org.junit.Test
    public void setSecretNumber() throws Exception {
        model.setPrimaryBariers(0, 100);
        model.setSecretNumber();
        assertEquals(model.getSecretNumber() > 0, model.getSecretNumber() < 100);
    }

    @org.junit.Test
    public void getMin() throws Exception {
        model.setPrimaryBariers(15,45);
        int expectedValue = 15;
        assertEquals(expectedValue, model.getMin());
    }

    @org.junit.Test
    public void getMax() throws Exception {
        model.setPrimaryBariers(15,45);
        int expectedValue = 45;
        assertEquals(expectedValue, model.getMax());
    }

    @org.junit.Test
    public void doMove() throws Exception {
        model.setPrimaryBariers(-10, 10);
        model.setSecretNumber();
        int userAnswer = 0;
        if (userAnswer < model.getSecretNumber()){
            assertEquals(GameState.LESS, model.doMove(userAnswer));
            assertEquals(userAnswer, model.getMin());
        }
        else if (userAnswer > model.getSecretNumber()){
            assertEquals(GameState.MORE, model.doMove(userAnswer));
            assertEquals(userAnswer, model.getMax());
        }
        if (userAnswer == model.getSecretNumber()){
            assertEquals(GameState.GUESSED, model.doMove(userAnswer));
        }
    }

}