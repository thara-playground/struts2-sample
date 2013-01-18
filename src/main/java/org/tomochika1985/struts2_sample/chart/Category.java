/*
* Copyright 2011 Tomochika Hara.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.tomochika1985.struts2_sample.chart;

/**
 * @author t_hara
 *
 */
public class Category {

	public static Category ROOT = new Category("","");
	
	String code;
	
	String name;

	/**
	 * @param code
	 * @param name
	 */
	public Category(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the key
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return code.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return name.equals(obj);
	}
	
	@Override
	public String toString() {
		return "(" + code + ":" + name + ")";
	}
}