package io.github.petermuller.raspberrypigpiocontroller;

/**
 * Created by Peter on 1/1/2015.
 */
public class PinHandler {

    /**
     * The socket client handles all communications to the RasPi GPIO server
     */
    private SocketClient sc;

    /**
     * Default constructor. Do not use.
     */
    public PinHandler() {} /*Don't use this*/

    /**
     * Constructor for using socket communication.
     * @param sc SocketClient for GPIO server communication
     */
    public PinHandler(SocketClient sc) {
        this.sc = sc;
    }

    /**
     * The UI to server data transfer
     * @param pin The pin number to change
     * @param direction The pin direction to set (input or output)
     */
    public void handlePins(int pin, String direction){
        //TODO unstub
    }

}
