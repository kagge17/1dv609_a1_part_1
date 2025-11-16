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
    public void shouldThrowExceptionOnShortPassword() {
        Exception exception = assertThrows(Exception.class, 
            () -> getPassword("1234567890A"));
        assertEquals("To short password", exception.getMessage());
    }
    
    @Test
    public void shouldThrowExceptionOnPasswordWithoutNumber() {
        Exception exception = assertThrows(Exception.class, 
            () -> getPassword("abcdefghijkl"));
        assertEquals("Does not contain a number", exception.getMessage());
    }
    
    @Test
    public void shouldAcceptValidPassword() throws Exception {
        IPassword pwd = getPassword("ValidPass123");
        assertNotNull(pwd);
    }
    
    @Test
    public void shouldTrimWhitespace() throws Exception {
        IPassword pwd1 = getPassword("  ValidPass123  ");
        IPassword pwd2 = getPassword("ValidPass123");
        assertTrue(pwd1.isPasswordSame(pwd2));
    }
    
    @Test
    public void shouldRejectPasswordWithOnlyElevenCharacters() {
        assertThrows(Exception.class, () -> getPassword("12345678901"));
    }
    
    @Test
    public void shouldCompareDifferentPasswords() throws Exception {
        IPassword pwd1 = getPassword("ValidPass123");
        IPassword pwd2 = getPassword("DifferentPass456");
        assertFalse(pwd1.isPasswordSame(pwd2));
    }
    
    @Test
    public void shouldCompareSamePasswords() throws Exception {
        IPassword pwd1 = getPassword("ValidPass123");
        IPassword pwd2 = getPassword("ValidPass123");
        assertTrue(pwd1.isPasswordSame(pwd2));
    }
}
