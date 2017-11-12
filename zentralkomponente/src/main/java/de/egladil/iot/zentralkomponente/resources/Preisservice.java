//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.zentralkomponente.resources;

import de.egladil.iot.zentralkomponente.dao.BildSensorRegistry;

/**
 * Preisservice
 */
public class Preisservice {

	int neuerPreisCent(final int anfangspreis, final int sekunden, final int centProSekunde) {
		return anfangspreis * 100 + sekunden * centProSekunde;
	}

	/**
	 * Berechnungsalgorithmus für das Bild mit der gegebenen id.
	 *
	 * @param id String id des sendenden Bildsensors
	 * @param registry BildSensorRegistry
	 * @param centProSekunde int
	 * @return String
	 */
	public String berechneNeuenPreis(final String id, final BildSensorRegistry registry, final int centProSekunde) {
		final String bildname = id.toLowerCase();
		final int sekunden = registry.getDauerZuBild(bildname);
		final int preisInCent = neuerPreisCent(registry.getAnfangspreis(), sekunden, centProSekunde);
		return preisInEuro(preisInCent);
	}

	String preisInEuro(final int cent) {
		final String p = "" + cent;
		final int length = p.length();
		switch (length) {
		case 1:
			return "0,0" + p;
		case 2:
			return "0," + p;
		default:
			return p.substring(0, p.length() - 2) + "," + p.substring(p.length() - 2, p.length());
		}
	}

}
