package com.krishna.vaadin.grid;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

/**
 * My Custom View Provider, Servs views that implement {@link MyView} interface.
 * If the {@link MyView#isCached()} property is set to true then the new
 * instance object is cached and serviced every time
 * 
 * @author Krishna
 *
 */
public class CachedViewProvider implements ViewProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = -205063630491757618L;

	private final Map<String, Class<? extends MyView>> registeredViews;

	private final Map<String, MyView> views;

	public CachedViewProvider(Map<String, Class<? extends MyView>> myViews) {

		if (null == myViews || myViews.isEmpty()) {
			throw new IllegalStateException("No views are registered");
		}
		registeredViews = Collections.unmodifiableMap(myViews);
		views = new ConcurrentHashMap<>();
	}

	@Override
	public String getViewName(String navigationState) {
		if (null == navigationState || navigationState.isEmpty()) {
			return null;
		}
		if (navigationState.endsWith("/")) {
			return navigationState.substring(0, navigationState.length() - 1);
		}
		return navigationState;
	}

	@Override
	public View getView(String viewName) {

		MyView requestedView = views.get(viewName);
		if (null != requestedView) {
			return requestedView;
		}
		try {
			requestedView = registeredViews.get(viewName).newInstance();
			if (requestedView.isCached()) {
				views.put(viewName, requestedView);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return requestedView;
	}

	/**
	 * Clears the cache
	 */
	public void clearCache() {
		views.clear();
	}

	/**
	 * Removes the specific view from Cache
	 * 
	 * @param viewName
	 */
	public void removeViewFromCache(String viewName) {

		views.remove(viewName);

	}

}
