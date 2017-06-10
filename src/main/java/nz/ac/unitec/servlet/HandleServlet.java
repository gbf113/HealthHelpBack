package nz.ac.unitec.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("---------00000000----");
		int a;
		int b;

		int res = 0;
		double res2 = 0;
		String result;

		String method = request.getHeader("method").toString();
		String str1 = request.getHeader("one").toString();
		String str2 = request.getHeader("two").toString();

		System.out.println(method + "/" + str1 + "/" + str2);
		try{
			a = Integer.parseInt(str1);
			b = Integer.parseInt(str2);
		}catch(Exception e){
			response.getWriter().append(e.getMessage() + "");
			return;
		}

		if(method.equals("+")){
			res = new Utils().plus(a, b);
		}else if(method.equals("-")){
			res = new Utils().minus(a, b);
		}else if(method.equals("*")){
			res = new Utils().multy(a, b);
		}else if(method.equals("/")){
			res2 = new Utils().trad(a, b);
		}

		if(method.equals("/")){
			result = res2 + "";
		}else{
			result = res + "";
		}

		response.setCharacterEncoding("utf-8");
		response.getWriter().append(result + "");
		
		getData(request);
	}

	public String getData(HttpServletRequest req){
		String result = null;
		
		try {
			//request
			BufferedReader br = new BufferedReader(  
					new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
			//
			StringBuffer sb=new StringBuffer("");
			String line;
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			br.close();
			result=sb.toString();
			
			System.out.println("result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return result;
	}


}
