package com.it.abox.co;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.it.abox.util.CoUtil;

@Controller
public class FileCo {
	
	@RequestMapping("/abox/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response){
		List<MultipartFile> files = CoUtil.getFiles(request, "file");
		String filename = request.getParameter("filename");
		for(MultipartFile file : files){
			try {
				if(filename.trim().length()==0){
					filename = file.getOriginalFilename();
				}
				OutputStream out = new FileOutputStream("C:/work/temp/"+filename);
				CoUtil.pipe(file, out);
				out.close();
				response.getWriter().write("成功");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/abox/upload2")
	public void upload2(HttpServletRequest request, HttpServletResponse response){
		System.out.println(CoUtil.decode(request.getParameter("param1")));
		System.out.println(CoUtil.decode(request.getParameter("param2")));
		MultipartFile file1 = CoUtil.getFile(request, "出張費用規則_201511.pdf");
		MultipartFile file2 = CoUtil.getFile(request, "入社しらせ2.0--2017.pdf");
		//MultiValueMap<String, MultipartFile> map =((MultipartHttpServletRequest)request).getMultiFileMap();
		OutputStream out = null;
		OutputStream out2 = null;
		try {
			out = new FileOutputStream("C:/work/temp/"+CoUtil.decode(file1.getOriginalFilename()));
			CoUtil.pipe(file1, out);
			out.close();
			out2 = new FileOutputStream("C:/work/temp/"+CoUtil.decode(file2.getOriginalFilename()));
			CoUtil.pipe(file2, out2);
			out2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
