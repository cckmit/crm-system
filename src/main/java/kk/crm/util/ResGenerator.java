package kk.crm.util;

import kk.crm.entity.Res;
import kk.crm.entity.ResEntity;

public class ResGenerator {

    private static ResEntity getEntity(Res res, Object data) {
        return new ResEntity(res.getStatus(), res.getMsg(), data);
    }

    public static ResEntity success(){
        return getEntity(Res.SUCCESS,null);
    }

    public static ResEntity success(Object data){
        return getEntity(Res.SUCCESS,data);
    }

    public static ResEntity fail(){
        return getEntity(Res.FAIL,null);
    }

    public static ResEntity fail(Object data){
        return getEntity(Res.FAIL,data);
    }

    public static ResEntity error(){
        return getEntity(Res.ERROR,null);
    }

    public static ResEntity error(Object data){
        return getEntity(Res.ERROR,data);
    }
}
