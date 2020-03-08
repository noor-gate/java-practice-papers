package aeroplane;

public class EconomyClassPassenger extends Passenger {

    private int age;

    public EconomyClassPassenger(String firstName, String surname, int age) {
        super(firstName, surname);
        assert age > 0;
        this.age = age;
    }

    @Override
    public boolean isAdult() {
        return age >= 18;
    }

    @Override
    public String toString() {
        return super.toString() + "economy class";
    }
}
