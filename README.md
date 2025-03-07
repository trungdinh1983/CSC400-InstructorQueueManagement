# Instructor Queue Management System

## Overview

This Java application manages a queue of instructors and provides functionality to sort them by last name or by the number of courses they teach. It's designed to be beginner-friendly with comprehensive documentation, robust error handling, and thorough input validation.

## Features

- Create and manage a queue of instructor records
- Add instructors with first name, last name, and number of courses
- Sort instructors by last name in descending order
- Sort instructors by number of courses in descending order
- Comprehensive input validation
- Detailed error handling
- JUnit test suite

## Classes

### InstructorQueueManagement

The main class that runs the application. It handles user interaction, input processing, and displays results.

### Instructor

Represents an individual instructor with:

- First name
- Last name
- Number of courses teaching

### InstructorQueue

A custom queue implementation for storing Instructor objects with:

- Basic queue operations (enqueue, dequeue)
- Ability to access elements by index
- QuickSort implementation for sorting by different criteria

### InstructorQueueTest

A comprehensive JUnit test class that validates all functionality.

## How to Run

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- JUnit library (for running tests)

### Running the Application

1. Compile the Java files:

   ```
   javac InstructorQueueManagement.java
   ```

2. Run the application:

   ```
   java InstructorQueueManagement
   ```

3. Follow the prompts to enter information for 5 instructors.

### Running the Tests

1. Compile the test file along with the main classes:

   ```
   javac -cp .:junit-4.XX.jar InstructorQueueTest.java
   ```

2. Run the tests:
   ```
   java -cp .:junit-4.XX.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore InstructorQueueTest
   ```
   (Replace XX with your JUnit version)

## Usage Example

```
Please enter information for 5 instructors.

Instructor #1
Enter first name: John
Enter last name: Smith
Enter number of courses teaching: 4

Instructor #2
Enter first name: Jane
Enter last name: Doe
Enter number of courses teaching: 2

Instructor #3
Enter first name: Robert
Enter last name: Williams
Enter number of courses teaching: 6

Instructor #4
Enter first name: Emily
Enter last name: Brown
Enter number of courses teaching: 3

Instructor #5
Enter first name: Michael
Enter last name: Johnson
Enter number of courses teaching: 5

Original Queue Contents:
1. John Smith, Courses: 4
2. Jane Doe, Courses: 2
3. Robert Williams, Courses: 6
4. Emily Brown, Courses: 3
5. Michael Johnson, Courses: 5

Queue Sorted by Last Name (Descending):
1. Robert Williams, Courses: 6
2. John Smith, Courses: 4
3. Michael Johnson, Courses: 5
4. Jane Doe, Courses: 2
5. Emily Brown, Courses: 3

Queue Sorted by Number of Courses (Descending):
1. Robert Williams, Courses: 6
2. Michael Johnson, Courses: 5
3. John Smith, Courses: 4
4. Emily Brown, Courses: 3
5. Jane Doe, Courses: 2
```

## Input Validation

The program validates all inputs:

- Names cannot be null or empty
- Number of courses must be a non-negative integer
- All operations check for valid indexes and states

## Error Handling

The program includes robust error handling:

- Meaningful error messages for invalid inputs
- Opportunity to retry when input is incorrect
- Protection against common errors like entering text when a number is expected

## Implementation Details

### Sorting Algorithm

The program uses QuickSort to sort the queue:

1. **quickSortByLastName**: Sorts instructors by last name in descending order
2. **quickSortByCourses**: Sorts instructors by number of courses in descending order

#### QuickSort Process:

1. Select a 'pivot' element from the array
2. Partition the array around the pivot (elements greater than pivot go to one side, smaller go to another)
3. Recursively apply the above steps to the sub-arrays

## Testing

The JUnit test suite validates:

- Queue creation and basic operations
- Input validation
- Sorting functionality
- Error handling
- Edge cases

## Extending the Application

You can extend this application by:

1. Adding more sorting criteria (e.g., by first name)
2. Implementing search functionality
3. Adding persistence (saving to/loading from files)
4. Creating a graphical user interface

## License

This project is available for educational purposes.
