package com.my.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.my.test.domain.Remark;
import com.my.test.domain.User;
import com.my.test.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dayuqichengbao
 * @version 创建时间 2023/6/23 11:19
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserMapper userMapper;

    @Test
    @Sql(statements = "delete from user",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("测试查询json数据")
    public void testJson() {
        Remark remark = new Remark();
        remark.setCity("beijing");
        remark.setCode("1002");

        User user = new User("TestJson", 20, "test@163.com", remark);
        userMapper.insert(user);
        User user2 = userMapper.selectById(user.getId());
        System.out.println("插入后数据：" + user2);

        //查询json
        String city = "bei";
        String code = "1002";
        List<User> list = new LambdaQueryChainWrapper<>(userMapper)
                .apply(StringUtils.isNotBlank(city),
                        "remark->'$.city' like CONCAT('%',{0},'%') ", city)
                .apply(StringUtils.isNotBlank(code),
                        "remark->'$.code'= {0} ", code)
                .list();
        System.out.println("查询结果：" + list.toString());

    }

    @Test
    @Sql(statements = "delete from user",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("测试自动填充updateTime")
    public void testUpdateAutoFill() throws InterruptedException {
        Remark remark = new Remark();
        remark.setCity("beijing");
        remark.setCode("1002");

        User user = new User("TestAutoFill", 20, "test@163.com", remark);

        userMapper.insert(user);
        Long userId = user.getId();
        userMapper.selectById(userId);
        System.out.println("插入数据：" + user);

        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId, userId);
        wrapper.set(User::getAge, 12);
        Thread.sleep(5 * 1000);

        User updateUser = new User();
        updateUser.setAge(20);//这里不会生效

        userMapper.update(new User(), wrapper);

        User user2 = userMapper.selectById(userId);
        System.out.println("更新后数据：" + user2);

    }



    @Test
    @Sql(statements = "delete from user",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("测试逻辑删除后插入唯一键数据")
    public void testLogicDelete(){

        Remark remark = new Remark();
        remark.setCity("beijing");
        remark.setCode("1002");

        String email = "test@163.com";

        User user = new User("TestLogicDelete", 20, email, remark);
        userMapper.insert(user);
        User user2 = userMapper.selectById(user.getId());
        System.out.println("插入后数据：" + user2);

        //没有删除情况下插入新数据
        try {
            User userDuplicate = new User("TestLogicDelete", 20, email, remark);
            userMapper.insert(userDuplicate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        userMapper.deleteById(user.getId());
        User user3 = userMapper.selectById(user.getId());
        System.out.println("逻辑删除后数据：" + user3);

        //重新插入唯一键数据

        User user4 = new User("TestLogicDelete", 20, email, remark);
        user4.setAge(20);
        userMapper.insert(user4);
        User user5 = userMapper.selectById(user4.getId());
        System.out.println("重新插入后数据：" + user5);
    }






    @Test
    public void testDelete() {
        User user = new User();
        user.setAge(10);
        user.setName("Testdel");
        userMapper.insert(user);
        userMapper.deleteById(user);
    }

    @Test
    public void DynamicQuery(){
        String  name = "Tom";
        Integer age = null;
        String email = null;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(name),"name",name)
                .eq(age!=null && age > 0 ,"age" ,age)
                .eq(StringUtils.isNotBlank(email),"email",email);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testAutoFill() throws InterruptedException {
        User user = new User();
        user.setAge(10);
        user.setName("Testdel");
        userMapper.insert(user);
        userMapper.selectById(user.getId());
        System.out.println("插入数据：" + user);

        user.setAge(12);
        Thread.sleep(5 * 1000);
        userMapper.updateById(user);
        User user2 = userMapper.selectById(user.getId());
        System.out.println("更新后数据：" + user2);

    }


}
