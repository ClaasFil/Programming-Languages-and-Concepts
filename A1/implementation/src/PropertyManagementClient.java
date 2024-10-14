import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PropertyManagementClient {

    //javac *.java
    //java PropertyManagementClient default_aptm list

    // java PropertyManagementClient default_aptm add OA 97 100.0 3 1 1890 1090 Berggasse 19 4 2.55 0.45
    // java PropertyManagementClient default_aptm add RA 2 95.0 3 3 1894 1080 "Lange Gasse" 15 16 8.75 2

    //RA 2 95.0 3 3 1894 1080 "Lange Gasse" 15 16 8.75 2

    public static void main(String[] args) {
        //default_aptm
        
        
        // print Hello world
        
        //OwnedApartment oa = new OwnedApartment(1, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 78787.89);

        //System.out.println(oa.toString());

        serilizazion_test();

        //test_Arpartments();

        comandLinestuff(args);

    }

    private static void comandLinestuff(String[] args){
        //System.out.println("start");
        if (args.length > 1) {
            processCommandLineArguments(args);
        }
        else{
            System.err.println("invalit comand line call"); 
        }
    }

    private static void processCommandLineArguments(String[] args){
        //System.out.println(args[0]);
        PropertyManagement pm = new PropertyManagement(new PropertyManagementSerializationDAO(args[0]));

        
        
        switch (args[1].toLowerCase()){
            case "list":
                listInput(args, pm);
                break;
            case "add":
                addInpu(args, pm);
                break;
            case "count":
                countInput(args, pm);
                break;
                case "meancosts":
                System.out.println(pm.avg_costs());
                break;
            case "oldest":
                oldestInput(args, pm);
                break;


            default:
                System.out.println("Unknown command: " + args[1]);
                break;
        }
    }

    private static void oldestInput(String[] args, PropertyManagement pm){
        List<Apartment> aptms = pm.oldest_aptm();
        aptms.stream().forEach(aptm -> System.out.println("ID: "+ String.valueOf(aptm.getId())));

    }

    private static void countInput(String[] args, PropertyManagement pm){
        Map<String, Long> res = pm.count_aptm();
        for (Map.Entry<String, Long> entry : res.entrySet()) {
            String className = entry.getKey();
            Long count = entry.getValue();
            //System.out.println(className + ": " + count);
        }
        if(args.length <= 2){
            System.out.println(res.values().stream().reduce(0L, Long::sum));
        }
        else{
            switch (args[2].toLowerCase()){
                case "ra":
                    System.out.println(res.get("RentedApartment"));
                    break;
                case "oa":
                    System.out.println(res.get("OwnedApartment"));
                    break;
            
            }
        }


    }


    private static void addInpu(String[] args, PropertyManagement pm){
        if(args.length < 14){
            System.out.println("Error: Invalid parameter.");
        }
        else {
            switch (args[2]) {
                case "RA":
                    try {
                        // Parse command-line arguments
                        int index = 3; 
                        Integer id = Integer.parseInt(args[index++]);
                        double area = Double.parseDouble(args[index++]);
                        double num_rooms = Double.parseDouble(args[index++]);
                        int floor = Integer.parseInt(args[index++]);
                        int year = Integer.parseInt(args[index++]);
                        Integer postal_code = Integer.parseInt(args[index++]);
                        String street = args[index++];
                        Integer house_number = Integer.parseInt(args[index++]);
                        Integer apartment_number = Integer.parseInt(args[index++]);
                        double rent = Double.parseDouble(args[index++]);
                        Integer tenants = Integer.parseInt(args[index++]);
                        

                        // Create OwnedApartment object
                        RentedApartment oa = new RentedApartment(
                            id,
                            area,
                            num_rooms,
                            floor,
                            year,
                            postal_code,
                            street,
                            house_number,
                            apartment_number,
                            rent,
                            tenants
                        );

                        
            
                        // Add the apartment to PropertyManagement
                        pm.save_aptm(oa);
                        System.out.println("Info: Apartment "+ args[3] + " added.");
            
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid parameter.");
                    } 
                    break;
            
                    case "OA":
                    try {
                        // Parse command-line arguments
                        int index = 3; 
                        Integer id = Integer.parseInt(args[index++]);
                        double area = Double.parseDouble(args[index++]);
                        double num_rooms = Double.parseDouble(args[index++]);
                        int floor = Integer.parseInt(args[index++]);
                        int year = Integer.parseInt(args[index++]);
                        Integer postal_code = Integer.parseInt(args[index++]);
                        String street = args[index++];
                        Integer house_number = Integer.parseInt(args[index++]);
                        Integer apartment_number = Integer.parseInt(args[index++]);
                        double operating_costs = Double.parseDouble(args[index++]);
                        double maintenance_reserve = Double.parseDouble(args[index++]);
                        

                        // Create OwnedApartment object
                        OwnedApartment oa = new OwnedApartment(
                            id,
                            area,
                            num_rooms,
                            floor,
                            year,
                            postal_code,
                            street,
                            house_number,
                            apartment_number,
                            operating_costs,
                            maintenance_reserve
                        );

                        
            
                        // Add the apartment to PropertyManagement
                        pm.save_aptm(oa);
                        System.out.println("Info: Apartment "+ args[3] + " added.");
            
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid parameter.");
                    } 
                    break;
            
                default:
                    System.out.println("Error: Invalid parameter.");
                    break;
            }
        }


    }


    private static void listInput(String[] args, PropertyManagement pm){
        // If no int provided print all apartments
        if (args.length < 3) {
            System.out.println(pm.get_info_all_aptm());
        }
        else{
            try {
            int apartmentId = Integer.parseInt(args[2]);
            Apartment apm = pm.get_info_aptm_by_id(apartmentId);
            if (apm != null) {
                System.out.println(apm.toString());
            } else {
                System.out.println("Apartment with ID " + apartmentId + " not found.");
            }
        } 
        catch (NumberFormatException e) {
            System.out.println("Invalid apartment ID: " + args[2]);
            }
        }
    }


    private static void serilizazion_test(){
        PropertyManagementSerializationDAO pmsd = new PropertyManagementSerializationDAO("default_aptm");
        pmsd.resetDB();
        Apartment oa_1 = new OwnedApartment(1, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 78787.89);
        Apartment oa_2 = new OwnedApartment(2, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 78787.89);
        Apartment oa_3 = new OwnedApartment(3, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 78787.89);
        Apartment oa_4 = new OwnedApartment(4, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 78787.89);
        Apartment oa_5 = new OwnedApartment(5, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 78787.89);
        Apartment oa_6 = new OwnedApartment(6, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 78787.89);
        Apartment oa_7 = new RentedApartment(7, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 3);
        Apartment oa_8 = new RentedApartment(8, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 1);
        Apartment oa_9 = new RentedApartment(9, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 20);


        List<Apartment> apartments = new ArrayList<>();
        apartments.add(oa_1);
        apartments.add(oa_2);
        apartments.add(oa_3);
        apartments.add(oa_4);
        apartments.add(oa_5);
        apartments.add(oa_6);
        apartments.add(oa_7);
        apartments.add(oa_8);
        apartments.add(oa_9);


        apartments.stream().forEach(apartment -> pmsd.saveApartment(apartment));


        //pmsd.saveApartmentList(apartments);
        //System.out.println(pmsd.getApartmentById(3));



        //pmsd.saveApartment(oa_1);
        //pmsd.saveApartment(oa_2);
        //pmsd.saveApartment(oa_3);
        //pmsd.saveApartment(oa_4);
        //pmsd.saveApartment(oa_5);
        //pmsd.saveApartment(oa_6);
        


        //List<Apartment> apartments_read = pmsd.getApartments();
        //for (Apartment apartment : apartments_read) {
        //    System.out.println(apartment.toString());
        //}

        //pmsd.deleteApartment(3);

        //apartments.add(oa_3);
        


    }




    private static void test_Arpartments() {
        try {
            Apartment oa_1 = new OwnedApartment(1, 10.2356, 3, 0, 1997, 14612, "someStreet", 34, 14, 34.3423, 78787.89);
           System.out.println("OA creation as intendet");
        } catch (IllegalArgumentException e) {
            // Catch the exception and print the error message
            System.err.println(e.getMessage());
        }
    
    

        try {
            //Apartment oa_true = new OwnedApartment(7, 100.0, 3, 1, 1890, 1090, "Berggasse", 19, 4, 2.55);
            System.out.println("OA creation as intendet");
        } catch (IllegalArgumentException e) {
            // Catch the exception and print the error message
            System.err.println("WWWWWWW");
        }
    
    
    
    
    
    
    }
}



