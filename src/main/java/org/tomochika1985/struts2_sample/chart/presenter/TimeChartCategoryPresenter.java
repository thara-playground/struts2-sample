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

import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;

/**
 * @author t_hara
 *
 */
public class TimeChartCategoryPresenter {

	final CategoryComponent category;
	
	final CategoryPresentationMediator mediator;
	
	/**
	 * @param category
	 * @param mediator
	 */
	protected TimeChartCategoryPresenter(CategoryComponent category,
			CategoryPresentationMediator mediator) {
		super();
		this.category = category;
		this.mediator = mediator;
	}
	
	/**
	 * @return the category
	 */
	public CategoryComponent getCategory() {
		return category;
	}

	public boolean isAlreadyRendered() {
		return mediator.alreadyRendered(category);
	}
	
	public final String getStringValue() {
		String str = toString(category);
		mediator.onRendered(category);
		return str;
	}
	
	protected String toString(CategoryComponent category) {
		return category.getCategory().getCode();
	}
}
