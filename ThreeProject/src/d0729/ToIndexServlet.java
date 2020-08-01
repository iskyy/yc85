package d0729;


public class ToIndexServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  {
		// 实现响应重定向
		response.sendRedirect("/photo/index.html");

	}



}
