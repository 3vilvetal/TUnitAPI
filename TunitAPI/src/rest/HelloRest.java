package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import system.sql.JdbcUtils;

//Plain old Java Object it does not extend as class or implements 
//an interface

//The class registers its methods for the HTTP GET request using the @GET annotation. 
//Using the @Produces annotation, it defines that it can deliver several MIME types,
//text, XML and HTML. 

//The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class HelloRest {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayPlainTextHello() {
		return "{ \"message\": \"Hello TUnit txt\"}";
	}
	
	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello TUnit XML" + "</hello>";
	}
	
	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello TUnit" + "</title>"
				+ "<body><h1>" + "Hello TUnit HTML NEW version !!!" + "</body></h1>" + "</html> ";
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(String track) throws JSONException {
 
		String result = "Track saved : " + track;
		System.out.println(result);
		
		JSONObject json = new JSONObject(track);
		JSONObject testResult = json.getJSONObject("result");
		
		JdbcUtils dbUtils = new JdbcUtils();
		dbUtils.insertResultsString(
				testResult.getString("suite"), 
				testResult.getString("name"), 
				testResult.getString("status"), 
				testResult.getString("result"), 
				testResult.getString("message"), 
				testResult.getString("expected"), 
				testResult.getString("actual"), 
				testResult.getString("error"));
		
		return Response.status(201).entity(result).build();
 
	}

} 
