package ch.ffhs.sema.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tdatatype database table.
 * 
 */
@Entity
@Table(name="tdatatype")
@NamedQuery(name="DataType.findAll", query="SELECT d FROM DataType d")
public class DataType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TDATATYPE_DTYKEY_GENERATOR", sequenceName="SEQ_DTYKEY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TDATATYPE_DTYKEY_GENERATOR")
	@Column(name="dtykey")
	private Long id;

	@Column(name="dtyname")
	private String name;

	@Column(name="dtyunit")
	private String unit;

	//bi-directional many-to-one association to SensorData
	@OneToMany(mappedBy="datatype")
	private List<SensorData> sensordata;

	public DataType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<SensorData> getSensordata() {
		return this.sensordata;
	}

	public void setSensordata(List<SensorData> sensordata) {
		this.sensordata = sensordata;
	}

	public SensorData addSensordata(SensorData sensordata) {
		getSensordata().add(sensordata);
		sensordata.setDatatype(this);

		return sensordata;
	}

	public SensorData removeSensordata(SensorData sensordata) {
		getSensordata().remove(sensordata);
		sensordata.setDatatype(null);

		return sensordata;
	}

}