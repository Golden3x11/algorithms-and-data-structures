public class Student implements Comparable<Student>{
    private double age;
    private String index;
    private String surname;
    public Student(String name, String index, double age ) {
        this.age = age;
        this.index = index;
        this.surname = name;
    }

    @Override
    public int compareTo(Student o) {
        if (this.getIndex().equals(o.getIndex())){
            if(this.getSurname().equals(o.getSurname())) {
                if (this.getAge() > o.getAge()) return 1;
                if (this.getAge() < o.getAge()) return -1;
                return 0;
            }
            return this.getSurname().compareTo(o.getSurname());
        }
        return this.getIndex().compareTo(o.getIndex());

    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String name) {
        this.surname = name;
    }

    @Override
    public String toString() {
        return"Nazwisko:" + surname +", Index:" + index  +
                ", Wiek:" + age  ;
    }
}
