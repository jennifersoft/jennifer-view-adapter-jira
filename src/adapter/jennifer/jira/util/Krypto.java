package adapter.jennifer.jira.util;

import com.jennifersoft.view.adapter.util.LogUtil;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;
import java.util.Scanner;

/**
 * Simple Utility to encrypt/decrypt password using javax.crypto
 */
public class Krypto {

    private KeySpec keySpec;
    private SecretKeyFactory secretKeyFactory;
    private Cipher cipher;
    private final String SEED    = "E8DB356D67F9B3627E7F19CB7E569";
    private final String SCHEMA = "DESede";
    private final String ENCODING = "UTF8";
    private SecretKey secretKey;

    public Krypto(){
        try{
            keySpec = new DESedeKeySpec(SEED.getBytes());
            secretKeyFactory = SecretKeyFactory.getInstance(SCHEMA);
            cipher = Cipher.getInstance(SCHEMA);
            secretKey = secretKeyFactory.generateSecret(keySpec);
        }catch (Exception ex){
            LogUtil.error("Failed to load initialize Krypto : " + ex.toString());
        }
    }

    public String encrypt(String source){
        try{
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] pByte = source.getBytes(ENCODING);
            byte[] eByte = cipher.doFinal(pByte);
            return new String(Base64.encodeBase64(eByte));
        }catch (Exception ex){
            LogUtil.error("Encrypting failed : " + ex.toString());
            return null;
        }
    }

    public String decrypt(String source){
        try{
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            byte[] eBytes = Base64.decodeBase64(source);
            byte[] pBytes = cipher.doFinal(eBytes);
            return new String(pBytes);
        }catch (Exception ex){
            LogUtil.error("Decryption failed : " + ex.toString());
            return null;
        }
    }

    private static void printUsage(){
        System.out.println("To encrypt a string please pass [e] as first argument and your string as second argument");
        System.out.println("\tExample: java adapter.jennifer.jira.util.Krypto e MyString\n");
        System.out.println("To decrypt a string please pass [d] as first argument and your string as second argument");
        System.out.println("\tExample: java adapter.jennifer.jira.util.Krypto d MyString");

    }

    public static void main(String[] args) {
        System.out.println("Krypt: Simple Encryption util");
        if(args.length == 0){
            printUsage();
            return;
        }
        else {
            String mode = args[0];
            String data = args[1];
            String result = null;
            if(mode.toLowerCase().equals("e")){
                result = new Krypto().encrypt(data);
                System.out.printf("Encryption Result of [%s] is: %s\n",data,result);
            }else if(mode.toLowerCase().equals("d")){
                result = new Krypto().decrypt(data);
                System.out.printf("Decryption Result of [%s] is: %s\n",data,result);
            }else{
                printUsage();
            }
        }
    }
}
