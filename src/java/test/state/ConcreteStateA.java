package test.state;

/**
 * 具体状态类
 * @author Administrator
 */
public class ConcreteStateA implements State{

	@Override
	public void hander(String paramer) {
		System.out.println("ConcreteStateA==="+paramer);
	}

}
