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
@NamedQueries({
	@NamedQuery(name="Sensor.findAll", query="SELECT s FROM Sensor s"),
	@NamedQuery(name="Sensor.findByName", query="SELECT s FROM Sensor s WHERE s.name = :name"),
	@NamedQuery(name="Sensor.findByStation", query="SELECT s FROM Sensor s WHERE s.station = :station"),
}) 
public class Sensor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SENSOR_ID_GENERATOR", sequenceName="SEQ_SENKEY", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SENSOR_ID_GENERATOR")
	@Column(name="senkey")
	private Long id;

	@Column(name="sendescription")
	private String description;

	@Column(name="senname")
	private String name;

	//bi-directional many-to-one association to Station
	@ManyToOne
	@JoinColumn(name="senstaid")
	private Station station;

	//bi-directional many-to-one association to SensorData
	@OneToMany(mappedBy="sensor")
	private List<SensorData> sensordata;

	public Sensor() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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