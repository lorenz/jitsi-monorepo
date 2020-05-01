package org.jitsi.bccontrib.tests;

import java.util.Arrays;

import org.jitsi.bccontrib.engines.ThreefishCipher;
import org.bouncycastle.crypto.params.*;
import org.jitsi.bccontrib.params.ParametersForThreefish;
import org.jitsi.bccontrib.util.ByteLong;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ThreefishTest {

    long[] three_256_00_key = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L };

    long[] three_256_00_input = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L };

    long[] three_256_00_tweak = { 0L, 0L };

    long[] three_256_00_result = { 0x94EEEA8B1F2ADA84L, 0xADF103313EAE6670L,
            0x952419A1F4B16D53L, 0xD83F13E63C9F6B11L };

    long[] three_256_01_key = { 0x1716151413121110L, 0x1F1E1D1C1B1A1918L,
            0x2726252423222120L, 0x2F2E2D2C2B2A2928L };

    long[] three_256_01_input = { 0xF8F9FAFBFCFDFEFFL, 0xF0F1F2F3F4F5F6F7L,
            0xE8E9EAEBECEDEEEFL, 0xE0E1E2E3E4E5E6E7L };

    long[] three_256_01_result = { 0x277610F5036C2E1FL, 0x25FB2ADD1267773EL,
            0x9E1D67B3E4B06872L, 0x3F76BC7651B39682L };

    long[] three_256_01_tweak = { 0x0706050403020100L, 0x0F0E0D0C0B0A0908L };

    long[] three_512_00_key = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
            0L, 0L, 0L, 0L };

    long[] three_512_00_input = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
            0L, 0L, 0L, 0L, 0L };

    long[] three_512_00_tweak = { 0L, 0L };

    long[] three_512_00_result = { 0xBC2560EFC6BBA2B1L, 0xE3361F162238EB40L,
            0xFB8631EE0ABBD175L, 0x7B9479D4C5479ED1L, 0xCFF0356E58F8C27BL,
            0xB1B7B08430F0E7F7L, 0xE9A380A56139ABF1L, 0xBE7B6D4AA11EB47EL };

    long[] three_512_01_key = { 0x1716151413121110L, 0x1F1E1D1C1B1A1918L,
            0x2726252423222120L, 0x2F2E2D2C2B2A2928L, 0x3736353433323130L,
            0x3F3E3D3C3B3A3938L, 0x4746454443424140L, 0x4F4E4D4C4B4A4948L };

    long[] three_512_01_input = { 0xF8F9FAFBFCFDFEFFL, 0xF0F1F2F3F4F5F6F7L,
            0xE8E9EAEBECEDEEEFL, 0xE0E1E2E3E4E5E6E7L, 0xD8D9DADBDCDDDEDFL,
            0xD0D1D2D3D4D5D6D7L, 0xC8C9CACBCCCDCECFL, 0xC0C1C2C3C4C5C6C7L };

    long[] three_512_01_tweak = { 0x0706050403020100L, 0x0F0E0D0C0B0A0908L };

    long[] three_512_01_result = { 0xD4A32EDD6ABEFA1CL, 0x6AD5C4252C3FF743L,
            0x35AC875BE2DED68CL, 0x99A6C774EA5CD06CL, 0xDCEC9C4251D7F4F8L,
            0xF5761BCB3EF592AFL, 0xFCABCB6A3212DF60L, 0xFD6EDE9FF9A2E14EL };

    long[] three_1024_00_key = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
            0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
            0L, 0L, 0L, 0L };

    long[] three_1024_00_input = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
            0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
            0L, 0L, 0L, 0L };

    long[] three_1024_00_tweak = { 0L, 0L };

    long[] three_1024_00_result = { 0x04B3053D0A3D5CF0L, 0x0136E0D1C7DD85F7L,
            0x067B212F6EA78A5CL, 0x0DA9C10B4C54E1C6L, 0x0F4EC27394CBACF0L,
            0x32437F0568EA4FD5L, 0xCFF56D1D7654B49CL, 0xA2D5FB14369B2E7BL,
            0x540306B460472E0BL, 0x71C18254BCEA820DL, 0xC36B4068BEAF32C8L,
            0xFA4329597A360095L, 0xC4A36C28434A5B9AL, 0xD54331444B1046CFL,
            0xDF11834830B2A460L, 0x1E39E8DFE1F7EE4FL };

    long[] three_1024_01_key = { 0x1716151413121110L, 0x1F1E1D1C1B1A1918L,
            0x2726252423222120L, 0x2F2E2D2C2B2A2928L, 0x3736353433323130L,
            0x3F3E3D3C3B3A3938L, 0x4746454443424140L, 0x4F4E4D4C4B4A4948L,
            0x5756555453525150L, 0x5F5E5D5C5B5A5958L, 0x6766656463626160L,
            0x6F6E6D6C6B6A6968L, 0x7776757473727170L, 0x7F7E7D7C7B7A7978L,
            0x8786858483828180L, 0x8F8E8D8C8B8A8988L };

    long[] three_1024_01_input = { 0xF8F9FAFBFCFDFEFFL, 0xF0F1F2F3F4F5F6F7L,
            0xE8E9EAEBECEDEEEFL, 0xE0E1E2E3E4E5E6E7L, 0xD8D9DADBDCDDDEDFL,
            0xD0D1D2D3D4D5D6D7L, 0xC8C9CACBCCCDCECFL, 0xC0C1C2C3C4C5C6C7L,
            0xB8B9BABBBCBDBEBFL, 0xB0B1B2B3B4B5B6B7L, 0xA8A9AAABACADAEAFL,
            0xA0A1A2A3A4A5A6A7L, 0x98999A9B9C9D9E9FL, 0x9091929394959697L,
            0x88898A8B8C8D8E8FL, 0x8081828384858687L };

    long[] three_1024_01_tweak = { 0x0706050403020100L, 0x0F0E0D0C0B0A0908L };

    long[] three_1024_01_result = { 0x483AC62C27B09B59L, 0x4CB85AA9E48221AAL,
            0x80BC1644069F7D0BL, 0xFCB26748FF92B235L, 0xE83D70243B5D294BL,
            0x316A3CA3587A0E02L, 0x5461FD7C8EF6C1B9L, 0x7DD5C1A4C98CA574L,
            0xFDA694875AA31A35L, 0x03D1319C26C2624CL, 0xA2066D0DF2BF7827L,
            0x6831CCDAA5C8A370L, 0x2B8FCD9189698DACL, 0xE47818BBFD604399L,
            0xDF47E519CBCEA541L, 0x5EFD5FF4A5D4C259L };

    public ThreefishTest() {
    }

    byte[] key;

    byte[] dataIn;

    byte[] dataOut;

    byte[] result;

    boolean basicTest256() {

        ThreefishCipher tfc = new ThreefishCipher();
        int stateSize = 256;

        // Key must match Threefish state size
        key = new byte[stateSize / 8];

        // For simple ECB mode length matches state size
        dataIn = new byte[stateSize / 8];
        dataOut = new byte[stateSize / 8];
        result = new byte[stateSize / 8];

        // Prepare first vector
        ByteLong.PutBytes(three_256_00_input, dataIn, 0, dataIn.length);
        ByteLong.PutBytes(three_256_00_key, key, 0, key.length);
        ByteLong.PutBytes(three_256_00_result, result, 0, result.length);

        ParametersForThreefish pft = new ParametersForThreefish(
                new KeyParameter(key), stateSize, three_256_00_tweak);
        
        // Encrypt and check
        tfc.init(true, pft);
        tfc.processBlock(dataIn, 0, dataOut, 0);

        if (!Arrays.equals(result, dataOut)) {
            hexdump("Wrong cipher text 256 00", dataOut, dataOut.length);
            return false;
        }
        // Decrypt and check
        tfc.init(false, pft);
        tfc.processBlock(dataOut, 0, result, 0);
        if (!Arrays.equals(dataIn, result)) {
            hexdump("Decrypt failed 256 00", result, result.length);
            return false;
        }
        // Next Vector
        ByteLong.PutBytes(three_256_01_input, dataIn, 0, dataIn.length);
        ByteLong.PutBytes(three_256_01_key, key, 0, key.length);
        ByteLong.PutBytes(three_256_01_result, result, 0, result.length);

        pft = new ParametersForThreefish(new KeyParameter(key), stateSize,
                three_256_01_tweak);

        // Encrypt and check
        tfc.init(true, pft);
        tfc.processBlock(dataIn, 0, dataOut, 0);

        // plaintext feed forward
        for (int i = 0; i < dataIn.length; i++)
            dataOut[i] ^= dataIn[i];

        if (!Arrays.equals(result, dataOut)) {
            hexdump("Wrong cipher text 256 01", dataOut, dataOut.length);
            return false;
        }
        // Decrypt and check
        // plaintext feed backward :-)
        for (int i = 0; i < dataIn.length; i++)
            dataOut[i] ^= dataIn[i];

        tfc.init(false, pft);
        tfc.processBlock(dataOut, 0, result, 0);
        if (!Arrays.equals(dataIn, result)) {
            hexdump("Decrypt failed 256 01", result, result.length);
            return false;
        }
        return true;
    }

    boolean basicTest512() {

        ThreefishCipher tfc = new ThreefishCipher();
        int stateSize = 512;

        // Key must match Threefish state size
        key = new byte[stateSize / 8];

        // For simple ECB mode length matches state size
        dataIn = new byte[stateSize / 8];
        dataOut = new byte[stateSize / 8];
        result = new byte[stateSize / 8];

        // Prepare first vector
        ByteLong.PutBytes(three_512_00_input, dataIn, 0, dataIn.length);
        ByteLong.PutBytes(three_512_00_key, key, 0, key.length);
        ByteLong.PutBytes(three_512_00_result, result, 0, result.length);

        ParametersForThreefish pft = new ParametersForThreefish(
                new KeyParameter(key), stateSize, three_512_00_tweak);

        // Encrypt and check
        tfc.init(true, pft);
        tfc.processBlock(dataIn, 0, dataOut, 0);

        if (!Arrays.equals(result, dataOut)) {
            hexdump("Wrong cipher text 512 00", dataOut, dataOut.length);
            return false;
        }
        // Decrypt and check
        tfc.init(false, pft);
        tfc.processBlock(dataOut, 0, result, 0);
        if (!Arrays.equals(dataIn, result)) {
            hexdump("Decrypt failed 512 00", result, result.length);
            return false;
        }
        // Next vector
        ByteLong.PutBytes(three_512_01_input, dataIn, 0, dataIn.length);
        ByteLong.PutBytes(three_512_01_key, key, 0, key.length);
        ByteLong.PutBytes(three_512_01_result, result, 0, result.length);

        pft = new ParametersForThreefish(new KeyParameter(key), stateSize,
                three_512_01_tweak);
        tfc.init(true, pft);
        tfc.processBlock(dataIn, 0, dataOut, 0);

        // plaintext feed forward
        for (int i = 0; i < dataIn.length; i++)
            dataOut[i] ^= dataIn[i];

        if (!Arrays.equals(result, dataOut)) {
            hexdump("Wrong cipher text 512 01", dataOut, dataOut.length);
            return false;
        }
        // Decrypt and check
        // plaintext feed backward :-)
        for (int i = 0; i < dataIn.length; i++)
            dataOut[i] ^= dataIn[i];

        tfc.init(false, pft);
        tfc.processBlock(dataOut, 0, result, 0);
        if (!Arrays.equals(dataIn, result)) {
            hexdump("Decrypt failed 512 01", result, result.length);
            return false;
        }
        return true;
    }

    boolean basicTest1024() {

        ThreefishCipher tfc = new ThreefishCipher();
        int stateSize = 1024;

        // Key must match Threefish state size
        key = new byte[stateSize / 8];

        // For simple ECB mode length matches state size
        dataIn = new byte[stateSize / 8];
        dataOut = new byte[stateSize / 8];
        result = new byte[stateSize / 8];

        // Prepare first vector
        ByteLong.PutBytes(three_1024_00_input, dataIn, 0, dataIn.length);
        ByteLong.PutBytes(three_1024_00_key, key, 0, key.length);
        ByteLong.PutBytes(three_1024_00_result, result, 0, result.length);

        ParametersForThreefish pft = new ParametersForThreefish(
                new KeyParameter(key), stateSize, three_1024_00_tweak);

        // Encrypt and check
        tfc.init(true, pft);
        tfc.processBlock(dataIn, 0, dataOut, 0);

        if (!Arrays.equals(result, dataOut)) {
            hexdump("Wrong cipher text 1024 00", dataOut, dataOut.length);
            return false;
        }
        // Decrypt and check
        tfc.init(false, pft);
        tfc.processBlock(dataOut, 0, result, 0);
        if (!Arrays.equals(dataIn, result)) {
            hexdump("Decrypt failed 1024 00", result, result.length);
            return false;
        }
        // Next vector
        ByteLong.PutBytes(three_1024_01_input, dataIn, 0, dataIn.length);
        ByteLong.PutBytes(three_1024_01_key, key, 0, key.length);
        ByteLong.PutBytes(three_1024_01_result, result, 0, result.length);

        pft = new ParametersForThreefish(new KeyParameter(key), stateSize,
                three_1024_01_tweak);
        tfc.init(true, pft);
        tfc.processBlock(dataIn, 0, dataOut, 0);

        // plaintext feed forward
        for (int i = 0; i < dataIn.length; i++)
            dataOut[i] ^= dataIn[i];

        if (!Arrays.equals(result, dataOut)) {
            hexdump("Wrong cipher text 1024 01", dataOut, dataOut.length);
            return false;
        }
        // Decrypt and check
        // plaintext feed backward :-)
        for (int i = 0; i < dataIn.length; i++)
            dataOut[i] ^= dataIn[i];

        tfc.init(false, pft);
        tfc.processBlock(dataOut, 0, result, 0);
        if (!Arrays.equals(dataIn, result)) {
            hexdump("Decrypt failed 1024 01", result, result.length);
            return false;
        }
        return true;
    }

    @Test public void vectorTest() {
        try {
            assertTrue(basicTest256());
            assertTrue(basicTest512());
            assertTrue(basicTest1024());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final char[] hex = "0123456789abcdef".toCharArray();

    /**
     * Dump a buffer in hex and readable format.
     * 
     * @param title
     *            Printed at the beginning of the dump
     * @param buf
     *            Byte buffer to dump
     * @param len
     *            Number of bytes to dump, should be less or equal the buffer
     *            length
     */
    public static void hexdump(String title, byte[] buf, int len) {
        byte b;
        System.err.println(title);
        for (int i = 0;; i += 16) {
            for (int j = 0; j < 16; ++j) {
                if (i + j >= len) {
                    System.err.print("   ");
                }
                else {
                    b = buf[i + j];
                    System.err.print(" " + hex[(b >>> 4) & 0xf] + hex[b & 0xf]);
                }
            }
            System.err.print("  ");
            for (int j = 0; j < 16; ++j) {
                if (i + j >= len)
                    break;
                b = buf[i + j];
                if ((byte) (b + 1) < 32 + 1) {
                    System.err.print('.');
                }
                else {
                    System.err.print((char) b);
                }
            }
            System.err.println();
            if (i + 16 >= len) {
                break;
            }
        }
    }
}
