package logico;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Representa el suscriptor
 */
public class Suscriptor {

	private static final String BROKER_URL = "tcp://mqtt.eict.ce.pucmm.edu.do:1883";
    private MqttClient client;

    public Suscriptor() {

        String clientId = "suscriptor-1";
        try {
            client = new MqttClient(BROKER_URL, clientId);
        }
        catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Inicio de la 
     */
    public void start() {

        try {
            client.setCallback(new SuscriptorCallback());
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName("itt363-grupo2");
            connectOptions.setPassword("knDH2P6N4w9g".toCharArray());
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setCleanSession(false); 
            client.connect(connectOptions);
            
            client.subscribe("/itt363-grupo2/estacion-1/sensores/#");
            client.subscribe("/itt363-grupo2/estacion-2/sensores/#");
        }
        catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}