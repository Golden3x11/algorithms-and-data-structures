package Student;

public class Student {
    private String index;
    private String name;
    private String surname;
    private double grade;

    public Student(String name,String surname,String index,double grade){
        this.name=name;
        this.surname=surname;
        this.index=index;
        this.grade=grade;
    }
    public void showData(){
        System.out.printf("%6s %10s %12s %1.1f\n",getIndex(),getName(),getSurname(),getGrade());
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
