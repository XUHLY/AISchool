package cn.org.aischool.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 * MP3转PCM Java方式实现
 * @author huangliyang
 * @date 2019/01/30
 */
public class Mp3ToPcmUtil {
	/**
	 * MP3转换PCM文件方法
	 * @param mp3FilePath 原始文件路径
	 * @param pcmFilePath 转换文件的保存路径
	 * @throws Exception 
	 */
	public static void mp3ToPcm(String mp3FilePath,String pcmFilePath) throws Exception{
		File mp3 = new File(mp3FilePath);
		File pcm = new File(pcmFilePath);
		//原MP3文件转AudioInputStream
		AudioInputStream mp3audioStream = AudioSystem.getAudioInputStream(mp3);
		//将AudioInputStream MP3文件 转换为PCM AudioInputStream
		AudioInputStream pcmaudioStream = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED, mp3audioStream);
		//准备转换的流输出到OutputStream
		OutputStream os = new FileOutputStream(pcm);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead=pcmaudioStream.read(buffer, 0, 8192))!=-1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		pcmaudioStream.close();
	}
}