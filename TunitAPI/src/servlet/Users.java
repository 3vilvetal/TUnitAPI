package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import system.FileCounter;

/**
 * Servlet implementation class Users
 */
@WebServlet("/Users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int count;
	FileCounter fileCounter;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set a cookie for the user, so that the counter does not increate
	    // every time the user press refresh
	    HttpSession session = request.getSession(true);
	    // Set the session valid for 5 secs
	    session.setMaxInactiveInterval(5);
	    response.setContentType("text/plain");
	    
	    PrintWriter out = response.getWriter();
	    if (session.isNew()) {
	      count ++;
	    }
	    out.println("This site has been accessed " + count + " times.");
	}
	
	@Override
	public void init() throws ServletException {
	    fileCounter = new FileCounter();
	    try {
	    	count = fileCounter.getCount();
	    } catch (Exception e) {
	    	getServletContext().log("An exception occurred in FileCounter", e);
	    	throw new ServletException("An exception occurred in FileCounter"
	    			+ e.getMessage());
	    }
	}
	  
	public void destroy() {
	    super.destroy();
		    try {
		    	fileCounter.save(count);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
