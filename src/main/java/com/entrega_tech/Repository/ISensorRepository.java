package com.entrega_tech.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entrega_tech.Model.Sensor;
@Repository
public interface ISensorRepository extends CrudRepository<Sensor, Long>{

}
