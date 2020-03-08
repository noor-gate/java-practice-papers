package aeroplane;

public class BusinessClassPassenger extends Passenger {

    private int age;
    private Luxury luxury;

    public BusinessClassPassenger(String firstName, String surname, int age, Luxury luxury) {
        super(firstName, surname);
        assert age > 0;
        this.age = age;
        this.luxury = luxury;
    }

    @Override
    public boolean isAdult() {
        return age >= 18;
    }

    @Override
    public String toString() {
        return super.toString() + "business class";
    }
}
