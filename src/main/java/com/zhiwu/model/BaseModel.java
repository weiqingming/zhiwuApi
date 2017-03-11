package com.zhiwu.model;

import com.zhiwu.utils.JsonUtils;

import java.io.Serializable;

/**
 * Created by weiqingming on 2017/2/27.
 */
public class BaseModel implements Serializable{

    @Override
    public String toString(){
        return JsonUtils.getUtils().toJson(this);
    }
}
