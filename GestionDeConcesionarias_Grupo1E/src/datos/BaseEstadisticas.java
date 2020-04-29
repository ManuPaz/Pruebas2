package datos;
import java.util.*;

public class BaseEstadisticas {
private HashMap<Plato,Integer> valoracionesPlatos;
private String horaDevolucion;
private String horaAsignacion;
private String dia;

public BaseEstadisticas(String horaAsignacion, String dia) {
	super();
	this.horaAsignacion = horaAsignacion;
	this.dia = dia;
}
public BaseEstadisticas(HashMap<Plato, Integer> valoracionesPlatos, String horaDevolucion, String horaAsignacion,
		String dia) {
	super();
	this.valoracionesPlatos = valoracionesPlatos;
	this.horaDevolucion = horaDevolucion;
	this.horaAsignacion = horaAsignacion;
	this.dia = dia;
}
public BaseEstadisticas(String horaDevolucion, String horaAsignacion, String dia) {
	super();
	this.horaDevolucion = horaDevolucion;
	this.horaAsignacion = horaAsignacion;
	this.dia = dia;
}
public HashMap<Plato, Integer> getValoracionesPlatos() {
	return valoracionesPlatos;
}
public void setValoracionesPlatos(HashMap<Plato, Integer> valoracionesPlatos) {
	this.valoracionesPlatos = valoracionesPlatos;
}
public String getHoraDevolucion() {
	return horaDevolucion;
}
public void setHoraDevolucion(String horaDevolucion) {
	this.horaDevolucion = horaDevolucion;
}
public String getHoraAsignacion() {
	return horaAsignacion;
}
public void setHoraAsignacion(String horaAsignacion) {
	this.horaAsignacion = horaAsignacion;
}
public String getDia() {
	return dia;
}
public void setDia(String dia) {
	this.dia = dia;
}


}
