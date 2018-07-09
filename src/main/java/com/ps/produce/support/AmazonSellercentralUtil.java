package com.ps.produce.support;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.ps.produce.support.pair.AmazonSite;
import com.ps.produce.support.utils.HttpClientUtil;
/**
 * 亚马逊fba费用工具类
 * @author yuyanzheng
 * 
 * 2018年1月23日
 * 
 * https://sellercentral.amazon.com/fba/profitabilitycalculator/index
 */
public class AmazonSellercentralUtil {
	
	/**
	 * 获取产品信息
	 * @param searchKey
	 * @param site
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getProductInfo(String searchKey,AmazonSite site){
		Map<String, String> params = Maps.newHashMap();
		params.put("searchKey", searchKey);
		params.put("language", site.getLanguage());
//		params.put("profitcalcToken", "R1oi2sbMKrgWCy5NmuXDDHW4YDYj3D");
		String content = HttpClientUtil.doGet(site.getSellercentralUrl()+"/productmatches", params);
		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(content, Map.class);
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("data");
		return list;
	}
	
	/**
	 * 通过产品信息获取fba费用
	 * @param productInfo
	 * @param site
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getafnfee(Map<String, Object> productInfo,AmazonSite site) {
		Gson gson = new Gson();
		Map<String, Object> params = Maps.newHashMap();
		params.put("afnPriceStr", "0");
		params.put("currency", site.getCurrency());
		params.put("futureFeeDate", "0");
		params.put("hasFutureFee", false);
		params.put("hasTaxPage", true);
		params.put("marketPlaceId", site.getMarketplaceId());
		params.put("mfnPriceStr", "0");
		params.put("mfnShippingPriceStr", "0");
		params.put("productInfoMapping", productInfo);
		String content = HttpClientUtil.doPostJson(site.getSellercentralUrl()+"/getafnfee", gson.toJson(params));
		return gson.fromJson(content, Map.class);
	}
	
	public static void main(String[] args) {
		getafnfee(getProductInfo("B01MU1GBLL", AmazonSite.US).get(0),AmazonSite.US);
	}
}
