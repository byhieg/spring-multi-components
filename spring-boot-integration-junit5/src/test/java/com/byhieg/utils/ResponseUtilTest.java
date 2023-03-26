package com.byhieg.utils;

import com.byhieg.entity.WebResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
class ResponseUtilTest {
	private Logger log = LoggerFactory.getLogger(NameUtilTest.class);
	

	@Test
	void OK() {
		WebResponse<String> actualResponse = ResponseUtil.OK("test success");
		WebResponse<String> expectedResponse = new WebResponse<>();
		expectedResponse.setCode(200);
		expectedResponse.setData("test success");
		expectedResponse.setStatus(ResponseUtil.SUCCESS_STATUS);
		expectedResponse.setErrorMsg(null);
		Assertions.assertEquals(expectedResponse.getCode(), actualResponse.getCode());
		Assertions.assertEquals(expectedResponse.getData(), actualResponse.getData());
		Assertions.assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
	}

	@Test
	void FAIL() {
		WebResponse<String> actualResponse = ResponseUtil.FAIL("test failed","service has error");
		WebResponse<String> expectedResponse = new WebResponse<>();
		expectedResponse.setCode(500);
		expectedResponse.setData("test failed");
		expectedResponse.setStatus(ResponseUtil.FAILURE_STATUS);
		expectedResponse.setErrorMsg("service has error");
		Assertions.assertEquals(expectedResponse.getCode(), actualResponse.getCode());
		Assertions.assertEquals(expectedResponse.getData(), actualResponse.getData());
		Assertions.assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
		Assertions.assertEquals(expectedResponse.getErrorMsg(), actualResponse.getErrorMsg());

	}
}