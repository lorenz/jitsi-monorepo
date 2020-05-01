/*
 * ice4j, the OpenSource Java Solution for NAT and Firewall Traversal.
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
package org.ice4j;

import java.util.*;

import org.ice4j.stack.*;

/**
 * The class is used to dispatch incoming UDP peer messages. Apart from the
 * message itself one could also obtain the address from where the message is
 * coming (used by a server implementation to determine the mapped address) as
 * well as the Descriptor of the NetAccessPoint that received it (In case the
 * stack is used on more than one ports/addresses).
 * 
 * @author Aakash Garg
 */
public class PeerUdpMessageEvent
    extends EventObject
{
    /**
     * A dummy version UID to suppress warnings.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The <tt>StunStack</tt> associated with this instance.
     */
    private final StunStack stunStack;

    /**
     * The Peer UDP <tt>Message</tt> associated with this event. Stored as a
     * RawMessage.
     */
    private final RawMessage udpMessage;

    /**
     * Initializes a new <tt>PeerUdpMessageEvent</tt> associated with a
     * specific PeerUdp <tt>Message</tt>.
     * 
     * @param stunStack the <tt>StunStack</tt> to be associated with the new
     *            instance.
     * @param udpMessage the Peer UDP <tt>Message</tt> associated
     *            with the new event.
     */
    public PeerUdpMessageEvent(StunStack stunStack, RawMessage udpMessage)
    {
        super(udpMessage.getRemoteAddress());
        
        this.stunStack = stunStack;
        this.udpMessage = udpMessage;
    }

    /**
     * Returns the message itself.
     * 
     * @return a binary array containing the message data.
     */
    public byte[] getBytes()
    {
        return this.udpMessage.getBytes();
    }

    /**
     * Returns the message length.
     * 
     * @return a the length of the message.
     */
    public int getMessageLength()
    {
        return this.udpMessage.getMessageLength();
    }

    /**
     * Returns the address and port of the host that sent the message
     * 
     * @return the [address]:[port] pair that sent the message.
     */
    public TransportAddress getRemoteAddress()
    {
        return this.udpMessage.getRemoteAddress();
    }

    /**
     * Returns the address that this message was received on.
     * 
     * @return the address that this message was received on.
     */
    public TransportAddress getLocalAddress()
    {
        return this.udpMessage.getLocalAddress();
    }
    
    /**
     * Gets the <tt>StunStack</tt> associated with this instance.
     * 
     * @return the <tt>StunStack</tt> associated with this instance
     */
    public StunStack getStunStack()
    {
        return stunStack;
    }

}
