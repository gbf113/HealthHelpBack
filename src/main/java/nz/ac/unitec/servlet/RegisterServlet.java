package nz.ac.unitec.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nz.ac.unitec.dao.proxy.UserDAOProxy;
import nz.ac.unitec.vo.User;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String result = null;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) request.getInputStream(), "utf-8"));

			StringBuffer sb = new StringBuffer("");
			String line;
			while ((line = br.readLine()) != null) {				sb.append(line);
			}
			br.close();
			result = sb.toString();

			JSONArray jsonReadArray;
			JSONObject jsonReadObj;

			jsonReadObj = JSONObject.fromObject(result);
			String id = jsonReadObj.get("param1").toString(); // etRegisterID
			String pwd = jsonReadObj.get("param2").toString(); // etRegisterPWD
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			
			User user = new User();
			user.setName(id);
			user.setPassword(pwd);
			UserDAOProxy userDAOProxy = new UserDAOProxy();
			try {

				if (userDAOProxy.isUserExist(user)) {
					map.put("result", false);
					JSONObject jsonOutputObj = JSONObject.fromObject(map);
					response.setCharacterEncoding("utf-8");
					response.getWriter().append(jsonOutputObj + "");
					return;
				}
			
				userDAOProxy = new UserDAOProxy();
				userDAOProxy.insertUser(user);
				map.put("result", true);
				JSONObject jsonOutputObj = JSONObject.fromObject(map);
				response.setCharacterEncoding("utf-8");
				response.getWriter().append(jsonOutputObj + "");
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
