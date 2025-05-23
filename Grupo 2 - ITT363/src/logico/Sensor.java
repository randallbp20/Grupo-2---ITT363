package logico;

import java.util.Date;

public class Sensor {

    private String sensorId;
    private double precipitacion;     
    private double direccionViento;   
    private double velocidadViento; 
    private Date fecha = new Date();

    public Sensor(String id) {
        this.sensorId = id;

     
        this.precipitacion = Math.random() * 20;         
        this.direccionViento = Math.random() * 360;       
        this.velocidadViento = Math.random() * 15 + 1;  
    }

    // Getters y Setters
    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public double getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(double precipitacion) {
        this.precipitacion = precipitacion;
    }

    public double getDireccionViento() {
        return direccionViento;
    }

    public void setDireccionViento(double direccionViento) {
        this.direccionViento = direccionViento;
    }

    public double getVelocidadViento() {
        return velocidadViento;
    }

    public void setVelocidadViento(double velocidadViento) {
        this.velocidadViento = velocidadViento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
