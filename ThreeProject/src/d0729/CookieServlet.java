package d0729;


public class CookieServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  {
		// 浏览器发送Cookie 值name=wusong
		Cookie cookie=new Cookie("name","wusong");
		cookie.setMax(60 * 60 * 24);
		cookie=new Cookie("sex","0");
		cookie.setMax(60 * 60 * 24);
		response.addCookie(cookie);
		response.getWriter().append("cookie");

	}



}
