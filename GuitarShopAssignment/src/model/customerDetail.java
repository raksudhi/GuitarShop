package model;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class customerDetail
 */
@WebServlet("/customerDetail")
public class customerDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customerDetail() {
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
		String customerID = request.getParameter("customerID");
		System.out.println(customerID);
		String tableInfo = "";
		
		
		List<Customer> CustomerName = displayCustomerNames(customerID);
		List<Address> AddressOfCustomer = displayAddress(customerID);
		
		List<Order> OrderInfo = displayOrderInfo(customerID);
	//	System.out.println(DCustomers);
		if(OrderInfo == null || AddressOfCustomer == null)
		{
			try
			{
				System.out.println("12121287889");
				tableInfo +="<tr><th>Customer Name</th><th>Customer Email Id</th></tr>";
				
				tableInfo += "<tr><td>"+ "<a href='deleteCustomer?customerID=" +  CustomerName.get(0).getCustomerId()+ "'>" + CustomerName.get(0).getFirstName()+ " " + CustomerName.get(0).getLastName() + "</th><th>" + CustomerName.get(0).getEmailAddress() +"</td></tr>";
				
				request.setAttribute("tableInfo", tableInfo);
			}
			catch(Exception e)
			{
				request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
			}
			getServletContext()
			.getRequestDispatcher("/displayNoInfo.jsp")
			.forward(request, response);
		}
		else
		{
			try
			{
				
				System.out.println("");
				tableInfo +="<tr><th>Customer Name</th><th>Customer Address</th><th>Customer City</th><th>Customer State</th><th>Customer ZipCode</th><th>Customer Phone Number</th><th>Order Date</th><th>Ship Amount</th><th>Order ID</th></tr>";
			
					
				tableInfo += "<tr><td>"+ CustomerName.get(0).getFirstName()+ " " + CustomerName.get(0).getLastName() + "</th><th>" + AddressOfCustomer.get(0).getLine1() + " " + AddressOfCustomer.get(0).getLine2() +  "</th><th>" +
						AddressOfCustomer.get(0).getCity() + "</th><th>" + AddressOfCustomer.get(0).getState() + "</th><th>" +  AddressOfCustomer.get(0).getZipCode() + "</th><th>" +  AddressOfCustomer.get(0).getPhone() +  "</th><th>" + 
						OrderInfo.get(0).getOrderDate() + "</th><th>" + OrderInfo.get(0).getShipAmount() + "</th><th>" + "<a href='OrderDetail?orderID=" + OrderInfo.get(0).getOrderId() + "'>" + OrderInfo.get(0).getOrderId() +"</td></tr>";
				
			
				request.setAttribute("tableInfo", tableInfo);
				request.setAttribute("Customer", CustomerName.get(0));
			}
			catch(Exception e)
			{
				request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
			}
		
		
			getServletContext()
			.getRequestDispatcher("/displayCustomerDetails.jsp")
			.forward(request, response);
		}
	}
	protected static List<Customer> displayCustomerNames(String customerID)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT d from Customer d where d.customerId = :customerID ";
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
		System.out.println(i);
		return i;
	}

		
	protected static List<Address> displayAddress(String customerID)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT a FROM Address a where a.customer.customerId = :customerID ";
		TypedQuery<Address> q = em.createQuery(qString, Address.class);
		q.setParameter("customerID", Long.parseLong(customerID));
		List<Address> a = null;
		try
		{
		
			a = q.getResultList();
			if(a == null || a.isEmpty())
			{
				a = null;
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
		//System.out.println(a);
		return a;
	}
	
	protected static List<Order> displayOrderInfo(String customerID)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT o FROM Order o where o.customer.customerId = :customerID ";
		TypedQuery<Order> q = em.createQuery(qString, Order.class);
		q.setParameter("customerID", Long.parseLong(customerID));
		List<Order> o = null;
		try
		{
		
			o = q.getResultList();
			if(o == null || o.isEmpty())
			{
				o = null;
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
		//System.out.println(a);
		return o;
	}
	
}
