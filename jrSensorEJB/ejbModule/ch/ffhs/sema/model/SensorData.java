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
	private Long sdakey;

	private Timestamp sdadt;

	private double sdavalue;

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

	public Long getSdakey() {
		return this.sdakey;
	}

	public void setSdakey(Long sdakey) {
		this.sdakey = sdakey;
	}

	public Timestamp getSdadt() {
		return this.sdadt;
	}

	public void setSdadt(Timestamp sdadt) {
		this.sdadt = sdadt;
	}

	public double getSdavalue() {
		return this.sdavalue;
	}

	public void setSdavalue(double sdavalue) {
		this.sdavalue = sdavalue;
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