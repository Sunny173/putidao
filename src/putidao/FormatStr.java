package putidao;

/**
 * 将文件以。号分割，并增加每行头尾信息； 每行头尾信息加完后，增加整个文件的头尾信息。
 * 
 * @author sun
 * 
 */
public class FormatStr {
	String lineStar = "lllllllllllllllllllllll";
	String lineEnd = "llllllllllllllllllllllleeeeeeeeeeeeeeeeee";
	String head = "hhhhhhhhhh";
	String footer = "ffffffff";
	String split = "。";

	ReadFileRegex regex;

	public FormatStr(ReadFileRegex regex) {
		// 当前目录的上级目录路径
		this.regex = regex;
		String parentPath = getClass().getResource("../").getFile().toString();
		parentPath = parentPath.replaceAll("bin/", "format/");
		// G:\jiejie\workspace\putidao\format
		// lineStar = regex
		// .readTxtFile("G:\\jiejie\\workspace\\putidao\\format\\lineStar.txt");
		lineStar = regex.readTxtFile(parentPath + "\\lineStar.txt");
		lineEnd = regex.readTxtFile(parentPath + "\\lineEnd.txt");
		head = regex.readTxtFile(parentPath + "\\head.txt");
		footer = regex.readTxtFile(parentPath + "\\footer.txt");
	}

	public FormatStr() {
		regex = new ReadFileRegex();
		String parentPath = getClass().getResource("../").getFile().toString();
		parentPath = parentPath.replaceAll("bin/", "format/");
		lineStar = regex.readTxtFile(parentPath + "\\lineStar.txt");
		lineEnd = regex.readTxtFile(parentPath + "\\lineEnd.txt");
		head = regex.readTxtFile(parentPath + "\\head.txt");
		footer = regex.readTxtFile(parentPath + "\\footer.txt");
	}

	public String formatbyHtml() {
		return formatbyHtml(regex.regexStr());
	}

	public String formatbyHtml(String str) {
		String[] strArray = new ReadFileRegex().regexStr().split(split);
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		for (String _str : strArray) {
			// 原文字符开始
			int judeR = judeSrcContent(_str);
			if (judeR == 1 || judeR == 2 || judeR == 3) {
				sb2.append(_str).append(split);
			}
			if (judeR == 1) {
				continue;
			}
			if (sb2.length() != 0) {
				_str = sb2.toString();
				sb2 = new StringBuilder();
				sb.append(lineStar).append(_str).append(lineEnd);
			} else {
				sb.append(lineStar).append(_str).append(split).append(lineEnd);
			}
		}
		sb.insert(0, head);
		sb.append(footer);
		return sb.toString();
	}

	private String srcStar = "【";
	private String srcEnd = "】";
	private String com = "";

	/**
	 * 判断_str是否是原文字符:
	 * 
	 * @param _str
	 * @return 1标识原文字符；
	 * @return 2标识是原文的最后一个字符;
	 * @return 3标识当前字符即包含开头也包含结尾；
	 * @return 0标识不是原文字符；
	 */
	private int judeSrcContent(String _str) {

		if (!com.equals("")) {
			// 读取到原文最后一个字符
			boolean douContain = _str.contains(srcStar)
					&& _str.contains(srcEnd) ? _str.endsWith(srcEnd) : _str
					.contains(srcEnd);
			if (douContain) {
				com = "";
				return 2;
			} else {
				return 1;
			}
		}
		if (_str.contains(srcStar) && _str.contains(srcEnd)) {
			com = "";
			return 3;
		} else if (_str.contains(srcStar)) {
			com = srcStar;
			return 1;
		}
		return 0;
	}

	public void writeback() {
		regex.write2File();
	}

}
