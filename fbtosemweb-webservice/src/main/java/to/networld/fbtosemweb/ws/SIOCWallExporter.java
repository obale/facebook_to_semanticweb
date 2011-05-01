/**
 * cas-webservice - to.networld.fbtosemweb.ws
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
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import to.networld.facebook.FacebookAgentHandler;
import to.networld.facebook.FacebookToSIOC;

/**
 * @author Alex Oberhauser
 */
public class SIOCWallExporter extends HttpServlet {
	private static final long serialVersionUID = -5966673735413828614L;

	@Override
	public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String access_token = (String) context.getAttribute("access_token");
		if ( access_token == null ) {
			access_token = (String) _request.getParameter("access_token");
			if ( access_token == null ) {
				_response.sendRedirect(".");
				return;
			} else {
				access_token = "access_token=" + access_token;
			}
		}
		
		PrintWriter out = _response.getWriter();
		
		try {
			FacebookAgentHandler currentAgentHandler = new FacebookAgentHandler(access_token);
			FacebookToSIOC siocFile = new FacebookToSIOC(currentAgentHandler.getFacebookWallFeed());
			_response.setCharacterEncoding("UTF-8");
			_response.setContentType("application/rdf+xml; charset=UTF-8");
			_response.setHeader("Content-Disposition", "attachment; filename=sioc_wall.rdf");
			out.println(siocFile);
		} catch (JSONException e) {
			out.println(e.getLocalizedMessage());
			_response.setStatus(500);
		}
	}
}
