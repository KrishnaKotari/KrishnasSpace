package com.krishna.jaasexample.ui;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.server.DefaultUIProvider;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * Custom vaadin servlet. Inspired from Gitrepo
 * 
 * https://github.com/mstahv/vaadin-cdi-jaas-jbossas-example/blob/workaround/src
 * /main/java/com/vaadin/cdi/example/Servlet.java
 * 
 * and CDIUIProvider
 * 
 * @author Krishna
 *
 */
@WebServlet(value = "/*", asyncSupported = true)
public class CustomVaadinServlet extends VaadinServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 228747325407920727L;

	public static final String AUTH_ORIGINALPAGE = "auth.originalpage";

	// Override the getUIClass method of the Default UI Provider
	private UIProvider uiProvider = new DefaultUIProvider() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8003660780488157843L;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.vaadin.server.DefaultUIProvider#getUIClass(com.vaadin.server.
		 * UIClassSelectionEvent)
		 */
		@Override
		public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {

			VaadinRequest request = event.getRequest();

			String url = parseUIMapping(request);
			// If url is login, return LoginUI class
			if (url.contains("login")) {
				return LoginUI.class;
			}

			// If url is an empty url then return Secure if user is an
			// authenticated else return login UI
			if (url.isEmpty()) {
				Principal principal = request.getUserPrincipal();
				if (null == principal) {

					return LoginUI.class;
				}
				return SecureUI.class;
			}
			// Return the secured UI
			if (url.contains("secured")) {
				return SecureUI.class;
			}

			return null;
		}

		// NOTE - Copied from CDIUIProvider
		private String parseUIMapping(VaadinRequest request) {
			return parseUIMapping(request.getPathInfo());
		}

		// NOTE - Copied from CDIUIProvider
		private String parseUIMapping(String requestPath) {
			if (requestPath != null && requestPath.length() > 1) {
				String path = requestPath;
				if (requestPath.endsWith("/")) {
					path = requestPath.substring(0, requestPath.length() - 1);
				}
				if (!path.contains("!")) {
					int lastIndex = path.lastIndexOf('/');
					return path.substring(lastIndex + 1);
				} else {
					int lastIndexOfBang = path.lastIndexOf('!');
					// strip slash with bank => /!
					String pathWithoutView = path.substring(0,
							lastIndexOfBang - 1);
					int lastSlashIndex = pathWithoutView.lastIndexOf('/');
					return pathWithoutView.substring(lastSlashIndex + 1);
				}
			}
			return "";
		}

	};

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();
		getService().addSessionInitListener(new SessionInitListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8279023207169735541L;

			@Override
			public void sessionInit(SessionInitEvent event)
					throws ServiceException {
				event.getSession().addUIProvider(uiProvider);

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.server.VaadinServlet#service(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	// NOTE - Copied from VAADIN CDI Example
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * Repository URL -
		 * https://github.com/mstahv/vaadin-cdi-jaas-jbossas-example
		 * /blob/workaround /src/main/java/com/vaadin/cdi/example/Servlet.java
		 */
		/*
		 * This haxi is only needed for app servers that for some reason lose
		 * request type on FORDARD.
		 * 
		 * Works (without): JBoss 7.1, Glassfish 4 Broken: Wildfly, TomEE
		 */
		if (request.getDispatcherType() == DispatcherType.FORWARD
				&& request.getRequestURI().contains("login")) {
			Object page = request
					.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
			request.getSession(true).setAttribute(AUTH_ORIGINALPAGE, page);
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", request.getRequestURI());
		} else {
			super.service(request, response);
		}
	}

}