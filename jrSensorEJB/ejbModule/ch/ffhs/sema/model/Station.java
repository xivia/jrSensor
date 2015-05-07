package ch.ffhs.sema.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tstation database table.
 * 
 */
@Entity
@Table(name="tstation")
@NamedQuery(name="Station.findAll", query="SELECT s FROM Station s")
public class Station implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TSTATION_STAKEY_GENERATOR", sequenceName="SEQ_STAKEY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TSTATION_STAKEY_GENERATOR")
	@Column(name="stakey")
	private Long id;

	@Column(name="stadescription")
	private String description;

	@Column(name="staname")
	private String name;

	//bi-directional many-to-one association to Sensor
	@OneToMany(mappedBy="station")
	private List<Sensor> sensors;

	public Station() {
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

	public List<Sensor> getSensors() {
		return this.sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	public Sensor addSensor(Sensor sensor) {
		getSensors().add(sensor);
		sensor.setStation(this);

		return sensor;
	}

	public Sensor removeSensor(Sensor sensor) {
		getSensors().remove(sensor);
		sensor.setStation(null);

		return sensor;
	}

}