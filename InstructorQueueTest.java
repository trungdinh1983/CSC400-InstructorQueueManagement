import org.junit.Test;
import org.junit.Before;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import static org.junit.Assert.*;

/**
 * Test class for InstructorQueue implementation
 * With concise output format
 */
public class InstructorQueueTest {
  private InstructorQueue queue;

  @Before
  public void setUp() {
    queue = new InstructorQueue(5);
  }

  @Test
  public void testQueueCreation() {
    assertEquals("New queue should be empty", 0, queue.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCapacity() {
    InstructorQueue invalidQueue = new InstructorQueue(0);
  }

  @Test
  public void testEnqueue() {
    Instructor instructor = new Instructor("John", "Doe", 3);
    queue.enqueue(instructor);
    assertEquals("Queue size should be 1 after adding one instructor", 1, queue.size());
    assertEquals("Instructor should be at index 0", instructor, queue.getInstructorAt(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEnqueueNull() {
    queue.enqueue(null);
  }

  @Test
  public void testDequeue() {
    Instructor instructor1 = new Instructor("John", "Doe", 3);
    Instructor instructor2 = new Instructor("Jane", "Smith", 4);
    queue.enqueue(instructor1);
    queue.enqueue(instructor2);

    Instructor removed = queue.dequeue();

    assertEquals("Dequeued instructor should be the first one added", instructor1, removed);
    assertEquals("Queue size should be 1 after dequeuing", 1, queue.size());
    assertEquals("Second instructor should now be at index 0", instructor2, queue.getInstructorAt(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testDequeueEmpty() {
    queue.dequeue();
  }

  @Test
  public void testSortByLastName() {
    Instructor instructor1 = new Instructor("John", "Doe", 3);
    Instructor instructor2 = new Instructor("Jane", "Smith", 5);
    Instructor instructor3 = new Instructor("Bob", "Adams", 2);

    queue.enqueue(instructor1);
    queue.enqueue(instructor2);
    queue.enqueue(instructor3);

    queue.quickSortByLastName(0, queue.size() - 1);

    assertEquals("First should be Smith", "Smith", queue.getInstructorAt(0).getLastName());
    assertEquals("Second should be Doe", "Doe", queue.getInstructorAt(1).getLastName());
    assertEquals("Third should be Adams", "Adams", queue.getInstructorAt(2).getLastName());
  }

  @Test
  public void testSortByCourses() {
    Instructor instructor1 = new Instructor("John", "Doe", 3);
    Instructor instructor2 = new Instructor("Jane", "Smith", 5);
    Instructor instructor3 = new Instructor("Bob", "Adams", 2);

    queue.enqueue(instructor1);
    queue.enqueue(instructor2);
    queue.enqueue(instructor3);

    queue.quickSortByCourses(0, queue.size() - 1);

    assertEquals("First should have 5 courses", 5, queue.getInstructorAt(0).getNumCourses());
    assertEquals("Second should have 3 courses", 3, queue.getInstructorAt(1).getNumCourses());
    assertEquals("Third should have 2 courses", 2, queue.getInstructorAt(2).getNumCourses());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInstructorNullName() {
    Instructor instructor = new Instructor(null, "Smith", 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInstructorEmptyName() {
    Instructor instructor = new Instructor("John", "", 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInstructorNegativeCourses() {
    Instructor instructor = new Instructor("John", "Doe", -1);
  }

  /**
   * Main method to run the tests with a concise summary
   */
  public static void main(String[] args) {
    System.out.println("\nINSTRUCTOR QUEUE TEST RESULTS");

    JUnitCore junit = new JUnitCore();
    junit.addListener(new ConciseListener());
    Result result = junit.run(InstructorQueueTest.class);

    System.out.println("\nSUMMARY");
    System.out.println("Tests run: " + result.getRunCount() +
        " | Failed: " + result.getFailureCount() +
        " | Time: " + result.getRunTime() + "ms");

    if (result.wasSuccessful()) {
      System.out.println("✅ ALL TESTS PASSED");
    } else {
      System.out.println("❌ TESTS FAILED:");
      for (Failure failure : result.getFailures()) {
        System.out.println("- " + failure.getTestHeader() + ": " + failure.getMessage());
      }
    }
  }

  /**
   * Concise listener that shows just test names and status
   */
  static class ConciseListener extends RunListener {
    @Override
    public void testStarted(Description description) {
      System.out.print(description.getMethodName() + "... ");
    }

    @Override
    public void testFinished(Description description) {
      System.out.println("✓");
    }

    @Override
    public void testFailure(Failure failure) {
      System.out.println("✗");
    }
  }
}