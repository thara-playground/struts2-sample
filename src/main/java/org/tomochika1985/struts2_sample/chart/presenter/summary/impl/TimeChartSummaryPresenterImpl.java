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

import java.util.List;

import org.tomochika1985.struts2_sample.chart.TimeChartLine;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartLinePresenter;
import org.tomochika1985.struts2_sample.chart.presenter.summary.ControlBreakResolver;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartSummaryLinesLocator;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartSummaryMediator;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartSummaryPresenter;

/**
 * @author t_hara
 *
 */
public class TimeChartSummaryPresenterImpl implements TimeChartSummaryPresenter {

	ControlBreakResolver controlBraekResolver;
	
	TimeChartSummaryLinesLocator summaryLinesLocator;
	
	TimeChartSummaryMediator mediator;
	
	/**
	 * @param controlBraekResolver
	 * @param summaryLinesLocator
	 * @param mediator
	 */
	public TimeChartSummaryPresenterImpl(
			ControlBreakResolver controlBraekResolver,
			TimeChartSummaryLinesLocator summaryLinesLocator,
			TimeChartSummaryMediator mediator) {
		super();
		this.controlBraekResolver = controlBraekResolver;
		this.summaryLinesLocator = summaryLinesLocator;
		this.mediator = mediator;
	}


	@Override
	public boolean isControlBreak() {
		return controlBraekResolver.isControlBreak(getControlBreakKey());
	}


	@Override
	public List<TimeChartLinePresenter> getSummaryLinePresenters() {
		CategoryCompositeKey groupingKey = controlBraekResolver.toGroupingKey(getControlBreakKey());
		return summaryLinesLocator.lookup(groupingKey);
	}
	
	/**
	 * @return
	 */
	private CategoryCompositeKey getControlBreakKey() {
		TimeChartLine lastLine = mediator.getLastLine();
		if (lastLine == null) return null;
		return lastLine.getKey();
	}

}
