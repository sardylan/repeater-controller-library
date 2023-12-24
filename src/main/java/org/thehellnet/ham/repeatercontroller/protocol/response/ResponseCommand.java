package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;

public interface ResponseCommand {

    ResponseType getResponseType();

    void parseArgs(byte[] args);
}
