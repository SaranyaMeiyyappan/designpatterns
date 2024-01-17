package com.designpatterns.creational;

/**
 * A Factory in general, is a place where something is created. The same applies here in programming too.
 * Thus it is a creational pattern.
 * 
 * It creates objects, based on some input and hides the object creation logic. We give a input parameter 
 * and the factory responds with the corresponding object type.
 * 
 * The newly created object is exposed as a common interface. Since, the design is like the factory produces different 
 * type of objects based on a parameter, we are sure that the return type of the factory method will keeping varying with
 * the input parameter.
 * 
 * In java we cannot return multiple types. So we are in need of a common type.
 * 
 * To achieve that, we create a base type(as an interface) and concrete implementations on the interface(classes) 
 * 
 */
public class FactoryPattern {

	public static void main(String[] args) {
		BeanFactory factory = new BeanFactory();
		
		try {
			factory.getBean("StudentBean").createBean();
			factory.getBean("EmployeeBean").createBean();
			factory.getBean("CountryBean").createBean();
			factory.getBean("NonExistingBean").createBean();
		} catch (Exception e) {
			System.out.println("Exception occured while creating bean:"+ e.getMessage());
		}
	}

}

/**
 * Common type - For all factory instances
 */
interface Bean {
	public Bean createBean();
}

/**
 *	A StudentBean returns a StudentBean() instance and for it to be compatible with the factory method, we implement
 *  the Common type and its method
 */
class StudentBean implements Bean {

	public StudentBean() {};
	
	public StudentBean(String name, int age, String course) {
		System.out.println("Creating student bean "+name+" -- "+age+" -- "+course);
	}
	
	@Override
	public Bean createBean() {
		return new StudentBean("Xyz", 20, "MBA");
	}
	
}

/**
 *	A EmployeeBean returns a EmployeeBean() instance and for it to be compatible with the factory method, we implement
 *  the Common type and its method
 */
class EmployeeBean implements Bean {
	public EmployeeBean() {};
	
	public EmployeeBean(String name, String empId, String company, String dept) 
	{
		System.out.println("EmployeeBean: "+name+"--"+empId+"--"+company+"--"+dept);
	}
	
	@Override
	public Bean createBean() {
		return new EmployeeBean("Raghu","Emp21212", "ABC-Company", "Food & Beverages");
	}
}

/**
 *	A CountryBean returns a CountryBean() instance and for it to be compatible with the factory method, we implement
 *  the Common type and its method
 */
class CountryBean implements Bean
{
	public CountryBean(){}
	
	public CountryBean(String name, String capital) 
	{
		System.out.println("CountryBean: "+name+"--"+capital);
	}

	@Override
	public Bean createBean() {
		return new CountryBean("Netherlands", "Amsterdam");
	}
}

/**
 * This is the factory which is responsible for taking an input parameter and returning appropriate Bean of the requested type
 */
class BeanFactory{
	public Bean getBean(String beanType) throws Exception {
		Bean bean = null;
		if("studentBean".equalsIgnoreCase(beanType)) {
			bean = new StudentBean();
		}else
			if("EmployeeBean".equalsIgnoreCase(beanType))
			{
				bean = new EmployeeBean();
			}
			else
				if("CountryBean".equalsIgnoreCase(beanType))
				{
					bean = new CountryBean();
				}
				else
				{
					throw new Exception("No Bean Available in factory for type --> "+beanType);
				}
		return bean;
	}
}
