/**
 * cas-framework - to.networld.facebook
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

package to.networld.facebook;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alex Oberhauser
 */
public class FacebookAgent {
	private final String id;
	private final JSONObject agent;
		
	public FacebookAgent(String _id, JSONObject _agent) {
		this.id = _id;
		this.agent = _agent;
	}
	
	public String getID() { return this.id; }
	
	public Object getProperty(String _property) throws JSONException {
		return this.agent.get(_property);
	}
	
	public String getLocationName() throws JSONException {
		return this.agent.getJSONObject("location").getString("name");
	}
	
	public String getLocationID() throws JSONException {
		return this.agent.getJSONObject("location").getString("id");
	}
	
	public Vector<FacebookEmployerEntity> getWork() throws JSONException {
		JSONArray workArray = this.agent.getJSONArray("work");
		Vector<FacebookEmployerEntity> resultVector = new Vector<FacebookEmployerEntity>();
		for ( int count = 0; count < workArray.length(); count++ ) {
			resultVector.add(new FacebookEmployerEntity(workArray.getJSONObject(count)));
		}
		return resultVector;
	}
	
	public Vector<FacebookEducationEntity> getEducation() throws JSONException {
		JSONArray educationArray = this.agent.getJSONArray("education");
		Vector<FacebookEducationEntity> resultVector = new Vector<FacebookEducationEntity>();
		for ( int count = 0; count < educationArray.length(); count++ ) {
			resultVector.add(new FacebookEducationEntity(educationArray.getJSONObject(count)));
		}
		return resultVector;
	}
}
