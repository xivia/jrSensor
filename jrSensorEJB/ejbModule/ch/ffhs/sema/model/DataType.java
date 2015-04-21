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
	private Long dtykey;

	private String dtyname;

	private String dtyunit;

	//bi-directional many-to-one association to SensorData
	@OneToMany(mappedBy="datatype")
	private List<SensorData> sensordata;

	public DataType() {
	}

	public Long getDtykey() {
		return this.dtykey;
	}

	public void setDtykey(Long dtykey) {
		this.dtykey = dtykey;
	}

	public String getDtyname() {
		return this.dtyname;
	}

	public void setDtyname(String dtyname) {
		this.dtyname = dtyname;
	}

	public String getDtyunit() {
		return this.dtyunit;
	}

	public void setDtyunit(String dtyunit) {
		this.dtyunit = dtyunit;
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