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
package org.ice4j.attribute;

/**
 * The REFLECTED-FROM attribute is present only in Binding Responses,
 * when the Binding Request contained a RESPONSE-ADDRESS attribute.  The
 * attribute contains the identity (in terms of IP address) of the
 * source where the request came from.  Its purpose is to provide
 * traceability, so that a STUN server cannot be used as a reflector for
 * denial-of-service attacks.
 *
 * Its syntax is identical to the MAPPED-ADDRESS attribute.
 *
 * @author Emil Ivov
 */
public class ReflectedFromAttribute extends AddressAttribute
{
    /**
     * Attribute name.
     */
    public static final String NAME = "REFLECTED-FROM";

    /**
     * Creates a REFLECTED-FROM attribute
     */
    public ReflectedFromAttribute()
    {
        super(REFLECTED_FROM);
    }
}
