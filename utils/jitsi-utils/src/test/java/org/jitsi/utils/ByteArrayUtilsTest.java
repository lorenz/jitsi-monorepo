/*
 * Copyright @ 2019 - present 8x8, Inc
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
package org.jitsi.utils;

import org.junit.*;

import static org.junit.Assert.*;
import static org.jitsi.utils.ByteArrayUtils.*;

public class ByteArrayUtilsTest
{
    @Test
    public void testReadWriteShort()
    {
        ByteArrayBuffer bab = new BasicByteArrayBuffer(10);
        int offset = 3;

        writeShort(bab, offset, (short) 123);
        assertEquals("Write/read a positive short", 123, readShort(bab, offset));

        writeShort(bab, offset, (short) -123);
        assertEquals("Write/read a negative short", -123, readShort(bab, offset));
    }

    @Test
    public void testReadWriteInt()
    {
        ByteArrayBuffer bab = new BasicByteArrayBuffer(10);
        int offset = 3;

        writeInt(bab, offset, 1234);
        assertEquals("Write/read a positive int", 1234, readInt(bab, offset));

        writeInt(bab, offset, -1234);
        assertEquals("Write/read a negative int", -1234, readInt(bab, offset));
    }

    @Test
    public void testReadWriteUint16()
    {
        ByteArrayBuffer bab = new BasicByteArrayBuffer(10);
        int offset = 3;

        writeUint16(bab, offset, 1234);
        assertEquals(
                "Write/read a 16-bit int", 1234, readUint16(bab, offset));
    }

    @Test
    public void testReadWriteUint24()
    {
        ByteArrayBuffer bab = new BasicByteArrayBuffer(10);
        int offset = 3;

        writeUint24(bab, offset, 1234);
        assertEquals(
                "Write/read a 24-bit int", 1234, readUint24(bab, offset));
    }

    @Test
    public void testReadUint32()
    {
        ByteArrayBuffer bab = new BasicByteArrayBuffer(10);
        int offset = 3;

        long l = 0xff11_1111L;
        writeInt(bab, offset, (int) l);
        assertEquals(
            "Read a 32-bit unsigned int (msb=1)", l, readUint32(bab, offset));

        l = 0x0f11_1111L;
        writeInt(bab, offset, (int) l);
        assertEquals(
            "Read a 32-bit unsigned int (msb=0)", l, readUint32(bab, offset));
    }
}
