
/**
 * JUnit Test class for InstructorQueue.
 * This class contains tests for the queue functionality and sorting methods.
 */
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class InstructorQueueTest {
  private InstructorQueue queue;

  /**
   * Sets up the test environment before each test.
   */
  @Before
  public void setUp() {
    queue = new InstructorQueue(5);
  }

  /**
   * Tests that queue creation works properly.
   */
  @Test
  public void testQueueCreation() {
    assertEquals("New queue should be empty", 0, queue.size());
  }

  /**
   * Tests that queue capacity validation works.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCapacity() {
    InstructorQueue invalidQueue = new InstructorQueue(0);
  }

  /**
   * Tests the enqueue operation.
   */
  @Test
  public void testEnqueue() {
    Instructor instructor = new Instructor("John", "Doe", 3);
    queue.enqueue(instructor);
    assertEquals("Queue size should be 1 after enqueue", 1, queue.size());
    assertEquals("First instructor should be John Doe", instructor, queue.getInstructorAt(0));
  }

  /**
   * Tests that null validation works when enqueueing.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEnqueueNull() {
    queue.enqueue(null);
  }

  /**
   * Tests the dequeue operation.
   */
  @Test
  public void testDequeue() {
    Instructor instructor1 = new Instructor("John", "Doe", 3);
    Instructor instructor2 = new Instructor("Jane", "Smith", 4);
    queue.enqueue(instructor1);
    queue.enqueue(instructor2);

    Instructor removed = queue.dequeue();
    assertEquals("Dequeued instructor should be John Doe", instructor1, removed);
    assertEquals("Queue size should be 1 after dequeue", 1, queue.size());
    assertEquals("First instructor should now be Jane Smith", instructor2, queue.getInstructorAt(0));
  }

  /**
   * Tests that error handling works when dequeueing from an empty queue.
   */
  @Test(expected = IllegalStateException.class)
  public void testDequeueEmpty() {
    queue.dequeue();
  }

  /**
   * Tests sorting by last name.
   */
  @Test
  public void testSortByLastName() {
    Instructor instructor1 = new Instructor("John", "Doe", 3);
    Instructor instructor2 = new Instructor("Jane", "Smith", 4);
    Instructor instructor3 = new Instructor("Bob", "Adams", 2);

    queue.enqueue(instructor1);
    queue.enqueue(instructor2);
    queue.enqueue(instructor3);

    queue.quickSortByLastName(0, queue.size() - 1);

    // Descending order: Smith, Doe, Adams
    assertEquals("First should be Smith", "Smith", queue.getInstructorAt(0).getLastName());
    assertEquals("Second should be Doe", "Doe", queue.getInstructorAt(1).getLastName());
    assertEquals("Third should be Adams", "Adams", queue.getInstructorAt(2).getLastName());
  }

  /**
   * Tests sorting by number of courses.
   */
  @Test
  public void testSortByCourses() {
    Instructor instructor1 = new Instructor("John", "Doe", 3);
    Instructor instructor2 = new Instructor("Jane", "Smith", 5);
    Instructor instructor3 = new Instructor("Bob", "Adams", 2);

    queue.enqueue(instructor1);
    queue.enqueue(instructor2);
    queue.enqueue(instructor3);

    queue.quickSortByCourses(0, queue.size() - 1);

    // Descending order: 5, 3, 2
    assertEquals("First should have 5 courses", 5, queue.getInstructorAt(0).getNumCourses());
    assertEquals("Second should have 3 courses", 3, queue.getInstructorAt(1).getNumCourses());
    assertEquals("Third should have 2 courses", 2, queue.getInstructorAt(2).getNumCourses());
  }

  /**
   * Tests the Instructor constructor validation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInstructorNullName() {
    Instructor instructor = new Instructor(null, "Smith", 3);
  }

  /**
   * Tests the Instructor constructor validation for empty names.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInstructorEmptyName() {
    Instructor instructor = new Instructor("John", "", 3);
  }

  /**
   * Tests the Instructor constructor validation for negative courses.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInstructorNegativeCourses() {
    Instructor instructor = new Instructor("John", "Doe", -1);
  }
}