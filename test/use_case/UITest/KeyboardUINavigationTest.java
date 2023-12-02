package use_case.UITest;

import app.Main;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyboardUINavigationTest {
    // This test goes the first couple screens of the UI. There are no assertions are we are just checking for crashes
    @Test
    void basicUITest() throws AWTException {
        String[] args = new String[0];
        Main.main(args);

        // Create an instance of Robot
        Robot robot = new Robot();

        // Give time for the application to start
        robot.delay(2000); // Delay in milliseconds

        ArrayList<Integer> keyCodes = new ArrayList<Integer>();
        keyCodes.add(KeyEvent.VK_A);
        keyCodes.add(KeyEvent.VK_B);
        keyCodes.add(KeyEvent.VK_C);
        keyCodes.add(KeyEvent.VK_D);

        // Navigate to the first text field
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

        for (Integer code: keyCodes) {
            robot.keyPress(code);
            robot.keyRelease(code);

            // Add a small delay between key events
            robot.delay(1000);

            // Simulate Tab key press to navigate
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }

        // Navigate to new game button
        for (int i = 0; i < 4; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            // Add a small delay between key events
            robot.delay(1000);
        }

        // Click new game button
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(1000);

        // Click ok button
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(1000);

        // Click generate story button
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(1000);

        // Wait for story to generate and then click continue
        robot.delay(10000);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(1000);

        // Kill player a
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);

        // Wait for story to generate and then click continue
        robot.delay(10000);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(1000);

        // Vote out player b
        robot.keyPress(KeyEvent.VK_B);
        robot.keyRelease(KeyEvent.VK_B);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);

        // Wait for story to generate and then click continue
        robot.delay(10000);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(1000);
    }

}
