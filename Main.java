import java.util.*;

class Main {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            int nextCheckpointX = in.nextInt(); // x position of the next check point
            int nextCheckpointY = in.nextInt(); // y position of the next check point
            int nextCheckpointDist = in.nextInt(); // distance to the next checkpoint
            int nextCheckpointAngle = in.nextInt(); // угол между ориентацией вашего модуля и направлением на следующую контрольную точку
            int opponentX = in.nextInt();
            int opponentY = in.nextInt();

            if(nextCheckpointAngle > 90 || nextCheckpointAngle < -90){
                System.out.println(nextCheckpointX + " " + nextCheckpointY + " 0");
            }
            else
                System.out.println(nextCheckpointX + " " + nextCheckpointY + " 100");
        }
    }
}