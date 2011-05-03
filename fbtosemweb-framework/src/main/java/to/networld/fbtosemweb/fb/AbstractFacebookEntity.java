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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alex Oberhauser
 */
public abstract class AbstractFacebookEntity {
	
	/**
	 * Returns a JSONObject instance with the result received from the URL.
	 * 
	 * @param _url The URL from where to fetch data. Should return JSON.
	 * @return JSONObject instance.
	 * @throws IOException
	 * @throws JSONException
	 */
	protected JSONObject getContent(String _url) throws IOException, JSONException {
		URL clientURL = new URL(_url);
		BufferedReader clientInformation = new BufferedReader(new InputStreamReader(clientURL.openStream()));
		String line = "";
		StringBuffer jsonResult = new StringBuffer();
		while ( (line = clientInformation.readLine()) != null ) {
			jsonResult.append(line);
		}
		return new JSONObject(jsonResult.toString());
	}
	
}
