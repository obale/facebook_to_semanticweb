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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alex Oberhauser
 */
public class FacebookEducationEntity {
	private final JSONObject education;
	
	public FacebookEducationEntity(JSONObject _education) {
		this.education = _education;
	}
	
	public String getSchoolID() throws JSONException {
		return this.education.getJSONObject("school").getString("id");
	}
	
	public String getSchoolName() throws JSONException {
		return this.education.getJSONObject("school").getString("name");
	}
	
	public String getConcentrationID() throws JSONException {
		return this.education.getJSONObject("concentration").getString("id");
	}
	
	public String getConcentrationName() throws JSONException {
		return this.education.getJSONObject("concentration").getString("name");
	}
	
	public String getEducationType() throws JSONException {
		return this.education.getString("type");
	}
}
