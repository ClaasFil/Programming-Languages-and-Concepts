import java.util.List;

public interface PropertyManagementDAO {

    public List<Apartment> getApartments();

    public Apartment getApartmentById(int id);

    public void saveApartment(Apartment apartment);

    public void deleteApartment(int id);
}