/**
 * Copyright (C) 2006-2008 Werner Dittmann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Authors: Werner Dittmann <Werner.Dittmann@t-online.de>
 */

package gnu.java.zrtp.packets;

import djb.Curve25519;
import gnu.java.zrtp.ZrtpConstants;
import gnu.java.zrtp.utils.ZrtpUtils;


/**
 * @author Werner Dittmann <Werner.Dittmann@t-online.de>
 *
 */
public class ZrtpPacketDHPart extends ZrtpPacketBase {
    
    private int dhLength;   // length of DH data (3k or 4k)
    
    /*
     * Now the DHPart packet specific offsets into the packet buffer. They
     * all start after ZRTP_HEADER_LENGTH and a given in bytes, not ZRTP
     * words.
     */
    private static final int HASH_H1_OFFSET = ZRTP_HEADER_LENGTH * ZRTP_WORD_SIZE;       // [8*ZRTP_WORD_SIZE];
    private static final int RS1ID_OFFSET = HASH_H1_OFFSET + HASH_IMAGE_SIZE;           // [2*ZRTP_WORD_SIZE];
    private static final int RS2ID_OFFSET = RS1ID_OFFSET + 2*ZRTP_WORD_SIZE;             // [2*ZRTP_WORD_SIZE];
//    private static final int SIGS_ID_OFFSET = RS2ID_OFFSET + 2*ZRTP_WORD_SIZE;           // [2*ZRTP_WORD_SIZE];
    private static final int S3_ID_OFFSET = RS2ID_OFFSET + 2*ZRTP_WORD_SIZE;          // [2*ZRTP_WORD_SIZE];
    private static final int PBX_SECRET_ID_OFFSET = S3_ID_OFFSET + 2*ZRTP_WORD_SIZE;// [2*ZRTP_WORD_SIZE];
    private static final int PUBLIC_KEY_OFFSET = PBX_SECRET_ID_OFFSET + 2*ZRTP_WORD_SIZE;
    
    /*
     * The number of the fixed DHPacket specific ZRTP fields in words
     */
    private static final int ZRTP_DHPART_FIXED_LENGTH = 16;

    // required bytes to hold the header, the fix part and the CRC
    private static final int DHPART_FIXED_LENGTH = 
        (ZRTP_HEADER_LENGTH + ZRTP_DHPART_FIXED_LENGTH) * ZRTP_WORD_SIZE + CRC_SIZE;

    /**
     * Constructor for a new DHPart message
     * 
     */
    
    public ZrtpPacketDHPart() {
        super(null);
    }

    /**
     * Constructor for a new DHPart message with DH part type.
     * 
     * @param pkt type of DH key agreement to use
     */
    @SuppressWarnings("unused")
    public ZrtpPacketDHPart(final ZrtpConstants.SupportedPubKeys pkt) {
        super(null);
        setPubKeyType(pkt);
    }

    /**
     * Constructor for DHPart message initialized with received data.
     * 
     * @param data received from the network.
     */
    public ZrtpPacketDHPart(final byte[] data) {
        super(data);

        short len = getLength();
        if (len == 85) {
            dhLength = 256;
        }
        else if (len == 117) {
            dhLength = 384;
        }
        else if (len == 37) {
            dhLength = 64;
        }
        else if (len == 45) {
            dhLength = 96;
        }
        else if (len == 29) {    // E255
            dhLength = 32;
        }
        else {
            dhLength = 0;
        }
    }

    public void setPubKeyType(final ZrtpConstants.SupportedPubKeys pkt) {
        if (pkt == ZrtpConstants.SupportedPubKeys.DH2K) {
            dhLength = 256;
        }
        else if (pkt == ZrtpConstants.SupportedPubKeys.DH3K) {
            dhLength = 384;
        }
        else if (pkt == ZrtpConstants.SupportedPubKeys.EC25) {
            dhLength = 64;
        }
        else if (pkt == ZrtpConstants.SupportedPubKeys.EC38) {
            dhLength = 96;
        }
        else if (pkt == ZrtpConstants.SupportedPubKeys.E255) {
            dhLength = Curve25519.KEY_SIZE;
        }
        else
            return;
        // compute total length of ZRTP message including space for CRC
        int length = DHPART_FIXED_LENGTH + dhLength + (2 * ZRTP_WORD_SIZE);  // HMAC field is 2*ZRTP_WORD_SIZE
        
        // allocate buffer
        packetBuffer = new byte[length];
        
        // Message length does not include CRC
        setLength((length-CRC_SIZE) / ZRTP_WORD_SIZE);
        setZrtpId();
    }

