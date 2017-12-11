package test.net;

public class EmployeeReportManager implements IReportManager{
	protected String tid = null;
 
	public EmployeeReportManager(String tid){
		this.tid = tid;
	}
	
	@Override
	public String createrReport() {
		return "this is 员工报表";
	}
	
	
}
