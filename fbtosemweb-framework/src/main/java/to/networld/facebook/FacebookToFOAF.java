/**
 * cas-framework - to.networld.facebook
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
import java.util.Vector;

import org.dom4j.Element;
import org.dom4j.QName;
import org.json.JSONException;

/**
 * @author Alex Oberhauser
 */
public class FacebookToFOAF extends AbstractRDFExporter {
	private FacebookAgent agent;
	private FacebookAgentHandler agentHandler;
	
	public FacebookToFOAF(FacebookAgentHandler _agentHandler) throws IOException, JSONException {
		super();
		this.agentHandler = _agentHandler;
		this.createFOAF();
	}
	
	private void addFoafEntry(Element _rootNode, String _foafName, String _fbValue) throws JSONException {
		String name = (String) this.agent.getProperty(_fbValue);
		if ( name != null )
			_rootNode.addElement(new QName(_foafName, FOAF_NS)).setText(name);
	}
	
	private void addHoldsAccount(Element _rootNode, String _serviceHomepage, String _profilePage, String _profileName) {
		Element holdsAccount = _rootNode.addElement(new QName("holdsAccount", FOAF_NS));
		Element onlineAccount = holdsAccount.addElement(new QName("OnlineAccount", FOAF_NS));
		onlineAccount.addElement(new QName("accountServiceHomepage", FOAF_NS)).addAttribute(new QName("resource", RDF_NS), _serviceHomepage);
		onlineAccount.addElement(new QName("accountProfilePage", FOAF_NS)).addAttribute(new QName("resource", RDF_NS), _profilePage);
		onlineAccount.addElement(new QName("accountName", FOAF_NS)).setText(_profileName);
	}
	
	private void createFOAF() throws IOException, JSONException {
		this.agent = this.agentHandler.getFacebookAgent();
		
		Element rootNode = this.rdfDocument.addElement(new QName("RDF", RDF_NS));
		rootNode.add(FOAF_NS);
		rootNode.add(DC_NS);
		rootNode.add(DCT_NS);
		rootNode.add(RDFS_NS);
		
		/**
		 * Start foaf:PersonalProfileDocument
		 */
		Element personalProfileDocumentNode = rootNode.addElement(new QName("PersonalProfileDocument", FOAF_NS));
		personalProfileDocumentNode.addAttribute(new QName("about", RDF_NS), "");
		
		personalProfileDocumentNode.addElement(new QName("title", DC_NS)).setText("FOAF File of Facebook User " + this.agent.getID());
//		personalProfileDocumentNode.addElement(new QName("created", DCT_NS)).setText(DateHelper.getXSDDateTime());
		personalProfileDocumentNode.addElement(new QName("modified", DCT_NS)).setText((String) this.agent.getProperty("updated_time"));
		personalProfileDocumentNode.addElement(new QName("maker", FOAF_NS)).addAttribute(new QName("resource", RDF_NS), "http://fbtofoaf.networld.to/");
		personalProfileDocumentNode.addElement(new QName("primaryTopic", FOAF_NS)).addAttribute(new QName("resource", RDF_NS), "#me");
		
		/**
		 * Start foaf:Person Block
		 */
		Element foafNode = rootNode.addElement(new QName("Person", FOAF_NS));
		foafNode.addAttribute(new QName("about", RDF_NS), "#me");
		
		this.addFoafEntry(foafNode, "name", "name");
		this.addFoafEntry(foafNode, "nick", "username");
		this.addFoafEntry(foafNode, "gender", "gender");
		this.addFoafEntry(foafNode, "firstName", "first_name");
		this.addFoafEntry(foafNode, "lastName", "last_name");
		foafNode.addElement(new QName("thumbnail", FOAF_NS)).addAttribute(
				new QName("resource", RDF_NS), "https://graph.facebook.com/" + this.agent.getID() + "/picture");
		
		try {
			String locationName = this.agent.getLocationName();
			String locationID = this.agent.getLocationID();
			Element basedNear = foafNode.addElement(new QName("based_near", FOAF_NS));
			Element geoPoint = basedNear.addElement(new QName("Point", GEO_NS));
			geoPoint.addElement(new QName("title", DC_NS)).setText(locationName);
			geoPoint.addElement(new QName("seeAlso", RDFS_NS))
				.addAttribute(new QName("resource", RDF_NS), "https://graph.facebook.com/" + locationID);
		} catch ( JSONException e ) {
			// TODO: Log here something, at least during development.
		}
		
		Vector<FacebookEducationEntity> eduVector = this.agent.getEducation();
		for ( FacebookEducationEntity entry : eduVector ) {
			Element schoolHomepage = foafNode.addElement(new QName("schoolHomepage", FOAF_NS));
			schoolHomepage.addAttribute(new QName("label", RDFS_NS), entry.getSchoolName());
			schoolHomepage.addAttribute(new QName("resource", RDF_NS), "https://graph.facebook.com/" + entry.getSchoolID());
		}
				
		Vector<FacebookEmployerEntity> employerVector = this.agent.getWork();
		for ( FacebookEmployerEntity entry : employerVector ) {
			try {
				Element workplaceHomepage = foafNode.addElement(new QName("workplaceHomepage", FOAF_NS));
				workplaceHomepage.addAttribute(new QName("label", RDFS_NS), entry.getEmployerName());
				workplaceHomepage.addAttribute(new QName("resource", RDF_NS), "https://graph.facebook.com/" + entry.getEmployerID());				
			} catch ( JSONException e ) {
				// TODO: Log here something, at least during development.
			}
		}

		String profileName = (String) this.agent.getProperty("username");
		if ( profileName == null )
			profileName = this.agent.getID();
		String profilePage = (String) this.agent.getProperty("link");
		this.addHoldsAccount(foafNode, "http://www.facebook.com", profilePage, profileName);
		
		Vector<FacebookLikesEntry> likes = this.agentHandler.getLikesConcepts();
		for ( FacebookLikesEntry entry : likes ) {
			// XXX: Maybe better FOAF Concept for Likes Pages is topic_interest
			Element interest = foafNode.addElement(new QName("interests", FOAF_NS));
			interest.addAttribute(new QName("resource", RDF_NS), "http://graph.facebook.com/" + entry.getID());
			interest.addAttribute(new QName("label", RDFS_NS), entry.getName());
		}
		
		Vector<FacebookFriendEntry> friends = this.agentHandler.getFriends();
		for ( FacebookFriendEntry entry : friends ) {
			String friendURL = "http://graph.facebook.com/" + entry.getID();
			foafNode.addElement(new QName("knows", FOAF_NS)).addAttribute(new QName("resource", RDF_NS), friendURL);
			Element friendNode = rootNode.addElement(new QName("Person", FOAF_NS));
			friendNode.addElement(new QName("name", FOAF_NS)).setText(entry.getName());
			friendNode.addElement(new QName("thumbnail", FOAF_NS)).addAttribute(new QName("resource", RDF_NS), friendURL + "/picture");
		}
	}

}
