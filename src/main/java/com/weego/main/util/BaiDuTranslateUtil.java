package com.weego.main.util;

import java.net.URLDecoder;
import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class BaiDuTranslateUtil {
	private static Logger logger = LogManager.getLogger(BaiDuTranslateUtil.class);
	
	private static final String UTF8 = "utf-8";
	
	//申请者开发者id，实际使用时请修改成开发者自己的appid
	private static final String appId = "20160307000014863";

	//申请成功后的证书token，实际使用时请修改成开发者自己的token
	private static final String token = "BqCVXUjo2I9FmbjqUsWj";

	private static final String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";

	//随机数，用于生成md5值，开发者使用时请激活下边第四行代码
	private static final Random random = new Random();
	
	public static String translate(String content, String from, String to) throws Exception {
		//用于md5加密
		int salt = random.nextInt(10000);

		// 对 appId + 源文 + 随机数 + token 计算 md5 值
		StringBuilder md5String = new StringBuilder();
		md5String.append(appId).append(content).append(salt).append(token);
		String md5 = DigestUtils.md5Hex(md5String.toString());
		String param = "q=" + content + "&from=" + from + "&to=" + to + "&appid=" + appId +
					   "&salt=" + String.valueOf(salt) + "&sign=" + md5;
		String result = HttpUtil.sendGet(url, param);

		//转化为json对象
		JSONObject resultJson = JSONObject.parseObject(result);

		//开发者自行处理错误，本示例失败返回为null
		try {
			String error_code = resultJson.getString("error_code");
			if (error_code != null) {
				logger.info("出错代码:" + error_code);
				logger.info("出错信息:" + resultJson.getString("error_msg"));
				return null;
			}
		} catch (Exception e) {
			logger.info("百度翻译出错了!");
			e.printStackTrace();
		}

		//获取返回翻译结果
		JSONArray array = (JSONArray) resultJson.get("trans_result");
		JSONObject dst = (JSONObject) array.get(0);
		String text = dst.getString("dst");
		text = URLDecoder.decode(text, UTF8);
		return text;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(translate("这是一个测试","zh","en"));
	}
}
