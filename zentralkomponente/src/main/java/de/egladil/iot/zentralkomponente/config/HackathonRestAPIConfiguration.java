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

	private int centProSekunde;

	private int anfangspreisEuro;

	/**
	 * HackathonRestAPIConfiguration
	 */
	public HackathonRestAPIConfiguration() {
	}

	public final int getCentProSekunde() {
		return centProSekunde;
	}

	public final int getAnfangspreisEuro() {
		return anfangspreisEuro;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("HackathonRestAPIConfiguration [centProSekunde=");
		builder.append(centProSekunde);
		builder.append(", anfangspreisEuro=");
		builder.append(anfangspreisEuro);
		builder.append("]");
		return builder.toString();
	}
}
