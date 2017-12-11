package test.builder;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Dirictor dirictor = new Dirictor();
		Person person = dirictor.constructPerson(new ManBuilder());
		System.out.println(person.getHead());
		System.out.println(person.getBody());
		System.out.println(person.getFoot());
	}

}
