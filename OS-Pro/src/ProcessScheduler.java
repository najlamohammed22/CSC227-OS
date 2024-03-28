import java.util.Scanner;
	class Process {
	    private String id;
	    private int priority;
	    private int arrivalTime;
	    private int cpuBurst;

	    public Process(String id, int priority, int arrivalTime, int cpuBurst) {
	        this.id = id;
	        this.priority = priority;
	        this.arrivalTime = arrivalTime;
	        this.cpuBurst = cpuBurst;
	    }

	    public String getId() {
	        return id;
	    }

	    public int getPriority() {
	        return priority;
	    }

	    public int getArrivalTime() {
	        return arrivalTime;
	    }

	    public int getCpuBurst() {
	        return cpuBurst;
	    }
	    
	}



	public class ProcessScheduler {

	    private Process[] Q1;
	    private Process[] Q2;
	    private int Q1Size = 0;
	    private int Q2Size = 0;
	    private Scanner scanner = new Scanner(System.in);

	    private void menu() {
	        System.out.print("Enter the maximum number of processes: ");
	        int maxProcesses = scanner.nextInt();
	        Q1 = new Process[maxProcesses];
	        Q2 = new Process[maxProcesses];

	        boolean exit = false;
	        while (!exit) {
	            showMenu();
	            int choice = scanner.nextInt();
	            switch (choice) {
	                case 1:
	                System.out.print("Enter number of processes to add: ");
	                int numProcesses = scanner.nextInt();
	                for (int i = 0; i < numProcesses; i++) {
	                    System.out.println("Entering information for process " + (Q1Size + Q2Size + 1));
	                    System.out.print("Priority (1-high or 2-low): ");
	                    int priority = scanner.nextInt();
	                    System.out.print("Arrival time: ");
	                    int arrivalTime = scanner.nextInt();
	                    System.out.print("CPU burst time: ");
	                    int cpuBurst = scanner.nextInt();
	        
	                    Process process = new Process("P" + (Q1Size + Q2Size + 1), priority, arrivalTime, cpuBurst);
	                    if (priority == 1) {
	                        Q1[Q1Size++] = process;
	                    } else {
	                        Q2[Q2Size++] = process;
	                    }

	                } 
	             break;
	                case 2:
	// ############//#endregion

	             break;
	                case 3:
	                    exit = true;
	                    System.out.println("Exiting program.");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
	                    break;
	            }
	        }
	    }

	    private void showMenu() {
	        System.out.println("\n1. Enter process information.");
	        System.out.println("2. Report detailed information about each process and different scheduling criteria.");
	        System.out.println("3. Exit the program.");
	        System.out.print("Enter your choice: ");
	    }

	    

	    



}
