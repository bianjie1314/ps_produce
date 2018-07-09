package com.ps.produce.support.pair;

import org.apache.commons.lang3.StringUtils;

public enum AmazonSite {
	US("10000001",1,"http://www.amazon.com","ATVPDKIKX0DER","completion.amazon.com",1,"https://sellercentral.amazon.com/fba/profitabilitycalculator","en_US","USD"),
	UK("10000002",2,"http://www.amazon.co.uk","A1F83G8C2ARO7P","completion.amazon.co.uk",3,"https://sellercentral.amazon.co.uk/fba/profitabilitycalculator","en_GB","GBP"),
	CA("10000003",6,"http://www.amazon.ca","A2EUQ1WTGCTBG2","completion.amazon.com",7,"https://sellercentral.amazon.ca/fba/profitabilitycalculator","en_CA","CAD"),
	DE("10000004",3,"http://www.amazon.de","A1PA6795UKMFR9","completion.amazon.co.uk",4,"https://sellercentral.amazon.de/fba/profitabilitycalculator","de_DE","EUR"),
	FR("10000005",4,"http://www.amazon.fr","A13V1IB3VIYZZH","completion.amazon.co.uk",5,"https://sellercentral.amazon.fr/fba/profitabilitycalculator","fr_FR","EUR"),
	ES("10000006",9,"http://www.amazon.es","A1RKKUPIHCS9HS","completion.amazon.co.uk",44551,"https://sellercentral.amazon.es/fba/profitabilitycalculator","es_ES","EUR"),
	IT("10000007",8,"http://www.amazon.it","APJ6JRA9NG5V4","completion.amazon.co.uk",35691,"https://sellercentral.amazon.it/fba/profitabilitycalculator","it_IT","EUR"),
	JP("10000008",5,"http://www.amazon.co.jp","A1VC38T7YXB528","completion.amazon.co.jp",6,"https://sellercentral.amazon.co.jp/fba/profitabilitycalculator","ja_JP","JPY"),
	CN("10000009",7,"http://www.amazon.cn","AAHKV2X7AFYLW","completion.amazon.cn",3240,"","",""),
	MX("10000010",0,"http://www.amazon.com.mx","A1AM78C64UM0Y8","completion.amazon.com.mx",0,"https://sellercentral.amazon.com.mx/fba/profitabilitycalculator","es_MX","MXN");
	private String id;
	private String endpoint;
	private String marketplaceId;
	private int mkt;
	private String completion;
	private int keepaId;
	private String sellercentralUrl;
	private String language;
	private String currency;
	
	private AmazonSite(String id,int keepaId, String endpoint, String marketplaceId,String completion ,int mkt,String sellercentralUrl,String language,String currency) {
		this.id = id;
		this.endpoint=endpoint;
		this.marketplaceId=marketplaceId;
		this.mkt=mkt;
		this.completion=completion;
		this.keepaId=keepaId;
		this.sellercentralUrl = sellercentralUrl;
		this.language = language;
		this.currency = currency;
	}

	public static AmazonSite getSiteDefaultUS(String site) {
		for(AmazonSite a:AmazonSite.values()){
			if(a.name().equalsIgnoreCase(site))
				return a;
		}
		return AmazonSite.US;
	}
	
	public static AmazonSite getSiteDefaultNull(String site) {
		for(AmazonSite a:AmazonSite.values()){
			if(a.name().equalsIgnoreCase(site))
				return a;
		}
		return null;
	}

	public static AmazonSite getByEndpoint(String url) {
		for (AmazonSite amazonSite : AmazonSite.values()) {
			String endpoint = amazonSite.getEndpoint();
			if(url.startsWith("https")){
				url = url.replaceAll("https", "http");
			}
			if(url.contains(endpoint) ){
				return amazonSite;
			}
		}
		return null;
	}

	public static AmazonSite getByMarketplaceId(String id) {
		if(StringUtils.isBlank(id)){
			return AmazonSite.US;
		}
		for (AmazonSite amazonSite : AmazonSite.values()) {
			String a = amazonSite.getMarketplaceId();
			if(id.contains(a) ){
				return amazonSite;
			}
		}
		return AmazonSite.US;//Default US
	}
	
	public static AmazonSite getByMarketplaceIdNull(String marketplaceId) {
		for (AmazonSite amazonSite : AmazonSite.values()) {
			String a = amazonSite.getMarketplaceId();
			if(a.equals(marketplaceId) ){
				return amazonSite;
			}
		}
		return null;
	}
	
	public static AmazonSite getSiteDefaultUSByMarketplaceId(String marketplaceId) {
		for(AmazonSite a:AmazonSite.values()){
			if(a.getMarketplaceId().equalsIgnoreCase(marketplaceId))
				return a;
		}
		return AmazonSite.US;//Default US
	}
	
	public static String getEndpointByMarketplaceId(String marketplaceId) {
		for (AmazonSite amazonSite : AmazonSite.values()) {
			String mpId = amazonSite.getMarketplaceId();
			if(mpId.equals(marketplaceId) ){
				return amazonSite.getEndpoint();
			}
		}
		return null;
	}
	
	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getMarketplaceId() {
		return marketplaceId;
	}

	public void setMarketplaceId(String marketplaceId) {
		this.marketplaceId = marketplaceId;
	}

	public int getMkt() {
		return mkt;
	}

	public void setMkt(int mkt) {
		this.mkt = mkt;
	}

	public String getCompletion() {
		return completion;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
	}

	public int getKeepaId() {
		return keepaId;
	}

	public void setKeepaId(int keepaId) {
		this.keepaId = keepaId;
	}

	public String getSellercentralUrl() {
		return sellercentralUrl;
	}

	public void setSellercentralUrl(String sellercentralUrl) {
		this.sellercentralUrl = sellercentralUrl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
