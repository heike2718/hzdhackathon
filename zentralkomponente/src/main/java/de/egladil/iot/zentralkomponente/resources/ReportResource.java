//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.zentralkomponente.resources;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.egladil.iot.zentralkomponente.dao.BildSensorRegistry;

/**
 * ReportResource
 */
@Path("/report")
public class ReportResource {

	private final BildSensorRegistry registry;

	private final int centProSekunde;

	private final Preisservice preisservice;

	private int requestCount = 0;

	/**
	 * ReportResource
	 */
	public ReportResource(final BildSensorRegistry registry, final int centProSekunde) {
		this.registry = registry;
		this.centProSekunde = centProSekunde;
		this.preisservice = new Preisservice();
	}

	@GET
	@Produces(MediaType.TEXT_HTML + "; charset=utf-8")
	public Response getReport() {
		this.requestCount++;
		final String datumUhrzeit = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN).format(System.currentTimeMillis());

		final List<String> namen = registry.getBildnamen();
		final StringBuffer sb = new StringBuffer();
		// sb.append("Preisliste (");
		// sb.append(this.requestCount);
		// sb.append(")\n");
		// sb.append("-------------------------------------------\n\n");
		sb.append("<html><body><h2>Preisliste</h2><h3>Abfrage Nummer ");
		sb.append(this.requestCount);
		sb.append("</h3><table>");
		// sb.append("&lt;html&gt;&lt;body&gt;&lt;h2&gt;Preisliste&lt;/h2&gt;&lt;h3&gt;");
		// sb.append(datumUhrzeit);

		// sb.append("&lt;/h3&gt;&lt;table&gt;");
		for (final String name : namen) {
			appendZeileFuerSensor(sb, name);
		}
		sb.append("</table></body></html>");
		return Response.ok().entity(sb.toString()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN + "; charset=utf-8")
	public Response getPreis(@PathParam("id") final String id) {
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).entity("path-Parameter id darf nicht null sein").build();
		}
		final String neuerPreis = new Preisservice().berechneNeuenPreis(id, registry, centProSekunde);
		return Response.ok().entity(neuerPreis).build();
	}

	private void appendZeileFuerSensor(final StringBuffer sb, final String name) {
		final String preisInEuro = preisservice.berechneNeuenPreis(name, registry, centProSekunde);
		sb.append("<tr><td>");
		sb.append(name);
		sb.append(": ");
		sb.append("</td><td>");
		sb.append(preisInEuro);
		sb.append(" EURO");
		sb.append("</td></tr>");

	}
}
