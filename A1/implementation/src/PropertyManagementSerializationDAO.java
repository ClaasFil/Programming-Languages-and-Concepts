import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyManagementSerializationDAO implements PropertyManagementDAO {
    String filename;
    
    public PropertyManagementSerializationDAO(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Error: Invalid parameter.");
        }
        this.filename = filename;

        // creat an empy file if no one is exsisting
        File file = new File(filename);
        
        //delete file for pratic reasons
        //if (file.exists()) file.delete();  // DELETE LATER -------------------------

        if (!file.exists()){
            try {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename, true));
                List<Apartment> initList = new ArrayList<>();
                writer.writeObject(initList);
                writer.close();
            }
            catch (Exception e) {
                System.err.println("Serialization error: " +
                e.getMessage());
                System.exit(1);
            }
        }



    }

    public void resetDB(){
        File file = new File(this.filename);
        if (file.exists()) file.delete();  // DELETE LATER -------------------------
        if (!file.exists()){
            try {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename, true));
                List<Apartment> initList = new ArrayList<>();
                writer.writeObject(initList);
                writer.close();
            }
            catch (Exception e) {
                System.err.println("Serialization error: " +
                e.getMessage());
                System.exit(1);
            }
        }
    }
    
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Apartment> getApartments() {
        List<Apartment> res = null;
        File file = new File(filename);
        
        try {
            ObjectInputStream reader;
            reader = new ObjectInputStream(new FileInputStream(file));
            res = (List<Apartment>) reader.readObject();
            reader.close();
        }
        catch (Exception e) {
            System.err.println("Deserialization error: " +
            e.getMessage());
            System.exit(1);
        }
        return res;
    }

    @Override
    public Apartment getApartmentById(int id) {
        List<Apartment> apartments = getApartments();
        return apartments.stream().filter(apartment -> apartment.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void saveApartment(Apartment newApartment) {
        List<Apartment> apartments = getApartments();
        Integer id = newApartment.getId();
        if (apartments.stream().noneMatch(a -> a.getId().equals(id))) {
            
            apartments.add(newApartment);
            saveApartmentList(apartments);
        }
        else {
            throw new IllegalArgumentException("Error: Apartment with id " + id + " already exists.");
        }
    }



    private void saveApartmentList(List<Apartment> apartments) {
        File file = new File(this.filename);
        if (file.exists()) file.delete();
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename, true));
            writer.writeObject(apartments);
            writer.close();
        }
        catch (Exception e) {
            System.err.println("Serialization error: " +
            e.getMessage());
            System.exit(1);
        }

    }


    @Override
    public void deleteApartment(int id) {
        List<Apartment> apartments = getApartments();

        if(apartments.stream().noneMatch(apa -> apa.getId().equals(id))){
            throw new IllegalArgumentException("Error: Apartment with id " + id + " can not be deleted.");
        }

        //apartments = apartments.stream().filter(apartment -> !apartment.getId().equals(id)).collect(Collectors.toList());
        apartments.removeIf(apartment -> apartment.getId().equals(id));
        saveApartmentList(apartments);
    }


}