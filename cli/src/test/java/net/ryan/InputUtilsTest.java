package net.ryan;

import net.ryan.util.InputUtils;
import net.ryan.util.Result;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InputUtilsTest {

    @Test
    void readIntFromConsole_ValidInput_ReturnsSuccess() {
        ByteArrayInputStream testIn = new ByteArrayInputStream("1".getBytes());
        System.setIn(testIn);
        InputUtils inputUtils = InputUtils.getInstance();
        Result<Integer> result = inputUtils.readIntFromConsole();
        assertFalse(result.isError());
        assertEquals(result.get(), 1);
    }

    @Test
    void readStringFromConsole_ValidInput_ReturnsSuccess() {
        ByteArrayInputStream testIn = new ByteArrayInputStream("been".getBytes());
        System.setIn(testIn);
        InputUtils inputUtils = InputUtils.getInstance();
        Result<String> result = inputUtils.readStringFromConsoleLowerCase();
        assertFalse(result.isError());
        assertEquals(result.get(), "been");
    }


}
