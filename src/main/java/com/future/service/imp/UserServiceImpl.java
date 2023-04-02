package com.future.service.imp;

import com.future.entity.Users;
import com.future.mapper.UserMapper;
import com.future.service.RelationshipsService;
import com.future.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    RelationshipsService relationshipsService;

    /**
     * 查找用户名是否存在
     *
     * @param name 前端输入的用户名
     * @return Users对象
     */
    @Override
    public Users isNameExist(String name) {
        return userMapper.findUser(name);
    }

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return 添加结果
     */
    @Override
    public Integer addUser(Users user) {
        return userMapper.addUser(user);
    }

    /**
     * 通过discriminator查询用户
     *
     * @param discriminator 用户discriminator
     * @return Users对象
     */
    @Override
    public Users findUserByDiscriminator(String discriminator) {
        return userMapper.findUserByDiscriminator(discriminator);
    }

    /**
     * 添加好友
     *
     * @param discriminator  当前用户discriminator
     * @param discriminator1 好友discriminator
     * @return
     */
    @Override
    public Users addFriends(String discriminator, String discriminator1) {
        Users userByDiscriminator1 = userMapper.findUserByDiscriminator(discriminator1);//判断好友是否存在
        if (userByDiscriminator1 != null) { //好友存在
            Integer id = userByDiscriminator1.getId();//获取好友id
            Users userByDiscriminator = userMapper.findUserByDiscriminator(discriminator);//获取当前用户
            if (userByDiscriminator != null) { //当前用户存在
                Integer id1 = userByDiscriminator.getId();//获取当前用户id
                /*
                 * 添加好友方法
                 * @param userId 用户id
                 * @param friendId 好友id
                 * @param status 状态
                 * @param createdAt 创建时间
                 * @return Boolean
                 */
                Boolean aBoolean = relationshipsService.addFriend(id1, id, "1", new Timestamp(System.currentTimeMillis()));//添加好友 ; new Timestamp(System.currentTimeMillis()) 获取当前时间
                if (aBoolean) { //添加成功
                    return userByDiscriminator1;
                } else { //添加失败
                    return null;
                }
            } else { //当前用户不存在
                return null;
            }
        }
        return null;
    }

    /**
     * 查看用户名是否重复
     *
     * @param name 用户名
     * @return Boolean
     */
    @Override
    public Boolean isNameRepeat(String name) {
        Users user = userMapper.findUser(name);
        return user != null; //如果user不为空，说明用户名重复
    }
}
