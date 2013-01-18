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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.tomochika1985.struts2_sample.chart.TimeChart;
import org.tomochika1985.struts2_sample.chart.TimeChartLine;
import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartLinePresenter;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartPresenter;
import org.tomochika1985.struts2_sample.chart.presenter.summary.impl.ControlBraekResolverImpl;
import org.tomochika1985.struts2_sample.chart.presenter.summary.impl.TimeChartSummaryPresenterImpl;

/**
 * @author t_hara
 *
 */
public class TimeChartSummaryFacotry {

	TimeChartSummarizer summarizer;
	
	TimeChartSummaryMediator mediator;
	
	TimeChartSummaryLinesLocator summaryLinesLocator;
	
	/**
	 * @param summarizer
	 * @param mediator
	 * @param summaryLinesLocator
	 */
	public TimeChartSummaryFacotry(TimeChartSummarizer summarizer,
			TimeChartSummaryMediator mediator,
			TimeChartSummaryLinesLocator summaryLinesLocator) {
		super();
		this.summarizer = summarizer;
		this.mediator = mediator;
		this.summaryLinesLocator = summaryLinesLocator;
	}

	TimeChartSummaryPresenter createSummaryPresenter(TimeChart timeChart, TimeChartPresenter timeChartPresenter) {
		
		CategoryComponent category = timeChart.getCategories();
		ControlBreakResolver controlBraekResolver = createControlBreakResolver(category);
		
		List<TimeChartLinePresenter> linePresenters = timeChartPresenter.getLinePresenters();
		
		Map<CategoryCompositeKey, List<TimeChartLinePresenter>> presenters = 
				new LinkedHashMap<CategoryCompositeKey, List<TimeChartLinePresenter>>();  
		
		for (TimeChartLinePresenter linePresenter : linePresenters) {
			TimeChartLine line = linePresenter.getLine();
			CategoryCompositeKey key = line.getKey();
			if (summarizer.isTargetSummarize(key)) {
				CategoryCompositeKey groupingKey = controlBraekResolver.toGroupingKey(key);
				if (groupingKey != null) {
					List<TimeChartLinePresenter> lines = presenters.get(groupingKey);
					if (lines == null) {
						lines = new ArrayList<TimeChartLinePresenter>();
					}
					lines.add(linePresenter);
					presenters.put(groupingKey, lines);	
				}
			}
		}
		
		for (Entry<CategoryCompositeKey, List<TimeChartLinePresenter>> entry : presenters.entrySet()) {
			CategoryCompositeKey groupingKey = entry.getKey();
			List<TimeChartLinePresenter> lines = entry.getValue();
			summaryLinesLocator.addSummaryLine(groupingKey, lines);
		}
		
		return new TimeChartSummaryPresenterImpl(controlBraekResolver, summaryLinesLocator, mediator);
	}

	/**
	 * @param category
	 * @return
	 */
	protected ControlBraekResolverImpl createControlBreakResolver(CategoryComponent category) {
		return new ControlBraekResolverImpl(category, summarizer);
	}
}
