package datos;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pagosCualificaciones.PagosCualificacionesImpl;
import sensores.SensoresImpl;

class testGuardarAlmacenarValoracion {

	SensoresImpl sensores;
	Plato primero;
	Plato segundo;
	Plato postre;
	Bebida bebida;
	Bandeja bandeja;
	HashMap<Plato,Integer> valoraciones;
	
	
	@BeforeEach
	void setUp() throws Exception {
		sensores=new SensoresImpl();
		primero=new Plato("primero","sopa fria");
		segundo=new Plato("seguno","patatas fritas");
		postre=new Plato("postre","croquetas");
		bebida=new Bebida("agua");
		bandeja=new Bandeja("715338501","V746813460",primero,segundo,postre,bebida);
		
		valoraciones=new HashMap<>();
		valoraciones.put(primero, 5);
		valoraciones.put(segundo, 2);
		valoraciones.put(postre, 3);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@DisplayName("CP26: Test integracion: se permite valorar los platos y se escribe la puntuación en el archivo.")
	@Test
	void testRealizarValoracion() {
		
		JSONObject objPlatos = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
			objPlatos = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray platosArr = objPlatos.getJSONArray("baseEstadistica");
		int x = platosArr.length() - 1;
		
		PagosCualificacionesImpl pC = new PagosCualificacionesImpl(sensores);
		
		pC.guardarValoracion(bandeja, valoraciones);
		
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
			objPlatos = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		platosArr = objPlatos.getJSONArray("baseEstadistica");
		int y;
		y = platosArr.length() - 1;
		
		assertEquals(3, y - x); //Vemos si aumenta el contenido del array de platos (3 valoraciones nuevas)
	}
	
	@DisplayName("CP27: Test integracion: no se permite valorar los platos y no se escribe la puntuación en el archivo.")
	@Test
	void testRealizarValoracionNegativa() {
		JSONObject objPlatos = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
			objPlatos = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray platosArr = objPlatos.getJSONArray("baseEstadistica");
		int x = platosArr.length() - 1;
		
		PagosCualificacionesImpl pC = new PagosCualificacionesImpl(sensores);
		
		valoraciones.put(primero, -2);
		
		pC.guardarValoracion(bandeja, valoraciones);
		
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
			objPlatos = new JSONObject(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		platosArr = objPlatos.getJSONArray("baseEstadistica");
		int y;
		y = platosArr.length() - 1;
		
		assertNotEquals(3, y - x); //Vemos que no aumenta el contenido del array de platos
	}

}
