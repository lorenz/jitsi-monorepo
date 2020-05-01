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

package gnu.java.zrtp;

import java.util.EnumSet;

/**
 * Application callback methods.
 *
 * The RTP specific part of GNU ZRTP uses these callback methods
 * to report ZRTP events to the application. This class implements a
 * default behaviour for each callback method, usually just a return.
 *
 * An application may extend this class and overload methods
 * to implement its own behaviour. The application must register its
 * callback class using ZrtpQueue#setUserCallback().
 *
 * <b>CAVEAT</b><br/>
 * All methods of the user callback class and classes that
 * extend this class run in the context of the RTP thread. Thus it is
 * of paramount importance to keep the execution time of the methods
 * as short as possible.
 *
 * @author Werner Dittmann <Werner.Dittmann@t-online.de>
 *
 */

public class ZrtpUserCallback {
    
    public ZrtpUserCallback() {}

    /**
     * Inform user interface that security is active now.
     *
     * ZRTP calls this method if the sender and the receiver are
     * in secure mode now.
     *
     * @param cipher
     *    Name and mode of cipher used to encrypt the SRTP stream
     */
    public void secureOn(String cipher) {
    }
    /**
     * Inform user interface that security is not active any more.
     *
     * ZRTP calls this method if either the sender or the receiver
     * left secure mode.
     *
     */
    public void secureOff() {
    }

    /**
     * Show the Short Authentication String (SAS) on user interface.
     *
     * ZRTP calls this method to display the SAS and inform about the SAS
     * verification status. The user interface shall enable a SAS verfication
     * button (or similar UI element). The user shall click on this UI
     * element after he/she confirmed the SAS code with the partner.
     *
     * @param sas
     *     The string containing the SAS.
     * @param verified
     *    If <code>verified</code> is true then SAS was verified by both
     *    parties during a previous call, otherwise it is set to false.
     */
    public void showSAS(String sas, boolean verified) {
    }

    /**
     * Inform the user that ZRTP received "go clear" message from its peer.
     *
     * On receipt of a go clear message the user is requested to confirm
     * a switch to unsecure (clear) modus. Until the user confirms ZRTP
     * (and the underlying RTP) does not send any data.
     */
    @SuppressWarnings("unused")
    public void confirmGoClear() {
    }

    /**
     * Show some information to user.
     *
     * ZRTP calls this method to display some information to the user.
     * Along with the message ZRTP provides a severity indicator that
     * defines: Info, Warning, Error, and Alert. Refer to the <code>
     * ZrtpCodes#MessageSeverity</code>. The
     * UI may use this indicator to highlight messages or alike.
     * 
     * Depending on the severity code the EnumSet subcode parameter contains
     * one enumeration of either ZrtpCodes#InfoCodes, ZrtpCodes#WarningCodes,
     * ZrtpCodes#SevereCodes, or ZrtpCodes#ZrtpErrorCodes. The 
     * ZrtpCodes#ZrtpErrorCodes enumeration has a public value field that
     * contains the ZRTP error code as defined in the ZRTP specification.
     *
     * @param sev
     *     Severity of the message.
     * @param subCode
     *     The subcode identifying the reason.
     *
     * @see gnu.java.zrtp.ZrtpCodes.MessageSeverity
     */
    public void showMessage(ZrtpCodes.MessageSeverity sev, EnumSet<?> subCode) {
    }

    /**
     * ZRTP core calls this if the negotiation failed.
     *
     * ZRTP core calls this method in case ZRTP negotiation failed. The
     * parameters show the severity as well as some explanatory text.
     * For the subcode refer to ZrtpUserCallback#showMessage() above.
     *
     * @param severity
     *     This defines the message's severity
     * @param subCode
     *     The subcode identifying the reason.
     */
    public void zrtpNegotiationFailed(ZrtpCodes.MessageSeverity severity, EnumSet<?> subCode) {
    }

    /**
     * ZRTP core calls this method if the other side does not support ZRTP.
     *
     * If the other side does not answer the ZRTP <em>Hello</em> packets then
     * ZRTP calls this method.
     *
     */
    public void zrtpNotSuppOther() {
    }

    /**
     * ZRTP core calls this method to inform about a PBX enrollment request.
     *
     * Please refer to chapter 7.3 ff to get more details about PBX enrollment
     * and SAS relay.
     *
     * @param info
     *    Give some information to the user about the PBX requesting an
     *    enrollment.
     *
     */
    public void zrtpAskEnrollment(ZrtpCodes.InfoEnrollment info) {
    }

    /**
     * ZRTP core calls this method to inform about PBX enrollment result.
     *
     * Informs the use about the acceptance or denial of a PBX enrollment
     * request
     *
     * @param info
     *    Give some information to the user about the result of an
     *    enrollment.
     *
     */
    public void zrtpInformEnrollment(ZrtpCodes.InfoEnrollment info) {
    }

    /**
     * ZRTP core calls this method to request a SAS signature.
     *
     * After ZRTP was able to compute the Short Authentication String
     * (SAS) it calls this method. The client may now use an
     * approriate method to sign the SAS hash.
     * 
     * <b>NOTE</b><br/>: 
     * The application must use the first 32 bytes of the SAS hash array
     * only, even if the array is longer (sasHash.length >32). Refer to 
     * chapter 4.5.3 of RFC 6189 (ZRTP specification).
     * 
     * The client calls <code>setSignatureData()</code> to set the resulting 
     * signature in the ZRTP protocol. Refer to chapter 7.2 of RFC 6189.
     *
     * @param sasHash
     *    The SAS hash to sign.
     *    
     * @see gnu.java.zrtp.jmf.transform.zrtp.ZRTPTransformEngine#setSignatureData
     *
     */
    public void signSAS(byte[] sasHash) {
    }

    /**
     * ZRTP core calls this method to request a SAS signature check.
     *
     * After ZRTP received a SAS signature in one of the Confirm packets it
     * call this method. The client uses <code>getSignatureData()</code>of 
     * ZrtpQueue to get the signature data and to perform the signature check. 
     * Refer to chapter 7.2 of RFC 6189 (ZRTP specification).
     *
     * <b>NOTE</b><br/>: 
     * The application must use the first 32 bytes of the SAS hash array
     * only, even if the array is longer (sasHash.length >32). Refer to 
     * chapter 4.5.3 of RFC 6189 (ZRTP specification).
     * 
     * If the signature check fails the client returns false. In
     * this case ZRTP signals an error to the other peer and terminates
     * the ZRTP handshake.
     *
     * @param sasHash
     *    The sasHash that was signed by the other peer.
     * @return
     *    true if the signature was ok, false otherwise.
     *
     * @see gnu.java.zrtp.jmf.transform.zrtp.ZRTPTransformEngine#getSignatureData
     */
    public boolean checkSASSignature(byte[] sasHash) {
        return true;
    }
    
//    public static void main(String argv[]) {
//        EnumSet<?> s = EnumSet.of(ZrtpCodes.SevereCodes.SevereCommitHMACFailed);
//        System.err.println("Enum set: " + s);
//        Iterator<ZrtpCodes.SevereCodes> ii = (Iterator<ZrtpCodes.SevereCodes>)s.iterator();
//        ZrtpCodes.SevereCodes scc = ((Iterator<ZrtpCodes.SevereCodes>)s.iterator()).next();
//        System.err.println("Via generic iterator: " + ii.next() + ", " + scc);
//
//        Iterator<ZrtpCodes.SevereCodes> i = s.iterator();
//        ZrtpCodes.SevereCodes sc = i.next();
//        System.err.println("SeverCodes: " + sc);
//    }

}
