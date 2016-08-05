package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pessoa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebServlet("/consulta")
public class ConsultaPessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConsultaPessoaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		Gson gson = new Gson();
		String id = request.getParameter("id");
		String endereco = "";
		try {
		if (id.trim().equals("")) {
			endereco = "http://localhost:8080/WSRestServer/Pessoas/";
		} else {
			endereco = "http://localhost:8080/WSRestServer/Pessoas/consultaPessoa/"
					+ id;
		}
		URL url = new URL(endereco);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		StringBuffer sb = new StringBuffer();
		while ((output = br.readLine()) != null) {
			sb.append(output);
		}
		try {
		Type listType = new TypeToken<List<Pessoa>>(){}.getType();
		lista = gson.fromJson(sb.toString(), listType);
		} catch (Exception e1){
			Pessoa p = gson.fromJson(sb.toString(), Pessoa.class);
			lista.add(p);
		}
		conn.disconnect();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			request.setAttribute("lista", lista);
			RequestDispatcher view = request
					.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		
	}
}
