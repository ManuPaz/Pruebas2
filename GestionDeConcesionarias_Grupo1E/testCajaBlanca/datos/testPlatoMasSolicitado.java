package datos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import estadisticas.EstadisticasImpl;
import sensores.SensoresImpl;

public class testPlatoMasSolicitado {
	
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
		platos.put(plato,0);
		
		
		//debe devolver un plato distinto
		assertNotEquals(plato.getNombre(),est.platoMasSolicitado(platos).getNombre(),"El plato devuelto debe distinto.");
		assertNotEquals(plato.getTipo(),est.platoMasSolicitado(platos).getTipo(),"El plato devuelto debe ser distinto.");

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
		assertEquals(plato.getTipo(),est.platoMasSolicitado(platos).getTipo(),"El plato devuelto debe ser el mismo.");
	}
	
	@DisplayName("P11_CP38")
	@Tag("PlatoMasSolicitado")
	@Tag("CajaBlanca")
	@Test
	
	void testSolicitadoCP38() {
		//caso de 2 valoraciones
		plato=new Plato("postre","helado de fresa");
		platos.put(plato,10);
		plato=new Plato("postre","Flan de café");
		platos.put(plato,7);
		
		//debe devolver el primer plato del array
		assertEquals(plato.getNombre(),est.platoMasSolicitado(platos).getNombre(),"El plato devuelto debe ser el primero del array.");
		assertEquals(plato.getTipo(),est.platoMasSolicitado(platos).getTipo(),"El plato devuelto debe ser el primero del array.");
	}

}
