package com.mavenweb.sample.SpringMVCProject.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.lang.*;
import java.io.*;
import java.net.*;

@Controller
public class HomeController {

	@RequestMapping({ "/", "/home" })
	public ModelAndView test(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/text", method = RequestMethod.GET)
	public ModelAndView textGet() {
		ModelAndView mv = new ModelAndView("text");
		mv.addObject("sendMessage", "");
		return mv;
	}

	@RequestMapping(value = "/text", method = RequestMethod.POST)
	public ModelAndView textPost(@RequestParam("textentered") String valueOne) {
		// System.out.println("Text Entered is "+valueOne);
		ModelAndView mv = new ModelAndView("text");
		try {
			Socket skt = new Socket("127.0.0.1", 5000);
			PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
			System.out.print("Sending value text and then: '" + valueOne + "'\n");
			out.print("Text0");
			out.flush();
			out.print(valueOne);
			out.flush();
			out.close();
			skt.close();
			mv.addObject("sendMessage", "Text has been send successfully");
		} catch (Exception e) {
			// System.out.print("Whoops! It didn't work!\n"+e.getMessage());
			mv.addObject("sendMessage", "There was a problem connecting to the raspberry pi");
		}

		return mv;
	}

	@RequestMapping(value = "/audio", method = RequestMethod.GET)
	public ModelAndView audioGet() {
		ModelAndView mv = new ModelAndView("audio");
		mv.addObject("sendMessage", "");
		return mv;
	}

	@RequestMapping(value = "/audio", method = RequestMethod.POST, params = "stop")
	public ModelAndView audioStop() {
		// System.out.println("filename is "+mFile.getSize());
			ModelAndView mv = new ModelAndView("audio");
			try {
				Socket skt = new Socket("127.0.0.1", 5000);
				PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
				out.print("Stop0");
				out.flush();
				out.close();
				skt.close();
				mv.addObject("sendMessage", "");
			} catch (Exception e) {
				// System.out.print("Whoops! It didn't work!\n"+e.getMessage());
				mv.addObject("sendMessage", "There was a problem connecting to the raspberry pi ");
			}
			return mv;

	}

	@RequestMapping(value = "/audio", method = RequestMethod.POST,params = "send")
	public ModelAndView audioPost(@RequestParam("userFile") MultipartFile mFile) {
		// System.out.println("filename is "+mFile.getSize());

		ModelAndView mv = new ModelAndView("audio");
		try {
			Socket socket = null;
			String host = "127.0.0.1";

			socket = new Socket(host, 5000);

			File file = convert(mFile);
			// Get the size of the file
			long length = file.length();
			InputStream in = new FileInputStream(file);
			OutputStream out = socket.getOutputStream();

			// PrintWriter printwriterOut = new PrintWriter(out, true);
			// System.out.print("Sending value text and then: '" + valueOne +
			// "'\n");
			// printwriterOut.print("Audio");
			// printwriterOut.close();

			int count;
			byte[] buffer = new byte[4096]; // or 4096, or more

			String test = "Audio";
			buffer = test.getBytes();
			out.write(buffer, 0, 5);

			while ((count = in.read(buffer)) > 0) {
				// System.out.println("Sending a part");
				out.write(buffer, 0, count);
			}
			out.close();
			in.close();
			socket.close();
			mv.addObject("sendMessage", "Audio file has been send successfully");
		} catch (IOException e) {
			mv.addObject("sendMessage", "There was a problem connecting to the raspberry pi");
		}

		return mv;

	}

	@RequestMapping(value = "/textaudio", method = RequestMethod.GET)
	public ModelAndView textAudioGet() {
		ModelAndView mv = new ModelAndView("textaudio");
		mv.addObject("sendMessage", "");
		return mv;
	}

	@RequestMapping(value = "/textaudio", method = RequestMethod.POST)
	public ModelAndView textAudioPost(@RequestParam("textentered") String valueOne) {
		System.out.println("Text Entered is " + valueOne);
		ModelAndView mv = new ModelAndView("textaudio");
		try {
			Socket skt = new Socket("127.0.0.1", 5000);
			PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
			System.out.print("Sending value text and then: '" + valueOne + "'\n");
			System.out.println("Sending string txosp");
			out.print("Txosp");
			out.flush();
			out.print(valueOne);
			out.flush();
			out.close();
			skt.close();
			mv.addObject("sendMessage", "Text has been send successfully to speak out");
		} catch (Exception e) {
			System.out.print("Whoops! It didn't work!\n" + e.getMessage());
			mv.addObject("sendMessage", "There was a problem connecting to the raspberry pi");
		}

		return mv;
	}

	/**
	 * The file uploaded reaches the controller in Multipart file format. This
	 * function is in order to convert a multipart file to a normal java file.
	 * 
	 * @param file
	 *            --> the multipartfile that is received from the webpage
	 * @return --> the normal java file
	 */
	public File convert(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {

		}
		return convFile;
	}
}
