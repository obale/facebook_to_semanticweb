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

import org.dom4j.Element;
import org.dom4j.QName;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alex Oberhauser
 */
public class FacebookToSIOC extends AbstractRDFExporter {
	private JSONObject object;
	
	public FacebookToSIOC(JSONObject _object) throws IOException, JSONException {
		super();
		this.object = _object;
		this.createSIOC();
	}
	
	/**
	 * TODO: If a link was posted, add also the link to the SIOC block.
	 * @throws IOException
	 * @throws JSONException
	 */
	public void createSIOC() throws IOException, JSONException {
		Element rootNode = this.rdfDocument.addElement(new QName("RDF", RDF_NS));
		rootNode.add(SIOC_NS);
		rootNode.add(DCT_NS);
		
		JSONArray wallEntries = this.object.getJSONArray("data");
		for ( int count=0; count < wallEntries.length(); count++ ) {
			JSONObject wallEntry = (JSONObject) wallEntries.get(count);
			Element siocPost = rootNode.addElement(new QName("Post", SIOC_NS))
				.addAttribute(new QName("about", RDF_NS), "http://graph.facebook.com/" + wallEntry.getString("id"));
			siocPost.addElement(new QName("content", SIOC_NS)).setText(wallEntry.getString("message"));
			String creatorID = wallEntry.getJSONObject("from").getString("id");
			siocPost.addElement(new QName("hasCreator", SIOC_NS)).addAttribute(new QName("resource", RDF_NS), "http://graph.facebook.com/" + creatorID);
			try {
				siocPost.addElement(new QName("created", SIOC_NS)).setText(wallEntry.getString("created_time"));
			} catch ( JSONException e) { }
			try {
				siocPost.addElement(new QName("modified", SIOC_NS)).setText(wallEntry.getString("updated_time"));
			} catch ( JSONException e) { }
			
			try {
				JSONArray comments = wallEntry.getJSONObject("comments").getJSONArray("data");
				for ( int count1=0; count1 < comments.length(); count1++ ) {
					JSONObject comment = comments.getJSONObject(count1);
					Element replyNode = siocPost.addElement(new QName("has_reply", SIOC_NS));
					Element replyPost = replyNode.addElement(new QName("Post", SIOC_NS));
					replyPost.addAttribute(new QName("about", RDF_NS), "http://graph.facebook.com/" + comment.getString("id"));
					replyPost.addElement(new QName("content", SIOC_NS)).setText(comment.getString("message"));
					String replierID = comment.getJSONObject("from").getString("id");
					replyPost.addElement(new QName("hasCreator", SIOC_NS)).addAttribute(new QName("resource", RDF_NS), "http://graph.facebook.com/" + replierID);
					try {
						replyPost.addElement(new QName("created", SIOC_NS)).setText(comment.getString("created_time"));
					} catch ( JSONException e) { }
					try {
						replyPost.addElement(new QName("modified", SIOC_NS)).setText(comment.getString("updated_time"));
					} catch ( JSONException e) { }
				}
			} catch ( JSONException e) {
				// TODO: Log here something, at least during development.
			}
		}
	}
}
