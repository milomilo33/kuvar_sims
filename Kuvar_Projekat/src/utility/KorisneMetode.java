package utility;

public class KorisneMetode {
	public static boolean isNameValid(String name) {
		if (name == null || "".equals(name))
			return false;
		if ("".equals(name.trim()))
			return false;
		for (char c : name.toCharArray())
			if (!Character.isLetter(c)) {
				if (c == ' ')
					continue;
				return false;
			}
		return true;
	}
	
	public static boolean isNotAnEmptyOrNullString(String str) {
		if (str == null || "".equals(str))
			return false;
		if ("".equals(str.trim()))
			return false;
		return true;
	}
}