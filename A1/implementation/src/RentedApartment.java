import java.text.DecimalFormat;


public class RentedApartment extends Apartment {
    private double rent;
    private int tenants;


    public RentedApartment(Integer id, double area, double num_rooms, int floor, int year, 
                    Integer postal_code, String street, Integer house_number, 
                    Integer arpartment_number, double rent, int tenants) {
        super(id, area, num_rooms, floor, year, postal_code, street, house_number, arpartment_number);
        
        this.rent = rent;
        this.tenants = tenants;
    }

    public double getTotalCost() {

        double tenants_cost = Math.max(0., Math.min(10., (tenants-1)*2.5));
        return  rent * getArea() +  tenants_cost;
    }

    @Override
    public String toString(){
        DecimalFormat df = Apartment.getDecimalFormat();;
        return "OA, ID: " + getId() + ", Area: " + df.format(getArea()) + ", Num. Rooms: " + df.format(getNumRooms()) + 
        ", Floor: " + getFloor() + ", Year: " + getAge() + ", Postal Code: " + getPostalCode() + 
        ", Street: " + getStreet() + "House Number: " + getHouseNumber() + ", Apartment Number: " + 
        getArpartmentNumber() + ", Monthly Rent: " + df.format(rent) + ", Number of Tenants: " +
        df.format(tenants) + ", Total Cost: " + df.format(getTotalCost());
        
    }


}
