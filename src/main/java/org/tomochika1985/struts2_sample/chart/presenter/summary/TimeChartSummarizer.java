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
package org.tomochika1985.struts2_sample.chart.presenter.summary;

import java.util.ArrayList;
import java.util.List;

import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;

/**
 * @author t_hara
 *
 */
public class TimeChartSummarizer {

	private List<CategoryCompositeKey> summarizeKeys = new ArrayList<CategoryCompositeKey>();
	
	public void addSummarizeKey(CategoryCompositeKey key) {
		summarizeKeys.add(key);
	}
	
	public boolean isTargetSummarize(CategoryCompositeKey key) {
		
		for (CategoryCompositeKey summarizeKey : this.summarizeKeys) {
			if (summarizeKey.containsParentsOf(key)) return true;
		}
		
		return false;
	}

	/**
	 * @return the summarizeKeys
	 */
	public List<CategoryCompositeKey> getSummarizeKeys() {
		return summarizeKeys;
	}
}
