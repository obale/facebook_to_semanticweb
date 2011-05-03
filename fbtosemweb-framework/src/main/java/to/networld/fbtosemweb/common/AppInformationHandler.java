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

package to.networld.fbtosemweb.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Alex Oberhauser
 */
public class AppInformationHandler {
	private static final Logger logger = Logger.getLogger(AppInformationHandler.class);

	public static String getApplicationID() {
		Properties prop = new Properties();
		try {
			prop.load(AppInformationHandler.class.getResourceAsStream("/default.properties"));
		} catch (IOException e) {
			logger.fatal(e.getLocalizedMessage());
		}
		return prop.getProperty("fb.appid");
	}
	
	public static String getApplicationSecret() {
		Properties prop = new Properties();
		try {
			prop.load(AppInformationHandler.class.getResourceAsStream("/default.properties"));
		} catch (IOException e) {
			logger.fatal(e.getLocalizedMessage());
		}
		return prop.getProperty("fb.secret");
	}
	
	public static String getVersion() {
		Properties prop = new Properties();
		try {
			prop.load(AppInformationHandler.class.getResourceAsStream("/default.properties"));
		} catch (IOException e) {
			logger.fatal(e.getLocalizedMessage());
		}
		return prop.getProperty("version");
	}
	
	public static String getAuthor() {
		Properties prop = new Properties();
		try {
			prop.load(AppInformationHandler.class.getResourceAsStream("/default.properties"));
		} catch (IOException e) {
			logger.fatal(e.getLocalizedMessage());
		}
		return prop.getProperty("author");
	}
}
