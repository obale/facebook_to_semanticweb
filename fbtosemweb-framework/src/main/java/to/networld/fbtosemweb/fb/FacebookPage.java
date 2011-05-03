/**
 * fbtosemweb-framework - to.networld.fbtosemweb
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
public class FacebookPage {
	private final JSONObject page;
	
	public FacebookPage(JSONObject _page) {
		this.page = _page;
	}
	
	public String getPageID() throws JSONException {
		return this.page.getString("id");
	}
	
	public String getPageName() throws JSONException {
		return this.page.getString("name");
	}
	
	public String getPageCategory() throws JSONException {
		return this.page.getString("category");
	}
}
