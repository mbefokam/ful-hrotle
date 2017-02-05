package org.njit.cs.workspace;

//import YelpRequest.YelpAPI;

import org.json.simple.*;

public class RunTest {
	public static String[] args;

	public static void main(String[] args) {
		String activities = new String();
		
		JSONObject userInfo = new JSONObject();
		JSONObject condition = new JSONObject();
		JSONArray condArray = new JSONArray();
		condArray.add("Mental Health");
		
		userInfo.put("condition", condArray);

		userInfo.put("age", 20);

		userInfo.put("weight", 190);
		
		userInfo.put("location", "27606");
		
		JSONObject user = new JSONObject();
		user.put("user", userInfo);
		
		activities = Activities.determineActivities("Mental Health", "27606");

		System.out.println(activities);
	}
}
