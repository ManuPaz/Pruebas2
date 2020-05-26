package gestionMenus;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import datos.Bebida;
import datos.Plato;
import gestionDatos.GestionDatosImpl;


class testGestionMenusImpl {
	String dia;
	ArrayList<Plato> primeros=new ArrayList<>();
	ArrayList<Plato> segundos=new ArrayList<>();
	ArrayList<Plato> postres=new ArrayList<>();
	ArrayList<Bebida> bebidas=new ArrayList<>();
	GestionDatosImpl datos;
	@InjectMocks
	GestionMenusImpl gM;

	@BeforeEach
	void setUp() throws Exception {
		
		datos=Mockito.mock(GestionDatosImpl.class);
		
		// Inyectamos en las clases anotadas sus clases simuladas
		MockitoAnnotations.initMocks(this);
		
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

	@DisplayName("CP01: Test construir menu, con 3 platos correctos de cada, un dia correcto y bebidas existentes")
	@Test
	void testConstruirMenuCorrecto() {
		
		gM.construirMenu(dia, primeros, segundos, postres, bebidas);
		Mockito.verify(datos, Mockito.times(1)).almacenarMenu(Mockito.any()); //Se debe invocar a almacenarMenu porque es un menu correcto
	}
	
	@DisplayName("CP02: Test construir menu, dia no laborable")
	@Test
	void testConstruirMenuDiaNoLaborable() {
		dia = "Domingo";
		gM.construirMenu(dia, primeros, segundos, postres, bebidas);
		Mockito.verify(datos, Mockito.times(0)).almacenarMenu(Mockito.any()); //No se debe invocar porque es incorrecto
	}
	
	@DisplayName("CP03: Test construir menu, con platos que no son del tipo que deberían ser")
	@Test
	void testConstruirMenuPlatosErroneos() {
		primeros.clear();
		Plato plato=new Plato("primero","Macarrones con queso");
		primeros.add(plato);
		plato=new Plato("primero","Atún con queso");
		primeros.add(plato);
		plato=new Plato("primero","Pollo asado");
		primeros.add(plato);
		gM.construirMenu(dia, primeros, segundos, postres, bebidas);
		Mockito.verify(datos, Mockito.times(0)).almacenarMenu(Mockito.any()); //No se debe invocar porque es incorrecto
	}
	
	@DisplayName("CP04: Test construir menu, con una lista de bebidas vacía y una lista con dos platos segundos")
	@Test
	void testConstruirMenuSinBebidas() {
		bebidas.clear();
		segundos.clear();
		Plato plato=new Plato("segundo","Pollo asado");
		segundos.add(plato);
		plato=new Plato("segundo","Judías");
		segundos.add(plato);
		gM.construirMenu(dia, primeros, segundos, postres, bebidas);
		Mockito.verify(datos, Mockito.times(0)).almacenarMenu(Mockito.any()); //No se debe invocar porque es incorrecto
	}
	
	@DisplayName("CP05: Test construir menu, con un día que no existe y platos repetidos")
	@Test
	void testConstruirMenuDiaMalRepeticiones() {
		bebidas.clear();
		Bebida bebida=new Bebida("agua");
		bebidas.add(bebida);
		bebida=new Bebida("agua");
		bebidas.add(bebida);
		
		dia="Maandag";
		
		gM.construirMenu(dia, primeros, segundos, postres, bebidas);
		Mockito.verify(datos, Mockito.times(0)).almacenarMenu(Mockito.any()); //No se debe invocar porque es incorrecto
	}

}
