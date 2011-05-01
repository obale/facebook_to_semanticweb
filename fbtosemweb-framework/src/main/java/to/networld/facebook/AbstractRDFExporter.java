/**
 * cas-webservice - to.networld.cas.ws
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Namespace;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * @author Alex Oberhauser
 */
public abstract class AbstractRDFExporter {
	protected Document rdfDocument;
	
	private static final int INDENT_SIZE = 4;
	private static final boolean EXPAND_EMPTY_ELEMENTS = false;
	
	public static Namespace RDF_NS = DocumentHelper.createNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
	public static Namespace RDFS_NS = DocumentHelper.createNamespace("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
	public static Namespace FOAF_NS = DocumentHelper.createNamespace("foaf", "http://xmlns.com/foaf/0.1/");
	public static Namespace DC_NS = DocumentHelper.createNamespace("dc", "http://purl.org/dc/elements/1.1/");
	public static Namespace DCT_NS = DocumentHelper.createNamespace("dct", "http://purl.org/dc/terms/");
	public static Namespace GEO_NS = DocumentHelper.createNamespace("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
	public static Namespace SIOC_NS = DocumentHelper.createNamespace("sioc", "http://rdfs.org/sioc/ns#");
	
	public AbstractRDFExporter() {		
		this.rdfDocument = DocumentHelper.createDocument();
		this.rdfDocument.setXMLEncoding("UTF-8");
		
		this.rdfDocument.addComment("Facebook to FOAF App (http://fbtofoaf.networld.to)");
		this.rdfDocument.addComment("(C) Alex Oberhauser - http://devnull.networld.to/foaf.rdf#me");
	}
	
	@Override
	public String toString() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setIndentSize(INDENT_SIZE);
			format.setExpandEmptyElements(EXPAND_EMPTY_ELEMENTS);
			XMLWriter writer = new XMLWriter(os, format);
			writer.write(this.rdfDocument);
			return os.toString();
		} catch (IOException e) {
			return null;
		}
	}
}
