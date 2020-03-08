package aeroplane;

public class CrewMember extends Passenger {

    public CrewMember(String firstName, String surname) {
        super(firstName, surname);
    }

    @Override
    public String toString() {
        return super.toString() + "crew";
    }
}
