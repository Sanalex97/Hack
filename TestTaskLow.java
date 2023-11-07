import java.util.*;
import java.util.stream.Collectors;

public class TestTaskLow {
    public static void main(String[] args) {
        Random rand = new Random();
        List<List<Integer>> listRouteJuniorOffice1 = new ArrayList<>(); // список возможных маршрутов движения джунов для офиса 1

        int[] disOfficeToLowTask = new int[11];// массив для хранения времени на дорогу от офиса до всех задач низкого приоритета
        // с учетом времени на выполнение задачи, номер ячейки - номер агентской точки
        for (int i = 0; i < disOfficeToLowTask.length; i++) {
            disOfficeToLowTask[i] = rand.nextInt(30, 100) + 90;
        }

        int[][] disLowToLowTask = new int[disOfficeToLowTask.length][disOfficeToLowTask.length]; //матрица для хранения времени на дорогу от низкой точки по приоритету
        // до всех задач низкого приоритета с учетом времени на выполнение задачи, номер ячейки - номер агентской точки

        for (int i = 0; i < disOfficeToLowTask.length; i++) {
            for (int j = 0; j < disOfficeToLowTask.length; j++) {
                if (i == j) {
                    disLowToLowTask[i][j] = 0;
                } else if (j < i) {
                    disLowToLowTask[i][j] = disLowToLowTask[j][i];
                } else {
                    disLowToLowTask[i][j] = rand.nextInt(10, 100) + 90;
                }
            }
        }

        /* построение всех удовлетворяющих условию (раб.день 8ч = 480 мин) маршрутов для синьора от офиса до любой  высокой по приоритету точки и до средней точки */

        for (int i = 0; i < disOfficeToLowTask.length; i++) {
            for (int j = 0; j < disOfficeToLowTask.length; j++) {
                if (i != j) {
                    int disTwoSignorTask = 480 - (disOfficeToLowTask[i] + disLowToLowTask[i][j]); // оставшеейся время у сотрудника после выполнения по одной задачи высокого и среднего уровня
                    if (disTwoSignorTask >= 0) {
                        listRouteJuniorOffice1.add(Arrays.asList(disTwoSignorTask, i, j, -1, -1)); // добавление расстояния и массива агентских точек, на которых выполнены задачи
                    }
                }
            }
        }

        for (int i = 0; i < listRouteJuniorOffice1.size(); i++) {

            int dis = listRouteJuniorOffice1.get(i).get(0); // оставшееся время у сотрудника в минутах
            int point1 = listRouteJuniorOffice1.get(i).get(1); // первая точка маршрута
            int point2 = listRouteJuniorOffice1.get(i).get(2);  // вторая точка маршрута

            /* построение всех удовлетворяющих условию (раб.день 8ч = 480 мин) маршрутов для мидла от средней по приоритету точки до любой другой средней по приоритету точки, за искл. пройденных точек */

            for (int j = 0; j < disLowToLowTask.length; j++) {
                int restDis = dis - disLowToLowTask[j][point2]; //остаток рабочего времени
                if (j != point1 && j != point2 && restDis >= 0) {
                    listRouteJuniorOffice1.get(i).set(0, restDis);
                    listRouteJuniorOffice1.get(i).set(3, j);
                }
            }
        }

        for (int i = 0; i < listRouteJuniorOffice1.size(); i++) {

            int dis = listRouteJuniorOffice1.get(i).get(0); // оставшееся время у сотрудника в минутах
            int point1 = listRouteJuniorOffice1.get(i).get(1); // первая точка маршрута
            int point2 = listRouteJuniorOffice1.get(i).get(2);  // вторая точка маршрута
            int point3 = listRouteJuniorOffice1.get(i).get(3); // третья точка маршрута

            /* построение всех удовлетворяющих условию (раб.день 8ч = 480 мин) маршрутов для мидла от средней по приоритету точки до любой другой средней по приоритету точки, за искл. пройденных точек */

            for (int j = 0; j < disLowToLowTask.length; j++) {
                if (point3 != -1) {
                    int restDis = dis - disLowToLowTask[j][point3]; //остаток рабочего времени
                    if (j != point1 && j != point2 && j != point3 && restDis >= 0) {
                        listRouteJuniorOffice1.get(i).set(0, restDis);
                        listRouteJuniorOffice1.get(i).set(4, j);
                    }
                }
            }
        }

      //  System.out.println("Маршрут для джуна из четырех точек: ");

        listRouteJuniorOffice1 = listRouteJuniorOffice1.stream().sorted((o1, o2) -> {
            for (int i = 0; i < Math.max(o1.size(), o2.size()); i++) {
                int c = o2.get(i).compareTo(o1.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        }).collect(Collectors.toList());

/*        for (int i = 0; i < listRouteJuniorOffice1.size(); i++) {
            if (listRouteJuniorOffice1.get(i).get(3) != 0) {
                System.out.println(Arrays.toString(new List[]{listRouteJuniorOffice1.get(i)}));
            }
        }*/

    //    System.out.println("Отсортированный: " + listRouteJuniorOffice1.size());


        listRouteJuniorOffice1 = removeEmptyRoutes(listRouteJuniorOffice1);


     //   System.out.println("Удаленный: " + listRouteJuniorOffice1.size());

        showRoutes(listRouteJuniorOffice1, "джун");

    }

    private static void init() {

    }

    private static List<List<Integer>> removeEmptyRoutes(List<List<Integer>> list) {
        Iterator<List<Integer>> iterator = list.iterator();
        while (iterator.hasNext()) {
            List<Integer> num = iterator.next();
            if (num.get(3) == -1 || num.get(4) == -1) {
                iterator.remove();
            }
        }
        return list;
    }

    private static void showRoutes(List<List<Integer>> list, String grade) {

        System.out.println("Возможные маршруты движения для " + grade + ":");

        for (int i = 0; i < list.size(); i++) {
            System.out.print("Оставшееся время: " + list.get(i).get(0) + "   ");
            System.out.print("Маршрут движения: ");
            for (int j = 1; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j));
                if (list.get(i).size() -1 != j) {
                    System.out.print("->");
                    continue;
                }
                System.out.println();
            }
        }

        System.out.printf("Количество возможных маршрутов: " + list.size());
    }
}
