package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.util.Arrays;
import java.util.Objects;

public class TelemetryResponseCommand extends AbstractResponseCommand {

    private float panelVoltage;
    private float panelCurrent;
    private float batteryVoltage;
    private float batteryChargeCurrent;
    private boolean globalStatus;

    public TelemetryResponseCommand() {
        super(CommandType.Telemetry);
    }

    public float getPanelVoltage() {
        return panelVoltage;
    }

    public float getPanelCurrent() {
        return panelCurrent;
    }

    public float getBatteryVoltage() {
        return batteryVoltage;
    }

    public float getBatteryChargeCurrent() {
        return batteryChargeCurrent;
    }

    public boolean isGlobalStatus() {
        return globalStatus;
    }

    @Override
    public void parseArgs(byte[] args) {
        byte[] temp;
        int offset = 0;

        temp = Arrays.copyOfRange(args, offset, offset + 4);
        panelVoltage = ByteUtility.bytesBEToFloat32(temp);
        offset += 4;

        temp = Arrays.copyOfRange(args, offset, offset + 4);
        panelCurrent = ByteUtility.bytesBEToFloat32(temp);
        offset += 4;

        temp = Arrays.copyOfRange(args, offset, offset + 4);
        batteryVoltage = ByteUtility.bytesBEToFloat32(temp);
        offset += 4;

        temp = Arrays.copyOfRange(args, offset, offset + 4);
        batteryChargeCurrent = ByteUtility.bytesBEToFloat32(temp);
        offset += 4;

        globalStatus = args[offset] > 0;
        offset += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TelemetryResponseCommand that = (TelemetryResponseCommand) o;
        return Float.compare(panelVoltage, that.panelVoltage) == 0 && Float.compare(panelCurrent, that.panelCurrent) == 0 && Float.compare(batteryVoltage, that.batteryVoltage) == 0 && Float.compare(batteryChargeCurrent, that.batteryChargeCurrent) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), panelVoltage, panelCurrent, batteryVoltage, batteryChargeCurrent);
    }

    @Override
    public String toString() {
        return String.format("PV: %.02f - PA: %.02f - BV: %.02f - BA: %.02f - GS: %s",
                panelVoltage, panelCurrent, batteryVoltage, batteryChargeCurrent,
                globalStatus ? "ENABLED" : "disabled"
        );
    }
}
