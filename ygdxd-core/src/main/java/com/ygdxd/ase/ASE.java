package com.ygdxd.ase;

import com.ygdxd.exp.BaseException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author ygdxd_admin
 * @create 2017-08-16 下午12:49
 */
public enum ASE {
    GBK {
        public String getCharset(){
            return "GBK";
        }
    },

    UTF8 {
        public String getCharset(){
            return "UTF-8";
        }
    };

    private static final String DEFAULT_KEY = "util-ase";

    private static ConcurrentHashMap<String, SecretKey> KEY_MAP = new ConcurrentHashMap();

    private static final Logger LOGGER = LoggerFactory.getLogger(ASE.class);

    /**
     * 获取编码
     * @return charset
     */
    public String getCharset(){
        throw new AbstractMethodError();
    }

    /**
     *
     * 把构建好的 SecretKey 保存到内存中，以方便下次使用时，不用再重新构建
     * @param key 密钥
     * @return 加密内容
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private SecretKey generatorKey(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        SecretKey secretKey = KEY_MAP.get(key);
        if (secretKey == null) {
            // 1、构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // 2、根据传入的字节数组，生成一个128位的随机源
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes(this.getCharset()));
            keyGenerator.init(128, random);
            // 3、产生原始对称密钥
            SecretKey originalKey = keyGenerator.generateKey();
            // 4、获得原始对称密钥的字节数组
            byte[] raw = originalKey.getEncoded();
            // 5、根据字节数组生成AES密钥
            secretKey = new SecretKeySpec(raw, "AES");
            KEY_MAP.put(key, secretKey);
        }
        return secretKey;
    }


    /**
     * ASE加密 默认密钥
     *
     * @param content 要加密的内容
     * @return 加密内容
     */
    public String encode(String content) throws BaseException{
        return encode(DEFAULT_KEY ,content);
    }


    /**
     *
     * AES加密
     * @param key 密钥
     * @param content 要加密的内容
     * @return 加密内容
     * @throws BaseException
     */
    public String encode(String key,String content)throws BaseException{
        try {
            // 6、根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 7、初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, generatorKey(key));
            // 8、获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteEncode = content.getBytes(this.getCharset());
            // 9、根据密码器的初始化方式--加密：将数据加密
            byte[] byteAES = cipher.doFinal(byteEncode);
            // 10、将加密后的数据转换为字符串
            return new String(Base64.decodeBase64(byteAES), this.getCharset());
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
            throw new BaseException("无此算法", e);
        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
            throw new BaseException("该环境没有此填充机制", e);
        } catch (InvalidKeyException e) {
//            e.printStackTrace();
            throw new BaseException("无效键（无效编码，长度错误，未初始化等）", e);
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            throw new BaseException("不支持字符编码。", e);
        } catch (BadPaddingException e) {
//            e.printStackTrace();
            throw new BaseException("填充机制未正确填充数据时。", e);
        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
            throw new BaseException("分组密码的数据的长度不正确。", e);
        }

    }


    /**
     * ASE 解密 默认密钥
     * @param content
     * @return 解密内容
     * @throws BaseException
     */
    public String decode(String content)throws BaseException{
        return decode(DEFAULT_KEY, content);
    }


    /**
     * 解密
     *
     * @param key 密钥
     * @param content 解密内容
     * @return 解密后的内容
     * @throws BaseException
     */
    public String decode(String key, String content) throws BaseException{
        try {
            // 6、根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 7、初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, generatorKey(key));
            // 8、将加密并编码后的内容解码成字节数组
            byte[] byteContent = Base64.decodeBase64(content);
            // 9、解密
            byte[] byteDecode = cipher.doFinal(byteContent);
            // 10、将解密后的数据转换为字符串
            return new String(byteContent, this.getCharset());
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
            throw new BaseException("无此算法",e);
        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
            throw new BaseException("该环境没有此填充机制", e);
        } catch (InvalidKeyException e) {
//            e.printStackTrace();
            throw new BaseException("无效键（无效编码，长度错误，未初始化等）", e);
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            throw new BaseException("不支持字符编码", e);
        } catch (BadPaddingException e) {
//            e.printStackTrace();
            throw new BaseException("填充机制未正确填充数据时",e);
        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
            throw new BaseException("分组密码的数据的长度不正确。", e);
        }
    }



}
