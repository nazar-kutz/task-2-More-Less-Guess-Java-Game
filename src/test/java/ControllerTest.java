import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class ControllerTest {
    private Model model;
    private View view;
    private Controller controller;
    private Scanner scanner;

    @Before
    public void setUp() throws Exception {
        model = new Model();
        view = new View();
        controller = new Controller(model, view);
        model.setPrimaryBariers(-10, 10);
    }

    @Test
    public void inputIntWithScanner() throws Exception {
        int expectedValue = -5;
        scanner = new Scanner("-5");
        assertEquals(expectedValue, controller.inputIntWithScanner(scanner));
    }

    @Test
    public void getUserInput() throws Exception {
        int expectedValue = 5;
        scanner = new Scanner("5");
        assertEquals(expectedValue, controller.getUserInput(scanner));
    }
}