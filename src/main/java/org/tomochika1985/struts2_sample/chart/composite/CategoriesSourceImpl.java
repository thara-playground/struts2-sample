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
package org.tomochika1985.struts2_sample.chart.composite;

import java.util.ArrayList;
import java.util.List;

import org.tomochika1985.struts2_sample.chart.Category;

/**
 * @author t_hara
 *
 */
public class CategoriesSourceImpl implements CategoriesSource {

	private List<Category> categories = new ArrayList<Category>();
	
	@Override
	public CategoriesSourceImpl add(Category c) {
		if (c != Category.ROOT) {
			this.categories.add(c);
		}
		return this;
	}
	
	@Override
	public List<Category> getCategories() {
		return categories;
	}
}
