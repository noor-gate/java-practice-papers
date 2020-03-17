package generalmatrices.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Matrix<T> {

    private T[][] matrix;

    @SuppressWarnings("unchecked")
    public Matrix(List<T> elements) {
        if (elements.size() == 0) {
            throw new IllegalArgumentException();
        }
        int order = (int) Math.sqrt(elements.size());
        matrix = (T[][]) new Object[order][order];
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                matrix[i][j] = elements.get(i * order + j);
            }
        }
    }

    public T get(int row, int col) {
        return matrix[row][col];
    }

    public int getOrder() {
        return matrix.length;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (T[] ts : matrix) {
            str.append("[");
            for (int j = 0; j < matrix.length; j++) {
                str.append(ts[j].toString());
                if (j != matrix.length - 1) {
                    str.append(" ");
                }
            }
            str.append("]");
        }
        str.append("]");
        return str.toString();
    }

    public Matrix<T> sum(Matrix<T> other, BinaryOperator<T> elementSum) {
        List<T> res = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                res.add(elementSum.apply(get(i, j), other.get(j, i)));
            }
        }
        return new Matrix<>(res);
    }

    public Matrix<T> product(Matrix<T> other, BinaryOperator<T> elementSum, BinaryOperator<T> elementProduct) {
        List<T> res = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                T first = elementProduct.apply(get(i, 0), other.get(0, j));
                T second = elementProduct.apply(get(i,  1), other.get(1, j));
                T sum = elementSum.apply(first, second);
                for (int k = 2; k < matrix.length; k++) {
                    T prod = elementProduct.apply(get(i,  k), other.get(k, j));
                    sum = elementSum.apply(sum, prod);
                }
                res.add(sum);
            }
        }
        return new Matrix<>(res);
    }

    // TODO: populate as part of Question 3
}
