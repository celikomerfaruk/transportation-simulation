
package codes.teaching.testing.student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import containers.BasicContainer;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import ports.Port;

/* TODO: Import the classes you need for testing */

// Tests will be sorted in lexicographical order. Therefore start the names as "test1_TestName" 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//@RunWith(JUnit4.class)
public final class ExamJUnitCases extends ExamJUnit {
	
//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
//	private final PrintStream originalOut = System.out;
//	private final PrintStream originalErr = System.err;
	
	public static String lineSeperator = System.lineSeparator();

	@Rule
	public TestName name = new TestName();
	
	@Test
	public void test1_containerHierarchyTest() {
		String inputForReport = "Container Hierarchy Test";
		String expectedAnswer = "true";
		ExamJUnit.testInitialization(1, name.getMethodName(), inputForReport, expectedAnswer);
		try {
			String answerStudent = "false";
			
			Class<BasicContainer> testClass = BasicContainer.class;
			boolean check1 = false;
			Class superClass = testClass.getSuperclass();
			if(superClass.getSimpleName().equals("Container")) {
				check1 = true;
			}
			
			Class<HeavyContainer> testClass1 = HeavyContainer.class;
			boolean check2 = false;
			Class superClass1 = testClass1.getSuperclass();
			if(superClass1.getSimpleName().equals("Container")) {
				check2 = true;
			}
			
			Class<RefrigeratedContainer> testClass2 = RefrigeratedContainer.class;
			boolean check3 = false;
			Class superClass2 = testClass2.getSuperclass();
			if(superClass2.getSimpleName().equals("HeavyContainer")) {
				check3 = true;
			}
			
			Class<LiquidContainer> testClass3 = LiquidContainer.class;
			boolean check4 = false;
			Class superClass3 = testClass3.getSuperclass();
			if(superClass3.getSimpleName().equals("HeavyContainer")) {
				check4 = true;
			}
			
			if(check1 && check2 && check3 && check4) {
				answerStudent = "true";
			}

			ExamJUnit.testCheck(answerStudent);
		} catch (Exception e) {
			testFailedExecution(e);
		}
	}

	@Test
	public void test2_distanceBetweenTwoPorts() {
		String inputForReport = "Distance between two ports";
		String expectedAnswer = "true";
		ExamJUnit.testInitialization(2, name.getMethodName(), inputForReport, expectedAnswer);
		try {
			String answerStudent = "false";
			Class<Port> testClass = Port.class;
			try {
				Constructor<Port> myConstructor = testClass.getConstructor(new Class[] {int.class, double.class, double.class});
				
				Object[] args = new Object[3];
				args[0] = 1;
				args[1] = 10.0;
				args[2] = 20.0;
				
				Field myField = testClass.getDeclaredField("ID");
				Field myField2 = testClass.getDeclaredField("X");
				Field myField3 = testClass.getDeclaredField("Y");
				
				myField.setAccessible(true);
				myField2.setAccessible(true);
				myField3.setAccessible(true);
				
				Port p1 = myConstructor.newInstance(args);
				
				Object[] args2 = new Object[3];
				args2[0] = 2;
				args2[1] = 16.0;
				args2[2] = 28.0;
				
				Port p2 = myConstructor.newInstance(args2);
				
				Method m = testClass.getDeclaredMethod("getDistance", Port.class);
				
				m.setAccessible(true);
				Object[] args3 = new Object[1];
				args3[0] = p2;
				
				Double dist = (double)m.invoke(p1, args3);
				
				if(dist == 10.0) {
					answerStudent = "true";
				}
				
			} catch (Exception e) {
				answerStudent = "false";
			}

			ExamJUnit.testCheck(answerStudent);
		} catch (Exception e) {
			testFailedExecution(e);
		}
	}
}
