package datos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import gestionDatos.GestionDatosImpl;

public class testGestionDatosImpl {
	@Nested
	@DisplayName("Prueba 13: Prueba del metodo recopilarEstadisticas")
	class P3 {
		@DisplayName("CP42: Test recopilarEstadisticas: Caso de prueba invalido que no debe generar resultado.")
		@Test
		void test1() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "c");
			Bebida n = new Bebida("a");
			bandeja = new Bandeja("12", "12", p2, p2, p2, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);
			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 1) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}
			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();

			Exception eoe = assertThrows(Exception.class, () -> {
				gD.recopilarDatosEstadisticas();
			});

		}

		@Disabled("Prueba no disponible")
		@DisplayName("CP43: Test recopilarEstadisticas: Caso de prueba invalido que no debe generar resultado.")
		@Test
		void test2() {

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			JSONObject objPlatos = new JSONObject();
			JSONArray objPlato = new JSONArray();
			objPlato.put(pPlato);

			objPlatos.put("plato", objPlato);

			File fichero = new File("./src/plato.json");
			fichero.delete();

			GestionDatosImpl gD = new GestionDatosImpl();

			Exception eoe = assertThrows(Exception.class, () -> {
				gD.recopilarDatosEstadisticas();
			});

			try {
				fichero.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}

			FileWriter archivoStreamP;
			try {
				archivoStreamP = new FileWriter(fichero, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@DisplayName("CP44: Test recopilarEstadisticas: Caso de prueba no válido que no debe generar resultado.")
		@Test
		void test3() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "c");
			Bebida n = new Bebida("a");
			bandeja = new Bandeja("12", "12", p2, p2, p2, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());
			objBandeja.getJSONArray("bandeja").put(bandejaJ);
			while (objBandeja.getJSONArray("bandeja").length() > 2) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 1) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}
			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();
			Exception eoe = assertThrows(Exception.class, () -> {
				gD.recopilarDatosEstadisticas();
			});

		}

		@DisplayName("CP45: Test recopilarEstadistica con caso de prueba invalido que no debe generar un resultado ")
		@Test
		void test4() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "a");
			Bebida n = new Bebida("a");
			Plato p3 = new Plato("segundo", "b");
			Plato p4 = new Plato("postre", "c");
			bandeja = new Bandeja("12", "12", p2, p3, p4, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 1) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}
			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			GestionDatosImpl gD = new GestionDatosImpl();

			Exception eoe = assertThrows(Exception.class, () -> {
				gD.recopilarDatosEstadisticas();
			});

		}

		@DisplayName("CP46: Test recopilarEstadistica con caso de prueba invalido que no debe generar un resultado ")
		@Test
		void test5() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "b");
			Bebida n = new Bebida("a");
			Plato p3 = new Plato("segundo", "a");
			Plato p4 = new Plato("postre", "c");
			bandeja = new Bandeja("12", "12", p2, p3, p4, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 1) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}
			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();
			Exception eoe = assertThrows(Exception.class, () -> {
				gD.recopilarDatosEstadisticas();
			});

		}

		@DisplayName("CP47: Test recopilarEstadistica con caso de prueba invalido que no debe generar un resultado ")
		@Test
		void test6() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "c");
			Bebida n = new Bebida("a");
			Plato p3 = new Plato("segundo", "b");
			Plato p4 = new Plato("postre", "a");
			bandeja = new Bandeja("12", "12", p2, p3, p4, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 1) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}
			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();
			Exception eoe = assertThrows(Exception.class, () -> {
				gD.recopilarDatosEstadisticas();
			});

		}

		@DisplayName("CP48: Test recopilarEstadistica con caso de prueba invalido que no debe generar un resultado ")
		@Test
		void test7() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "c");
			Bebida n = new Bebida("a");
			Plato p3 = new Plato("segundo", "b");
			Plato p4 = new Plato("postre", "a");
			bandeja = new Bandeja("12", "12", p2, p3, p4, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 1) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}
			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();
			Exception eoe = assertThrows(Exception.class, () -> {
				gD.recopilarDatosEstadisticas();
			});

		}

		@DisplayName("CP49: Test recopilarEstadistica con caso de prueba invalido que no debe generar un resultado ")
		@Test
		void test8() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "c");
			Bebida n = new Bebida("a");
			Plato p3 = new Plato("segundo", "b");
			Plato p4 = new Plato("postre", "a");
			bandeja = new Bandeja("12", "12", p2, p3, p4, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", "15");
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 1) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}
			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();
				// Esto esta sin probar
				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();

			gD.recopilarDatosEstadisticas();

		}

		@DisplayName("CP51: Test recopilarEstadistica con caso de prueba valido que  debe generar un resultado ")
		@Test
		void test9() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "a");
			Bebida n = new Bebida("a");

			bandeja = new Bandeja("12", "12", p2, p2, p2, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 1) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}
			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}
			while (valoraciones.length() > 2) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();

			assertEquals(1, gD.recopilarDatosEstadisticas().size());

		}

		@DisplayName("CP52: Test recopilarEstadistica con caso de prueba valido que  debe generar un resultado ")
		@Test
		void test10() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "a");
			Bebida n = new Bebida("a");

			bandeja = new Bandeja("12", "12", p2, p2, p2, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 1) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "n");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);
				valoraciones1.put(base1);
				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}

			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();

			assertEquals(1, gD.recopilarDatosEstadisticas().size());

		}

		@DisplayName("CP52: Test recopilarEstadistica con caso de prueba valido que  debe generar un resultado ")
		@Test
		void test11() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "a");
			Bebida n = new Bebida("a");
			Plato p3 = new Plato("segundo", "b");
			Plato p4 = new Plato("postre", "c");
			bandeja = new Bandeja("12", "12", p2, p3, p4, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "b");
			pPlato.put("tipo", "segundo");
			pPlato.put("notaMedia", 1);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "c");
			pPlato.put("tipo", "postre");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 3) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "a");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);

				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}

			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();

			assertEquals(1, gD.recopilarDatosEstadisticas().size());

		}

		@DisplayName("CP53: Test recopilarEstadistica con caso de prueba valido que  debe generar un resultado ")
		@Test
		void test12() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "a");
			Bebida n = new Bebida("a");
			Plato p3 = new Plato("segundo", "b");
			Plato p4 = new Plato("postre", "c");
			bandeja = new Bandeja("12", "12", p2, p3, p4, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "b");
			pPlato.put("tipo", "segundo");
			pPlato.put("notaMedia", 1);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "c");
			pPlato.put("tipo", "postre");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 3) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "b");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);

				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}

			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();

			assertEquals(1, gD.recopilarDatosEstadisticas().size());

		}

		@DisplayName("CP54: Test recopilarEstadistica con caso de prueba valido que  debe generar un resultado ")
		@Test
		void test13() {
			Bandeja bandeja = null;
			Plato Plato = new Plato("primero", "a");
			Plato p2 = new Plato("primero", "a");
			Bebida n = new Bebida("a");
			Plato p3 = new Plato("segundo", "b");
			Plato p4 = new Plato("postre", "c");
			bandeja = new Bandeja("12", "12", p2, p3, p4, n);

			HashMap<Plato, Integer> p1 = new HashMap();
			p1.put(Plato, 1);
			bandeja.getEstadisticas().setValoracionesPlatos(p1);

			JSONObject objBandeja = null, objBase = null, objPlatos = null;

			try {
				String content = new String(Files.readAllBytes(Paths.get("./src/bandeja.json")));
				objBandeja = new JSONObject(content);
				String content2 = new String(Files.readAllBytes(Paths.get("./src/baseEstadistica.json")));
				objBase = new JSONObject(content2);
				String content3 = new String(Files.readAllBytes(Paths.get("./src/plato.json")));
				objPlatos = new JSONObject(content3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray platoArr = objPlatos.getJSONArray("plato");

			JSONObject pPlato = new JSONObject();
			pPlato.put("nombre", "a");
			pPlato.put("tipo", "primero");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "b");
			pPlato.put("tipo", "segundo");
			pPlato.put("notaMedia", 1);
			objPlatos.getJSONArray("plato").put(pPlato);
			pPlato = new JSONObject();
			pPlato.put("nombre", "c");
			pPlato.put("tipo", "postre");
			pPlato.put("notaMedia", 12);
			objPlatos.getJSONArray("plato").put(pPlato);
			JSONObject bandejaJ = new JSONObject();
			bandejaJ.put("ID", Integer.parseInt(bandeja.getId()));
			bandejaJ.put("primeroSeleccion", bandeja.getPrimeroSeleccionado().getNombre());
			bandejaJ.put("segundoSeleccion", bandeja.getSegundoSeleccionado().getNombre());
			bandejaJ.put("postreSeleccion", bandeja.getPostreSeleccionado().getNombre());
			bandejaJ.put("bebidasSeleccion", bandeja.getBebidaSeleccionada().getNombre());
			bandejaJ.put("idVale", bandeja.getIdVale());

			objBandeja.getJSONArray("bandeja").put(bandejaJ);

			while (objBandeja.getJSONArray("bandeja").length() > 1) {
				objBandeja.getJSONArray("bandeja").remove(0);
			}
			while (objPlatos.getJSONArray("plato").length() > 3) {
				objPlatos.getJSONArray("plato").remove(0);
			}

			JSONObject baseEstJ = new JSONObject();
			baseEstJ.put("horaDevolucion", bandeja.getEstadisticas().getHoraDevolucion());
			baseEstJ.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
			baseEstJ.put("dia", bandeja.getEstadisticas().getDia());
			baseEstJ.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));

			JSONArray valoraciones = objBase.getJSONArray("baseEstadistica");

			for (Map.Entry<Plato, Integer> entry : bandeja.getEstadisticas().getValoracionesPlatos().entrySet()) {
				Plato p = entry.getKey();
				Integer i = entry.getValue();
				JSONObject base = new JSONObject();
				JSONObject base1 = new JSONObject();
				base1.put("plato", "c");
				base1.put("nota", i);
				JSONArray valoraciones1 = new JSONArray();
				valoraciones1.put(base1);

				base.put("valoracionesPlatos", valoraciones1);
				base.put("horaDevolucion", "12:12:12");
				base.put("horaAsignacion", bandeja.getEstadisticas().getHoraAsignacion());
				base.put("dia", bandeja.getEstadisticas().getDia());
				base.put("ID_Bandeja", Integer.parseInt(bandeja.getId()));
				valoraciones.put(base);
				// Actualizacion de las notas

			}

			while (valoraciones.length() > 1) {
				valoraciones.remove(0);
			}

			try {
				// Aqui se guardan los datos sobreescribiendo los archivos
				File archivoB = new File("./src/bandeja.json");
				FileWriter archivoStreamB = new FileWriter(archivoB, false);
				archivoStreamB.write(objBandeja.toString());
				archivoStreamB.close();

				File archivoBS = new File("./src/baseEstadistica.json");
				FileWriter archivoStreamBS = new FileWriter(archivoBS, false);
				archivoStreamBS.write(objBase.toString());
				archivoStreamBS.close();
				File archivoP = new File("./src/plato.json");
				FileWriter archivoStreamP = new FileWriter(archivoP, false);
				archivoStreamP.write(objPlatos.toString());
				archivoStreamP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			GestionDatosImpl gD = new GestionDatosImpl();

			assertEquals(1, gD.recopilarDatosEstadisticas().size());

		}
	}


}
