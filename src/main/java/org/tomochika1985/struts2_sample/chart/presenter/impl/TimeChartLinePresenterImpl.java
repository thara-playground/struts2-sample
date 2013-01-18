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
package org.tomochika1985.struts2_sample.chart.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import org.tomochika1985.struts2_sample.chart.TimeChartLine;
import org.tomochika1985.struts2_sample.chart.presenter.LinePresentationListener;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartCategoryPresenter;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartLinePresenter;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartQuentitiesPresenter;

/**
 * @author t_hara
 *
 */
public class TimeChartLinePresenterImpl implements TimeChartLinePresenter {

	final TimeChartLine line;
	
	final List<TimeChartCategoryPresenter> categoryPresenters;
	
	final TimeChartQuentitiesPresenterImpl quentitiesPresenter;
	
	final LinePresentationListener listener;

	/**
	 * @param categoryPresenters
	 * @param quentitiesPresenter
	 */
	public TimeChartLinePresenterImpl(
			TimeChartLine line,
			List<TimeChartCategoryPresenter> categoryPresenters,
			TimeChartQuentitiesPresenterImpl quentitiesPresenter,
			LinePresentationListener listener) {
		super();
		this.line = line;
		this.categoryPresenters = new ArrayList<TimeChartCategoryPresenter>(categoryPresenters);
		this.quentitiesPresenter = quentitiesPresenter;
		this.listener = listener;
	}
	
	@Override
	public TimeChartLine getLine() {
		return line;
	}
	
	/**
	 * @return the categoryPresenters
	 */
	@Override
	public List<TimeChartCategoryPresenter> getCategoryPresenters() {
		return categoryPresenters;
	}
	
	/**
	 * @return the quentitiesPresenter
	 */
	@Override
	public TimeChartQuentitiesPresenter getQuentitiesPresenter() {
		if (listener != null) listener.onRendered(quentitiesPresenter.getLine());
		return quentitiesPresenter;
	}
}
