/**
 * fbtosemweb-webservice - to.networld.fbtosemweb.ws.common
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

package to.networld.fbtosemweb.ws.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Alex Oberhauser
 */
public class SessionHandler {
	private static final String SESSION_KEY = "2493353e-b4fc-4bef-abe9-72b5ae565702";
	
	public static void initSession(HttpServletRequest _request, String _access_token) {
		HttpSession session = _request.getSession();
		session.setAttribute(SESSION_KEY, _access_token);
	}
	
	public static String getAccessToken(HttpServletRequest _request) {
		HttpSession session = _request.getSession();
		return (String) session.getAttribute(SESSION_KEY);
	}
	
	public static void invalidateSession(HttpServletRequest _request) {
		HttpSession session = _request.getSession();
		session.removeAttribute(SESSION_KEY);
		session.invalidate();
	}
}
