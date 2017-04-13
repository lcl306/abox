package com.it.abox.set;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.it.abox.util.HttpClientUtil;
import com.it.abox.util.HttpClientUtil.InputStreamPost;

public class HttpClientUtilTest {
	
	public static void main(String[] args)throws Exception{
		String url = "http://localhost:8080/abox/upload2";
		File file1 = new File("C:/文档/上海裕日制度/出張費用規則_201511.pdf");
		File file2 = new File("C:/文档/上海裕日制度/入社しらせ2.0--2017.pdf");
		FileInputStream in = new FileInputStream(file1);
		FileInputStream in2 = new FileInputStream(file2);
		InputStreamPost p1 = new InputStreamPost(file1.getName(), in);
		InputStreamPost p2 = new InputStreamPost(file2.getName(), in2);
		List<InputStreamPost> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		Map<String, String> params = new HashMap<String, String>();
		params.put("param1", "出張費用規則_201511");
		params.put("param2", "入社しらせ2.0--2017");
		HttpClientUtil.sendHttpInputStream(url, params, list);
		in.close();
		in2.close();
	}

}
