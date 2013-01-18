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

import java.util.HashSet;
import java.util.Set;

import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.presenter.CategoryPresentationMediator;

/**
 * @author t_hara
 *
 */
public class CategoryPresentationMediatorImpl implements CategoryPresentationMediator{

	final Set<CategoryCompositeKey> alreadyRenderesCategories = new HashSet<CategoryCompositeKey>();
	
	@Override
	public void onRendered(CategoryComponent category) {
		alreadyRenderesCategories.add(category.getKey());
	}

	@Override
	public boolean alreadyRendered(CategoryComponent category) {
		CategoryCompositeKey key = category.getKey();
		return alreadyRenderesCategories.contains(key);
	}
}
