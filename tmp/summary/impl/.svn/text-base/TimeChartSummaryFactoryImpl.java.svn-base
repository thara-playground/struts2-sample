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
import java.util.Map.Entry;

import org.tomochika1985.struts2_sample.chart.TimeChartLine;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.presenter.PresentationListener;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartLinePresenter;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartPresenter;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartLineSummarizer;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartSummaryFactory;

/**
 * @author t_hara
 *
 */
public class TimeChartSummaryFactoryImpl implements
		TimeChartSummaryFactory {

	final TimeChartLineSummarizer summarizer;
	
	final Map<CategoryCompositeKey, TimeChartSummaryLinePresenterImpl> totalLinePresenters = 
			new HashMap<CategoryCompositeKey, TimeChartSummaryLinePresenterImpl>();

	/**
	 * @param summarizer
	 */
	public TimeChartSummaryFactoryImpl(TimeChartLineSummarizer summarizer) {
		super();
		this.summarizer = summarizer;
	}

	@Override
	public PresentationListener createSummaryPresentationMediator() {
		return new SummaryPresentationMediatorImpl(summarizer);
	}
	
	@Override
	public TimeChartLineSummarizer getSummarizer() {
		return summarizer;
	}
	
	@Override
	public void addSummary(TimeChartLine line,
			TimeChartLinePresenter linePresenter) {
		CategoryCompositeKey key = line.getKey();
		CategoryCompositeKey totalizeKey = summarizer.getTotalizeKey(key);
		if (totalizeKey != null) {
			TimeChartSummaryLinePresenterImpl summaryLinePresenter = totalLinePresenters.get(totalizeKey);
			if (summaryLinePresenter == null) {
				summaryLinePresenter = new TimeChartSummaryLinePresenterImpl();
				totalLinePresenters.put(totalizeKey, summaryLinePresenter);
			}
			summaryLinePresenter.add(linePresenter.getQuentitiesPresenter());
		}
	}
	
	@Override
	public void setupSummary(TimeChartPresenter presenter) {
		for (Entry<CategoryCompositeKey, TimeChartSummaryLinePresenterImpl> entry : totalLinePresenters.entrySet()) {
			presenter.putSummaryLinePresetenr(entry.getKey(), entry.getValue());
		}
	}

}
