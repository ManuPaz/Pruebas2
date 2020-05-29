package datos;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import codigoPrincipal.ISensores;
import estadisticas.EstadisticasImpl;
import gestionDatos.GestionDatosImpl;
import sensores.SensoresImpl;

class testEstadisticasImpl {

	ArrayList<Bandeja> valoraciones = new ArrayList<>();
	Estadisticas result;
	SensoresImpl sensores = new SensoresImpl();
	EstadisticasImpl est = new EstadisticasImpl(sensores);
	Plato plato11, plato21, resultado;

	@BeforeEach
	void setUp() throws Exception {
		plato11 = new Plato("postre", "Flan de cafe");
		float nota1 = (float) 3.5;
		plato11.setNotaMedia(nota1);
		plato21 = new Plato("postre", "Helado de fresa");
		float nota2 = (float) 2.7;
		plato21.setNotaMedia(nota2);

		String codigoBandeja = sensores.generarCodigoBandeja();
		String codigoVale = sensores.generarCodigoVale();

		Bandeja bandeja1 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"),
				new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
		valoraciones.add(bandeja1);
	}

	// Prueba de caja blanca
	@Nested
	@DisplayName("Prueba 11: Prueba del m�todo platoMasSolicitado ")
	class P{
		HashMap<Plato,Integer> platos;
		Plato plato;
		SensoresImpl sensores;
		@InjectMocks
		EstadisticasImpl est;
		
		@BeforeEach
		void setUp() throws Exception {
			platos=new HashMap<>();
			plato=new Plato(" "," ");
			sensores=new SensoresImpl();
			est=new EstadisticasImpl(sensores);
			
		}
	//Prueba de caja blanca
	@DisplayName("P11_CP35")
	@Tag("PlatoMasSolicitado")
	@Tag("CajaBlanca")
	@Test
	void testSolicitadoCP35() {
		//caso de hashmap nulo
		//el plato tiene que ser nulo
		assertNull(est.platoMasSolicitado(null), "El plato devuelto debe ser nulo.");
	}
	
	@DisplayName("P11_CP36")
	@Tag("PlatoMasSolicitado")
	@Tag("CajaBlanca")
	@Test
	
	void testSolicitadoCP36() {
		//caso de integer 0
		plato=new Plato("postre","helado de fresa");
		platos.put(plato,1);
		
		
		//debe devolver un plato distinto (ya que se supone que hay uno en el sistema con mas puntuacion)
		assertNotEquals(plato.getNombre(),est.platoMasSolicitado(platos).getNombre(),"El plato devuelto debe distinto.");
		assertNotEquals(plato.getTipo(),est.platoMasSolicitado(platos).getTipo(),"El plato devuelto debe ser del mismo tipo.");

	}
	
	@DisplayName("P11_CP37")
	@Tag("PlatoMasSolicitado")
	@Tag("CajaBlanca")
	@Test
	
	void testSolicitadoCP37() {
		//caso de 1 valoracion
		plato=new Plato("postre","helado de fresa");
		platos.put(plato,6);
		
		
		//debe devolver el propio plato
		assertEquals(plato.getNombre(),est.platoMasSolicitado(platos).getNombre(),"El plato devuelto debe ser el mismo.");
		assertEquals(plato.getTipo(),est.platoMasSolicitado(platos).getTipo(),"El plato devuelto debe ser del mismo tipo.");
	}
	
	@DisplayName("P11_CP38")
	@Tag("PlatoMasSolicitado")
	@Tag("CajaBlanca")
	@Test
	
	void testSolicitadoCP38() {
		//caso de 2 valoraciones
		plato=new Plato("postre","helado de fresa");
		platos.put(plato,10);
		Plato plato2=new Plato("postre","Flan de café");
		platos.put(plato2,7);
		
		//debe devolver el primer plato del array
		assertEquals(plato.getNombre(),est.platoMasSolicitado(platos).getNombre(),"El plato devuelto debe ser el de puntuación más alta.");
		assertEquals(plato.getTipo(),est.platoMasSolicitado(platos).getTipo(),"El plato devuelto debe ser del mismo tipo.");
	}
	}

