package de.egladil.iot.ampel;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class HackathonAmpel extends Application<HackathonAmpelConfiguration> {

	public static void main(final String[] args) {
		try {
			final HackathonAmpel application = new HackathonAmpel();
			application.run(args);
		} catch (final Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public String getName() {
		return "AMPEL";
	}

	@Override
	public void run(final HackathonAmpelConfiguration config, final Environment environment) throws Exception {
		environment.jersey().register(new AmpelResource(config.getPreis1(), config.getPreis2()));
		environment.jersey().register(new PingResource());
	}
}
