package gestionDatos;
import datos.Menu;
import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datos.Bebida;
import datos.Plato;

class testGestionDatosImpl {
	String dia;
	ArrayList<Plato> primeros=new ArrayList<>();
	ArrayList<Plato> segundos=new ArrayList<>();
	ArrayList<Plato> postres=new ArrayList<>();
	ArrayList<Bebida> bebidas=new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		dia="Lunes";
		Plato plato=new Plato("primero","Macarrones con queso");
		primeros.add(plato);
		plato=new Plato("primero","Atún con queso");
		primeros.add(plato);
		plato=new Plato("primero","Sopa fría");
		primeros.add(plato);
		plato=new Plato("segundo","Pollo asado");
		segundos.add(plato);
		plato=new Plato("segundo","Judías");
		segundos.add(plato);
		plato=new Plato("segundo","Patatas fritas");
		segundos.add(plato);
		plato=new Plato("postre","flan de huevo");
		postres.add(plato);
		plato=new Plato("postre","helado de fresa");
		postres.add(plato);
		plato=new Plato("postre","flan de café");
		postres.add(plato);
		Bebida bebida=new Bebida("agua");
		bebidas.add(bebida);
		bebida=new Bebida("Coca cola");
		bebidas.add(bebida);
		
		
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
	
	}

	@Test
	void testAlmacenarMenuCorrecto() {
		JSONObject objMenus = null,objPlatos = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		}catch(Exception e) {
			e.printStackTrace();
		}
			JSONArray menusArr = objMenus.getJSONArray("menu");
			int x=menusArr.length()-1;
			int y;
		Menu menu=new Menu(primeros,segundos,postres,bebidas,dia);
		GestionDatosImpl gD=new GestionDatosImpl();
		gD.almacenarMenu(menu);
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		}catch(Exception e) {
			e.printStackTrace();
		}
		 menusArr = objMenus.getJSONArray("menu");
		 y=menusArr.length()-1;
		assertEquals(1, y-x);
				
	}

}
