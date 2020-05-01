/**
 * Copyright (C) 2006-2009 Werner Dittmann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Authors: Werner Dittmann <Werner.Dittmann@t-online.de>
 */

package gnu.java.zrtp;

import gnu.java.bigintcrypto.BigIntegerCrypto;
import gnu.java.zrtp.utils.ZrtpFortuna;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.engines.TwofishEngine;
import org.bouncycastle.crypto.modes.CFBBlockCipher;

import org.bouncycastle.cryptozrtp.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.cryptozrtp.BasicAgreement;
import org.bouncycastle.cryptozrtp.agreement.Djb25519DHBasicAgreement;
import org.bouncycastle.cryptozrtp.agreement.ECDHBasicAgreement;
import org.bouncycastle.cryptozrtp.generators.Djb25519KeyPairGenerator;
import org.bouncycastle.cryptozrtp.params.*;
import org.bouncycastle.cryptozrtp.agreement.DHBasicAgreement;
import org.bouncycastle.cryptozrtp.generators.DHBasicKeyPairGenerator;
import org.bouncycastle.cryptozrtp.generators.ECKeyPairGenerator;
import org.bouncycastle.mathzrtp.ec.ECCurve;

/**
 * @author Werner Dittmann <Werner.Dittmann@t-online.de>
 *
 */
public class ZrtpConstants {
    
    // Number of bytes of a SHA256 hash
    public static final int SHA256_DIGEST_LENGTH = 32;
    public static final int MAX_DIGEST_LENGTH = 64;

    // The following string must contain ASCII characters only
    //                                               1
    //                                     0123456789012345
    public static final String clientId = "GNU ZRTP4J 4.1.0"  ;
 
//  "1.10"
    public static final byte[] zrtpVersion_11 = {
        (byte)0x31, (byte)0x2e, (byte)0x31, (byte)0x30};

//  "1.20"
    public static final byte[] zrtpVersion_12 = {
        (byte)0x31, (byte)0x2e, (byte)0x32, (byte)0x30};

    // Human readable names of encryption algorithms
    public static final String AES_128 = "AES-CM-128";
    public static final String AES_256 = "AES-CM-256";
    public static final String TWO_128 = "TWO-CM-128";
    public static final String TWO_256 = "TWO-CM-256";

