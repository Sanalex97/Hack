import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<AgencyPoint> agencyPointList = new ArrayList<>();
    static List<AgencyPoint> agencyPointListHighPriority = new ArrayList<>();
    static List<AgencyPoint> agencyPointListMediumPriority = new ArrayList<>();
    static List<AgencyPoint> agencyPointListLowPriority = new ArrayList<>();

    public static void main(String[] args) {
            init();
            distributionTasks();


        }

    public static List<List<AgencyPoint>> getTasksWithPriority(List<AgencyPoint> agencyPoints) {
        for (int i = 0; i < agencyPoints.size(); i++) {
            AgencyPoint agencyPoint = agencyPoints.get(i);
            if (getTasksWithHighPriority(agencyPoint)) {
                agencyPointListHighPriority.add(agencyPoint);
            }
            if (getTasksWithMediumPriority(agencyPoint)) {
                agencyPointListMediumPriority.add(agencyPoint);
            }
            if (getTasksWithLowPriority(agencyPoint)) {
                agencyPointListLowPriority.add(agencyPoint);
            }
        }

        return Arrays.asList(agencyPointListHighPriority, agencyPointListMediumPriority, agencyPointListLowPriority);
    }

    public static boolean getTasksWithHighPriority(AgencyPoint agencyPoint) {
        if (agencyPoint.getNumberOfDaysOfIssue() > 14) {
            return true;
        } else if (agencyPoint.getNumberOfDaysOfIssue() > 7 && agencyPoint.getNumberOfApproved() > 0) {
            return true;
        }
        return false;
    }

    public static boolean getTasksWithMediumPriority(AgencyPoint agencyPoint) {
        if (agencyPoint.getNumberOfIssued() > 0 && (agencyPoint.getNumberOfIssued() / agencyPoint.getNumberOfApproved()) * 100 < 50) {
            return true;
        }
        return false;
    }

    public static boolean getTasksWithLowPriority(AgencyPoint agencyPoint) {
        if (agencyPoint.getPointsConnected().equals("вчера") || !agencyPoint.isDelivered()) {
            return true;
        }
        return false;
    }

    private static void init() {

        AgencyPoint agencyPoint1 = new AgencyPoint(1, "вчера", false, 0, 0, 0);
        AgencyPoint agencyPoint2 = new AgencyPoint(2, "давно", true, 3, 15, 3);
        AgencyPoint agencyPoint3 = new AgencyPoint(3, "давно", true, 3, 9, 1);
        AgencyPoint agencyPoint4 = new AgencyPoint(4, "давно", true, 0, 38, 23);
        AgencyPoint agencyPoint5 = new AgencyPoint(5, "давно", false, 0, 14, 0);
        AgencyPoint agencyPoint6 = new AgencyPoint(6, "давно", true, 12, 19, 1);
        AgencyPoint agencyPoint7 = new AgencyPoint(7, "давно", true, 27, 19, 12);
        AgencyPoint agencyPoint8 = new AgencyPoint(8, "давно", true, 33, 84, 63);
        AgencyPoint agencyPoint9 = new AgencyPoint(9, "давно", true, 2, 15, 1);
        AgencyPoint agencyPoint10 = new AgencyPoint(10, "давно", true, 0, 19, 0);
        AgencyPoint agencyPoint11 = new AgencyPoint(11, "давно", true, 15, 29, 15);
        AgencyPoint agencyPoint12 = new AgencyPoint(12, "вчера", false, 0, 0, 0);
        AgencyPoint agencyPoint13 = new AgencyPoint(13, "давно", true, 4, 21, 5);
        AgencyPoint agencyPoint14 = new AgencyPoint(14, "вчера", false, 0, 5, 0);
        AgencyPoint agencyPoint15 = new AgencyPoint(15, "давно", true, 7, 14, 3);
        AgencyPoint agencyPoint16 = new AgencyPoint(16, "вчера", false, 0, 0, 0);
        AgencyPoint agencyPoint20 = new AgencyPoint(20, "давно", true, 4, 35, 15);
        AgencyPoint agencyPoint21 = new AgencyPoint(21, "вчера", false, 0, 6, 0);
        AgencyPoint agencyPoint22 = new AgencyPoint(22, "давно", true, 6, 18, 6);
        AgencyPoint agencyPoint23 = new AgencyPoint(23, "давно", true, 0, 15, 5);
        AgencyPoint agencyPoint24 = new AgencyPoint(24, "давно", true, 2, 96, 20);
        AgencyPoint agencyPoint25 = new AgencyPoint(25, "давно", false, 0, 0, 0);
        AgencyPoint agencyPoint26 = new AgencyPoint(26, "давно", true, 0, 16, 0);
        AgencyPoint agencyPoint27 = new AgencyPoint(27, "давно", true, 3, 43, 29);
        AgencyPoint agencyPoint28 = new AgencyPoint(28, "давно", true, 3, 13, 4);
        AgencyPoint agencyPoint29 = new AgencyPoint(29, "давно", true, 6, 19, 5);
        AgencyPoint agencyPoint30 = new AgencyPoint(30, "давно", true, 16, 45, 30);
        AgencyPoint agencyPoint31 = new AgencyPoint(31, "давно", true, 1, 19, 4);
        AgencyPoint agencyPoint32 = new AgencyPoint(32, "давно", true, 3, 20, 9);
        AgencyPoint agencyPoint33 = new AgencyPoint(33, "вчера", false, 0, 19, 0);
        AgencyPoint agencyPoint34 = new AgencyPoint(34, "давно", true, 76, 82, 72);
        AgencyPoint agencyPoint35 = new AgencyPoint(35, "давно", true, 23, 32, 21);
        AgencyPoint agencyPoint36 = new AgencyPoint(36, "давно", true, 4, 19, 4);
        AgencyPoint agencyPoint37 = new AgencyPoint(37, "давно", true, 9, 10, 7);
        AgencyPoint agencyPoint38 = new AgencyPoint(38, "вчера", false, 0, 13, 0);
        AgencyPoint agencyPoint39 = new AgencyPoint(39, "вчера", false, 0, 10, 0);
        AgencyPoint agencyPoint40 = new AgencyPoint(40, "давно", true, 6, 30, 14);
        AgencyPoint agencyPoint41 = new AgencyPoint(41, "давно", true, 6, 65, 12);
        AgencyPoint agencyPoint42 = new AgencyPoint(42, "давно", true, 3, 20, 4);
        AgencyPoint agencyPoint43 = new AgencyPoint(43, "вчера", false, 0, 0, 0);

        agencyPointList.add(agencyPoint1);
        agencyPointList.add(agencyPoint2);
        agencyPointList.add(agencyPoint3);
        agencyPointList.add(agencyPoint4);
        agencyPointList.add(agencyPoint5);
        agencyPointList.add(agencyPoint6);
        agencyPointList.add(agencyPoint7);
        agencyPointList.add(agencyPoint8);
        agencyPointList.add(agencyPoint9);
        agencyPointList.add(agencyPoint10);
        agencyPointList.add(agencyPoint11);
        agencyPointList.add(agencyPoint12);
        agencyPointList.add(agencyPoint13);
        agencyPointList.add(agencyPoint14);
        agencyPointList.add(agencyPoint15);
        agencyPointList.add(agencyPoint16);
        agencyPointList.add(agencyPoint20);
        agencyPointList.add(agencyPoint21);
        agencyPointList.add(agencyPoint22);
        agencyPointList.add(agencyPoint23);
        agencyPointList.add(agencyPoint24);
        agencyPointList.add(agencyPoint25);
        agencyPointList.add(agencyPoint26);
        agencyPointList.add(agencyPoint27);
        agencyPointList.add(agencyPoint28);
        agencyPointList.add(agencyPoint29);
        agencyPointList.add(agencyPoint30);
        agencyPointList.add(agencyPoint31);
        agencyPointList.add(agencyPoint32);
        agencyPointList.add(agencyPoint33);
        agencyPointList.add(agencyPoint34);
        agencyPointList.add(agencyPoint35);
        agencyPointList.add(agencyPoint36);
        agencyPointList.add(agencyPoint37);
        agencyPointList.add(agencyPoint38);
        agencyPointList.add(agencyPoint39);
        agencyPointList.add(agencyPoint40);
        agencyPointList.add(agencyPoint41);
        agencyPointList.add(agencyPoint42);
        agencyPointList.add(agencyPoint43);

    }

    private static void distributionTasks () {
        List<List<AgencyPoint>> listTaskEmployees = getTasksWithPriority(agencyPointList);

        for (int i = 0; i < listTaskEmployees.size(); i++) {
            if (i == 0) {
                System.out.println("Задачи с высоким приоритетом: ");
            }
            if (i == 1) {
                System.out.println("Задачи со средним приоритетом: ");
            }
            if (i == 2) {
                System.out.println("Задачи с низким приоритетом: ");
            }

            for (int j = 0; j < listTaskEmployees.get(i).size(); j++) {
                System.out.println(listTaskEmployees.get(i).get(j).getId());
            }

            System.out.println("Кол-во задач: " + listTaskEmployees.get(i).size());
        }
    }


}