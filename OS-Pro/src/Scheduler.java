

import java.util.Scanner;

public class Scheduler {

    static class process {
        String processID;
        int priority;
        int arrivalTime;
        int cpuBurst;
        int startTime ;
        int terminationTime ;
        int turnAroundTime ;
        int waitingTime ;
        int responseTime ;

        public process(String processID, int priority, int arrivalTime, int cpuBurst) {
            this.processID = processID;
            this.priority = priority;
            this.arrivalTime = arrivalTime;
            this.cpuBurst = cpuBurst;
        }
    }

    // Assuming a maximum size for simplicity, but this could be dynamically resized as needed
    static final int MAX_PROCESSES = 100;
    static process[] Q1 = new process[MAX_PROCESSES];
    static process[] Q2 = new process[MAX_PROCESSES];
    static int Q1Size = 0;
    static int Q2Size = 0;

    private void menu () {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    enterProcessInformation(scanner);
                    break;
                case 2:
                    // Placeholder for displaying scheduling order and detailed process information ( i did not apply it ) 
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("1. Enter process' information.");
        System.out.println("2. Report detailed information about each process and different scheduling criteria.");
        System.out.println("3. Exit the program.");
        System.out.print("Enter your choice: ");
    }

    public static void enterProcessInformation(Scanner scanner) {
        System.out.print("Enter the total number of processes: ");
        int numberOfProcesses = scanner.nextInt();
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println("Enter information for Process " + (i + 1) + " (priority, arrival time, CPU burst):");
            int priority = scanner.nextInt();
            int arrivalTime = scanner.nextInt();
            int cpuBurst = scanner.nextInt();
            process process = new process("P" + (i + 1), priority, arrivalTime, cpuBurst);
            if (priority == 1) {
                if (Q1Size < MAX_PROCESSES) {
                    Q1[Q1Size++] = process;
                } else {
                    System.out.println("Q1 is full. Cannot add more processes.");
                }
            } else {
                if (Q2Size < MAX_PROCESSES) {
                    Q2[Q2Size++] = process;
                } else {
                    System.out.println("Q2 is full. Cannot add more processes.");
                }
            }
        }
    }
}
