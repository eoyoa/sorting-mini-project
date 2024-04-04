import java.util.Comparator;

/**
 * Sort using insertion sort.
 *
 * @author Julian Kim
 */

public class InsertionSort implements Sorter {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new InsertionSort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  InsertionSort() {} // InsertionSort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    // everything to the left of i is sorted, to the right is not.
    // i is currently what we are looking at.
    for (int i = 0; i < values.length; i++) {
      int current = i;
      for (int j = i - 1; j >= 0 && order.compare(values[current], values[j]) < 0; j--) {
        SortUtils.swap(values, current, j);
        current = j;
      }
    }
  } // sort(T[], Comparator<? super T>
} // class InsertionSort
