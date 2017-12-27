package test.lession1;

import org.apache.commons.lang3.StringEscapeUtils;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "&amp;lt;p&amp;gt;斯蒂芬撒旦发 斯蒂芬撒旦发射 斯蒂芬斯蒂芬&amp;lt;/p&amp;gt;";
		str = StringEscapeUtils.escapeHtml4(str);
		System.out.println(str);
	}

}
