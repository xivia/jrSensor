package ch.ffhs.sema.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tsensordata database table.
 * 
 */
@Entity
@Table(name="tsensordata")
@NamedQuery(name="SensorData.findAll", query="SELECT s FROM SensorData s")
public class SensorData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TSENSORDATA_SDAKEY_GENERATOR", sequenceName="SEQ_SDAKEY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TSENSORDATA_SDAKEY_GENERATOR")
	@Column(name="sdakey")
	private Long id;

	@Column(name="sdadt")
	private Timestamp timestamp;

	@Column(name="sdavalue")
	private double value;

	//bi-directional many-to-one association to DataType
	@ManyToOne
	@JoinColumn(name="sdadtyid")
	private DataType datatype;

	//bi-directional many-to-one association to Sensor
	@ManyToOne
	@JoinColumn(name="sdasenid")
	private Sensor sensor;

	public SensorData() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public DataType getDatatype() {
		return this.datatype;
	}

	public void setDatatype(DataType datatype) {
		this.datatype = datatype;
	}

	public Sensor getSensor() {
		return this.sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

}