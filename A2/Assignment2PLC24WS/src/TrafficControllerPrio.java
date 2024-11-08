import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TrafficControllerPrio implements TrafficController {
	private TrafficRegistrar registrar;
	private boolean bridge_occuped;
	private int prio_side_counter;
	private final Condition bridge_free_signal;
	private final Condition prio_side_signal;
	private final Lock lock;
	private boolean prio;
	
	public TrafficControllerPrio(TrafficRegistrar r, String prioSide) {
		
		this.registrar = r;
		this.bridge_occuped = false;
		this.prio_side_counter = 0;
		this.lock = new ReentrantLock(true); 
		this.bridge_free_signal = lock.newCondition();
		this.prio_side_signal = lock.newCondition();

		switch (prioSide) {
			case "rigth":
				this.prio = true;
				System.out.println("Prioside rigth");
				break;
			case "left":
				this.prio = false;
				System.out.println("Prioside left");
				break;
		
			default:
				System.out.println("Prioside not recognized");
				break;
		}

	}
	
	public void enterRight(Vehicle v) { 
		lock.lock();
		System.out.println("Car" + v.getId() +" Demands Enteringfrom rigth");
		try{
			if (prio){
				this.prio_side_counter += 1;
				System.out.println("Car" + v.getId() +" Prio Side counter: " + this.prio_side_counter);
			}

			while (this.bridge_occuped){
				if (prio){
					this.prio_side_signal.await();
				}
				else{
					this.bridge_free_signal.await();
				}
				
			}
			this.bridge_occuped = true;
			System.out.println("Car" + v.getId() + " started from right");
			registrar.registerRight(v);
		}
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
			System.out.println("Thread interrupted: " + e.getMessage());
		}finally {
            lock.unlock();
        }
		
		   
	}
	
	public void enterLeft(Vehicle v) {
		lock.lock();
		System.out.println("Car" + v.getId() +" Demands Enteringfrom left ");
		try{
			if (!prio){
				this.prio_side_counter += 1;
				System.out.println("Car" + v.getId() +" Prio Side counter: " + this.prio_side_counter);
			}

			while (this.bridge_occuped){
				if (!prio){
					this.prio_side_signal.await();
				}
				else{
					this.bridge_free_signal.await();
				}
				
			}
			this.bridge_occuped = true;
			System.out.println("Car" + v.getId() + " started from left");
			registrar.registerLeft(v);
		}
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
			System.out.println("Thread interrupted: " + e.getMessage());
		}finally {
            lock.unlock();
        }
	}
	
	public void leaveLeft(Vehicle v) { 
		lock.lock();
        try {
            registrar.deregisterLeft(v);
            this.bridge_occuped = false;
			System.out.println("Car" + v.getId() + " left to the left");
			if (prio){
				this.prio_side_counter -= 1;
				System.out.println("Car" + v.getId() +"Prio Side counter: " + this.prio_side_counter);
			}
			if (this.prio_side_counter > 0){
				this.prio_side_signal.signal();
			}
			else{
				bridge_free_signal.signal(); // Notify any waiting vehicle that the bridge is now free
			}
            
            
        } finally {
            lock.unlock();
        }  
	}
	
	public void leaveRight(Vehicle v) { 
		lock.lock();
        try {
            registrar.deregisterRight(v);
            this.bridge_occuped = false;
            System.out.println("Car" + v.getId() + " left to the rigth");
			if (!prio){
				this.prio_side_counter -= 1;
				System.out.println("Car" + v.getId() +"Prio Side counter: " + this.prio_side_counter);
			}
            if (this.prio_side_counter > 0){
				this.prio_side_signal.signal();
			}
			else{
				bridge_free_signal.signal(); // Notify any waiting vehicle that the bridge is now free
			}
        } finally {
            lock.unlock();
        }  
	}
}

// javac src/*.java
// java -cp src BridgeGUI
