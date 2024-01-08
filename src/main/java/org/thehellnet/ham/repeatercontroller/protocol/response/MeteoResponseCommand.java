package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.util.Arrays;

public class MeteoResponseCommand extends AbstractResponseCommand {

    private float pressure;
    private float temperature;

    public MeteoResponseCommand() {
        super(CommandType.Meteo);
    }

    public float getPressure() {
        return pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    @Override
    public void parseArgs(byte[] args) {
        byte[] temp;
        int offset = 0;

        temp = Arrays.copyOfRange(args, offset, offset + 4);
        pressure = ByteUtility.bytesBEToFloat32(temp);
        offset += 4;

        temp = Arrays.copyOfRange(args, offset, offset + 4);
        temperature = ByteUtility.bytesBEToFloat32(temp);
        offset += 4;
    }

    @Override
    public String toString() {
        return "MeteoResponseCommand";
    }
}
