import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortType {
    public List<String> array;

    public List getList() {
        return array;
    }

    public void setList(List list) {
        array = list;
    }

    public void sortPlay() {
        sort(array);
    }

    public void sortWatch(List<String> temp, int i) throws IOException {
        List<String> t = new ArrayList<>();
        List<String> t1 = new ArrayList<>();
        List<String> t2 = new ArrayList<>();

        for (int j = 0; j < temp.size(); j++) {
            t.add(temp.get(j));
            t1.add(temp.get(j));
            t2.add(temp.get(j));
        }

        try {
            if (SortTrue.checkingElementsSorting(i, t, t1, t2, this)) return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void merge(List<String> arr, List<String> l, List<String> r) {
        int i = 0;
        int j = 0;
        int idx = 0;

        while (i < l.size() && j < r.size()) {

            if (FileHandling.type.equals("-s")) {
                if (sortType() == 1) {
                    if (r.get(j).compareTo(l.get(i)) > 0) {
                        arr.set(idx, l.get(i));
                        i++;
                    } else {
                        arr.set(idx, r.get(j));
                        j++;
                    }
                } else {
                    if (r.get(j).compareTo(l.get(i)) < 0) {
                        arr.set(idx, l.get(i));
                        i++;
                    } else {
                        arr.set(idx, r.get(j));
                        j++;
                    }
                }
            } else {
                if (sortType() == 1) {
                    if (integerList(r.get(j), l.get(i))) {
                        arr.set(idx, l.get(i));
                        i++;
                    } else {
                        arr.set(idx, r.get(j));
                        j++;
                    }
                } else {
                    if (integerList(l.get(i), r.get(j))) {
                        arr.set(idx, l.get(i));
                        i++;
                    } else {
                        arr.set(idx, r.get(j));
                        j++;
                    }
                }
            }

            idx++;
        }

        for (int il = i; il < l.size(); il++) {
            arr.set(idx++, l.get(il));
        }

        for (int ir = j; ir < r.size(); ir++) {
            arr.set(idx++, r.get(ir));
        }
    }

    private void sort(List<String> list) {
        int n = list.size();

        if (list.size() == 1) // выход из рекурсии - массив из 1 элемента отсортирован по определению
            return;

        int mid = list.size() / 2; //

        ArrayList<String> l = new ArrayList<>();
        ArrayList<String> r = new ArrayList<>();

        try {
            for (int i = 0; i < mid; i++) {
                l.add(list.get(i));
            }

            for (int i = mid; i < n; i++) {
                r.add(list.get(i));
            }

            sort(l);    // сортировка 1-й половины массива
            sort(r); // сортировка 2-й половины массива
            merge(list, l, r);
        } catch (Exception e) {
            return;
        }
    }

    private boolean integerList(String number1, String number2) {
        if (Integer.valueOf(number1).compareTo(Integer.valueOf(number2)) > 0) {
            return true;
        }

        return false;
    }

    private int sortType() {
        if (FileHandling.kindSorting.equals("-d")) {
            return 2;
        }

        return 1;
    }
}
