package test.adapter;
//类适配器模式
public class Adapter extends Person implements Job{
	@Override
	public void speakFrench() {
		System.out.println("法语");
	}
}
