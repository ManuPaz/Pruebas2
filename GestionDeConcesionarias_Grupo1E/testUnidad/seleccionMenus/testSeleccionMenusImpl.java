package seleccionMenus;

import static org.junit.jupiter.api.Assertions.*;
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
import gestionMenus.GestionMenusImpl;

class testSeleccionMenusImpl {

	Plato primero;
	Plato segundo;
	Plato postre;
	Bebida bebida;
	Bandeja bandeja;
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
		primero=new Plato(" ", " ");
		segundo=new Plato(" ", " ");
		postre=new Plato(" ", " ");
		bebida=new Bebida(" ");
		bandeja=new Bandeja(primero,segundo,postre,bebida);
		
	}

	@DisplayName("CP08: Test seleccionarMenu: todos los platos son del menu del dia.")
	@Test
	void testSeleccionarMenuCorrecto() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();

		Plato primero=new Plato("primero", "sopa fria");
		Plato segundo=new Plato("segundo", "patatas fritas");
		Plato postre=new Plato("postre", "natillas fresa");
		Bebida bebida=new Bebida("agua");
	
		bandeja=sM.seleccionarMenu(primero, segundo, postre, bebida);
				
		assertEquals("primero",bandeja.getPrimeroSeleccionado().getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",bandeja.getSegundoSeleccionado().getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",bandeja.getPostreSeleccionado().getTipo(), "El plato introducido  como postre no es un postre.");
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),bandeja.getPrimeroSeleccionado().getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),bandeja.getSegundoSeleccionado().getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),bandeja.getPostreSeleccionado().getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bandeja.getBebidaSeleccionada().getNombre(),"La bebida introducida no esta en la lista.");
			}
		}		
		
	}
	
	@DisplayName("CP09: Test seleccionarMenu: el primero introducido no es un primero.")
	@Test
	void testSeleccionarMenuPrimeroIncorrecto() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("segundo", "patatas fritas");
		Plato segundo=new Plato("segundo", "patatas fritas");
		Plato postre=new Plato("postre", "natillas chocolate");
		Bebida bebida=new Bebida("agua");
		
		bandeja=sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		assertEquals("primero",bandeja.getPrimeroSeleccionado().getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",bandeja.getSegundoSeleccionado().getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",bandeja.getPostreSeleccionado().getTipo(), "El plato introducido  como postre no es un postre.");
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),bandeja.getPrimeroSeleccionado().getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),bandeja.getSegundoSeleccionado().getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),bandeja.getPostreSeleccionado().getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bandeja.getBebidaSeleccionada().getNombre(),"La bebida introducida no esta en la lista.");
			}
		}	
		
		
	}
	
	@DisplayName("CP10: Test seleccionarMenu: el segundo introducido no es un segundo.")
	@Test
	void testSeleccionarMenuSegundoIncorrecto() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "sopa fria");
		Plato segundo=new Plato("primero", "Atún con queso");
		Plato postre=new Plato("postre", "croquetas");
		Bebida bebida=new Bebida("agua");
		
		bandeja=sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		assertEquals("primero",bandeja.getPrimeroSeleccionado().getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",bandeja.getSegundoSeleccionado().getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",bandeja.getPostreSeleccionado().getTipo(), "El plato introducido  como postre no es un postre.");
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),bandeja.getPrimeroSeleccionado().getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),bandeja.getSegundoSeleccionado().getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),bandeja.getPostreSeleccionado().getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bandeja.getBebidaSeleccionada().getNombre(),"La bebida introducida no esta en la lista.");
			}
		}	
		
		
	}
	
	@DisplayName("CP11: Test seleccionarMenu: el postre introducido no es un postre.")
	@Test
	void testSeleccionarMenuPostreIncorrecto() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "sopa fria");
		Plato segundo=new Plato("segundo", "Pollo asado");
		Plato postre=new Plato("segundo", "Pollo asado");
		Bebida bebida=new Bebida("agua");
		
		bandeja=sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		assertEquals("primero",bandeja.getPrimeroSeleccionado().getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",bandeja.getSegundoSeleccionado().getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",bandeja.getPostreSeleccionado().getTipo(), "El plato introducido  como postre no es un postre.");
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),bandeja.getPrimeroSeleccionado().getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),bandeja.getSegundoSeleccionado().getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),bandeja.getPostreSeleccionado().getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bandeja.getBebidaSeleccionada().getNombre(),"La bebida introducida no esta en la lista.");
			}
		}	
		
		
	}
	
	@DisplayName("CP12: Test seleccionarMenu: la bebida no es válida.")
	@Test
	void testSeleccionarMenuBebidaIncorrecta() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "Sopa fría");
		Plato segundo=new Plato("segundo", "Pollo asado");
		Plato postre=new Plato("postre", "helado de fresa");
		Bebida bebida=new Bebida("alcohol");
		
		bandeja=sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		assertEquals("primero",bandeja.getPrimeroSeleccionado().getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",bandeja.getSegundoSeleccionado().getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",bandeja.getPostreSeleccionado().getTipo(), "El plato introducido  como postre no es un postre.");
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),bandeja.getPrimeroSeleccionado().getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),bandeja.getSegundoSeleccionado().getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),bandeja.getPostreSeleccionado().getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bandeja.getBebidaSeleccionada().getNombre(),"La bebida introducida no esta en la lista.");
			}
		}	
		
		
	}
	
	@DisplayName("CP13: Test seleccionarMenu: platos del tipo correcto pero nombre inexistente.")
	@Test
	void testSeleccionarMenuDatosNoExistentes() {
		
		datos.Menu menu=mG.mostrarMenuDelDia();
		
		Plato primero=new Plato("primero", "kefjwf");
		Plato segundo=new Plato("segundo", "ewrgferg");
		Plato postre=new Plato("postre", "fewgrreg");
		
		bandeja=sM.seleccionarMenu(primero, segundo, postre, bebida);
		
		assertEquals("primero",bandeja.getPrimeroSeleccionado().getTipo(), "El plato introducido como primero no es un primero.");
		assertEquals("segundo",bandeja.getSegundoSeleccionado().getTipo(), "El plato introducido como segundo no es un segundo.");
		assertEquals("postre",bandeja.getPostreSeleccionado().getTipo(), "El plato introducido  como postre no es un postre.");
		
		for(Plato prim:menu.getPrimerosDisponibles()) {
			
			if(prim.getNombre().equals(primero.getNombre())) {
				assertEquals(prim.getNombre(),bandeja.getPrimeroSeleccionado().getNombre(),"El primero introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato seg:menu.getSegundosDisponibles()) {
			
			if(seg.getNombre().equals(segundo.getNombre())) {
				assertEquals(seg.getNombre(),bandeja.getSegundoSeleccionado().getNombre(),"El segundo introducido no esta en el menu del dia.");
			}
		}
		
		for(Plato pos:menu.getPostresDisponibles()) {
			
			if(pos.getNombre().equals(postre.getNombre())) {
				assertEquals(pos.getNombre(),bandeja.getPostreSeleccionado().getNombre(),"El postre introducido no esta en el menu del dia.");
			}
		}
		
		for(Bebida beb:menu.getBebidas()) {
			
			if(beb.getNombre().equals(bebida.getNombre())) {
				assertEquals(beb.getNombre(),bandeja.getBebidaSeleccionada().getNombre(),"La bebida introducida no esta en la lista.");
			}
		}	
		
		
	}

}
