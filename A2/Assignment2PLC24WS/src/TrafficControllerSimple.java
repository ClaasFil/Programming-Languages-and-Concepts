public class TrafficControllerSimple implements TrafficController {
	private TrafficRegistrar registrar;
	boolean bridge_free;
	
	public TrafficControllerSimple(TrafficRegistrar r) {
		this.registrar = r;
		this.bridge_free = true;
	}
	
	public synchronized void enterRight(Vehicle v) { 
		System.out.println("Enteringfrom rigth is free: " + this.bridge_free);
		while (!this.bridge_free){
			try{
				wait();
			}
			catch (InterruptedException e){
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " + e.getMessage());
			}
		}
		this.bridge_free = false;
		System.out.println("actually entering bridge");
		registrar.registerRight(v);       
	}
	
	public synchronized void enterLeft(Vehicle v) {
		System.out.println("Enteringfrom left is free: " + this.bridge_free);
		while (!this.bridge_free){
			try{
				wait();
			}
			catch (InterruptedException e){
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " + e.getMessage());
			}
		}
		this.bridge_free = false;
		System.out.println("actually entering bridge");
		registrar.registerLeft(v);   
	}
	
	public synchronized void leaveLeft(Vehicle v) { 
		if (this.bridge_free){
			System.out.println("ERROR: Tryint to leave an empty bride");
		}
		this.bridge_free = true;
		System.out.println("actually leaving bridge");
		registrar.deregisterLeft(v);
		notify();     
	}
	
	public synchronized void leaveRight(Vehicle v) { 
		if (this.bridge_free){
			System.out.println("ERROR: Tryint to leave an empty bride");
		}
		System.out.println("actually leaving bridge");
		this.bridge_free = true;
		registrar.deregisterRight(v); 
		notify();
	}
}

// javac src/*.java
// java -cp src BridgeGUI
