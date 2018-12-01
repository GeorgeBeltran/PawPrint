package databases;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SQLconnect")
public class SQLconnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Include the JDBC Driver for MySQL
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException( e );
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>SQL Link</title>");
        out.println("     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        
        Connection c = null; // Defined here for scope
        try {
//Change cs3220stu06 to your credentials
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu06";
        	//Change cs3220stu06 to your credentials        	
    		String username = "cs3220stu06";
    		//Change password to your credentials
    		String password = /* Your Password Here */ 	"0aO2oJo2";
    	
    		// Connect to the database
    		c = DriverManager.getConnection(url, username, password);
    		
    		// Create a statement object
    		Statement stmt = c.createStatement();
    		
    		// Use the statement object to execute a query, and get a result set
  //Change "Examples" to your table name
    		ResultSet rs = stmt.executeQuery("SELECT * FROM Examples");
    		out.println("<table class=\"table table-bordered table-striped table-hover\">");
//    		int count = 0;
    		out.println("  <tr>");
			out.println("    <th>First Name</th>");
			out.println("    <th>Last Name</th>");
			out.println("    <th>Weight</th>");
			out.println("    <th>Breed</th>");
			out.println("  </tr>");
    		while (rs.next()) {
    			
    			//Set + match your fields (firstname, lastname, weight, etc)
    			String firstname = rs.getString("firstname");
    			String lastname = rs.getString("lastname");
    			int weight = rs.getInt("weight");
    			String breed = rs.getString("breed");
    			
    			
    			
    			out.println("<tr>");//Row 2
    			out.println("<td>"+firstname+"</td>");
    			out.println("<td>"+lastname+"</td>");
    			out.println("<td>"+weight+"</td>");
    			out.println("<td>"+breed+"</td>");
    			out.println("</tr>");//End Row 2
    			
//    			out.println( "|"+firstname );
//    			out.println( "|"+lastname );
//    			out.println( "|"+weight+" lbs");
//    			out.println( "|"+breed);
//    			out.println("|");
//    			out.println("<hr>");
    			
    			
    		}out.println("</table>");
        }
        catch (SQLException e) {
        	throw new ServletException( e );
        }
        finally {
        	try {
        		if ( c != null ) c.close();
        	}
        	catch( SQLException e) {
        		throw new ServletException( e );
        	}
        }
        
        
        out.println("</div>");
        out.println("</body>");        
        out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}