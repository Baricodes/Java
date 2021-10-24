public class Student {
    
    private int id;
    private String name;
    private int grade;
    private double feesPaid;
    private double feesTotal;

    /**
     * To create new student by initializzizng
     * Fees for every student is $30,000
     * Fees paid intially is 0.
     * @param id id for thhe student: unique
     * @param name name of the student.
     * @param grade grade of the student.
     */
    public Student(int id, String name, int grade){
        feesPaid = 0;
        feesTotal = 30000;
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    /**
     * Used to update the students grade
     * @param grade grade the new grade of the student
     */
    public void setGrade(int grade){
        this.grade = grade;
    }

    /**
     * Method to upadte student fees paid
     * @param fees fees is the amount of the total fee the student wants to pay
     */
    public void payFees(double fees){
        feesPaid += fees;
        School.updateTotalMoneyEarned(feesPaid);
    }

    /**
     * 
     * @return Student's id
     */
    public int getID(){
        return this.id;
    }

    /**
     * 
     * @return Student's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * 
     * @return Student's grade level
     */
    public int getGrade(){
        return this.grade;
    }

    /**
     * 
     * @return Student's fees paid
     */
    public double getFeesPaid(){
        return this.feesPaid;
    }

    /**
     * 
     * @return Student's total fees
     */
    public double getFeesTotal(){
        return this.feesTotal;
    }

    public double getRemainingFees(){
        return feesTotal - feesPaid;
    }

    @Override
    public String toString() {
        return "Student's name: " +name+
                "\nStudent's ID: " +id;
    }

}
