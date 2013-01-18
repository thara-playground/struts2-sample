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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.tomochika1985.struts2_sample.chart.Category;

/**
 * @author t_hara
 *
 */
public class CategoriesBuilderImpl implements CategoriesBuilder {

	int depth;
	
	@Override
	public CategoryComponent buildComponent(List<CategoriesSource> sources) {
		CategoryComposite root = new CategoryComposite(Category.ROOT, CategoryCompositeKey.ROOT);
		List<CategoryComponent> children = buildComponents(sources);

		for (CategoryComponent child : children) {
			root.addChild(child);
		}
		return root;
	}
	
	List<CategoryComponent> buildComponents(List<CategoriesSource> sources) {

		this.depth = sources.get(0).getCategories().size();
		
		List<CategoryComponent> results = new ArrayList<CategoryComponent>();
		Map<CategoryCompositeKey, AbstractCategoryComponent> components = 
				new HashMap<CategoryCompositeKey, AbstractCategoryComponent>();
		
		for (Entry<CategoryCompositeKey, List<Category>> entry : createCategoryMap(sources).entrySet()) {
			CategoryCompositeKey key = entry.getKey();
			
			List<Category> categories = entry.getValue();
			AbstractCategoryComponent newComponent = createComponent(key, categories);
			
			if (key.getLevel() == 1) {
				components.put(key, newComponent);
				results.add(newComponent);
			} else {
				for (CategoryCompositeKey parentKey : components.keySet()) {
					if (key.isChildOf(parentKey)) {
						AbstractCategoryComponent parent = components.get(parentKey);
						parent.addChild(newComponent);
						break;
					}
				}
				components.put(key, newComponent);
			}
		}
		
		return results;
	}

	/**
	 * @param key
	 * @param category
	 * @return
	 */
	private AbstractCategoryComponent createComponent(CategoryCompositeKey key, List<Category> categories) {
		Category category = categories.get(categories.size() - 1);
		
		return (key.getLevel() == depth) ? 
				new CategoryLeaf(category, key) : new CategoryComposite(category, key);
	}

	/**
	 * @param sources
	 * @return
	 */
	Map<CategoryCompositeKey, List<Category>> createCategoryMap(
			List<CategoriesSource> sources) {
		Map<CategoryCompositeKey, List<Category>> categoryMap = 
				new LinkedHashMap<CategoryCompositeKey, List<Category>>();
		
		for (CategoriesSource src : sources) {
			List<Category> categories = src.getCategories();
			List<List<Category>> hierarchy = createHierarchy(categories);
			for (List<Category> list : hierarchy) {
				CategoryCompositeKey key = createKey(list);
				if (!categoryMap.containsKey(key)) {
					categoryMap.put(key, list);
				}
			}
		}
		return categoryMap;
	}
	
	List<List<Category>> createHierarchy(List<Category> categories) {
		
		List<List<Category>> hierarchy = new ArrayList<List<Category>>();

		Map<Integer, List<Category>> depthMap = new LinkedHashMap<Integer, List<Category>>();
		
		int depth = categories.size();
		for (int i = 0; i < depth; i++) {
			Category c = categories.get(i);

			List<Category> parent = depthMap.get(i - 1);
			if (parent == null) {
				parent = new ArrayList<Category>();
			} else {
				parent = new ArrayList<Category>(parent);
			}
			parent.add(c);
			
			depthMap.put(i, parent);
		}
		
		Set<Entry<Integer, List<Category>>> depthEntries = depthMap.entrySet();
		for (Entry<Integer, List<Category>> entry : depthEntries) {
			hierarchy.add(entry.getValue());
		}
		
		return hierarchy;
	}
	
	protected CategoryCompositeKey createKey(List<Category> categories) {
		return new CategoryCompositeKey(categories);
	}
}
