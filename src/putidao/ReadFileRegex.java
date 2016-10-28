package putidao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 从本地读取文件，将该文件去数字，小括号，子母
 * 
 * @author sun
 * 
 */
public class ReadFileRegex {
	String path = "G:\\jiejie\\workspace\\putidao\\putidao.txt";
	String[] regex = { "\\d{1,10}", "p", "\\.", "\\(\\)", " " };

	public String regexStr() {
		String result = readTxtFile(path);
		// String result = "p. 116【摄决择中所说此等。】";
		for (String reg : regex) {
			result = result.replaceAll(reg, "");
		}
		return result;
	}

	/**
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public String readTxtFile(String filePath) {
		String result = "";
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					result = result + lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return result;

	}

	public void write2File() {

	}
}
