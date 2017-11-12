package de.egladil.iot.ampel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;

/**
 *
 * PingResource ist ein Beispiel für beschränkte Zugriffe auf REST-Resourcen. Die Annotation @Auth sorgt zusammen mit
 * der Initialisierung der EgladilBVApplication dafür, dass der AccessTokenAuthenticator.authenticate() aufgerufen wird,
 * bevor eine zugriffsbeschränkte Methode aufgerufen wird. Klassen wie PingResource werden in Clients dieses
 * Microservices verwendet.
 */
@Path("/ping")
@Produces(MediaType.TEXT_PLAIN + "; charset=utf-8")
public class PingResource {

	/**
	 * Dient zur CSRF Prävention vor dem Login. Erzeugt ein temporäres CSRF-Token.
	 *
	 * @return Map
	 */
	@GET
	@Timed
	public Response sayHello() {
		return Response.ok().entity("Ampel pong").encoding("UTF-8").build();
	}
}
