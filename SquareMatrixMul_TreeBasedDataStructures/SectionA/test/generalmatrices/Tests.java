package generalmatrices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import generalmatrices.examples.Example;
import generalmatrices.matrix.Matrix;
import generalmatrices.operators.RingElement;
import generalmatrices.pair.Pair;
import generalmatrices.pair.PairWithOperators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream; // Unused, but here to ensure that Java 8 or higher is used.
import org.junit.Test;

public class Tests {

  /*
  @Test
  public void testQuestion1() {

    final Matrix<String> stringMatrix = new Matrix<>(
          Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i"));

    final Matrix<Integer> integerMatrix = new Matrix<>(
          Arrays.asList(1, 2, 3, 4));

    final Matrix<Matrix<Integer>> integerMatrixMatrix = new Matrix<>(
          Arrays.asList(
                new Matrix<>(Arrays.asList(1, 2, 3, 4)),
                new Matrix<>(Arrays.asList(2, 3, 4, 1)),
                new Matrix<>(Arrays.asList(3, 4, 1, 2)),
                new Matrix<>(Arrays.asList(4, 1, 2, 3))));

    assertEquals("f", stringMatrix.get(1, 2));
    assertEquals(new Integer(4), integerMatrix.get(1, 1));
    assertEquals(new Integer(1), integerMatrixMatrix.get(1, 1).get(0, 1));

    assertEquals(3, stringMatrix.getOrder());
    assertEquals(2, integerMatrix.getOrder());
    assertEquals(2, integerMatrixMatrix.getOrder());
    assertEquals(2, integerMatrixMatrix.get(0, 0).getOrder());

    try {
      new Matrix<Integer>(new ArrayList<Integer>());
      throw new RuntimeException("Expected IllegalArgumentException on attempt to create "
            + "empty matrix");
    } catch (IllegalArgumentException exception) {
      // Good: exception should be thrown
    }

    assertEquals("[[a b c][d e f][g h i]]", stringMatrix.toString());
    assertEquals("[[1 2][3 4]]", integerMatrix.toString());
    assertEquals("[[[[1 2][3 4]] [[2 3][4 1]]][[[3 4][1 2]] [[4 1][2 3]]]]",
          integerMatrixMatrix.toString());

  }
  */

  /*
  @Test
  public void testQuestion2() {
    final PairWithOperators p1 = new PairWithOperators(2, 3);
    final PairWithOperators p2 = new PairWithOperators(4, 5);
    assertTrue(p1 instanceof Pair);
    assertTrue(p1 instanceof RingElement);
    assertEquals(new Integer(6), p1.sum(p2).getCoordX());
    assertEquals(new Integer(8), p1.sum(p2).getCoordY());
    assertEquals(new Integer(8), p1.product(p2).getCoordX());
    assertEquals(new Integer(15), p1.product(p2).getCoordY());
  }
  */

  /*
  @Test
  public void testQuestion3() {

    final Matrix<Integer> integerMatrix1 = new Matrix<>(
          Arrays.asList(-1, 5, 8, -42));
    final Matrix<Integer> integerMatrix2 = new Matrix<>(
          Arrays.asList(2, 0, -4, 127));

    final Matrix<String> stringMatrix1 = new Matrix<>(
          Arrays.asList("hi", "fi", "wi", "fi"));
    final Matrix<String> stringMatrix2 = new Matrix<>(
          Arrays.asList("si", "fi", "py", "py"));

    final Matrix<Boolean> booleanMatrix1 = new Matrix<>(
          Arrays.asList(true, false, false, false, true, false, false, false, true));
    final Matrix<Boolean> booleanMatrix2 = new Matrix<>(
          Arrays.asList(true, false, true, false, true, false, true, true, false));

    final Matrix<Integer> integerMatrixMaxSum =
          integerMatrix1.sum(integerMatrix2, Math::max);
    assertEquals(new Integer(2), integerMatrixMaxSum.get(0, 0));
    assertEquals(new Integer(5), integerMatrixMaxSum.get(0, 1));
    assertEquals(new Integer(8), integerMatrixMaxSum.get(1, 0));
    assertEquals(new Integer(127), integerMatrixMaxSum.get(1, 1));

    final Matrix<String> stringMatrixProduct =
          stringMatrix1.product(stringMatrix2, String::concat,
                (a, b) -> a + "!" + b);
    assertEquals("hi!sifi!py", stringMatrixProduct.get(0, 0));
    assertEquals("hi!fifi!py", stringMatrixProduct.get(0, 1));
    assertEquals("wi!sifi!py", stringMatrixProduct.get(1, 0));
    assertEquals("wi!fifi!py", stringMatrixProduct.get(1, 1));

    final Matrix<Boolean> booleanMatrixProduct =
          booleanMatrix1.product(booleanMatrix2, Boolean::logicalOr, Boolean::logicalAnd);
    assertEquals(true, booleanMatrixProduct.get(0, 0));
    assertEquals(false, booleanMatrixProduct.get(0, 1));
    assertEquals(true, booleanMatrixProduct.get(0, 2));
    assertEquals(false, booleanMatrixProduct.get(1, 0));
    assertEquals(true, booleanMatrixProduct.get(1, 1));
    assertEquals(false, booleanMatrixProduct.get(1, 2));
    assertEquals(true, booleanMatrixProduct.get(2, 0));
    assertEquals(true, booleanMatrixProduct.get(2, 1));
    assertEquals(false, booleanMatrixProduct.get(2, 2));

  }
  */

  /*
  @Test
  public void testQuestion4() {

    final List<Matrix<PairWithOperators>> pairMatrices = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      final List<PairWithOperators> firstPairs = new ArrayList<>();
      final List<PairWithOperators> secondPairs = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        firstPairs.add(new PairWithOperators(i + j, -(i + j)));
      }
      pairMatrices.add(new Matrix<>(firstPairs));
    }
    final Matrix<PairWithOperators> productOfPairMatrices = Example
          .multiplyPairMatrices(pairMatrices);
    assertEquals(new Integer(2416), productOfPairMatrices.get(0, 0).getCoordX());
    assertEquals(new Integer(-2416), productOfPairMatrices.get(0, 0).getCoordY());
    assertEquals(new Integer(2889), productOfPairMatrices.get(0, 1).getCoordX());
    assertEquals(new Integer(-2889), productOfPairMatrices.get(0, 1).getCoordY());
    assertEquals(new Integer(9424), productOfPairMatrices.get(1, 0).getCoordX());
    assertEquals(new Integer(-9424), productOfPairMatrices.get(1, 0).getCoordY());
    assertEquals(new Integer(11269), productOfPairMatrices.get(1, 1).getCoordX());
    assertEquals(new Integer(-11269), productOfPairMatrices.get(1, 1).getCoordY());

  }
  */


}
