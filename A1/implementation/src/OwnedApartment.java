import java.io.Serializable;
import java.text.DecimalFormat;


public class OwnedApartment extends Apartment{
    private double operating_costs;
    private double maintenace_reserve;

    public OwnedApartment(Integer id, double area, double num_rooms, int floor, int year, 
                    Integer postal_code, String street, Integer house_number, 
                    Integer arpartment_number, double operating_costs, double maintenace_reserve) {
        super(id, area, num_rooms, floor, year, postal_code, street, house_number, arpartment_number);
        
        this.operating_costs = operating_costs;
        this.maintenace_reserve = maintenace_reserve;
    }



    public double getTotalCost() {
        double level_cost = (getFloor() *0.02);
        return  (operating_costs + maintenace_reserve)*getArea() * (1 + level_cost);
    }



    @Override
    public String toString() {
        DecimalFormat df = Apartment.getDecimalFormat();  // Use DecimalFormat for 2 decimal places
        
        return "Type:              OA\n" +
            "Id:                " + this.getId() + "\n" +
            "Area:              " + df.format(this.getArea()) + "\n" +
            "Rooms:             " + df.format(getNumRooms()) + "\n" +
            "Floor:             " + this.getFloor() + "\n" +
            "Year Built:        " + this.getYear() + "\n" +
            "Postal Code:       " + this.getPostalCode() + "\n" +
            "Street:            " + this.getStreet() + "\n" +
            "House Number:      " + this.getHouseNumber() + "\n" +
            "Apartment Number:  " + this.getArpartmentNumber() + "\n" +
            "Operating Costs:   " + df.format(operating_costs) + "\n" +
            "Reserve Fund:      " + df.format(maintenace_reserve);
    }

    

}