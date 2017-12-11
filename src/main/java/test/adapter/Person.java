package test.adapter;

public class Person {
	private String name;
	private String sex;


	public void speakJapanese(){
		System.out.println("日语");
	}
	
	public void speakEnglish(){
		System.out.println("英语");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
