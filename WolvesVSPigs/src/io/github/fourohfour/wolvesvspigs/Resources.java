package io.github.fourohfour.wolvesvspigs;

public class Resources {
	public static String RemoveLastChar(String str) {

		  if (str.length() > 0 && str.charAt(str.length()-1)=='x') {
		    str = str.substring(0, str.length()-1);
		  }
		  return str;
		}
	public static Boolean StrToBoolean(String str) {
		if (str.equalsIgnoreCase("True")){
			Boolean val = true;
			return val;
		} else if (str.equalsIgnoreCase("False")){
			Boolean val = false;
			return val;
		}
		return null;
	}
	public static void setGlobalsToDefaults(){
		Globals.globalvars.put("gamestage", "none");
		Globals.globalvars.put("cleft", 0);
	}
}
