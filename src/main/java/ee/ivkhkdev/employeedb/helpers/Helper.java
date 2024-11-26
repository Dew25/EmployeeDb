package ee.ivkhkdev.employeedb.helpers;

import java.util.List;
import java.util.Optional;

public interface Helper<T> {
    Optional<T> create();
    boolean printList(List<T> ts);

}
