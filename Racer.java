// RaceSimulator.java

class Racer extends Thread {
    private String racerName;
    private int sleepTime; // Time to sleep in milliseconds

    // Constructor
    public Racer(String name, int sleepTime) {
        this.racerName = name;
        this.sleepTime = sleepTime;
    }

    public void run() {
        try {
            // Simulate 5 steps in the race
            for (int i = 1; i <= 5; i++) {
                System.out.println(racerName + " - Completed step " + i);
                // Pause for the racer's specific sleep time
                Thread.sleep(sleepTime);
            }
            // Racer finishes the race
            System.out.println(racerName + " - finished the race!");
        } catch (InterruptedException e) {
            // Handle if the thread is interrupted while sleeping
            System.out.println(racerName + " was interrupted!");
        }
    }
}

public class RaceSimulator {
    public static void main(String[] args) {
        // Create three Racer threads with different sleep times
        // Racer A: 500ms (Medium)
        Racer racerA = new Racer("Racer A", 500); 
        // Racer B: 200ms (Fastest)
        Racer racerB = new Racer("Racer B", 200); 
        // Racer C: 600ms (Slowest)
        Racer racerC = new Racer("Racer C", 600); 

        // Announce the start of the race
        System.out.println("Race started concurrently!");

        // Start the threads. The start() method calls the run() method
        // in a new execution thread.
        racerA.start();
        racerB.start();
        racerC.start();

        // The main thread now waits for the race to complete while 
        // the three racer threads run simultaneously.
    }
}
