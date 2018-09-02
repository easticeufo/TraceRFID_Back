package com.madongfang.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.madongfang.util.TcpUtil.Callback;

@Service
public class TcpService implements Callback {

	@Override
	public byte[] dataProcess(byte[] receiveDatas) {
		logger.debug("receiveDatas.length={},receiveDatas={}", receiveDatas.length, Arrays.toString(receiveDatas));
		
		byte[] sendDatas = "error".getBytes();
		
		String[] recvStrs = new String(receiveDatas).split("#");
		
		if (recvStrs.length < 1)
		{
			logger.warn("receiveDatas format error");
			return sendDatas;
		}
		
		String cmd = recvStrs[0];
		String[] args = new String[recvStrs.length - 1];
		for (int i = 0; i < args.length; i++)
		{
			args[i] = recvStrs[i + 1];
		}
		
		switch (cmd) {
		default:
			logger.warn("unknow command:cmd={}", cmd);
			break;
		}
		
		return sendDatas;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

}
