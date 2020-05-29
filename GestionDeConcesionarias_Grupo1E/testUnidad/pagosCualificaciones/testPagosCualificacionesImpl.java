package pagosCualificaciones;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import datos.Bandeja;
import datos.BaseEstadisticas;
import datos.Bebida;
import datos.Estadisticas;
import datos.Plato;
import gestionDatos.GestionDatosImpl;
import sensores.SensoresImpl;
class testPagosCualificacionesImpl {
	Bandeja bandeja;
	Plato plato;
	SensoresImpl sensores;
	GestionDatosImpl conexionManipuladorDeDatos;
	@InjectMocks
	PagosCualificacionesImpl gPC;

	
	@BeforeEach
	void setUp() throws Exception {
		conexionManipuladorDeDatos=Mockito.mock(GestionDatosImpl.class);
		sensores=new SensoresImpl();
		gPC=new PagosCualificacionesImpl(sensores);
		MockitoAnnotations.initMocks(this);
		
		String codigoBandeja ="10";
		String codigoVale ="1";	
		plato = new Plato("primero", "Macarrones con queso");
		bandeja = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"), new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
@Nested
@DisplayName("Prueba 5: Prueba del metodo guardarValoracion")
	class P{
	@DisplayName("CP15: Test guardar valoracion")
	@Test
	void testGuardarValoracionesLimiteInferior() {
		HashMap<Plato,Integer> valoraciones = new HashMap<>();
		valoraciones.put(plato, 0);
		gPC.guardarValoracion(bandeja, valoraciones);
		Mockito.verify(conexionManipuladorDeDatos, Mockito.times(1)).almacenarValoracion(Mockito.any()); //Se debe invocar a almacenarMenu porque es un menu correcto
	}
	
	@DisplayName("CP16: Test guardar valoracion")
	@Test
	void testGuardarValoracionesLimiteSuperior() {
		HashMap<Plato,Integer> valoraciones = new HashMap<>();
		valoraciones.put(plato, 5);


		
		gPC.guardarValoracion(bandeja, valoraciones);
		Mockito.verify(conexionManipuladorDeDatos, Mockito.times(1)).almacenarValoracion(Mockito.any()); //Se debe invocar a almacenarMenu porque es un menu correcto
	}
	
	
	@DisplayName("CP17: Test no guardar valoracion")
	@Test
	void testGuardarValoracionesInferior() {
		HashMap<Plato,Integer> valoraciones = new HashMap<>();
		valoraciones.put(plato, -1);

		gPC.guardarValoracion(bandeja, valoraciones);
		Mockito.verify(conexionManipuladorDeDatos, Mockito.times(0)).almacenarValoracion(Mockito.any()); //Se debe invocar a almacenarMenu porque es un menu correcto
	}
	
	@DisplayName("CP18: Test no guardar valoracion")
	@Test
	void testGuardarValoracionesSuperior() {
		HashMap<Plato,Integer> valoraciones = new HashMap<>();
		valoraciones.put(plato, 6);
	
		gPC.guardarValoracion(bandeja, valoraciones);
		Mockito.verify(conexionManipuladorDeDatos, Mockito.times(0)).almacenarValoracion(Mockito.any()); 
	}
}
	@Nested
	@DisplayName("Prueba 4: Prueba del método pagarMenu")
	class P1{
	@DisplayName("CP14")
	@Test
	void testPagarMenu() {
		PagosCualificacionesImpl gP1;
		SensoresImpl sensores1=new SensoresImpl();
		gP1=new PagosCualificacionesImpl(sensores1);
		
		Bandeja bandejaSalida = gP1.pagarMenu(bandeja);
		assertEquals(bandeja.getId(),bandejaSalida.getId(),"El id de las bandejas debería ser el mismo.");
	}
	}
}
