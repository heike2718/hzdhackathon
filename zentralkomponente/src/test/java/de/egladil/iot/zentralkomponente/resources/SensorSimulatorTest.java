//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.zentralkomponente.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import de.egladil.iot.zentralkomponente.dao.BildSensorRegistry;
import de.egladil.iot.zentralkomponente.payload.Bildsensor;

/**
 * SensorSimulatorTest
 */
public class SensorSimulatorTest {

	private static final int PREIS = 5;

	private List<String> sensoren;

	private Random random;

	private ReportResource reportResource;

	private BildResource bildResource;

	@Before
	public void setUp() {
		sensoren = Arrays.asList(new String[] { "Gaugin", "van Gogh", "Kandisky" });
		random = new Random();
		final BildSensorRegistry registry = new BildSensorRegistry(100);
		reportResource = new ReportResource(registry, PREIS);
		bildResource = new BildResource(registry, PREIS);
	}

	@Test
	public void sendenUndAbfragen() {
		for (int i = 0; i < 20; i++) {
			final int index = random.nextInt(3);
			final int dauer = random.nextInt(80) + 10;

			final String name = sensoren.get(index);
			final Bildsensor sensor = new Bildsensor(name, dauer);
			final Response response = bildResource.verarbeiteDaten(sensor);
			final String preis = (String) response.getEntity();
			System.out.println(sensor.getId() + ": " + preis);

			System.err.println(reportResource.getPreis(name).getEntity().toString());
		}
		final Response report = reportResource.getReport();
		System.out.println(report.getEntity());
	}

}
