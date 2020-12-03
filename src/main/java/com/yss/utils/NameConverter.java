package com.yss.utils;

/**
 * 命名转换
 * @author sonny
 *
 */
public class NameConverter {
	public static String toJavaCase(String s) {
		if ((s == null) || (s.trim().length() == 0)) {
            return s;
        }
		StringBuffer sb = new StringBuffer();
		String[] array = s.split("_");
		boolean firstTime = true;
		for (String e : array) {
			if (e.length() == 0) {
                continue;
            }
			if (e.length() == 1) {
                sb.append((!(firstTime)) ? e.toUpperCase() : e);
            } else {
                sb.append((!(firstTime)) ? e.substring(0, 1).toUpperCase() + e.substring(1) : e);
            }
			firstTime = false;
		}
		return sb.toString();
	}

	@SuppressWarnings("unused")
	public static String toDbCase(String s) {
		if ((s == null) || (s.trim().length() == 0)) {
            return s;
        }
		char[] chars = s.toCharArray();

		boolean firstTime = true;
		StringBuffer sb = new StringBuffer();
		for (char c : chars) {
			if ((c >= 'A') && (c <= 'Z')) {
				char c1 = (char) (c + ' ');
				sb.append("_" + c1);
			} else {
				sb.append(c);
			}
			firstTime = false;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(toDbCase("sssFss".toLowerCase()));
	}
}