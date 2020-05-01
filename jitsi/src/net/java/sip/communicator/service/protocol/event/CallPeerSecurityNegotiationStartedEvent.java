/*
 * Jitsi, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Copyright @ 2015 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.java.sip.communicator.service.protocol.event;

import net.java.sip.communicator.service.protocol.*;

import org.jitsi.service.neomedia.*;

/**
 * The <tt>CallPeerSecurityNegotiationStartedEvent</tt> is triggered whenever a
 * communication with a given peer is established,
 * we started securing the connection.
 *
 * @author Damian Minkov
 */
public class CallPeerSecurityNegotiationStartedEvent
    extends CallPeerSecurityStatusEvent
{
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 0L;

    /**
     * The sender of this event.
     */
    private final SrtpControl srtpControl;

    /**
     * The event constructor
     *
     * @param callPeer the call peer associated with this event
     * @param sessionType the type of the session, either
     *            {@link net.java.sip.communicator.service.protocol.event.CallPeerSecurityStatusEvent#AUDIO_SESSION} or
     *            {@link net.java.sip.communicator.service.protocol.event.CallPeerSecurityStatusEvent#VIDEO_SESSION}
     * @param srtpControl the security controller that caused this event
     */
    public CallPeerSecurityNegotiationStartedEvent(CallPeer callPeer,
                                                   int sessionType,
                                                   SrtpControl srtpControl)
    {
        super(callPeer, sessionType);
        this.srtpControl = srtpControl;
    }

    /**
     * Gets the security controller that caused this event.
     *
     * @return the security controller that caused this event.
     */
    public SrtpControl getSecurityController()
    {
        return srtpControl;
    }
}
