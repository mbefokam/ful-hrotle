package org.njit.cs.workspace;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.ACTIVITY_COMPLETED;

//import YelpRequest.YelpAPI;
//import YelpRequest.TwoStepOAuth;

/**
 * Determines which activities user's should use depending on their condition,
 * age, weight, and location.
 * 
 * @author ezerihun
 *
 */
public class Activities {
	// The user information given by the front end
	private static JSONObject user;
	
	//private static String[] activityLabel = { "one", "two", "three" };

	/**
	 * Initialize the user for the Activities class
	 * 
	 * @param JSONObject
	 *            the user
	 * 
	 */
	public Activities(JSONObject user) {
		this.user = user;
	}

	/**
	 * Determine which activities the user should perform
	 * 
	 * @param JSONObject
	 *            user
	 * @return array of activities
	 */
	public static String determineActivities(String condition, String location) {
		// The user's condition
		String conditionString = null;
		// Convert JSONObject to string
		StringWriter out = new StringWriter();
		// Array of activities the user should perform
		JSONObject activities = null;

		// Go to the method that corresponds with their health condition
		switch (condition) {
		case "mentalhealth":
			activities = doMentalHealth(location);
			break;
		case "heartdisease":
			activities = doHeartDisease(location);
			break;
		case "hypertension":
			activities = doHypertension(location);
			break;
		case "diabetes":
			activities = doType2Diabetes(location);
			break;
		case "asthma":
			activities = doAsthmaAllergies(location);
			break;
		case "arthritis":
			activities = doArthritis(location);
			break;
		case "nocondition":
			activities = doNoCondition(location);
			break;
		default:
			activities = doNoCondition(location);
			break;
		}

		// Return the list of activities
		return activities.toJSONString();
	}

	/**
	 * Create activities for the condition mental health
	 * 
	 * @param age
	 * @param weight
	 * @param location
	 * @return array of activities
	 */
	public static JSONObject doMentalHealth(String location) {
		// Array of activities
		JSONObject activities = new JSONObject();
		// List of activity locations to search Yelp with
		String[] activityLocList = { "jogging", "cycling", "dancing" };
		// Get the list of activities
		activities = getActivities(location, activityLocList);

		// Add tips to JSON
		JSONArray jogging = new JSONArray();
		jogging.add("");
		JSONArray cycling = new JSONArray();
		cycling.add("");
		JSONArray dancing = new JSONArray();
		dancing.add("");
		for (int i = 0; i < activityLocList.length; i++) {
			JSONObject tips = new JSONObject();
			if (i == 0)
				tips.put("tips", jogging);
			if (i == 1)
				tips.put("tips", cycling);
			if (i == 2)
				tips.put("tips", dancing);
			((JSONArray) ((JSONObject) ((JSONArray) activities.get("user"))
					.get(i)).get("activityLabel")).add(tips);
		}

		// Return array of activities
		return activities;
	}

	/**
	 * Create activities for the condition mental health
	 * 
	 * @param age
	 * @param weight
	 * @param location
	 * @return array of activities
	 */
	public static JSONObject doHeartDisease(String location) {
		// Array of activities
		JSONObject activities = new JSONObject();
		// List of activity locations to search Yelp with
		String[] activityLocList = { "jogging", "yoga", "swimming" };

		// Get the list of activities
		activities = getActivities(location, activityLocList);

		// Add tips to JSON
		JSONArray jogging = new JSONArray();
		jogging.add("Avoid hilly areas");
		jogging.add("Try aerobic exercises");
		jogging.add("Avoid exercises that cause shortness of breath");
		for (int i = 0; i < activityLocList.length; i++) {
			JSONObject tips = new JSONObject();
			if (i == 0)
				tips.put("tips", jogging);
			((JSONArray) ((JSONObject) ((JSONArray) activities.get("user"))
					.get(i)).get("activityLabel")).add(tips);
		}

		// Return array of activities
		return activities;
	}

	/**
	 * Create activities for the condition mental health
	 * 
	 * @param age
	 * @param weight
	 * @param location
	 * @return array of activities
	 */
	public static JSONObject doHypertension(String location) {
		// Array of activities
		JSONObject activities = new JSONObject();
		// List of activity locations to search Yelp with
		String[] activityLocList = { "jogging", "swimming", "rowing" };
		// Get the list of activities
		activities = getActivities(location, activityLocList);

		// Add tips to JSON
		JSONArray jogging = new JSONArray();
		jogging.add("");
		JSONArray swimming = new JSONArray();
		swimming.add("");
		JSONArray rowing = new JSONArray();
		rowing.add("");
		for (int i = 0; i < activityLocList.length; i++) {
			JSONObject tips = new JSONObject();
			if (i == 0)
				tips.put("tips", jogging);
			if (i == 1)
				tips.put("tips", swimming);
			if (i == 2)
				tips.put("tips", rowing);
			((JSONArray) ((JSONObject) ((JSONArray) activities.get("user"))
					.get(i)).get("activityLabel")).add(tips);
		}

		// Return array of activities
		return activities;
	}

