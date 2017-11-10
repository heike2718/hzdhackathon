package de.egladil.iot.zentralkomponente;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import de.egladil.iot.zentralkomponente.config.HackathonRestAPIConfiguration;
import de.egladil.iot.zentralkomponente.health.PingHealthCheck;
import de.egladil.iot.zentralkomponente.resources.PingResource;
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

		final SQLiteConnectionPoolDataSource ds = new SQLiteConnectionPoolDataSource();
		ds.setUrl(configuration.getDburl());
		final DBI jdbi = new DBI(ds);

		final PingResource pingResource = new PingResource();
		environment.jersey().register(pingResource);

		final PingHealthCheck pingHealthCheck = new PingHealthCheck(pingResource);
		environment.healthChecks().register("PingResource", pingHealthCheck);

	}

	@Override
	public String getName() {
		return SERVICE_NAME;
	}
}
