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
package net.java.sip.communicator.plugin.ircaccregwizz;

import net.java.sip.communicator.service.resources.*;

import org.jitsi.service.resources.*;

/**
 * The Messages class manages the access to the internationalization
 * properties files.
 *
 * @author Lionel Ferreira & Michael Tarantino
 */
public class Resources
{
    private static ResourceManagementService resourcesService;

    /**
     * A constant pointing to the IRC protocol logo image.
     */
    public static ImageID IRC_LOGO
        = new ImageID("service.protocol.irc.IRC_16x16");

    /**
     * A constant pointing to the IRC protocol wizard page image.
     */
    public static ImageID PAGE_IMAGE
        = new ImageID("service.protocol.irc.IRC_64x64");

    /**
     * Returns an internationalized string corresponding to the given key.
     * @param key The key of the string.
     * @return An internationalized string corresponding to the given key.
     */
    public static String getString(String key)
    {
        return getResources().getI18NString(key);
    }

    /**
     * Loads an image from a given image identifier.
     * @param imageID The identifier of the image.
     * @return The image for the given identifier.
     */
    public static byte[] getImage(ImageID imageID)
    {
        return getResources().getImageInBytes(imageID.getId());
    }

    /**
     * Returns the <tt>ResourceManagementService</tt>.
     *
     * @return the <tt>ResourceManagementService</tt>.
     */
    public static ResourceManagementService getResources()
    {
        if (resourcesService == null)
            resourcesService =
                ResourceManagementServiceUtils
                    .getService(IrcAccRegWizzActivator.bundleContext);
        return resourcesService;
    }
}
