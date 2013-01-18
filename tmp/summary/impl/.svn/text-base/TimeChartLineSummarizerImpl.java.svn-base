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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.composite.visitor.CategoryLeafsVisitor;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartLineSummarizer;

/**
 * @author t_hara
 *
 */
public class TimeChartLineSummarizerImpl implements TimeChartLineSummarizer {

	final Map<CategoryCompositeKey, CategoryCompositeKey> breakWithTotalizeKeys = 
			new HashMap<CategoryCompositeKey, CategoryCompositeKey>();

	List<CategoryCompositeKey> totalizeKeys;
	
	final List<CategoryCompositeKey> breakKeys = new ArrayList<CategoryCompositeKey>();
	
	CategoryComponent component;
	
	/**
	 * 
	 */
	public TimeChartLineSummarizerImpl() {
		
		List<CategoryComponent> leafsAll = component.accept(new CategoryLeafsVisitor()).getLeafAll();
		
		for (CategoryCompositeKey totalizeKey : totalizeKeys) {
			CategoryCompositeKey breakKey = null;
			for (CategoryComponent c : leafsAll) {
				CategoryCompositeKey key = c.getKey();
				if (totalizeKey.containsParentsOf(key)) {
					breakKey = key;
				} else if (breakKey != null) {
					breakKeys.add(key);
					breakKey = null;
					break;
				}
			}
		}
		
	}
	
	public void putBreakWithTotalizeKeys(CategoryCompositeKey breakKey, CategoryCompositeKey totalizeKey) {
		breakWithTotalizeKeys.put(breakKey, totalizeKey);
	}
	
	@Override
	public boolean isControlBreak(CategoryCompositeKey key) {
		return breakKeys.contains(key);
	}

	@Override
	public CategoryCompositeKey getTotalizeKey(
			CategoryCompositeKey key) {
		
		Collection<CategoryCompositeKey> values = breakWithTotalizeKeys.values();
		for (CategoryCompositeKey sammulizeKey : values) {
			if (key.containsParentsOf(sammulizeKey)) {
				return key;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean containsTotal(CategoryCompositeKey key) {
		
		Collection<CategoryCompositeKey> values = breakWithTotalizeKeys.values();
		for (CategoryCompositeKey sammulizeKey : values) {
			if (key.containsParentsOf(sammulizeKey)) {
				return true;
			}
		}
		
		return false;
	}
}
