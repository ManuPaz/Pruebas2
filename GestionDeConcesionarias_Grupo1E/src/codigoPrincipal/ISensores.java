package codigoPrincipal;
import datos.*;
import java.util.*;

public interface ISensores {
 
	public String generarCodigoVale();
	public boolean canjearVale(String valeACanjear) ;
	public String generarCodigoBandeja();
	public String generarCodigoBandejaDevuelta(Bandeja bandeja);
	public int numeroDeBandejasNoDevueltas();
}
