import java.util.ArrayList;
import java.util.Scanner;

// Course class
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        } else {
            return false;
        }
    }

    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    public int availableSlots() {
        return capacity - enrolledStudents;
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Slots: " + availableSlots());
        System.out.println("--------------------------");
    }
}

// Student class
class Student {
    int studentID;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Course " + course.title + " is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println("Successfully dropped " + course.title);
        } else {
            System.out.println("You are not registered for " + course.title);
        }
    }

    public void listRegisteredCourses() {
        System.out.println("Courses registered by " + name + ":");
        for (Course course : registeredCourses) {
            System.out.println(course.title);
        }
    }
}

// Main class
public class CourseManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample courses
        Course course1 = new Course("CS101", "Introduction to Computer Science", "Basics of CS", 2, "Mon-Wed-Fri 10AM");
        Course course2 = new Course("MATH201", "Calculus I", "Intro to Calculus", 3, "Tue-Thu 1PM");
        Course course3 = new Course("PHYS301", "Physics I", "Fundamentals of Physics", 2, "Mon-Wed-Fri 2PM");
        
        Course[] courses = { course1, course2, course3 };

        // Sample student
        Student student = new Student(123, "John Doe");

        int choice;
        do {
            System.out.println("\nCourse Management System:");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses) {
                        course.displayCourseDetails();
                    }
                    break;
                case 2:
                    System.out.print("Enter course code to register: ");
                    String registerCode = scanner.next();
                    boolean courseFound = false;
                    for (Course course : courses) {
                        if (course.courseCode.equalsIgnoreCase(registerCode)) {
                            student.registerCourse(course);
                            courseFound = true;
                            break;
                        }
                    }
                    if (!courseFound) {
                        System.out.println("Invalid course code.");
                    }
                    break;
                case 3:
                    System.out.print("Enter course code to drop: ");
                    String dropCode = scanner.next();
                    courseFound = false;
                    for (Course course : courses) {
                        if (course.courseCode.equalsIgnoreCase(dropCode)) {
                            student.dropCourse(course);
                            courseFound = true;
                            break;
                        }
                    }
                    if (!courseFound) {
                        System.out.println("Invalid course code.");
                    }
                    break;
                case 4:
                    student.listRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
