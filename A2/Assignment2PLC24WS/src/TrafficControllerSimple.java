public class TrafficControllerSimple implements TrafficController {
	private TrafficRegistrar registrar;
	boolean bridge_free;
	boolean bridge_free_rigth;
	int carcounter;
	
	public TrafficControllerSimple(TrafficRegistrar r) {
		this.registrar = r;
		this.bridge_free = true;
		this.bridge_free_rigth = true;
		this.carcounter= 0;
	}
	
	public synchronized void enterRight(Vehicle v) { 
		System.out.println("Car" + v.getId() +" Demands Enteringfrom rigth");
		System.out.println("Car" + v.getId() +" finds condition "+ this.bridge_free + " " + this.bridge_free_rigth);
		while (!this.bridge_free && !this.bridge_free_rigth) {
			try{
				wait();
			}
			catch (InterruptedException e){
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " + e.getMessage());
			}
		}
		this.bridge_free = false;
		this.carcounter += 1;
		if (this.carcounter == 2){
			this.bridge_free_rigth = false;
		}
		System.out.println("Car" + v.getId() + " stets to " + this.bridge_free + " " + this.bridge_free_rigth);
		System.out.println("Car" + v.getId() + " started from right");
		registrar.registerRight(v);       
	}
	
	public synchronized void enterLeft(Vehicle v) {
		System.out.println("Car" + v.getId() +" Demands Enteringfrom left ");
		System.out.println("Car" + v.getId() +" finds condition "+ this.bridge_free + " " + this.bridge_free_rigth);
		while (!this.bridge_free){
			try{
				wait();
			}
			catch (InterruptedException e){
				Thread.currentThread().interrupt();
				System.out.println("Thread interrupted: " + e.getMessage());
			}
		}
		System.out.println("Car" + v.getId() + " started from left");
		this.bridge_free = false;
		registrar.registerLeft(v);   
	}
	
	public synchronized void leaveLeft(Vehicle v) { 
		if (this.bridge_free){
			System.out.println("ERROR: Tryint to leave an empty bride");
		}

		this.carcounter -= 1;
		if (this.carcounter < 2){
			this.bridge_free_rigth = true;
		}
		if (this.carcounter == 0){
			this.bridge_free = true;
		}
		System.out.println("Car" + v.getId() + " stets to " + this.bridge_free + " " +this.bridge_free_rigth);
		System.out.println("Car" + v.getId() + " left to the left");
		registrar.deregisterLeft(v);
		notify();     
	}
	
	public synchronized void leaveRight(Vehicle v) { 
		if (this.bridge_free){
			System.out.println("ERROR: Tryint to leave an empty bride");
		}
		System.out.println("Car" + v.getId() + " left to the rigth");
		this.bridge_free = true;
		registrar.deregisterRight(v); 
		notify();
	}
}

// javac src/*.java
// java -cp src BridgeGUI
