//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.ampel;

import io.dropwizard.Configuration;

/**
 * HackathonAmpelConfiguration
 */
public class HackathonAmpelConfiguration extends Configuration {

	private int preis1;

	private int preis2;

	/**
	 * HackathonAmpelConfiguration
	 */
	public HackathonAmpelConfiguration() {
	}

	public final int getPreis1() {
		return preis1;
	}

	public final int getPreis2() {
		return preis2;
	}

}
