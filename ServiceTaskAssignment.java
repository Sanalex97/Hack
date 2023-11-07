import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ServiceTaskAssignment {
    private static int[] disOffice1ToLowTask = new int[11];
    private static int[] disOffice1ToMediumTask = new int[27];
    private static int[] disOffice1ToHighTask = new int[8];
    private static int[] disOffice2ToLowTask = new int[11];
    private static int[] disOffice2ToMediumTask = new int[27];
    private static int[] disOffice2ToHighTask = new int[8];
    private static int[] disOffice3ToLowTask = new int[11];
    private static int[] disOffice3ToMediumTask = new int[7];
    private static int[] disOffice3ToHighTask = new int[8];
    private static int[][] disMediumToMediumTask = new int[27][27];
    private static int[][] disLowToLowTask = new int[11][11];
    private static int[][] disLowToMediumTask = new int[11][27];
    private static int[][] disLowToHighTask = new int[8][11];
    private static int[][] disMediumToHighTask = new int[8][27];

    private static final int[] TIME_ROUTE = {30, 100}; //время на дорогу минимальное и максимальное

    private static final int TIME_MAX_TASK = 480;
    private static final int TIME_HIGH_TASK = 240;
    private static final int TIME_MEDIUM_TASK = 120;
    private static final int TIME_LOW_TASK = 90;

    private static List<List<Integer>> listRouteSignorOffice1 = new ArrayList<>(); // список возможных маршрутов движения синьоров для офиса 1
    private static List<List<Integer>> listRouteMiddleOffice1 = new ArrayList<>(); // список возможных маршрутов движения мидлов для офиса 1
    private static List<List<Integer>> listRouteJuniorOffice1 = new ArrayList<>(); // список возможных маршрутов движения джунов для офиса 1

    private static List<List<Integer>> listRouteSignorOffice2 = new ArrayList<>(); // список возможных маршрутов движения синьоров для офиса 2
    private static List<List<Integer>> listRouteMiddleOffice2 = new ArrayList<>(); // список возможных маршрутов движения мидлов для офиса 2
    private static List<List<Integer>> listRouteJuniorOffice2 = new ArrayList<>(); // список возможных маршрутов движения джунов для офиса 2

    private static List<List<Integer>> listRouteSignorOffice3 = new ArrayList<>(); // список возможных маршрутов движения синьоров для офиса 3
    private static List<List<Integer>> listRouteMiddleOffice3 = new ArrayList<>(); // список возможных маршрутов движения мидлов для офиса 3
    private static List<List<Integer>> listRouteJuniorOffice3 = new ArrayList<>(); // список возможных маршрутов движения джунов для офиса 3


    public static void main(String[] args) {
        initDistanceBetweenPoints();


       /* System.out.println("Вариант СВ: ");
        showRoutes(buildingRouteFromTwoPoints(listRouteSignorOffice1, disOffice1ToHighTask, disMediumToHighTask, new int[]{240, 120}), "Синьор");
        System.out.println("Вариант ВС: ");
        showRoutes(buildingRouteFromTwoPoints(listRouteSignorOffice1, disOffice1ToMediumTask, disMediumToHighTask, new int[]{120, 240}), "Синьор");*/
/*        System.out.println("Вариант ВН: ");
        showRoutes(buildingRouteFromTwoPoints(listRouteSignorOffice1, disOffice1ToLowTask, disLowToHighTask, new int[]{90, 240}), "Синьор");
        System.out.println("Вариант НВ: ");
        showRoutes(buildingRouteFromTwoPoints(listRouteSignorOffice1, disOffice1ToHighTask, disLowToHighTask, new int[]{240, 90}), "Синьор");*/


        System.out.println("Вариант СCC: ");
        //showRoutes(buildingRouteFromFreePoints(listRouteMiddleOffice1, disOffice1ToMediumTask, disMediumToMediumTask, new int[]{120, 120}), "Мидл");


        showRoutes(buildingRouteFromFreePoints(listRouteMiddleOffice1, disOffice1ToMediumTask, disMediumToMediumTask,
                new int[]{TIME_MEDIUM_TASK, TIME_MEDIUM_TASK},
                TIME_MAX_TASK, TIME_MEDIUM_TASK + TIME_ROUTE[0]), "Мидл");

      /*  List<List<Integer>> listRouteFromThreePoints = buildingRouteFromTwoPoints(listRouteFromTwoPoints, disOffice1ToMediumTask, disMediumToMediumTask,
                new int[]{TIME_MEDIUM_TASK, TIME_MEDIUM_TASK},
                TIME_MAX_TASK, TIME_MEDIUM_TASK + TIME_ROUTE[0]);*/


    }

    private static List<List<Integer>> buildingRouteFromTwoPoints(List<List<Integer>> listRoute, int[] disOffice, int[][] disPointToPoint,
                                                                  int[] priorityTask, int remainingTime, int desiredRemainingTime) {

        for (int i = 0; i < disOffice.length; i++) {
            for (int j = 0; j < disPointToPoint.length; j++) {
                if (i != j) {
                    int disTwoSignorTask = remainingTime - (disOffice[i] + priorityTask[0] + disPointToPoint[j][i] + priorityTask[1]); // оставшеейся время у сотрудника после выполнения по одной задачи высокого и среднего уровня

                    if (disTwoSignorTask >= desiredRemainingTime) {
                        List<Integer> list = new ArrayList<>();

                        list.add(disTwoSignorTask);
                        list.add(i);
                        list.add(j);

                        listRoute.add(list); // добавление расстояния и массива агентских точек, на которых выполнены задачи
                    }
                }
            }
        }
        listRoute = sortedList(listRoute);

        return listRoute;
    }

    private static List<List<Integer>> buildingRouteFromFreePoints(List<List<Integer>> listRoute, int[] disOffice, int[][] disPointToPoint, int[] priorityTask, int remainingTime, int desiredRemainingTime) {

        listRoute = buildingRouteFromTwoPoints(listRoute, disOffice, disPointToPoint,
                new int[]{priorityTask[0], priorityTask[1]},
                remainingTime, desiredRemainingTime);

        for (int i = 0; i < listRoute.size(); i++) {

            int maxDis = 0;
            int dis = listRoute.get(i).get(0); // оставшееся время у сотрудника в минутах
            int point1 = listRoute.get(i).get(1); // первая точка маршрута
            int point2 = listRoute.get(i).get(2);  // вторая точка маршрута

            listRoute.get(i).add(-1); // маршрут до третьей точке равен бесконечности

            /* построение всех удовлетворяющих условию (раб.день 8ч = 480 мин) маршрутов для мидла от средней по приоритету точки до любой другой средней по приоритету точки, за искл. пройденных точек */

            for (int j = 0; j < disPointToPoint.length; j++) {
                if (point2 != -1) {
                    int restDis = dis - (disPointToPoint[j][point2] + priorityTask[1]); //остаток рабочего времени
                    if (j != point1 && j != point2 && restDis >= maxDis) {
                        maxDis = restDis;
                        listRoute.get(i).set(0, restDis);
                        listRoute.get(i).set(3, j);
                    }
                }
            }
        }

        removeEmptyRoutes(listRoute);
        listRoute = sortedList(listRoute);

        return listRoute;
    }

    private static int[] getDistanceOfficeToPoints(int[] disToPoints) {
        Random rand = new Random();
        for (int i = 0; i < disToPoints.length; i++) {

          /*  int taskTime = 0;
            if (grade == 1) {
                disToPoints[i] += 240;
            } else if (grade == 2) {
                taskTime = 120;
            } else if (grade == 3) {
                taskTime = 90;
            }*/

            disToPoints[i] = rand.nextInt(TIME_ROUTE[0], TIME_ROUTE[1]);
        }
        return disToPoints;
    }

    private static int[][] getDistancePointsToPoints(int[][] disPointsToPoints) {
        Random rand = new Random();
        for (int i = 0; i < disPointsToPoints.length; i++) {
            for (int j = 0; j < disPointsToPoints.length; j++) {
                if (i == j) {
                    disPointsToPoints[i][j] = 0;
                } else if (j < i) {
                    disPointsToPoints[i][j] = disPointsToPoints[j][i];
                } else {
 /*                   int taskTime = 0;
                    if (grade == 1) {
                        taskTime = 240;
                    } else if (grade == 2) {
                        taskTime = 120;
                    } else if (grade == 3) {
                        taskTime = 90;
                    }*/

                    disPointsToPoints[i][j] = rand.nextInt(TIME_ROUTE[0], TIME_ROUTE[1]);
                }

            }
        }
        return disPointsToPoints;
    }

    private static void initDistanceBetweenPoints() {
        disOffice1ToHighTask = getDistanceOfficeToPoints(disOffice1ToHighTask);
        disOffice1ToMediumTask = getDistanceOfficeToPoints(disOffice1ToMediumTask);
        disOffice1ToLowTask = getDistanceOfficeToPoints(disOffice1ToLowTask);

        disOffice2ToHighTask = getDistanceOfficeToPoints(disOffice2ToHighTask);
        disOffice2ToMediumTask = getDistanceOfficeToPoints(disOffice2ToMediumTask);
        disOffice2ToLowTask = getDistanceOfficeToPoints(disOffice2ToLowTask);

        disOffice3ToHighTask = getDistanceOfficeToPoints(disOffice3ToHighTask);
        disOffice3ToMediumTask = getDistanceOfficeToPoints(disOffice3ToMediumTask);
        disOffice3ToLowTask = getDistanceOfficeToPoints(disOffice3ToLowTask);

        disMediumToMediumTask = getDistancePointsToPoints(disMediumToMediumTask);
        disLowToLowTask = getDistancePointsToPoints(disLowToLowTask);
        disLowToHighTask = getDistancePointsToPoints(disLowToHighTask);
        disLowToMediumTask = getDistancePointsToPoints(disLowToMediumTask);
        disMediumToHighTask = getDistancePointsToPoints(disMediumToHighTask);

    }

    private static void showRoutes(List<List<Integer>> list, String grade) {

        System.out.println("Возможные маршруты движения для " + grade + ":");

        for (int i = 0; i < list.size(); i++) {
            System.out.print("Оставшееся время: " + list.get(i).get(0) + "   ");
            System.out.print("Маршрут движения: ");
            for (int j = 1; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j));
                if (list.get(i).size() - 1 != j) {
                    System.out.print("->");
                    continue;
                }
                System.out.println();
            }
        }

        System.out.printf("Количество возможных маршрутов: " + list.size());
        System.out.println();
    }

    private static List<List<Integer>> sortedList(List<List<Integer>> listRoute) {
        listRoute = listRoute.stream().sorted((o1, o2) -> {
            for (int i = 0; i < Math.max(o1.size(), o2.size()); i++) {
                int c = o2.get(i).compareTo(o1.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        }).collect(Collectors.toList());

        return listRoute;
    }

    private static List<List<Integer>> removeEmptyRoutes(List<List<Integer>> list) {
        Iterator<List<Integer>> iterator = list.iterator();
        while (iterator.hasNext()) {
            List<Integer> num = iterator.next();
            if (num.get(num.size() - 2) == -1 || num.get(num.size() - 1) == -1) {
                iterator.remove();
            }
        }
        return list;
    }
}
