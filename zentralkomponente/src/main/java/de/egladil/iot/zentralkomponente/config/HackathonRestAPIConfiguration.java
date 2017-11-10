//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.zentralkomponente.config;

import io.dropwizard.Configuration;

/**
 * HackathonRestAPIConfiguration
 */
public class HackathonRestAPIConfiguration extends Configuration {

	private String dburl;

	/**
	 * HackathonRestAPIConfiguration
	 */
	public HackathonRestAPIConfiguration() {
	}

	public final String getDburl() {
		return dburl;
	}

}
