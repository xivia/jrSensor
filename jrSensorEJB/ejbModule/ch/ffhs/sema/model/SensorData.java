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
@NamedQueries({
	@NamedQuery(name="SensorData.findAll", query="SELECT s FROM SensorData s"),
	@NamedQuery(name="SensorData.findBySensor", query="SELECT s FROM SensorData s WHERE s.sensor = :sensor")
}) 
public class SensorData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SENSORDATA_ID_GENERATOR", sequenceName="SEQ_SDAKEY", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SENSORDATA_ID_GENERATOR")
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