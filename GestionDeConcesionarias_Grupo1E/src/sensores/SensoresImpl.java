package sensores;

import java.util.ArrayList;
import java.util.Calendar;

import codigoPrincipal.ISensores;
import datos.Bandeja;

public class SensoresImpl implements ISensores{
	private ArrayList<String> valesGenerados; //Para llevar el control de los vales que han sido generados por el sistema
	private int generadorDeVales; //dígito para generar el identificador de los vales, ya que tienen la forma VXXXXXXXX
	private ArrayList<String> bandejasEntregadas;
	private int generadorDeBandejas;
	
	public SensoresImpl(){
		this.valesGenerados = new ArrayList<String>();
		this.generadorDeVales = 0;
		this.bandejasEntregadas = new ArrayList<String>();
		this.generadorDeBandejas = 0;
	}
	
	@Override
	public String generarCodigoVale() {
		String valeGenerado = new String();
		
		if(this.generadorDeVales >= 999999999) {
			this.generadorDeVales = 0;
		}
		
		valeGenerado = "V"+Integer.toString(this.generadorDeVales);
		
		while(this.valesGenerados.contains(valeGenerado)) {
			this.generadorDeVales++;
			valeGenerado = "V"+Integer.toString(this.generadorDeVales);
		}
		
		this.generadorDeVales++;
		this.valesGenerados.add(valeGenerado);
		System.out.println("Tu vale es: " + valeGenerado);
		
		return valeGenerado;
	}

	@Override
	public boolean canjearVale(String valeACanjear) {
		if(this.valesGenerados.contains(valeACanjear)) {
			this.valesGenerados.remove(valeACanjear);
			return true;
		}
		
		System.out.println("Lo sentimos, no es un vale válido.");
		
		return false;
		
	}

	@Override
	public String generarCodigoBandeja() {
		String bandejaGenerada = new String();
		
		if(this.generadorDeBandejas >= 999999999) {
			this.generadorDeBandejas = 0;
		}
		
		bandejaGenerada = Integer.toString(this.generadorDeBandejas);
		
		while(this.bandejasEntregadas.contains(bandejaGenerada)) {
			this.generadorDeBandejas++;
			bandejaGenerada = Integer.toString(this.generadorDeBandejas);
		}
		
		this.generadorDeBandejas++;
		this.bandejasEntregadas.add(bandejaGenerada);
		
		return bandejaGenerada;
	}

	@Override
	public String generarCodigoBandejaDevuelta(Bandeja bandeja) {
		String idAux = null;
		Calendar fecha = Calendar.getInstance();
		bandeja.getEstadisticas().setHoraDevolucion(Integer.toString(fecha.HOUR_OF_DAY) + Integer.toString(fecha.MINUTE));
		
		if(this.bandejasEntregadas.contains(bandeja.getId())) {
			idAux = bandeja.getId();
			this.bandejasEntregadas.remove(bandeja.getEstadisticas());
		}
		
		return idAux;
	}
	
	@Override
	public int numeroDeBandejasNoDevueltas() {
		return this.bandejasEntregadas.size();
	}
}
