import java.util.Arrays;
import java.util.Comparator;

public class KimJulianSort implements Sorter {
  public static Sorter SORTER = new KimJulianSort();

  // port of twinsort (https://github.com/scandum/twinsort) with comments
  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    if (twinswap(values, order))
      tailmerge(values, 2, order);
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
          swap(values, index, index + 1);
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

  private static <T> void tailmerge(T[] values, int startSize, Comparator<? super T> order) {
    T[] swap = Arrays.copyOf(values, values.length / 2);
    int blockSize = startSize;
    while (blockSize < values.length) {
      for (int mergeStart = 0; mergeStart + blockSize < values.length; mergeStart +=
          blockSize * 2) {
        int lo = mergeStart;
        int leftTail = lo + blockSize - 1;

        if (order.compare(values[leftTail], values[leftTail + 1]) <= 0) {
          // the left and right subarrays are in order
          continue;
        }

        /**
         * set proper mid and hi pointers!
         */
        int mid, hi;
        if (mergeStart + blockSize * 2 <= values.length) {
          mid = blockSize;
          hi = lo + blockSize * 2;
        } else {
          // right block is too short, fix mid and hi pointers
          mid = values.length - (mergeStart + blockSize);
          hi = values.length;
        }

        /**
         * skip unnecessary merging!
         */
        int rightTail = hi - 1;

        while (order.compare(values[leftTail], values[rightTail]) <= 0) {
          // we don't need to merge if the last left is already less than our
          // current last right, so fix the pointers
          hi--;
          rightTail--;

          mid--;
        }

        // copy only the part we will merge from right subarray into swap memory
        int swapI = 0;
        int rightI = lo + blockSize;

        while (swapI < mid) {
          swap[swapI++] = values[rightI++];
        }
        swapI--; // swap index is now at the last element in swap

        /**
         * start merging left to right!
         */
        int lastL = lo + blockSize - 1;
        int lastR = hi - 1;

        if (order.compare(values[lo], values[lo + blockSize]) <= 0) {
          // current run is forward-like
          values[lastR--] = values[lastL--];

          while (swapI >= 0) {
            while (order.compare(values[lastL], swap[swapI]) > 0) {
              values[lastR--] = values[lastL--];
            }
            values[lastR--] = swap[swapI--];
          }
        } else {
          // current run is unknown distribution
          values[lastR--] = values[lastL--];

          while (lastL >= lo) {
            while (order.compare(values[lastL], swap[swapI]) <= 0) {
              values[lastR--] = swap[swapI--];
            }
            values[lastR--] = values[lastL--];
          }
          while (swapI >= 0) {
            values[lastR--] = swap[swapI--];
          }
        }
      }
      blockSize *= 2;
    }
  }

  private static <T> void reverseRange(T[] arr, int start, int end) {
    while (start < end) {
      T temp = arr[start];
      arr[start++] = arr[end];
      arr[end--] = temp;
    }
  }

  public static <T> void swap(T[] values, int i, int j) {
    T temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  }
}
