package datos;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import codigoPrincipal.IGestionDatos;
import gestionDatos.GestionDatosImpl;
import gestionMenus.GestionMenusImpl;

class testGestionMenuDatos {

	String dia;
	IGestionDatos datos;
	GestionMenusImpl gM;
	
	ArrayList<Plato> primeros1 = new ArrayList<>();
	ArrayList<Plato> segundos1 = new ArrayList<>();
	ArrayList<Plato> postres1 = new ArrayList<>();
	ArrayList<Bebida> bebidas1 = new ArrayList<>();
	
	ArrayList<Plato> primeros2 = new ArrayList<>();
	ArrayList<Plato> segundos2 = new ArrayList<>();
	ArrayList<Plato> postres2 = new ArrayList<>();
	ArrayList<Bebida> bebidas2 = new ArrayList<>();
	
	ArrayList<Plato> primeros3 = new ArrayList<>();
	ArrayList<Plato> segundos3 = new ArrayList<>();
	ArrayList<Plato> postres3 = new ArrayList<>();
	ArrayList<Bebida> bebidas3 = new ArrayList<>();
	
	ArrayList<Plato> primeros4 = new ArrayList<>();
	ArrayList<Plato> segundos4 = new ArrayList<>();
	ArrayList<Plato> postres4 = new ArrayList<>();
	ArrayList<Bebida> bebidas4 = new ArrayList<>();
	
	ArrayList<Plato> primeros5 = new ArrayList<>();
	ArrayList<Plato> segundos5 = new ArrayList<>();
	ArrayList<Plato> postres5 = new ArrayList<>();
	ArrayList<Bebida> bebidas5 = new ArrayList<>();
	
	ArrayList<Plato> primeros6 = new ArrayList<>();
	ArrayList<Plato> segundos6 = new ArrayList<>();
	ArrayList<Plato> postres6 = new ArrayList<>();
	ArrayList<Bebida> bebidas6 = new ArrayList<>();
	
	@BeforeEach
	void setUp() throws Exception {
		datos=new GestionDatosImpl();
		gM = new GestionMenusImpl(datos);
		Plato plato = new Plato("primero", "Macarrones con queso");
		primeros1.add(plato);
		plato = new Plato("primero", "Atún con queso");
		primeros1.add(plato);
		plato = new Plato("primero", "Sopa fría");
		primeros1.add(plato);
		plato = new Plato("segundo", "Pollo asado");
		segundos1.add(plato);
		plato = new Plato("segundo", "Judías");
		segundos1.add(plato);
		plato = new Plato("segundo", "Patatas fritas");
		segundos1.add(plato);
		plato = new Plato("postre", "flan de huevo");
		postres1.add(plato);
		plato = new Plato("postre", "helado de fresa");
		postres1.add(plato);
		plato = new Plato("postre", "flan de café");
		postres1.add(plato);
		Bebida bebida = new Bebida("agua");
		bebidas1.add(bebida);
		bebida = new Bebida("Coca cola");
		bebidas1.add(bebida);
		

		plato = new Plato("primero", "Ensaladilla");
		primeros2.add(plato);
		plato = new Plato("primero", "Ensalada");
		primeros2.add(plato);
		plato = new Plato("segundo", "Paella");
		segundos2.add(plato);
		plato = new Plato("segundo", "Milanesa");
		segundos2.add(plato);
		plato = new Plato("segundo", "Pechuga");
		segundos2.add(plato);
		plato = new Plato("postre", "Fresas");
		postres2.add(plato);
		plato = new Plato("postre", "Naranja");
		postres2.add(plato);
		plato = new Plato("postre", "Cerezas");
		postres2.add(plato);
		bebida = new Bebida("agua");
		bebidas2.add(bebida);
		bebida = new Bebida("Fanta");
		bebidas2.add(bebida);
		

		plato = new Plato("primero", "Ensaladilla");
		primeros3.add(plato);
		plato = new Plato("primero", "Empanadilla");
		primeros3.add(plato);
		plato = new Plato("segundo", "Paella");
		segundos3.add(plato);
		plato = new Plato("segundo", "Milanesa");
		segundos3.add(plato);
		plato = new Plato("segundo", "Churrasco");
		segundos3.add(plato);
		plato = new Plato("postre", "Flan de cafe");
		postres3.add(plato);
		plato = new Plato("postre", "Naranja");
		postres3.add(plato);
		plato = new Plato("postre", "Cerezas");
		postres3.add(plato);
		bebida = new Bebida("Cocacola");
		bebidas3.add(bebida);
		bebida = new Bebida("Fanta");
		bebidas3.add(bebida);
		

		plato = new Plato("primero", "Empanada");
		primeros4.add(plato);
		plato = new Plato("primero", "Ensalada de pasta");
		primeros4.add(plato);
		plato = new Plato("segundo", "Pizza hawaiana");
		segundos4.add(plato);
		plato = new Plato("segundo", "Milanesa");
		segundos4.add(plato);
		plato = new Plato("segundo", "Merluza");
		segundos4.add(plato);
		plato = new Plato("postre", "Fresas");
		postres4.add(plato);
		plato = new Plato("postre", "Helado");
		postres4.add(plato);
		plato = new Plato("postre", "Cerezas");
		postres4.add(plato);
		bebida = new Bebida("agua");
		bebidas4.add(bebida);
		bebida = new Bebida("Fanta");
		bebidas4.add(bebida);
		

		plato = new Plato("primero", "Lentejas");
		primeros5.add(plato);
		plato = new Plato("primero", "Ensalada de pasta");
		primeros5.add(plato);
		plato = new Plato("segundo", "Pizza barbacoa");
		segundos5.add(plato);
		plato = new Plato("segundo", "Sardinas");
		segundos5.add(plato);
		plato = new Plato("segundo", "Paella");
		segundos5.add(plato);
		plato = new Plato("postre", "Fresas");
		postres5.add(plato);
		plato = new Plato("postre", "Yogurt");
		postres5.add(plato);
		plato = new Plato("postre", "Helado");
		postres5.add(plato);
		bebida = new Bebida("agua");
		bebidas5.add(bebida);
		bebida = new Bebida("Fanta");
		bebidas5.add(bebida);
		

		plato = new Plato("primero", "Empanada");
		primeros6.add(plato);
		plato = new Plato("primero", "Ensaladilla");
		primeros6.add(plato);
		plato = new Plato("segundo", "Pizza barbacoa");
		segundos6.add(plato);
		plato = new Plato("segundo", "Hamburguesa");
		segundos6.add(plato);
		plato = new Plato("segundo", "Paella");
		segundos6.add(plato);
		plato = new Plato("postre", "Fresas");
		postres6.add(plato);
		plato = new Plato("postre", "Cerezas");
		postres6.add(plato);
		plato = new Plato("postre", "Helado");
		postres6.add(plato);
		bebida = new Bebida("agua");
		bebidas6.add(bebida);
		bebida = new Bebida("agua con gas");
		bebidas6.add(bebida);
	}
	@Nested
	@DisplayName("	Prueba 9: Prueba de aceptación de los métodos construir menú y almacenar menú")
	class P{
	
