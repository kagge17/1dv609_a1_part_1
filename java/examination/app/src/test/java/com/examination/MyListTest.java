package com.examination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class MyListTest {

  private MyList list;

  @Test
  public void shouldReturnCorrectSum() {
    list = new MyList();
    int result = list.sum(Arrays.asList(1, 2, 3));
    assertEquals(6, result);
  }
}