    /**
    * The message names as defined in ZRTP specification.
    */
//  "Hello   "
    public static final byte[] HelloMsg = {
        (byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f, (byte)0x20, (byte)0x20, (byte)0x20};

//  "HelloACK"
    public static final byte[] HelloAckMsg = {
        (byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f, (byte)0x41, (byte)0x43, (byte)0x4b};

//  "Commit  "
    public static final byte[] CommitMsg = {
        (byte)0x43, (byte)0x6f, (byte)0x6d, (byte)0x6d, (byte)0x69, (byte)0x74, (byte)0x20, (byte)0x20};

//  "DHPart1 "
    public static final byte[] DHPart1Msg = {
        (byte)0x44, (byte)0x48, (byte)0x50, (byte)0x61, (byte)0x72, (byte)0x74, (byte)0x31, (byte)0x20};

//  "DHPart2 "
    public static final byte[] DHPart2Msg = {
        (byte)0x44, (byte)0x48, (byte)0x50, (byte)0x61, (byte)0x72, (byte)0x74, (byte)0x32, (byte)0x20};

//  "Confirm1"
    public static final byte[] Confirm1Msg = {
        (byte)0x43, (byte)0x6f, (byte)0x6e, (byte)0x66, (byte)0x69, (byte)0x72, (byte)0x6d, (byte)0x31};

//  "Confirm2"
    public static final byte[] Confirm2Msg = {
        (byte)0x43, (byte)0x6f, (byte)0x6e, (byte)0x66, (byte)0x69, (byte)0x72, (byte)0x6d, (byte)0x32};

//  "Conf2ACK"
    public static final byte[] Conf2AckMsg = {
        (byte)0x43, (byte)0x6f, (byte)0x6e, (byte)0x66, (byte)0x32, (byte)0x41, (byte)0x43, (byte)0x4b};

//  "Error   "
    public static final byte[] ErrorMsg = {
        (byte)0x45, (byte)0x72, (byte)0x72, (byte)0x6f, (byte)0x72, (byte)0x20, (byte)0x20, (byte)0x20};

//  "ErrorACK"
    public static final byte[] ErrorAckMsg = {
        (byte)0x45, (byte)0x72, (byte)0x72, (byte)0x6f, (byte)0x72, (byte)0x41, (byte)0x43, (byte)0x4b};

//  "Ping    "
    public static final byte[] PingMsg = {
        (byte)0x50, (byte)0x69, (byte)0x6e, (byte)0x67, (byte)0x20, (byte)0x20, (byte)0x20, (byte)0x20};

//  "PingACK "
    public static final byte[] PingAckMsg = {
        (byte)0x50, (byte)0x69, (byte)0x6e, (byte)0x67, (byte)0x41, (byte)0x43, (byte)0x4b, (byte)0x20};

//  "GoClear "
//    public static final byte[] GoClearMsg = {
//        (byte)0x47, (byte)0x6f, (byte)0x43, (byte)0x6c, (byte)0x65, (byte)0x61, (byte)0x72, (byte)0x20};

//  "ClearACK"
//    public static final byte[] ClearAckMsg = {
//        (byte)0x43, (byte)0x6c, (byte)0x65, (byte)0x61, (byte)0x72, (byte)0x41, (byte)0x43, (byte)0x4b};

//  "SASrelay"
    public static final byte[] SASRelayMsg = {
        (byte)0x53, (byte)0x41, (byte)0x53, (byte)0x72, (byte)0x65, (byte)0x6c, (byte)0x61, (byte)0x79};

//  "RelayACK"
    public static final byte[] RelayAckMsg = {
        (byte)0x52, (byte)0x65, (byte)0x6c, (byte)0x61, (byte)0x79, (byte)0x41, (byte)0x43, (byte)0x4b};

    /**
     * Various strings used to build the keys, hashes and HMACs
     */
//  "Responder"
    public static final byte[] responder = {
        (byte)0x52, (byte)0x65, (byte)0x73, (byte)0x70, (byte)0x6f, (byte)0x6e, (byte)0x64, (byte)0x65,
        (byte)0x72};
    
//  "Initiator"
    public static final byte[] initiator = {
        (byte)0x49, (byte)0x6e, (byte)0x69, (byte)0x74, (byte)0x69, (byte)0x61, (byte)0x74, (byte)0x6f,
        (byte)0x72};

//  "Initiator SRTP master key"
    public static final byte[] iniMasterKey = {
        (byte)0x49, (byte)0x6e, (byte)0x69, (byte)0x74, (byte)0x69, (byte)0x61, (byte)0x74, (byte)0x6f,
        (byte)0x72, (byte)0x20, (byte)0x53, (byte)0x52, (byte)0x54, (byte)0x50, (byte)0x20, (byte)0x6d,
        (byte)0x61, (byte)0x73, (byte)0x74, (byte)0x65, (byte)0x72, (byte)0x20, (byte)0x6b, (byte)0x65,
        (byte)0x79, (byte)0x0};

//  "Initiator SRTP master salt"
    public static final byte[] iniMasterSalt = {
        (byte)0x49, (byte)0x6e, (byte)0x69, (byte)0x74, (byte)0x69, (byte)0x61, (byte)0x74, (byte)0x6f,
        (byte)0x72, (byte)0x20, (byte)0x53, (byte)0x52, (byte)0x54, (byte)0x50, (byte)0x20, (byte)0x6d,
        (byte)0x61, (byte)0x73, (byte)0x74, (byte)0x65, (byte)0x72, (byte)0x20, (byte)0x73, (byte)0x61,
        (byte)0x6c, (byte)0x74, (byte)0x0};

//  "Responder SRTP master key"
    public static final byte[] respMasterKey = {
        (byte)0x52, (byte)0x65, (byte)0x73, (byte)0x70, (byte)0x6f, (byte)0x6e, (byte)0x64, (byte)0x65,
        (byte)0x72, (byte)0x20, (byte)0x53, (byte)0x52, (byte)0x54, (byte)0x50, (byte)0x20, (byte)0x6d,
        (byte)0x61, (byte)0x73, (byte)0x74, (byte)0x65, (byte)0x72, (byte)0x20, (byte)0x6b, (byte)0x65,
        (byte)0x79, (byte)0x0};

//  "Responder SRTP master salt"
    public static final byte[] respMasterSalt = {
        (byte)0x52, (byte)0x65, (byte)0x73, (byte)0x70, (byte)0x6f, (byte)0x6e, (byte)0x64, (byte)0x65,
        (byte)0x72, (byte)0x20, (byte)0x53, (byte)0x52, (byte)0x54, (byte)0x50, (byte)0x20, (byte)0x6d,
        (byte)0x61, (byte)0x73, (byte)0x74, (byte)0x65, (byte)0x72, (byte)0x20, (byte)0x73, (byte)0x61,
        (byte)0x6c, (byte)0x74, (byte)0x0};

//  "Initiator HMAC key"
    public static final byte[] iniHmacKey = {
        (byte)0x49, (byte)0x6e, (byte)0x69, (byte)0x74, (byte)0x69, (byte)0x61, (byte)0x74, (byte)0x6f,
        (byte)0x72, (byte)0x20, (byte)0x48, (byte)0x4d, (byte)0x41, (byte)0x43, (byte)0x20, (byte)0x6b,
        (byte)0x65, (byte)0x79, (byte)0x0};

//  "Responder HMAC key"
    public static final byte[] respHmacKey = {
        (byte)0x52, (byte)0x65, (byte)0x73, (byte)0x70, (byte)0x6f, (byte)0x6e, (byte)0x64, (byte)0x65,
        (byte)0x72, (byte)0x20, (byte)0x48, (byte)0x4d, (byte)0x41, (byte)0x43, (byte)0x20, (byte)0x6b,
        (byte)0x65, (byte)0x79, (byte)0x0};

//  "retained secret"
    public static final byte[] retainedSec = {
        (byte)0x72, (byte)0x65, (byte)0x74, (byte)0x61, (byte)0x69, (byte)0x6e, (byte)0x65, (byte)0x64,
        (byte)0x20, (byte)0x73, (byte)0x65, (byte)0x63, (byte)0x72, (byte)0x65, (byte)0x74, (byte)0x0};

//  "Initiator ZRTP key"
    public static final byte[] iniZrtpKey = {
        (byte)0x49, (byte)0x6e, (byte)0x69, (byte)0x74, (byte)0x69, (byte)0x61, (byte)0x74, (byte)0x6f,
        (byte)0x72, (byte)0x20, (byte)0x5a, (byte)0x52, (byte)0x54, (byte)0x50, (byte)0x20, (byte)0x6b,
        (byte)0x65, (byte)0x79, (byte)0x0};
    
//  "Responder ZRTP key"
    public static final byte[] respZrtpKey = {
        (byte)0x52, (byte)0x65, (byte)0x73, (byte)0x70, (byte)0x6f, (byte)0x6e, (byte)0x64, (byte)0x65,
        (byte)0x72, (byte)0x20, (byte)0x5a, (byte)0x52, (byte)0x54, (byte)0x50, (byte)0x20, (byte)0x6b,
        (byte)0x65, (byte)0x79, (byte)0x0};

//  "SAS"
    public static final byte[] sasString = {
        (byte)0x53, (byte)0x41, (byte)0x53, (byte)0x0};

//  "ZRTP-HMAC-KDF"
    public static final byte[] KDFString = {
        (byte)0x5a, (byte)0x52, (byte)0x54, (byte)0x50, (byte)0x2d, (byte)0x48, (byte)0x4d, (byte)0x41,
        (byte)0x43, (byte)0x2d, (byte)0x4b, (byte)0x44, (byte)0x46};

//  "ZRTP Session Key"
    public static final byte[] zrtpSessionKey = {
        (byte)0x5a, (byte)0x52, (byte)0x54, (byte)0x50, (byte)0x20, (byte)0x53, (byte)0x65, (byte)0x73,
        (byte)0x73, (byte)0x69, (byte)0x6f, (byte)0x6e, (byte)0x20, (byte)0x4b, (byte)0x65, (byte)0x79,
        (byte)0x0};

//  "ZRTP MSK"
    public static final byte[] zrtpMsk = {
        (byte)0x5a, (byte)0x52, (byte)0x54, (byte)0x50, (byte)0x20, (byte)0x4d, (byte)0x53, (byte)0x4b,
        (byte)0x0};

//  "Trusted MiTM key"
    public static final byte[] zrtpTrustedMitm = {
        (byte)0x54, (byte)0x72, (byte)0x75, (byte)0x73, (byte)0x74, (byte)0x65, (byte)0x64, (byte)0x20,
        (byte)0x4d, (byte)0x69, (byte)0x54, (byte)0x4d, (byte)0x20, (byte)0x6b, (byte)0x65, (byte)0x79,
        (byte)0x0};

    /**
     * The names of the algorithms as defined in ZRTP specification
     */

    public static final byte[] s256 = {
        (byte)0x53, (byte)0x32, (byte)0x35, (byte)0x36};        // "S256"
    public static final byte[] s384 = {
        (byte)0x53, (byte)0x33, (byte)0x38, (byte)0x34};        // "S384"
    public static final byte[] aes3 = {
        (byte)0x41, (byte)0x45, (byte)0x53, (byte)0x33};        // "AES3"
    public static final byte[] aes1 = {
        (byte)0x41, (byte)0x45, (byte)0x53, (byte)0x31};        // "AES1"
    public static final byte[] two1 = {
        (byte)0x32, (byte)0x46, (byte)0x53, (byte)0x31};        // "2FS1"
    public static final byte[] two3 = {
        (byte)0x32, (byte)0x46, (byte)0x53, (byte)0x33};        // "2FS3"
//    public static final byte[] dh4k = {
//        (byte)0x44, (byte)0x48, (byte)0x34, (byte)0x6b};        // "DH4k"
    public static final byte[] dh3k = {
        (byte)0x44, (byte)0x48, (byte)0x33, (byte)0x6b};        // "DH3k"
    public static final byte[] dh2k = {
        (byte)0x44, (byte)0x48, (byte)0x32, (byte)0x6b};        // "DH2k"
    public static final byte[] mult = {
        (byte)0x4D, (byte)0x75, (byte)0x6c, (byte)0x74};        // "Mult"
    public static final byte[] b32 = {
        (byte)0x42, (byte)0x33, (byte)0x32, (byte)0x20};        // "B32 "
    public static final byte[] b256 = {
        (byte)0x42, (byte)0x32, (byte)0x35, (byte)0x36};        // "B256"
    public static final byte[] b32e = {
        (byte)0x42, (byte)0x33, (byte)0x32, (byte)0x45};        // "B32E"
    public static final byte[] hs32 = {
        (byte)0x48, (byte)0x53, (byte)0x33, (byte)0x32};        // "HS32"
    public static final byte[] hs80 = {
        (byte)0x48, (byte)0x53, (byte)0x38, (byte)0x30};        // "HS80"
    public static final byte[] sk32 = {
        (byte)0x53, (byte)0x4B, (byte)0x33, (byte)0x32};        // "SK32"
    public static final byte[] sk64 = {
        (byte)0x53, (byte)0x4B, (byte)0x36, (byte)0x34};        // "SK64"
    public static final byte[] ec25 = {
        (byte)0x45, (byte)0x43, (byte)0x32, (byte)0x35};        // "EC25"
    public static final byte[] ec38 = {
        (byte)0x45, (byte)0x43, (byte)0x33, (byte)0x38};        // "EC38"
    public static final byte[] e255 = {
            (byte)0x45, (byte)0x32, (byte)0x35, (byte)0x35 };   // "E255"

    public enum  SupportedHashes {
        S256(s256),
        S384(s384);
        
        public byte[] name;
        SupportedHashes(byte[] nm) {
            name = nm;
        }
    }
    public enum SupportedSymCiphers {
        AES3(aes3, 32, AES_256, new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), 128)), SupportedSymAlgos.AES),
        AES1(aes1, 16, AES_128, new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), 128)), SupportedSymAlgos.AES), 
        TWO3(two3, 32, TWO_256, new BufferedBlockCipher(new CFBBlockCipher(new TwofishEngine(), 128)), SupportedSymAlgos.TwoFish),
        TWO1(two1, 16, TWO_128, new BufferedBlockCipher(new CFBBlockCipher(new TwofishEngine(), 128)), SupportedSymAlgos.TwoFish);

        final public byte[] name;
        final public int keyLength;
        final public String readable;
        final public BufferedBlockCipher cipher;
        final public SupportedSymAlgos algo;

        SupportedSymCiphers(byte[] nm, int keyLen, String ra, BufferedBlockCipher ci, SupportedSymAlgos al) {
            name = nm;
            keyLength = keyLen;
            readable = ra;
            cipher = ci;
            algo = al;
        }
    }

    public enum SupportedSymAlgos {
        AES, TwoFish
    }

    public static final X9ECParameters x9Ec25 = SECNamedCurves.getByName("secp256r1");
    public static final X9ECParameters x9Ec38 = SECNamedCurves.getByName("secp384r1");

    public enum SupportedPubKeys {
        EC25(ec25, 64, new ECKeyGenerationParameters(new ECDomainParameters(x9Ec25.getCurve(),
                x9Ec25.getG(), x9Ec25.getN(), x9Ec25.getH(),
                x9Ec25.getSeed()), ZrtpFortuna.getInstance())),
        EC38(ec38, 96, new ECKeyGenerationParameters(new ECDomainParameters(x9Ec38.getCurve(),
                x9Ec38.getG(), x9Ec38.getN(), x9Ec38.getH(),
                x9Ec38.getSeed()), ZrtpFortuna.getInstance())),

        E255(e255, 32, new Djb25519KeyGenerationParameters(ZrtpFortuna.getInstance())),

        DH2K(dh2k, 256, new DHKeyGenerationParameters(ZrtpFortuna.getInstance(),
                specDh2k)),
        DH3K(dh3k, 384, new DHKeyGenerationParameters(ZrtpFortuna.getInstance(),
                specDh3k)),
        MULT(mult);


        public byte[] name;
        final public AsymmetricCipherKeyPairGenerator keyPairGen;
        final public int pubKeySize;
        final public DHParameters specDh;
        final public ECCurve curve;
        final public BasicAgreement dhContext;

        SupportedPubKeys(byte[] nm) {
            name = nm;
            pubKeySize = 0;
            keyPairGen = null;
            specDh = null;
            dhContext = null;
            curve = null;
        }
        
        SupportedPubKeys(byte[] nm, int size, ECKeyGenerationParameters ecdh) {
            name = nm;
            pubKeySize = size;
            if (ecdh != null) {
                keyPairGen = new ECKeyPairGenerator();
                keyPairGen.init(ecdh);
                curve = ecdh.getDomainParameters().getCurve();
                dhContext = new ECDHBasicAgreement();
            }
            else {
                curve = null;
                keyPairGen = null;
                dhContext = null;
            }
            specDh = null;
        }

        SupportedPubKeys(byte[] nm, int size, DHKeyGenerationParameters dh) {
            name = nm;
            pubKeySize = size;
            if (dh != null) {
                keyPairGen = new DHBasicKeyPairGenerator();
                keyPairGen.init(dh);
                specDh = dh.getParameters();
                dhContext = new DHBasicAgreement();
            }
            else {
                keyPairGen = null;
                specDh = null;
                dhContext = null;
            }
            curve = null;
        }

        SupportedPubKeys(byte[] nm, int size, Djb25519KeyGenerationParameters djbdh) {
            name = nm;
            pubKeySize = size;
            keyPairGen = new Djb25519KeyPairGenerator();
            keyPairGen.init(djbdh);
            dhContext = new Djb25519DHBasicAgreement();
            curve = null;

            specDh = null;
        }
    }

    public enum SupportedSASTypes {
        B32(b32),
        B256(b256),
        B32E(b32e);
        
        public byte[] name;
        SupportedSASTypes(byte[] nm) {
            name = nm;
        }
    }

    public enum SupportedAuthLengths {
        SK32(sk32, SupportedAuthAlgos.SK, 32),
        HS32(hs32, SupportedAuthAlgos.HS, 32),
        SK64(sk64, SupportedAuthAlgos.SK, 64),
        HS80(hs80, SupportedAuthAlgos.HS, 80);
        
        final public byte[] name;
        final public SupportedAuthAlgos algo;
        final public int length;
        SupportedAuthLengths(byte[] nm, SupportedAuthAlgos al, int len) {
            name = nm;
            algo = al;
            length = len;
        }
    }
    
    public enum SupportedAuthAlgos {
        HS, SK
    }


    // The Diffie-Helman constants as defined in the ZRTP specification
    // The DH prime for DH2k (2048 bit) as defined in RFC 3526
    public static final BigIntegerCrypto P2048 = new BigIntegerCrypto(
//                        1                   2        
//    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3           24 bytes per line
    "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1" +        // 0
    "29024E088A67CC74020BBEA63B139B22514A08798E3404DD" +        // 1
    "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245" +        // 2
    "E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED" +        // 3
    "EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3D" +        // 4
    "C2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F" +        // 5
    "83655D23DCA3AD961C62F356208552BB9ED529077096966D" +        // 6
    "670C354E4ABC9804F1746C08CA18217C32905E462E36CE3B" +        // 7
    "E39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9" +        // 8
    "DE2BCBF6955817183995497CEA956AE515D2261898FA0510" +        // 9
    "15728E5A8AACAA68FFFFFFFFFFFFFFFF", 16);                    // Total = 16 + 24 * 10 = 256
    
   
    // The DH prime for DH3k (3072 bit) as defined in RFC 3526
    public static final BigIntegerCrypto P3072 = new BigIntegerCrypto(
//                        1                   2        
//    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3           24 bytes per line
    "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1" +        // 0
    "29024E088A67CC74020BBEA63B139B22514A08798E3404DD" +        // 1
    "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245" +        // 2
    "E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED" +        // 3
    "EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3D" +        // 4
    "C2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F" +        // 5
    "83655D23DCA3AD961C62F356208552BB9ED529077096966D" +        // 6
    "670C354E4ABC9804F1746C08CA18217C32905E462E36CE3B" +        // 7
    "E39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9" +        // 8
    "DE2BCBF6955817183995497CEA956AE515D2261898FA0510" +        // 9
    "15728E5A8AAAC42DAD33170D04507A33A85521ABDF1CBA64" +        // 10
    "ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7" +        // 11
    "ABF5AE8CDB0933D71E8C94E04A25619DCEE3D2261AD2EE6B" +        // 12
    "F12FFA06D98A0864D87602733EC86A64521F2B18177B200C" +        // 13
    "BBE117577A615D6C770988C0BAD946E208E24FA074E5AB31" +        // 14
    "43DB5BFCE0FD108E4B82D120A93AD2CAFFFFFFFFFFFFFFFF", 16);    // 15, total = 24 * 16 = 384

    /* **** Not used anymore in ZRTP
    // The DH prime for DH4k (4096 bit) as defined in RFC 3526
    public static final BigIntegerCrypto P4096 = new BigIntegerCrypto(
//                        1                   2        
//    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3           24 bytes per line
    "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1" +        // 0
    "29024E088A67CC74020BBEA63B139B22514A08798E3404DD" +        // 1
    "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245" +        // 2
    "E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED" +        // 3
    "EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3D" +        // 4
    "C2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F" +        // 5
    "83655D23DCA3AD961C62F356208552BB9ED529077096966D" +        // 6
    "670C354E4ABC9804F1746C08CA18217C32905E462E36CE3B" +        // 7
    "E39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9" +        // 8
    "DE2BCBF6955817183995497CEA956AE515D2261898FA0510" +        // 9
    "15728E5A8AAAC42DAD33170D04507A33A85521ABDF1CBA64" +        // 10
    "ECFB850458DBEF0A8AEA71575D060C7DB3970F85A6E1E4C7" +        // 11
    "ABF5AE8CDB0933D71E8C94E04A25619DCEE3D2261AD2EE6B" +        // 12
    "F12FFA06D98A0864D87602733EC86A64521F2B18177B200C" +        // 13
    "BBE117577A615D6C770988C0BAD946E208E24FA074E5AB31" +        // 14
    "43DB5BFCE0FD108E4B82D120A92108011A723C12A787E6D7" +        // 15
    "88719A10BDBA5B2699C327186AF4E23C1A946834B6150BDA" +        // 16
    "2583E9CA2AD44CE8DBBBC2DB04DE8EF92E8EFC141FBECAA6" +        // 17
    "287C59474E6BC05D99B2964FA090C3A2233BA186515BE7ED" +        // 18
    "1F612970CEE2D7AFB81BDD762170481CD0069127D5B05AA9" +        // 19
    "93B4EA988D8FDDC186FFB7DC90A6C08F4DF435C934063199" +        // 20
    "FFFFFFFFFFFFFFFF", 16);                                    // Total = 8 + 24 * 21 = 512 
*** */
    // DH generator 2
    public static final BigIntegerCrypto two = BigIntegerCrypto.valueOf(2);
    
    public static final BigIntegerCrypto P2048MinusOne = P2048.subtract(BigIntegerCrypto.ONE);
    public static final BigIntegerCrypto P3072MinusOne = P3072.subtract(BigIntegerCrypto.ONE);
