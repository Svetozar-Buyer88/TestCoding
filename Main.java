import java.util.*;

class Main {


        public static void main(String args[]) {
            Scanner in = new Scanner(System.in);
            boolean boostUsed=false;
            // game loop
            while (true) {
                int x = in.nextInt();
                int y = in.nextInt();
                int nextCheckpointX = in.nextInt(); // x position of the next check point
                int nextCheckpointY = in.nextInt(); // y position of the next check point
                int nextCheckpointDist = in.nextInt(); // distance to the next checkpoint
                int nextCheckpointAngle = in.nextInt(); // angle between your pod orientation and the direction of the next checkpoint
                int opponentX = in.nextInt();
                int opponentY = in.nextInt();
                int thrust = 0;
                boolean boost=false; // флаг буста(изначально false не используется)


                if(nextCheckpointAngle > 90 || nextCheckpointAngle < -90){
                    thrust=0;
                }

                else {
                    thrust=100;
                    if(nextCheckpointDist > 6000 && !boostUsed){
                        boost = true;
                        boostUsed = true;
                    }
                }
                System.out.println(nextCheckpointX +" "+nextCheckpointY +" "+(boost ? "BOOST" : thrust)
                );

            }
        }
    }