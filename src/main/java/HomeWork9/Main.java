package HomeWork9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Александр", Arrays.asList(new Course("Математика"), new Course("Физика"),
                new Course("Химия"))));
        students.add(new Student("Владимир", Arrays.asList(new Course("Математика"), new Course("География"),
                new Course("Психология"), new Course("ИТ"))));
        students.add(new Student("Юлия", Arrays.asList(new Course("Физика"), new Course("Химия"),
                new Course("Психология"), new Course("ИТ"))));
        students.add(new Student("Мария", Arrays.asList(new Course("Математика"), new Course("География"),
                new Course("Психология"), new Course("Информационные технологии"))));
        students.add(new Student("Андрей", Arrays.asList(new Course("Физика"), new Course("Химия"),
                new Course("Психология"), new Course("ИТ"))));

        //1
        System.out.println(students.stream()
                .map(s -> s.getCourses())
                .flatMap(f -> f.stream())
                .collect(Collectors.toSet()));
        System.out.println("*******");

        //2
        System.out.println(students.stream()
                .sorted((s1, s2) -> s2.getCourses().size() - s1.getCourses().size())
                .limit(2)
                .collect(Collectors.toList()));
        System.out.println("*******");

        //3
        Course course = new Course("Физика");
        System.out.println(students.stream()
                .filter(s -> s.getCourses().contains(course))
                .collect(Collectors.toList()));
    }
}
