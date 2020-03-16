package generalmatrices.pair;

public class Pair {

  private final Integer coordX;
  private final Integer coordY;

  public Pair(Integer coordX, Integer coordY) {
    this.coordX = coordX;
    this.coordY = coordY;
  }

  public Integer getCoordX() {
    return coordX;
  }

  public Integer getCoordY() {
    return coordY;
  }

  @Override
  public String toString() {
    return "(" + coordX + ", " + coordY + ")";
  }

}
