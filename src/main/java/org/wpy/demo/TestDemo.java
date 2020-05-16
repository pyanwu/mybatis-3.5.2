/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.wpy.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.wpy.demo.dao.UserMapper;
import org.wpy.demo.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDemo {


    public static SqlSessionFactory getSqlSessionFactory(){
        String resource="mybatis-config.xml";
        InputStream inputStream= null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    public void testt(){
        InvocationHandler ih;
    }
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        User user=new User();
//        user.setName("张三");
//        user.setAge(19);
//        user.setGender("男");
//        int insert = userMapper.insert(user);
//        System.out.println(insert);
    }

    @Test
    public void testUser(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user=new User();
        user.setId(3);
        user.setName("张三");
        user.setAge(19);
        user.setGender("男");
        int insert = userMapper.insert(user);
        sqlSession.commit();
        System.out.println(insert);
    }

    @Test
    public void testUser2(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
      //  User user = userMapper.selectByPrimaryKey(3);
        User user=userMapper.selectByName("李四");
        System.out.println(user.getName());
        System.out.println(user.getGender());
        System.out.println(user.getAge());
    }

    @Test
    public void testUser3(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
          User user = userMapper.selectByPrimaryKey(3);
      //    User user=userMapper.selectByPrimaryKeyAndName(2,"zhangsan");
        List<User> users = userMapper.selectUsersByNames(Arrays.asList("张三", "李四"));
        System.out.println("**************");
        users.forEach((us)-> System.out.println(us));
        //     User user=userMapper.selectByName("李四");
    }

    @Test
    public void test4(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByName("张三");
        System.out.println(user);
    }

    @Test
    public void test5(){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> pmap=new HashMap<String,Object>();
        pmap.put("names",Arrays.asList("张三", "李四"));
        pmap.put("flag","a");
        List<User> userList=userMapper.selectByMap(pmap);
        userList.forEach(u->System.out.println(u));
    }
}
