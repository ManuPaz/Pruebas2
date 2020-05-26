package datos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import codigoPrincipal.ISensores;
import estadisticas.EstadisticasImpl;
import sensores.SensoresImpl;

class testEstadisticasImpl {

	ArrayList<Bandeja> valoraciones = new ArrayList<>();
	Estadisticas result;
	SensoresImpl sensores = new SensoresImpl();
	EstadisticasImpl est = new EstadisticasImpl(sensores);
	
	@BeforeEach
	void setUp() throws Exception {
		
		
		String codigoBandeja = sensores.generarCodigoBandeja();
		String codigoVale = sensores.generarCodigoVale();
		
		Bandeja bandeja1 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"), new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
		valoraciones.add(bandeja1);
	}

	//Prueba de caja blanca
	@DisplayName("P10_CP30")
	@Test
	void testEstadisticasCP30() {
		
		result = est.generarEstadisticas(valoraciones);
		assertEquals("Macarrones con queso",result.getPrimeroMasSolicitado().getNombre(),"El primero más solicitado debe ser macarrones.");
		assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),"El segundo más solicitado debe ser pollo.");
		assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),"El postre más solicitado debe ser flan de huevo.");
		
	}
	
	@DisplayName("P10_CP31")
	@Test
	void testEstadisticasCP31() {
		String codigoBandeja = sensores.generarCodigoBandeja();
		String codigoVale = sensores.generarCodigoVale();
		
		Bandeja bandeja2 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"), new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
		valoraciones.add(bandeja2);
		
		result = est.generarEstadisticas(valoraciones);
		assertEquals("Macarrones con queso",result.getPrimeroMasSolicitado().getNombre(),"El primero más solicitado debe ser macarrones.");
		assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),"El segundo más solicitado debe ser pollo.");
		assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),"El postre más solicitado debe ser flan de huevo.");
		
	}
	
	@DisplayName("P10_CP32")
	@Test
	void testEstadisticasCP32() {
		String codigoBandeja = sensores.generarCodigoBandeja();
		String codigoVale = sensores.generarCodigoVale();
		
		Bandeja bandeja2 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"), new Plato("segundo", "Judías"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
		valoraciones.add(bandeja2);
		
		result = est.generarEstadisticas(valoraciones);
		assertEquals("Macarrones con queso",result.getPrimeroMasSolicitado().getNombre(),"El primero más solicitado debe ser macarrones.");
		assertEquals("Judías", result.getSegundoMasSolicitado().getNombre(),"El segundo más solicitado debe ser pollo Y judías.");
		assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),"El segundo más solicitado debe ser pollo Y judías.");
		assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),"El postre más solicitado debe ser flan de huevo.");
		
	}
	
	@DisplayName("P10_CP33")
	@Test
	void testEstadisticasCP33() {
		String codigoBandeja = sensores.generarCodigoBandeja();
		String codigoVale = sensores.generarCodigoVale();
		
		Bandeja bandeja2 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con atún"), new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de huevo"), new Bebida("agua"));
		valoraciones.add(bandeja2);
		
		result = est.generarEstadisticas(valoraciones);
		assertEquals("Macarrones con queso",result.getPrimeroMasSolicitado().getNombre(),"El primero más solicitado debe ser macarrones con atún y con queso.");
		assertEquals("Macarrones con atún",result.getPrimeroMasSolicitado().getNombre(),"El primero más solicitado debe ser macarrones con atún y con queso.");
		assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),"El segundo más solicitado debe ser pollo.");
		assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),"El postre más solicitado debe ser flan de huevo.");
		
	}

	@DisplayName("P10_CP34")
	@Test
	void testEstadisticasCP34() {
		String codigoBandeja = sensores.generarCodigoBandeja();
		String codigoVale = sensores.generarCodigoVale();
		
		Bandeja bandeja2 = new Bandeja(codigoBandeja, codigoVale, new Plato("primero", "Macarrones con queso"), new Plato("segundo", "Pollo asado"), new Plato("postre", "flan de café"), new Bebida("agua"));
		valoraciones.add(bandeja2);
		
		result = est.generarEstadisticas(valoraciones);
		assertEquals("Macarrones con queso",result.getPrimeroMasSolicitado().getNombre(),"El primero más solicitado debe ser macarrones con atún y con queso.");
		assertEquals("Pollo asado", result.getSegundoMasSolicitado().getNombre(),"El segundo más solicitado debe ser pollo.");
		assertEquals("flan de huevo", result.getPostreMasSolicitado().getNombre(),"El postre más solicitado debe ser flan de huevo y de café.");
		assertEquals("flan de café", result.getPostreMasSolicitado().getNombre(),"El postre más solicitado debe ser flan de huevo y de café.");
		
	}
}
