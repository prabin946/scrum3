package interfaces;

import java.util.List;

public interface Notification<T> {


    boolean message(List<T> items);
}
