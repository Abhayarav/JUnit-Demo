package com;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;


//To restrict the class to execute only once and create a single instance which will be shared by all test methods, make it Pre_Class
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {
	static Calculator c; // Object creation should be static because every test method inside test class creating separate instance of the test class. so to make the object creation only once, we have made it static.
	
	boolean flag = false;
	
	@BeforeAll
	public static void creatCalObj()
	{
	 c = new Calculator();
	System.out.println("Before All method called");
	}
	
	@BeforeEach
	public void beforeEach()
	{
	
	System.out.println("Before Each called");
	}
	
	@AfterEach
	public void afterEach()
	{
	
	System.out.println("After Each called");
	System.out.println("----------------------");
	}
	
	@AfterAll
	public static void afterAll()
	{
	System.out.println("Thanks for testing!");
	}
	
	
	@Test
	@Tag("Math") //to group testcases for running separatly. for running a tag based method, choose run configuration and then create a configuration in JUnit and Include tags as per requirement.
	@DisplayName("Testing for Addition")
	public void testAdd()
	{
		System.out.println("Tested Add");
		// To execute multiple test steps inside a testcase if some test steps failed, other should execute, we can use assertAll() which will have all test steps in lambda expressions.
		assertAll(
		()->assertEquals(40, c.add(10, 30)),
		()->assertEquals(80, c.add(50, 30)),
		()->assertEquals(-10, c.add(10, -20)),
		()->assertEquals(50, c.add(20, 30)),
		()->assertEquals(-10, c.add(-40, 30)),
		()->assertEquals(60, c.add(10, 50))
				
				);
		
	}
	
	@Test
	@Tag("Math")
	//@Disabled
	public void testSub()
	{
		System.out.println("Tested Sub");
		int actual = c.sub(30, 10);
		assertEquals(20, actual);
	}
	
	@Test
	@Tag("Math")
	public void testMul()
	{
		System.out.println("Tested Multiply");
		int actual = c.mul(3, 7);
		assumeTrue(flag); //similar to break for this function,works as per flag value;
		assertEquals(21, actual, "This is a message, showing if there is test error");
	}
	
	@Test
	@Tag("Math")
	public void testDiv()
	{
		System.out.println("Tested Division");
		int actual = c.div(8, 2);
		assertEquals(4, actual);
		assertThrows(ArithmeticException.class, ()->c.div(5, 0));
		
	}
	
	@Test
	@Tags(value = {@Tag("Demo"), @Tag("Math")})  // Adding multiple tags to this method
	@EnabledOnOs(OS.WINDOWS)
	void windowsMethod()
	{
		System.out.println("For Windows OS only");
	}
	
	@Test
	@EnabledOnOs(OS.SOLARIS)
	void solarisMethod()
	{
		System.out.println("For Solaris OS only");
	}
	
	
	@Test
	//@EnabledOnJre(value = JRE.JAVA_8)
	@EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_15)
	void testJava()
	{
		System.out.println("Executed as per the Java Version");
	}

}
