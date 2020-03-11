package cells;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {

  @Override
  public int compare(String first, String second) {
    return first.compareTo(second);
  }

}
