package jlopezmx.demo.http;

/**
 * Jetty Embebbed Launcher
 *
 * Jaziel Lopez, Software Engineer, BC, Mexico
 *
 * juan.jaziel@gmail.com
 *
 * https://jlopez.mx
 *
 */
public class Message {

    /**
     *
     */
    String deviceId;

    /**
     *
     */
    String timestamp;

    /**
     *
     * @return
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     *
     * @param deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     *
     * @return
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