	/**
	 * Create activities for the condition mental health
	 * 
	 * @param age
	 * @param weight
	 * @param location
	 * @return array of activities
	 */
	public static JSONObject doType2Diabetes(String location) {
		// Array of activities
		JSONObject activities = new JSONObject();
		// List of activity locations to search Yelp with
		String[] activityLocList = { "tai chi", "gym", "yoga" };
		// Get the list of activities
		activities = getActivities(location, activityLocList);

		// Add tips to JSON
		JSONArray taichi = new JSONArray();
		taichi.add("");
		JSONArray gym = new JSONArray();
		gym.add("");
		JSONArray yoga = new JSONArray();
		yoga.add("");
		JSONArray jogging = new JSONArray();
		jogging.add("");
		for (int i = 0; i < activityLocList.length; i++) {
			JSONObject tips = new JSONObject();
			if (i == 0)
				tips.put("tips", taichi);
			if (i == 1)
				tips.put("tips", gym);
			if (i == 2)
				tips.put("tips", yoga);
			if (i == 3)
				tips.put("tips", jogging);
			((JSONArray) ((JSONObject) ((JSONArray) activities.get("user"))
					.get(i)).get("activityLabel")).add(tips);
		}

		// Return array of activities
		return activities;
	}

	/**
	 * Create activities for the condition mental health
	 * 
	 * @param age
	 * @param weight
	 * @param location
	 * @return array of activities
	 */
	public static JSONObject doAsthmaAllergies(String location) {
		// Array of activities
		JSONObject activities = new JSONObject();
		// List of activity locations to search Yelp with
		String[] activityLocList = { "gym", "yoga", "taichi" };
		// Get the list of activities
		activities = getActivities(location, activityLocList);

		// Add tips to JSON
		JSONArray gym = new JSONArray();
		gym.add("Avoid walking or exercising outdoors during high allergy season");
		JSONArray yoga = new JSONArray();
		yoga.add("");
		JSONArray taichi = new JSONArray();
		yoga.add("");
		for (int i = 0; i < activityLocList.length; i++) {
			JSONObject tips = new JSONObject();
			if (i == 0)
				tips.put("tips", gym);
			if (i == 1)
				tips.put("tips", yoga);
			if (i == 2)
				tips.put("tips", taichi);
			((JSONArray) ((JSONObject) ((JSONArray) activities.get("user"))
					.get(i)).get("activityLabel")).add(tips);
		}

		// Return array of activities
		return activities;
	}

	/**
	 * Create activities for the condition mental health
	 * 
	 * @param age
	 * @param weight
	 * @param location
	 * @return array of activities
	 */
	public static JSONObject doArthritis(String location) {
		// Array of activities
		JSONObject activities = new JSONObject();
		// List of activity locations to search Yelp with
		String[] activityLocList = { "pilates", "yoga", "swimming" };
		// Get the list of activities
		activities = getActivities(location, activityLocList);

		// Add tips to JSON
		JSONArray pilates = new JSONArray();
		pilates.add("");
		JSONArray yoga = new JSONArray();
		yoga.add("");
		JSONArray swimming = new JSONArray();
		swimming.add("Water aerobics eases the strain on joints");
		for (int i = 0; i < activityLocList.length; i++) {
			JSONObject tips = new JSONObject();
			if (i == 0)
				tips.put("tips", pilates);
			if (i == 1)
				tips.put("tips", yoga);
			if (i == 2)
				tips.put("tips", swimming);
			((JSONArray) ((JSONObject) ((JSONArray) activities.get("user"))
				.get(i)).get("activityLabel")).add(tips);
		}

		// Return array of activities
		return activities;
	}

	/**
	 * Create activities for the condition mental health
	 * 
	 * @param age
	 * @param weight
	 * @param location
	 * @return array of activities
	 */
	public static JSONObject doNoCondition(String location) {
		// Array of activities
		JSONObject activities = new JSONObject();
		// List of activity locations to search Yelp with
		String[] activityLocList = { "jogging", "swimming", "gym" };
		// Get the list of activities
		activities = getActivities(location, activityLocList);

		// Add tips to JSON
		JSONArray jogging = new JSONArray();
		jogging.add("");
		JSONArray swimming = new JSONArray();
		swimming.add("");
		JSONArray gym = new JSONArray();
		gym.add("");
		for (int i = 0; i < activityLocList.length; i++) {
			JSONObject tips = new JSONObject();
			if (i == 0)
				tips.put("tips", jogging);
			if (i == 1)
				tips.put("tips", swimming);
			if (i == 2)
				tips.put("tips", gym);
			((JSONArray) ((JSONObject) ((JSONArray) activities.get("user"))
					.get(i)).get("activityLabel")).add(tips);
		}

		// Return array of activities
		return activities;
	}

