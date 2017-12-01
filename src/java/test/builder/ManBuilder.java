package test.builder;

public class ManBuilder implements PersonBuilder{

	private Person person = null;
	public ManBuilder(){
		person = new  Man();
	}
	
	@Override
	public void buildHead() {
		person.setHead("头");
	}

	@Override
	public void buildBody() {
		person.setBody("身体");
		
	}

	@Override
	public void buildFoot() {
		person.setFoot("脚");
		
	}

	@Override
	public Person buildPerson() {
		return person;
	}

}
