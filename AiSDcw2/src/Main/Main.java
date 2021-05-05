package Main;

import Iterators.ArrayIterator;
import Iterators.FilterIterator;
import Iterators.Predicate;
import Student.Student;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Student[] array = new Student[5];
        array[0] = new Student("Konrad", "Kielczynski", "11111", 5);
        array[1] = new Student("Jakub", "Kowalski", "22222", 4);
        array[2] = new Student("Jan", "Nowak", "33333", 2);
        array[3] = new Student("Szymon", "Paszkiewicz", "44444", 3.5);
        array[4] = new Student("Sebastian", "Ląka", "55555", 4.5);
        showAll(array);
        changeGrade(array);
        //showAll(array);
        averageGrade(array);
        failed(array);
    }

    public static void showAll(Student[] array) {
        for (Student student : array) {
            student.showData();
        }
        /*
        Iterator<Student> iterator= new ArrayIterator<>(array);
        while(iterator.hasNext()){
            iterator.next().showData();
        }
         */
    }

    public static void changeGrade(Student[] array) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Podaj indeks studenta do zmiany oceny");
        String indeks=scan.nextLine();
        System.out.println("Podaj ocene");
        double grade=Double.parseDouble(scan.nextLine());
        scan.close();
        Iterator<Student> iterator = new ArrayIterator<>(array);
        FilterIterator<Student> filterIterator = new FilterIterator<>(iterator, new Predicate() {
            @Override
            public boolean accept(Object arg) {

                return indeks.equals(((Student) arg).getIndex());
            }
        });
        while (filterIterator.hasNext()) {
            filterIterator.next().setGrade(grade);
        }
    }
    public static void averageGrade(Student[] array){
        Iterator<Student> iterator = new ArrayIterator<>(array);
        int valueOfStudents=0;
        double averageGrade = 0;
        FilterIterator<Student> filterIterator = new FilterIterator<>(iterator, new Predicate() {
            @Override
            public boolean accept(Object arg) {
                return ((Student)arg).getGrade()>=3;
            }
        });
        System.out.println();
        while (filterIterator.hasNext()) {
            averageGrade+=filterIterator.next().getGrade();
            valueOfStudents++;
        }
        System.out.println("Srednia zdających studentów to "+averageGrade/valueOfStudents);
    }
    public static void failed(Student[] array){
        System.out.println("Studenci nie zdający kursu");
        Iterator<Student> iterator = new ArrayIterator<>(array);
        FilterIterator<Student> filterIterator = new FilterIterator<>(iterator,arg ->((Student) arg).getGrade() < 3.0);
        System.out.println();
        while (filterIterator.hasNext()) {
            filterIterator.next().showData();
        }
    }
}
