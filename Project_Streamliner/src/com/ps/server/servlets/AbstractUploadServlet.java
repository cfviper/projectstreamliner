package com.ps.server.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public abstract class AbstractUploadServlet extends HttpServlet {
	protected abstract void showForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	protected abstract void handleSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	protected abstract void showRecord(long id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String action = req.getParameter("action");
	    if ("display".equals(action)) {
	        // don't know why GAE appends underscores to the query string
	        long id = Long.parseLong(req.getParameter("id").replace("_", ""));
	        showRecord(id, req, resp);
	    } else {
	        showForm(req, resp);
	    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    handleSubmit(req, resp);
	}
	
}