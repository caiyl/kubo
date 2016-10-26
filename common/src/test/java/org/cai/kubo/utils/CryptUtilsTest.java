package org.cai.kubo.utils;

import junit.framework.TestCase;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CryptUtilsTest extends TestCase {

    public void testEncrypt(){
        assertNotNull( CryptUtils.getInstance().encrypt("helloWorld"));
    }

}
