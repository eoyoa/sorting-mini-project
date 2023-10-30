import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects.
 *
 * @author Your Name
 */
public class SortTester {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  Sorter sorter;

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  @Test
  public void orderedStringTest() {
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // orderedStringTest

  @Test
  public void reverseOrderedStringTest() {
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // orderedStringTest

  // New test cases
  @Test
  public void orderedIntegerTest() {
     Integer[] original = { 1, 2, 3, 4, 5 };
     Integer[] expected = original.clone();
     sorter.sort(original, (x, y) -> x.compareTo(y));
     assertArrayEquals(original, expected);
  }

  @Test
  public void reverseOrderedIntegerTest() {
     Integer[] original = { 5, 4, 3, 2, 1 };
     Integer[] expected = { 1, 2, 3, 4, 5 };
     sorter.sort(original, (x, y) -> x.compareTo(y));
     assertArrayEquals(original, expected);
  }

  @Test
  public void integersWithDupesTest() {
     Integer[] original = { 5, 1, 3, 1, 3, 2, 6 };
     Integer[] expected = { 1, 1, 2, 3, 3, 5, 6 };
     sorter.sort(original, (x, y) -> x.compareTo(y));
     assertArrayEquals(original, expected);
  }

  @Test
  public void stringLengthTest() {
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    int[] expectedLengths = { 5, 5, 5, 7, 7 };
    sorter.sort(original, (x, y) -> x.length() - y.length());
    for (int i = 0; i < original.length; i++) {
      assertEquals(original[i].length(), expectedLengths[i]);
    }
  }

  @Test
  public void floatTest() {
    Float[] original = { -0.01f, 2f, 1f, -2f, 0f, 1.01f, 0.1f, 0.1f };
    Float[] expected = { -2f, -0.01f, 0f, 0.1f, 0.1f, 1f, 1.01f, 2f };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  }

} // class SortTester
