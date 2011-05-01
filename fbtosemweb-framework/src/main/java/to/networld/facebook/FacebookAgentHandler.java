package to.networld.facebook;
import java.io.IOException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * cas-framework - 
 *
 * Copyright (C) 2011 by Networld Project
 * Written by Alex Oberhauser <oberhauseralex@networld.to>
 * All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see <http://www.gnu.org/licenses/>
 */

/**
 * @author Alex Oberhauser
 */
public class FacebookAgentHandler extends AbstractFacebookEntity {
	private final String accessToken;
	
	private final String FB_ME_URL ;
	private final String FB_WALL_URL;
	private final String FB_LIKES_URL;
	private final String FB_FRIENDS_URL;
	
	public FacebookAgentHandler(String _accessToken) {
		this.accessToken = _accessToken;
		this.FB_ME_URL = "https://graph.facebook.com/me?" + this.accessToken;
		this.FB_WALL_URL = "https://graph.facebook.com/me/feed?" + this.accessToken;
		this.FB_LIKES_URL = "https://graph.facebook.com/me/likes?" + this.accessToken;
		this.FB_FRIENDS_URL = "https://graph.facebook.com/me/friends?" + this.accessToken;
	}
	
	public FacebookAgent getFacebookAgent() throws IOException, JSONException {
		JSONObject entryObject = this.getContent(this.FB_ME_URL);
		return new FacebookAgent(entryObject.getString("id"), entryObject);
	}
	
	public JSONObject getFacebookWall() throws IOException, JSONException {
		return this.getContent(FB_WALL_URL);
	}
	
	public Vector<FacebookLikesEntry> getLikesConcepts() throws IOException, JSONException {
		Vector<FacebookLikesEntry> resultEntries = new Vector<FacebookLikesEntry>();
		JSONObject likesObject = this.getContent(this.FB_LIKES_URL);
		JSONArray likesEntries = likesObject.getJSONArray("data");
		for ( int count=0; count < likesEntries.length(); count++ ) {
			JSONObject jsonEntry = (JSONObject) likesEntries.get(count);
			FacebookLikesEntry likesEntry = new FacebookLikesEntry(jsonEntry.getString("id"),
					jsonEntry.getString("name"),
					jsonEntry.getString("category"),
					jsonEntry.getString("created_time"));
			resultEntries.add(likesEntry);
		}
		return resultEntries;
	}
	
	public Vector<FacebookFriendEntry> getFriends() throws IOException, JSONException {
		Vector<FacebookFriendEntry> resultEntries = new Vector<FacebookFriendEntry>();
		JSONObject likesObject = this.getContent(this.FB_FRIENDS_URL);
		JSONArray likesEntries = likesObject.getJSONArray("data");
		for ( int count=0; count < likesEntries.length(); count++ ) {
			JSONObject jsonEntry = (JSONObject) likesEntries.get(count);
			FacebookFriendEntry friendEntry = new FacebookFriendEntry(jsonEntry.getString("id"),
					jsonEntry.getString("name"));
			resultEntries.add(friendEntry);
		}
		return resultEntries;
	}
}
