package io.github.petermuller.raspberrypigpiocontroller;

/**
 * Created by Peter on 1/1/2015.
 */
public class OutputHandler {

    /**
     * The socket client handles all communications to the RasPi GPIO server
     */
    private SocketClient sc;

    /**
     * Default constructor. Do not use.
     */
    public OutputHandler() {} /*Don't use this*/

    /**
     * Constructor for using socket communication.
     * @param sc SocketClient for GPIO server communication
     */
    public OutputHandler(SocketClient sc){
        this.sc = sc;
    }

    /**
     * The UI to server data transfer
     * @param pin The pin number to change
     * @param value The pin PWM value to set
     */
    public void handleOutput(int pin, int value){
        //TODO unstub
    }
}