//    public static final BigIntegerCrypto P4096MinusOne = P4096.subtract(BigIntegerCrypto.ONE);
    
    public static final DHParameters specDh2k = new DHParameters(ZrtpConstants.P2048,
            ZrtpConstants.two, null, 256);
    public static final DHParameters specDh3k = new DHParameters(ZrtpConstants.P3072,
            ZrtpConstants.two, null, 512);

//    public static final DHParameters specDh4k = new DHParameters(ZrtpConstants.P4096, ZrtpConstants.two, null, 512);
    public static final String sas256WordsOdd[] = { "adroitness", "adviser", "aftermath", "aggregate", "alkali",
                    "almighty", "amulet", "amusement", "antenna", "applicant", "Apollo", "armistice", "article",
                    "asteroid", "Atlantic", "atmosphere", "autopsy", "Babylon", "backwater", "barbecue", "belowground",
                    "bifocals", "bodyguard", "bookseller", "borderline", "bottomless", "Bradbury", "bravado",
                    "Brazilian", "breakaway", "Burlington", "businessman", "butterfat", "Camelot", "candidate",
                    "cannonball", "Capricorn", "caravan", "caretaker", "celebrate", "cellulose", "certify",
                    "chambermaid", "Cherokee", "Chicago", "clergyman", "coherence", "combustion", "commando",
                    "company", "component", "concurrent", "confidence", "conformist", "congregate", "consensus",
                    "consulting", "corporate", "corrosion", "councilman", "crossover", "crucifix", "cumbersome",
                    "customer", "Dakota", "decadence", "December", "decimal", "designing", "detector", "detergent",
                    "determine", "dictator", "dinosaur", "direction", "disable", "disbelief", "disruptive",
                    "distortion", "document", "embezzle", "enchanting", "enrollment", "enterprise", "equation",
                    "equipment", "escapade", "Eskimo", "everyday", "examine", "existence", "exodus", "fascinate",
                    "filament", "finicky", "forever", "fortitude", "frequency", "gadgetry", "Galveston", "getaway",
                    "glossary", "gossamer", "graduate", "gravity", "guitarist", "hamburger", "Hamilton", "handiwork",
                    "hazardous", "headwaters", "hemisphere", "hesitate", "hideaway", "holiness", "hurricane",
                    "hydraulic", "impartial", "impetus", "inception", "indigo", "inertia", "infancy", "inferno",
                    "informant", "insincere", "insurgent", "integrate", "intention", "inventive", "Istanbul",
                    "Jamaica", "Jupiter", "leprosy", "letterhead", "liberty", "maritime", "matchmaker", "maverick",
                    "Medusa", "megaton", "microscope", "microwave", "midsummer", "millionaire", "miracle", "misnomer",
                    "molasses", "molecule", "Montana", "monument", "mosquito", "narrative", "nebula", "newsletter",
                    "Norwegian", "October", "Ohio", "onlooker", "opulent", "Orlando", "outfielder", "Pacific",
                    "pandemic", "Pandora", "paperweight", "paragon", "paragraph", "paramount", "passenger", "pedigree",
                    "Pegasus", "penetrate", "perceptive", "performance", "pharmacy", "phonetic", "photograph",
                    "pioneer", "pocketful", "politeness", "positive", "potato", "processor", "provincial", "proximate",
                    "puberty", "publisher", "pyramid", "quantity", "racketeer", "rebellion", "recipe", "recover",
                    "repellent", "replica", "reproduce", "resistor", "responsive", "retraction", "retrieval",
                    "retrospect", "revenue", "revival", "revolver", "sandalwood", "sardonic", "Saturday", "savagery",
                    "scavenger", "sensation", "sociable", "souvenir", "specialist", "speculate", "stethoscope",
                    "stupendous", "supportive", "surrender", "suspicious", "sympathy", "tambourine", "telephone",
                    "therapist", "tobacco", "tolerance", "tomorrow", "torpedo", "tradition", "travesty", "trombonist",
                    "truncated", "typewriter", "ultimate", "undaunted", "underfoot", "unicorn", "unify", "universe",
                    "unravel", "upcoming", "vacancy", "vagabond", "vertigo", "Virginia", "visitor", "vocalist",
                    "voyager", "warranty", "Waterloo", "whimsical", "Wichita", "Wilmington", "Wyoming", "yesteryear",
                    "Yucatan" };

    public static final String sas256WordsEven[] = { "aardvark", "absurd", "accrue", "acme", "adrift", "adult",
                    "afflict", "ahead", "aimless", "Algol", "allow", "alone", "ammo", "ancient", "apple", "artist",
                    "assume", "Athens", "atlas", "Aztec", "baboon", "backfield", "backward", "banjo", "beaming",
                    "bedlamp", "beehive", "beeswax", "befriend", "Belfast", "berserk", "billiard", "bison",
                    "blackjack", "blockade", "blowtorch", "bluebird", "bombast", "bookshelf", "brackish", "breadline",
                    "breakup", "brickyard", "briefcase", "Burbank", "button", "buzzard", "cement", "chairlift",
                    "chatter", "checkup", "chisel", "choking", "chopper", "Christmas", "clamshell", "classic",
                    "classroom", "cleanup", "clockwork", "cobra", "commence", "concert", "cowbell", "crackdown",
                    "cranky", "crowfoot", "crucial", "crumpled", "crusade", "cubic", "dashboard", "deadbolt",
                    "deckhand", "dogsled", "dragnet", "drainage", "dreadful", "drifter", "dropper", "drumbeat",
                    "drunken", "Dupont", "dwelling", "eating", "edict", "egghead", "eightball", "endorse", "endow",
                    "enlist", "erase", "escape", "exceed", "eyeglass", "eyetooth", "facial", "fallout", "flagpole",
                    "flatfoot", "flytrap", "fracture", "framework", "freedom", "frighten", "gazelle", "Geiger",
                    "glitter", "glucose", "goggles", "goldfish", "gremlin", "guidance", "hamlet", "highchair",
                    "hockey", "indoors", "indulge", "inverse", "involve", "island", "jawbone", "keyboard", "kickoff",
                    "kiwi", "klaxon", "locale", "lockup", "merit", "minnow", "miser", "Mohawk", "mural", "music",
                    "necklace", "Neptune", "newborn", "nightbird", "Oakland", "obtuse", "offload", "optic", "orca",
                    "payday", "peachy", "pheasant", "physique", "playhouse", "Pluto", "preclude", "prefer",
                    "preshrunk", "printer", "prowler", "pupil", "puppy", "python", "quadrant", "quiver", "quota",
                    "ragtime", "ratchet", "rebirth", "reform", "regain", "reindeer", "rematch", "repay", "retouch",
                    "revenge", "reward", "rhythm", "ribcage", "ringbolt", "robust", "rocker", "ruffled", "sailboat",
                    "sawdust", "scallion", "scenic", "scorecard", "Scotland", "seabird", "select", "sentence",
                    "shadow", "shamrock", "showgirl", "skullcap", "skydive", "slingshot", "slowdown", "snapline",
                    "snapshot", "snowcap", "snowslide", "solo", "southward", "soybean", "spaniel", "spearhead",
                    "spellbind", "spheroid", "spigot", "spindle", "spyglass", "stagehand", "stagnate", "stairway",
                    "standard", "stapler", "steamship", "sterling", "stockman", "stopwatch", "stormy", "sugar",
                    "surmount", "suspense", "sweatband", "swelter", "tactics", "talon", "tapeworm", "tempest", "tiger",
                    "tissue", "tonic", "topmost", "tracker", "transit", "trauma", "treadmill", "Trojan", "trouble",
                    "tumor", "tunnel", "tycoon", "uncut", "unearth", "unwind", "uproot", "upset", "upshot", "vapor",
                    "village", "virus", "Vulcan", "waffle", "wallet", "watchword", "wayside", "willow", "woodlark",
                    "Zulu" };
}
