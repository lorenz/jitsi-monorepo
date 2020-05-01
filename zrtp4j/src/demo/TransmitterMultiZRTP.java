
package demo;

import gnu.java.zrtp.ZrtpCodes;
import gnu.java.zrtp.ZrtpConstants;
import gnu.java.zrtp.ZrtpUserCallback;
import gnu.java.zrtp.ZrtpConfigure;
import gnu.java.zrtp.jmf.transform.TransformManager;
import gnu.java.zrtp.jmf.transform.zrtp.ZRTPTransformEngine;
import gnu.java.zrtp.jmf.transform.zrtp.ZrtpTransformConnector;

import java.net.*;
import java.util.EnumSet;
import java.util.Iterator;

import javax.media.rtp.*;
import javax.media.rtp.event.NewSendStreamEvent;
import javax.media.rtp.event.SendStreamEvent;

public class TransmitterMultiZRTP {

    Thread senderThread = null;
    Thread multiSenderThread = null;
    SenderMulti senderFirst = null;
    SenderMulti senderSecond = null;
    
    public class SenderMulti implements SendStreamListener, Runnable {

        public SimpleDataSource dataOutput = null;

        RTPManager rtpManager = null;

        SessionAddress sa = null;

        SessionAddress target = null;

        ZrtpTransformConnector transConnector = null;

        ZRTPTransformEngine zrtpEngine = null;
       
        boolean multiStream = false;
        
        byte[] multiParams = null;

        InetAddress ia = null;

        SendStream sendStream = null;
        
        protected class MyCallback extends ZrtpUserCallback {
            
            String prefix = new String("");
            MyCallback() {
            }

            public void secureOn(String cipher) {
                System.err.println(prefix + "Tx Cipher: " + cipher);
                System.err.println(prefix + "Tx peer hello hash: " + zrtpEngine.getPeerHelloHash());
            }

            public void showSAS(String sas, boolean verified) {
                System.err.println(prefix + "Tx SAS: " + sas + ", verified: " + verified);
            }

            public void showMessage(ZrtpCodes.MessageSeverity sev, EnumSet<?> subCode) {
                Iterator<?> ii = subCode.iterator();
                if (sev == ZrtpCodes.MessageSeverity.Info) {
                    ZrtpCodes.InfoCodes inf = (ZrtpCodes.InfoCodes)ii.next();
                    System.err.println(prefix + "Tx show message sub code: " + inf);
                    if (inf == ZrtpCodes.InfoCodes.InfoSecureStateOn) {
                        System.err.println(prefix + "Tx peer hello hash: " + senderFirst.zrtpEngine.getPeerHelloHash());
                        senderSecond.setMultiStreamParams(senderFirst.getMultiStreamParams());
                        multiSenderThread.start();
                    }
                    return;
                }
                System.err.println(prefix + "Tx show message sub code: " + ii.next());
            }

            public void zrtpNegotiationFailed(ZrtpCodes.MessageSeverity severity, EnumSet<?> subCode) {
                Iterator<?> ii = subCode.iterator();
                System.err.println(prefix + "Tx negotiation failed sub code: " + ii.next());
            }

            public void secureOff() {
                System.err.println(prefix + "Tx Security off");
            }

            public void zrtpNotSuppOther() {
                System.err.println(prefix + "Tx ZRTP not supported");
            }

            public void signSAS(byte[] sasHash) {
                System.err.println("Transmitter: SAS to sign: ");
                byte[] sign = new byte[12];
                sign[0] = sasHash[0];
                sign[1] = sasHash[1];
                sign[2] = sasHash[2];
                sign[3] = sasHash[3];
                sign[4] = (byte)'T';
                sign[5] = (byte)'R';
                sign[6] = (byte)'A';
                sign[7] = (byte)'N';
                sign[8] = (byte)'S';
                sign[9] = (byte)'M';
                sign[10] = (byte)'I';
                sign[11] = (byte)'T';
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.err.println("Transmitter set signature data result: " + zrtpEngine.setSignatureData(sign));
            }

            public boolean checkSASSignature(byte[] sasHash) {
                System.err.print("Transmitter: check signature: ");
                byte[] sign = zrtpEngine.getSignatureData();
                String signStrng = new String(sign);
                System.err.println(signStrng);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            }

            void setPrefix(String pre) {
                prefix = pre;
            }
        }

        protected class MyCallbackMulti extends MyCallback {
            MyCallbackMulti() {
            }

            public void showMessage(ZrtpCodes.MessageSeverity sev, EnumSet<?> subCode) {
                Iterator<?> ii = subCode.iterator();
                System.err.println(prefix + "Tx show message sub code: " + ii.next());
            }

        }

        public SenderMulti() {
            try {
                ia = InetAddress.getByName("localhost");
            } catch (java.net.UnknownHostException ex) {
                System.err.println("Unknown local host: " + ex.getMessage());
            }

            System.err.println("Internet address: " + ia);

        }

