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

    int i1 = 0;
    int i2 = mid - lo;

    for (int i = lo; i < hi; i++) {
      if (i1 >= mid - lo) {
        vals[i] = tempArr[i2++];
        continue;
      }

      if (i2 >= hi - lo) {
        vals[i] = tempArr[i1++];
        continue;
      }

      vals[i] = comparator.compare(tempArr[i1], tempArr[i2]) <= 0 ? tempArr[i1++] : tempArr[i2++];
    }
  } // merge

} // class MergeSort
