//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.zentralkomponente.health;

import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck;

import de.egladil.iot.zentralkomponente.resources.PingResource;

/**
 * PingHealthCheck
 */
public class PingHealthCheck extends HealthCheck {

	private final PingResource pingResource;

	/**
	 * PingHealthCheck
	 */
	public PingHealthCheck(final PingResource pingResource) {
		this.pingResource = pingResource;
	}

	@Override
	protected Result check() throws Exception {
		final Response response = pingResource.sayHello();
		if (response.getStatus() == 200) {
			return Result.healthy();
		}
		return Result.unhealthy("erwarten Statuscode 200, war aber " + response.getStatus());
	}

}
