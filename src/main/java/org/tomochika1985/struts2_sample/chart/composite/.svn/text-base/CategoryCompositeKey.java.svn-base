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
import java.util.Arrays;
import java.util.List;

import org.tomochika1985.struts2_sample.chart.Category;

public class CategoryCompositeKey {
	
	public static CategoryCompositeKey ROOT = new CategoryCompositeKey(Category.ROOT);
	
	public static CategoryCompositeKey create(String key, String... keys) {
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(key, key));
		for (String k : keys) {
			categories.add(new Category(k, k));
		}
		return new CategoryCompositeKey(categories);
	}
	
	final String key;
	
	final int level;
	
	final List<String> keys;
	
	final List<Category> categories;
	
	public CategoryCompositeKey(Category... categories) {
		this(Arrays.asList(categories));
	}
	
	/**
	 * 
	 */
	public CategoryCompositeKey(List<Category> categories) {
		assert categories != null && !categories.isEmpty();
		
		this.level = categories.size();
		
		StringBuilder sb = new StringBuilder();
		List<String> keys = new ArrayList<String>();
		int depth = 0;
		for (Category c : categories) {
			String k = getKeyFrom(c);
			sb.append(k);
			keys.add((depth++) + k);
		}
		this.key = sb.toString();
		this.keys = keys;
		
		this.categories = new ArrayList<Category>(categories);
	}

	/**
	 * @param c
	 * @return
	 */
	protected String getKeyFrom(Category c) {
		return c.getCode();
	}
	
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof CategoryCompositeKey)) return false;
		if (this == obj) return true;
		return getKey().equals(CategoryCompositeKey.class.cast(obj).getKey());
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * @return the keys
	 */
	protected List<String> getKeys() {
		return keys;
	}
	
	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return new ArrayList<Category>(this.categories);
	}
	
	public boolean containsParentsOf(CategoryCompositeKey key) {
		return key.getKeys().containsAll(getKeys());
	}
	
	public boolean isChildOf(CategoryCompositeKey key) {
		return getKeys().containsAll(key.getKeys()) && getLevel() == key.getLevel() + 1;
	}
	
	@Override
	public String toString() {
		return getKey();
	}
}