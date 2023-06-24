package com.my.test.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author dayuqichengbao
 * @version 创建时间 2023/6/23 22:14
 */
@Component
public class DataOperationInterceptor implements MetaObjectHandler {
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";


    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        if (UPDATE_TIME.equals(fieldName)) {
            if (Objects.nonNull(fieldVal)) {
                metaObject.setValue(fieldName, fieldVal.get());
            }
        } else if (getFieldValByName(fieldName, metaObject) == null) {
            setFieldValByName(fieldName, fieldVal.get(), metaObject);
        }
        return this;

    }


}
