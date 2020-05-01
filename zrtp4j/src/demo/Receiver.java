package demo;

import java.net.*;

import javax.media.*;
import javax.media.control.*;
import javax.media.protocol.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;

/**
 */
public class Receiver implements ReceiveStreamListener, SessionListener,
        BufferTransferHandler {

    private RTPManager mgr = null;

    public Receiver() {
    }

    public void run() {
        this.initialize();
    }

    /**
     * Initializes an RTP session
     */
    protected void initialize() {

        InetAddress ia = null;
        try {
            ia = InetAddress.getByName("localhost");
        } catch (java.net.UnknownHostException ex) {
            System.err.println("Unknown local host: " + ex.getMessage());
        }
        System.err.println("Internet address: " + ia);
        SessionAddress sa = new SessionAddress(ia, 5002);
        SessionAddress target = new SessionAddress(ia, 5004);
        try {

            mgr = RTPManager.newInstance();

            mgr.initialize(sa);

            mgr.addSessionListener(this);
            mgr.addReceiveStreamListener(this);

            mgr.addTarget(target);

            BufferControl bc = (BufferControl) mgr
                    .getControl("javax.media.control.BufferControl");
            if (bc != null) {
                System.err.println("Buffer length: " + bc.getBufferLength()
                        + ", Threshold: " + bc.getEnabledThreshold()
                        + ", minimum: " + bc.getMinimumThreshold());
                // bc.setEnabledThreshold(false);
                bc.setBufferLength(20);

                System.err.println("Buffer length: " + bc.getBufferLength());
            }
        } catch (Exception e) {
            System.err.println("Cannot create the RTP Session: "
                    + e.getMessage());
        }
    }

    /**
     * Closes the players and the session manager.
     */
    protected void close() {

        // close the RTP session.

        mgr.removeReceiveStreamListener(this);
        mgr.dispose();
        mgr = null;
    }

    /**
     * SessionListener.
     */

    /*
     * (Kein Javadoc)
     * 
     * @see javax.media.rtp.SessionListener#update(javax.media.rtp.event.SessionEvent)
     */
    public void update(SessionEvent evt) {
        System.err.println("SessionEvent received: " + evt);
        if (evt instanceof NewParticipantEvent) {
            NewParticipantEvent npe = (NewParticipantEvent) evt;
            System.err.println("cname: " + npe.getParticipant().getCNAME());
            // nothing to do
        }
    }

    /**
     * ReceiveStreamListener.
     */

    /*
     * (Kein Javadoc)
     * 
     * @see javax.media.rtp.ReceiveStreamListener#update(javax.media.rtp.event.ReceiveStreamEvent)
     */
    public void update(ReceiveStreamEvent evt) {

        System.err.println("ReceiveStreamEvent received: " + evt);
        // RTPManager mgr = (RTPManager) evt.getSource();
        // Participant participant = evt.getParticipant(); // could be null.
        ReceiveStream stream = evt.getReceiveStream(); // could be null.

        if (evt instanceof RemotePayloadChangeEvent) {
            System.err
                    .println("RemotePayloadChangeEvent received. Can't handle this event. "
                            + evt);
        } else if (evt instanceof NewReceiveStreamEvent) {

            try {
                /**
                 * @todo is this really neccessary? already got stream above?
                 */
                stream = ((NewReceiveStreamEvent) evt).getReceiveStream();
                PushBufferDataSource ds = (PushBufferDataSource) stream
                        .getDataSource();
                // Find out the formats.
                RTPControl ctl = (RTPControl) ds
                        .getControl("javax.media.rtp.RTPControl");
                if (ctl != null) {
                    System.err.println("Received new RTP stream: "
                            + ctl.getFormat());
                } else {
                    System.err.println("Received new RTP stream");
                }

                // System.err.println("DS is: " + ds.toString());
                PushBufferStream[] pbs = ds.getStreams();
                // System.err.println("Number of pbs: " + pbs.length);
                // System.err.println("pbs format: " + pbs[0].getFormat());
                pbs[0].setTransferHandler(this);
                ds.start();

            } catch (Exception e) {
                System.err.println("NewReceiveStreamEvent exception "
                        + e.getMessage());
                return;
            }

        } else if (evt instanceof StreamMappedEvent) {

        } else if (evt instanceof ByeEvent) {

            System.err.println("BYE received");
        } else {
            System.err.println("Unknown Event: " + evt);
        }
    }

    /*
     * Method required by BufferTransferHandler
     */
    public void transferData(PushBufferStream stream) {
        // System.err.println("Received a transferData request from: " +
        // stream.toString());
        Buffer buf = new Buffer();
        try {
            stream.read(buf);
        } catch (java.io.IOException ex) {
            System.err.println("Buffer read exception: " + ex.getMessage());
        }
        Format fmt = buf.getFormat();
        Class<?> cls = fmt.getDataType();
        System.err.println("buf length: " + buf.getLength() + ", timestamp: "
                + buf.getTimeStamp());
        // System.err.println("buffer: " + buf.getFormat().toString());

        if (cls == Format.byteArray) {
            byte[] data = (byte[]) buf.getData();
            System.err.println("Data: '"
                    + new String(data, buf.getOffset(), buf.getLength()) + "'");
        }
    }

    public static void main(String[] args) {

        Receiver rcv = new Receiver();
        // rcv.start();
        rcv.run();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException ie) {
        }

        System.exit(0);
    }
}
