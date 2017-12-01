package test.adapter;

public class TextMain {
	public static void main(String[] args) {
		//类适配器  
		Adapter adapter = new Adapter();
		adapter.setName("ssxxx");
		adapter.setSex("男");
		adapter.speakEnglish();
		adapter.speakFrench();
		adapter.speakJapanese();
	}
}
