package com.example.security.config.satoken;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.util.SaFoxUtil;
import com.example.security.utils.RedisCache;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: Sa -Token持久层接口(本地RedisCache实现)
 * @Date 2023 /2/25 10:58
 * @author iumyxF
 */
@Repository
public class SecuritySaTokenDao implements SaTokenDao {

    @Resource
    private RedisCache redisCache;

    /**
     * 获取value
     *
     * @param key 键名称
     * @return 缓存键值对应的数据
     */
    @Override
    public String get(String key) {
        return redisCache.getCacheObject(key);
    }

    /**
     * 写入value，设置存活时间（单位秒）
     *
     * @param key     键名称
     * @param value   值
     * @param timeout 过期时间(值大于0时限时存储，值=-1时永久存储，值=0或小于-2时不存储)
     */
    @Override
    public void set(String key, String value, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        // 判断是否为永不过期
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            redisCache.setCacheObject(key, value);
        } else {
            redisCache.setCacheObject(key, value, (int) timeout, TimeUnit.SECONDS);
        }
    }

    /**
     * 修改键值对，过期时间不变
     *
     * @param key   键名称
     * @param value 值
     */
    @Override
    public void update(String key, String value) {
        long expire = getTimeout(key);
        // -2 = 无此键
        if (expire == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.set(key, value, expire);
    }

    /**
     * 删除value
     *
     * @param key 键名称
     */
    @Override
    public void delete(String key) {
        redisCache.deleteObject(key);
    }

    /**
     * 获取存活时间（单位秒）
     *
     * @param key 指定key
     * @return value存活时间
     */
    @Override
    public long getTimeout(String key) {
        long timeout = redisCache.getExpire(key);
        return timeout < 0 ? timeout : timeout / 1000;
    }

    /**
     * 修改Value的剩余存活时间 (单位秒)
     *
     * @param key     指定key
     * @param timeout 过期时间
     */
    @Override
    public void updateTimeout(String key, long timeout) {
        // 判断是否想要设置为永久
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            long expire = getTimeout(key);
            if (expire == SaTokenDao.NEVER_EXPIRE) {
                // 如果其已经被设置为永久，则不作任何处理
            } else {
                // 如果尚未被设置为永久，那么再次set一次
                this.set(key, this.get(key), timeout);
            }
            return;
        }
        redisCache.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取Obj
     *
     * @param key 键名称
     * @return 缓存对象
     */
    @Override
    public Object getObject(String key) {
        return redisCache.getCacheObject(key);
    }

    /**
     * 写入缓存对象（单位秒）
     *
     * @param key     键名称
     * @param object  值
     * @param timeout 存活时间 (值大于0时限时存储，值=-1时永久存储，值=0或小于-2时不存储)
     */
    @Override
    public void setObject(String key, Object object, long timeout) {
        if (timeout == 0 || timeout <= SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        // 判断是否为永不过期
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            redisCache.setCacheObject(key, object);
        } else {
            redisCache.setCacheObject(key, object, (int) timeout, TimeUnit.SECONDS);
        }
    }

    /**
     * 更新缓存对象，不修改过期时间
     *
     * @param key    键名称
     * @param object 值
     */
    @Override
    public void updateObject(String key, Object object) {
        long expire = getObjectTimeout(key);
        // -2 = 无此键
        if (expire == SaTokenDao.NOT_VALUE_EXPIRE) {
            return;
        }
        this.setObject(key, object, expire);
    }

    /**
     * 删除缓存对象
     *
     * @param key 键名称
     */
    @Override
    public void deleteObject(String key) {
        redisCache.deleteObject(key);
    }

    /**
     * 获取缓存对象存活剩余时间
     *
     * @param key 指定key
     * @return
     */
    @Override
    public long getObjectTimeout(String key) {
        long timeout = redisCache.getExpire(key);
        return timeout < 0 ? timeout : timeout / 1000;
    }

    /**
     * 更新缓存时间
     *
     * @param key     指定key
     * @param timeout 过期时间
     */
    @Override
    public void updateObjectTimeout(String key, long timeout) {
        // 判断是否想要设置为永久
        if (timeout == SaTokenDao.NEVER_EXPIRE) {
            long expire = getObjectTimeout(key);
            if (expire == SaTokenDao.NEVER_EXPIRE) {
                // 如果其已经被设置为永久，则不作任何处理
            } else {
                // 如果尚未被设置为永久，那么再次set一次
                this.setObject(key, this.getObject(key), timeout);
            }
            return;
        }
        redisCache.expire(key, timeout);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param prefix   前缀
     * @param keyword  关键字
     * @param start    开始处索引
     * @param size     获取数量  (-1代表从start处一直取到末尾)
     * @param sortType 排序类型（true=正序，false=反序）
     * @return
     */
    @Override
    public List<String> searchData(String prefix, String keyword, int start, int size, boolean sortType) {
        Collection<String> keys = redisCache.keys(prefix + "*" + keyword + "*");
        List<String> list = new ArrayList<>(keys);
        return SaFoxUtil.searchList(list, start, size, sortType);
    }
}
