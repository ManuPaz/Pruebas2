package gestionDatos;

import datos.Menu;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import datos.Bandeja;
import datos.BaseEstadisticas;
import datos.Bebida;
import datos.Plato;

class testGestionDatosImpl {
	String dia;
	ArrayList<Plato> primeros = new ArrayList<>();
	ArrayList<Plato> segundos = new ArrayList<>();
	ArrayList<Plato> postres = new ArrayList<>();
	ArrayList<Bebida> bebidas = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		dia = "Lunes";
		Plato plato = new Plato("primero", "Macarrones con queso");
		primeros.add(plato);
		plato = new Plato("primero", "Atún con queso");
		primeros.add(plato);
		plato = new Plato("primero", "Sopa fría");
		primeros.add(plato);
		plato = new Plato("segundo", "Pollo asado");
		segundos.add(plato);
		plato = new Plato("segundo", "Judías");
		segundos.add(plato);
		plato = new Plato("segundo", "Patatas fritas");
		segundos.add(plato);
		plato = new Plato("postre", "flan de huevo");
		postres.add(plato);
		plato = new Plato("postre", "helado de fresa");
		postres.add(plato);
		plato = new Plato("postre", "flan de café");
		postres.add(plato);
		Bebida bebida = new Bebida("agua");
		bebidas.add(bebida);
		bebida = new Bebida("Coca cola");
		bebidas.add(bebida);

	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@DisplayName("CP06: Test almacenarMenu: se escribe un menu en el archivo.")
	@Test
	void testAlmacenarMenuCorrecto() {
		JSONObject objMenus = null, objPlatos = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray menusArr = objMenus.getJSONArray("menu");
		int x = menusArr.length() - 1;
		int y;
		Menu menu = new Menu(primeros, segundos, postres, bebidas, dia);
		GestionDatosImpl gD = new GestionDatosImpl();
		gD.almacenarMenu(menu);
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		menusArr = objMenus.getJSONArray("menu");
		y = menusArr.length() - 1;
		assertEquals(1, y - x);
	}
	@Test
	void test() {
		Bandeja bandeja=null;
		Plato Plato=new Plato("a","primero");	
		Bebida n=new Bebida("a");
		bandeja=new  Bandeja("12", "12", Plato, Plato ,Plato,n);
	
	HashMap <Plato,Integer> p1=new HashMap();
	p1.put(Plato,1);
	bandeja.getEstadisticas().setValoracionesPlatos(p1);
	
		JSONObject objBandeja = null,objBase = null,objPlatos= null;
	
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
			objBandeja = new JSONObject(content);
			String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
			objBase = new JSONObject(content2);
			String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
			objPlatos = new JSONObject(content3);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		JSONObject bandejaJ = new JSONObject();
		bandejaJ.put("ID",Integer.parseInt(bandeja.getId()));
		bandejaJ.put("primeroSeleccion",bandeja.getPrimeroSeleccionado().getNombre());
		bandejaJ.put("segundoSeleccion",bandeja.getSegundoSeleccionado().getNombre());
		bandejaJ.put("postreSeleccion",bandeja.getPostreSeleccionado().getNombre());
		bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
		bandejaJ.put("idVale", bandeja.getIdVale());
		
		
		objBandeja.getJSONArray("bandeja").put(bandejaJ);
		while(objBandeja.getJSONArray("bandeja").length()>1) {
			objBandeja.getJSONArray("bandeja").remove(0);
		}
		System.out.println(objBandeja.getJSONArray("bandeja").length());
		
		JSONObject baseEstJ = new JSONObject();
		baseEstJ.put("horaDevolucion",bandeja.getEstadisticas().getHoraDevolucion());
		baseEstJ.put("horaAsignacion",bandeja.getEstadisticas().getHoraAsignacion());
		baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
		baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
		
		JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");
		JSONArray platoArr = objPlatos.getJSONArray("plato");
		for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
		    Plato p = entry.getKey();
		    Integer i= entry.getValue();
		    JSONObject base = new JSONObject();
		    base.put("plato", p.getNombre());
		    base.put("nota", i);
		    base.put("horaDevolucion",bandeja.getEstadisticas().getHoraDevolucion());
			base.put("horaAsignacion",bandeja.getEstadisticas().getHoraAsignacion());
			base.put("dia", bandeja.getEstadisticas().getDia());
			base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
		    valoraciones.put(base);
		    //Actualizacion de las notas
		   
		}
		
		
		
		
				
		
		
		
		try {
			//Aqui se guardan los datos sobreescribiendo los archivos
			File archivoB = new File("./src/bandeja.json");
	        FileWriter archivoStreamB = new FileWriter(archivoB, false);
	        archivoStreamB.write(objBandeja.toString());
	        archivoStreamB.close();
	        //Esto esta sin probar
	        File archivoBS = new File("./src/baseEstadistica.json");
	        FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
	        archivoStreamBS.write(objBase.toString());
	        archivoStreamBS.close();
	        
	       
	        
		
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	//almacenar valoracion


	@DisplayName("CP19: Test almacenarValoracion: se escribe una valoracion en el archivo.")
		@Test
		void testAlmacenarValoracion() {
			JSONObject objValoracion = null;
			JSONArray valoracionArray = null;
			int x, y;
			Bandeja bandeja;
			GestionDatosImpl gD = new GestionDatosImpl();
			BaseEstadisticas estadistica;
			
			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objValoracion = new JSONObject(content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			valoracionArray = objValoracion.getJSONArray("baseEstadistica");
			x = valoracionArray.length() - 1;
			
			String codigoBandeja = "23";
			String codigoVale ="12";	
			bandeja = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"), new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
			estadistica = new BaseEstadisticas(new Date().toString().substring(11, 20),new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			bandeja.setEstadisticas(estadistica);
			gD.almacenarValoracion(bandeja);
			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objValoracion = new JSONObject(content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			valoracionArray = objValoracion.getJSONArray("baseEstadistica");
			y = valoracionArray.length() - 1;
			assertEquals(1, y - x, "No se ha escrito la valoración en el archivo correspondiente");
		}
		
		
		@DisplayName("CP20: Test almacenarValoracion: se escribe una valoracion en el archivo.")
		@Test
		void testAlmacenarValoracionNula() {
			JSONObject objValoracion = null;
			JSONArray valoracionArray = null;
			int x, y;
			Bandeja bandeja;
			GestionDatosImpl gD = new GestionDatosImpl();
			
			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objValoracion = new JSONObject(content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			valoracionArray = objValoracion.getJSONArray("baseEstadistica");
			x = valoracionArray.length() - 1;
			
			bandeja = null;
			gD.almacenarValoracion(bandeja);
			
			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objValoracion = new JSONObject(content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			valoracionArray = objValoracion.getJSONArray("baseEstadistica");
			y = valoracionArray.length() - 1;
			assertEquals(y, x, "Se ha escrito una valoración nula");
		}

}
