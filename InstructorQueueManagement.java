
/**
 * Main class that manages a queue of instructors with sorting capabilities.
 * This program allows users to add instructors and sort them by last name or number of courses.
 */
import java.util.Scanner;

public class InstructorQueueManagement {
  /**
   * The main method that runs the application.
   * Prompts the user to enter information for 5 instructors and displays sorted
   * results.
   * 
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // Initialize the queue with capacity for 10 instructors
    InstructorQueue queue = new InstructorQueue(10);

    System.out.println("Please enter information for 5 instructors.");

    // Add 5 instructors to the queue
    for (int i = 1; i <= 5; i++) {
      try {
        System.out.println("\nInstructor #" + i);

        // Get first name with validation
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        if (firstName == null || firstName.trim().isEmpty()) {
          System.out.println("Error: First name cannot be empty. Please try again.");
          i--; // Retry this instructor
          continue;
        }

        // Get last name with validation
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        if (lastName == null || lastName.trim().isEmpty()) {
          System.out.println("Error: Last name cannot be empty. Please try again.");
          i--; // Retry this instructor
          continue;
        }

        // Get number of courses with validation
        System.out.print("Enter number of courses teaching: ");
        String coursesInput = scanner.nextLine();
        int numCourses;

        try {
          numCourses = Integer.parseInt(coursesInput);
          if (numCourses < 0) {
            System.out.println("Error: Number of courses cannot be negative. Please try again.");
            i--; // Retry this instructor
            continue;
          }
        } catch (NumberFormatException e) {
          System.out.println("Error: Please enter a valid number for courses. Please try again.");
          i--; // Retry this instructor
          continue;
        }

        // Create and add the instructor to the queue
        Instructor instructor = new Instructor(firstName, lastName, numCourses);
        queue.enqueue(instructor);

      } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        System.out.println("Please try adding this instructor again.");
        i--; // Retry this instructor
      }
    }

    // Display original queue
    System.out.println("\nOriginal Queue Contents:");
    queue.display();

    try {
      // Sort by last name in descending order
      InstructorQueue lastNameSortedQueue = new InstructorQueue(queue.size());
      copyQueue(queue, lastNameSortedQueue);
      lastNameSortedQueue.quickSortByLastName(0, lastNameSortedQueue.size() - 1);

      System.out.println("\nQueue Sorted by Last Name (Descending):");
      lastNameSortedQueue.display();

      // Sort by number of courses in descending order
      InstructorQueue coursesSortedQueue = new InstructorQueue(queue.size());
      copyQueue(queue, coursesSortedQueue);
      coursesSortedQueue.quickSortByCourses(0, coursesSortedQueue.size() - 1);

      System.out.println("\nQueue Sorted by Number of Courses (Descending):");
      coursesSortedQueue.display();
    } catch (Exception e) {
      System.out.println("An error occurred during sorting: " + e.getMessage());
    }

    scanner.close();
  }

  /**
   * Helper method to copy the contents of one queue to another.
   * 
   * @param source      The source queue to copy from
   * @param destination The destination queue to copy to
   * @throws IllegalArgumentException If either queue is null
   */
  private static void copyQueue(InstructorQueue source, InstructorQueue destination) {
    // Input validation
    if (source == null) {
      throw new IllegalArgumentException("Source queue cannot be null");
    }

    if (destination == null) {
      throw new IllegalArgumentException("Destination queue cannot be null");
    }

    // Copy each instructor from source to destination
    for (int i = 0; i < source.size(); i++) {
      destination.enqueue(source.getInstructorAt(i));
    }
  }
}

/**
 * Represents an instructor with first name, last name, and number of courses.
 */
class Instructor {
  private String firstName;
  private String lastName;
  private int numCourses;

  /**
   * Creates a new Instructor with the specified attributes.
   * 
   * @param firstName  The first name of the instructor
   * @param lastName   The last name of the instructor
   * @param numCourses The number of courses the instructor teaches
   * @throws IllegalArgumentException If firstName or lastName is null or empty
   */
  public Instructor(String firstName, String lastName, int numCourses) {
    // Input validation
    if (firstName == null || firstName.trim().isEmpty()) {
      throw new IllegalArgumentException("First name cannot be null or empty");
    }

    if (lastName == null || lastName.trim().isEmpty()) {
      throw new IllegalArgumentException("Last name cannot be null or empty");
    }

    if (numCourses < 0) {
      throw new IllegalArgumentException("Number of courses cannot be negative");
    }

    this.firstName = firstName;
    this.lastName = lastName;
    this.numCourses = numCourses;
  }

  /**
   * Gets the first name of the instructor.
   * 
   * @return The first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets the last name of the instructor.
   * 
   * @return The last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Gets the number of courses the instructor teaches.
   * 
   * @return The number of courses
   */
  public int getNumCourses() {
    return numCourses;
  }

  /**
   * Returns a string representation of the instructor.
   * 
   * @return A string in the format "firstName lastName, Courses: numCourses"
   */
  @Override
  public String toString() {
    return firstName + " " + lastName + ", Courses: " + numCourses;
  }
}

/**
 * A queue data structure specifically for storing Instructor objects.
 * Includes methods for sorting instructors by last name or number of courses.
 */
class InstructorQueue {
  private Instructor[] instructors;
  private int capacity;
  private int size;

