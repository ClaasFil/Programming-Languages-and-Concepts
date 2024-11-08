import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TrafficControllerFair implements TrafficController {
	private TrafficRegistrar registrar;
	private boolean bridge_occuped;
	private final Condition bridge_free;
	private final Lock lock;
	
	public TrafficControllerFair(TrafficRegistrar r) {
		this.registrar = r;
		this.bridge_occuped = false;
		this.lock = new ReentrantLock(true); 
		this.bridge_free = lock.newCondition();

	}
	
	public void enterRight(Vehicle v) { 
		lock.lock();
		//System.out.println("Enteringfrom rigth is free: " + this.bridge_free);
		try{
			while (this.bridge_occuped){
				this.bridge_free.await();
			}
			this.bridge_occuped = true;
			//System.out.println("Car" + v.getId() + " started from right");
			registrar.registerRight(v);
		}
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
			//System.out.println("Thread interrupted: " + e.getMessage());
		}finally {
            lock.unlock();
        }
		
		   
	}
	
	public void enterLeft(Vehicle v) {
		lock.lock();
		//System.out.println("Enteringfrom left is free: " + this.bridge_free);
		try{
			while (this.bridge_occuped){
				this.bridge_free.await();
			}
			this.bridge_occuped = true;
			//System.out.println("Car" + v.getId() + " started from left");
			registrar.registerLeft(v);
		}
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
			//System.out.println("Thread interrupted: " + e.getMessage());
		}finally {
            lock.unlock();
        }
	}
	
	public void leaveLeft(Vehicle v) { 
		lock.lock();
        try {
            registrar.deregisterLeft(v);
            this.bridge_occuped = false;
            //System.out.println("Car" + v.getId() + " left to the left");
            bridge_free.signal(); // Notify any waiting vehicle that the bridge is now free
        } finally {
            lock.unlock();
        }  
	}
	
	public void leaveRight(Vehicle v) { 
		lock.lock();
        try {
            registrar.deregisterRight(v);
            this.bridge_occuped = false;
            //System.out.println("Car" + v.getId() + " left to the rigth");
            bridge_free.signal(); // Notify any waiting vehicle that the bridge is now free
        } finally {
            lock.unlock();
        }  
	}
}

// javac src/*.java
// java -cp src BridgeGUI
