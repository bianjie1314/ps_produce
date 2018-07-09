package com.ps.produce.support.utils;

import java.util.UUID;

public class UUidUtil {
	public static String getUuid() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
}
