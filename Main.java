import java.util.*;

class Main {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            int x = in.nextInt(); // x position of your pod
            int y = in.nextInt(); // y position of your pod
            int nextCheckpointX = in.nextInt(); // x position of the next check point
            int nextCheckpointY = in.nextInt(); // y position of the next check point

            System.out.println(nextCheckpointX + " " + nextCheckpointY + " 100");


        }
    }
}