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
	private Long stakey;

	private String stadescription;

	private String staname;

	//bi-directional many-to-one association to Sensor
	@OneToMany(mappedBy="station")
	private List<Sensor> sensors;

	public Station() {
	}

	public Long getStakey() {
		return this.stakey;
	}

	public void setStakey(Long stakey) {
		this.stakey = stakey;
	}

	public String getStadescription() {
		return this.stadescription;
	}

	public void setStadescription(String stadescription) {
		this.stadescription = stadescription;
	}

	public String getStaname() {
		return this.staname;
	}

	public void setStaname(String staname) {
		this.staname = staname;
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