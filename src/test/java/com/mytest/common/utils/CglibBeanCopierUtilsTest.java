package com.mytest.common.utils;

import org.junit.Assert;
import org.junit.Test;

import com.mytest.model.OtherUser;
import com.mytest.model.UserInfo;
import net.sf.cglib.core.DebuggingClassWriter;

import static org.junit.Assert.*;

/**
 * @author liqingyu
 * @since 2018/06/05
 */
public class CglibBeanCopierUtilsTest {

    @Test
    public void copy() {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("1");
        userInfo.setEmail("liqingyu@mytest.com");
        userInfo.setMobile("13812345678");
        OtherUser copiedBean = new OtherUser();
        CglibBeanCopierUtils.copy(userInfo, copiedBean);
        Assert.assertEquals("1", copiedBean.getUserId());
        Assert.assertEquals("liqingyu@mytest.com", copiedBean.getEmail());
        Assert.assertEquals(null, copiedBean.getName());
    }
}