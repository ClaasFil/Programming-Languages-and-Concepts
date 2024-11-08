import java.text.DecimalFormat;


public class BusApartment extends Apartment {
    private double rent;
    private int tenants;


    public BusApartment(Integer id, double area, int num_rooms, int floor, int year, 
                    Integer postal_code, String street, Integer house_number, 
                    Integer arpartment_number, double rent, int tenants) {
        super(id, area, num_rooms, floor, year, postal_code, street, house_number, arpartment_number);
        
        this.rent = rent;
        this.tenants = tenants;
    }

    public double getTotalCost() {
        double tenants_cost = Math.min((2024 - this.getYear())*0.03, 25);
        return  rent * getArea() +  tenants_cost;
    }


    @Override
    public String toString() {
        DecimalFormat df = Apartment.getDecimalFormat();  // Use DecimalFormat for 2 decimal places
        
        return "Type:              BA\n" +
            "Id:                " + this.getId() + "\n" +
            "Area:              " + df.format(this.getArea()) + "\n" +
            "Rooms:             " + getNumRooms() + "\n" +
            "Floor:             " + this.getFloor() + "\n" +
            "Year Built:        " + this.getYear() + "\n" +
            "Postal Code:       " + this.getPostalCode() + "\n" +
            "Street:            " + this.getStreet() + "\n" +
            "House Number:      " + this.getHouseNumber() + "\n" +
            "Apartment:         " + this.getArpartmentNumber() + "\n" +
            "Rent/m2:           " + df.format(rent) + "\n" +
            "Number of Tenants: " + tenants;
    }


}
