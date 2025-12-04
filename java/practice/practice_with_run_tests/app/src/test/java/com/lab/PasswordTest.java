package com.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Password implementations.
 *
 * To test different buggy versions, simply uncomment the corresponding
 * getPassword() method and comment out the others.
 *
 * Available implementations:
 * - Password: Correct implementation
 * - BugDoesNotTrim: Does not trim whitespace
 * - BugToShortPassword: Allows passwords shorter than 12 characters
 * - BugVeryShort: Allows way to short passwords
 * - BugWrongExceptionMessage: Wrong exception message for short passwords
 * - BugMissingPasswordLengthCheck: Does not throw exception for short passwords
 * - BugMissingNumberCheck: Does not throw exception if password lacks a number
 * - BugIsPasswordSameAlwaysTrue: isPasswordSame always returns true
 * - BugWrongHashingAlgorithm: Wrong hashing algorithm
 */

public class PasswordTest {
    private IPassword getPassword(String s) throws Exception {
        // return (IPassword) new Password(s);
        // return (IPassword) new BugDoesNotTrim(s);
        // return (IPassword) new BugToShortPassword(s);
        // return (IPassword) new BugVeryShort(s);
        // return (IPassword) new BugWrongExceptionMessage(s);
        // return (IPassword) new BugMissingPasswordLengthCheck(s);
        // return (IPassword) new BugMissingNumberCheck(s);
        // return (IPassword) new BugIsPasswordSameAlwaysTrue(s);
        return (IPassword) new BugWrongHashingAlgorithm(s);
    }

    @Test
    public void shouldAlwaysPass() throws Exception {
        assertTrue(true);
    }

    @Test
    public void shouldTrimWhitespace() throws Exception {
        IPassword p1 = getPassword("  password1234 ");
        IPassword p2 = getPassword("password1234");

        assertTrue(p1.isPasswordSame(p2));
    }

    @Test
    public void shouldThrowExceptionForMissingNumber() {
        assertThrows(Exception.class, () -> getPassword("password!!!!"));
    }

    @Test
    public void shouldThrowExceptionFor11CharPassword() {
        assertThrows(Exception.class, () -> getPassword("password123"));
    }

    @Test
    public void shouldThrowExceptionFor5CharPassword() {
        assertThrows(Exception.class, () -> getPassword("pass12"));
    }

    @Test
    public void shouldThrowExceptionForEmptyPassword() {
        Exception e = assertThrows(Exception.class, () -> getPassword(""));
    }

    @Test
    public void shouldThrowCorrectMessageForShortPassword() {
        Exception e = assertThrows(Exception.class, () -> getPassword("p123"));
        assertEquals("To short password", e.getMessage());
    }

    @Test
    public void getPasswordHashShouldReturnExpectedValue() throws Exception {
        IPassword p = getPassword("password1234");
        assertEquals(-1487852828, p.getPasswordHash());
    }

    @Test
    public void isPasswordSameShouldReturnFalseForDifferentPasswords() throws Exception {
        IPassword p1 = getPassword("password1234");
        IPassword p2 = getPassword("password1235");

        assertFalse(p1.isPasswordSame(p2));
    }
}