        public void init() {
            if (!multiStream) {
                sa = new SessionAddress(ia, 5004);
                target = new SessionAddress(ia, 5002);
            }
            else {
                sa = new SessionAddress(ia, 5004+10);
                target = new SessionAddress(ia, 5002+10);
                
            }
            // create a send stream for the output data source
            dataOutput = createDataSource();

            // create the RTP Manager
            rtpManager = RTPManager.newInstance();

            try {
                // create a ZRTP connector with own bind address
                transConnector = (ZrtpTransformConnector) TransformManager
                        .createZRTPConnector(sa);
                zrtpEngine = transConnector.getEngine();
                
                ZrtpConfigure config = new ZrtpConfigure();
                config.setStandardConfig();
//                config.clear();
//                config.addPubKeyAlgo(ZrtpConstants.SupportedPubKeys.DH2K);
//                config.addPubKeyAlgo(ZrtpConstants.SupportedPubKeys.DH3K);
//                config.addPubKeyAlgo(ZrtpConstants.SupportedPubKeys.EC25);
//                config.addHashAlgo(ZrtpConstants.SupportedHashes.S384);

//                config.setMandatoryOnly();
//                config.addSasTypeAlgo(ZrtpConstants.SupportedSASTypes.B256);
                // IMPORTANT: crypto provider must be set before initialization
                // IMPORTANT: set other data only _after_ initialization
                if (multiStream) {
                    MyCallbackMulti mcb = new MyCallbackMulti();
                    mcb.setPrefix("multi - ");
                    zrtpEngine.setUserCallback(mcb);
                    if (!zrtpEngine.initialize("test_r.zid", config))
                        System.err.println("TX: Initialize failed, multi: " + multiStream);
                    zrtpEngine.setMultiStrParams(multiParams);
                    int versions = zrtpEngine.getNumberSupportedVersions();
                    for (int idx = 0; idx < versions; idx++)
                        System.out.println("multi - Tx Hello hash: " + zrtpEngine.getHelloHash(idx));
                } else {
                    zrtpEngine.setSignSas(true);
                    zrtpEngine.setParanoidMode(true);
                    zrtpEngine.setUserCallback(new MyCallback());
                    if (!zrtpEngine.initialize("test_r.zid", config))
                        System.err.println("TX: Initialize failed, multi: " + multiStream);
                    int versions = zrtpEngine.getNumberSupportedVersions();
                    for (int idx = 0; idx < versions; idx++)
                        System.out.println("Tx Hello hash: " + zrtpEngine.getHelloHash(idx));
                }
                // initialize the RTPManager using the SRTP connector
                rtpManager.addSendStreamListener(this);
                // Add a transmit target, must be done in connector
                transConnector.addTarget(target);
                
                rtpManager.initialize(transConnector);
                
                sendStream = rtpManager.createSendStream(dataOutput, 0);
                sendStream.start();
            } catch (java.io.IOException ex) {
                System.err.println("Cannot start sendStream: "
                        + ex.getMessage());
            } catch (javax.media.rtp.InvalidSessionAddressException ex) {
                System.err.println("Invalid session address: "
                        + ex.getMessage());
            } catch (javax.media.format.UnsupportedFormatException ex) {
                System.err.println("Unsupported format: " + ex.getMessage());
            }
        }

        public void stopIt() {

            // close the connection if no longer needed.
//            transConnector.removeTarget(target);
            // close the connection if no longer needed.
            sendStream.close();
            zrtpEngine.close();
           

            // call dispose at the end of the life-cycle of this RTPManager so
            // it is prepared to be garbage-collected.
            rtpManager.dispose();
        }

        SimpleDataSource createDataSource() {
            SimpleDataSource sps = new SimpleDataSource();
            return sps;
        }

        public void update(SendStreamEvent evt) {
            // System.err.println("TX: SendStreamEvent received: " + evt);
            if (evt instanceof NewSendStreamEvent) {
                SendStream ss = evt.getSendStream();
                // System.err.println("My SSRC is: " + ss.getSSRC());
                zrtpEngine.setOwnSSRC(ss.getSSRC());
//                zrtpEngine.startZrtp();
            }
        }

        byte[] getMultiStreamParams() {
            return zrtpEngine.getMultiStrParams();            
        }

        void setMultiStreamParams(byte[] params) {
            multiParams = params;
            multiStream = true;
        }

        public void run() {
            init();
            System.err.println("starting send loop");
            for (int i = 0; i < 10; i++) {
                dataOutput.pushData();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
            stopIt();

        }
    }

    public void doIt() {
        senderFirst = new SenderMulti();
        senderThread = new Thread(senderFirst);

        senderSecond = new SenderMulti();
        multiSenderThread = new Thread(senderSecond);

        // start first sender
        senderThread.start();
        try {
            senderThread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            multiSenderThread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {

        TransmitterMultiZRTP trans = new TransmitterMultiZRTP();
        trans.doIt();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
        }

        System.exit(0);
    }

}
