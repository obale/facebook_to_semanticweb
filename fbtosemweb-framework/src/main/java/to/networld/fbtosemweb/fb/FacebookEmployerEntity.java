/**
 * cas-framework - to.networld.fbtosemweb
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

package to.networld.fbtosemweb.fb;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alex Oberhauser
 */
public class FacebookEmployerEntity {
	private final JSONObject employer;
	
	public FacebookEmployerEntity(JSONObject _employer) {
		this.employer = _employer;
	}
	
	public String getEmployerID() throws JSONException {
		return this.employer.getJSONObject("employer").getString("id");
	}
	
	public String getEmployerName() throws JSONException {
		return this.employer.getJSONObject("employer").getString("name");
	}
	
	public String getPositionID() throws JSONException {
		return this.employer.getJSONObject("position").getString("id");
	}
	
	public String getPositionName() throws JSONException {
		return this.employer.getJSONObject("position").getString("name");
	}
}
