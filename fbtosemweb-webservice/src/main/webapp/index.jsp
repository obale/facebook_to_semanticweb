<!doctype html>
<%@page import="to.networld.facebook.FacebookAgentHandler"%>
<%@page import="to.networld.facebook.FacebookAgent"%>
<html>
        <head>
                <title>Facebook To Semantic Web</title>
                <link href="styles/default.css" rel="stylesheet" type="text/css">
        </head>
        <body>
<%
	String access_token = (String)getServletContext().getAttribute("access_token");
%>
                <div id="fb-root"></div>

                <h1><a href="http://fbtofoaf.networld.to" target="_blank">Facebook To Semantic Web</a></h1>

                <p/>
<%
	if ( access_token == null ) {
%>
        <div style="text-align: center">
                <a href="login"><img src="pic/fb_connect.gif" border="0"/></a><p/>
                 The application does not save your data. If you want to check that, you could look into the <a href="http://sources.networld.to/?p=application/fb/fbsemweb.git;a=summary" target="_blank">source code</a>.
        </div>
        <hr/>
<%
	} else {// End "Not Logged In" Block
		try {
			FacebookAgentHandler agentHandler = new FacebookAgentHandler(access_token);
			FacebookAgent agent = agentHandler.getFacebookAgent();
%>
        <div class="header-left">
                Welcome, <b><a href="foaf" target="_blank"><% out.print(agent.getProperty("name")); %></a></b>
        </div>
        <div class="header-right">
                <a href="logout"><img src="pic/fb_logout.gif" border="0"/></a>
        </div>
        <br/>
        <hr/>


        <h2>Use the App</h2>

        <h4>Direct Access</h4>

        <ul>
                <li> <a href="foaf">Facebook Profile to FOAF</a></li>
                <li> <a href="sioc">Wall to SIOC</a></li>
        </ul>

        <div>
                All files were evaluated with the <a href="http://www.w3.org/RDF/Validator/" target="_blank">W3C RDF Validator</a>
        </div>

        <h4>SOAP Web Service</h4>

                <div>
                You need the following information to be able to use the SOAP Web Service. Please be aware that the generated access token expires after some time. To fix this problem take a newly generated token from this page. Later versions will be include the possibility to generate an access_token that do not expire (optional).<p/>
                <table>
                        <tr>
                                <td>Service Description</td>
                                <td><a href="<% out.println(request.getContextPath()); %>/services/fbtosemweb?wsdl" target="_blank">WSDL</a></td>
                                <tr>
                                        <td>Operation List</td>
                                        <td><a href="<% out.println(request.getContextPath()); %>/services" target="_blank">Service/Operation List</a></td>
                                 </tr>
                        </tr>
                        <tr>
                                <td><b>access_token</b></td>
                                <%
                                boolean isSecure = request.getRequestURL().toString().startsWith("https");
                                String access_tokenInput = access_token.split("=")[1];
                                if ( isSecure ) {
                                %>
                                        <td><input type="text" readonly="true" size="110" value="<% out.println(access_tokenInput); %>"></input></td>
                                <%
                                } else {
                                	String sslURL = request.getRequestURL().toString().replaceFirst("http", "https").replaceFirst("8080", "8443");
                                %>
                                        <td>Change to <a href="<% out.println(sslURL); %>">HTTPS Connection</a> to view this value. (security reason)</td>
                                <%
                                }
                                %>
                        </tr>
                                        <%
                        		if ( isSecure ) {
                        		%>
                        <tr>
                                <td>Examples</td>
                                <td>
                                        <a href="<% out.println(request.getContextPath()); %>/services/fbtosemweb/convertFacebookProfileToFOAF?access_token=<% out.print(access_tokenInput); %>" target="_blank">Facebook Profile (FOAF)</a>
                                        and <a href="<% out.println(request.getContextPath()); %>/services/fbtosemweb/convertFacebookWallToSIOC?access_token=<% out.print(access_tokenInput); %>" target="_blank">Facebook Wall (SIOC)</a>
                                </td>
                        </tr>
                                        <%
                        		}
                        		%>
                </table>
                <p/>
                        If you are not familiar with SOAP Services you should maybe use the <i>Direct Access</i> links from the previous section.

                </div>

        <h2>App Information</h2>

        <table>
                <tr>
                        <td width="250px"><b>Version</b></td>
                        <td>0.2 (<a href="ChangeLog" target="_blank">ChangeLog</a>)</td>
                </tr>
                <tr>
                        <td><b>Direct Link</b></td>
                        <td><a href="http://fbtofoaf.networld.to" target="_blank">Open</a><td/>
                </tr>
                        </tr><td></td><td><hr/></td></tr>
                <tr>
                        <td><b>Facebook Canvas Page</b></td>
                        <td><a href="http://apps.facebook.com/fbtofoaf" target="_blank">Open</a></td>
                </tr>
                <tr>
                        <td><b>Facebook App Page</b></td>
                        <td><a href="http://www.facebook.com/apps/application.php?id=152466208148875" target="_blank">Open</a></td>
                </tr>
                        </tr><td></td><td><hr/></td></tr>
                <tr>
                        <td><b>Data Storage</b></td>
                        <td>There is no storage, data is exported on-the-fly.</td>
                </tr>
                <tr>
                        <td><b>Source Code</b></td>
                        <td><a href="http://sources.networld.to/?p=application/fb/fbsemweb.git;a=summary" target="_blank">Git Repository</a></td>
                </tr>
        </table>



        <h2>Related Apps</h2>

        <ul>
                <li> <a href="http://foafviewer.android.networld.to/" target="_blank">FOAF Viewer for Android</a><br/>
                After the export of your Facebook Profile to FOAF you could visualize this file on your mobile device. For more information see the Screenshots on the website.
                </li>
        </ul>

        <h2>Known Bugs and Missing Features</h2>

        <ul>
                <li> [FEATURE] Getting GPS Coordinates for the location</li>
                <li> [FEATURE] Linking the Location to the <a href="http://linkeddata.org/" target="_blank">Linked Open Data Cloud</a></li>
                <li> [FEATURE] Provide export in alternative formats (e.g. N3, Turtle)</li>
                <li> [FEATURE] Let the user choose if the access_token should expire or not (offline_access). Could be useful for SOAP Service Access.</li>
                <li> [FEATURE] Wrapper that provides public available FOAF File in Real Time, e.g. http://fbtofoaf.networld.to/users/john.doe/foaf.rdf</li>
                <li> [BUG] All links are pointing to the Facebook Graph API and not to the Linke Open Data Cloud.
        </ul>
        <hr/>
<%
		} catch (Exception e) {
			response.sendRedirect("./logout");
		}
	} // End "Logged In" Block
%>
        <div class="footer-left">
                <iframe src="http://www.facebook.com/plugins/like.php?href=http%3A%2F%2Fwww.facebook.com%2Fapps%2Fapplication.php%3Fid%3D152466208148875&amp;send=true&amp;layout=standard&amp;width=450&amp;show_faces=false&amp;action=like&amp;colorscheme=light&amp;font&amp;height=35" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:450px; height:35px;" allowTransparency="true"></iframe>
        </div>

        <div class="footer-right">
                &copy; <a href="http://devnull.networld.to/foaf.rdf#me" target="_blank">Alex Oberhauser</a>
        </div>
        </body>
</html>
