//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.zentralkomponente.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.iot.zentralkomponente.payload.Bildsensor;

/**
 * BildSensorRegistry merkt sich für jeden Sensor die Dauer
 */
public class BildSensorRegistry {

	private static final Logger LOG = LoggerFactory.getLogger(BildSensorRegistry.class);

	private Map<String, Integer> akkumulierteDaten;

	private Map<String, Integer> anfangspreise;

	private final List<String> ids;

	private final int anfangspreis;

	/**
	 * BildSensorRegistry
	 */
	public BildSensorRegistry(final int anfangspreis) {
		anfangspreise = new HashMap<String, Integer>();
		akkumulierteDaten = new HashMap<String, Integer>();
		ids = new ArrayList<String>();
		this.anfangspreis = anfangspreis;
	}

	/**
	 *
	 * @param sensor Bildsensor
	 */
	public void addToSensordaten(final Bildsensor sensor) {
		final String sensorId = sensor.getId();
		if (!ids.contains(sensorId)) {
			anfangspreise.put(sensorId, anfangspreis);
			ids.add(sensorId);
		}
		final Integer bisher = akkumulierteDaten.getOrDefault(sensorId, 0);

		final int akk = bisher + sensor.getDauer();
		akkumulierteDaten.put(sensorId, akk);

		LOG.info("sensor:{}, akk:{}", sensorId, akk);
	}

	/**
	 * @param bildname
	 * @return int die akkumulierte Anstarrzeit
	 */
	public int getDauerZuBild(final String bildname) {
		return akkumulierteDaten.getOrDefault(bildname, 0);
	}

	public final Map<String, Integer> getAkkumulierteDaten() {
		return akkumulierteDaten;
	}

	public final Map<String, Integer> getAnfangspreise() {
		return anfangspreise;
	}

	public final int getAnfangspreis() {
		return anfangspreis;
	}

	/**
	 *
	 * @return List sortierte Liste aller bekannten Sensor-IDs
	 */
	public List<String> getBildnamen() {
		final List<String> result = new ArrayList<String>();
		result.addAll(akkumulierteDaten.keySet());
		Collections.sort(result);
		return result;
	}
}
