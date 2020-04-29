package codigoPrincipal;
import datos.*;
import java.util.*;
public interface IGestionDatos {
	
	public ArrayList<Menu> leerMenus();
	public void almacenarMenu(Menu menu);
	public void almacenarValoracion(Bandeja bandeja);
	public ArrayList<Bandeja> recopilarDatosEstadisticas();
}
