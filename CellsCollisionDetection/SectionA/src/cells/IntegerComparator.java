package cells;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {

  @Override
  public int compare(Integer first, Integer second) {
    return first.compareTo(second);
  }

}
