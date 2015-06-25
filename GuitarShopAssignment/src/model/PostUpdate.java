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
 * Servlet implementation class PostUpdate
 */
@WebServlet("/PostUpdate")
public class PostUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostUpdate() {
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
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		
		
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
			
			
			
			trans.begin();

			
			em.merge(cust);
			
			trans.commit();
		}
	
		finally
		{
			em.close();
		}
		
		getServletContext()
		.getRequestDispatcher("/postupdate.jsp")
		.forward(request, response);
		
	}
	

}
