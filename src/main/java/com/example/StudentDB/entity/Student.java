package com.example.StudentDB.entity;


import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;


    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "number")
    private String number;
    @Column(name = "age")
    private int age;
    @Column(name = "class")
    private int clas;
    @Column(name = "subclass")
    private String subClass;

    public Student() {
    }

    public Student(String firstName, String lastName, String number, int age, int clas, String subClass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.age = age;
        this.clas = clas;
        this.subClass = subClass;
    }



    public String getFirstName() {
        return toCorrectCase(firstName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return toCorrectCase(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return toCorrectNumber(number);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClas() {
        return clas;
    }

    public void setClas(int clas) {
        this.clas = clas;
    }

    public String getSubClass() {
        return subClass.toUpperCase();
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public String toCorrectCase(String str) {
        if (str.length() > 1) {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
        }
        else {
            return str.toUpperCase();
        }
    }

    public String toCorrectNumber(String number) {
        long countNumber = number.chars().count();
        if (countNumber == 9) {
            return "380" + number;
        } else if (countNumber == 12 && number.substring(0, 3) != "380") {
            return "380" + number.substring(3, 12);
        } else {
            return number;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        if (clas != student.clas) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) return false;
        if (number != null ? !number.equals(student.number) : student.number != null) return false;
        if (subClass != null ? !subClass.equals(student.subClass) : student.subClass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + clas;
        result = 31 * result + (subClass != null ? subClass.hashCode() : 0);
        return result;
    }
}
