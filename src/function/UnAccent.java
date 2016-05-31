package function;

import java.text.Normalizer;
import java.util.regex.Pattern;

/*
 * Chuyen tieng viet co dau sang khong dau
 */
public class UnAccent {

	public UnAccent() {
		
	}
	
	public String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
	}
}
