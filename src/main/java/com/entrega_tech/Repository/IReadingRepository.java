package com.entrega_tech.Repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entrega_tech.Model.Reading;
@Repository
public interface IReadingRepository extends CrudRepository<Reading, Long>{
	/*@org.springframework.data.jpa.repository.Query(value = "select id_reading,reading_value,timestamp,sensor_readings.id_sensor_plant from plant_sensor inner join sensor_readings  on plant_sensor.id_sensor_plant = sensor_readings.id_sensor_plant where id_plant= :id_sensor_plant",nativeQuery = true)
	public List<Reading> getReadsByIdSensorPlant(@Param("id_user") Long id);*/
}
