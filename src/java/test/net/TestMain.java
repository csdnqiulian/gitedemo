package test.net;

public class TestMain {
	public static void main(String[] args) {
		//整个系统只有一个工程，可以考虑启动系统创建一个
		//如果每次创建 一个工厂的话，那他这个享元模式就没有意义
		ReportManagerFactory factory = new ReportManagerFactory();
		IReportManager rm = factory.getEmpReportManager("A");
		System.out.println(rm.createrReport());
		
		IReportManager rm1 = factory.getEmpReportManager("B");
		System.out.println(rm1.createrReport());
	}
}
