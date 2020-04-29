package gestionMenus;

import java.text.SimpleDateFormat;

import datos.*;
import codigoPrincipal.*;
import java.util.*;

public class GestionMenusImpl implements IGestionMenus{
	//esta funcion guardaria el menu en la base de datos
	IGestionDatos datos;
	
	public GestionMenusImpl(IGestionDatos datos) {
		super();
		this.datos = datos;
	}
	private String _diaSemana() {
		String diaS="";
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(now);
	    int dia=calendar.get(Calendar.DAY_OF_WEEK);
		switch(dia)
        {
            case 1:
            	diaS="Domingo";
                break;
            case 2:
            	diaS="Lunes";
                break;
            case 3:
            	diaS="Martes";
                break;
            case 4:
            	diaS="Miercoles";
                break;
            case 5:
            	diaS="Jueves";
                break;
            case 6:
            	diaS="Jueves";
                break;
            case 7:
               diaS= "Sabado";
                break;
        }
		return diaS;
	}

	public void construirMenu(String dia, ArrayList<Plato> primeros,  ArrayList<Plato> segundos,  ArrayList<Plato> postres, ArrayList<Bebida> bebidas) {
		Menu nuevoMenu= new Menu(primeros, segundos, postres, bebidas, dia);
		datos.almacenarMenu(nuevoMenu);
	}
	
	public Menu mostrarMenuDelDia() {
		ArrayList<Menu> menusDisponibles=datos.leerMenus();
		Menu menuDia = new Menu();
		for(Menu menu: menusDisponibles) {
			if(menu.getDiaSemana().equals(_diaSemana())) {
				menuDia=menu;
			}
		}
		return menuDia;
	}
	
	public ArrayList<Menu> mostrarMenus(){
		return datos.leerMenus();
	}

	 
}
