/**
 * fbtosemweb-webservice - to.networld.fbtosemweb.ws
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

package to.networld.fbtosemweb.ws;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

import org.json.JSONException;

import to.networld.fbtosemweb.FacebookToFOAF;
import to.networld.fbtosemweb.FacebookToSIOC;
import to.networld.fbtosemweb.api.ws.IFacebookToSemanticWeb;
import to.networld.fbtosemweb.fb.FacebookAgentHandler;

/**
 * @author Alex Oberhauser
 */
@WebService(targetNamespace="http://ws.fbtosemweb.networld.to/")
public class FacebookToSemanticWeb  implements IFacebookToSemanticWeb {
	
	@WebMethod
	public String convertFacebookProfileToFOAF(@WebParam(name="access_token")String _accessToken) throws IOException, JSONException {
		if ( _accessToken == null )
			throw new WebServiceException("Please specify the 'access_token' parameter");
		FacebookAgentHandler currentAgentHandler = new FacebookAgentHandler("access_token=" + _accessToken);
		FacebookToFOAF foafFile = new FacebookToFOAF(currentAgentHandler);
		return foafFile.toString();
	}
	
	@WebMethod
	public String convertFacebookWallToSIOC(@WebParam(name="access_token")String _accessToken) throws IOException, JSONException {
		if ( _accessToken == null )
			throw new WebServiceException("Please specify the 'access_token' parameter");
		FacebookAgentHandler currentAgentHandler = new FacebookAgentHandler("access_token=" + _accessToken);				
		FacebookToSIOC siocFile = new FacebookToSIOC(currentAgentHandler.getFacebookWallFeed());
		return siocFile.toString();
	}
	
//	@WebMethod
//	public String convertFacebookHomeToSIOC(@WebParam(name="access_token")String _accessToken) throws IOException, JSONException {
//		if ( _accessToken == null )
//			throw new WebServiceException("Please specify the 'access_token' parameter");
//		FacebookAgentHandler currentAgentHandler = new FacebookAgentHandler("access_token=" + _accessToken);				
//		FacebookToSIOC siocFile = new FacebookToSIOC(currentAgentHandler.getFacebookHomeFeed());
//		return siocFile.toString();
//	}
}
