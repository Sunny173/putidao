package putidao;

public class Test {
	/**
	 */
	public static void main(String[] args) {

//		 ReadFileRegex regex = new ReadFileRegex();
//		 System.out.println(regex.regexStr());
		test();
	}

	private static void test() {
		// // 将文件以。号分割前后增加字符
		FormatStr format = new FormatStr();
		String result = format.formatbyHtml();
		System.out.println(result);
	}

}
