package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Guan
 * @date 2023/6/1 11:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int id;
    private String name;

}
