package cells;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Tests {

  private static int mismatches = 0;
  private static boolean exception = false;

  /*
  @Test
  public void testQuestion1() {

    System.out.println("================");
    System.out.println("Question 1 tests");
    System.out.println("================");

    MutableCell<String> stringCell = new MutableCell<>();
    assertTrue(!stringCell.isSet());
    stringCell.set("Hello");
    assertEquals("Hello", stringCell.get());
    assertTrue(stringCell.isSet());
    try {
      stringCell.set(null);
      reportExceptionShouldHaveBeenThrown();
    } catch (IllegalArgumentException exception) {
      // Exception expected
    }
    stringCell.set("World");
    assertEquals("World", stringCell.get());
    assertTrue(stringCell.isSet());

    MutableCell<MutableCell<Integer>> nestedIntegerCell = new MutableCell<>(new MutableCell<>(20));
    assertEquals(new Integer(20), nestedIntegerCell.get().get());
    assertTrue(nestedIntegerCell.isSet());
    nestedIntegerCell.set(new MutableCell<>(30));
    assertEquals(new Integer(30), nestedIntegerCell.get().get());

    try {
      new MutableCell<Void>(null);
      reportExceptionShouldHaveBeenThrown();
    } catch (IllegalArgumentException exception) {
      // Exception expected
    }

  }
  */

  /*
  @Test
  public void testQuestion2() {

    System.out.println("================");
    System.out.println("Question 2 tests");
    System.out.println("================");

    Exception anException = new Exception("An exeption to be wrapped in a cell");
    ImmutableCell<Exception> exceptionCell = new ImmutableCell<>(anException);
    try {
      exceptionCell.set(new Exception());
      reportExceptionShouldHaveBeenThrown();
    } catch (UnsupportedOperationException exception) {
      // Exception expected
    }
    assertTrue(anException == exceptionCell.get());
    try {
      new ImmutableCell<Void>(null);
      reportExceptionShouldHaveBeenThrown();
    } catch (IllegalArgumentException exception) {
      // Exception expected
    }
    final ImmutableCell<Integer> i1 = new ImmutableCell<>(20);
    final ImmutableCell<Integer> i2 = new ImmutableCell<>(30);
    final ImmutableCell<Integer> i3 = new ImmutableCell<>(20);
    final ImmutableCell<String> s1 = new ImmutableCell<>("Hello");
    final ImmutableCell<String> s2 = new ImmutableCell<>("World");
    final ImmutableCell<String> s3 = new ImmutableCell<>("Hello");
    final ImmutableCell<ImmutableCell<Integer>> nested1 = new ImmutableCell<>(i1);
    final ImmutableCell<ImmutableCell<Integer>> nested2 = new ImmutableCell<>(i2);
    final ImmutableCell<ImmutableCell<Integer>> nested3 = new ImmutableCell<>(i3);

    assertTrue(i1.equals(i1));
    assertTrue(i1.equals(i3));
    assertTrue(!i1.equals(i2));
    assertTrue(!i1.equals(s1));
    assertTrue(!i1.equals(new Integer(20)));
    assertTrue(!i1.equals(null));

    assertTrue(s1.equals(s1));
    assertTrue(s1.equals(s3));
    assertTrue(!s2.equals(s1));
    assertTrue(!s1.equals(nested1));
    assertTrue(!s3.equals(new Exception()));
    assertTrue(!s2.equals(null));

    assertTrue(nested1.equals(nested1));
    assertTrue(nested1.equals(nested3));
    assertTrue(!nested2.equals(nested1));

  }
  */

  /*
  @Test
  public void testQuestion3() {

    System.out.println("================");
    System.out.println("Question 3 tests");
    System.out.println("================");

    BackedUpMutableCell<Integer> boundedBackup = new BackedUpMutableCell<>(4);
    BackedUpMutableCell<String> noBackup = new BackedUpMutableCell<>(0);
    BackedUpMutableCell<String> unboundedBackup = new BackedUpMutableCell<>();

    assertTrue(!boundedBackup.isSet());
    assertTrue(!noBackup.isSet());
    assertTrue(!unboundedBackup.isSet());

    assertTrue(!boundedBackup.hasBackup());
    assertTrue(!noBackup.hasBackup());
    assertTrue(!unboundedBackup.hasBackup());

    try {
      unboundedBackup.revertToPrevious();
      reportExceptionShouldHaveBeenThrown();
    } catch (UnsupportedOperationException exception) {
      // Exception expected
    }

    boundedBackup.set(5);
    noBackup.set("Hello");
    unboundedBackup.set("Monday");

    assertTrue(boundedBackup.isSet());
    assertTrue(noBackup.isSet());
    assertTrue(unboundedBackup.isSet());

    assertTrue(!boundedBackup.hasBackup());
    assertTrue(!noBackup.hasBackup());
    assertTrue(!unboundedBackup.hasBackup());

    boundedBackup.set(6);
    noBackup.set("World");
    unboundedBackup.set("Tuesday");

    assertTrue(boundedBackup.hasBackup());
    assertTrue(!noBackup.hasBackup());
    assertTrue(unboundedBackup.hasBackup());

    for (int i = 0; i < 10; i++) {
      unboundedBackup.set(String.valueOf(i));
      boundedBackup.set(i);
    }

    for (int i = 0; i < 4; i++) {
      boundedBackup.revertToPrevious();
    }
    assertEquals(new Integer(5), boundedBackup.get());
    assertTrue(!boundedBackup.hasBackup());

    for (int i = 0; i < 10; i++) {
      unboundedBackup.revertToPrevious();
    }
    assertEquals("Tuesday", unboundedBackup.get());

  }
  */

  /*
  @Test
  public void testQuestion4() {

    System.out.println("================");
    System.out.println("Question 4 tests");
    System.out.println("================");

    BackedUpMutableCell<String> s1 = new BackedUpMutableCell<>();
    BackedUpMutableCell<String> s2 = new BackedUpMutableCell<>(3);

    BackedUpCellComparator<String> comparator = new BackedUpCellComparator<>(
        new StringComparator());

    assertEquals(0, comparator.compare(s1, s2));

    s1.set("A");
    assertTrue(comparator.compare(s1, s2) > 0);

    s2.set("A");
    assertEquals(0, comparator.compare(s1, s2));

    s1.set("B");
    assertTrue(comparator.compare(s2, s1) < 0);

    s1.set("C");
    assertTrue(comparator.compare(s2, s1) < 0);

    s2.set("B");
    assertTrue(comparator.compare(s1, s2) > 0);

    s2.set("C");
    assertEquals(0, comparator.compare(s1, s2));

    s1.set("D");
    assertTrue(comparator.compare(s1, s2) > 0);

    s2.set("D");
    assertEquals(0, comparator.compare(s1, s2));

    s1.set("E");
    assertTrue(comparator.compare(s1, s2) > 0);

    s2.set("E");
    assertTrue(comparator.compare(s2, s1) < 0);

  }
  */

  private static void reportExceptionShouldHaveBeenThrown() {
    throw new RuntimeException("UnsupportedOperationException should have been thrown");
  }

}
