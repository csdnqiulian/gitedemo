package test.lession1;
//对象适配器模式
public class Adapter implements Job{
	Person person;  
	public Adapter(Person person) {  
        this.person = person;  
    }  
	
	@Override
	public void speakFrench() {
		
		System.out.println(person.getName()+"法语");
	}

	@Override
	public void speakJapanese() {
		person.speakJapanese();
		
	}

	@Override
	public void speakEnglish() {
		person.speakEnglish();
	}
}
