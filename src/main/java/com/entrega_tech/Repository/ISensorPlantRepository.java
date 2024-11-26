package com.entrega_tech.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entrega_tech.Model.Sensor;
import com.entrega_tech.Model.SensorPlant;

@Repository
public interface ISensorPlantRepository extends CrudRepository<SensorPlant, Long>{
	List<SensorPlant> findBySensor2(Sensor sensor);
}
