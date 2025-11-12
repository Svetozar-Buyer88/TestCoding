import java.util.*;

class Main {

    //внутренний класс для чекпоинта
    static class CheckPoint {
        int x, y;

        CheckPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // переопределил контракт equals и hashcode для корректной работы List
        public boolean equals(Object obj) {
            if (obj instanceof CheckPoint) {
                CheckPoint other = (CheckPoint) obj;
                return x == other.x && y == other.y;
            }
            return false;
        }

        public int hashCode() {
            return 31 * x + y;
        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        boolean boostUsed = false;//флаг буста
        List<CheckPoint> checkPoints = new ArrayList<>();// список всех чекпоинтов
        boolean courseKnown = false;//указатель собрана ли трасса
        int longestIndex = -1;// индекс конца самого длинного участка
        double maxDist = 0;// макс расстояние между чекпоинтами
        int prevX = 0;
        int prevY = 0;


        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            int nextCheckpointX = in.nextInt();
            int nextCheckpointY = in.nextInt();
            int nextCheckpointDist = in.nextInt();
            int nextCheckpointAngle = in.nextInt();
            int opponentX = in.nextInt();
            int opponentY = in.nextInt();

            // оценка скорости
            // для компенсации инерции, чтобы не перелетал чекпоинт
            int vx = x - prevX;
            int vy = y - prevY;
            prevX = x;
            prevY = y;

            // управление чекпоинтами
            CheckPoint currentNext = new CheckPoint(nextCheckpointX, nextCheckpointY);//текущий чекпоинт
            if (!checkPoints.contains(currentNext)) {
                checkPoints.add(currentNext);//добавляем чекпоинт если его нет
            }
            if (!courseKnown && checkPoints.size() > 1 &&
                    currentNext.equals(checkPoints.get(0))) {//Если вернулся к первому чекпоинту, ставим courseKnown = true
                courseKnown = true;
                int n = checkPoints.size();
                //находим расстояние между чекпонитами
                for (int i = 0; i < n; i++) {
                    CheckPoint p1 = checkPoints.get(i);
                    CheckPoint p2 = checkPoints.get((i + 1) % n);//остаток от деления для получения индекса 1
                    // теорема Пифагора
                    double d = Math.hypot(p1.x - p2.x, p1.y - p2.y);
                    if (d > maxDist) {
                        maxDist = d;
                        longestIndex = (i + 1) % n;//индекса конца самого длинного участка
                    }
                }
            }

            // корректировка цели с учетом инерции
            double factor = 0.3; // коэф компенсации
            int targetX = nextCheckpointX - (int) (vx * factor);
            int targetY = nextCheckpointY - (int) (vy * factor);

            // расчет thrust - тяга/скорость относительно угла
            double angleAbs = Math.abs(nextCheckpointAngle);//модуль
            int thrust;
            if (angleAbs > 90) {
                thrust = 0;
            } else {
                double alignment = (90 - angleAbs) / 90.0;
                double distFactor = nextCheckpointDist / 1200.0; //расстояние до чекпоинта к диаметру
                thrust = (int) (100 * alignment * distFactor);
                thrust = Math.min(100, Math.max(0, thrust));
            }

            // буст
            boolean boost = false;
            int angleThreshold = 15;// максимальный угол который можно использовать для буста
            int distThreshold = 5000;// расстояние, которое подходит для буста
            //
            boolean isLongest = courseKnown ? checkPoints.indexOf(currentNext) == longestIndex : true;//если текущий чекпоинт это самый длинный участок или же на старте юзаем буст
            //буст не использован && угол меньше 15 && расстояние больше 5000 && самая длинная прямая
            if (!boostUsed && angleAbs < angleThreshold && nextCheckpointDist > distThreshold && isLongest) {
                boost = true;
                boostUsed = true;
            }

            // Output the action
            String power = boost ? "BOOST" : String.valueOf(thrust);
            System.out.println(targetX + " " + targetY + " " + power);
        }
    }
}