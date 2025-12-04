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
        return (IPassword) new Password(s);
        // return (IPassword) new BugDoesNotTrim(s);
        // return (IPassword) new BugToShortPassword(s);
        // return (IPassword) new BugToShortPassword(s);
        // return (IPassword) new BugVeryShort(s);
        // return (IPassword) new BugWrongExceptionMessage(s);
        // return (IPassword) new BugMissingPasswordLengthCheck(s);
        // return (IPassword) new BugMissingNumberCheck(s);
        // return (IPassword) new BugIsPasswordSameAlwaysTrue(s);
        // return (IPassword) new BugWrongHashingAlgorithm(s);
    }

    @Test
    public void shouldAlwaysPass() throws Exception {
        assertTrue(true);
    }

    @Test
    public void whitespacePasswordShouldBeTrimmed() throws Exception {
        IPassword spaceP = getPassword(" password1234 ");
        IPassword trimP = getPassword("password1234");

        assertTrue(trimP.isPasswordSame(spaceP));
    }

    @Test
    public void shortPasswordShouldThrowException() {
        String tooShort = "password123";

        assertThrows(Exception.class, () -> {
            IPassword p = getPassword(tooShort);
        });
    }

    @Test
    public void shortPasswordShouldThrowExpectedExceptionMessage() {
        String tooShort = "password123";

        Exception e = assertThrows(Exception.class, () -> {
            IPassword p = getPassword(tooShort);
        });
        assertEquals("To short password", e.getMessage());
    }

    @Test
    public void noNumberPasswordShouldThrowException() {
        String letters = "password!!!!";

        assertThrows(Exception.class, () -> {
            IPassword p = getPassword(letters);
        });
    }

    @Test
    public void noNumberPasswordShouldThrowExpectedExceptionMessage() {
        String noNumber = "password!!!!";

        Exception e = assertThrows(Exception.class, () -> {
            IPassword p = getPassword(noNumber);
        });
        assertEquals("Does not contain a number", e.getMessage());
    }

    @Test
    public void passwordShouldReturnExpectedHash() throws Exception {
        int expected = -1487852828;
        IPassword p = getPassword("password1234");

        assertEquals(expected, p.getPasswordHash());
    }

    @Test
    public void samePasswordShouldReturnTrue() throws Exception {
        IPassword p1 = getPassword("password1234");
        IPassword p2 = getPassword("password1234");

        assertTrue(p1.isPasswordSame(p2));
    }

    @Test
    public void differentPasswordsShouldReturnFalse() throws Exception {
        IPassword p1 = getPassword("password1234");
        IPassword p2 = getPassword("password1235");

        assertFalse(p1.isPasswordSame(p2));
    }
}
