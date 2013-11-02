package io.github.fourohfour.wolvesvspigs;

import io.github.fourohfour.devcountdown.Countdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Globals {
	    public static Map<String, Object> globalvars = new HashMap<String, Object>();
	    static {
	    	globalvars.put("gamestage", null);
	    	globalvars.put("cleft", null);
	    }
	    public static Map<String, Integer[]> cdpresets = new HashMap<String, Integer[]>();
	    static {
	    	Integer[] prematch = {64, 20};
	    	cdpresets.put("pregame", prematch);
	    	Integer[] getready = {10, 1200};
	    	cdpresets.put("prepare", getready);
	    	Integer[] battle  = {5, 1200};
	    	cdpresets.put("fight", battle);
	    }
	    public static List<Countdown> countdowns = new ArrayList<Countdown>();
}