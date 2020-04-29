package datos;
import java.util.*;
import java.text.SimpleDateFormat;

public class Bandeja {
private String id;
private String idVale;
private Plato primeroSeleccionado;
private Plato segundoSeleccionado;
private Plato postreSeleccionado;
private Bebida bebidaSeleccionada;
private BaseEstadisticas estadisticas;


public Bandeja(Plato primeroSeleccionado, Plato segundoSeleccionado, Plato postreSeleccionado,
		Bebida bebidaSeleccionada) {
	super();
	this.primeroSeleccionado = primeroSeleccionado;
	this.segundoSeleccionado = segundoSeleccionado;
	this.postreSeleccionado = postreSeleccionado;
	this.bebidaSeleccionada = bebidaSeleccionada;
}
public Bandeja(String id, String idVale, Plato primeroSeleccionado, Plato segundoSeleccionado, Plato postreSeleccionado,
		Bebida bebidaSeleccionada) {
	this.id = id;
	this.idVale = idVale;
	this.primeroSeleccionado = primeroSeleccionado;
	this.segundoSeleccionado = segundoSeleccionado;
	this.postreSeleccionado = postreSeleccionado;
	this.bebidaSeleccionada = bebidaSeleccionada;
	Date date = new Date();   // given date
	Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
	calendar.setTime(date);   // assigns calendar to given date 
	//cuando se crea la bandeja se le asigna la hora de asignacion y el dia actual
	this.estadisticas=new BaseEstadisticas(new Date().toString().substring(11, 20),new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getIdVale() {
	return idVale;
}
public void setIdVale(String idVale) {
	this.idVale = idVale;
}
public Plato getPrimeroSeleccionado() {
	return primeroSeleccionado;
}
public void setPrimeroSeleccionado(Plato primeroSeleccionado) {
	this.primeroSeleccionado = primeroSeleccionado;
}
public Plato getSegundoSeleccionado() {
	return segundoSeleccionado;
}
public void setSegundoSeleccionado(Plato segundoSeleccionado) {
	this.segundoSeleccionado = segundoSeleccionado;
}
public Plato getPostreSeleccionado() {
	return postreSeleccionado;
}
public void setPostreSeleccionado(Plato postreSeleccionado) {
	this.postreSeleccionado = postreSeleccionado;
}
public Bebida getBebidaSeleccionada() {
	return bebidaSeleccionada;
}
public void setBebidaSeleccionada(Bebida bebidaSeleccionada) {
	this.bebidaSeleccionada = bebidaSeleccionada;
}
public BaseEstadisticas getEstadisticas() {
	return estadisticas;
}
public void setEstadisticas(BaseEstadisticas estadisticas) {
	this.estadisticas = estadisticas;
}




}
