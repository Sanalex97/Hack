public class AgencyPoint {
    private int id;
    // private String address; //адрес точки
    private String pointsConnected; // когда подключена точка?
    private boolean isDelivered;// карты и материалы доставлены?
    private int numberOfDaysOfIssue;// кол-во дней после выдачи последней карты?
    private int numberOfApproved;// кол-во одобренных заявок?
    private int numberOfIssued;// кол-во выданных карт?

    public AgencyPoint(int id, String pointsConnected, boolean isDelivered, int numberOfDaysOfIssue, int numberOfApproved, int numberOfIssued) {
        this.id = id;
        //  this.address = address;
        this.pointsConnected = pointsConnected;
        this.isDelivered = isDelivered;
        this.numberOfDaysOfIssue = numberOfDaysOfIssue;
        this.numberOfApproved = numberOfApproved;
        this.numberOfIssued = numberOfIssued;
    }

    public int getId() {
        return id;
    }


    public String getPointsConnected() {
        return pointsConnected;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public int getNumberOfDaysOfIssue() {
        return numberOfDaysOfIssue;
    }

    public int getNumberOfApproved() {
        return numberOfApproved;
    }

    public int getNumberOfIssued() {
        return numberOfIssued;
    }
}
