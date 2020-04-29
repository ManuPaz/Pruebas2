package seleccionMenus;
import codigoPrincipal.*;
import datos.*;
public class SeleccionMenusImpl implements ISeleccionMenus {
	ISensores sensores;
	
	
	public SeleccionMenusImpl(ISensores sensores) {
		super();
		this.sensores = sensores;
	}


	public Bandeja seleccionarMenu(Plato primero, Plato segundo, Plato postre, Bebida bebida) {
		String idVale=sensores.generarCodigoVale();
		String idBandeja=sensores.generarCodigoBandeja();
		Bandeja bandeja= new Bandeja(idBandeja, idVale, primero, segundo, postre, bebida);
		return bandeja;
	}
}
