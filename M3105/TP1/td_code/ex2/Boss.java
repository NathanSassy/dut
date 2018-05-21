public final class Boss extends Employee {
    private double weeklySalary;

    public Boss(String first, String last, double s) {
        super(first, last);
        this.setWeeklySalary(s);
    }

    public void setWeeklySalary(double s) {
        weeklySalary = (s >
            0 ? s :
            0
        );
    }
    public
    double
    earnings() {
        return
        weeklySalary;
    }
    public
    String toString() {
        return "Boss: " +
            super
            .toString();
    }
}
