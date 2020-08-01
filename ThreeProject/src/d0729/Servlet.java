package d0729;

import java.io.IOException;

/*
 * Servlet 继承关系
 * servlet ==> GenriceServlet ==>HttpServlet==>自定义的Servlet
 */
public interface Servlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException;

}
