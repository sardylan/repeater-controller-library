package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.thehellnet.ham.repeatercontroller.protocol.CommandByte;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.TimestampCommand;
import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.util.Arrays;
import java.util.Objects;

public class TelemetryCommand extends TimestampCommand {

    private float panelVoltage = 0;
    private float panelCurrent = 0;
    private float batteryVoltage = 0;
    private float batteryChargeCurrent = 0;

    public TelemetryCommand(CommandType commandType) {
        super(commandType, CommandByte.Telemetry);
    }

    public float getPanelVoltage() {
        return panelVoltage;
    }

    public void setPanelVoltage(float panelVoltage) {
        this.panelVoltage = panelVoltage;
    }

    public float getPanelCurrent() {
        return panelCurrent;
    }

    public void setPanelCurrent(float panelCurrent) {
        this.panelCurrent = panelCurrent;
    }

    public float getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(float batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public float getBatteryChargeCurrent() {
        return batteryChargeCurrent;
    }

    public void setBatteryChargeCurrent(float batteryChargeCurrent) {
        this.batteryChargeCurrent = batteryChargeCurrent;
    }

    @Override
    public byte[] serializeArgs() {
        if (getCommandType() == CommandType.Request) {
            return new byte[0];
        }

        byte[] args = new byte[20];

        System.arraycopy(super.serializeArgs(), 0, args, 0, 4);
        System.arraycopy(ByteUtility.float32ToBytesBE(panelVoltage), 0, args, 4, 4);
        System.arraycopy(ByteUtility.float32ToBytesBE(panelCurrent), 0, args, 8, 4);
        System.arraycopy(ByteUtility.float32ToBytesBE(batteryVoltage), 0, args, 12, 4);
        System.arraycopy(ByteUtility.float32ToBytesBE(batteryChargeCurrent), 0, args, 16, 4);

        return args;
    }

    @Override
    protected void parseCommandArgs(byte[] args) {
        super.parseCommandArgs(args);

        byte[] temp = Arrays.copyOfRange(args, 4, 8);
        panelVoltage = ByteUtility.bytesBEToFloat32(temp);

        temp = Arrays.copyOfRange(args, 8, 12);
        panelCurrent = ByteUtility.bytesBEToFloat32(temp);

        temp = Arrays.copyOfRange(args, 12, 16);
        batteryVoltage = ByteUtility.bytesBEToFloat32(temp);

        temp = Arrays.copyOfRange(args, 16, 20);
        batteryChargeCurrent = ByteUtility.bytesBEToFloat32(temp);
    }

    @Override
    protected int argsSize() {
        if (getCommandType() == CommandType.Request) {
            return 0;
        }

        return 20;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TelemetryCommand that = (TelemetryCommand) o;
        return Float.compare(panelVoltage, that.panelVoltage) == 0 && Float.compare(panelCurrent, that.panelCurrent) == 0 && Float.compare(batteryVoltage, that.batteryVoltage) == 0 && Float.compare(batteryChargeCurrent, that.batteryChargeCurrent) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), panelVoltage, panelCurrent, batteryVoltage, batteryChargeCurrent);
    }

    @Override
    public String toString() {
        return String.format("%s - PV: %.02f - PA: %.02f - BV: %.02f - BA: %.02f", super.toString(), panelVoltage, panelCurrent, batteryVoltage, batteryChargeCurrent);
    }

}
