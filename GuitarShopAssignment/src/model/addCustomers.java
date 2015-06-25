package model;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addCustomers
 */
@WebServlet("/addCustomers")
public class addCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCustomers() {
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
		
		String CustomerFirstName = request.getParameter("firstname");
		String CustomerLastName = request.getParameter("lastname");
		String CustomerEmailID = request.getParameter("emailid");
		String CustomerID 	   = request.getParameter("customerid");
		String CustomerBillingAddressID = request.getParameter("billingaddressid");
		String CustomerPassword = request.getParameter("password");
		String CustomerShippingAddressID = request.getParameter("shippingaddressid");
		
		
		
		//System.out.println(CustomerFirstName);
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		
		try
		{
			BigDecimal bdBillingAddress = new BigDecimal(CustomerBillingAddressID);
			BigDecimal bdShippingAddress = new BigDecimal(CustomerShippingAddressID);
			//System.out.println("121212");
			
			model.Customer cust = new model.Customer();
			cust.setFirstName(CustomerFirstName);
			cust.setLastName(CustomerLastName);
			cust.setEmailAddress(CustomerEmailID);
			cust.setCustomerId(Long.parseLong(CustomerID));
			cust.setBillingAddressId(bdBillingAddress);
			cust.setPassword(CustomerPassword);
			cust.setShippingAddressId(bdShippingAddress);
			
			em.persist(cust);
			
			trans.commit();
		}
		catch(Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally
		{
			em.close();
		}
		
		getServletContext()
		.getRequestDispatcher("/postaddcustomer.jsp")
		.forward(request, response);
		
	}

}
