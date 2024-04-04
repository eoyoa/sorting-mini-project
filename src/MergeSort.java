import java.util.Arrays;
import java.util.Comparator;

/**
 * Sort using merge sort.
 *
 * @author Julian Kim
 */

public class MergeSort implements Sorter {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new MergeSort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  MergeSort() {} // MergeSort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    sort(values, order, 0, values.length);
  } // sort(T[], Comparator<? super T>

  public <T> void sort(T[] values, Comparator<? super T> order, int lo, int hi) {
    if (hi - lo <= 1)
      return;

    int mid = (lo + hi) / 2;
    sort(values, order, lo, mid);
    sort(values, order, mid, hi);
    merge(values, lo, mid, hi, order);
  } // sort(T[], Comparator<? super T>

  /**
   * Merge the values from positions [lo..mid) and [mid..hi) back into the same part of the array.
   *
   * Preconditions: Each subarray is sorted accorting to comparator.
   */
  static <T> void merge(T[] vals, int lo, int mid, int hi, Comparator<? super T> comparator) {
    T[] tempArr = Arrays.copyOfRange(vals, lo, hi);

    final int SECOND_HALF_START = mid - lo;
    final int LENGTH = hi - lo;

    /* make two pointers */
    // first half pointer
    int p1 = 0;
    // second half pointer
    int p2 = SECOND_HALF_START;


    for (int i = lo; i < hi; i++) {
      if (p1 >= SECOND_HALF_START) {
        // if you have exhausted all objects in first half
        vals[i] = tempArr[p2++];
        continue;
      }

      if (p2 >= LENGTH) {
        // if you have exhausted all objects in second half
        vals[i] = tempArr[p1++];
        continue;
      }

      // otherwise, choose the smaller object
      vals[i] = comparator.compare(tempArr[p1], tempArr[p2]) <= 0
        ? tempArr[p1++]
        : tempArr[p2++];
    }
  } // merge

} // class MergeSort
