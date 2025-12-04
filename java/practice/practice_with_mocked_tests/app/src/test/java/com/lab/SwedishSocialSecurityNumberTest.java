package com.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SwedishSocialSecurityNumberTest {

    private SSNHelper mockHelper;
    private SwedishSocialSecurityNumber ssn;

    @BeforeEach
    public void setUp() {
        mockHelper = mock(SSNHelper.class);
    }

    @Test
    public void shouldCreateValidSSNWhenAllChecksPass() throws Exception {
        when(mockHelper.isCorrectLength("900101-0017")).thenReturn(true);
        when(mockHelper.isCorrectFormat("900101-0017")).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect("900101-0017")).thenReturn(true);

        ssn = new SwedishSocialSecurityNumber("900101-0017", mockHelper);

        // Assert: Verify the SSN was created and methods work
        assertEquals("90", ssn.getYear());
        assertEquals("01", ssn.getMonth());
        assertEquals("01", ssn.getDay());
        assertEquals("0017", ssn.getSerialNumber());

        // Verify that the mock methods were called
        verify(mockHelper).isCorrectLength("900101-0017");
        verify(mockHelper).isCorrectFormat("900101-0017");
        verify(mockHelper).isValidMonth("01");
        verify(mockHelper).isValidDay("01");
        verify(mockHelper).luhnIsCorrect("900101-0017");
    }

    @Test
    public void shouldStoreTrimmedSSN() throws Exception {
        String raw = " 900101-0017 ";
        String expected = "900101-0017";

        when(mockHelper.isCorrectLength(expected)).thenReturn(true);
        when(mockHelper.isCorrectFormat(expected)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(expected)).thenReturn(true);

        ssn = new SwedishSocialSecurityNumber(raw, mockHelper);

        assertEquals(expected, ssn.getSSN());
    }

    @Test
    public void notElevenCharsShouldThrowException() {
        String input = "900101-001";

        when(mockHelper.isCorrectLength(input)).thenReturn(false);

        assertThrows(Exception.class, () -> {
            ssn = new SwedishSocialSecurityNumber(input, mockHelper);
        });

        verify(mockHelper).isCorrectLength(input);
        verify(mockHelper, never()).isCorrectFormat(anyString());
        verify(mockHelper, never()).isValidMonth(anyString());
        verify(mockHelper, never()).isValidDay(anyString());
        verify(mockHelper, never()).luhnIsCorrect(anyString());
    }

    @Test
    public void incorretFormatShouldThrowException() {
        String input = "90010100170";

        when(mockHelper.isCorrectLength(input)).thenReturn(true);
        when(mockHelper.isCorrectFormat(input)).thenReturn(false);

        assertThrows(Exception.class, () -> {
            ssn = new SwedishSocialSecurityNumber(input, mockHelper);
        });

        verify(mockHelper).isCorrectLength(input);
        verify(mockHelper).isCorrectFormat(input);
        verify(mockHelper, never()).isValidMonth(anyString());
        verify(mockHelper, never()).isValidDay(anyString());
        verify(mockHelper, never()).luhnIsCorrect(anyString());
    }

    @Test
    public void invalidMonthShouldThrowException() {
        String input = "900001-0017";

        when(mockHelper.isCorrectLength(input)).thenReturn(true);
        when(mockHelper.isCorrectFormat(input)).thenReturn(true);
        when(mockHelper.isValidMonth("00")).thenReturn(false);

        assertThrows(Exception.class, () -> {
            ssn = new SwedishSocialSecurityNumber(input, mockHelper);
        });

        verify(mockHelper).isCorrectLength(input);
        verify(mockHelper).isCorrectFormat(input);
        verify(mockHelper).isValidMonth("00");
        verify(mockHelper, never()).isValidDay(anyString());
        verify(mockHelper, never()).luhnIsCorrect(anyString());
    }

    @Test
    public void invalidDayShouldThrowException() {
        String input = "900100-0017";

        when(mockHelper.isCorrectLength(input)).thenReturn(true);
        when(mockHelper.isCorrectFormat(input)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("00")).thenReturn(false);

        assertThrows(Exception.class, () -> {
            ssn = new SwedishSocialSecurityNumber(input, mockHelper);
        });

        verify(mockHelper).isCorrectLength(input);
        verify(mockHelper).isCorrectFormat(input);
        verify(mockHelper).isValidMonth("01");
        verify(mockHelper).isValidDay("00");
        verify(mockHelper, never()).luhnIsCorrect(anyString());
    }

    @Test
    public void invalidLuhnShouldThrowException() {
        String input = "900101-0018";

        when(mockHelper.isCorrectLength(input)).thenReturn(true);
        when(mockHelper.isCorrectFormat(input)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(input)).thenReturn(false);

        assertThrows(Exception.class, () -> {
            ssn = new SwedishSocialSecurityNumber(input, mockHelper);
        });

        verify(mockHelper).isCorrectLength(input);
        verify(mockHelper).isCorrectFormat(input);
        verify(mockHelper).isValidMonth("01");
        verify(mockHelper).isValidDay("01");
        verify(mockHelper).luhnIsCorrect(input);
    }
}