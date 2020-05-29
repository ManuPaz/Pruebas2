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
import org.mockito.InjectMocks;

import codigoPrincipal.IGestionMenus;
import gestionDatos.GestionDatosImpl;
import gestionMenus.GestionMenusImpl;


class testMenuDatos {
	
	String dia;
	ArrayList<Plato> primeros=new ArrayList<>();
	ArrayList<Plato> segundos=new ArrayList<>();
	ArrayList<Plato> postres=new ArrayList<>();
	ArrayList<Bebida> bebidas=new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		
		dia = "Lunes";
		Plato plato = new Plato("primero", "Macarrones con queso");
		primeros.add(plato);
		plato = new Plato("primero", "Atï¿½n con queso");
		primeros.add(plato);
		plato = new Plato("primero", "Sopa frï¿½a");
		primeros.add(plato);
		plato = new Plato("segundo", "Pollo asado");
		segundos.add(plato);
		plato = new Plato("segundo", "Judï¿½as");
		segundos.add(plato);
		plato = new Plato("segundo", "Patatas fritas");
		segundos.add(plato);
		plato = new Plato("postre", "flan de huevo");
		postres.add(plato);
		plato = new Plato("postre", "helado de fresa");
		postres.add(plato);
		plato = new Plato("postre", "flan de cafï¿½");
		postres.add(plato);
		Bebida bebida = new Bebida("agua");
		bebidas.add(bebida);
		bebida = new Bebida("Coca cola");
		bebidas.add(bebida);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Nested
	@DisplayName("Prueba 7: Prueba de integración de los métodos construirMenu y almacenarMenu")
	class P{
	@DisplayName("CP21: Test integracion: se crea y se escribe un menu en el archivo.")
	@Test
	void testAlmacenarMenuConstruido() {
		
		JSONObject objMenus = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray menusArr = objMenus.getJSONArray("menu");
		int x = menusArr.length() - 1;
		
		GestionDatosImpl gD = new GestionDatosImpl();
		GestionMenusImpl mG = new GestionMenusImpl(gD);
		mG.construirMenu(dia, primeros, segundos, postres, bebidas);
		
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		menusArr = objMenus.getJSONArray("menu");
		int y;
		y = menusArr.length() - 1;
		assertEquals(1, y - x); //Vemos si aumentï¿½ el contenido del fichero de menus
	}
	
	@DisplayName("CP22: Test integracion: sin bebidas.")
	@Test
	void testAlmacenarMenuConstruidoBebidaNula() {
		
		JSONObject objMenus = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray menusArr = objMenus.getJSONArray("menu");
		int x = menusArr.length() - 1;
		
		GestionDatosImpl gD = new GestionDatosImpl();
		GestionMenusImpl mG = new GestionMenusImpl(gD);
		bebidas.clear();
		mG.construirMenu(dia, primeros, segundos, postres, bebidas);
		
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		menusArr = objMenus.getJSONArray("menu");
		int y;
		y = menusArr.length() - 1;
		assertNotEquals(1, y - x); //Vemos que no aumentï¿½ el contenido del fichero de menus
	}
	
	@DisplayName("CP23: Test integracion: dia no laborable.")
	@Test
	void testAlmacenarMenuConstruidoNoLaborable() {
		
		JSONObject objMenus = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray menusArr = objMenus.getJSONArray("menu");
		int x = menusArr.length() - 1;
		
		GestionDatosImpl gD = new GestionDatosImpl();
		GestionMenusImpl mG = new GestionMenusImpl(gD);
		dia = "Domingo";
		mG.construirMenu(dia, primeros, segundos, postres, bebidas);
		
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		menusArr = objMenus.getJSONArray("menu");
		int y;
		y = menusArr.length() - 1;
		assertNotEquals(1, y - x); //Vemos que no aumentï¿½ el contenido del fichero de menus
	}
	
	@DisplayName("CP24: Test integracion: con dos segundos solo.")
	@Test
	void testNoAlmacenarMenuConstruido() {
		
		JSONObject objMenus = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray menusArr = objMenus.getJSONArray("menu");
		int x = menusArr.length() - 1;
		
		GestionDatosImpl gD = new GestionDatosImpl();
		GestionMenusImpl mG = new GestionMenusImpl(gD);
		bebidas.clear();
		segundos.remove(2);
		mG.construirMenu(dia, primeros, segundos, postres, bebidas);
		
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		menusArr = objMenus.getJSONArray("menu");
		int y;
		y = menusArr.length() - 1;
		assertNotEquals(1, y - x); //Vemos que no aumentï¿½ el contenido del fichero de menus
	}
	
	@DisplayName("CP25: Test integracion: con un dia inexistente.")
	@Test
	void testAlmacenarMenuConstruidoDiaErroneo() {
		
		JSONObject objMenus = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray menusArr = objMenus.getJSONArray("menu");
		int x = menusArr.length() - 1;
		
		GestionDatosImpl gD = new GestionDatosImpl();
		GestionMenusImpl mG = new GestionMenusImpl(gD);
		bebidas.remove(1);
		bebidas.add(new Bebida("agua"));
		dia = "Maandag";
		mG.construirMenu(dia, primeros, segundos, postres, bebidas);
		
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		menusArr = objMenus.getJSONArray("menu");
		int y;
		y = menusArr.length() - 1;
		assertNotEquals(1, y - x); //Vemos que no aumentï¿½ el contenido del fichero de menus
	}
	}

}
