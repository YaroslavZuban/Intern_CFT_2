import java.util.Collections;
import java.util.List;

public class SortTrue {
    public static boolean checkingElementsSorting(int i, List t, List t1, List t2, SortType sort) throws Exception {
        Collections.reverse(t2);
        sort.setList(t);
        sort.sortPlay();

        if (!t1.equals(sort.getList()) && !t2.equals(sort.getList())) {
            throw new Exception("Please check that the file " + FileHandling.nameFile.get(i) + " sorted correctly!");
        }

        return false;
    }
}
