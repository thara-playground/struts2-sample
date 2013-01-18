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

import java.util.ArrayList;
import java.util.List;

import org.tomochika1985.struts2_sample.chart.LocalMonth;
import org.tomochika1985.struts2_sample.chart.TimeChart;
import org.tomochika1985.struts2_sample.chart.TimeChartLine;
import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.composite.visitor.CategoryHierarchyKeyMatcher;
import org.tomochika1985.struts2_sample.chart.presenter.impl.CategoryPresentationMediatorImpl;
import org.tomochika1985.struts2_sample.chart.presenter.impl.TimeChartLinePresenterImpl;
import org.tomochika1985.struts2_sample.chart.presenter.impl.TimeChartQuentitiesPresenterImpl;

/**
 * @author t_hara
 *
 */
public class TimeChartPresentationFactory {

	TimeChart timeChart;
	
	CategoryPresentationMediator categoryMediator;
	LinePresentationListener linePresentationListener;
	
	/**
	 * 
	 */
	public TimeChartPresentationFactory(TimeChart timeChart) {
		this.timeChart = timeChart;
	}
	
	public TimeChartPresenter createTimeChartPresenter() {
		categoryMediator = createCategoryPresentationMediator();
		
		List<LocalMonth> targetMonths = timeChart.getTargetMonths();
		TimeChartPresenter result = new TimeChartPresenter(targetMonths);

		List<TimeChartLine> lines = timeChart.getLines();
		for (TimeChartLine line : lines) {
			TimeChartLinePresenter linePresenter = createTimeChartLinePresenter(line);
			result.addLinePresenter(linePresenter);
		}
		
		return result;
	}
	
	TimeChartLinePresenterImpl createTimeChartLinePresenter(TimeChartLine line) {
		CategoryCompositeKey key = line.getKey();
		
		CategoryHierarchyKeyMatcher matcher = new CategoryHierarchyKeyMatcher(key);
		List<CategoryComponent> hierachy = timeChart.getCategories().accept(matcher).getHierachy();
		
		List<TimeChartCategoryPresenter> categoryPresenters = new ArrayList<TimeChartCategoryPresenter>();
		for (CategoryComponent c : hierachy) {
			TimeChartCategoryPresenter p = createCategoryPresenter(c);
			categoryPresenters.add(p);
		}
		
		TimeChartQuentitiesPresenterImpl quentitiesPresenter = createQuentitiesPresenter(line);
		
		return new TimeChartLinePresenterImpl(line, categoryPresenters, quentitiesPresenter, linePresentationListener);
	}
	
	TimeChartCategoryPresenter createCategoryPresenter(CategoryComponent categoryComponent) {
		return new TimeChartCategoryPresenter(categoryComponent, categoryMediator);
	}
	
	TimeChartQuentitiesPresenterImpl createQuentitiesPresenter(TimeChartLine line) {
		return new TimeChartQuentitiesPresenterImpl(line);
	}
	
	/**
	 * @return
	 */
	protected  CategoryPresentationMediatorImpl createCategoryPresentationMediator() {
		return new CategoryPresentationMediatorImpl();
	}
	
	/**
	 * @param linePresentationListener the linePresentationListener to set
	 */
	public void setLinePresentationListener(
			LinePresentationListener linePresentationListener) {
		this.linePresentationListener = linePresentationListener;
	}
}
