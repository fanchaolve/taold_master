
package com.bb.taold.lianlian.env;

public class EnvConstants {
    private EnvConstants() {
    }

    /**
     * TODO 商户号，商户MD5 key 配置。本测试Demo里的“PARTNER”；强烈建议将私钥配置到服务器上，以免泄露。“MD5_KEY”字段均为测试字段。正式接入需要填写商户自己的字段
     */
    public static final String PARTNER_PREAUTH = "201504071000272504"; // 短信

    public static final String MD5_KEY_PREAUTH = "201504071000272504_test_20150417";

    //测试环境 快捷支付测试商户号
//    public static final String PARTNER = "201310102000003524";
//    public static final String MD5_KEY = "md5key_201311062000003548_20131107";//md5key_201311062000003548_20131107
    
  //测试环境 认证支付测试商户号
//   public static final String PARTNER = "201506050000022002";
//    public static final String MD5_KEY = "md5key_201506050000022002";
    
//    public static final String PARTNER = "201307232000003510";
//   public static final String MD5_KEY = "201103171000000000";
    
    
    //正式环境 快捷支付测试商户号
//    public static final String PARTNER = "201408071000001546";
//    public static final String MD5_KEY = "201408071000001546_test_20140815";
    
//    //正式环境 认证支付测试商户号
//    public static final String PARTNER = "201408071000001543";
//    public static final String MD5_KEY = "201408071000001543test_20140812";
    
    //生产环境 认证支付测试商户号
    public static final String PARTNER = "201408071000001543";
    public static final String MD5_KEY = "201408071000001543";
   // 生产环境 游易付测试商户号
//      public static final String PARTNER = "201510211000547504";
//     public static final String MD5_KEY = "yintong1234567890";
   
   // 生产环境 车易付测试商户号
// public static final String PARTNER = "201605251000875402";
//public static final String MD5_KEY = "201605251000875402";
     
    

    // 商户（RSA）私钥 TODO 强烈建议将私钥配置到服务器上，否则有安全隐患
//     public static final String RSA_PRIVATE =
//     "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOilN4tR7HpNYvSBra/DzebemoAiGtGeaxa+qebx/O2YAdUFPI+xTKTX2ETyqSzGfbxXpmSax7tXOdoa3uyaFnhKRGRvLdq1kTSTu7q5s6gTryxVH2m62Py8Pw0sKcuuV0CxtxkrxUzGQN+QSxf+TyNAv5rYi/ayvsDgWdB3cRqbAgMBAAECgYEAj02d/jqTcO6UQspSY484GLsL7luTq4Vqr5L4cyKiSvQ0RLQ6DsUG0g+Gz0muPb9ymf5fp17UIyjioN+ma5WquncHGm6ElIuRv2jYbGOnl9q2cMyNsAZCiSWfR++op+6UZbzpoNDiYzeKbNUz6L1fJjzCt52w/RbkDncJd2mVDRkCQQD/Uz3QnrWfCeWmBbsAZVoM57n01k7hyLWmDMYoKh8vnzKjrWScDkaQ6qGTbPVL3x0EBoxgb/smnT6/A5XyB9bvAkEA6UKhP1KLi/ImaLFUgLvEvmbUrpzY2I1+jgdsoj9Bm4a8K+KROsnNAIvRsKNgJPWd64uuQntUFPKkcyfBV1MXFQJBAJGs3Mf6xYVIEE75VgiTyx0x2VdoLvmDmqBzCVxBLCnvmuToOU8QlhJ4zFdhA1OWqOdzFQSw34rYjMRPN24wKuECQEqpYhVzpWkA9BxUjli6QUo0feT6HUqLV7O8WqBAIQ7X/IkLdzLa/vwqxM6GLLMHzylixz9OXGZsGAkn83GxDdUCQA9+pQOitY0WranUHeZFKWAHZszSjtbe6wDAdiKdXCfig0/rOdxAODCbQrQs7PYy1ed8DuVQlHPwRGtokVGHATU=";

