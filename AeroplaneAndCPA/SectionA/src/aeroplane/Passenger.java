package aeroplane;

public abstract class Passenger {

    private String firstName;
    private String surname;

    public Passenger(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public boolean isAdult() {
        return true;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + surname + "; Type: ";
    }

}
