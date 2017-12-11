package test.lession1;

public class TextMain {
	public static void main(String[] args) {
		Person person = new Person();
		//类适配器  
		Adapter adapter = new Adapter(person);
		adapter.person.setName("中国");
		adapter.person.setSex("男");
		adapter.speakEnglish();
		adapter.speakFrench();
		adapter.speakJapanese();
	}
}
