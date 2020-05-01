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
package org.ice4j.ice.harvest;

import org.ice4j.*;
import org.ice4j.ice.*;

/**
 * Implements a <tt>CandidateHarvester</tt> which gathers Google TURN dialect
 * <tt>Candidate</tt>s for a specified {@link Component}.
 *
 * @author Sebastien Vincent
 */
public class GoogleTurnCandidateHarvester
    extends StunCandidateHarvester
{
    /**
     * The gingle candidates password necessary to use the TURN server.
     */
    private String password = null;

    /**
     * Initializes a new <tt>GoogleTurnCandidateHarvester</tt> instance which
     * is to work with a specific Google TURN server.
     *
     * @param turnServer the <tt>TransportAddress</tt> of the TURN server the
     * new instance is to work with
     */
    public GoogleTurnCandidateHarvester(TransportAddress turnServer)
    {
        this(turnServer, null, null);
    }

    /**
     * Initializes a new <tt>GoogleTurnCandidateHarvester</tt> instance which is
     * to work with a specific TURN server using a specific username for the
     * purposes of the STUN short-term credential mechanism.
     *
     * @param turnServer the <tt>TransportAddress</tt> of the TURN server the
     * new instance is to work with
     * @param shortTermCredentialUsername the username to be used by the new
     * instance for the purposes of the STUN short-term credential mechanism or
     * <tt>null</tt> if the use of the STUN short-term credential mechanism is
     * not determined at the time of the construction of the new instance
     * @param password The gingle candidates password necessary to use this TURN
     * server.
     */
    public GoogleTurnCandidateHarvester(TransportAddress turnServer,
            String shortTermCredentialUsername,
            String password)
    {
        super(turnServer, shortTermCredentialUsername);
        this.password = password;
    }

    /**
     * Creates a new <tt>GoogleTurnCandidateHarvest</tt> instance which is to
     * perform TURN harvesting of a specific <tt>HostCandidate</tt>.
     *
     * @param hostCandidate the <tt>HostCandidate</tt> for which harvesting is
     * to be performed by the new <tt>TurnCandidateHarvest</tt> instance
     * @return a new <tt>GoogleTurnCandidateHarvest</tt> instance which is to
     * perform TURN harvesting of the specified <tt>hostCandidate</tt>
     * @see StunCandidateHarvester#createHarvest(HostCandidate)
     */
    @Override
    protected GoogleTurnCandidateHarvest createHarvest(
            HostCandidate hostCandidate)
    {
        return
            new GoogleTurnCandidateHarvest(this, hostCandidate, getPassword());
    }

    /**
     * Returns the password for this candidate.
     * @return the password for this candidate.
     */
    public String getPassword()
    {
        return this.password;
    }
}
