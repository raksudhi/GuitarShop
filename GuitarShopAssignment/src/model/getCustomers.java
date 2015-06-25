package model;

import java.io.IOException;
import java.util.List;
import java.lang.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import mytools.DBUtil;

/**
 * Servlet implementation class getCustomers
 */
@WebServlet("/getCustomers")
public class getCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getCustomers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String tableInfo = "";
		
		List<Customer> DemoCustomers = displayCustomerNames();
		
		try
		{
			
			tableInfo += tableInfo += "<tr><th>Customer ID</th><th>Customer Name</th><th>Email Id</th></tr>";
			for(int i = 0; i < DemoCustomers.size(); i++)
			{
				
				tableInfo += "<tr><td>" + DemoCustomers.get(i).getCustomerId()
						+ "</th><th>" + "<a href='customerDetail?customerID="
						+ DemoCustomers.get(i).getCustomerId() + "'>"
						+ DemoCustomers.get(i).getFirstName() + " "
						+ DemoCustomers.get(i).getLastName() + "</th><th>"
						+ DemoCustomers.get(i).getEmailAddress() + "</td></tr>";

			}
			request.setAttribute("tableInfo", tableInfo);
		}
		catch(Exception e)
		{
			request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
		}
		
		getServletContext()
		.getRequestDispatcher("/displaycustomers.jsp")
		.forward(request, response);
		
	}
	
	protected static List<Customer> displayCustomerNames()
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM Customer c";
		TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
		
		List<Customer> i = null;
		try
		{
		
			i = q.getResultList();
			if(i == null || i.isEmpty())
			{
				i = null;
			}
		}
		catch(NoResultException e)
		{
			System.out.println(e);
		}
		
		finally 
		{
			em.close();
		}
		
		return i;
	}

}
