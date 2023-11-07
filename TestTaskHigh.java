import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TestTaskHigh {
    public static void main(String[] args) {
        Random rand = new Random();
        List<List<Integer>> listRouteSignorOffice1 = new ArrayList<>(); // список возможных маршрутов движения cиньоров для офиса 1

        int[] disOfficeToHighTask = new int[8]; // массив для хранения времени на дорогу от офиса до всех задач высокого приоритета
        // с учетом времени на выполнение задачи, номер ячейки - номер агентской точки
        for (int i = 0; i < disOfficeToHighTask.length; i++) {
            disOfficeToHighTask[i] = rand.nextInt(10, 100) + 240;
        }

        int[] disOfficeToMediumTask = new int[27]; // массив для хранения времени на дорогу от офиса до всех задач среднего приоритета
        // с учетом времени на выполнение задачи, номер ячейки - номер агентской точки
        for (int i = 0; i < disOfficeToMediumTask.length; i++) {
            disOfficeToMediumTask[i] = rand.nextInt(10, 100) + 120;
        }


        int[][] disMediumToHighTask = new int[disOfficeToHighTask.length][disOfficeToHighTask.length]; //матрица для хранения времени на дорогу от средней точки до всех задач высокого приоритета
        // с учетом времени на выполнение задачи, номер ячейки - номер агентской точки

        for (int i = 0; i < disMediumToHighTask.length; i++) {
            for (int j = 0; j < disMediumToHighTask.length; j++) {
                if (i == j) {
                    disMediumToHighTask[i][j] = 0;
                } else if (j < i) {
                    disMediumToHighTask[i][j] = disMediumToHighTask[j][i];
                } else {
                    disMediumToHighTask[i][j] = rand.nextInt(10, 100) + 120;
                }
            }
        }

        /* построение всех удовлетворяющих условию (раб.день 8ч = 480 мин) маршрутов для синьора от офиса до любой  высокой по приоритету точки и до средней точки */

        for (int i = 0; i < disOfficeToHighTask.length; i++) {
            for (int j = 0; j < disMediumToHighTask.length; j++) {
                if (i != j) {
                    int disTwoSignorTask = 480 - (disOfficeToHighTask[i] + disMediumToHighTask[i][j]); // оставшеейся время у сотрудника после выполнения по одной задачи высокого и среднего уровня
                    if (disTwoSignorTask >= 0) {
                        listRouteSignorOffice1.add(Arrays.asList(disTwoSignorTask, i, j)); // добавление расстояния и массива агентских точек, на которых выполнены задачи
                    }
                }
            }
        }

        System.out.println("Маршрут для синьора из двух точек: ");

        listRouteSignorOffice1 = listRouteSignorOffice1.stream().sorted((o1, o2) -> {
            for (int i = 0; i < Math.max(o1.size(), o2.size()); i++) {
                int c = o2.get(i).compareTo(o1.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        }).collect(Collectors.toList());

        for (int i = 0; i < listRouteSignorOffice1.size(); i++) {
            System.out.println(Arrays.toString(new List[]{listRouteSignorOffice1.get(i)}));
        }

        System.out.println("Отсортированный: " + listRouteSignorOffice1.size());
    }
}
