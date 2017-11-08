package com.ygdxd.utils;


import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JSON Web Token
 * <p>
 * 官网：https://jwt.io/
 *
 * <pre>
 * id: 对应body中的jti。
 * issuer: 对应body中的iss。请求的发起者
 * issuedAt: 对应body中的iat。请求的发起时间
 * expiration: 对应body中的exp。过期时间
 * compressWith: 对应heaer中的zip。
 * signWith: 对应header中的alg。
 * key: 签名key
 * </pre>
 * @author Created by ygdxd_admin at 2017-09-21 下午1:32
 */
public class JWTTool {

    private final String id;
    private final String issuer;
    private final Date issuedAt;
    private final Date expiration;
    private final CompressionCodec compressWith;
    private final SignatureAlgorithm signWith;
    private final String key;

    private JwtBuilder builder;
    private SecretKey secretKey;

    /**
     * 不允许通过new的方式创建对象
     */
    private JWTTool(String id, String issuer, Date issuedAt, Date expiration,
                CompressionCodec compressWith, SignatureAlgorithm signWith, String key) {
        super();
        this.id = id;
        this.issuer = issuer;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
        this.compressWith = compressWith;
        this.signWith = signWith;
        this.key = key;

        if (!JWTTool.isNotNull(this.getKey())) {
            throw new IllegalArgumentException("key is null");
        }
        if (this.getSignWith() == null) {
            throw new IllegalArgumentException("signWith is null");
        }
        builder = Jwts.builder();
        if (JWTTool.isNotNull(this.getId())) {
            builder.setId(this.getId());
        }
        if (JWTTool.isNotNull(this.getIssuer())) {
            builder.setIssuer(this.getIssuer());
        }
        if (this.getIssuedAt() != null) {
            builder.setIssuedAt(this.getIssuedAt());
        }
        if (this.getExpiration() != null) {
            builder.setExpiration(this.getExpiration());
        }
        if (this.getCompressWith() != null) {
            builder.compressWith(this.getCompressWith());
        }
        secretKey = JWTTool.createKey(this.getKey());
        // 两个参数，一个是加密算法，一个秘钥
        builder.signWith(this.getSignWith(), secretKey);
    }


    public String getId() {
        return id;
    }

    public String getIssuer() {
        return issuer;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public Date getExpiration() {
        return expiration;
    }

    public CompressionCodec getCompressWith() {
        return compressWith;
    }

    public SignatureAlgorithm getSignWith() {
        return signWith;
    }

    public String getKey() {
        return key;
    }

    public JwtBuilder getBuilder() {
        return builder;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public static Builder copy(final JWTTool copy) {
        return new Builder(copy.key).id(copy.id).issuer(copy.issuer).issuedAt(copy.issuedAt)
                .expiration(copy.expiration);
    }

    public static class Builder {
        private String id;
        private String issuer;
        private Date issuedAt;
        private Date expiration;
        private CompressionCodec compressWith;
        private SignatureAlgorithm signWith = SignatureAlgorithm.HS256;
        private String key;

        public Builder(String key) {
            this.key = key;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder issuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public Builder issuedAt(Date issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        public Builder expiration(Date expiration) {
            this.expiration = expiration;
            return this;
        }

        public Builder compressWith(CompressionCodec compressWith) {
            this.compressWith = compressWith;
            return this;
        }

        public Builder signWith(SignatureAlgorithm signWith) {
            this.signWith = signWith;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public JWTTool build() {
            return new JWTTool(id, issuer, issuedAt, expiration, compressWith, signWith, key);
        }
    }

    private static boolean isNotNull(String str) {
        if (str != null && str.trim().length() > 0)
            return true;
        return false;
    }

    private static SecretKey createKey(String key){
        byte[] bytes = key.getBytes(Charset.forName("utf-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes,"AES");
        return secretKeySpec;
    }

    public String compact(String subject){
        if(!JWTTool.isNotNull(subject)){
            throw new IllegalArgumentException("arg:subject is null");
        }
        builder.setSubject(subject);
        return builder.compact();
    }

    /**
     * 根据构建器的当前配置状态解析指定的紧凑型串行化JWS字符串，并返回结果声明JWS实例
     *
     * @param compact 加密的jwt信息
     */
    public Jws<Claims> parser(String compact) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(compact);
    }

    // 初始化jwt时，推荐使用静态成员变量，多少种jwt实现就多少个静态成员变量。这样可以减少JwtBuilder、SecretKey等对象的创建
    private static final String KEY = "ygdxd"; // 密钥
    private static final JWTTool jwt = new JWTTool.Builder(KEY).build();
    private static final JWTTool jwt_10086 = new JWTTool.Builder("10086").build();
    // 如果要使用不同的jwt对象构建解析，推荐使用这种方法
    // private static final JWT jwt_10086 = JWT.copy(jwt).key("10086").build();

    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("id","xiaoming");
        map.put("username","daxiong");
        String compact = jwt.compact(JSON.toJSONString(map));
        System.out.println("compact:"+compact);
        Jws<Claims> parser = jwt.parser(compact);
        System.out.println("parser : " + parser);
        System.out.println("parser : " + parser.getBody().getSubject());
        System.out.println("parser : " + parser.getBody().get("sub"));

    }

}
