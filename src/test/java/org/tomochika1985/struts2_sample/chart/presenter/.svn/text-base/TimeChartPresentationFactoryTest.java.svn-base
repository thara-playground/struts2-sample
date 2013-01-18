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
package org.tomochika1985.struts2_sample.chart.presenter;

import java.util.List;

import org.apache.commons.lang.SystemUtils;
import org.junit.Test;
import org.tomochika1985.struts2_sample.chart.LocalMonth;
import org.tomochika1985.struts2_sample.chart.TimeChart;
import org.tomochika1985.struts2_sample.chart.TimeChartBuilder;
import org.tomochika1985.struts2_sample.chart.TimeChartSource;
import org.tomochika1985.struts2_sample.chart.action.SampleFacade;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesBuilderImpl;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

/**
 * @author t_hara
 *
 */
public class TimeChartPresentationFactoryTest {

	@Test
	public void caseOfNotSummarize() {
		
		List<TimeChartSource> sources = new SampleFacade().find();
		
		TimeChart timeChart = new TimeChartBuilder(sources).buildTimeChart(new CategoriesBuilderImpl());
		TimeChartPresentationFactory target = new TimeChartPresentationFactory(timeChart);
		
		TimeChartPresenter timeChartPresenter = target.createTimeChartPresenter();
		
		List<LocalMonth> targetMonths = timeChartPresenter.getTargetMonths();
		assertThat(targetMonths.size(), is(3));
		
		List<TimeChartLinePresenter> linePresenters = timeChartPresenter.getLinePresenters();
		assertThat(linePresenters.size(), is(6));
		
		TimeChartLinePresenter linePresenter;
		TimeChartQuentitiesPresenter quentitiesPresenter;
		
		linePresenter = linePresenters.get(0);
		assertThat(linePresenter.getCategoryPresenters().size(), is(3));
		
		assertThat(linePresenter.getCategoryPresenters().get(0).getStringValue(), is("A"));
		assertThat(linePresenter.getCategoryPresenters().get(1).getStringValue(), is("A0"));
		assertThat(linePresenter.getCategoryPresenters().get(2).getStringValue(), is("A00"));
		
		quentitiesPresenter = linePresenter.getQuentitiesPresenter();
		assertThat(quentitiesPresenter.getQuentiryOf(new LocalMonth("201201", "yyyyMM")).getInteger(), is(1));
		assertThat(quentitiesPresenter.getQuentiryOf(new LocalMonth("201202", "yyyyMM")).getInteger(), is(2));
		assertThat(quentitiesPresenter.getQuentiryOf(new LocalMonth("201203", "yyyyMM")).getInteger(), is(3));

		StringBuilder expect = new StringBuilder();
		expect.append("A,A0,A00,1,2,3").append(SystemUtils.LINE_SEPARATOR);
		expect.append("A,A0,A01,4,5,6").append(SystemUtils.LINE_SEPARATOR);
		expect.append("A,A1,A00,7,8,9").append(SystemUtils.LINE_SEPARATOR);
		expect.append("B,A0,A00,11,12,13").append(SystemUtils.LINE_SEPARATOR);
		expect.append("B,A1,A00,14,15,16").append(SystemUtils.LINE_SEPARATOR);
		expect.append("B,A1,A01,17,18,19").append(SystemUtils.LINE_SEPARATOR);
		
		StringBuilder actual = new StringBuilder();
		for (TimeChartLinePresenter p : linePresenters) {
			List<TimeChartCategoryPresenter> cpList = p.getCategoryPresenters();
			for (TimeChartCategoryPresenter cp : cpList) {
				actual.append(cp.getStringValue() + ",");
			}
			TimeChartQuentitiesPresenter qp = p.getQuentitiesPresenter();
			for (LocalMonth m : targetMonths) {
				actual.append(qp.getQuentiryOf(m) + ",");
			}
			actual.deleteCharAt(actual.length() - 1).append(SystemUtils.LINE_SEPARATOR);
		}
		
//		System.out.print(actual);
		assertThat(actual.toString(), is(expect.toString()));
	}
	
