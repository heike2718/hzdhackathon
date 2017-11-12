//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.zentralkomponente.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * PreisserviceTest
 */
public class PreisserviceTest {

	@Test
	public void formatPreisDreistellig() {
		// Arrange

		// Act
		final String actual = new Preisservice().preisInEuro(122);

		// Assert
		assertEquals("1,22", actual);
	}

	@Test
	public void formatPreisZweistellig() {
		// Act
		final String actual = new Preisservice().preisInEuro(54);

		// Assert
		assertEquals("0,54", actual);
	}

	@Test
	public void formatPreisEinstellig() {
		// Act
		final String actual = new Preisservice().preisInEuro(4);

		// Assert
		assertEquals("0,04", actual);
	}

	@Test
	public void neuerPreisCentBekanntesBild() {
		// Arrange
		final int anfangspreis = 243;
		final int centProSek = 6;
		final int dauer = 53;

		final int expected = 24618;

		// Act
		final int actual = new Preisservice().neuerPreisCent(anfangspreis, dauer, centProSek);

		// Assert
		assertEquals(expected, actual);

	}

}
