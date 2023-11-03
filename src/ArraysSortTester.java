import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class ArraysSortTester {
  final int BIG_SIZE = Short.MAX_VALUE;
  final int SHUFFLE_SEED = 42;

  Integer[] bigExpected;

  Integer[] bigOrderedActual;
  Integer[] bigReverseOrderedActual;
  Integer[] bigRandomOrderedActual;

  public ArraysSortTester() {
    bigExpected = new Integer[BIG_SIZE];
    for (int i = 0; i < BIG_SIZE; i++) {
      bigExpected[i] = i;
    }

    bigOrderedActual = Arrays.copyOf(bigExpected, BIG_SIZE);

    List<Integer> tempRev = Arrays.asList(Arrays.copyOf(bigExpected, BIG_SIZE));
    Collections.reverse(tempRev);
    bigReverseOrderedActual = tempRev.toArray(new Integer[0]);

    List<Integer> tempRand = Arrays.asList(Arrays.copyOf(bigExpected, BIG_SIZE));
    Collections.shuffle(tempRand, new Random(SHUFFLE_SEED));
    bigRandomOrderedActual = tempRand.toArray(new Integer[0]);
  }

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  @Test
  public void orderedStringTest() {
    String[] original = {"alpha", "bravo", "charlie", "delta", "foxtrot"};
    String[] expected = original.clone();
    Arrays.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(expected, original);
  } // orderedStringTest

  @Test
  public void reverseOrderedStringTest() {
    String[] original = {"foxtrot", "delta", "charlie", "bravo", "alpha"};
    String[] expected = {"alpha", "bravo", "charlie", "delta", "foxtrot"};
    Arrays.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(expected, original);
  } // orderedStringTest

  // New test cases
  @Test
  public void emptyArrayTest() {
    String[] original = {};
    String[] expected = {};
    Arrays.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(expected, original);
  }

  @Test
  public void allNegativeTest() {
    Integer[] original = { -7, -2, -3, -1, -4, -6, -5 };
    Integer[] expected = { -7, -6, -5, -4, -3, -2, -1 };
    Arrays.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(expected, original);
  }

  @Test
  public void bigOrderedTest() {
    Arrays.sort(bigOrderedActual, (x, y) -> x.compareTo(y));
    assertArrayEquals(bigExpected, bigOrderedActual);
  }

  @Test
  public void bigReverseOrderedTest() {
    Arrays.sort(bigReverseOrderedActual, (x, y) -> x.compareTo(y));
    assertArrayEquals(bigExpected, bigReverseOrderedActual);
  }

  @Test
  public void bigRandomOrderedTest() {
    Arrays.sort(bigRandomOrderedActual, (x, y) -> x.compareTo(y));
    assertArrayEquals(bigExpected, bigRandomOrderedActual);
  }
}