	@Nested
	@DisplayName("Prueba 14: Prueba del metodo generarEstadisticas")
	class P2 {
		@DisplayName("P10_CP30")
		@Test
		void testEstadisticasCP30() {

			result = est.generarEstadisticas(valoraciones);
			assertEquals("Macarrones con queso", result.getPrimeroMasSolicitado().getNombre(),
					"El primero m�s solicitado debe ser macarrones.");
			assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),
					"El segundo m�s solicitado debe ser pollo.");
			assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),
					"El postre m�s solicitado debe ser flan de huevo.");

		}

		@DisplayName("P10_CP31")
		@Test
		void testEstadisticasCP31() {
			String codigoBandeja = sensores.generarCodigoBandeja();
			String codigoVale = sensores.generarCodigoVale();

			Bandeja bandeja2 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"),
					new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
			valoraciones.add(bandeja2);

			result = est.generarEstadisticas(valoraciones);
			assertEquals("Macarrones con queso", result.getPrimeroMasSolicitado().getNombre(),
					"El primero m�s solicitado debe ser macarrones.");
			assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),
					"El segundo m�s solicitado debe ser pollo.");
			assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),
					"El postre m�s solicitado debe ser flan de huevo.");

		}

		@DisplayName("P10_CP32")
		@Test
		void testEstadisticasCP32() {
			String codigoBandeja = sensores.generarCodigoBandeja();
			String codigoVale = sensores.generarCodigoVale();

			Bandeja bandeja2 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"),
					new Plato("segundo", "Jud�as"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
			valoraciones.add(bandeja2);

			result = est.generarEstadisticas(valoraciones);
			assertEquals("Macarrones con queso", result.getPrimeroMasSolicitado().getNombre(),
					"El primero m�s solicitado debe ser macarrones.");
			assertEquals("Jud�as", result.getSegundoMasSolicitado().getNombre(),
					"El segundo m�s solicitado debe ser pollo Y jud�as.");
			assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),
					"El segundo m�s solicitado debe ser pollo Y jud�as.");
			assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),
					"El postre m�s solicitado debe ser flan de huevo.");

		}

		@DisplayName("P10_CP33")
		@Test
		void testEstadisticasCP33() {
			String codigoBandeja = sensores.generarCodigoBandeja();
			String codigoVale = sensores.generarCodigoVale();

			Bandeja bandeja2 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con at�n"),
					new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
			valoraciones.add(bandeja2);

			result = est.generarEstadisticas(valoraciones);
			assertEquals("Macarrones con queso", result.getPrimeroMasSolicitado().getNombre(),
					"El primero m�s solicitado debe ser macarrones con at�n y con queso.");
			assertEquals("Macarrones con at�n", result.getPrimeroMasSolicitado().getNombre(),
					"El primero m�s solicitado debe ser macarrones con at�n y con queso.");
			assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),
					"El segundo m�s solicitado debe ser pollo.");
			assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),
					"El postre m�s solicitado debe ser flan de huevo.");

		}

		@DisplayName("P10_CP34")
		@Test
		void testEstadisticasCP34() {
			String codigoBandeja = sensores.generarCodigoBandeja();
			String codigoVale = sensores.generarCodigoVale();

			Bandeja bandeja2 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"),
					new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de caf�"), new Bebida("agua"));
			valoraciones.add(bandeja2);

			result = est.generarEstadisticas(valoraciones);
			assertEquals("Macarrones con queso", result.getPrimeroMasSolicitado().getNombre(),
					"El primero m�s solicitado debe ser macarrones con at�n y con queso.");
			assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),
					"El segundo m�s solicitado debe ser pollo.");
			assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),
					"El postre m�s solicitado debe ser flan de huevo y de caf�.");
			assertEquals("flan de caf�", result.getPostreMasSolicitado().getNombre(),
					"El postre m�s solicitado debe ser flan de huevo y de caf�.");

		}
		
	}
		@Nested
		@DisplayName("Prueba 12: Prueba del metodo compararPlatoMejorValorado ")
		class P3{
		// Casos de prueba
		@DisplayName("P12_CP39")
		@Test
		void testMejorCP39() {
			Plato aComparar = null;
			Plato maxActual = plato11;

			resultado = est.compararPlatoMejorValorado(aComparar, maxActual);
			assertEquals(null, resultado, "El resultado es nulo porque el plato a comparar es nulo.");

		}

		@DisplayName("P12_CP40")
		@Test
		void testMejorCP40() {
			Plato aComparar = plato11;
			Plato maxActual = plato21;

			resultado = est.compararPlatoMejorValorado(aComparar, maxActual);
			assertEquals(plato11, resultado,
					"Se ha actualizado un nuevo plato cuya nota media es mayor que el registrado con anterioridad.");

		}

		@DisplayName("P12_CP41")
		@Test
		void testMejorCP41() {
			Plato aComparar = plato21;
			Plato maxActual = plato11;

			resultado = est.compararPlatoMejorValorado(aComparar, maxActual);
			assertEquals(plato11, resultado,
					"Se devuelve el plato con nota media mayor, el ya registrado con anterioridad.");

		}
		}
	

	
}
