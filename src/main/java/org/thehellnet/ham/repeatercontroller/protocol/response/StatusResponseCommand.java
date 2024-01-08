package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.enums.*;

public class StatusResponseCommand extends AbstractResponseCommand {

    private boolean wrongVoltageIdentification;
    private StatusTemperature temperature;
    private StatusBattery battery;
    private StatusCharging charging;
    private StatusArrays arrays;
    private StatusLoad load;

    public StatusResponseCommand() {
        super(CommandType.Status);
    }

    public boolean isWrongVoltageIdentification() {
        return wrongVoltageIdentification;
    }

    public StatusTemperature getTemperature() {
        return temperature;
    }

    public StatusBattery getBattery() {
        return battery;
    }

    public StatusCharging getCharging() {
        return charging;
    }

    public StatusArrays getArrays() {
        return arrays;
    }

    public StatusLoad getLoad() {
        return load;
    }

    @Override
    public void parseArgs(byte[] args) {
        int offset = 0;

        wrongVoltageIdentification = args[offset] > 0;
        offset += 1;

        temperature = StatusTemperature.parse(args[offset]);
        offset += 1;

        battery = StatusBattery.parse(args[offset]);
        offset += 1;

        charging = StatusCharging.parse(args[offset]);
        offset += 1;

        arrays = StatusArrays.parse(args[offset]);
        offset += 1;

        load = StatusLoad.parse(args[offset]);
        offset += 1;
    }

    @Override
    public String toString() {
        return "StatusResponseCommand";
    }
}
