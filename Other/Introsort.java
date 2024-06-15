package Other;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.swap;

public class Introsort<T extends Comparable<T>> {

    // Maksymalna głębokość rekursji, po której następuje przełączenie na heapsorta
    private int maxRecursionDepth;
    // Wielkość podlisty, przy której algorytm przełącza się na insert sort
    private final int insertSortListSize = 6;
    private List<T> list;

    public void sortList(List<T> list){
        this.list = list;
        this.maxRecursionDepth = (int) (Math.log(list.size()) / Math.log(2)) * 2;  // Zwiększenie głębokości, aby bardziej odpowiadało praktyce
        sort(0, list.size() - 1, 0);
    }

    private void sort(int sublistBegId, int sublistEndId, int recursionDepth) {
        if (sublistBegId >= sublistEndId) {
            return;
        }
        else if (sublistEndId - sublistBegId + 1 <= insertSortListSize) {
            insertSort(sublistBegId, sublistEndId);
        } else if (recursionDepth >= maxRecursionDepth) {
            heapSort(sublistBegId, sublistEndId, recursionDepth);
        } else {

            quickSort(sublistBegId, sublistEndId, recursionDepth);
        }
    }

    private void insertSort(int sublistBegId, int sublistEndId) {
        for (int i = sublistBegId; i < sublistEndId; i++) {
            int insertMin = i;
            for (int j = i + 1; j < sublistEndId + 1; j++) {
                if (list.get(j).compareTo(list.get(insertMin)) < 0) insertMin = j;
            }
            if (insertMin != i) swap(list, insertMin, i);
        }
    }

    private void insertSort(List<T> list, int sublistBegId, int sublistEndId) {
        for (int i = sublistBegId; i < sublistEndId; i++) {
            int insertMin = i;
            for (int j = i + 1; j < sublistEndId + 1; j++) {
                if (list.get(j).compareTo(list.get(insertMin)) < 0) insertMin = j;
            }
            if (insertMin != i) swap(list, insertMin, i);
        }
    }

    private void heapSort(int sublistBegId, int sublistEndId, int recursionDepth) {
        int sublistSize = sublistEndId - sublistBegId + 1;
        int unsortedCount = sublistSize;

        for (int i = sublistSize / 2 - 1; i >= 0; i--) {
            heapify(sublistSize, i, sublistBegId);
        }

        for (int i = sublistSize - 1; i > 0; i--) {
            if (unsortedCount == insertSortListSize) {
                sort(sublistBegId, sublistBegId + insertSortListSize - 1, recursionDepth);
                return;
            }
            swap(list , sublistBegId, sublistBegId + i);
            unsortedCount--;
            heapify(i, 0, sublistBegId);
        }
    }

    private void heapify(int n, int i, int offset) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && list.get(left + offset).compareTo(list.get(largest + offset)) > 0) {
            largest = left;
        }

        if (right < n && list.get(right + offset).compareTo(list.get(largest + offset)) > 0) {
            largest = right;
        }

        if (largest != i) {
            swap(list, i + offset, largest + offset);
            heapify(n, largest, offset);
        }
    }

    private void quickSort(int sublistBegId, int sublistEndId, int recursionDepth) {

        T qsort_pivot = getQuickSortPivot(sublistBegId, sublistEndId);

        int j = sublistEndId + 1;
        int i = sublistBegId - 1;

        while (i < j) {
            for (j = j - 1; list.get(j).compareTo(qsort_pivot) > 0; j--) {
            }
            for (i = i + 1; list.get(i).compareTo(qsort_pivot) < 0; i++) {
            }
            if (i < j) {
                swap(list, i, j);
            }
        }

        sort(sublistBegId, j, recursionDepth);
        sort(j + 1, sublistEndId, recursionDepth);
    }

    private T getQuickSortPivot(int sublistBegId, int sublistEndId) {
        List<T> med = Arrays.asList(
                list.get(sublistBegId),
                list.get(sublistBegId + ((sublistEndId - sublistBegId) / 2)),
                list.get(sublistEndId)
        );
        insertSort(med , 0 , 2);

        return med.get(1);
    }
}
