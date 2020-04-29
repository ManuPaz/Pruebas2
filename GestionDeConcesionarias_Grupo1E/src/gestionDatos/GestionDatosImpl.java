package gestionDatos;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import codigoPrincipal.IGestionDatos;
import datos.Bandeja;
import datos.BaseEstadisticas;
import datos.Bebida;
import datos.Menu;
import datos.Plato;

import org.json.*;

public class GestionDatosImpl implements IGestionDatos{

	@Override
	public ArrayList<Menu> leerMenus() {
		ArrayList<Menu> menus = new ArrayList<Menu>();
		JSONObject objMenus = null,objPlatos = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
			String content2 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
			objPlatos = new JSONObject(content2);
		}catch(Exception e) {
			e.printStackTrace();
		}
			JSONArray menusArr = objMenus.getJSONArray("menu");
			JSONArray platosArr = objPlatos.getJSONArray("plato");
			for(int x=0; x<menusArr.length();x++) {
				ArrayList<Plato> primeros = new ArrayList<Plato>();
				ArrayList<Plato> segundos = new ArrayList<Plato>();
				ArrayList<Plato> postres = new ArrayList<Plato>();
				ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
				JSONArray primerosArray = menusArr.getJSONObject(x).getJSONArray("primerosDisponibles");
				JSONArray segundosArray = menusArr.getJSONObject(x).getJSONArray("segundosDisponibles");
				JSONArray postresArray = menusArr.getJSONObject(x).getJSONArray("postresDisponibles");
				JSONArray bebidasArray = menusArr.getJSONObject(x).getJSONArray("bebidas");
				
				
				for(int y=0;y<primerosArray.length();y++) {
					String nombre,tipo;
					float notamedia;
					nombre = primerosArray.getJSONObject(y).getString("nombre");
					for(int z=0;z<platosArr.length();z++) {
						if(nombre.equals((String)platosArr.getJSONObject(z).get("nombre"))) {
							tipo = (String)platosArr.getJSONObject(z).getString("tipo");
							notamedia = (Float)platosArr.getJSONObject(z).getFloat("notaMedia");
							Plato p = new Plato(tipo,nombre);
							p.setNotaMedia(notamedia);
							primeros.add(p);
							break;
						}
					}
				}
				for(int y=0;y<segundosArray.length();y++) {
					String nombre,tipo;
					float notamedia;
					nombre = segundosArray.getJSONObject(y).getString("nombre");
					for(int z=0;z<platosArr.length();z++) {
						if(nombre.equals((String)platosArr.getJSONObject(z).get("nombre"))) {
							tipo = (String)platosArr.getJSONObject(z).getString("tipo");
							notamedia = (Float)platosArr.getJSONObject(z).getFloat("notaMedia");
							Plato p = new Plato(tipo,nombre);
							p.setNotaMedia(notamedia);
							segundos.add(p);
							break;
						}
					}
				}
				for(int y=0;y<postresArray.length();y++) {
					String nombre,tipo;
					float notamedia;
					nombre = postresArray.getJSONObject(y).getString("nombre");
					for(int z=0;z<platosArr.length();z++) {
						if(nombre.equals((String)platosArr.getJSONObject(z).get("nombre"))) {
							tipo = (String)platosArr.getJSONObject(z).getString("tipo");
							notamedia = (Float)platosArr.getJSONObject(z).getFloat("notaMedia");
							Plato p = new Plato(tipo,nombre);
							p.setNotaMedia(notamedia);
							postres.add(p);
							break;
						}
					}
				}
				for(int y=0;y<bebidasArray.length();y++) {
					Bebida b = new Bebida(bebidasArray.getJSONObject(y).getString("nombre"));
					bebidas.add(b);
				}
				Menu m = new Menu(primeros,segundos,postres,bebidas,menusArr.getJSONObject(x).getString("diaSemana"));
				menus.add(m);
			}
		
		
		return menus;
		
	}

	//cambios con comprobacion realizados, hay que probar esto
	@Override
	public void almacenarMenu(Menu menu) {
		JSONObject objMenus = null,objPlatos = null,objBebidas = null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/menu.json")));
			objMenus = new JSONObject(content);
			String content2 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
			objPlatos = new JSONObject(content2);
			String content3 = new String(Files.readAllBytes(Paths.get("./src/bebida.json")));
			objBebidas = new JSONObject(content3);
		}catch(Exception e) {
			e.printStackTrace();
		}
		JSONArray primeros = new JSONArray();
		JSONArray segundos = new JSONArray();
		JSONArray postres = new JSONArray();
		JSONArray bebidas = new JSONArray();
		JSONObject jmenu = new JSONObject();


		for(int x=0;x<menu.getPrimerosDisponibles().size();x++) {
			JSONObject pMenu = new JSONObject();
			pMenu.put("nombre", menu.getPrimerosDisponibles().get(x).getNombre());
			primeros.put(pMenu);
			JSONArray pAhora = objPlatos.getJSONArray("plato");
			boolean estaPlato= false;
			for(int w =0;w<pAhora.length();w++) {
				if(pAhora.getJSONObject(w).getString("nombre").equals(menu.getPrimerosDisponibles().get(x).getNombre())) {
					estaPlato = true;
				}
			}
			if(!estaPlato) {
				JSONObject pPlato = new JSONObject();
				pPlato.put("nombre", menu.getPrimerosDisponibles().get(x).getNombre());
				pPlato.put("tipo", menu.getPrimerosDisponibles().get(x).getTipo());
				pPlato.put("notaMedia", menu.getPrimerosDisponibles().get(x).getNotaMedia());
				objPlatos.getJSONArray("plato").put(pPlato);
			}
		}
		for(int x=0;x<menu.getSegundosDisponibles().size();x++) {
			JSONObject pMenu = new JSONObject();
			pMenu.put("nombre", menu.getSegundosDisponibles().get(x).getNombre());
			segundos.put(pMenu);
			JSONArray pAhora = objPlatos.getJSONArray("plato");
			boolean estaPlato= false;
			for(int w =0;w<pAhora.length();w++) {
				if(pAhora.getJSONObject(w).getString("nombre").equals(menu.getSegundosDisponibles().get(x).getNombre())) {
					estaPlato = true;
				}
			}
			if(!estaPlato) {
				JSONObject pPlato = new JSONObject();
				pPlato.put("nombre", menu.getSegundosDisponibles().get(x).getNombre());
				pPlato.put("tipo", menu.getSegundosDisponibles().get(x).getTipo());
				pPlato.put("notaMedia", menu.getSegundosDisponibles().get(x).getNotaMedia());
				objPlatos.getJSONArray("plato").put(pPlato);
			}
		}
		for(int x=0;x<menu.getPostresDisponibles().size();x++) {
			JSONObject pMenu = new JSONObject();
			pMenu.put("nombre", menu.getPostresDisponibles().get(x).getNombre());
			postres.put(pMenu);
			JSONArray pAhora = objPlatos.getJSONArray("plato");
			boolean estaPlato= false;
			for(int w =0;w<pAhora.length();w++) {
				if(pAhora.getJSONObject(w).getString("nombre").equals(menu.getPostresDisponibles().get(x).getNombre())) {
					estaPlato = true;
				}
			}
			if(!estaPlato) {
				JSONObject pPlato = new JSONObject();
				pPlato.put("nombre", menu.getPostresDisponibles().get(x).getNombre());
				pPlato.put("tipo", menu.getPostresDisponibles().get(x).getTipo());
				pPlato.put("notaMedia", menu.getPostresDisponibles().get(x).getNotaMedia());
				objPlatos.getJSONArray("plato").put(pPlato);
			}
		}
		for(int x=0;x<menu.getBebidas().size();x++) {
			
			JSONObject bebidaM = new JSONObject();
			bebidaM.put("nombre", menu.getBebidas().get(x).getNombre());
			bebidas.put(bebidaM);
			JSONArray bAhora = objBebidas.getJSONArray("bebida");
			boolean estaBebida = false;
			for(int w=0;w<bAhora.length();w++) {
				if(bAhora.getJSONObject(w).getString("nombre").equals(menu.getBebidas().get(x).getNombre())) {
					estaBebida= true;
				}
			}
			if(!estaBebida) {
				objBebidas.getJSONArray("bebida").put(bebidaM);
			}
		}
		
		jmenu.put("diaSemana", menu.getDiaSemana());
		jmenu.put("primerosDisponibles", primeros);
		jmenu.put("segundosDisponibles", segundos);
		jmenu.put("postresDisponibles", postres);
		jmenu.put("bebidas", bebidas);
		
		
		try {
			//Aqui se guardan los datos sobreescribiendo los archivos
			File archivoM = new File("./src/menu.json");
	        FileWriter archivoStreamM = new FileWriter(archivoM, false);
	        objMenus.getJSONArray("menu").put(jmenu);
	        archivoStreamM.write(objMenus.toString());
	        archivoStreamM.close();
	        
	        File archivoP = new File("./src/plato.json");
	        FileWriter archivoStreamP = new FileWriter(archivoP, false);
	        archivoStreamP.write(objPlatos.toString());
	        archivoStreamP.close();
	        
			File archivoB = new File("./src/bebida.json");
	        FileWriter archivoStreamB = new FileWriter(archivoB, false);
	        archivoStreamB.write(objBebidas.toString());
	        archivoStreamB.close();
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		
        
        
	}

	@Override
	public void almacenarValoracion(Bandeja bandeja) {
		JSONObject objBandeja = null,objBase = null,objPlatos= null;
		try {
			String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
			objBandeja = new JSONObject(content);
			String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
			objBase = new JSONObject(content2);
			String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
			objPlatos = new JSONObject(content3);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		JSONObject bandejaJ = new JSONObject();
		bandejaJ.put("ID",Integer.parseInt(bandeja.getId()));
		bandejaJ.put("primeroSeleccion",bandeja.getPrimeroSeleccionado().getNombre());
		bandejaJ.put("segundoSeleccion",bandeja.getSegundoSeleccionado().getNombre());
		bandejaJ.put("postreSeleccion",bandeja.getPostreSeleccionado().getNombre());
		bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
		bandejaJ.put("idVale", bandeja.getIdVale());
		
		
		objBandeja.getJSONArray("bandeja").put(bandejaJ);
		
		
		JSONObject baseEstJ = new JSONObject();
		baseEstJ.put("horaDevolucion",bandeja.getEstadisticas().getHoraDevolucion());
		baseEstJ.put("horaAsignacion",bandeja.getEstadisticas().getHoraAsignacion());
		baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
		baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
		
		JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");
		JSONArray platoArr = objPlatos.getJSONArray("plato");
		for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
		    Plato p = entry.getKey();
		    Integer i= entry.getValue();
		    JSONObject base = new JSONObject();
		    base.put("plato", p.getNombre());
		    base.put("nota", i);
		    valoraciones.put(base);
		    //Actualizacion de las notas
		    for(int x=0;x<platoArr.length();x++) {
				if(p.getNombre().equals(platoArr.getJSONObject(x).getString("nombre"))) {
					platoArr.getJSONObject(x).put("notaMedia", (bandeja.getEstadisticas().getValoracionesPlatos().get(p)+ platoArr.getJSONObject(x).getFloat("notaMedia"))/2);
				}
			}
		}
		baseEstJ.put("valoracionesPlatos", valoraciones);
		
		
		
				
		
		
		
		try {
			//Aqui se guardan los datos sobreescribiendo los archivos
			File archivoB = new File("./src/bandeja.json");
	        FileWriter archivoStreamB = new FileWriter(archivoB, false);
	        archivoStreamB.write(objBandeja.toString());
	        archivoStreamB.close();
	        //Esto esta sin probar
	        File archivoBS = new File("./src/baseEstadistica.json");
	        FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
	        archivoStreamBS.write(objBase.toString());
	        archivoStreamBS.close();
	        
	        File archivoP = new File("./src/plato.json");
	        FileWriter archivoStreamP = new FileWriter(archivoP, false);
	        archivoStreamP.write(objPlatos.toString());
	        archivoStreamP.close();
	        
		
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}


	@Override
	public ArrayList<Bandeja> recopilarDatosEstadisticas() {
		ArrayList<Bandeja> bandejas = new ArrayList<Bandeja>();
		JSONObject objBandeja = null,objBase = null,objPlato = null;
		try {

			String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
			objBandeja = new JSONObject(content);
			String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
			objBase = new JSONObject(content2);
			String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
			objPlato = new JSONObject(content3);
		}catch(Exception e) {
			e.printStackTrace();
		}
		JSONArray bandejaArr = objBandeja.getJSONArray("bandeja");
		JSONArray baseArr = objBase.getJSONArray("baseEstadistica");
		JSONArray platoArr = objPlato.getJSONArray("plato");
		
		for(int x=0;x<bandejaArr.length();x++) {
			String id,idvale;
			Plato primero=null,segundo=null,postre=null;
			Bebida bebida;
			BaseEstadisticas be=null;
			Bandeja bandeja;
			for(int z=0;z< platoArr.length();z++) {
				if(platoArr.getJSONObject(z).getString("nombre").equals(bandejaArr.getJSONObject(x).getString("primeroSeleccion"))) {
					primero = new Plato("primero",platoArr.getJSONObject(z).getString("nombre"));
					primero.setNotaMedia(platoArr.getJSONObject(z).getFloat("notaMedia"));
				}
				if(platoArr.getJSONObject(z).getString("nombre").equals(bandejaArr.getJSONObject(x).getString("segundoSeleccion"))) {
					segundo = new Plato("segundo",platoArr.getJSONObject(z).getString("nombre"));
					segundo.setNotaMedia(platoArr.getJSONObject(z).getFloat("notaMedia"));
				}
				if(platoArr.getJSONObject(z).getString("nombre").equals(bandejaArr.getJSONObject(x).getString("postreSeleccion"))) {
					postre = new Plato("postre",platoArr.getJSONObject(z).getString("nombre"));
					postre.setNotaMedia(platoArr.getJSONObject(z).getFloat("notaMedia"));
				}
			}
			bebida = new Bebida(bandejaArr.getJSONObject(x).getString("bebidasSeleccion"));
			id =String.valueOf( bandejaArr.getJSONObject(x).getInt("ID"));
			idvale= bandejaArr.getJSONObject(x).getString("idVale");
			
			for(int z=0;z<baseArr.length();z++) {
				if(baseArr.getJSONObject(z).getInt("ID_Bandeja")== bandejaArr.getJSONObject(x).getInt("ID")) {
					HashMap<Plato,Integer> valoraciones = new HashMap<Plato,Integer>();
					String horaAsign,horaDevol,dia;
					
					horaAsign = baseArr.getJSONObject(z).getString("horaAsignacion");
					horaDevol = baseArr.getJSONObject(z).getString("horaDevolucion");
					dia = baseArr.getJSONObject(z).getString("dia");
					
					for(int w=0;w<baseArr.getJSONObject(z).getJSONArray("valoracionesPlatos").length();w++) {
						if(primero.getNombre().equals(baseArr.getJSONObject(z).getJSONArray("valoracionesPlatos").getJSONObject(w).getString("plato"))) {
							valoraciones.put(primero,baseArr.getJSONObject(z).getJSONArray("valoracionesPlatos").getJSONObject(w).getInt("nota"));
						}
						if(segundo.getNombre().equals(baseArr.getJSONObject(z).getJSONArray("valoracionesPlatos").getJSONObject(w).getString("plato"))) {
							valoraciones.put(segundo,baseArr.getJSONObject(z).getJSONArray("valoracionesPlatos").getJSONObject(w).getInt("nota"));
						}
						if(postre.getNombre().equals(baseArr.getJSONObject(z).getJSONArray("valoracionesPlatos").getJSONObject(w).getString("plato"))) {
							valoraciones.put(postre,baseArr.getJSONObject(z).getJSONArray("valoracionesPlatos").getJSONObject(w).getInt("nota"));
						}
					}
					
					be = new BaseEstadisticas(valoraciones,horaDevol,horaAsign,dia);
					break;
					
				}
			}
			bandeja = new Bandeja(id,idvale,primero,segundo,postre,bebida);
			bandeja.setEstadisticas(be);
			bandejas.add(bandeja);
			
		}
		
		
		return bandejas;
	}

}
