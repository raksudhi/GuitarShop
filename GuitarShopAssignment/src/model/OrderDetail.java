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
 * Servlet implementation class OrderDetail
 */
@WebServlet("/OrderDetail")
public class OrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetail() {
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
		String orderID = request.getParameter("orderID");

		List<OrderItem> orderitems = orderItemDetails(orderID);
		
		Product product = orderitems.get(0).getProduct();
		
		List<Product> products = productDetails(product.getProductId());
		
		
		try
		{
			
			tableInfo += tableInfo += "<tr><th>Order Item ID</th><th>Product Name</th><th>Quantity</th><th>Discount Amount</th></tr>";
			
				tableInfo += "<tr><td>"+ orderitems.get(0).getItemId()  + "</th><th>" + products.get(0).getProductName() + " " + 
			 "</th><th>" + orderitems.get(0).getQuantity() + "</th><th>" + orderitems.get(0).getDiscountAmount() + "</td></tr>";
			
			request.setAttribute("tableInfo", tableInfo);
		}
		catch(Exception e)
		{
			request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
		}
		
		getServletContext()
		.getRequestDispatcher("/displayroderdetails.jsp")
		.forward(request, response);
	}
	
	

	/*protected static List<Order> orderDetails(String orderID)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT o FROM Order o where o.orderId = :orderID";
		TypedQuery<Order> q = em.createQuery(qString, Order.class);
		q.setParameter("orderID", Long.parseLong(orderID));
		List<Order> i = null;
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
	}*/
	
	
	protected static List<OrderItem> orderItemDetails(String orderID)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT o FROM OrderItem o where o.order.orderId = :orderID";
		TypedQuery<OrderItem> q = em.createQuery(qString, OrderItem.class);
		q.setParameter("orderID", Long.parseLong(orderID));
		List<OrderItem> i = null;
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
	
	
	protected static List<Product> productDetails(long productid)
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT p FROM Product p where p.productId = :productid";
		TypedQuery<Product> q = em.createQuery(qString, Product.class);
		q.setParameter("productid", productid);
		List<Product> i = null;
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
