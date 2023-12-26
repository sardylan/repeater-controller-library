package org.thehellnet.ham.repeatercontroller;

import org.thehellnet.ham.repeatercontroller.protocol.response.TelemetryResponseCommand;

import java.net.UnknownHostException;

public class RepeaterController {

    public static void main(String[] args) throws UnknownHostException {
        RepeaterControllerClient client = new RepeaterControllerClient();
        client.setServerAddress("qse.thehellnet.org");

        TelemetryResponseCommand responseCommand = client.telemetry();
        System.out.println(responseCommand);
    }
}
