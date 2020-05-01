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
package org.ice4j.ice;

import java.net.*;
import java.util.*;

import org.ice4j.*;
import org.ice4j.socket.*;
import org.ice4j.stack.*;

/**
 * Extends {@link org.ice4j.ice.HostCandidate} allowing the instance to have
 * a list of <tt>Socket</tt>s instead of just one socket. This is needed,
 * because with TCP, connections from different remote addresses result in
 * different <tt>Socket</tt> instances.
 *
 * @author Boris Grozev
 */
public class TcpHostCandidate
    extends HostCandidate
{
    /**
     * List of <tt>accept</tt>ed sockets for this <tt>TcpHostCandidate</tt>.
     */
    private final List<IceSocketWrapper> sockets = new LinkedList<>();

    /**
     * Initializes a new <tt>TcpHostCandidate</tt>.
     *
     * @param transportAddress the transport address of this
     * <tt>TcpHostCandidate</tt>.
     * @param parentComponent the <tt>Component</tt> that this candidate
     * belongs to.
     */
    public TcpHostCandidate(TransportAddress transportAddress,
                            Component parentComponent)
    {
        super(transportAddress, parentComponent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IceSocketWrapper getCandidateIceSocketWrapper(
            SocketAddress remoteAddress)
    {
        for (IceSocketWrapper socket : sockets)
        {
            if (socket.getTCPSocket().getRemoteSocketAddress()
                .equals(remoteAddress))
                return socket;
        }

        return null;
    }

    public void addSocket(IceSocketWrapper socket)
    {
        sockets.add(socket);
    }

    @Override
    protected void free()
    {
        StunStack stunStack = getStunStack();
        TransportAddress localAddr = getTransportAddress();

        for (IceSocketWrapper socket : sockets)
        {
            //remove our sockets from the stack.
            Socket tcpSocket = socket.getTCPSocket();

            stunStack.removeSocket(
                    localAddr,
                    new TransportAddress(
                            tcpSocket.getInetAddress(),
                            tcpSocket.getPort(),
                            Transport.TCP));

            socket.close();
        }

        super.free();
    }

}
