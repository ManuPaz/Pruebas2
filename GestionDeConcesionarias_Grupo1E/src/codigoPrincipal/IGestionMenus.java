package codigoPrincipal;
import datos.*;
import java.util.*;
public interface IGestionMenus {

	public void construirMenu(String dia, ArrayList<Plato> primeros,  ArrayList<Plato> segundos,  ArrayList<Plato> postres, ArrayList<Bebida> bebida);
	public Menu mostrarMenuDelDia(); //igual aqui deberiamos pedir el dia de la semana y asi permitiriamos ver los de otros dias
	//esta basicamente muestra los de toda la semana
	public ArrayList<Menu> mostrarMenus();
}
