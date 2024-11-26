package com.entrega_tech.Repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entrega_tech.Model.Alert;
import com.entrega_tech.Model.SensorPlant;
@Repository
public interface IAlertRepository extends CrudRepository<Alert, Long>{
	@org.springframework.data.jpa.repository.Query(value = "SELECT COUNT(id_alert) from alerts WHERE alert_type = :text and id_sensor_plant = :id_sensor_plant and (alert_status = 'ENVIADA' OR alert_status = 'PENDIENTE')",nativeQuery = true)
	int findByIdAndType(@Param("text") String nombre_alerta,@Param("id_sensor_plant")Long id_sensor);
}
