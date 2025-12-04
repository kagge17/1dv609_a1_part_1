package com.lab;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SSNHelperTest {

  public SSNHelper createHelper() {
    return new SSNHelper();
  }

  @Test
  public void shouldAlwaysPass() {
    assertTrue(true);
  }

  @Test
  public void isCorrectLength_WithElevenCharacters_ReturnsTrue() {
    var helper = createHelper();

    assertTrue(helper.isCorrectLength("900101-0017"));
  }

  @Test
  public void isCorrectLength_WithLessThanElevenCharacters_ReturnsFalse() {
    var helper = createHelper();

    assertFalse(helper.isCorrectLength("900101-001"));
  }

  @Test
  public void isCorrectLength_WithMoreThanElevenCharacters_ReturnsFalse() {
    var helper = createHelper();

    assertFalse(helper.isCorrectLength("900101-00170"));
  }

  @Test
  public void isCorrectFormat_WithCorrectFormat_ReturnsTrue() {
    var helper = createHelper();

    assertTrue(helper.isCorrectFormat("900101-0017"));
  }

  @Test
  public void isCorrectFormat_WithMissingHyphen_ReturnsFalse() {
    var helper = createHelper();

    assertFalse(helper.isCorrectFormat("90010100017"));
  }

  @Test
  public void isValidMonth_WithMonthOne_ReturnsTrue() {
    var helper = createHelper();

    assertTrue(helper.isValidMonth("01"));
  }

  @Test
  public void isValidMonth_WithMonthLessThanOne_ReturnsFalse() {
    var helper = createHelper();

    assertFalse(helper.isValidMonth("00"));
  }

  @Test
  public void isValidMonth_WithMonthGreaterThanTwelve_ReturnsFalse() {
    var helper = createHelper();

    assertFalse(helper.isValidMonth("13"));
  }

  @Test
  public void isValidDay_WithDayOne_ReturnsTrue() {
    var helper = createHelper();

    assertTrue(helper.isValidDay("01"));
  }

  @Test
  public void isValidDay_WithDayThirtyOne_ReturnsTrue() {
      var helper = createHelper();
      assertTrue(helper.isValidDay("31"));
  }

  @Test
  public void isValidDay_WithDayLessThanOne_ReturnsFalse() {
    var helper = createHelper();

    assertFalse(helper.isValidDay("00"));
  }

  @Test
  public void isValidDay_WithDayGreaterThanThirtyOne_ReturnsFalse() {
    var helper = createHelper();

    assertFalse(helper.isValidDay("32"));
  }

  @Test
  public void luhnIsCorrect_WithCorrectLuhn_ReturnsTrue() {
    var helper = createHelper();

    assertTrue(helper.luhnIsCorrect("900101-0017"));
  }

  @Test
  public void luhnIsCorrect_WithIncorrectLuhn_ReturnsFalse() {
    var helper = createHelper();

    assertFalse(helper.luhnIsCorrect("900101-0018"));
  }
}
