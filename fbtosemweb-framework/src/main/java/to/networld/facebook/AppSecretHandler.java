/**
 * fbtosemweb-framework - to.networld.facebook
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

import java.io.IOException;
import java.util.Properties;

/**
 * TODO: Read out this value from a config that is not pushed to the repository.
 * 
 * @author Alex Oberhauser
 */
public class AppSecretHandler {
	
	public static String getApplicationID() {
		Properties prop = new Properties();
		try {
			prop.load(AppSecretHandler.class.getResourceAsStream("/fb.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("fb.appid");
	}
	
	public static String getApplicationSecret() {
		Properties prop = new Properties();
		try {
			prop.load(AppSecretHandler.class.getResourceAsStream("/fb.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("fb.secret");
	}
}
