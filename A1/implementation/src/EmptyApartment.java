import java.text.DecimalFormat;


public class EmptyApartment extends Apartment {
    private Integer empty_since;


    public EmptyApartment(Integer id, double area, int num_rooms, int floor, int year, 
                    Integer postal_code, String street, Integer house_number, 
                    Integer arpartment_number, Integer empty_since) {
        super(id, area, num_rooms, floor, year, postal_code, street, house_number, arpartment_number);
        
        this.empty_since = empty_since;
    }

    public double getTotalCost() {
        Double empty_years = Double.valueOf(2024 - this.empty_since);

        return empty_years*this.getArea()*3.2 + 1.5* Double.valueOf(this.getFloor());

    }


    @Override
    public String toString() {
        DecimalFormat df = Apartment.getDecimalFormat();  // Use DecimalFormat for 2 decimal places
        
        return "Type:              RA\n" +
            "Id:                " + this.getId() + "\n" +
            "Area:              " + df.format(this.getArea()) + "\n" +
            "Rooms:             " + getNumRooms() + "\n" +
            "Floor:             " + this.getFloor() + "\n" +
            "Year Built:        " + this.getYear() + "\n" +
            "Postal Code:       " + this.getPostalCode() + "\n" +
            "Street:            " + this.getStreet() + "\n" +
            "House Number:      " + this.getHouseNumber() + "\n" +
            "Apartment:         " + this.getArpartmentNumber() + "\n" +
            "Empty since:       " + df.format(empty_since);
    }


}
