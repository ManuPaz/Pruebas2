package pagosCualificaciones;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import datos.Bandeja;
import datos.Bebida;
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
		MockitoAnnotations.initMocks(this);
		sensores=new SensoresImpl();
		String codigoBandeja = sensores.generarCodigoBandeja();
		String codigoVale = sensores.generarCodigoVale();	
		plato = new Plato("primero", "Macarrones con queso");
		bandeja = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"), new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	

	
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
		Mockito.when(conexionManipuladorDeDatos.almacenarValoracion(Mockito.any())).thenReturn(Integer.valueOf(5));
		gPC.guardarValoracion(bandeja, valoraciones);
		Mockito.verify(conexionManipuladorDeDatos, Mockito.times(1)).almacenarValoracion(Mockito.any()); //Se debe invocar a almacenarMenu porque es un menu correcto
	}
	
	
	@DisplayName("CP17: Test no guardar valoracion")
	@Test
	void testGuardarValoracionesInferior() {
		HashMap<Plato,Integer> valoraciones = new HashMap<>();
		valoraciones.put(plato, -1);
		
		gPC.guardarValoracion(bandeja, valoraciones);
		Mockito.verify(conexionManipuladorDeDatos, Mockito.times(1)).almacenarValoracion(Mockito.any()); //Se debe invocar a almacenarMenu porque es un menu correcto
	}
	
	@DisplayName("CP15: Test no guardar valoracion")
	@Test
	void testGuardarValoracionesSuperior() {
		HashMap<Plato,Integer> valoraciones = new HashMap<>();
		valoraciones.put(plato, 6);
		
		gPC.guardarValoracion(bandeja, valoraciones);
		Mockito.verify(conexionManipuladorDeDatos, Mockito.times(1)).almacenarValoracion(Mockito.any()); //Se debe invocar a almacenarMenu porque es un menu correcto
	}
}
