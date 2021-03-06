package com.baselogic.components;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * SimpleComponent
 * 
 * <p>Spring Certification objective: 1.2 Lifecycle</p>
 * 
 * @see <a href="http://springcert.sourceforge.net/core-3/index.html#beans">Objective 1.2 Lifecycle</a>
 *
 * @author Mick Knutson
 * @see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * @see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * @see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * @see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 * 
 * @see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 7 Cookbook Packt</a>
 * @see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 7 Cookbook Amazon</a>
 * 
 * @since 2012
 * 
 */
public class SimpleComponent {
	
	private String message;	

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}


	public static void main(String... args) {

		ApplicationContext springApplicationContext = new ClassPathXmlApplicationContext(
				"/META-INF/spring/simple.xml");

		SimpleComponent simpleComponent = springApplicationContext.getBean("simpleComponent", SimpleComponent.class);

		System.out.println("simpleComponent:getMessage() is "
				+ simpleComponent.getMessage());

		SimpleComponent simpleComponentAlias = springApplicationContext.getBean("simpleComponentAlias", SimpleComponent.class);

		System.out.println("simpleComponentAlias:getMessage() is "
				+ simpleComponentAlias.getMessage());


		// Single constructor args:
		ConstructorInjectionComponent constructorInjectionComponent = 
				springApplicationContext.getBean("constructorInjectionComponent", ConstructorInjectionComponent.class);
	
		System.out.println("constructorInjectionComponent:getMessage() is "
			+ constructorInjectionComponent.getMessage());


		// Multiple constructor args
		MultipleConstructorInjectionComponent multipleConstructorInjectionComponent = springApplicationContext
				.getBean("multipleConstructorInjectionComponent",
						MultipleConstructorInjectionComponent.class);

		System.out.println("multipleConstructorInjectionComponent:getMessage() is "
				+ multipleConstructorInjectionComponent.getMessage());

		
		
		PropertyInjectionComponent propertyInjectionComponent = springApplicationContext
				.getBean("propertyInjectionComponent",
						PropertyInjectionComponent.class);

		System.out.println("propertyInjectionComponent: String: "
				+ propertyInjectionComponent.getMessage() +", instance of String: " 
				+ (propertyInjectionComponent.getMessage() instanceof String));

		System.out.println("propertyInjectionComponent: int: "
				+ propertyInjectionComponent.getSomeInt() +", instance of Integer: " 
				+ (propertyInjectionComponent.getSomeInt() instanceof Integer));

		System.out.println("propertyInjectionComponent: double: "
				+ propertyInjectionComponent.getSomeDouble() +", instance of Double: " 
				+ (propertyInjectionComponent.getSomeDouble() instanceof Double));


		
		/*ApplicationContext springApplicationContext2 = new ClassPathXmlApplicationContext(
				"/META-INF/spring/simple2.xml");

		ExampleService exampleService2 = (ExampleService) springApplicationContext2
				.getBean("exampleService");

		System.out.println("exampleService2:getMessage() is "
				+ exampleService2.getMessage());

		ExampleService altExampleService2 = (ExampleService) springApplicationContext2
				.getBean("altExampleService");

		System.out.println("altExampleService2:getMessage() is "
				+ altExampleService2.getMessage());*/
	}

}
