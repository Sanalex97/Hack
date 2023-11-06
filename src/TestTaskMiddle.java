import java.util.*;
import java.util.stream.Collectors;

public class TestTaskMiddle {
    public static void main(String[] args) {
        Random rand = new Random();
        List<List<Integer>> listRouteMiddleOffice1 = new ArrayList<>();

        HashMap<Integer, int[]> mapRouteMiddleOffice1 = new HashMap<>(); //id офиса + map c id средних точек


        int[] disOfficeToMediumTask = new int[27];
        for (int i = 0; i < disOfficeToMediumTask.length; i++) {
            disOfficeToMediumTask[i] = rand.nextInt(30, 60) + 120;
        }

        int[][] disMediumTaskToMediumTask = new int[disOfficeToMediumTask.length][disOfficeToMediumTask.length];
        for (int i = 0; i < disOfficeToMediumTask.length; i++) {
            for (int j = 0; j < disOfficeToMediumTask.length; j++) {
                if (i == j) {
                    disMediumTaskToMediumTask[i][j] = 0;
                } else if (j < i) {
                    disMediumTaskToMediumTask[i][j] = disMediumTaskToMediumTask[j][i];
                } else {
                    disMediumTaskToMediumTask[i][j] = rand.nextInt(30, 60) + 120;
                }

            }
        }


        for (int i = 0; i < disOfficeToMediumTask.length; i++) {
            for (int j = 0; j < disMediumTaskToMediumTask.length; j++) {
                if (i != j) {
                    int disTwoMiddleTask = 480 - (disOfficeToMediumTask[i] + disMediumTaskToMediumTask[i][j]);
                    if (disTwoMiddleTask > 150) {

                        listRouteMiddleOffice1.add(Arrays.asList(disTwoMiddleTask, i, j, 0));
                       /* int[] middleTasks = new int[3];
                        middleTasks[0] = i;
                        middleTasks[1] = j;

                        mapRouteMiddleOffice1.put(disTwoMiddleTask, middleTasks);*/
                    }
                }
            }
        }

        System.out.println("Маршрут для мидла из двух мидловских задач: ");
        for (int i = 0; i < listRouteMiddleOffice1.size(); i++) {
            System.out.println(Arrays.toString(new List[]{listRouteMiddleOffice1.get(i)}));
        }
        System.out.println("Возможных вариантов: " + listRouteMiddleOffice1.size());



       /* for (Integer k : mapRouteMiddleOffice1.keySet()) {
            for (int i = 0; i < disMediumTaskToMediumTask.length; i++) {

                if (i != mapRouteMiddleOffice1.get(k)[0] && i != mapRouteMiddleOffice1.get(k)[1] && k - disMediumTaskToMediumTask[i][mapRouteMiddleOffice1.get(k)[1]] >= 0) {
                    System.out.println("Остаток времени: " + (k - disMediumTaskToMediumTask[i][mapRouteMiddleOffice1.get(k)[1]]));

                }
            }

        }
*/


        for (int i = 0; i < listRouteMiddleOffice1.size(); i++) {

            int dis = listRouteMiddleOffice1.get(i).get(0);
            int point1 = listRouteMiddleOffice1.get(i).get(1);
            int point2 = listRouteMiddleOffice1.get(i).get(2);


            for (int j = 0; j < disMediumTaskToMediumTask.length; j++) {
                int restDis = dis - disMediumTaskToMediumTask[j][point2];//остаток рабочего времени
                if (j != point1 && j != point2 && restDis >= 0) {
                    listRouteMiddleOffice1.get(i).set(0, restDis);
                    listRouteMiddleOffice1.get(i).set(3, j);
                }
            }
        }


        System.out.println("Маршрут для мидла из трех мидловских задач: ");
        int count = 0;
        for (int i = 0; i < listRouteMiddleOffice1.size(); i++) {
            if (listRouteMiddleOffice1.get(i).get(3) != 0) {
                System.out.println(Arrays.toString(new List[]{listRouteMiddleOffice1.get(i)}));
                count++;
            }
        }

        System.out.println("Возможных вариантов: " + count);


        System.out.println("Сортируем: ");

        listRouteMiddleOffice1 = listRouteMiddleOffice1.stream().sorted((o1, o2) -> {
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                int c = o1.get(i).compareTo(o2.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        }).collect(Collectors.toList());

        for (int i = 0; i < listRouteMiddleOffice1.size(); i++) {
            if (listRouteMiddleOffice1.get(i).get(3) != 0) {
                System.out.println(Arrays.toString(new List[]{listRouteMiddleOffice1.get(i)}));
            }
        }

        System.out.println("Отсортированный: " + count);
        /*int disThreeMiddleTask = disMediumTaskToMediumTask[mapRouteMiddleOffice1]*/
    }
    //int disThreeMiddleTask = disMediumTaskToMediumTask[mapRouteMiddleOffice1.]
}