	/**
	 * Gets the activity information given activity locations
	 * 
	 * @param age
	 * @param weight
	 * @param location
	 * @param activityLocations
	 * @return activities array
	 */
	public static JSONObject getActivities(String location,
			String[] activityLocations) {
		// Array of activities for the user to perform
		JSONArray activities = new JSONArray();
		// Initialize args to input into YelpAPI class
		String[] args = {};
		// List of activity locations to search Yelp with
		String[] activityLocList = activityLocations;
		// Run through all activity locations in the list
		for (int i = 0; i < activityLocList.length; i++) {
			// Array of businesses for the user to perform activities
			JSONArray businessesArray = new JSONArray();
			// Set Yelp search term and location
			YelpAPI.setFields(activityLocList[i], location);
			// Run YelpAPI class
			YelpAPI.main(args);
			// Save response from YelpAPI class
			JSONObject response = YelpAPI.responseServer;
			// Store businesses from the Yelp response
			JSONArray responseBussinesses = (JSONArray) response
					.get("businesses");
			// Run through all businesses in the Yelp response
			for (int j = 0; j < responseBussinesses.size(); j++) {
				// Array to store information for a business
				// e.g. ["name":"Pullen Park",
				// "location":"4242 Six Forks Road",...]
				JSONArray businessArray = new JSONArray();
				// Store response for a specific business
				JSONObject responseBusiness = (JSONObject) responseBussinesses
						.get(j);

				// Get the name of the response business
				String responseBusinessName = (String) responseBusiness
						.get("name");
				// Store the name as a JSONObject
				JSONObject businessName = new JSONObject();
				businessName.put("name", responseBusinessName);
				// Store the name into the information array of the specific
				// business
				businessArray.add(businessName);

				// Get the address of the response business
				JSONArray addressValue = (JSONArray) ((JSONObject) responseBusiness
						.get("location")).get("display_address");
				// Store the address as a JSONObject
				JSONObject address = new JSONObject();
				address.put("address", addressValue);
				// Store the address into the information array of the specific
				// business
				businessArray.add(address);

				// Get the phone of the response business
				String phone = (String) responseBusiness.get("phone");
				// Store the name as a JSONObject
				JSONObject phoneObj = new JSONObject();
				phoneObj.put("phone", phone);
				// Store the name into the information array of the specific
				// business
				businessArray.add(phoneObj);

				// Get the closed of the response business
				boolean closed = (boolean) responseBusiness.get("is_closed");
				// Store the name as a JSONObject
				JSONObject closedObj = new JSONObject();
				closedObj.put("open", !closed);
				// Store the name into the information array of the specific
				// business
				businessArray.add(closedObj);

				// Get the rating of the response business
				Double rating = (Double) responseBusiness.get("rating");
				// Store the name as a JSONObject
				JSONObject ratingObj = new JSONObject();
				ratingObj.put("rating", rating);
				// Store the name into the information array of the specific
				// business
				businessArray.add(ratingObj);

				// Get the image of the response business
				String image = (String) responseBusiness.get("image_url");
				// Store the name as a JSONObject
				JSONObject imageObj = new JSONObject();
				imageObj.put("locationImage", image);
				// Store the name into the information array of the specific
				// business
				businessArray.add(imageObj);

				// Add other business information to get from Yelp
				// ...

				// Create a JSONObject to store the array of business
				// information
				// e.g. "Pullen Park":["name":"Pullen Park",
				// "location":"4242 Six Forks Road",...]
				JSONObject business = new JSONObject();
				business.put("business", businessArray);
				// Add the newly created business to the array of businesses
				businessesArray.add(business);
			}
			// Array of activity information
			// e.g. ["activity":park,"locations":[...],...]
			JSONArray activityArray = new JSONArray();

			// Create label for activity
			JSONObject act = new JSONObject();
			act.put("activity", activityLocList[i]);
			// Add label to array of activity information
			activityArray.add(act);

			// Add the array of all businesses to a JSONObject
			JSONObject locations = new JSONObject();
			locations.put("locations", businessesArray);
			// Add locations to array of activity information
			activityArray.add(locations);

			// Add other information for activity information
			// ...

			// Create JSONObject for array of activity information
			// e.g. "park":["activity":park,"locations":[...],...]
			JSONObject activity = new JSONObject();
			activity.put("activityLabel", activityArray);
			

			// Add activity to array of activities
			// e.g. ["park":["activity":park,"locations":[...],...], "pool":...]
			activities.add(activity);
		}

		JSONObject activitiesObject = new JSONObject();
		activitiesObject.put("user", activities);

		// Return array of activities
		return activitiesObject;
	}
}
