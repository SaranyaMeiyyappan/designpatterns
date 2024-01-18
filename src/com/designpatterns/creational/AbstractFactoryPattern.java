package com.designpatterns.creational;

/**
* Modified Factory Pattern example
**/
public class AbstractFactoryPattern {

	public static void main(String[] args) {
		AbstractBeanFactory studentFactory = new StudentBeanFactory();
		AbstractBeanFactory employeeFactory = new EmployeeBeanFactory();
		
		try {
			studentFactory.getStudent("bachelorStudent").getStudentDetails();
			employeeFactory.getEmployee("contractEmployee").getEmployeeDetails();
		} catch (Exception e) {
			System.out.println("Exception occured while creating bean:"+ e.getMessage());
		}

	}

}

interface Student {
	public void getStudentDetails();
}

interface Employee {
	public void getEmployeeDetails();
}

class BachelorStudent implements Student {

	@Override
	public void getStudentDetails() {
		System.out.println("Created Bachelor Student");
	}
	
}

class MasterStudent implements Student {

	@Override
	public void getStudentDetails() {
		System.out.println("Created Master Student");
	}
	
}

class FullTimeEmployee implements Employee {

	@Override
	public void getEmployeeDetails() {
		System.out.println("Created Full Time Employee");		
	}
	
}

class ContractEmployee implements Employee {

	@Override
	public void getEmployeeDetails() {
		System.out.println("Created Contract Employee");
	}
	
}
abstract class AbstractBeanFactory {
	public abstract Student getStudent(String beanType) throws Exception;
	public abstract Employee getEmployee(String beanType) throws Exception;
}

class StudentBeanFactory extends AbstractBeanFactory {

	@Override
	public Student getStudent(String beanType) throws Exception {
		if(beanType.equalsIgnoreCase("bachelorStudent"))
		{
			return new BachelorStudent();
		}
		else
			if(beanType.equalsIgnoreCase("masterStudent"))
			{
				return new MasterStudent();
			}
			else{
				throw new Exception("No student available for this type --> "+beanType);
			}
	}

	@Override
	public Employee getEmployee(String beanType) {
		return null;
	}
	
}

class EmployeeBeanFactory extends AbstractBeanFactory {

	@Override
	public Student getStudent(String beanType) throws Exception {
		return null;
	}

	@Override
	public Employee getEmployee(String beanType) throws Exception {
		if(beanType.equalsIgnoreCase("fullTimeEmployee"))
		{
			return new FullTimeEmployee();		}
		else
			if(beanType.equalsIgnoreCase("contractEmployee"))
			{
				return new ContractEmployee();
			}
			else{
				throw new Exception("No employee available for this type --> "+beanType);
			}
	}
	
}
