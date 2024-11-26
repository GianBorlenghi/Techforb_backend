package com.entrega_tech.Model;

import java.time.LocalDateTime;

public class AlertDTO {
    private Long id_alert;
    private String alert_type;
    private String alert_status;
    private LocalDateTime alert_at;
    public Long getId_alert() {
        return id_alert;
    }

    public void setId_alert(Long id_alert) {
        this.id_alert = id_alert;
    }

    public String getAlert_type() {
        return alert_type;
    }

    public void setAlert_type(String alert_type) {
        this.alert_type = alert_type;
    }

  

    public String getAlert_status() {
        return alert_status;
    }

    public void setAlert_status(String alert_status) {
        this.alert_status = alert_status;
    }

	public LocalDateTime getAlert_at() {
		return alert_at;
	}

	public void setAlert_at(LocalDateTime alert_at) {
		this.alert_at = alert_at;
	}
    
}
