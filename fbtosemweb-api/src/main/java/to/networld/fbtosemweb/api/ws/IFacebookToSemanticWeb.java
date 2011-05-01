package to.networld.fbtosemweb.api.ws;
import javax.jws.WebParam;

/**
 * fbtosemweb-api - 
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

/**
 * @author Alex Oberhauser
 */
public interface IFacebookToSemanticWeb {
	public String convertFacebookProfileToFOAF(@WebParam(name="accessToken")String _accessToken) throws Exception;
	public String convertFacebookWallToSIOC(@WebParam(name="accessToken")String _accessToken) throws Exception;
}