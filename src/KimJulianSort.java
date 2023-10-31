import java.util.Comparator;

public class KimJulianSort implements Sorter {
  public static Sorter SORTER = new KimJulianSort();

  // simplified port of twinsort (https://github.com/scandum/twinsort) with comments
  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    if (twinswap(values, order))
      new MergeSort().sort(values, order);
  }

  // returns if sorting should continue
  private static <T> boolean twinswap(T[] values, Comparator<? super T> order) {
    int index = 0;
    int start;
    int end = values.length - 2;
    while (index <= end) {
      // if current pair is in order, skip to next pair
      if (order.compare(values[index], values[index + 1]) <= 0) {
        index += 2;

        continue;
      }
      // otherwise, check for reversed or fix the pair

      start = index;

      index += 2;

      while (true) {
        // reversed run detector
        if (index > end) {
          // this current reversed run extends to the end of the array
          if (start == 0) {
            // the entire array is possibly reversed since the run starts at the beginning
            if (values.length % 2 == 0 || order.compare(values[index - 1], values[index]) > 0) {
              // the array was reversed
              end = values.length - 1;
              reverseRange(values, start, end);
              
              // don't merge sort, it is sorted
              return false;
            }
          }
          // entire array is not reversed, stop checking
          break;
        }

        // check if index - 1 to index + 1 are reversed
        if (order.compare(values[index], values[index + 1]) > 0) {
          if (order.compare(values[index - 1], values[index]) > 0) {
            // if they are, increase the length of the current run
            index += 2;
            continue;
          }

          // this pair is not a part of a reversed run, swap and stop checking for
          // possible reverse order array
          SortUtils.swap(values, index, index + 1);
        }
        break;
      }

      // reverse the detected reversed run previous to last checked pair
      end = index - 1;
      reverseRange(values, start, end);

      end = values.length - 2;

      index += 2;
    }
    return true;
  }

  private static <T> void reverseRange(T[] arr, int start, int end) {
    while (start < end) {
      T temp = arr[start];
      arr[start++] = arr[end];
      arr[end--] = temp;
    }
  }
}
