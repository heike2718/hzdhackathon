//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.zentralkomponente.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.iot.zentralkomponente.dao.BildSensorRegistry;
import de.egladil.iot.zentralkomponente.payload.Bildsensor;

/**
 * BildResource
 */
@Path("/bild")
public class BildResource {

	private static final Logger LOG = LoggerFactory.getLogger(BildResource.class);

	private final BildSensorRegistry registry;

	private final int centProSekunde;

	private Preisservice preisservice = new Preisservice();

	/**
	 * BildResource
	 */
	public BildResource(final BildSensorRegistry registry, final int centProSekunde) {
		this.registry = registry;
		this.centProSekunde = centProSekunde;
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN + "; charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
	public Response verarbeiteDaten(final Bildsensor sensor) {
		if (sensor == null) {
			return Response.status(Status.BAD_REQUEST).entity("payload darf nicht null sein").build();
		}
		sensor.setTimestamp(System.currentTimeMillis());
		registry.addToSensordaten(sensor);
		final String euro = preisservice.berechneNeuenPreis(sensor.getId(), registry, centProSekunde);

		LOG.info(sensor.toString());
		return Response.ok().entity(euro).build();
	}
}
