package datos;

public class Plato {
private String tipo;
private String nombre;
private float notaMedia;
public Plato(String tipo, String nombre) {
	this.tipo = tipo;
	this.nombre = nombre;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public float getNotaMedia() {
	return notaMedia;
}
public void setNotaMedia(float notaMedia) {
	this.notaMedia = notaMedia;
}


}
