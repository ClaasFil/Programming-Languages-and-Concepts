import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class test {
    public static void main(String[] args) {
        
        /* 
        TrafficRegistrar registrar = new TrafficRegistrarEmpty();
        TrafficController trafficController = new TrafficControllerSimple(registrar);
        //TrafficController trafficController = new TrafficControllerPrio(registrar, "rigth");

        // Executor service to run multiple threads concurrently
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Random random = new Random();

        // Create and run multiple threads representing vehicles entering from both sides
        for (int i = 0; i < 10; i++) {
            final int vehicleId = i;
            executorService.execute(() -> {
                int carType = random.nextInt(2); // Randomly decide if the car is REDCAR or BLUECAR
                Car car = new Car(carType, null, null, trafficController);
                if (carType == Car.REDCAR) {
                    //System.out.println("Car" + car.getId() + " register from right");
                    trafficController.enterRight(car);
                } else {
                    //System.out.println("Car" + car.getId() + " register from left");
                    trafficController.enterLeft(car);
                }
                try {
                    // Simulate the vehicle staying on the bridge for some time
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    if (carType == Car.REDCAR) {
                        //System.out.println("Car" + car.getId() + " registers to leave to the left");
                        trafficController.leaveLeft(car);
                    } else {
                        //System.out.println("Car" + car.getId() + " registers to leave to the right");
                        trafficController.leaveRight(car);
                    }
                }
            });
        }

        // Shutdown the executor service
        executorService.shutdown();
        */
    }

    


}
// javac src/*.java
// java -cp src BridgeGUI