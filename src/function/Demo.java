package function;

import java.io.IOException;
import java.net.MalformedURLException;

public class Demo {
public static void main(String[] args) throws MalformedURLException, IOException {
	
	//EXp1:
//	ArrayList<Integer> number = new ArrayList<Integer>();
//	number.add(1);
//	number.add(2);
//	int a = 0, b =2;
//	for (int i = 0; i < number.size(); i ++) {
//		if (b == number.get(i)) {
//			a ++;
//		}
//	}
//	if (a == 0) {
//		System.out.println("d");
//	} else {
//		System.out.println("e");
//	}
	
	//Exp2: Parsing images
//	List<String> list = new ArrayList<String>();
//	Document document;
//	String url = "http://www.it-ebooks.info/book/1463484288/";
//	document = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
//	Elements tags = document.getElementsByAttribute("itemprop");
//	System.out.println(tags.get(1).absUrl("src"));
//	
	
	//Exp3
	String localAddress = "31 Tràng Thi, Hàng Trống, Hoàn Kiếm, Hà Nội".replace(" ", "+");
	System.out.println(localAddress);
	
}
}
