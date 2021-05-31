package codes.teaching.testing.student;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/* TODO: Import the classes you need for testing */

// Tests will be sorted in lexicographical order. Therefore start the names as "test1_TestName" 
 
public class ExamJUnit {

//	private static final long testDuration = 2000;
//   
// 	private static final double DOUBLE_TOLERANCE = 0.001; // for floating-point
//															// comparison, if
//															// necessary.
  
	void tcFail(String msg) {
		System.err.println(msg);
		fail(msg);
	}

	void tcSuccess(String msg) {
		System.out.println(msg);
	}

 
	/*
	 * TEST SCENARIOS END HERE
	 */

	public String answerStudent;
	static String description;
	static String answerCorrect;
	static String methodName;
	StringBuilder stringBuilder = new StringBuilder();
	static final String REPORT_DESCRIPTION = "\n\tDESCRIPTION:\t";

	static final void testInitialization(int scenarioNo, String methodName, String description, String answerCorrect) {
		ExamJUnit.methodName = methodName;
		ExamJUnit.description = methodName + REPORT_DESCRIPTION + description;
		ExamJUnit.answerCorrect = answerCorrect;
	}

	static final void testCheck(String answerStudent) {
		try {
			assertTrue(answerCorrect.equals(answerStudent));
			System.out.println(description+"\tSuccess: Your answer:\t" + answerStudent);
		} catch (AssertionError e) {
			/* runs but produces wrong answer */
			System.err.println(description+"\tFAILED :");
			System.err.println("\tYour answer:\t" + System.lineSeparator() + answerStudent);
			System.err.println("\tCorrect answer:\t" + System.lineSeparator() + ExamJUnit.answerCorrect);
			fail("Your answer does not match the expected answer");
			
		}
	}

	/*
	 * TEST SCENARIOS END HERE
	 */

	static final void testFailedExecution(Throwable e) {
		String explanation = "Test failed with exception " + e.getClass().getCanonicalName();
		System.err.println(description+"\t failed to complete: " + explanation);
		fail(explanation);
	}

	public static void main(String[] args) {
		System.out.println("ExamJUnit Main");
		Result result = JUnitCore.runClasses(ExamJUnitCases.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
	}

}
