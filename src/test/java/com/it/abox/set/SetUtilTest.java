package com.it.abox.set;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class SetUtilTest {
	
	@Test
	public void changeTemplate(){
		SetUtil.changeTemplate("template123");
		SetUtil.debug(true);
	}
	
	@Test
	public void setTextbox(){
		SetUtil.set("textbox112", "text", "颜色");			  //设置内容
		SetUtil.set("textbox112", "color", "0,0,0");        //设置字体颜色
		SetUtil.set("textbox112", "bkcolor", "255,255,0"); //设置背景色
		SetUtil.set("textbox112", "blink", "0.5");         //设置闪烁
		//SetUtil.set("textbox112", "disable", "True");     //设置是否无效
		//SetUtil.set("textbox112", "visible", "False");    //设置是否可见
	}
	
	@Test
	public void setImage(){
		SetUtil.set("leftImage", "imagefile", "sl95734016877.jpg");
	}
	
	@Test
	public void snapshot(){
		SetUtil.snapshot("C:/work/temp");
	}
	
	@Test
	public void info(){
		SetUtil.info();
	}
	
	@Test
	public void setBar(){
		SetUtil.set("bar", "barcolor", "0,255,0");
		SetUtil.set("bar", "text", "2/3");
		SetUtil.set("bar", "percentage", "66");
		SetUtil.debug(false);
	}
	
	@Test
	public void time(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			
			int i = 0;
			
			@Override
			public void run() {
				i = i+1;
				SetUtil.set("textbox123", "text", i+"");
			}
			
		}, 0, 100);
	}

}

