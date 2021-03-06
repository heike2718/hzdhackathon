package de.egladil.iot.zentralkomponente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.iot.zentralkomponente.config.HackathonRestAPIConfiguration;
import de.egladil.iot.zentralkomponente.dao.BildSensorRegistry;
import de.egladil.iot.zentralkomponente.health.PingHealthCheck;
import de.egladil.iot.zentralkomponente.resources.BildResource;
import de.egladil.iot.zentralkomponente.resources.PingResource;
import de.egladil.iot.zentralkomponente.resources.ReportResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class HackathonRestAPI extends Application<HackathonRestAPIConfiguration> {

	private static final String SERVICE_NAME = "Hackathon-REST-API";

	private static final Logger LOG = LoggerFactory.getLogger(HackathonRestAPI.class);

	public static void main(final String[] args) {
		try {
			final HackathonRestAPI application = new HackathonRestAPI();
			application.run(args);
		} catch (final Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void run(final HackathonRestAPIConfiguration configuration, final Environment environment) throws Exception {

		LOG.info("Starten Rest-API mit {}", configuration);

//		configureCors(configuration, environment);

		final PingResource pingResource = new PingResource();
		environment.jersey().register(pingResource);

		final PingHealthCheck pingHealthCheck = new PingHealthCheck(pingResource);
		environment.healthChecks().register("PingResource", pingHealthCheck);

		final BildSensorRegistry bildRegistry = new BildSensorRegistry(configuration.getAnfangspreisEuro());
		final BildResource bildResource = new BildResource(bildRegistry, configuration.getCentProSekunde());
		environment.jersey().register(bildResource);

		final ReportResource reportResource = new ReportResource(bildRegistry, configuration.getCentProSekunde());
		environment.jersey().register(reportResource);
	}

//	private void configureCors(final HackathonRestAPIConfiguration configuration, final Environment environment) {
//		// ab Dropwizard 0.8.1 erforderlich
//		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
//		final FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORSFilter", CrossOriginFilter.class);
//
//		filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
//
//		// Add URL mapping
//		// FIXME: secure?
//		filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
//	}

	@Override
	public String getName() {
		return SERVICE_NAME;
	}
}
