//Resources
//Has some utility classes.
//By FourOhFour
//http://fourohfour.github.io

package io.github.fourohfour.wolvesvspigs;

public class Resources {
	//One to remove last character from a string
	public static String RemoveLastChar(String str) {

		  if (str.length() > 0 && str.charAt(str.length()-1)=='x') {
		    str = str.substring(0, str.length()-1);
		  }
		  return str;
		}
	
	//One to turn a string to a boolean
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
	
	//Set the global values to their defaults
	public static void setGlobalsToDefaults(){
		Globals.globalvars.put("gamestage", "none");
		Globals.globalvars.put("cleft", 0);
		
		Integer[] prematch = {64, 20};
    	Globals.cdpresets.put("pregame", prematch);
    	Integer[] getready = {5, 1200};
    	Globals.cdpresets.put("prepare", getready);
    	Integer[] battle  = {10, 1200};
    	Globals.cdpresets.put("fight", battle);
    	
    	Globals.countdowns.clear();
    	Globals.lobby.clear();
	}
}
