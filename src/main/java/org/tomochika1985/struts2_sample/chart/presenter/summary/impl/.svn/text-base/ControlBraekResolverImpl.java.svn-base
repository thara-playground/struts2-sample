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
package org.tomochika1985.struts2_sample.chart.presenter.summary.impl;


import java.util.HashMap;
import java.util.Map;

import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.composite.visitor.CategoryComponentVisitor;
import org.tomochika1985.struts2_sample.chart.presenter.summary.ControlBreakResolver;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartSummarizer;

/**
 * @author t_hara
 *
 */
public class ControlBraekResolverImpl implements ControlBreakResolver, CategoryComponentVisitor {

	final Map<CategoryCompositeKey, CategoryCompositeKey> breakWithGroupings = 
			new HashMap<CategoryCompositeKey, CategoryCompositeKey>();
	
	final Map<CategoryCompositeKey, CategoryCompositeKey> allkeyWithGroupings = 
			new HashMap<CategoryCompositeKey, CategoryCompositeKey>();
	
	CategoryCompositeKey summarizeKey;
	
	CategoryCompositeKey controlBreakKey;
	
	/**
	 * @param category
	 * @param summarizer
	 */
	public ControlBraekResolverImpl(CategoryComponent category,
			TimeChartSummarizer summarizer) {
		super();
		
		for (CategoryCompositeKey key : summarizer.getSummarizeKeys()) {
			this.summarizeKey = key;
			category.accept(this);
			if (controlBreakKey != null) {
				breakWithGroupings.put(controlBreakKey, summarizeKey);
				controlBreakKey = null;
			}
		}
	}
	
	@Override
	public boolean visit(CategoryComponent component) {
		if (!component.isLeaf()) return true;
		
		CategoryCompositeKey target = component.getKey();
		if (summarizeKey.containsParentsOf(target)) {
			controlBreakKey = target;
			allkeyWithGroupings.put(target, summarizeKey);
		} else if (controlBreakKey != null) {
			return false;
		}
		
		return true;
	}

	@Override
	public CategoryCompositeKey toGroupingKey(
			CategoryCompositeKey key) {
		return allkeyWithGroupings.get(key);
	}

	@Override
	public boolean isControlBreak(CategoryCompositeKey controlBreakKey) {
		return breakWithGroupings.containsKey(controlBreakKey);
	}
}
