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
package net.java.sip.communicator.impl.gui.main;

/**
 * The <tt>UINotificationListener</tt> listens for new notifications received
 * in the user interface. Notifications could be for example missed calls,
 * voicemails or email messages.
 *
 * @author Yana Stamcheva
 */
public interface UINotificationListener
{
    /**
     * Indicates that a new notification is received.
     *
     * @param notification the notification that was received
     */
    public void notificationReceived(UINotification notification);

    /**
     * Indicates that a notification has been cleared.
     * 
     * @param notification the notification that was cleared.
     */
    public void notificationCleared(UINotification notification);
}
