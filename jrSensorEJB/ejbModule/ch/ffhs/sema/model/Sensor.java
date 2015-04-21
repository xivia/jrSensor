package ch.ffhs.sema.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tsensor database table.
 * 
 */
@Entity
@Table(name="tsensor")
@NamedQuery(name="Sensor.findAll", query="SELECT s FROM Sensor s")
public class Sensor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TSENSOR_SENKEY_GENERATOR", sequenceName="SEQ_SENKEY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TSENSOR_SENKEY_GENERATOR")
	private Long senkey;

	private String sendescription;

	private String senname;

	//bi-directional many-to-one association to Station
	@ManyToOne
	@JoinColumn(name="senstaid")
	private Station station;

	//bi-directional many-to-one association to SensorData
	@OneToMany(mappedBy="sensor")
	private List<SensorData> sensordata;

	public Sensor() {
	}

	public Long getSenkey() {
		return this.senkey;
	}

	public void setSenkey(Long senkey) {
		this.senkey = senkey;
	}

	public String getSendescription() {
		return this.sendescription;
	}

	public void setSendescription(String sendescription) {
		this.sendescription = sendescription;
	}

	public String getSenname() {
		return this.senname;
	}

	public void setSenname(String senname) {
		this.senname = senname;
	}

	public Station getStation() {
		return this.station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public List<SensorData> getSensordata() {
		return this.sensordata;
	}

	public void setSensordata(List<SensorData> sensordata) {
		this.sensordata = sensordata;
	}

	public SensorData addSensordata(SensorData sensordata) {
		getSensordata().add(sensordata);
		sensordata.setSensor(this);

		return sensordata;
	}

	public SensorData removeSensordata(SensorData sensordata) {
		getSensordata().remove(sensordata);
		sensordata.setSensor(null);

		return sensordata;
	}

}