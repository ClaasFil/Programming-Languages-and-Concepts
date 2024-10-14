import java.util.List;
import java.util.stream.Collectors; 
import java.util.Map;
import java.util.Comparator;
import java.text.DecimalFormat;


public class PropertyManagement{
    private PropertyManagementDAO dao;

    public PropertyManagement(PropertyManagementDAO dao){
        this.dao = dao;
    }

    public List<Apartment> get_info_all_aptm(){
        List<Apartment> apartments = dao.getApartments();
        return apartments;
    }

    public Apartment get_info_aptm_by_id(int id){
        Apartment apartment = dao.getApartmentById(id);
        return apartment;
    }

    public void save_aptm(Apartment apartment){
        dao.saveApartment(apartment);
    }

    public void delete_aptm(int id){
        dao.deleteApartment(id);
    }

    public Map<String, Long> count_aptm(){
        List<Apartment> apartments = dao.getApartments();

        Map<String, Long> counts = apartments.stream()
            .collect(Collectors.groupingBy(
                apartment -> apartment.getClass().getSimpleName(),
                Collectors.counting()
            ));
        return counts;
    }


    public String avg_costs(){
        DecimalFormat df = Apartment.getDecimalFormat();;
        List<Apartment> aptm  = dao.getApartments();
        double res = aptm.stream().mapToDouble(Apartment::getTotalCost).average().orElse(0.0);
        return df.format(res);
    }

    public List<Apartment> oldest_aptm(){
        List<Apartment> aptm = dao.getApartments();
        double max_cost = aptm.stream().mapToDouble(Apartment::getTotalCost).max().orElse(Double.NaN);
        List<Apartment> res = aptm.stream().filter(each_atm -> (each_atm.getTotalCost() >= max_cost)).collect(Collectors.toList());
        return res;
    }


}