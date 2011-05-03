/**
 * cas-framework - to.networld.fbtosemweb
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

package to.networld.fbtosemweb.fb;

/**
 * @author Alex Oberhauser
 */
public class FacebookLikesEntry {
	private final String id;
	private final String name;
	private final String category;
	private final String createdTime;
	
	public FacebookLikesEntry(String _id, String _name, String _category, String _createdTime) {
		this.id = _id;
		this.name = _name;
		this.category = _category;
		this.createdTime = _createdTime;
	}
	
	public String getID() { return this.id; }
	public String getName() { return this.name; }
	public String getCategory() { return this.category; }
	public String getCreatedTime() { return this.createdTime; }
	
	public String toString() {
		return this.name + "," + this.id + "," + this.category + "," + this.createdTime;
	}
}
