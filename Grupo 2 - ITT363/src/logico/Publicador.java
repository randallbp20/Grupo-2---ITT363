package logico;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

/**
 * 
 */
public class Publicador {

	private static final String BROKER_URL = "tcp://mqtt.eict.ce.pucmm.edu.do:1883";
    private MqttClient client;

    public Publicador(String id){
        String clientId = id;
        try {
            client = new MqttClient(BROKER_URL, clientId);
            
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 
     * @param topic
     * @param mensaje
     */
    public void enviarMensaje(String topic, String mensaje){
        System.out.println("Enviando Informacion Topic: "+ topic);
        try {
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName("itt363-grupo2");
            connectOptions.setPassword("knDH2P6N4w9g".toCharArray());
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setCleanSession(false);
            client.connect(connectOptions);
            client.publish(topic, mensaje.getBytes(), 2, false);
            client.disconnect();
            client.close();
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }

    public static void iniciarPrueba() {
        Thread estacion1 = new Thread(() -> {
            while (true) {
                Gson gson = new Gson();
                Sensor sensor = new Sensor("estacion-1");
                String mensaje = gson.toJson(sensor);
                new Publicador("pub-estacion1").enviarMensaje("/itt363-grupo2/estacion-1/sensores/datos", mensaje);
                try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        Thread estacion2 = new Thread(() -> {
            while (true) {
                Gson gson = new Gson();
                Sensor sensor = new Sensor("estacion-2");
                String mensaje = gson.toJson(sensor);
                new Publicador("pub-estacion2").enviarMensaje("/itt363-grupo2/estacion-2/sensores/datos", mensaje);
                try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        estacion1.start();
        estacion2.start();
    }
}