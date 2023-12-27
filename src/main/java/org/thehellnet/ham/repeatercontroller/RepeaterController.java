package org.thehellnet.ham.repeatercontroller;

import org.thehellnet.ham.repeatercontroller.protocol.response.TelemetryResponseCommand;

import java.net.UnknownHostException;

public class RepeaterController {

    public static void main(String[] args) throws UnknownHostException {
        RepeaterControllerClient client = new RepeaterControllerClient();
        client.setServerAddress("172.29.10.66");

//        PingResponseCommand responseCommand = client.ping();
        TelemetryResponseCommand responseCommand = client.telemetry();
        System.out.println(responseCommand);
    }
}
