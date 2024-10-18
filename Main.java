import java.util.*;

public class Main {

  static int countSwapsToSort(int[] arr, boolean ascending) {
    int n = arr.length;
    List<Pair> indexedArr = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      indexedArr.add(new Pair(arr[i], i));
    }

    if (ascending) {
      indexedArr.sort(Comparator.comparingInt(a -> a.value));
    } else {
      indexedArr.sort((a, b) -> b.value - a.value);
    }

    boolean[] visited = new boolean[n];
    Arrays.fill(visited, false);
    int swapCount = 0;

    for (int i = 0; i < n; i++) {
      if (visited[i] || indexedArr.get(i).index == i) {
        continue;
      }

      int cycleSize = 0;
      int j = i;

      while (!visited[j]) {
        visited[j] = true;
        j = indexedArr.get(j).index;
        cycleSize++;
      }

      if (cycleSize > 1) {
        swapCount += (cycleSize - 1);
      }
    }

    return swapCount;
  }

  static int minSwapsToMakeBeautiful(int[] arr) {
    int ascendingSwaps = countSwapsToSort(arr, true);
    int descendingSwaps = countSwapsToSort(arr, false);

    return Math.min(ascendingSwaps, descendingSwaps);
  }

  static class Pair {
    int value;
    int index;

    Pair(int value, int index) {
      this.value = value;
      this.index = index;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextLine()    }
    System.out.println(minSwapsToMakeBeautiful(arr));
  }
}