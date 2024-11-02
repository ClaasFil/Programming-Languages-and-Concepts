import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
//import java.util.Locale;
import java.io.Serializable;

public abstract class Apartment implements Serializable{
	private Integer id;
	private double area;
	private Integer num_rooms;
	private Integer floor;
	private Integer year;
	private Integer postal_code;
	private String street;
	private Integer house_number;
	private Integer arpartment_number;





	public Apartment(Integer id, double area, Integer num_rooms, int floor, int year, 
					Integer postal_code, String street, Integer house_number, 
					Integer arpartment_number) {

		
		
		this.id = id;
		this.area = area;
		this.num_rooms = num_rooms;
		this.floor = floor;
		this.year = year;
		this.postal_code = postal_code;
		this.street = street;
		this.house_number = house_number;
		this.arpartment_number = arpartment_number;



	}

    public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}
	

	public Integer getAge() {
		return  year;
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
	public Integer getNumRooms() {
		return num_rooms;
	}
	public int getFloor() {
		return floor;
	}
	public int getYear() {
		return year;
	}
	public Integer getPostalCode() {
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





