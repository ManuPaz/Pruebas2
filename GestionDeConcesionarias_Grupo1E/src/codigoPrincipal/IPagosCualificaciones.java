package codigoPrincipal;
import datos.*;
import java.util.*;

public interface IPagosCualificaciones {
	
	//esta funcion q hace
	public Bandeja pagarMenu(Bandeja bandeja);
	public void guardarValoracion(Bandeja bandeja, HashMap<Plato,Integer> valoraciones);	
	public String devolverBandeja(Bandeja bandeja);
	

}
