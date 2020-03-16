package generalmatrices.operators;

public interface RingElement<T> {

  T sum(T other);

  T product(T other);

}
