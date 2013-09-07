package io.github.fourohfour.wolvesvspigs;

public class Resources {
	public static String RemoveLastChar(String str) {

		  if (str.length() > 0 && str.charAt(str.length()-1)=='x') {
		    str = str.substring(0, str.length()-1);
		  }
		  return str;
		}

}
