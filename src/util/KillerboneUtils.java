package util;

public class KillerboneUtils
{
	final public static String KILLERBONE_FULL_URL = "https://killerbone.mooo.com/";
	final public static String KILLERBONE_DATE_FORMAT = "dd-MM-yyyy HH:mm";
	
	//-----------------------------------------------------------------------//
	
	public static String getUser(long userId)
	{
		return KILLERBONE_FULL_URL + "user/" + String.valueOf(userId);
	}
	
	public static String postUserCreateUrl()
	{
		return KILLERBONE_FULL_URL + "user";
	}
	
	public static String putUserUpdateUrl(long userId)
	{
		return KILLERBONE_FULL_URL + "user/" + String.valueOf(userId);
	}
	
	public static String deleteUserDeleteUrl(long userId)
	{
		return KILLERBONE_FULL_URL + "user/" + String.valueOf(userId);
	}
	
	public static String postAuthTokenRequestUrl()
	{
		return KILLERBONE_FULL_URL + "authenticate/api";
	}
	
	public static String getFriendships(long userId)
	{
		return KILLERBONE_FULL_URL + "friendships/" + String.valueOf(userId);
	}
	
	public static String postFrienshipCreateUrl()
	{
		return KILLERBONE_FULL_URL + "friendship";
	}
	
	public static String putFriendshipAcceptRequestUrl(long friendshipId, long userId)
	{
		return KILLERBONE_FULL_URL + "friendship/" + String.valueOf(friendshipId) + "/accept/" + String.valueOf(userId);
	}
	
	public static String putFriendshipDeclineRequestUrl(long friendshipId, long userId)
	{
		return KILLERBONE_FULL_URL + "friendship/" + String.valueOf(friendshipId) + "/decline/" + String.valueOf(userId);
	}
	
	public static String deleteFriendshipEndRequestUrl(long friendshipId, long userId)
	{
		return KILLERBONE_FULL_URL + "friendship/" + String.valueOf(friendshipId) + "/end/" + String.valueOf(userId);
	}
	
	public static String putFriendshipUpdateLocationRequestUrl(long friendshipId, long userId)
	{
		return KILLERBONE_FULL_URL + "friendship/" + String.valueOf(friendshipId) + "/location/" + String.valueOf(userId);
	}
	
	public static String getAllEventsUrl()
	{
		return KILLERBONE_FULL_URL + "events";
	}
	
	public static String getNewEventsUrl(long timestamp)
	{
		return KILLERBONE_FULL_URL + "events/new/" + String.valueOf(timestamp);
	}
	
	public static String getAllEventCategoriesUrl()
	{
		return KILLERBONE_FULL_URL + "events/categories";
	}	
	
	public static String getAllEventsFromCategoryUrl(String categoryId)
	{
		return KILLERBONE_FULL_URL + "events/" + categoryId;
	}
	
	public static String getNewEventsFromCategoryUrl(String categoryId, long timestamp)
	{
		return KILLERBONE_FULL_URL + "events/" + categoryId + "/new/" + String.valueOf(timestamp);
	}
	
	public static String getAllLocationsUrl()
	{
		return KILLERBONE_FULL_URL + "locations";
	}
	
	public static String getNewLocationsUrl(long timestamp)
	{
		return KILLERBONE_FULL_URL + "locations/new/" + String.valueOf(timestamp);
	}
	
	//-----------------------------------------------------------------------//
	
	/**
	 * Composes the xml for a create user request
	 */
	public static String composeUserCreateXml(String name, String email, String password)
	{
		return String.format("<user><name>%s</name><email>%s</email><password>%s</password></user>", name, email, password);
	}
	
	/**
	 * Composes the xml for a update user request
	 */
	public static String composeUserUpdateXml(String name, String email, String password)
	{
		return String.format("<user><name>%s</name><email>%s</email><password>%s</password></user>", name, email, password);
	}
	
	/**
	 * Composes the xml for an authtoken request
	 */
	public static String composeAuthTokenRequestXml(String email, String password)
	{
		return String.format("<user><email>%s</email><password>%s</password></user>", email, password);
	}
	
	/**
	 * Composes the xml for an friendship request
	 */
	public static String composeFriendshipRequestXml(String toEmail, long fromId)
	{
		return String.format("<friendshipRequest><participantEmail>%s</participantEmail><initiatorId>%d</initiatorId></friendshipRequest>", toEmail, fromId);
	}
	
	/**
	 * Composes the xml for an friendship location update
	 */
	public static String composeFriendshipLocationUpdateXml(double latitude, double longitude)
	{
		return String.format("<location><latitude>%s</latitude><longitude>%s</longitude></location>", String.valueOf(latitude), String.valueOf(longitude));
	}	
}
