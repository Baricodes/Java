public class Teacher {
   
    private int id;
    private String name;
    private double salary;
    private double salaryEarned;

    public Teacher(int id, String name, double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.salaryEarned = 0;

    }

    /**
     * 
     * @return Teacher's ID
     */
    public int getID(){
        return this.id;
    }

    /**
     * 
     * @return Teacher's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * 
     * @return Teacher's salary
     */
    public double getSalary(){
        return this.salary;
    }

    /**
     * Sets salary
     * @param salary salary amount to be set
     */
    public void setSalary(double salary){
        this.salary = salary;
    }

    /**
     * Adds to salary
     * removes to schools total money earned
     * @param salary salary teacher's receive
     */
    public void receiveSalary(double salary){

        salaryEarned += salary;
        School.updateTotalMoneySpent(salary);
    }

    @Override
    public String toString(){
        return "Teacher's name: " +name+
                "\nTeacher's ID: " +id;
    }

}
