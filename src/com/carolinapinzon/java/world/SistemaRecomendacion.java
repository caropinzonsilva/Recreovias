package com.carolinapinzon.java.world;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SistemaRecomendacion {
	private ArrayList<double[]> matriz_distancias;
	private ArrayList<double[]> matriz_DC;
	private ArrayList<double[]> matriz_DC_entrenamiento;
	private ArrayList<double[]> matriz_DC_prueba;
	private ArrayList<double[]> matriz_C;
	private ArrayList<double[]> matriz_C_entrenamiento;
	private ArrayList<double[]> matriz_C_prueba;
	private ArrayList<double[]> matriz_pesos_CB_actividades;
	private ArrayList<double[]> matriz_item_pesos_CB_actividades;
	private String[] columnasC = { "GP_sm", "GM_sm", "MA_sm", "EF_sm", "RT_sm", "GA_sm", "AN_sm", "AM_sm", "P_sm",
			"D_sm", "GS_sm", "GP_sn", "GM_sn", "MA_sn", "EF_sn", "RT_sn", "GA_sn", "AN_sn", "AM_sn", "P_sn",
			"D_sn", "GS_sn", "GP_fs", "GM_fs", "MA_fs", "EF_fs", "RT_fs", "GA_fs", "AN_fs", "AM_fs", "P_fs",
			"D_fs", "GS_fs" };
//	private String[] columnasC = { "GP_sm", "GM_sm", "MA_sm", "EF_sm", "RT_sm", "GA_sm", "AN_sm", "GM_sn", "MA_sn", "EF_sn", "RT_sn", "GA_sn", "AN_sn", "GP_fs", "GM_fs", "MA_fs", "EF_fs", "RT_fs", "GA_fs", "AN_fs" };
	private String[] nombresParques = {"Altablanca", "Atahualpa", "Candelaria", "Casona", "Castilla", "Cayetano Cañizares", "CC Centro Mayor", "CC Fiesta Suba", "Central Piloto", "Ciudad Montes", "Eduardo Santos", "El Jazmín", "El Porvenir", "El Salitre", "El Sol", "El Tunal", "Éxito Colina", "Éxito Suba", "Hayuelos", "La Andrea", "La Gaitana", "La Serena", "Madelena", "MetroRecreo CC", "Milenta Tejar", "Molinos", "Nacional", "Naranjos", "Plaza de las Américas CC", "Plaza de Bolivar", "Salitre Plaza CC", "San Andrés", "San Carlos", "San Cristobal", "San Rafael CC", "Santa Isabel", "Simón Bolivar", "Timiza", "Unicentro Occidente CC", "Valles de Cafam", "Villa de los Alpes", "Virrey Sur"};
	double[] arreglo_IDF;
	
	public SistemaRecomendacion() {
		initMatrizCBActividades();
	}

	public ArrayList<Vecino> seleccionarIndiceVecinosDC(int cantidad, double[] user) {		
		ArrayList<double[]> matriz = matriz_DC;
		ArrayList<Vecino> vecinos = new ArrayList<Vecino>();
		for (int j = 0; j < matriz.size(); j++) {
			double sim = cos(matriz.get(j), user);
			Vecino v = new Vecino(j, sim);
			vecinos.add(v);
		}
		Collections.sort(vecinos);
		return new ArrayList<Vecino>(vecinos.subList(0,cantidad));
	}
	
	public ArrayList<Vecino> seleccionarIndiceVecinosDCPrueba(int cantidad, double[] user) {		
		ArrayList<double[]> matriz = matriz_DC;
		ArrayList<Vecino> vecinos = new ArrayList<Vecino>();
		for (int j = 0; j < matriz.size(); j++) {
			double sim = cos(matriz.get(j), user);
			Vecino v = new Vecino(j, sim);
			vecinos.add(v);
		}
		Collections.sort(vecinos);
		return new ArrayList<Vecino>(vecinos.subList(0,cantidad));
	}

	public ArrayList<Vecino> seleccionarIndiceVecinosC(int cantidad, double[] user) {
		ArrayList<double[]> matriz = matriz_C;
		ArrayList<Vecino> vecinos = new ArrayList<Vecino>();
		for (int j = 0; j < matriz.size(); j++) {
			double sim = cos(matriz.get(j), user);
			Vecino v = new Vecino(j, sim);
			vecinos.add(v);
		}
		Collections.sort(vecinos);
		return new ArrayList<Vecino>(vecinos.subList(0,cantidad));
	}
	
	public ArrayList<Vecino> seleccionarIndiceVecinosCPrueba(int cantidad, double[] user) {
		ArrayList<double[]> matriz = matriz_C_entrenamiento;
		ArrayList<Vecino> vecinos = new ArrayList<Vecino>();
		for (int j = 0; j < matriz.size(); j++) {
			double sim = jaccard(matriz.get(j), user);
			Vecino v = new Vecino(j, sim);
			vecinos.add(v);
		}
		Collections.sort(vecinos);
		return new ArrayList<Vecino>(vecinos.subList(0,cantidad));
	}

	public ArrayList<Sugerencia> seleccionarActividadesDC(int cantidad, double[] user_DC, ArrayList<Vecino> vecinos) {
		ArrayList<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
		for (int j = 0; j < matriz_C.get(0).length; j++) {
			double sug = darSugerenciaActividadDC(user_DC, j, vecinos);
			if(sug >= 0) {
				Sugerencia s = new Sugerencia(columnasC[j], sug);
				sugerencias.add(s);
			}
		}
		Collections.sort(sugerencias);
		return sugerencias;
	}

	public ArrayList<Sugerencia> seleccionarActividadesCB(int cantidad, double[] user_C) {
		ArrayList<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
		for (int j = 0; j < matriz_C.get(0).length; j++) {
			double sug = darSugerenciaActividadCB(user_C, j);
			if(sug > 0) {
				Sugerencia s = new Sugerencia(columnasC[j], sug);
				sugerencias.add(s);
			}
		}
		Collections.sort(sugerencias);
		return sugerencias;
	}

	public ArrayList<Sugerencia> seleccionarActividadesC(int cantidad, double[] user_C, ArrayList<Vecino> vecinos) {
		ArrayList<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
		for (int j = 0; j < matriz_C.get(0).length; j++) {
			double sug = darSugerenciaActividadC(user_C, j, vecinos);
			if(sug > 0) {
				Sugerencia s = new Sugerencia(columnasC[j], sug);
				sugerencias.add(s);
			}
		}
		Collections.sort(sugerencias);
		return sugerencias;
	}

	public double darSugerenciaActividadCB(double[] user_CB, int indiceItem) {
		double dotProduct = 0;
		System.out.println(arrayToString(user_CB));
		System.out.println(arrayToString(matriz_item_pesos_CB_actividades.get(indiceItem)));
		dotProduct = cos(user_CB, matriz_item_pesos_CB_actividades.get(indiceItem));
		return dotProduct;
	}
	
	public double darSugerenciaActividadCBPrueba(double[] user_CB, int indiceItem) {
		double dotProduct = 0;
		dotProduct = cos(user_CB, matriz_item_pesos_CB_actividades.get(indiceItem));
//		for(int j = 0; j < user_CB.length; j++) {
//			double mult = user_CB[j]*arreglo_IDF[j]*matriz_item_pesos_CB_actividades.get(indiceItem)[j];
//			dotProduct += mult;
//		}
		return dotProduct;
	}

	public double darSugerenciaActividadDC(double[] user_DC, double indiceItem, ArrayList<Vecino> vecinos) {
		double numerator_DC = 0;
		double denominator_DC = 0;

		for (int i = 0; i < vecinos.size(); i++) {
			double[] user_prime_C = matriz_C.get(vecinos.get(i).indice);
			double[] user_prime_DC = matriz_DC.get(vecinos.get(i).indice);
			double dem_cor = cos(user_DC, user_prime_DC);
			
			numerator_DC += dem_cor * user_prime_C[(int) indiceItem];
			denominator_DC += Math.abs(dem_cor);

		}
		double result;
		if (denominator_DC == 0) {
			result = 0;
		} else {
			result = numerator_DC / denominator_DC;
		}
		return result;
	}
	
	public double darSugerenciaActividadDCPrueba(double[] user_DC, double[] user_C, double indiceItem, ArrayList<Vecino> vecinos) {
		double numerator_DC = 0;
		double denominator_DC = 0;
		for (int i = 0; i < vecinos.size(); i++) {
			double[] user_prime_C = matriz_C.get(vecinos.get(i).indice);
			double[] user_prime_DC = matriz_DC.get(vecinos.get(i).indice);
			double dem_cor = cos(user_DC, user_prime_DC);
//			double sim = cos(user_C, user_prime_C);
//			double enh_cor = sim + sim*dem_cor;
//			numerator_DC += enh_cor * user_prime_C[(int) indiceItem];
			numerator_DC += dem_cor * user_prime_C[(int) indiceItem];
			denominator_DC += Math.abs(dem_cor);

		}
		double result;
		if (denominator_DC == 0) {
			result = 0;
		} else {
			result = numerator_DC / denominator_DC;
		}
		return result;
	}

	public double darSugerenciaActividadC(double[] user_C, double indiceItem, ArrayList<Vecino> indiceVecinos) {
		double average_user_C = 0;
		double number_rated_user_C = 0;
		double numerator_C = 0;
		double denominator_C = 0;

		for (int i = 0; i < user_C.length; i++) {
			if (user_C[i] != 0) {// Item rated by a
				average_user_C += user_C[i];
				number_rated_user_C++;
			}
		}
		average_user_C = average_user_C / number_rated_user_C;
		for (int i = 0; i < indiceVecinos.size(); i++) {
			double[] user_prime_C = matriz_C.get(indiceVecinos.get(i).indice);
			double average_user_prime = 0;
			double number_rated_user_prime = 0;
			for (int j = 0; j < user_C.length; j++) {
				if (user_prime_C[j] != 0) {// Item rated by a
					average_user_prime += user_prime_C[i];
					number_rated_user_prime++;
				}
			}
			if (number_rated_user_prime == 0) {
				average_user_prime = 0;
			} else {
				average_user_prime = average_user_prime / number_rated_user_prime;
			}
			double sim = cos(user_C, user_prime_C);
			numerator_C += sim * (user_prime_C[(int) indiceItem] - average_user_prime);
			denominator_C += Math.abs(sim);

		}
		double result;
		if (denominator_C == 0) {
			result = 0;
		} else {
			result = numerator_C / denominator_C;
		}
		return result;
	}
	
	public double darSugerenciaActividadCPrueba(double[] user_C, double indiceItem, ArrayList<Vecino> indiceVecinos) {
		double average_user_C = 0;
		double numerator_C = 0;
		double denominator_C = 0;

		for (int i = 0; i < user_C.length; i++) {
			average_user_C += user_C[i];
		}
		average_user_C = average_user_C / user_C.length;
		
		
		for (int i = 0; i < indiceVecinos.size(); i++) {
			//[] user_prime_C = matriz_C.get(indiceVecinos.get(i).indice);
			double[] user_vecino = matriz_C_entrenamiento.get(indiceVecinos.get(i).indice);
			double average_user_vecino = 0;
			for (int j = 0; j < user_vecino.length; j++) {
				average_user_vecino += user_vecino[j];
			}
			average_user_vecino = average_user_vecino / user_vecino.length;
			
			//double sim = cos(user_C, user_prime_C);
			double sim = jaccard(user_C, user_vecino);
			numerator_C += sim * (user_vecino[(int) indiceItem]);
			//numerator_C += sim * (user_vecino[(int) indiceItem] - average_user_vecino);
			denominator_C += Math.abs(sim);
		}
		double result;
		if (denominator_C == 0) {
			result = 0;
		} else {
			result = numerator_C / denominator_C;
			//result = numerator_C / denominator_C + average_user_C;
		}
		return result;
	}

	public double darSugerenciaDistancia(double[] user_DC, double indiceItem, ArrayList<Vecino> vecinos) {
		double numerator_DC = 0;
		double denominator_DC = 0;

		for (int i = 0; i < vecinos.size(); i++) {
			double[] user_prime_C = matriz_distancias.get(vecinos.get(i).indice);
			double[] user_prime_DC = matriz_DC.get(vecinos.get(i).indice);
			double dem_cor = cos(user_DC, user_prime_DC);

			numerator_DC += dem_cor * user_prime_C[(int) indiceItem];
			denominator_DC += Math.abs(dem_cor);

		}
		double result;
		if (denominator_DC == 0) {
			result = 0;
		} else {
			result = numerator_DC / denominator_DC;
		}
		return result;
	}

	public ArrayList<Actividad> ordenarSugerencias(int id,double distancia, double[] distanciasRecreovias,
			ArrayList<Sugerencia> indiceActividadesDCSeleccionadas, ArrayList<Sugerencia> indiceActividadesCSeleccionadas,
			ArrayList<Sugerencia> indiceActividadesCBSeleccionadas, Connection conn) {
		class Recreovia implements Comparable<Recreovia> {
			public String nombre;
			public int contador;

			public Recreovia(String nombreP, int contadorP) {
				nombre = nombreP;
				contador = contadorP;
			}

			public void addContador() {
				contador++;
			}

			public int compareTo(Recreovia o) {
				return (this.contador - o.contador);
			}

			public String toString() {
				return "Nombre: " + this.nombre + " | Contador: " + this.contador;
			}

			@Override
			public boolean equals(Object object)
			{
				boolean sameSame = false;
				if (object != null && object instanceof Recreovia) {
					sameSame = this.nombre == ((Recreovia) object).nombre;
				}
				return sameSame;
			}
		}

		double p = 1;
		double m = 0.5;
		double g = 0.2;
		//							1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42
		double[] multiplicadores = {p, m, p, p, m, m, m, p, p, p, p, m, p, m, m, g, m, p, m, m, m, m, p, p, m, p, g, m, g, m, p, m, g, m, p, g, g, m, m, g, p, g};

		ArrayList<Recreovia> listaRecreovias = new ArrayList<Recreovia>();
		if(indiceActividadesCSeleccionadas.size() == 0 && indiceActividadesCBSeleccionadas.size() == 0) {
			for (int i = 0; i < indiceActividadesDCSeleccionadas.size(); i++) {
				Recreovia r = new Recreovia(indiceActividadesDCSeleccionadas.get(i).nombre, 0);
				int index  = listaRecreovias.indexOf(r);
				if(index >= 0) {
					r = listaRecreovias.get(index);
				}
				else {
					listaRecreovias.add(r);
				}
				r.addContador();
			}
		}

		for (int i = 0; i < indiceActividadesCSeleccionadas.size(); i++) {
			Recreovia r = new Recreovia(indiceActividadesCSeleccionadas.get(i).nombre, 0);
			int index  = listaRecreovias.indexOf(r);
			if(index >= 0) {
				r = listaRecreovias.get(index);
			}
			else {
				listaRecreovias.add(r);
			}
			r.addContador();
		}
		for (int i = 0; i < indiceActividadesCBSeleccionadas.size(); i++) {
			Recreovia r = new Recreovia(indiceActividadesCBSeleccionadas.get(i).nombre, 0);
			int index  = listaRecreovias.indexOf(r);
			if(index >= 0) {
				r = listaRecreovias.get(index);
			}
			else {
				listaRecreovias.add(r);
			}
			r.addContador();
		}
		Collections.sort(listaRecreovias);

		ArrayList<ArrayList<Actividad>> todasActividades = new ArrayList<ArrayList<Actividad>>();
		ArrayList<String> parquesNegativos = darParquesNegativos(id,conn);

		for (int i = 0; i < listaRecreovias.size(); i++) {
			String actividad = listaRecreovias.get(i).nombre.split("_")[0];
			String tipo_horario = listaRecreovias.get(i).nombre.split("_")[1];

			String search = "SELECT * FROM \"Recreovias_actividades\" WHERE short = '" + actividad
					+ "' AND tipo_horario = '" + tipo_horario + "';";
			//System.out.println(search);
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(search);

				ArrayList<Actividad> listaParques = new ArrayList<Actividad>();
				while (rs.next()) {

					int index = -1;
					boolean cont = true;
					for (int j = 0; j < nombresParques.length && cont; j++) {
						if(nombresParques[j].equals(rs.getString("Parque"))) {
							cont = false;
							index = j;
						}
					}
					Actividad parque = new Actividad(rs.getString("Parque"), distanciasRecreovias[index], multiplicadores[index], rs.getString("short"), rs.getString("dia"), rs.getString("horario"));
					//System.out.println(parque);
					listaParques.add(parque);

				}
				Collections.sort(listaParques);

				for (int j = 0; j < parquesNegativos.size(); j++) {
					for (int j2 = 0; j2 < listaParques.size(); j2++) {
						if(parquesNegativos.get(j).equalsIgnoreCase(listaParques.get(j2).nombre)) {
							listaParques.remove(j2);
						}
					}
				}

				if(listaParques.size() > 0) {
					todasActividades.add(listaParques);
				}

			}
			catch(Exception e) {
				//System.out.println("Exception search ordenar sugerencias");
				//System.out.println(e.getMessage());
				//System.out.println(e.getClass());
			}
		}

		ArrayList<Actividad> sugerencias = new ArrayList<Actividad>();
		for (int j = 0; j < todasActividades.size() && sugerencias.size() < 12; j++) {
			sugerencias.add(todasActividades.get(j).get(0));
		}
		for (int j = 0; j < todasActividades.size() && sugerencias.size() < 12; j++) {
			if(todasActividades.get(j).size() > 1) {
				sugerencias.add(todasActividades.get(j).get(1));
			}
		}
		for (int j = 0; j < todasActividades.size() && sugerencias.size() < 12; j++) {
			if(todasActividades.get(j).size() > 2) {
				sugerencias.add(todasActividades.get(j).get(2));
			}
		}

		//System.out.println(sugerencias);

		return sugerencias;

	}

	public int getUsedID(String id_auth, Connection conn) {
		try {
			matriz_DC = new ArrayList<double[]>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM matriz WHERE id_auth = '" + id_auth + "';");

			int id = -1;
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("id"));

			}
			return id;
		} catch (Exception e) {
			System.err.println("Got an exception getUsedID");
			// System.err.println(e.getClass().getCanonicalName());
		}
		return -1;
	}

	public double[] getUserDC(int id, Connection conn) {
		try {
			matriz_DC = new ArrayList<double[]>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM matriz_demographic WHERE id = " + id + ";");

			String[] columnas = { "edad_0", "edad_1", "edad_2", "F", "M", "frec_ano", "frec_mes_1", "frec_mes_2",
					"frec_semana_1", "frec_semana_2", "frec_semana_3", "estrato_0_1_2", "estrato_3_4", "estrato_5_6" };

			double[] row = new double[columnas.length];
			while (rs.next()) {
				for (int i = 0; i < columnas.length; i++) {
					Double value = rs.getDouble(columnas[i]);
					row[i] = value;
				}
			}
			return row;
		} catch (Exception e) {
			System.err.println("Got an exception getUsedID");
			// System.err.println(e.getClass().getCanonicalName());
		}
		return null;
	}

	public double[] getUserC(int id,Connection conn) {
		try {
			matriz_DC = new ArrayList<double[]>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM matriz_collaborative_actividades WHERE id = " + id + ";");

			String[] columnas = columnasC;

			double[] row = new double[columnas.length];
			while (rs.next()) {
				for (int i = 0; i < columnas.length; i++) {
					Double value = rs.getDouble(columnas[i]);
					row[i] = value;
				}
			}
			return row;
		} catch (Exception e) {
			System.err.println("Got an exception getUserC");
			// System.err.println(e.getClass().getCanonicalName());
		}
		return null;
	}

	public double[] getUserCB(double[] userC) {
		double[] row = new double[userC.length];
		for (int i = 0; i < matriz_pesos_CB_actividades.size(); i++) {
			double dotProduct = 0;
			for (int j = 0; j < userC.length; j++) {
				dotProduct += matriz_pesos_CB_actividades.get(i)[j] * userC[j];
			}
			row[i] = dotProduct;
		}
		return row;
	}
	
	public double[] getUserCBPrueba(double[] userC) {
		double[] user = new double[matriz_pesos_CB_actividades.size()];
		for(int i = 0; i < matriz_pesos_CB_actividades.size(); i++) {
			double dotProduct = 0;
			for(int j = 0; j < userC.length; j++) {
				dotProduct += matriz_pesos_CB_actividades.get(i)[j]*userC[j];
			}
			user[i] = dotProduct;
		}
		return user;
	}

	public double[] getUserDistancias(int id,Connection conn) {

		try {
			matriz_DC = new ArrayList<double[]>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT recreovias_distancias FROM matriz WHERE id = " + id + ";");

			String barrio = "";
			double[] response = null;
			while (rs.next()) {
				barrio = rs.getString("recreovias_distancias");
				String[] distanciasString = barrio.split("-");
				response = new double[distanciasString.length];
				for (int i = 0; i < response.length; i++) {
					response[i] = Double.parseDouble(distanciasString[i]);
				}
			}
			return response;
		} catch (Exception e) {
			System.err.println("Got an exception getUserDistancias");
			System.err.println(e.getClass().getCanonicalName());
		}
		return null;
	}

	public void initMatrizCollaborative(int userId,Connection conn) {
		try {
			matriz_C = new ArrayList<double[]>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM matriz_collaborative_actividades WHERE id != " + userId);
			String[] columnas = columnasC;

			while (rs.next()) {
				double[] row = new double[columnas.length];
				for (int i = 0; i < columnas.length; i++) {
					Double value = rs.getDouble(columnas[i]);
					row[i] = value;
				}
				matriz_C.add(row);
				// System.out.println(arrayToString(row));
			}
		} catch (Exception e) {
			System.err.println("Got an exception initMatrizDemographic");
			// System.err.println(e.getClass().getCanonicalName());
		}
	}
	
	public void initMatrizCollaborativePruebas(Connection conn) {
		try {
			matriz_C = new ArrayList<double[]>();
			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM matriz_collaborative_actividades WHERE id < 521");
			ResultSet rs = stmt.executeQuery("SELECT * FROM matriz WHERE id < 521");
//			String[] columnas = columnasC;
			String[] columnas = {"distancia"};

			while (rs.next()) {
				double[] row = new double[columnas.length];
				for (int i = 0; i < columnas.length; i++) {
					Double value = rs.getDouble(columnas[i]);
					row[i] = value;
				}
				matriz_C.add(row);
				
			}
		} catch (Exception e) {
			System.err.println("Got an exception initMatrizDemographic");
			// System.err.println(e.getClass().getCanonicalName());
		}
	}

	public void initMatrizDemographic(int userId,Connection conn) {
		try {
			matriz_DC = new ArrayList<double[]>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM matriz_demographic WHERE id != " + userId);
			String[] columnas = { "edad_0", "edad_1", "edad_2", "F", "M", "frec_ano", "frec_mes_1", "frec_mes_2",
					"frec_semana_1", "frec_semana_2", "frec_semana_3", "estrato_0_1_2", "estrato_3_4", "estrato_5_6" };

			while (rs.next()) {
				double[] row = new double[columnas.length];
				for (int i = 0; i < columnas.length; i++) {
					Double value = rs.getDouble(columnas[i]);
					row[i] = value;
				}
				matriz_DC.add(row);
				// System.out.println(arrayToString(row));
			}
		} catch (Exception e) {
			System.err.println("Got an exception initMatrizDemographic");
			// System.err.println(e.getClass().getCanonicalName());
		}
	}
	
	public void initMatrizDemographicPruebas(Connection conn) {
		try {
			matriz_DC = new ArrayList<double[]>();
//			ArrayList<double[]> matriz_DC_temp = new ArrayList<double[]>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM matriz JOIN localidades ON matriz.id = localidades.id_usuario");
			String[] columnas = { "edad_0", "edad_1", "edad_2", "F", "M", "frec_ano", "frec_mes_1", "frec_mes_2",
					"frec_semana_1", "frec_semana_2", "frec_semana_3", "estrato_0_1_2", "estrato_3_4", "estrato_5_6" };
			
//			ArrayList<String> barrios = new ArrayList<String>();
//			ArrayList<String> barriosUsuario = new ArrayList<String>();
			while (rs.next()) {
//				String barrio = rs.getString("barrio");
//				double distancia = rs.getDouble("distancia");
//				barriosUsuario.add(barrio);
//				if(!barrios.contains(barrio)) {
//					barrios.add(barrio);
//				}
				double[] row = new double[columnas.length + 3];
				for (int i = 0; i < columnas.length; i++) {
					Double value = rs.getDouble(columnas[i]);
					row[i] = value;
				}
//				row[columnas.length] = 0;
//				row[columnas.length + 1] = 0;
//				row[columnas.length + 2] = 0;
//				if(distancia <= 2) {
//					row[columnas.length] = 1;
//				}
//				else if(distancia <= 5) {
//					row[columnas.length + 1] = 1;
//				}
//				else {
//					row[columnas.length + 2] = 1;
//				}
				
//				matriz_DC_temp.add(row);
				matriz_DC.add(row);
				// System.out.println(arrayToString(row));
			}
//			for (int i = 0; i < matriz_DC_temp.size(); i++) {
//				double[] rowFinal = new double[columnas.length + barrios.size()];
//				for (int j = 0; j < rowFinal.length; j++) {
//					if(j < matriz_DC_temp.get(i).length) {
//						rowFinal[j] = matriz_DC_temp.get(i)[j];
//					}
//					else {
//						rowFinal[j] = 0;
//					}
//				}
//				int indiceBarrio = barrios.indexOf(barriosUsuario.get(i));
//				rowFinal[columnas.length + indiceBarrio] = 1;
//				matriz_DC.add(rowFinal);
//			}
			
		} catch (Exception e) {
			System.err.println("Got an exception initMatrizDemographic");
			System.err.println(e.getClass().getCanonicalName());
			System.err.println(e.getMessage());
		}
	}

	public void initMatrizDistancias(int userId,Connection conn) {
		try {
			matriz_distancias = new ArrayList<double[]>();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM matriz_distancia WHERE id != " + userId);
			String[] columnas = { "distancia" };

			while (rs.next()) {
				double[] row = new double[columnas.length];
				for (int i = 0; i < columnas.length; i++) {
					Double value = rs.getDouble(columnas[i]);
					row[i] = value;
				}
				matriz_distancias.add(row);
				// System.out.println(arrayToString(row));
			}
		} catch (Exception e) {
			System.err.println("Got an exception initMatrizDistancias");
			System.err.println(e.getClass().getCanonicalName());
		}
	}

	public void initMatrizCBActividades() {
		matriz_pesos_CB_actividades = new ArrayList<double[]>();
		matriz_item_pesos_CB_actividades = new ArrayList<double[]>();

		double[] tipo_GP = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		matriz_pesos_CB_actividades.add(tipo_GP);
		double[] tipo_GM = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		matriz_pesos_CB_actividades.add(tipo_GM);
		double[] tipo_MA = { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
				0, 0, 0 };
		matriz_pesos_CB_actividades.add(tipo_MA);
		double[] tipo_EF = { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
				0, 0, 0 };
		matriz_pesos_CB_actividades.add(tipo_EF);
		double[] tipo_RT = { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
				0, 0, 0 };
		matriz_pesos_CB_actividades.add(tipo_RT);
		double[] tipo_GA = { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
				0, 0, 0 };
		matriz_pesos_CB_actividades.add(tipo_GA);
		double[] tipo_AN = { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
				0, 0, 0 };
		matriz_pesos_CB_actividades.add(tipo_AN);
		double[] tipo_AM = { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
				0, 0, 0 };
		matriz_pesos_CB_actividades.add(tipo_AM);
		double[] tipo_P = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
				0, 0 };
		matriz_pesos_CB_actividades.add(tipo_P);
		double[] tipo_D = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0 };
		matriz_pesos_CB_actividades.add(tipo_D);
		double[] tipo_GS = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 1 };
		matriz_pesos_CB_actividades.add(tipo_GS);
		double[] MANANA = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		matriz_pesos_CB_actividades.add(MANANA);
		double[] NOCHE = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		matriz_pesos_CB_actividades.add(NOCHE);
		double[] FINES = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1 };
		matriz_pesos_CB_actividades.add(FINES);
		double[] cardio = { 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0,
				1, 0 };
		matriz_pesos_CB_actividades.add(cardio);
		double[] estiramiento = { 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0,
				0, 1, 0, 1 };
		matriz_pesos_CB_actividades.add(estiramiento);
		double[] fuerza = { 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1,
				0, 1 };
		matriz_pesos_CB_actividades.add(fuerza);
		double[] ninos = { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
				0, 0 };
		matriz_pesos_CB_actividades.add(ninos);

		normalizarMatrizDC();

		// Transpuesta
		calcularMatrizItemDC();
	}
	
	public void initMatrizCBActividadesPruebas() {
		matriz_pesos_CB_actividades = new ArrayList<double[]>();
		matriz_item_pesos_CB_actividades = new ArrayList<double[]>();

//		double[] tipo_GP = { 1,0,0,0,0,0,0, 1,0,0,0,0,0,0, 1,0,0,0,0,0,0 };
//		matriz_pesos_CB_actividades.add(tipo_GP);
//		double[] tipo_GM = { 0,1,0,0,0,0,0, 0,1,0,0,0,0,0, 0,1,0,0,0,0,0 };
//		matriz_pesos_CB_actividades.add(tipo_GM);
//		double[] tipo_MA = { 0,0,1,0,0,0,0, 0,0,1,0,0,0,0, 0,0,1,0,0,0,0 };
//		matriz_pesos_CB_actividades.add(tipo_MA);
//		double[] tipo_EF = { 0,0,0,1,0,0,0, 0,0,0,1,0,0,0, 0,0,0,1,0,0,0 };
//		matriz_pesos_CB_actividades.add(tipo_EF);
//		double[] tipo_RT = { 0,0,0,0,1,0,0, 0,0,0,0,1,0,0, 0,0,0,0,1,0,0 };
//		matriz_pesos_CB_actividades.add(tipo_RT);
//		double[] tipo_GA = { 0,0,0,0,0,1,0, 0,0,0,0,0,1,0, 0,0,0,0,0,1,0 };
//		matriz_pesos_CB_actividades.add(tipo_GA);
//		double[] tipo_AN = { 0,0,0,0,0,0,1, 0,0,0,0,0,0,1, 0,0,0,0,0,0,1 };
//		matriz_pesos_CB_actividades.add(tipo_AN);
//		double[] MANANA_ = { 1,1,1,1,1,1,1, 0,0,0,0,0,0,0, 0,0,0,0,0,0,0 };
//		matriz_pesos_CB_actividades.add(MANANA_);
//		double[] NOCHE__ = { 0,0,0,0,0,0,0, 1,1,1,1,1,1,1, 0,0,0,0,0,0,0 };
//		matriz_pesos_CB_actividades.add(NOCHE__);
//		double[] FINES__ = { 0,0,0,0,0,0,0, 0,0,0,0,0,0,0, 1,1,1,1,1,1,1 };
//		matriz_pesos_CB_actividades.add(FINES__);
//		double[] cardio_ = { 0,1,0,0,1,1,0, 0,1,0,0,1,1,0, 0,1,0,0,1,1,0 };
//		matriz_pesos_CB_actividades.add(cardio_);
//		double[] estiram = { 1,0,1,0,0,0,0, 1,0,1,0,0,0,0, 1,0,1,0,0,0,0 };
//		matriz_pesos_CB_actividades.add(estiram);
//		double[] fuerza_ = { 0,0,0,1,0,0,0, 0,0,0,1,0,0,0, 0,0,0,1,0,0,0 };
//		matriz_pesos_CB_actividades.add(fuerza_);
//		double[] ninos__ = { 0,0,0,0,0,0,1, 0,0,0,0,0,0,1, 0,0,0,0,0,0,1 };
//		matriz_pesos_CB_actividades.add(ninos__);
		
		double[] tipo_GP = { 1,0,0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0,0,0 };
		matriz_pesos_CB_actividades.add(tipo_GP);
		double[] tipo_GM = { 0,1,0,0,0,0,0,0,0,0,0, 0,1,0,0,0,0,0,0,0,0,0, 0,1,0,0,0,0,0,0,0,0,0 };
		matriz_pesos_CB_actividades.add(tipo_GM);
		double[] tipo_MA = { 0,0,1,0,0,0,0,0,0,0,0, 0,0,1,0,0,0,0,0,0,0,0, 0,0,1,0,0,0,0,0,0,0,0 };
		matriz_pesos_CB_actividades.add(tipo_MA);
		double[] tipo_EF = { 0,0,0,1,0,0,0,0,0,0,0, 0,0,0,1,0,0,0,0,0,0,0, 0,0,0,1,0,0,0,0,0,0,0 };
		matriz_pesos_CB_actividades.add(tipo_EF);
		double[] tipo_RT = { 0,0,0,0,1,0,0,0,0,0,0, 0,0,0,0,1,0,0,0,0,0,0, 0,0,0,0,1,0,0,0,0,0,0 };
		matriz_pesos_CB_actividades.add(tipo_RT);
		double[] tipo_GA = { 0,0,0,0,0,1,0,0,0,0,0, 0,0,0,0,0,1,0,0,0,0,0, 0,0,0,0,0,1,0,0,0,0,0 };
		matriz_pesos_CB_actividades.add(tipo_GA);
		double[] tipo_AN = { 0,0,0,0,0,0,1,0,0,0,0, 0,0,0,0,0,0,1,0,0,0,0, 0,0,0,0,0,0,1,0,0,0,0 };
		matriz_pesos_CB_actividades.add(tipo_AN);
		double[] tipo_AM = { 0,0,0,0,0,0,0,1,0,0,0, 0,0,0,0,0,0,0,1,0,0,0, 0,0,0,0,0,0,0,1,0,0,0 };
		matriz_pesos_CB_actividades.add(tipo_AM);
		double[] tipo_P_ = { 0,0,0,0,0,0,0,0,1,0,0, 0,0,0,0,0,0,0,0,1,0,0, 0,0,0,0,0,0,0,0,1,0,0 };
		matriz_pesos_CB_actividades.add(tipo_P_);
		double[] tipo_D_ = { 0,0,0,0,0,0,0,0,0,1,0, 0,0,0,0,0,0,0,0,0,1,0, 0,0,0,0,0,0,0,0,0,1,0 };
		matriz_pesos_CB_actividades.add(tipo_D_);
		double[] tipo_GS = { 0,0,0,0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,0,0,0,1, 0,0,0,0,0,0,0,0,0,0,1 };
		matriz_pesos_CB_actividades.add(tipo_GS);
		double[] MANANA_ = { 1,1,1,1,1,1,1,1,1,1,1, 0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0 };
		matriz_pesos_CB_actividades.add(MANANA_);
		double[] NOCHE__ = { 0,0,0,0,0,0,0,0,0,0,0, 1,1,1,1,1,1,1,1,1,1,1, 0,0,0,0,0,0,0,0,0,0,0 };
		matriz_pesos_CB_actividades.add(NOCHE__);
		double[] FINES__ = { 0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0, 1,1,1,1,1,1,1,1,1,1,1 };
		matriz_pesos_CB_actividades.add(FINES__);
		double[] cardio_ = { 0,1,0,0,1,1,0,1,0,1,0, 0,1,0,0,1,1,0,1,0,1,0, 0,1,0,0,1,1,0,1,0,1,0 };
		matriz_pesos_CB_actividades.add(cardio_);
		double[] estiram = { 1,0,1,0,0,0,0,0,1,0,1, 1,0,1,0,0,0,0,0,1,0,1, 1,0,1,0,0,0,0,0,1,0,1 };
		matriz_pesos_CB_actividades.add(estiram);
		double[] fuerza_ = { 0,0,0,1,0,0,0,0,1,0,1, 0,0,0,1,0,0,0,0,1,0,1, 0,0,0,1,0,0,0,0,1,0,1 };
		matriz_pesos_CB_actividades.add(fuerza_);
		double[] ninos__ = { 0,0,0,0,0,0,1,0,0,0,0, 0,0,0,0,0,0,1,0,0,0,0, 0,0,0,0,0,0,1,0,0,0,0 };
		matriz_pesos_CB_actividades.add(ninos__);
		
		normalizarMatrizDC();

		// Transpuesta
		calcularMatrizItemDC();
		
		calcularIDF();
	}

	public void normalizarMatrizDC() {
		double[] contadores = new double[matriz_pesos_CB_actividades.get(0).length];
		for (int i = 0; i < contadores.length; i++) {
			contadores[i] = 0;
		}
		for (int i = 0; i < matriz_pesos_CB_actividades.size(); i++) {
			for (int j = 0; j < matriz_pesos_CB_actividades.get(i).length; j++) {
				contadores[j] += matriz_pesos_CB_actividades.get(i)[j];
			}
		}
		for (int i = 0; i < matriz_pesos_CB_actividades.size(); i++) {
			double[] normalizada = new double[matriz_pesos_CB_actividades.get(i).length];
			for (int j = 0; j < matriz_pesos_CB_actividades.get(i).length; j++) {
				normalizada[j] = matriz_pesos_CB_actividades.get(i)[j] / Math.sqrt(contadores[j]);
			}
			matriz_pesos_CB_actividades.set(i, normalizada);
		}
	}

	public void calcularMatrizItemDC() {
		for (int i = 0; i < matriz_pesos_CB_actividades.get(0).length; i++) {
			double[] item_pesos = new double[matriz_pesos_CB_actividades.size()];
			for (int j = 0; j < matriz_pesos_CB_actividades.size(); j++) {
				item_pesos[j] = matriz_pesos_CB_actividades.get(j)[i];
			}
			matriz_item_pesos_CB_actividades.add(item_pesos);
		}
	}
	
	public void calcularIDF() {
		arreglo_IDF = new double[matriz_pesos_CB_actividades.size()];
		for (int i = 0; i < arreglo_IDF.length; i++) {
			int df = 0;
			for (int j = 0; j < matriz_pesos_CB_actividades.get(i).length; j++) {
				if(matriz_pesos_CB_actividades.get(i)[j] > 0) {
					df ++;
				}
			}
			double div = matriz_pesos_CB_actividades.get(i).length/df;
			arreglo_IDF[i] = Math.log10(div);
		}
	}

	public ArrayList<String> darParquesNegativos(int id,Connection conn) {


		try{
			ArrayList<String> resp = new  ArrayList<String>();
			String search = "SELECT * FROM matriz WHERE id=" + id + ";";
			//System.out.println(search);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(search);

			while (rs.next()) {
				for (int i = 0; i < nombresParques.length; i++) {
					String parqueColumn = Normalizer.normalize(nombresParques[i], Normalizer.Form.NFD);
					parqueColumn = parqueColumn.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
					parqueColumn = parqueColumn.replace(" ", "_");
					parqueColumn = parqueColumn.toLowerCase();

					int calificacion = rs.getInt(parqueColumn);

					if(calificacion < 0) {
						resp.add(nombresParques[i]);
					}
				}
			}
			//System.out.println("parques negativos");
			//System.out.println(resp);
			return resp;
		}
		catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return null;
	}

	//Pearson correlation coefficient
	public static double pcc(double[] a, double[] b) {
		double average_a = 0;
		double number_rated_a = 0;
		double average_b = 0;
		double number_rated_b = 0;
		double numerator = 0;
		double denominator_a = 0;
		double denominator_b = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] != 0) {//Item rated by a
				average_a += a[i];
				number_rated_a ++;
			}
			if(b[i] != 0) {//Item rated by b
				average_b += b[i];
				number_rated_b ++;
			}
		}
		average_a = average_a / number_rated_a;
		average_b = average_b / number_rated_b;
		for(int i = 0; i < a.length; i++){
			if(a[i] != 0 && b[i] != 0) {//Item rated by both a and b
				numerator += (a[i] - average_a)*(b[i] - average_b);
				denominator_a += Math.pow((a[i] - average_a),2);
				denominator_b += Math.pow((b[i] - average_b),2);
			}
		}
		double denominator = (Math.sqrt(denominator_a)*Math.sqrt(denominator_b));
		double result = 0;
		if(denominator != 0)  {
			result = numerator / denominator;
		}
		return result;
	}

	//Cosine similarity
	public double cos(double[] a, double[] b) {
		double numerator = 0;
		double magnitude_a = 0;
		double magnitude_b = 0;
		for(int i = 0; i < a.length; i++){
			numerator += a[i]*b[i];
			magnitude_a += Math.pow(a[i],2);
			magnitude_b += Math.pow(b[i],2);
		}
		magnitude_a = (Math.sqrt(magnitude_a));
		magnitude_b = (Math.sqrt(magnitude_b));
		double result = numerator / (magnitude_a*magnitude_b);
		return result;
	}

	//Constrained Pearson correlation coefficient
	public static double cpcc(double[] a, double[] b) {
		double median_rating = 3;
		double numerator = 0;
		double denominator_a = 0;
		double denominator_b = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] != 0 && b[i] != 0) {//Item rated by both a and b
				numerator += (a[i] - median_rating)*(b[i] - median_rating);
				denominator_a += Math.pow((a[i] - median_rating),2);
				denominator_b += Math.pow((b[i] - median_rating),2);
			}
		}
		double result = numerator / (Math.sqrt(denominator_a)*Math.sqrt(denominator_b));
		return result;
	}

	//Weighted Pearson correlation coefficient
	public static double wpcc(double[] a, double[] b) {
		double number_items_rated_by_both = 0;
		double threshold = 50;
		for(int i = 0; i < a.length; i++){
			if(a[i] != 0 && b[i] != 0) {//Item rated by both a and b
				number_items_rated_by_both ++;
			}
		}
		double result = pcc(a, b);
		if(number_items_rated_by_both <= threshold) {
			result = result * number_items_rated_by_both / threshold;
		}
		return result;
	}

	//Sigmoid function based Pearson correlation coefficient
	public static double spcc(double[] a, double[] b) {
		double number_items_rated_by_both = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] != 0 && b[i] != 0) {//Item rated by both a and b
				number_items_rated_by_both ++;
			}
		}
		double result = pcc(a, b) / (1 + Math.exp(-number_items_rated_by_both/2));
		return result;
	}

	//Adjusted cosine measure
	public static double acos(double[] a, double[] b) {
		double average_a = 0;
		double number_rated_a = 0;
		double average_b = 0;
		double number_rated_b = 0;
		double numerator = 0;
		double denominator_a = 0;
		double denominator_b = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] != 0) {//Item rated by a
				average_a += a[i];
				number_rated_a ++;
			}
			if(b[i] != 0) {//Item rated by b
				average_b += b[i];
				number_rated_b ++;
			}
		}
		average_a = average_a / number_rated_a;
		average_b = average_b / number_rated_b;
		for(int i = 0; i < a.length; i++){
			numerator += (a[i] - average_a)*(b[i] - average_b);
			denominator_a += Math.pow((a[i] - average_a),2);
			denominator_b += Math.pow((b[i] - average_b),2);
		}
		double denominator = (Math.sqrt(denominator_a)*Math.sqrt(denominator_b));
		double result;
		if(denominator == 0) {
			result = 0;
		}
		else {
			result = numerator / denominator;
		}
		return result;
	}

	//Jaccard
	public static double jaccard(double[] a, double[] b) {
		double number_rated_a_or_b = 0;
		double number_rated_a_and_b = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] != 0 || b[i] != 0) {
				number_rated_a_or_b ++;
			}
			if(a[i] != 0 && b[i] == a[i]){
				number_rated_a_and_b ++;
			}
		}
		double result = 0;
		if(number_rated_a_or_b != 0) {
			result = number_rated_a_and_b / number_rated_a_or_b;
		}
		return result;
	}

	//Mean squared difference
	public static double msd(double[] a, double[] b) {
		double numerator = 0;
		double number_rated_a_and_b = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] != 0 && b[i] != 0){
				numerator += Math.pow(a[i] - b[i], 2);
				number_rated_a_and_b ++;
			}
		}
		double result = (numerator / number_rated_a_and_b);
		return result;
	}

	public void seleccionarUsuariosPrueba(double porcentaje) {
		matriz_C_prueba = new ArrayList<double[]>();
		matriz_C_entrenamiento = new ArrayList<double[]>(matriz_C);
		matriz_DC_prueba = new ArrayList<double[]>();
		matriz_DC_entrenamiento = new ArrayList<double[]>(matriz_DC);

		Random random = new Random();
		int cantidad = (int) (matriz_C.size()*porcentaje);
		for(int i = 0; i < cantidad; i++) {
			int indice = random.nextInt(matriz_C_entrenamiento.size());

			double[] prueba_C = matriz_C_entrenamiento.remove(indice);
			matriz_C_prueba.add(prueba_C);
		}
	}
	
	public void seleccionarUsuariosPruebaDC(double porcentaje) {
		matriz_C_prueba = new ArrayList<double[]>();
		matriz_C_entrenamiento = new ArrayList<double[]>(matriz_C);
		matriz_DC_prueba = new ArrayList<double[]>();
		matriz_DC_entrenamiento = new ArrayList<double[]>(matriz_DC);

		Random random = new Random();
		int cantidad = (int) (matriz_C.size()*porcentaje);
		for(int i = 0; i < cantidad; i++) {
			int indice = random.nextInt(matriz_C_entrenamiento.size());

			double[] prueba_C = matriz_C_entrenamiento.remove(indice);
			matriz_C_prueba.add(prueba_C);
			double[] prueba_DC = matriz_DC_entrenamiento.remove(indice);
			matriz_DC_prueba.add(prueba_DC);
		}
	}

	public double[] hacerPruebaC() {
		int truePositives = 0;
		int trueNegatives = 0;
		int falsePositives = 0;
		int falseNegatives = 0;
		int noPredictionPositive = 0;
		int noPredictionNegative = 0;
		
		double MAE = 0;
		
		Random random = new Random();
		for (int i = 0; i < matriz_C_prueba.size(); i++) {
			double[] userC = matriz_C_prueba.get(i);
			ArrayList<Vecino> vecinosC = seleccionarIndiceVecinosCPrueba(20, userC);
			
			int indiceItem = random.nextInt(userC.length);
			double rating = userC[indiceItem];
			while(rating == 0) {
				System.out.println("inside while");
				indiceItem = random.nextInt(userC.length);
				rating = userC[indiceItem];
			}
			
			userC[indiceItem] = 0;
			
			double sugerencia_C = darSugerenciaActividadCPrueba(userC, indiceItem, vecinosC);
			if(sugerencia_C >= 0) {
				if(rating >= 0) {
					truePositives ++;
				}
				else {
					falsePositives ++;
				}
			}
			else {
				if(rating < 0) {
					trueNegatives ++;
				}
				else {
					falseNegatives ++;
				}
			}
			double resta_C = sugerencia_C - rating;
			MAE += Math.abs(resta_C);
		}
		
		MAE = MAE/matriz_C_prueba.size();
		double[] resp = {truePositives, trueNegatives, falsePositives, falseNegatives, noPredictionPositive, noPredictionNegative, MAE};
		return resp;
	}
	
	public double[] hacerPruebaCB() {
		int truePositives = 0;
		int trueNegatives = 0;
		int falsePositives = 0;
		int falseNegatives = 0;
		int noPredictionPositive = 0;
		int noPredictionNegative = 0;
		
		double MAE = 0;
		
		Random random = new Random();
		
		int cantidadActividadesProbadas = 0;
		
		for (int i = 0; i < matriz_C.size(); i++) {
			double[] user_C = matriz_C.get(i);
			int cantidadCalificaciones = 0;
			for (int j = 0; j < user_C.length; j++) {
				if(user_C[j] != 0) {
					cantidadCalificaciones ++;
				}
			}
			int cantidadPruebasUsuario = (int) Math.floor(cantidadCalificaciones*0.3);
			if(cantidadPruebasUsuario == 0) {
				cantidadPruebasUsuario = 1;
			}
			double[] ratings = new double[cantidadPruebasUsuario];
			int[] indiceItems = new int[cantidadPruebasUsuario];
			for (int j = 0; j < cantidadPruebasUsuario; j++) {
				int indiceItem = random.nextInt(user_C.length);
				double rating = user_C[indiceItem];
				while(rating == 0) {
					indiceItem = random.nextInt(user_C.length);
					rating = user_C[indiceItem];
				}
				ratings[j] = rating;
				indiceItems[j] = indiceItem;
			}
			for (int j = 0; j < cantidadPruebasUsuario; j++) {
				cantidadActividadesProbadas ++;
				int indiceItem = indiceItems[j];
				double rating = ratings[j];
				user_C[indiceItem] = 0;
				
			
				double[] user_CB = getUserCBPrueba(matriz_C.get(i));
				
				double sugerencia_DC = darSugerenciaActividadCBPrueba(user_CB, indiceItem);
				
				if(sugerencia_DC >= 0) {
					if(rating >= 0) {
						truePositives ++;
					}
					else {
						falsePositives ++;
					}
				}
				else {
					if(rating < 0) {
						trueNegatives ++;
					}
					else {
						falseNegatives ++;
					}
				}
				double resta_C = sugerencia_DC - rating;
				MAE += Math.abs(resta_C);
			}
		}
		
		MAE = MAE/cantidadActividadesProbadas;
		double[] resp = {truePositives, trueNegatives, falsePositives, falseNegatives, noPredictionPositive, noPredictionNegative, MAE};
		return resp;
	}
	
	public double[] hacerPruebaDC() {
		int pred0_real0 = 0;
		int pred0_real1 = 0;
		int pred0_real2 = 0;
		int pred1_real0 = 0;
		int pred1_real1 = 0;
		int pred1_real2 = 0;
		int pred2_real0 = 0;
		int pred2_real1 = 0;
		int pred2_real2 = 0;
		double MAE = 0;
		
		Random random = new Random();
		
		for (int i = 0; i < matriz_DC_entrenamiento.size(); i++) {
			double[] userDC = matriz_DC_entrenamiento.get(i);
			double[] userC = matriz_C_entrenamiento.get(i);
			
			ArrayList<Vecino> vecinos = seleccionarIndiceVecinosDCPrueba(20, userDC);
			
//			int indiceItem = random.nextInt(userC.length);
//			double rating = userC[indiceItem];
//			while(rating == 0) {
//				indiceItem = random.nextInt(userC.length);
//				rating = userC[indiceItem];
//			}
			int indiceItem = 0;
			double distancia = userC[indiceItem];
			
			userC[indiceItem] = 0;
			
			double sug = darSugerenciaActividadDCPrueba(userDC, userC, indiceItem, vecinos);
			
			if(sug <= 2) {
				if(distancia <= 2) {
					pred0_real0 ++;
				}
				else if(sug <=5 ) {
					pred0_real1 ++;
				}
				else {
					pred0_real2 ++;
				}
			}
			else if(sug <= 5) {
				if(distancia <= 2) {
					pred1_real0 ++;
				}
				else if(sug <=5 ) {
					pred1_real1 ++;
				}
				else {
					pred1_real2 ++;
				}
			}
			else {
				if(distancia <= 2) {
					pred2_real0 ++;
				}
				else if(sug <=5 ) {
					pred2_real1 ++;
				}
				else {
					pred2_real2 ++;
				}
			}
			double resta_C = sug - distancia;
			MAE += Math.abs(resta_C);
		}
		
		MAE = MAE/matriz_DC_entrenamiento.size();
		double[] resp = {pred0_real0, pred0_real1, pred0_real2, pred1_real0, pred1_real1, pred1_real2, pred2_real0, pred2_real1, pred2_real2, MAE};
		return resp;
	}
	
	public String arrayToString(double[] array) {
		String result = "{";
		for (int i = 0; i < array.length; i++) {
			result += array[i];
			if (i < array.length - 1) {
				result += ",";
			}
		}
		result += "}";
		return result;
	}
}
