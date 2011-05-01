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

/**
 * @author Alex Oberhauser
 */
public abstract class ServletHelper {
	
	public static String getCurrentUrl(HttpServletRequest _request, boolean _withQueryString) { 
	    String reqUrl = _request.getRequestURL().toString(); 
	    String queryString = _request.getQueryString(); 
	    if ( _withQueryString && queryString != null ) { 
	    	reqUrl += "?" + queryString;
	    } 
	    return reqUrl; 
	}
	
}
