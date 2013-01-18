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
package org.tomochika1985.struts2_sample.chart.composite.visitor;

import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;

/**
 * @author t_hara
 *
 */
public class CategoryCompositeKeyMatcher implements CategoryComponentVisitor {

	final CategoryCompositeKey key;
	
	CategoryComponent result;
	
	/**
	 * @param key
	 */
	public CategoryCompositeKeyMatcher(CategoryCompositeKey key) {
		super();
		this.key = key;
	}

	@Override
	public boolean visit(CategoryComponent component) {
		if (component.getKey().equals(key)) {
			result = component;
			return false;
		}
		return true;
	}

	/**
	 * @return the result
	 */
	public CategoryComponent getResult() {
		return result;
	}
}
