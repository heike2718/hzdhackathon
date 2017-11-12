//=====================================================
// Projekt: ampel
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.ampel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.codahale.metrics.annotation.Timed;

/**
 * AmpelResource
 */
@Path("/ampel")
@Produces(MediaType.TEXT_PLAIN + "; charset=utf-8")
public class AmpelResource {

	private final int preis1;

	private final int preis2;

	/**
	 * AmpelResource
	 */
	public AmpelResource(final int preis1, final int preis2) {
		this.preis1 = preis1;
		this.preis2 = preis2;
	}

	/**
	 * Dient zur CSRF Prävention vor dem Login. Erzeugt ein temporäres CSRF-Token.
	 *
	 * @return Map
	 */
	@GET
	@Timed
	@Path("{preis}")
	public Response registrierePreis(@PathParam("preis") final String preis) {
		int preisInt = 0;
		try {
			preisInt = Integer.valueOf(preis);
		} catch (final NumberFormatException e) {
			return Response.status(Status.BAD_REQUEST).entity("preis muss Zahl sein").build();
		}
		String payload = "";
		if (preisInt < preis1) {
			payload += "Preis kleiner als 400";
			System.out.println(payload);
			// hier jetzt die GPOI-Pin für grün antriggern
		}
		if (400 <= preisInt && preisInt < preis2) {
			payload += "Preis zwischen " + preis1 + " und " + preis2;
			System.out.println(payload);
			// hier jetzt die GPOI-Pin für gelb antriggern
		}
		if (preisInt >= preis2) {
			payload += "Preis größer " + preis2;
			System.out.println(payload);
			// hier jetzt die GPOI-Pin für rot antriggern
		}
		return Response.ok().entity(payload).build();
	}
}
