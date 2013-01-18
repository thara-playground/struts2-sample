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

import org.tomochika1985.struts2_sample.chart.TimeChartLine;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.presenter.summary.SummaryPresentationMediator;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartLineSummarizer;

/**
 * @author t_hara
 *
 */
public class SummaryPresentationMediatorImpl implements
		SummaryPresentationMediator {

	TimeChartLineSummarizer totalizer;
	
	CategoryCompositeKey lastKey;
	
	/**
	 * @param totalizer
	 */
	public SummaryPresentationMediatorImpl(TimeChartLineSummarizer totalizer) {
		super();
		this.totalizer = totalizer;
	}

	@Override
	public void onRendered(TimeChartLine line) {
		lastKey = line.getKey();
	}
	
	@Override
	public boolean isControlBreak(CategoryCompositeKey key) {
		
		if (totalizer.containsTotal(lastKey)) {
			
		}
		
		return lastKey == null ? false : totalizer.isControlBreak(key);
	}
}
