package com.example.dwz.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * @author iumyxF
 * @description: 网址工具类
 * @date 2024/4/17 14:17
 */
public class UrlUtils {

    /**
     * 生成短链
     * 这里只说明两个比较清晰的算法
     * 算法1:
     * 1. 使用MurmurHash算法(效率高)
     * 2. 使用重哈希解决哈希冲突(在原有长链后面加上固定的特殊字符，后续拿出长链时再将其去掉),如果还发生冲突就继续重哈希(微乎其微的机率)
     * 3. 最后将10进制的哈希值转成 62 进制的合法网址字符(Base62)
     *
     * @param url
     * @return
     */
    public static String createShortUrl(String url) {
        HashFunction hashFunction = Hashing.murmur3_32_fixed();
        HashCode hashCode = hashFunction.hashString(url, StandardCharsets.UTF_8);

        //这里查询该hashCode是否有重复,如果有则加上指定字符传再加密一次

        //用guava工具类第三步都省略了

        return hashCode.toString();
    }
}
