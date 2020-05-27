package seleccionMenus;

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

class testSeleccionMenusImpl {

	Plato primero;
	Plato segundo;
	Plato postre;
	Bebida bebida;
	Bandeja bandeja;
	GestionDatosImpl gD;
	SensoresImpl sensores;
	@InjectMocks
	SeleccionMenusImpl sM;

	@BeforeEach
	void setUp() throws Exception{
		
		gD=Mockito.mock(GestionDatosImpl.class);
		sensores=new SensoresImpl();
		sM=new SeleccionMenusImpl(sensores);
		
		// Inyectamos en las clases anotadas sus clases simuladas
		MockitoAnnotations.initMocks(this);
		primero=new Plato(" ", " ");
		segundo=new Plato(" ", " ");
		postre=new Plato(" ", " ");
		bebida=new Bebida(" ");
		bandeja=new Bandeja(" "," ",primero,segundo,postre,bebida);
		
	}

	@DisplayName("CP08: Test seleccionarMenu: todos los platos son del menu del dia.")
	@Test
	void testSeleccionarMenuCorrecto() {
		
		Plato primero=new Plato("primero", "sopa fria");
		Plato segundo=new Plato("segundo", "patatas fritas");
		Plato postre=new Plato("postre", "natillas fresa");
		Bebida bebida=new Bebida("agua");
	
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		//se almacena el menu pq la seleccion es valida
		Mockito.verify(gD, Mockito.times(1)).almacenarMenu(Mockito.any());	
		
	}
	
	@DisplayName("CP09: Test seleccionarMenu: el primero introducido no es un primero.")
	@Test
	void testSeleccionarMenuPrimeroIncorrecto() {
				
		Plato primero=new Plato("segundo", "patatas fritas");
		Plato segundo=new Plato("segundo", "patatas fritas");
		Plato postre=new Plato("postre", "natillas chocolate");
		Bebida bebida=new Bebida("agua");
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		//no se deberia invocar pq el tipo es incorrecto
		Mockito.verify(gD, Mockito.times(0)).almacenarMenu(Mockito.any());	
		
	}
	
	@DisplayName("CP10: Test seleccionarMenu: el segundo introducido no es un segundo.")
	@Test
	void testSeleccionarMenuSegundoIncorrecto() {
				
		Plato primero=new Plato("primero", "sopa fria");
		Plato segundo=new Plato("primero", "Atún con queso");
		Plato postre=new Plato("postre", "croquetas");
		Bebida bebida=new Bebida("agua");
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		//no se deberia invocar pq el tipo es incorrecto
		Mockito.verify(gD, Mockito.times(0)).almacenarMenu(Mockito.any());			
		
	}
	
	@DisplayName("CP11: Test seleccionarMenu: el postre introducido no es un postre.")
	@Test
	void testSeleccionarMenuPostreIncorrecto() {
		
		
		Plato primero=new Plato("primero", "sopa fria");
		Plato segundo=new Plato("segundo", "Pollo asado");
		Plato postre=new Plato("segundo", "Pollo asado");
		Bebida bebida=new Bebida("agua");
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		//no se deberia invocar pq el tipo es incorrecto
		Mockito.verify(gD, Mockito.times(0)).almacenarMenu(Mockito.any());		}
	
	@DisplayName("CP12: Test seleccionarMenu: la bebida no es válida.")
	@Test
	void testSeleccionarMenuBebidaIncorrecta() {
				
		Plato primero=new Plato("primero", "Sopa fría");
		Plato segundo=new Plato("segundo", "Pollo asado");
		Plato postre=new Plato("postre", "helado de fresa");
		Bebida bebida=new Bebida("alcohol");
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		//no se deberia invocar pq la bebida es incorrecta
		Mockito.verify(gD, Mockito.times(0)).almacenarMenu(Mockito.any());			
	}
	
	@DisplayName("CP13: Test seleccionarMenu: platos del tipo correcto pero nombre inexistente.")
	@Test
	void testSeleccionarMenuDatosNoExistentes() {
				
		Plato primero=new Plato("primero", "kefjwf");
		Plato segundo=new Plato("segundo", "ewrgferg");
		Plato postre=new Plato("postre", "fewgrreg");
		
		sM.seleccionarMenu(primero, segundo, postre, bebida);
		//no se deberia invocar pq el nombre del plato es incorrecto
		Mockito.verify(gD, Mockito.times(0)).almacenarMenu(Mockito.any());			
	}

}
