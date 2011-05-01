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

/**
 * @author Alex Oberhauser
 */
public class FacebookFriendEntry {
	private final String id;
	private final String name;
	
	public FacebookFriendEntry(String _id, String _name) {
		this.id = _id;
		this.name = _name;
	}
	
	public String getID() { return this.id; }
	public String getName() { return this.name; }
	
	public String toString() {
		return this.name + "," + this.id;
	}
}
