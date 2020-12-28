package com.fuzs;

import java.util.concurrent.atomic.AtomicReference;

public class UseAtomicReference {
    static AtomicReference<UserInfo> userRef = new AtomicReference<UserInfo>();

    public static void main(String[] args){
        UserInfo user = new UserInfo("zhangsan",18);
        userRef.set(user);

        UserInfo updateUser = new UserInfo("lisi",21);
        userRef.compareAndSet(user, updateUser);

        System.out.println(userRef.get().getName());
        System.out.println(userRef.get().getAge());

        System.out.println(user.getName());
        System.out.println(user.getAge());
    }


    static class UserInfo{
        private String name;
        private int age;
        public UserInfo(String name,int age){
            this.name = name;
            this.age = age;
        }
        public String getName(){
            return this.name;
        }
        public int  getAge(){
            return this.age;
        }
    }
}