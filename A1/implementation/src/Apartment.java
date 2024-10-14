import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Apartment implements Serializable{
	private Integer id;
	private double area;
	private double num_rooms;
	private Integer floor;
	private Integer year;
	private Integer postal_code;
	private String street;
	private Integer house_number;
	private Integer arpartment_number;





	public Apartment(Integer id, double area, double num_rooms, int floor, int year, 
					Integer postal_code, String street, Integer house_number, 
					Integer arpartment_number) {

		
		
		//System.out.println("Apartment constructor called");
		// Input param test

		if (id == null || id < 0) {
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
		this.id = id;

		if (area == 0 || area < 0) {
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
		this.area = area;

		if (num_rooms == 0 || num_rooms < 0) {
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
		this.num_rooms = num_rooms;
		
		if ( floor < 0) {
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
		this.floor = floor;

		if (2024 < year|| year < 0) {
			throw new IllegalArgumentException("Invalid year of construction.");
		}
		this.year = year;

		if (postal_code == null || postal_code < 0) {
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
		this.postal_code = postal_code;
		
		if (street == null || street.isEmpty()) {
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
		this.street = street;
		
		if (house_number == null || house_number < 0) {
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
		this.house_number = house_number;
		
		if (arpartment_number == null || arpartment_number < 0) {
			throw new IllegalArgumentException("Error: Invalid parameter.");
		}
		this.arpartment_number = arpartment_number;



	}

    public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}
	

	public Integer getAge() {
		return 2024 - year;
	}

	public abstract double getTotalCost(); 
	
	public abstract String toString();

	// Getters
	public Integer getId() {
		return id;
	}
	public double getArea() {
		return area;
	}
	public double getNumRooms() {
		return num_rooms;
	}
	public int getFloor() {
		return floor;
	}
	public int getYear() {
		return year;
	}
	public double getPostalCode() {
		return postal_code;
	}
	public String getStreet() {
		return street;
	}
	public Integer getHouseNumber() {
		return house_number;
	}
	public Integer getArpartmentNumber() {
		return arpartment_number;
	}


	// Setters
	//TODO: Do I really need the setters?
	// if so Input check!!!






}