  /**
   * Creates a new queue with the specified capacity.
   * 
   * @param capacity The maximum number of instructors the queue can hold
   * @throws IllegalArgumentException If capacity is less than or equal to 0
   */
  public InstructorQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Queue capacity must be greater than 0");
    }

    this.capacity = capacity;
    this.instructors = new Instructor[capacity];
    this.size = 0;
  }

  /**
   * Adds an instructor to the end of the queue.
   * 
   * @param instructor The instructor to add
   * @throws IllegalArgumentException If instructor is null
   * @throws IllegalStateException    If queue is full
   */
  public void enqueue(Instructor instructor) {
    // Input validation
    if (instructor == null) {
      throw new IllegalArgumentException("Cannot add null instructor to the queue");
    }

    if (size >= capacity) {
      throw new IllegalStateException("Queue is full! Cannot add more instructors.");
    }

    instructors[size++] = instructor;
  }

  /**
   * Removes and returns the instructor at the front of the queue.
   * 
   * @return The instructor at the front of the queue
   * @throws IllegalStateException If queue is empty
   */
  public Instructor dequeue() {
    if (size == 0) {
      throw new IllegalStateException("Queue is empty! Cannot remove instructors.");
    }

    // Get the instructor at the front of the queue
    Instructor instructor = instructors[0];

    // Shift all elements to the left
    for (int i = 0; i < size - 1; i++) {
      instructors[i] = instructors[i + 1];
    }

    // Clear the last position and decrease size
    instructors[--size] = null;
    return instructor;
  }

  /**
   * Gets the instructor at the specified index without removing it.
   * 
   * @param index The index of the instructor to get
   * @return The instructor at the specified index, or null if index is invalid
   */
  public Instructor getInstructorAt(int index) {
    if (index < 0 || index >= size) {
      return null;
    }

    return instructors[index];
  }

  /**
   * Swaps two instructors in the queue.
   * 
   * @param i The index of the first instructor
   * @param j The index of the second instructor
   * @throws IndexOutOfBoundsException If either index is invalid
   */
  public void swapInstructors(int i, int j) {
    // Input validation
    if (i < 0 || i >= size || j < 0 || j >= size) {
      throw new IndexOutOfBoundsException("Invalid index for swapping");
    }

    Instructor temp = instructors[i];
    instructors[i] = instructors[j];
    instructors[j] = temp;
  }

  /**
   * Gets the current number of instructors in the queue.
   * 
   * @return The number of instructors
   */
  public int size() {
    return size;
  }

  /**
   * Displays all instructors in the queue.
   */
  public void display() {
    if (size == 0) {
      System.out.println("Queue is empty!");
      return;
    }

    // Print each instructor with its position
    for (int i = 0; i < size; i++) {
      System.out.println((i + 1) + ". " + instructors[i]);
    }
  }

  /**
   * Sorts the queue by last name in descending order using QuickSort.
   * 
   * @param low  The starting index for sorting
   * @param high The ending index for sorting
   * @throws IllegalArgumentException If indices are invalid
   */
  public void quickSortByLastName(int low, int high) {
    // Input validation
    if (low < 0 || high >= size) {
      throw new IllegalArgumentException("Invalid sorting range");
    }

    if (low < high) {
      // Find the partition point
      int pivotIndex = partitionByLastName(low, high);

      // Recursively sort the two partitions
      quickSortByLastName(low, pivotIndex - 1);
      quickSortByLastName(pivotIndex + 1, high);
    }
  }

  /**
   * Helper method for QuickSort by last name.
   * 
   * @param low  The starting index
   * @param high The ending index
   * @return The pivot index
   */
  private int partitionByLastName(int low, int high) {
    // Use the last element as the pivot
    String pivot = instructors[high].getLastName();
    int i = low - 1;

    // Compare each element with the pivot
    for (int j = low; j < high; j++) {
      // For descending order, we check if current element is greater than pivot
      if (instructors[j].getLastName().compareTo(pivot) > 0) {
        i++;
        swapInstructors(i, j);
      }
    }

    // Place the pivot in its correct position
    swapInstructors(i + 1, high);
    return i + 1;
  }

  /**
   * Sorts the queue by number of courses in descending order using QuickSort.
   * 
   * @param low  The starting index for sorting
   * @param high The ending index for sorting
   * @throws IllegalArgumentException If indices are invalid
   */
  public void quickSortByCourses(int low, int high) {
    // Input validation
    if (low < 0 || high >= size) {
      throw new IllegalArgumentException("Invalid sorting range");
    }

    if (low < high) {
      // Find the partition point
      int pivotIndex = partitionByCourses(low, high);

      // Recursively sort the two partitions
      quickSortByCourses(low, pivotIndex - 1);
      quickSortByCourses(pivotIndex + 1, high);
    }
  }

  /**
   * Helper method for QuickSort by number of courses.
   * 
   * @param low  The starting index
   * @param high The ending index
   * @return The pivot index
   */
  private int partitionByCourses(int low, int high) {
    // Use the last element as the pivot
    int pivot = instructors[high].getNumCourses();
    int i = low - 1;

    // Compare each element with the pivot
    for (int j = low; j < high; j++) {
      // For descending order, we check if current element is greater than pivot
      if (instructors[j].getNumCourses() > pivot) {
        i++;
        swapInstructors(i, j);
      }
    }

    // Place the pivot in its correct position
    swapInstructors(i + 1, high);
    return i + 1;
  }
}
