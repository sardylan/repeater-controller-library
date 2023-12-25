package org.thehellnet.ham.repeatercontroller;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.response.PingResponseCommand;

import java.net.UnknownHostException;

class RepeaterControllerClientTest {

    @Test
    public void testClient() throws UnknownHostException {
        RepeaterControllerClient client = new RepeaterControllerClient();
        client.setServerAddress("qse.thehellnet.org");

        client.start();

        PingResponseCommand ping = client.ping();
        System.out.println(ping.getTimestamp());

        client.stop();
    }
}