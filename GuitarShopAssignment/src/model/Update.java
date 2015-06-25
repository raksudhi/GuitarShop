package model;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		
		String customerID = request.getParameter("customerid");
		
		String tableInfo = "";
		
		List<Customer> DemoCustomers = displayCustomerNames(customerID);
		
		request.setAttribute("customerid", DemoCustomers.get(0).getCustomerId());
		request.setAttribute("customerfirstname", DemoCustomers.get(0).getFirstName());
		request.setAttribute("customerlastname", DemoCustomers.get(0).getLastName());
		request.setAttribute("customeremailid", DemoCustomers.get(0).getEmailAddress());
		request.setAttribute("customerpassword", DemoCustomers.get(0).getPassword());
		request.setAttribute("customershippingid", DemoCustomers.get(0).getShippingAddressId());
		request.setAttribute("customerbillingid", DemoCustomers.get(0).getBillingAddressId());
		
		
		
		getServletContext()
		.getRequestDispatcher("/update.jsp")
		.forward(request, response);
	}
	
	
	protected static List<Customer> displayCustomerNames(String customerID)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT c FROM Customer c where c.customerId = :customerID";
		TypedQuery<Customer> q = em.createQuery(qString, Customer.class);
		q.setParameter("customerID", Long.parseLong(customerID));
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
