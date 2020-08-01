package d0729;


public class PostServlet extends HttpServlet  {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String name= request.getParamter("name");
		response.getWriter().append("post, name=" + name);
	}


}
