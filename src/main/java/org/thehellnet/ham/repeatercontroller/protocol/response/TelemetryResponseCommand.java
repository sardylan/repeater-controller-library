package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;
import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class TelemetryResponseCommand extends AbstractResponseCommand {

    private LocalDateTime timestamp;
    private float panelVoltage;
    private float panelCurrent;
    private float batteryVoltage;
    private float batteryChargeCurrent;
    private boolean globalStatus;

    public TelemetryResponseCommand(ResponseType responseType) {
        super(CommandType.Telemetry, responseType);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
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

        temp = Arrays.copyOfRange(args, 0, 4);
        timestamp = ByteUtility.bytesToDateTime(temp);

        temp = Arrays.copyOfRange(args, 4, 8);
        panelVoltage = ByteUtility.bytesBEToFloat32(temp);

        temp = Arrays.copyOfRange(args, 8, 12);
        panelCurrent = ByteUtility.bytesBEToFloat32(temp);

        temp = Arrays.copyOfRange(args, 12, 16);
        batteryVoltage = ByteUtility.bytesBEToFloat32(temp);

        temp = Arrays.copyOfRange(args, 16, 20);
        batteryChargeCurrent = ByteUtility.bytesBEToFloat32(temp);

        globalStatus = args[20] > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TelemetryResponseCommand that = (TelemetryResponseCommand) o;
        return Float.compare(panelVoltage, that.panelVoltage) == 0 && Float.compare(panelCurrent, that.panelCurrent) == 0 && Float.compare(batteryVoltage, that.batteryVoltage) == 0 && Float.compare(batteryChargeCurrent, that.batteryChargeCurrent) == 0 && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timestamp, panelVoltage, panelCurrent, batteryVoltage, batteryChargeCurrent);
    }

    @Override
    public String toString() {
        return String.format("%s - PV: %.02f - PA: %.02f - BV: %.02f - BA: %.02f - GS: %s",
                timestamp.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                panelVoltage, panelCurrent, batteryVoltage, batteryChargeCurrent,
                globalStatus ? "ENABLED" : "disabled"
        );
    }
}
