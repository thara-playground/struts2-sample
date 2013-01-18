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

import java.util.List;

import org.apache.commons.lang.SystemUtils;
import org.junit.Test;
import org.tomochika1985.struts2_sample.chart.LocalMonth;
import org.tomochika1985.struts2_sample.chart.TimeChart;
import org.tomochika1985.struts2_sample.chart.TimeChartBuilder;
import org.tomochika1985.struts2_sample.chart.TimeChartSource;
import org.tomochika1985.struts2_sample.chart.action.SampleFacade;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesBuilderImpl;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartCategoryPresenter;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartLinePresenter;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartPresentationFactory;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartPresenter;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartQuentitiesPresenter;
import org.tomochika1985.struts2_sample.chart.presenter.summary.impl.TimeChartSummaryLinesLocatorImpl;
import org.tomochika1985.struts2_sample.chart.presenter.summary.total.TimeChartTotalLinesLocator;

/**
 * @author t_hara
 *
 */
public class TimeChartSummaryFacotryTest {

	@Test
	public void test() {
		
		List<TimeChartSource> sources = new SampleFacade().find();
		
		TimeChartSummarizer summarizer = new TimeChartSummarizer();
		summarizer.addSummarizeKey(CategoryCompositeKey.create("A", "A0"));
		summarizer.addSummarizeKey(CategoryCompositeKey.create("B", "A1"));
		
		TimeChartSummaryMediator mediator = new TimeChartSummaryMediator();
		
		TimeChartSummaryLinesLocatorImpl summaryLinesLocator = new TimeChartSummaryLinesLocatorImpl();
		summaryLinesLocator.addBuilder(new TimeChartTotalLinesLocator());
		
		TimeChartSummaryFacotry target = new TimeChartSummaryFacotry(summarizer, mediator, summaryLinesLocator);
		
		
		TimeChart timeChart = new TimeChartBuilder(sources).buildTimeChart(new CategoriesBuilderImpl());
		TimeChartPresentationFactory presentationFactory = new TimeChartPresentationFactory(timeChart);
		presentationFactory.setLinePresentationListener(mediator);

		
		TimeChartPresenter timeChartPresenter = presentationFactory.createTimeChartPresenter();
		TimeChartSummaryPresenter summaryPresenter = target.createSummaryPresenter(timeChart, timeChartPresenter);
	
		
		List<LocalMonth> targetMonths = timeChartPresenter.getTargetMonths();
		List<TimeChartLinePresenter> linePresenters = timeChartPresenter.getLinePresenters();
	
		StringBuilder actual = new StringBuilder();
		for (TimeChartLinePresenter p : linePresenters) {
			List<TimeChartCategoryPresenter> cpList = p.getCategoryPresenters();
			
			int categoryPadding = 0;
			for (TimeChartCategoryPresenter cp : cpList) {
				int level = cp.getCategory().getKey().getLevel();
				if (cp.isAlreadyRendered()) {
					actual.append(String.format("%-" + level + "s", "") + ",");
				} else {
					actual.append(cp.getStringValue() + ",");
				}
				categoryPadding += (level + 1);
			}
			TimeChartQuentitiesPresenter qp = p.getQuentitiesPresenter();
			for (LocalMonth m : targetMonths) {
				actual.append(qp.getQuentiryOf(m) + ",");
			}
			
			actual.deleteCharAt(actual.length() - 1).append(SystemUtils.LINE_SEPARATOR);
			
			if (summaryPresenter.isControlBreak()) {
				List<TimeChartLinePresenter> summaryLinePresenters = summaryPresenter.getSummaryLinePresenters();
				for (TimeChartLinePresenter summaryLine : summaryLinePresenters) {
					actual.append(String.format("%-" + categoryPadding + "s", ""));
					TimeChartQuentitiesPresenter summaryQuentities = summaryLine.getQuentitiesPresenter();
					for (LocalMonth m : targetMonths) {
						actual.append(summaryQuentities.getQuentiryOf(m) + ",");
					}
					actual.deleteCharAt(actual.length() - 1).append(" (summary)").append(SystemUtils.LINE_SEPARATOR);
				}
			}
		}
		
		System.out.println(actual);
	}

}
