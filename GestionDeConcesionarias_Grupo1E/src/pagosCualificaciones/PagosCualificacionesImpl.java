package pagosCualificaciones;

import java.util.HashMap;
import java.util.Scanner;

import codigoPrincipal.IGestionDatos;
import codigoPrincipal.IPagosCualificaciones;
import codigoPrincipal.ISensores;
import datos.Bandeja;
import datos.Plato;
import gestionDatos.GestionDatosImpl;


public class PagosCualificacionesImpl implements IPagosCualificaciones{
	private ISensores conexionSensor; //Para la generacion y lectura de codigos.
	private IGestionDatos conexionManipuladorDeDatos; //Para la lectura-escritura de los datos
	private HashMap<String,Bandeja> historialDeBandejas;
	
	public PagosCualificacionesImpl(ISensores conexionSensores) {
		this.conexionSensor = conexionSensores;
		this.historialDeBandejas = new HashMap<>();
		this.conexionManipuladorDeDatos = new GestionDatosImpl();
	}
	
	@Override
	public Bandeja pagarMenu(Bandeja bandeja) {
		/*Como en el primer incremento no tratamos el pago de menús,
		 *  lo único que realizamos es la asignacion de una bandeja a nuestro usuario
		 */
		//Generamos un vale para simular el pago
		String codigoGenerado = this.conexionSensor.generarCodigoVale();
		
		String valeAux = codigoGenerado;
		
		if(this.conexionSensor.canjearVale(valeAux)) {
			Bandeja bandejaDelUsuario = new Bandeja(this.conexionSensor.generarCodigoBandeja(), this.conexionSensor.generarCodigoVale(), bandeja.getPrimeroSeleccionado(), bandeja.getSegundoSeleccionado(), bandeja.getPostreSeleccionado(), bandeja.getBebidaSeleccionada());
			
			//Introducimos la bandeja en el historial para poder tener cuenta de las que estamos entregando a los usuarios, y realizar las comprobaciones necesarias cuando estas son devueltas
			this.historialDeBandejas.put(bandejaDelUsuario.getId(), bandejaDelUsuario);
			
			return bandejaDelUsuario;
		}
		
		return null;
	}

	@Override
	public void guardarValoracion(Bandeja bandeja, HashMap<Plato,Integer> valoraciones){
		Integer nota = 0;
		String entradaPorTeclado= new String();
		Scanner entradaEscaner = new Scanner("System.in");
		
		//Una vez introducidas todas las valoraciones, las almacenamos en la bandeja para luego almacenarlas en los archivos
		bandeja.getEstadisticas().setValoracionesPlatos(valoraciones);
		
		//Le pasamos la bandeja a la funcion encargada de almacenar dicha bandeja
		this.conexionManipuladorDeDatos.almacenarValoracion(bandeja);
	}

	@Override
	public String devolverBandeja(Bandeja bandeja) {
		if(this.historialDeBandejas.containsKey(bandeja.getId())) {
			this.conexionManipuladorDeDatos.almacenarValoracion(bandeja);
			return this.conexionSensor.generarCodigoBandejaDevuelta(bandeja);
		}
		
		return null;
	}
}
