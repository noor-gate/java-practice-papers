package aeroplane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeatAllocator {

	private Map<Seat, Passenger> allocation;

	private static final String CREW = "crew";
	private static final String BUSINESS = "business";
	private static final String ECONOMY = "economy";
	
	public SeatAllocator() {
		allocation = new HashMap<Seat, Passenger>();
	}

	@Override
	public String toString() {
		return allocation.toString();
	}
	
	private void allocateInRange(Passenger passenger,
			Seat first, Seat last) throws AeroplaneFullException {

		Seat current = first;
		while (!allocation.containsValue(passenger)) {
			if (allocation.get(current) != null || (allocation.get(current) == null
					&& current.isEmergencyExit() && !passenger.isAdult())) {
				if (current.equals(last)) {
					throw new AeroplaneFullException();
				}
				current = current.next();
			} else {
				allocation.put(current, passenger);
			}
		}
	}

	private static String readStringValue(BufferedReader br) throws MalformedDataException, IOException {

		String result = br.readLine();
		
		if(result == null) {
			throw new MalformedDataException();
		}
		
		return result;
		
	}

	private static int readIntValue(BufferedReader br)
			throws MalformedDataException, IOException {
		try {
			return Integer.parseInt(readStringValue(br));
		} catch(NumberFormatException e) {
			throw new MalformedDataException();
		}
	}

	private static Luxury readLuxuryValue(BufferedReader br)
			throws MalformedDataException, IOException {
		try {
			return Luxury.valueOf(readStringValue(br));
		} catch(IllegalArgumentException e) {
			throw new MalformedDataException();
		}
	}

	
	public void allocate(String filename) throws IOException, AeroplaneFullException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));

		String line;
		while((line = br.readLine()) != null) {
			try {
				if(line.equals(CREW)) {
					allocateCrew(br);
				} else if(line.equals(BUSINESS)) {
					allocateBusiness(br);
				} else if(line.equals(ECONOMY)) {
					allocateEconomy(br);
				} else {
					throw new MalformedDataException();
				}
			} catch(MalformedDataException e) {
				System.out.println("Skipping malformed line of input");
			}
		}
		
	}
	
	private void allocateCrew(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);

		Passenger crew = new CrewMember(firstName, lastName);
		allocateInRange(crew, new Seat(1, 'A'), new Seat(1, 'F'));

	}

	private void allocateBusiness(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		int age = readIntValue(br);
		Luxury luxury = readLuxuryValue(br);

		Passenger business = new BusinessClassPassenger(firstName, lastName, age, luxury);
		allocateInRange(business, new Seat(2, 'A'), new Seat(15, 'F'));

	}

	private void allocateEconomy(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		int age = readIntValue(br);

		Passenger economy = new EconomyClassPassenger(firstName, lastName, age);
		allocateInRange(economy, new Seat(16, 'A'), new Seat(50, 'F'));

	}

	public void upgrade() {
		Seat first = new Seat(16, 'A');
		Seat last = new Seat(50, 'F');
		Seat current = first;
		while (!current.equals(last) && allocation.get(current) != null) {
			try {
				Passenger passenger = allocation.get(current);
				allocation.remove(current);
				allocateInRange(passenger, new Seat(2, 'A'), new Seat(15, 'F'));
				current = current.next();
			} catch (Exception e) {
				System.out.println("Upgrade is not available");
				break;
			}
		}

	}

	// TODO: Section A, Task 5 - add upgrade method

}