	@DisplayName("P09_CP28")
	@Test
	void testConstAlmacenMenu() {
		JSONObject objMenus = null, objPlatos = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray menusArr = objMenus.getJSONArray("menu");
		int lineasAntes = menusArr.length() - 1;
		int lineasDespues;
		dia = "Lunes";
		gM.construirMenu(dia,primeros1, segundos1, postres1, bebidas1);
		dia = "Martes";
		gM.construirMenu(dia,primeros2, segundos2, postres2, bebidas2);
		dia = "Miercoles";
		gM.construirMenu(dia,primeros3, segundos3, postres3, bebidas3);
		dia = "Jueves";
		gM.construirMenu(dia,primeros4, segundos4, postres4, bebidas4);
		dia = "Viernes";
		gM.construirMenu(dia,primeros5, segundos5, postres5, bebidas5);
		
		
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		menusArr = objMenus.getJSONArray("menu");
		lineasDespues = menusArr.length() - 1;
		assertEquals(5, lineasDespues - lineasAntes);
	}
	
	@DisplayName("P09_CP29")
	@Test
	void testConstAlmacenMenuErr() {
		JSONObject objMenus = null, objPlatos = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray menusArr = objMenus.getJSONArray("menu");
		int lineasAntes = menusArr.length() - 1;
		int lineasDespues;
		dia = "Sabado";
		gM.construirMenu(dia,primeros1, segundos1, postres1, bebidas1);
		dia = "Martes";
		gM.construirMenu(dia,primeros2, segundos2, postres2, bebidas2);
		dia = "Miercoles";
		gM.construirMenu(dia,primeros3, segundos3, postres3, bebidas3);
		dia = "Jueves";
		gM.construirMenu(dia,primeros4, segundos4, postres4, bebidas4);
		dia = "Viernes";
		gM.construirMenu(dia,primeros5, segundos5, postres5, bebidas5);
		dia = "Lunes";
		gM.construirMenu(dia,primeros6, segundos6, postres6, bebidas6);
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		menusArr = objMenus.getJSONArray("menu");
		lineasDespues = menusArr.length() - 1;
		assertEquals(5, lineasDespues - lineasAntes);
	}
	}

}
