package test.net;
//实际对象
public class FinacialReportManager implements IReportManager{
	protected String tid = null;
	public FinacialReportManager(String tid){
		this.tid = tid;
	}
	@Override
	public String createrReport() {
		
		return "this is 财务报表";
	}
	
}
