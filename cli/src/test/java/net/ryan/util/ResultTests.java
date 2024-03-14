package net.ryan.util;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    void testSuccess() {
        String value = "test";
        Result<String> result = Result.success(value);

        assertFalse(result.isError());
        assertEquals(value, result.get());
    }

    @Test
    void testFailure() {
        Exception exception = new RuntimeException("error");
        Result<String> result = Result.fail(exception);

        assertTrue(result.isError());
        Exception thrownException = assertThrows(NoSuchElementException.class, result::get);
        assertEquals("Attempted to retrieve value on erroneous result", thrownException.getMessage());
    }

    @Test
    void testGetSuccess() {
        String value = "test";
        Result<String> result = Result.success(value);

        assertEquals(value, result.get());
    }

    @Test
    void testGetFailure() {
        Exception exception = new RuntimeException("error");
        Result<String> result = Result.fail(exception);

        assertThrows(NoSuchElementException.class, result::get);
    }

    @Test
    void testIsError() {
        Result<String> successResult = Result.success("test");
        assertFalse(successResult.isError());

        Result<String> failureResult = Result.fail(new RuntimeException("error"));
        assertTrue(failureResult.isError());
    }
}
