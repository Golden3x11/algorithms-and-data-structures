package Zadanie2;

public class Person implements Comparable<Person> {

    private String name;
    private String surname;
    private int age;

    public Person(String name,String surname,int age){
        this.name=name;
        this.surname=surname;
        this.age=age;
    }

    @Override
    public String toString() {
        return  surname +
                " " + name +
                " " + age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        if (this.getSurname().equals(o.getSurname())){
            if(this.getName().equals(o.getName())) {
                if (this.getAge() > o.getAge()) return 1;
                if (this.getAge() < o.getAge()) return -1;
                return 0;
            }
            return this.getName().compareTo(o.getName());
        }
        return this.getSurname().compareTo(o.getSurname());

    }
}
