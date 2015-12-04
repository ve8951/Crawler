import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class CommentReader {

	public void readComments() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("C://Work/November2012/Homevito/src/com/orvito/homevito/presentors/ACTAbout.java"));
		try {
			Boolean commentFound = false;
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				//				sb.append(line);
				//				sb.append(System.lineSeparator());
				line = br.readLine();
				if (line != null) {

					if (line.indexOf("//") != -1 ) {
						//						System.out.print();
						sb.append(line.substring(line.indexOf("//"))+"\n\n");
					}else if (line.indexOf("/*") != -1) {
						commentFound = true;
					}else if (line.indexOf("*/") != -1){
						commentFound = false;
					}
					if (commentFound == true) {
						sb.append(line);
						sb.append(System.lineSeparator());
					}
				}

			}
			String everything = sb.toString();
			System.out.print(everything);

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}
	}

}
