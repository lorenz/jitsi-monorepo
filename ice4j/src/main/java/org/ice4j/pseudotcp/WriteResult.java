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
package org.ice4j.pseudotcp;

/**
 * The result of write packet operations
 * @author Pawel Domas
 */
public enum WriteResult
{
    /**
     * Packet successfully transmitted
     */
    WR_SUCCESS, 
    /**
     * Packet was too large
     */
    WR_TOO_LARGE, 
    /**
     * Write failed
     */
    WR_FAIL
}
