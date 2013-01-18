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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;

/**
 * @author t_hara
 *
 */
public class CategoryHierarchyKeyMatcher implements CategoryComponentVisitor {

	final CategoryCompositeKey key;
	
	final List<CategoryComponent> hierachy = new ArrayList<CategoryComponent>();
	
	/**
	 * @param key
	 */
	public CategoryHierarchyKeyMatcher(CategoryCompositeKey key) {
		super();
		this.key = key;
	}

	@Override
	public boolean visit(CategoryComponent component) {
		CategoryCompositeKey target = component.getKey();
		if (target.containsParentsOf(key) || target.equals(key)) {
			hierachy.add(component);
		}
		return true;
	}
	
	/**
	 * @return the parents
	 */
	public List<CategoryComponent> getHierachy() {
		Collections.sort(hierachy, new Comparator<CategoryComponent>() {
			@Override
			public int compare(CategoryComponent o1, CategoryComponent o2) {
				return new Integer(o1.getKey().getLevel()).compareTo(o2.getKey().getLevel());
			}
		});
		return hierachy;
	}

}
