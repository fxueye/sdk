//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.goldautumn.sdk.minterface;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public AES() {
    }

    public String encrypt(String content, String password) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, skeySpec);
            byte[] result = cipher.doFinal(byteContent);
            String r = Base64.encodeToString(result, 8).trim();
            r = r.replaceAll(" ", "");
            r = r.replaceAll("\n", "");
            return r;
        } catch (NoSuchAlgorithmException var8) {
            var8.printStackTrace();
        } catch (NoSuchPaddingException var9) {
            var9.printStackTrace();
        } catch (InvalidKeyException var10) {
            var10.printStackTrace();
        } catch (UnsupportedEncodingException var11) {
            var11.printStackTrace();
        } catch (IllegalBlockSizeException var12) {
            var12.printStackTrace();
        } catch (BadPaddingException var13) {
            var13.printStackTrace();
        }

        return null;
    }

    public String decrypt(String content, String password) {
        try {
            byte[] byt = Base64.decode(content, 8);
            SecretKeySpec key = new SecretKeySpec(password.getBytes("utf-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            byte[] result = cipher.doFinal(byt);
            return new String(result);
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
        } catch (NoSuchPaddingException var8) {
            var8.printStackTrace();
        } catch (InvalidKeyException var9) {
            var9.printStackTrace();
        } catch (UnsupportedEncodingException var10) {
            var10.printStackTrace();
        } catch (IllegalBlockSizeException var11) {
            var11.printStackTrace();
        } catch (BadPaddingException var12) {
            var12.printStackTrace();
        }

        return null;
    }
}
