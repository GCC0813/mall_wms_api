package com.mall.wms.service;

import com.mall.wms.entity.UserEntity;

import java.util.*;

public class Test1 {

    public static void main(String[] args) {
        System.out.println(test());
    }

    static int test() {
        int x = 1;
        try {
            return x;
        } finally {
            ++x;
        }

    }
}
