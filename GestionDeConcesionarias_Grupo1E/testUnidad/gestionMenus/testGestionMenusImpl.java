package gestionMenus;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import codigoPrincipal.IGestionDatos;
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

	@Test
	void testConstruirMenuCorrecto() {
		
		
		
		gM.construirMenu(dia, primeros, segundos, postres, bebidas);
		
		
	}

}
