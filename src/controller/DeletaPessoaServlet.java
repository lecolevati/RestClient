package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pessoa;

@WebServlet("/exclusao")
public class DeletaPessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeletaPessoaServlet() {
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
		Pessoa p = new Pessoa();
		p.setId(Integer.parseInt(request.getParameter("id")));

		try {
			URL url = new URL(
					"http://localhost:8080/WSRestServer/Pessoas/deletaPessoa/"+p.getId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Content-Type", "text/plain");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED
					|| conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
	}

}
