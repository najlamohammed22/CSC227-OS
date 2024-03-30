import java.util.Scanner;

public class Scheduler {

    static class process {
        String processID;
        int priority;
        int arrivalTime;
        int cpuBurst;
        int startTime;
        int terminationTime;
        int turnaroundTime;
        int waitingTime;
        int responseTime;

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

    private void menu() {
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
                    scheduleProcesses();
                    printReport();
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

    void scheduleProcesses() {
        int timeQuantum = 3;
        int currentTime = 0;

        // RR for Q1
        while (Q1Size > 0) {
            process currentProcess = Q1[0];
            if (currentProcess.arrivalTime > currentTime) {
                currentTime = currentProcess.arrivalTime;
            }


        // Check if any new processes arrive at the same time a process releases the CPU
        List<process> newProcesses = new ArrayList<>();
        for (process newProcess : Q1) {
            if (newProcess != null && newProcess.arrivalTime == currentTime && newProcess != currentProcess) {
                newProcesses.add(newProcess);
            }
        }

        // Add the new processes to the front of the queue
        for (process newProcess : newProcesses) {
            if (newProcess.priority == 1) {
                Q1[Q1Size++] = newProcess;
            } else {
                Q2[Q2Size++] = newProcess;
            }
        }


            // Execute the process for the time quantum or until completion
            if (currentProcess.cpuBurst > timeQuantum) {
                currentProcess.cpuBurst -= timeQuantum;
                currentTime += timeQuantum;
                currentProcess.terminationTime = currentTime;
                executionOrder[Q1Size + Q2Size] = currentProcess;
                shiftArray(Q1, Q1Size);
                Q1[Q1Size - 1] = currentProcess; // Move the process to the end of the queue
            } else {
                currentTime += currentProcess.cpuBurst;
                currentProcess.terminationTime = currentTime;
                currentProcess.turnaroundTime = currentProcess.terminationTime - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.cpuBurst;
                currentProcess.responseTime = currentProcess.startTime - currentProcess.arrivalTime;
                executionOrder[Q1Size + Q2Size] = currentProcess;
                shiftArray(Q1, Q1Size);
                Q1Size--;
            }
        }
        // SJF here 
    }

    void printReport() {
        // Print scheduling order of processes
        System.out.print("Scheduling Order of Processes: ");
        for (process process : Q1) {
            if (process != null) {
                System.out.print(process.processID + " | ");
            }
        }
        System.out.println("\n");

        // Print detailed information for each process
        for (process process : Q1) {
            if (process != null) {
                System.out.println("Process ID: " + process.processID);
                System.out.println("Priority: " + process.priority);
                System.out.println("Arrival Time: " + process.arrivalTime);
                System.out.println("CPU Burst: " + process.cpuBurst);
                System.out.println("Start Time: " + process.startTime);
                System.out.println("Termination Time: " + process.terminationTime);
                System.out.println("Turnaround Time: " + process.turnaroundTime);
                System.out.println("Waiting Time: " + process.waitingTime);
                System.out.println("Response Time: " + process.responseTime + "\n");
            }
        }

        // Calculate and print average turnaround time, waiting time, and response time
        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;
        int totalResponseTime = 0;
        int count = 0;
        for (process process : Q1) {
            if (process != null) {
                totalTurnaroundTime += process.turnaroundTime;
                totalWaitingTime += process.waitingTime;
                totalResponseTime += process.responseTime;
                count++;
            }
        }
        double avgTurnaroundTime = (double) totalTurnaroundTime / count;
        double avgWaitingTime = (double) totalWaitingTime / count;
        double avgResponseTime = (double) totalResponseTime / count;

        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        System.out.println("Average Waiting Time: " + avgWaitingTime);
        System.out.println("Average Response Time: " + avgResponseTime);
       
        // write them to text file 
    }

    private void shiftArray(process[] array, int size) {
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
    }


}