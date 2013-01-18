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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartLinePresenter;
import org.tomochika1985.struts2_sample.chart.presenter.summary.SummaryLinePresenterBuilder;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartSummaryLinesLocator;

/**
 * @author t_hara
 *
 */
public class TimeChartSummaryLinesLocatorImpl implements
		TimeChartSummaryLinesLocator {

	final Map<CategoryCompositeKey, List<TimeChartLinePresenter>> preseters = 
			new HashMap<CategoryCompositeKey, List<TimeChartLinePresenter>>();
	
	List<SummaryLinePresenterBuilder> builders = new ArrayList<SummaryLinePresenterBuilder>();
	
	public void addBuilder(SummaryLinePresenterBuilder builder) {
		builders.add(builder);
	}
	
	@Override
	public List<TimeChartLinePresenter> lookup(CategoryCompositeKey groupingKey) {
		return preseters.get(groupingKey);
	}
	
	@Override
	public void addSummaryLine(CategoryCompositeKey groupingKey, List<TimeChartLinePresenter> sources) {
		
		List<TimeChartLinePresenter> summaryLines = new ArrayList<TimeChartLinePresenter>();
		for (SummaryLinePresenterBuilder builder : builders) {
			TimeChartLinePresenter summaryLine = builder.build(groupingKey, sources);
			summaryLines.add(summaryLine);
		}
		
		if (summaryLines != null && !summaryLines.isEmpty()) {
			this.preseters.put(groupingKey, summaryLines);
		}
	}
}
