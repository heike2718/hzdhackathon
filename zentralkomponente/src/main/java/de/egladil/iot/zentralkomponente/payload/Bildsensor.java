//=====================================================
// Projekt: zentralkomponente
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.iot.zentralkomponente.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bildsensor
 */
public class Bildsensor {

	@JsonProperty
	private String id;

	@JsonProperty
	private int dauer;

	@JsonIgnore
	private long timestamp;

	/**
	 * Bildsensor
	 */
	public Bildsensor() {
	}

	/**
	 * Bildsensor
	 */
	public Bildsensor(final String id, final int dauer) {
		this.id = id;
		this.dauer = dauer;
	}

	public final String getId() {
		return id.toLowerCase();
	}

	public final int getDauer() {
		return dauer;
	}

	public final long getTimestamp() {
		return timestamp;
	}

	public final void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Bildsensor other = (Bildsensor) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (timestamp != other.timestamp) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(id);
		builder.append(", dauer=");
		builder.append(dauer);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}
}
