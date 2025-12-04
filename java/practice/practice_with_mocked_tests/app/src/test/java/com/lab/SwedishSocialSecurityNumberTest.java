package com.lab;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SwedishSocialSecurityNumberTest {

    private SSNHelper mockHelper;

    public BuggySwedishSocialSecurityNumberWrongYear createSSN(String s) throws Exception {
        return new BuggySwedishSocialSecurityNumberWrongYear(s, mockHelper);
    }

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

        var ssn = createSSN("900101-0017");

        // Assert: Verify the SSN was created and methods work
        assertEquals("90", ssn.getYear());
        assertEquals("01", ssn.getMonth());
        assertEquals("01", ssn.getDay());
        assertEquals("0017", ssn.getSerialNumber());
        assertEquals("900101-0017", ssn.getSSN());

        // Verify that the mock methods were called
        verify(mockHelper, times(1)).isCorrectLength("900101-0017");
        verify(mockHelper).isCorrectFormat("900101-0017");
        verify(mockHelper).isValidMonth("01");
        verify(mockHelper).isValidDay("01");
        verify(mockHelper).luhnIsCorrect("900101-0017");
    }

    @Test
    public void constructorShouldTrimInputBeforeValidation() throws Exception {
        String raw = " 900101-0017 ";
        String expected = "900101-0017";

        when(mockHelper.isCorrectLength(expected)).thenReturn(true);
        when(mockHelper.isCorrectFormat(expected)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(expected)).thenReturn(true);

        var ssn = createSSN(raw);

        assertEquals(expected, ssn.getSSN());
    }

    @Test
    public void constructorShouldThrowExceptionForInvalidLength() {
        String input = "900101-001";

        when(mockHelper.isCorrectLength(input)).thenReturn(false);

        assertThrows(Exception.class, () -> {
            var ssn = createSSN(input);
        });

        verify(mockHelper).isCorrectLength(input);
        verify(mockHelper, never()).isCorrectFormat(anyString());
        verify(mockHelper, never()).isValidMonth(anyString());
        verify(mockHelper, never()).isValidDay(anyString());
        verify(mockHelper, never()).luhnIsCorrect(anyString());
    }

    @Test
    public void constructorShouldThrowExceptionForInvalidLuhn() {
        String input = "900101-0018";

        when(mockHelper.isCorrectLength(input)).thenReturn(true);
        when(mockHelper.isCorrectFormat(input)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(input)).thenReturn(false);

        assertThrows(Exception.class, () -> {
            var ssn = createSSN(input);
        });

        verify(mockHelper).luhnIsCorrect(input);
    }

    @Test
    public void getYearShouldReturnExpectedValue() throws Exception {
        String input = "900101-0017";

        when(mockHelper.isCorrectLength(input)).thenReturn(true);
        when(mockHelper.isCorrectFormat(input)).thenReturn(true);
        when(mockHelper.isValidMonth("01")).thenReturn(true);
        when(mockHelper.isValidDay("01")).thenReturn(true);
        when(mockHelper.luhnIsCorrect(input)).thenReturn(true);

        var ssn = createSSN(input);

        assertEquals("90", ssn.getYear());
    }
}