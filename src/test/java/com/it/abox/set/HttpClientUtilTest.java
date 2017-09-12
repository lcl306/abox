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
		uploadTemplate("172.16.191.137");
		uploadTemplate("172.16.191.135");
		setText("172.16.191.137");
	}
	
	public static void uploadTemplate(String ip){
		System.out.println("----------------setup template: "+ip+ "--------------");
		ip +=":8080";
		SetUtil.setUrl(ip);
		SetUtil.upload("C:/文档/大福/项目/上海开能/09_现场调试/template.xml");
		SetUtil.reload();
		setText(ip);
	}
	
	public static void setText(String ip){
		Map<String, String> comps = new HashMap<>();
		comps.put("textbox11.text", "入出库站台"+ip);
		comps.put("textbox11.align", "middle");
		comps.put("textbox21.text", " 人员:");
		comps.put("textbox22.text", "上海开能-技术部-某某某");
		comps.put("textbox31.text", " 单据:");
		comps.put("textbox32.text", "UWTN050174");
		comps.put("textbox41.text", " 来源:");
		comps.put("textbox42.text", "1437091");
		comps.put("textbox51.text", " 托盘:");
		comps.put("textbox52.text", "100076");
		comps.put("textbox53.text", " 方式:");
		comps.put("textbox54.text", "拣选2");
		comps.put("textbox61.text", " 物料:");
		comps.put("textbox62.text", "[1]16000001CMCk0001-1186.6(公斤) [3]16000001CMCk0003-86.7(公斤)");
		comps.put("textbox71.text", " 物料:");
		comps.put("textbox72.text", "[2]16000001CMCk0002-186.6(公斤)");
		comps.put("textbox81.text", " 收货:");
		comps.put("textbox82.text", "中国XXXXXXXXXXXX公司XXX部门XXX");
		comps.put("textbox91.text", " 类型:");
		comps.put("textbox92.text", "成品出库");
		SetUtil.set(comps);
		/*SetUtil.set("textbox11", "text", "入出库站台"+ip);
		SetUtil.set("textbox11", "align", "middle");
		SetUtil.set("textbox21", "text", " 人员:");
		SetUtil.set("textbox22", "text", "上海开能-技术部-某某某");
		SetUtil.set("textbox31", "text", " 单据:");
		SetUtil.set("textbox32", "text", "UWTN050174");
		SetUtil.set("textbox41", "text", " 来源:");
		SetUtil.set("textbox42", "text", "1437091");
		SetUtil.set("textbox51", "text", " 托盘:");
		SetUtil.set("textbox52", "text", "100076");
		SetUtil.set("textbox53", "text", " 方式:");
		SetUtil.set("textbox54", "text", "拣选2");
		SetUtil.set("textbox61", "text", " 物料:");
		SetUtil.set("textbox62", "text", "[1]16000001CMCk0001-1186.6(公斤) [3]16000001CMCk0003-86.7(公斤)");
		SetUtil.set("textbox71", "text", " 物料:");
		SetUtil.set("textbox72", "text", "[2]16000001CMCk0002-186.6(公斤)");
		SetUtil.set("textbox81", "text", " 收货:");
		SetUtil.set("textbox82", "text", "中国XXXXXXXXXXXX公司XXX部门XXX");
		SetUtil.set("textbox91", "text", " 类型:");
		SetUtil.set("textbox92", "text", "成品出库");*/
		
	}
	
	public static void upload(){
		String url = "http://localhost:8080/abox/upload";
		Map<String, String> maps = new HashMap<String, String>();   
        Map<String, File> files = new HashMap<>();
        maps.put("filename", "201511_copy.pdf");
        files.put("file", new File("C:/文档/裕日/上海裕日制度/出張費用規則_201511.pdf"));
        byte[] bs = HttpClientUtil.sendHttpPost(url, maps, files);
        System.out.println(new String(bs));
	}
	
	public static void upload2()throws Exception{
		String url = "http://localhost:8080/abox/upload2";
		File file1 = new File("C:/文档/裕日/上海裕日制度/出張費用規則_201511.pdf");
		File file2 = new File("C:/文档/裕日/上海裕日制度/入社しらせ2.0--2017.pdf");
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
