package generalmatrices.examples;

import generalmatrices.matrix.Matrix;
import generalmatrices.pair.PairWithOperators;
import java.util.List;

public class Example {

  public static Matrix<PairWithOperators> multiplyPairMatrices(
        List<Matrix<PairWithOperators>> matrices) {
    // TODO: implement as part of Question 4
    return matrices.stream()
            .reduce((m1, m2) -> m1.product(m2,
                    PairWithOperators::sum,
                    PairWithOperators::product))
            .get();
  }

}