	@Test
	public void testTraceingAlreadyRenderedCategory() {
		
		List<TimeChartSource> sources = new SampleFacade().find();
		TimeChart timeChart = new TimeChartBuilder(sources).buildTimeChart(new CategoriesBuilderImpl());
		TimeChartPresentationFactory target = new TimeChartPresentationFactory(timeChart);
		
		TimeChartPresenter timeChartPresenter = target.createTimeChartPresenter();
		
		List<LocalMonth> targetMonths = timeChartPresenter.getTargetMonths();
		List<TimeChartLinePresenter> linePresenters = timeChartPresenter.getLinePresenters();
		
		StringBuilder expect = new StringBuilder();
		expect.append("A,A0,A00,1,2,3").append(SystemUtils.LINE_SEPARATOR);
		expect.append(" ,  ,A01,4,5,6").append(SystemUtils.LINE_SEPARATOR);
		expect.append(" ,A1,A00,7,8,9").append(SystemUtils.LINE_SEPARATOR);
		expect.append("B,A0,A00,11,12,13").append(SystemUtils.LINE_SEPARATOR);
		expect.append(" ,A1,A00,14,15,16").append(SystemUtils.LINE_SEPARATOR);
		expect.append(" ,  ,A01,17,18,19").append(SystemUtils.LINE_SEPARATOR);
		
		StringBuilder actual = new StringBuilder();
		for (TimeChartLinePresenter p : linePresenters) {
			List<TimeChartCategoryPresenter> cpList = p.getCategoryPresenters();
			for (TimeChartCategoryPresenter cp : cpList) {
				if (cp.isAlreadyRendered()) {
					actual.append(String.format("%-" + cp.category.getKey().getLevel() + "s", "") + ",");
				} else {
					actual.append(cp.getStringValue() + ",");
				}
			}
			TimeChartQuentitiesPresenter qp = p.getQuentitiesPresenter();
			for (LocalMonth m : targetMonths) {
				actual.append(qp.getQuentiryOf(m) + ",");
			}
			actual.deleteCharAt(actual.length() - 1).append(SystemUtils.LINE_SEPARATOR);
		}
		
//		System.out.print(actual);
		assertThat(actual.toString(), is(expect.toString()));
	}
	
//	@Test
//	public void caseOfTotalize() {
//		
//		TimeChartLineSummarizerImpl summarizer = new TimeChartLineSummarizerImpl();
//		CategoryCompositeKey breakKey = 
//				new CategoryCompositeKey(new Category("B",""), new Category("A1",""), new Category("A00",""));
//		
//		summarizer.putBreakWithTotalizeKeys(breakKey, CategoryCompositeKey.ROOT);
//		
//		List<TimeChartSource> sources = new SampleFacade().find();
//		
//		TimeChart timeChart = new TimeChartBuilder(sources).buildTimeChart(new CategoriesBuilderImpl());
//		TimeChartSummaryFactory summaryFactory = new TimeChartSummaryFactoryImpl(summarizer);;
//		TimeChartPresentationFactory target = new TimeChartPresentationFactory(timeChart, summaryFactory);
//		
//		TimeChartPresenter timeChartPresenter = target.buildTimeChartPresenter();
//		List<LocalMonth> targetMonths = timeChartPresenter.getTargetMonths();
//		List<TimeChartLinePresenter> linePresenters = timeChartPresenter.getLinePresenters();
//	
//		StringBuilder actual = new StringBuilder();
//		for (TimeChartLinePresenter p : linePresenters) {
//			List<TimeChartCategoryPresenter> cpList = p.getCategoryPresenters();
//			
//			int categoryPadding = 0;
//			for (TimeChartCategoryPresenter cp : cpList) {
//				int level = cp.category.getKey().getLevel();
//				if (cp.isAlreadyRendered()) {
//					actual.append(String.format("%-" + level + "s", "") + ",");
//				} else {
//					actual.append(cp.getStringValue() + ",");
//				}
//				categoryPadding += (level + 1);
//			}
//			TimeChartQuentitiesPresenter qp = p.getQuentitiesPresenter();
//			for (LocalMonth m : targetMonths) {
//				actual.append(qp.getQuentiryOf(m) + ",");
//			}
//			
//			actual.deleteCharAt(actual.length() - 1).append(SystemUtils.LINE_SEPARATOR);
//			
//			if (p.isSummarizeOfNextLine()) {
//				actual.append(String.format("%-" + categoryPadding + "s", "") + ",");
//				TimeChartSummaryLinePresenter summary = 
//						timeChartPresenter.getSummaryLinePresenter(p.getLine().getKey());
//				System.out.println(p.getLine().getKey());
//				assertNotNull(summary);
//				for (LocalMonth m : targetMonths) {
//					actual.append(summary.getQuentiryOf(m) + ",");
//				}
//				
//				actual.deleteCharAt(actual.length() - 1).append(SystemUtils.LINE_SEPARATOR);
//			}
//		}
//		
//		System.out.println(actual);
//	}

}
