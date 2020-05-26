package seleccionMenus;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.awt.Menu;
import java.util.ArrayList;

import datos.Bebida;
import datos.Plato;
import gestionDatos.GestionDatosImpl;
import gestionMenus.GestionMenusImpl;
import codigoPrincipal.ISeleccionMenus;
import seleccionMenus.SeleccionMenusImpl;
import sensores.SensoresImpl;

class testSeleccionMenusImpl {

	Plato primero;
	Plato segundo;
	Plato postre;
	Bebida bebida;
	GestionDatosImpl gD;
	GestionMenusImpl mG;
	@InjectMocks
	SeleccionMenusImpl sM;

	@BeforeEach
	void setUp() throws Exception{
		sM=Mockito.mock(SeleccionMenusImpl.class);
		gD=new GestionDatosImpl();
		mG=new GestionMenusImpl(gD);
		
		// Inyectamos en las clases anotadas sus clases simuladas
		MockitoAnnotations.initMocks(this);
		Plato primero=new Plato(" ", " ");
		Plato segundo=new Plato(" ", " ");
		Plato postre=new Plato(" ", " ");
		Bebida bebida=new Bebida(" ");
		
	}

	@DisplayName("CP08: Test seleccionarMenu: todos los platos son del menu del dia.")
	@Test
	void testSeleccionarMenuCorrecto() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "Sopa fría");
		Plato segundo=new Plato("segundo", "Pollo asado");
		Plato postre=new Plato("postre", "helado de fresa");
		Bebida bebida=new Bebida("agua");
		
		assertEquals("primero",primero.getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",primero.getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",primero.getTipo(), "El plato introducido  como postre no es un postre.");
	
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),primero.getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),segundo.getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),postre.getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bebida.getNombre(),"La bebida introducida no esta en la lista.");
			}
		}
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		
	}
	
	@DisplayName("CP09: Test seleccionarMenu: el primero introducido no es del tipo primero.")
	@Test
	void testSeleccionarMenuPrimeroIncorrecto() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "Patatas fritas");
		Plato segundo=new Plato("segundo", "Pollo asado");
		Plato postre=new Plato("postre", "helado de fresa");
		Bebida bebida=new Bebida("agua");
		
		assertEquals("primero",primero.getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",primero.getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",primero.getTipo(), "El plato introducido  como postre no es un postre.");
	
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),primero.getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),segundo.getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),postre.getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bebida.getNombre(),"La bebida introducida no esta en la lista.");
			}
		}
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		
	}
	
	@DisplayName("CP10: Test seleccionarMenu: el segundo introducido no es del tipo segundo.")
	@Test
	void testSeleccionarMenuSegundoIncorrecto() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "Sopa fría");
		Plato segundo=new Plato("segundo", "Atún con queso");
		Plato postre=new Plato("postre", "helado de fresa");
		Bebida bebida=new Bebida("agua");
		
		assertEquals("primero",primero.getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",primero.getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",primero.getTipo(), "El plato introducido  como postre no es un postre.");
	
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),primero.getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),segundo.getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),postre.getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bebida.getNombre(),"La bebida introducida no esta en la lista.");
			}
		}
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		
	}
	
	@DisplayName("CP11: Test seleccionarMenu: el postre introducido no es del tipo postre.")
	@Test
	void testSeleccionarMenuPostreIncorrecto() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "Sopa fría");
		Plato segundo=new Plato("segundo", "Pollo asado");
		Plato postre=new Plato("postre", "Pollo asado");
		Bebida bebida=new Bebida("agua");
		
		assertEquals("primero",primero.getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",primero.getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",primero.getTipo(), "El plato introducido  como postre no es un postre.");
	
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),primero.getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),segundo.getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),postre.getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bebida.getNombre(),"La bebida introducida no esta en la lista.");
			}
		}
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		
	}
	
	@DisplayName("CP12: Test seleccionarMenu: la bebida no es válida.")
	@Test
	void testSeleccionarMenuBebidaIncorrecta() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "Sopa fría");
		Plato segundo=new Plato("segundo", "Pollo asado");
		Plato postre=new Plato("postre", "helado de fresa");
		
		assertEquals("primero",primero.getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",primero.getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",primero.getTipo(), "El plato introducido  como postre no es un postre.");
	
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),primero.getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),segundo.getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),postre.getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bebida.getNombre(),"La bebida introducida no esta en la lista.");
			}
		}
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		
	}
	
	@DisplayName("CP13: Test seleccionarMenu: platos del tipo correcto pero nombre inexistente.")
	@Test
	void testSeleccionarMenuDatosNoExistentes() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "kefjwf");
		Plato segundo=new Plato("segundo", "ewrgferg");
		Plato postre=new Plato("postre", "fewgrreg");
		
		assertEquals("primero",primero.getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",primero.getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",primero.getTipo(), "El plato introducido  como postre no es un postre.");
	
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),primero.getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),segundo.getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),postre.getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bebida.getNombre(),"La bebida introducida no esta en la lista.");
			}
		}
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		
	}

}
