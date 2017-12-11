package test.state;

public class ConcreteStateB implements State{
	
	@Override
	public void hander(String paramer) {
		System.out.println("ConcreteStateB==="+paramer);
	}
}
