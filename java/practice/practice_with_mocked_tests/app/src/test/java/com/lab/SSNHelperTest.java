package com.lab;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SSNHelperTest {

  public MyBuggyHelper createHelper() {
    return new MyBuggyHelper();
  }

  @Test
  public void shouldAlwaysPass() {
    assertTrue(true);
  }

  @Test
  public void isValidDayShouldReturnTrueForValidDay() {
      var helper = createHelper();
      assertTrue(helper.isValidDay("31"));
  }

  @Test
  public void isValidDayShouldReturnFalseForInvalidDay() {
    var helper = createHelper();
    assertFalse(helper.isValidDay("0"));
  }

  @Test
  public void isValidMonthShouldReturnFalseForInvalidMonth() {
    var helper = createHelper();
    assertFalse(helper.isValidMonth("00"));
  }

  @Test
  public void isCorrectFormatShouldReturnFalseForInvalidFormat() {
    var helper = createHelper();
    assertFalse(helper.isCorrectFormat("9001010017"));
  }

  @Test
  public void isCorrectFormatShouldReturnTrueForValidFormat() {
    var helper = createHelper();
    assertTrue(helper.isCorrectFormat("900101-0017"));
  }

  @Test
  public void luhnIsCorrectShouldReturnTrueForValidLuhn() {
    var helper = createHelper();
    assertTrue(helper.luhnIsCorrect("900101-0017"));
  }

  @Test
  public void isCorrectLengthShouldReturnFalseForMoreThanElevenChars() {
    var helper = createHelper();

    assertFalse(helper.isCorrectLength("900101-00170"));
  }
}