    public final byte[] getPv() { 
        return ZrtpUtils.readRegion(packetBuffer, PUBLIC_KEY_OFFSET, dhLength);
    }

    public final byte[] getRs1Id() {
        return ZrtpUtils.readRegion(packetBuffer, RS1ID_OFFSET, 2*ZRTP_WORD_SIZE);
    }
        
    public final byte[] getRs2Id() { 
        return ZrtpUtils.readRegion(packetBuffer, RS2ID_OFFSET, 2*ZRTP_WORD_SIZE);
    }

    public final byte[] getAuxSecretId() {
        return ZrtpUtils.readRegion(packetBuffer, S3_ID_OFFSET, 2*ZRTP_WORD_SIZE);
    }

    public final byte[] getPbxSecretId() {
        return ZrtpUtils.readRegion(packetBuffer, PBX_SECRET_ID_OFFSET, 2*ZRTP_WORD_SIZE);
    }
        
    public final byte[] getH1() { 
        return ZrtpUtils.readRegion(packetBuffer, HASH_H1_OFFSET, HASH_IMAGE_SIZE);
    }

    public final byte[] getHMAC() {
        return ZrtpUtils.readRegion(packetBuffer, PUBLIC_KEY_OFFSET+dhLength, 2*ZRTP_WORD_SIZE);
    }

    /// Check if packet length makes sense. Smallest DHpart packet is 29 words, using DH E255
    public final boolean isLengthOk() {
        return getLength() >= 29;
    }

    /**
     * Setter methods.
     *
     */
    
    public final void setPv(final byte[] data) {
        System.arraycopy(data, 0, packetBuffer, PUBLIC_KEY_OFFSET, dhLength);
    }

    public final void setRs1Id(final byte[] data) {
        System.arraycopy(data, 0, packetBuffer, RS1ID_OFFSET, 2*ZRTP_WORD_SIZE);
    }
        
    public final void setRs2Id(final byte[] data) { 
        System.arraycopy(data, 0, packetBuffer, RS2ID_OFFSET, 2*ZRTP_WORD_SIZE);
    }
        
    public final void setAuxSecretId(final byte[] data) {
        System.arraycopy(data, 0, packetBuffer, S3_ID_OFFSET, 2*ZRTP_WORD_SIZE);
    }

    public final void setPbxSecretId(final byte[] data) {
        System.arraycopy(data, 0, packetBuffer, PBX_SECRET_ID_OFFSET, 2*ZRTP_WORD_SIZE);
    }
        
    public final void setH1(final byte[] data) { 
        System.arraycopy(data, 0, packetBuffer, HASH_H1_OFFSET, HASH_IMAGE_SIZE);
    }

    public final void setHMAC(final byte[] data) {
        System.arraycopy(data, 0, packetBuffer, PUBLIC_KEY_OFFSET+dhLength, 2*ZRTP_WORD_SIZE);
    }

    /* ***
    public static void main(String[] args) {
        ZrtpPacketDHPart pkt = new ZrtpPacketDHPart(ZrtpConstants.SupportedPubKeys.DH3K);
        System.err.println("DHPart length: " + pkt.getLength());
        pkt.setMessageType(ZrtpConstants.DHPart1Msg);

        System.err.println("packetBuffer length in bytes: " + pkt.getHeaderBase().length);
        ZrtpUtils.hexdump("DHPart packet", pkt.getHeaderBase(), pkt.getHeaderBase().length);
    }
    *** */
}