    public static final String RSA_PRIVATE =
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDQ/TMsW3MZMgja\n" +
                    "kPkY5BKG77mtHXf3Y3zbmPpTYqT80TiEqx1woY0v5hkm6LXZa5bvDde8Rr3s8BQW\n" +
                    "IktCJfusf7VQPybURbU5DBMC3GK55iFg9Z6NbAgu+2e2UvIFYooAVCi1odoBZb4u\n" +
                    "eEa5ATGI4NWoVFU8b0h3I3B/1IBVYCHespVXFxARoOCZKlaedfLQqA72/dZZpEwG\n" +
                    "oXoDV3BUW8Xu0FvaLVzCOn7XIKzQNIn50VfZlPjBPmGZUxwXAwd29U+U/VcBBOGz\n" +
                    "eNxtrKcJ7vpxDkOqQuhslgVFvQ3ouAzj1zF2T8s634bL7mq0gtM2WyCTTcyUTrBb\n" +
                    "XZoK8LVxAgMBAAECggEAeYCbvdA1LJLQXvImQHho5RZ+ps0ZMDUhJDLeZphVP78L\n" +
                    "0uYBKbhi81Qdj2eAQjWw/K7ZnFasmRJDO1MY2g5nMbTV3x+4HXWhlNcWPnWO2HGZ\n" +
                    "OlvSoHoMj/QOKgwzNTpC7UoAmQjOTD2wwiefRnQRLnk4/rJ5E7fHM6zg/RC4w+kg\n" +
                    "j+pEiAImpUv7zkaxv5kcwqJspf4Cc8t366vWTFcxzssfzCx0h271CLTqFE8S9Tam\n" +
                    "G3UgqBo1hmBq7MPkP+rHzUY7ngR89u3UEGyoXUDAYXchBsRq4zaAh2472hDvPDpf\n" +
                    "cPeXYYQHNqPT024AKGUMEtFQfCnuhP/zHJsROfQ2oQKBgQD2DL3EwTDi4mv3s2Mu\n" +
                    "G/peu14/gM4NjjaMkOJ26TDh+Nyem9Nh0F7kT0g0zyJV2bl4UrZ/UrWTqhePPxiO\n" +
                    "qyLFCBjxALgRD/HBao4+bOsadImo/+KYCHlu2wwANlmCXDSCnMUr7P31R1WoXPXg\n" +
                    "1M1x5ORc38O9SljNdztyEzeQfQKBgQDZcMiI679Z0H6ACsgjlu3SmL2UjGGUx2lQ\n" +
                    "vWI5wHHK06ZLHP6A/jab33Es4Hc6unwDn4f8P8uXINBNNGIGe24KqqUAH9+Oq4Qr\n" +
                    "iznrsyjmetHIg3MRGhWq+qSeHM7IL13uNEOk911AuNzpA4098tzKHnxYg1KEeNrI\n" +
                    "KPWtwlbfBQKBgQDh5IC1CsjfBBErsD32LymFn2+/zXlfaJkcJEhF84E1dUzXMSi4\n" +
                    "PJ1h7ofUM/bJ6sIkfYufCUg68YX/tWXOVb/uNKjxWj+jKohBkppVJZoTB7r6tTJL\n" +
                    "cjxKUkCKUVuMGyr6XwOGeGYPshYqJG54o+aaEhK2UZlbR86PlE+3Q31X4QKBgDSN\n" +
                    "FHo697keMaSCdpJbZMj0D0V6lA7gDuzriHTKtPW/Jz6aGExWpB4R54lgCpDQBy7c\n" +
                    "/IGTbYHt9Unjchl89UBws4YPLfmOLFhVwr7bQvRJFLBwTHwCoN/8yZJJBhTNldb/\n" +
                    "HKYe/pOo2gMaz6T8MC5NJeJCgsvvZXjgpVfEUzh9AoGAUZ0MUVoJqYkvI0dWh3od\n" +
                    "TMUyGqe1+EW1uo19YCTyrMiMKPmLXjY+k6YyeJ3HtB83G17xhSbrrpKGc0A9uKYO\n" +
                    "965KfKeGAHvIVCMq9HDcZNClU+2dU/84ND20NMpKSrPpr/lpMyNzSRIr8yJcMLZh\n" +
                    "7jNmfJuXzN/62VIkxTp3q8o=";

//    public static final String RSA_PRIVATE =
//            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJJL8OLB0J/9pmzHFxpwOeigamHd3Yk6PkZdaL6reDOdlq5mOQ0/xIqXcnaWI/Q7qtT9j/b34hR74ZMyEw4Um5mbWG0C0qK7l6RbQaUExbF/gU+RiVCQ8TQW1qgw/eBh+H47Aj58hGulbfJKfeZJydzpnvTSdT9VitGR9xIJtKdHAgMBAAECgYBMmbzATnE5RGu+qyP6sOZxWoU5Rx03PCrdVw2AQHIIvKvoFxgqSshTNOc3Fngu6osRSM73pmVXCmJbWy3FAp9Rqg2FZfQoX+ds4cnj3QVpeILw6b2Sr0rI2OBkbXGFre/crM+JcjYBAkV7pnwcWRH3EyOvzLUqKs5qEkOycxTi8QJBAOUFVS8ipCnp7Qaynig6PcfJC0JP4GxpFmQu0w1OrmlzP/zezUfRwihTx1NPssJm9HD7KNiBDlgFj0PQJkGbB18CQQCjh90kBAoloAsCxe/qD4w7lbre75P16Kicb+K0FCeJsZrdXpApFhlDo60zPNUJEPph9HFptZfNBE8I8dIesHEZAkEAxe4V8Oa/ennxoBg/GAU936yhTm46R3eLIopVXOrjUb+JTcJBKBDg/Hlri1UV6W2RVRO7+WGQRAKKDtGWPpz9gQJAImZAFIVtBQEnj8vHbfsbSqVyi9blzwLEBTRcAfmDX6mmpA5yUNI/OkVB99dCEQgrQ1PCT7RNXGkdnwoPYzlGcQJBAJQQrWM8SxovyqcN7Md2wRvIjA1Ny7OJGSR8y+0eu/D0GydQbUj1rNdPX5CLNFVwvcgMwkLNUD+u+JSol5+PQHk=";

    
    // 银通支付（RSA）公钥
    public static final String RSA_YT_PUBLIC =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB";

    public static String getSignKey(String oid_partner, String sign_type){
        if (sign_type.equals("MD5")){
            return MD5_KEY;
        }
        else {
            return RSA_PRIVATE;
        }
    }
}
