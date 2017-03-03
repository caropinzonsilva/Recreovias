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
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.carolinapinzon.java.world.Actividad;
import com.carolinapinzon.java.world.SistemaRecomendacion;
import com.carolinapinzon.java.world.Sugerencia;
import com.carolinapinzon.java.world.Vecino;

/**
 * Servlet implementation class RecommendationServlet
 */
@WebServlet("/recomendacion")
public class RecommendationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL = "jdbc:postgresql://138.197.76.68:5432/recreovias?user=postgres&password=postgres&sslmode=require";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
        	double inicio = System.currentTimeMillis();
        	double fin = 0;
        	double inicioC = 0;
        	double finC = 0;
        	double inicioCB = 0;
        	double finCB = 0;
        	double inicioDC = 0;
        	double finDC = 0;
        	double inicioDC_dist = 0;
        	double finDC_dist = 0;
        	double inicioHibrido = 0;
        	double finHibrido = 0;
	        String id_auth = req.getParameter("id");
	        
	        SistemaRecomendacion sistema = new SistemaRecomendacion();

	        Connection conn = getConnection();
	        
	        int id = sistema.getUsedID(id_auth,conn);
	        if(id >= 0) {
	            //Sugerencia demographic correlation distancia -> parque
	            double[] userDC = sistema.getUserDC(id,conn);
	            double[] userC = sistema.getUserC(id,conn);
	            sistema.initMatrizDemographic(id,conn);
	            sistema.initMatrizDistancias(id,conn);
	            
	            inicioDC_dist = System.currentTimeMillis();
	            ArrayList<Vecino> vecinosDC = sistema.seleccionarIndiceVecinosDC(50, userDC);
	            double sugDistancias = sistema.darSugerenciaDistancia(userDC, 0, vecinosDC);
	            //System.out.println("sugDistancias " + sugDistancias);
	            finDC_dist = System.currentTimeMillis();
	            
	            //Sugerencia demographic correlation -> actividad
	            inicioC = System.currentTimeMillis();
	            sistema.initMatrizCollaborative(id,conn);
	            ArrayList<Sugerencia> sugerenciasDCSeleccionadas = sistema.seleccionarActividadesDC(10, userDC, vecinosDC);
	            finC = System.currentTimeMillis();
	            
	            
	            //Sugerencia collaborative filtering -> actividad
	            ArrayList<Vecino> vecinosC = sistema.seleccionarIndiceVecinosC(20, userC);
	            ArrayList<Sugerencia> sugerenciasCSeleccionadas = sistema.seleccionarActividadesC(10, userC, vecinosC);
	            
	            
	            //Sugerencia content-based filtering ->actividad
	            inicioCB = System.currentTimeMillis();
	            double[] userCB = sistema.getUserCB(userC);
	            ArrayList<Sugerencia> sugerenciasCBSeleccionadas = sistema.seleccionarActividadesCB(10, userCB);
	            finCB = System.currentTimeMillis();
	            
	            double[] distancias = sistema.getUserDistancias(id,conn);
	            inicioHibrido = System.currentTimeMillis();
	            ArrayList<Actividad> actividades = sistema.ordenarSugerencias(id,sugDistancias, distancias, sugerenciasDCSeleccionadas, sugerenciasCSeleccionadas, sugerenciasCBSeleccionadas,conn);
	            
	            
	            ArrayList<ArrayList<Actividad>> actividadesDias = agruparPorDia(actividades);
	            finHibrido = System.currentTimeMillis();
	            //System.out.println(actividadesDias);
	            String[] dias = {"M","I","J","V","S","D"};
	            resp.setContentType("application/json");
	            resp.setCharacterEncoding("UTF-8");
	            PrintWriter out = resp.getWriter();;
	            out.println("[");
	            for (int i = 0; i < actividadesDias.size(); i++) {
	            	out.println("{");
	            	out.println("\"dia\": \"" + dias[i] + "\",");
	            	out.println("\"actividades\": [");
	            	for (int j = 0; j < actividadesDias.get(i).size(); j++) {
	            		out.println("{");
		            	out.println("\"actividad\": \"" + actividadesDias.get(i).get(j).shortt + "\",");
		            	out.println("\"parque\": \"" + actividadesDias.get(i).get(j).nombre + "\",");
		            	out.println("\"horario\": \"" + actividadesDias.get(i).get(j).horario + "\"");
	            		out.println("}");
	            		if(j < actividadesDias.get(i).size() - 1) {
	            			out.println(",");
	            		}
					}
	            	out.println("]}");
	            	if(i < actividadesDias.size() - 1) {
	            		out.println(",");
	            	}
				}
	            out.println("]");
	            out.close();
	        }
	        
	        conn.close();
	        fin = System.currentTimeMillis();
	        System.out.println("inicio: " + inicio);
	        System.out.println("fin: " + fin);
	        System.out.println("inicioC: " + inicioC);
	        System.out.println("finC: " + finC);
	        System.out.println("inicioCB: " + inicioCB);
	        System.out.println("finCB: " + finCB);
	        System.out.println("inicioDC: " + inicioDC);
	        System.out.println("finDC: " + finDC);
	        System.out.println("inicioDC_dist: " + inicioDC_dist);
	        System.out.println("finDC_dist: " + finDC_dist);
	        System.out.println("inicioHibrido: " + inicioHibrido);
	        System.out.println("finHibrido: " + finHibrido);
	        
	    } catch(Exception e) {
            System.out.println(e);
            
            for (int i = 0; i < e.getStackTrace().length; i++) {
            	System.out.println("at " + e.getStackTrace()[i].getLineNumber());
            	System.out.println("at " + e.getStackTrace()[i].getMethodName());
			}
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
	        JSONArray actividades = obj.getJSONArray("actividades");
	        Connection conn = getConnection();
	        int positivas = 0;
	        int negativas = 0;
	        String id_auth = req.getParameter("id");
			
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT id FROM matriz WHERE id_auth = '" + id_auth + "';");
	        int id = -1;
	        while (rs.next()) {
	        	id = Integer.parseInt(rs.getString("id"));
	        }
	        
        	for (int i = 0; i < actividades.length(); i++) {
				//System.out.println(actividades.get(i));
				JSONObject actividad = (JSONObject) actividades.get(i);
				String parque = actividad.getString("parque");
				String tipo_actividad = actividad.getString("actividad");
				String calificacion = actividad.getString("like");
				String horario = actividad.getString("horario");
				String dia = actividad.getString("dia");
				String tipo_horario = "sm"; 
				//System.out.println("dia: " + dia + ", Domingo, " + dia.equalsIgnoreCase("Domingo"));
				if(dia.equalsIgnoreCase("Sábado") || dia.equalsIgnoreCase("Domingo")) {
					tipo_horario = "fs";
				}
				else {
					if(horario.contains("pm")) {
						tipo_horario = "sn";
					}
				}
				
				
				String sqlQuery = "INSERT INTO actividades (id_usuario, parque, actividad, calificacion) VALUES (" + id + ", '" + parque + "', '" + tipo_actividad + "', " + calificacion + ");";
				//System.out.println(sqlQuery);
			    stmt.executeUpdate(sqlQuery);
			    
			    int calificacionInt = -1;
		        if(calificacion.equals("true")) {
		        	calificacionInt = 1;
		        	positivas ++;
		        }
		        else {
		        	negativas ++;
		        }
		        String actividadColumn = tipo_actividad + "_" + tipo_horario;
		        
		        String parqueColumn = Normalizer.normalize(parque, Normalizer.Form.NFD);
		        parqueColumn = parqueColumn.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		        parqueColumn = parqueColumn.replace(" ", "_");
		        parqueColumn = parqueColumn.toLowerCase();
		        
		        int sum = 0;
		        ResultSet rs2 = stmt.executeQuery("SELECT * FROM actividades WHERE id_usuario = " + id + " AND parque = '" + parque + "';");
		        while (rs2.next()) {
		        	if(rs2.getString("calificacion").equalsIgnoreCase("t")) {
		        		sum ++;
		        	}
		        	else {
		        		sum --;
		        	}
		        }
		        int calificacionParque = 1;
		        if(sum < 0) {
		        	calificacionParque = -1;
		        }
			    
			    String sqlUpdate = "UPDATE matriz SET " + actividadColumn + "=" + calificacionInt + ", " + parqueColumn + "=" + calificacionParque + " WHERE id=" + id;
		        //System.out.println(sqlUpdate);
		        stmt.executeUpdate(sqlUpdate);
		        
		       
				
			}
        	String sqlQueryCalificacionSistema = "INSERT INTO calificaciones_sistema (id_usuario, negativas, positivas) VALUES (" + id + ", " + positivas + ", " + negativas + ");";
	        //System.out.println(sqlQueryCalificacionSistema);
		    stmt.executeUpdate(sqlQueryCalificacionSistema);
        	
        	conn.close();
        	resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.println("{");
            out.println("\"code\": \"OK\"");
            out.println("}");
            out.close();
        } catch(Exception e) {
            //System.out.println(e);
        }
	}
	
	public ArrayList<ArrayList<Actividad>> agruparPorDia(ArrayList<Actividad> actividades) {
    	String[] dias = {"M","I","J","V","S","D"};
    	ArrayList<ArrayList<Actividad>> resp = new ArrayList<ArrayList<Actividad>>();
    	for (int i = 0; i < dias.length; i++) {
    		ArrayList<Actividad> dia = new ArrayList<Actividad>();
    		resp.add(dia);
		}
    	
    	for (int i = 0; i < actividades.size(); i++) {
    		int index = -1;
    		boolean cont  = true;
    		for (int j = 0; j < dias.length && cont; j++) {
				if(dias[j].equals(actividades.get(i).dia)) {
					index = j;
					cont = false;
				}
			}
    		
    		resp.get(index).add(actividades.get(i));
		}
    	return resp;
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
