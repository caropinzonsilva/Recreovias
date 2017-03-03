package com.carolinapinzon.java.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class ActivityServlet
 */
@WebServlet("/activity")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://138.197.76.68:5432/recreovias?user=postgres&password=postgres&sslmode=require";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
	        String id_auth = req.getParameter("id");
	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT id FROM matriz WHERE id_auth = '" + id_auth + "';");
	        int id = -1;
	        while (rs.next()) {
	        	id = Integer.parseInt(rs.getString("id"));
	        }
	        String select = "SELECT * FROM actividades WHERE id_usuario = " + id + " AND duracion IS NOT NULL;";
	        //System.out.println(select);
			Statement stmt2 = conn.createStatement();
			ResultSet rs2 = stmt2.executeQuery(select);

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("[");
            while (rs2.next()) {
            	String parque = rs2.getString("parque");
            	String actividad = rs2.getString("actividad");
            	String duracion = rs2.getString("duracion");
            	String calorias = rs2.getString("calorias");
            	String fecha = rs2.getString("fecha");
            	Boolean calificacion = rs2.getBoolean("calificacion");
	        	out.println("{");
	            out.println("\"parque\": \"" + parque + "\",");
	            out.println("\"actividad\": \"" + actividad + "\",");
	            out.println("\"duracion\": \"" + duracion + "\",");
	            out.println("\"calorias\": " + calorias + ",");
	            out.println("\"fecha\": \"" + fecha + "\",");
	            out.println("\"calificacion\": " + calificacion + "");
	            out.println("}");
	            if(!rs2.isLast()) {
	            	out.println(",");
	            }
	        }
            out.println("]");
            out.close();

            conn.close();
	        
	    } catch(Exception e) {
            System.out.println(e);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
        	req.setCharacterEncoding("UTF-8");
	        String body = getPostData(req);
	        JSONObject obj = new JSONObject(body);
	        String id_auth = obj.getString("id_auth");
	        String parque = obj.getString("parque");
	        String actividad = obj.getString("actividad");
	        String fecha = obj.getString("fecha");
	        String horario = obj.getString("horario");
	        String duracion = obj.getString("duracion");
	        int calorias = Integer.parseInt(obj.getString("calorias"));
	        String calificacion = obj.getString("calificacion");

	        Connection conn = getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT id FROM matriz WHERE id_auth = '" + id_auth + "';");
	        int id = -1;
	        while (rs.next()) {
	        	id = Integer.parseInt(rs.getString("id"));
	        }
	        String sqlQuery = "INSERT INTO actividades (id_usuario, parque, actividad, fecha, horario, duracion, calorias, calificacion) VALUES (" + id + ", '" + parque + "', '" + actividad + "', '" + fecha + "', '" + horario + "', '" + duracion + "', " + calorias + ", " + calificacion + ");";
	        stmt.executeUpdate(sqlQuery);
	        
	        int calificacionInt = -1;
	        if(calificacion.equals("true")) {
	        	calificacionInt = 1;
	        }
	        
	        String parqueColumn = Normalizer.normalize(parque, Normalizer.Form.NFD);
	        parqueColumn = parqueColumn.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	        parqueColumn = parqueColumn.replace(" ", "_");
	        parqueColumn = parqueColumn.toLowerCase();
	        
	        
	        String sqlUpdate = "UPDATE matriz SET " + actividad + "_" + horario + "=" + calificacionInt + ", " + parqueColumn + "=" + calificacionInt + " WHERE id=" + id;
	        stmt.executeUpdate(sqlUpdate);
	        
	        conn.close();

	        resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.println("{");
            out.println("\"code\": \"OK\"");
            out.println("}");
            out.close();
	    } catch(Exception e) {
            System.out.println(e);
        }
	}
	
	public static String getPostData(HttpServletRequest req) {
        String response = "";
        try {
            BufferedReader reader = req.getReader();
            reader.mark(10000);

            String line = reader.readLine();
            while(line != null) {
                response += line + "\n";
                line = reader.readLine();
            }
            reader.reset();
            // do NOT close the reader here, or you won't be able to get the post data twice
        } catch(IOException e) {
            //System.out.println("getPostData couldn't.. get the post data" + e);  // This has happened if the request's reader is closed    
        }

        return response;
    }
	
	private static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		
		return DriverManager.getConnection(URL);
    }

}
