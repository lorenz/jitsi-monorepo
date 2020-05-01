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
package org.jitsi.impl.osgi.framework;

import org.osgi.framework.*;

/**
 * @author Pawel Domas
 */
public class MockBundleActivator
    implements BundleActivator
{
    private BundleContext context;

    @Override
    public synchronized void start(BundleContext context)
        throws Exception
    {
        this.context = context;

        this.notifyAll();
    }

    @Override
    public synchronized void stop(BundleContext context)
        throws Exception
    {
        this.context = null;

        this.notifyAll();
    }

    public boolean isRunning()
    {
        return context != null;
    }

    public synchronized boolean waitToBeStarted()
    {
        if (isRunning())
        {
            return true;
        }

        try
        {
            this.wait();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        return isRunning();
    }

    public synchronized boolean waitToBeStopped()
    {
        if (!isRunning())
        {
            return true;
        }

        try
        {
            this.wait();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        return !isRunning();
    }
}
