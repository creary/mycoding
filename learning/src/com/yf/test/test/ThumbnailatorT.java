package com.yf.test.test;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

import org.junit.Test;

public class ThumbnailatorT {

	@Test
	public void thumbTest(){

		try {
			Thumbnails.of("F:\\website\\sourceforget\\code\\learning\\WebContent\\images\\thumbnailator\\feiji.jpg").size(500, 100).toFile(new File("D:\\test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		};
		
	}
}
