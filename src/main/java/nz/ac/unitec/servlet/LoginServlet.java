package nz.ac.unitec.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nz.ac.unitec.dao.proxy.UserDAOProxy;
import nz.ac.unitec.vo.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		List<String> info = new ArrayList<String>();
		if (name == null || "".equals(name)) {
			info.add("user name cannot be empty");
			System.out.println("user name cannot be empty");
		}

		if (password == null || "".equals(password)) {
			info.add("password cannot be empty");
			System.out.println("password cannot be empty");
		}
		if (info.size() == 0) {
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			UserDAOProxy userDAOProxy = new UserDAOProxy();
			try {

				if (userDAOProxy.findLogin(user)) {
					info.add("user login sucessfully welcome" + user.getName() + "!");
				} else {
					info.add("user login failed, invaild user name or password");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("info", info);
		request.getRequestDispatcher("Login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
