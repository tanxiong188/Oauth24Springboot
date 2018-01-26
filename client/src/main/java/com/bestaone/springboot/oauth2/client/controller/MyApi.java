package com.bestaone.springboot.oauth2.client.controller;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.oauth.OAuth20Service;

public class MyApi extends DefaultApi20 {

    protected MyApi() {
    }

    private static class InstanceHolder {
        private static final MyApi INSTANCE = new MyApi();
    }

    public static MyApi instance() {
        return InstanceHolder.INSTANCE;
    }

	@Override
	public String getAccessTokenEndpoint() {
		return "http://localhost:8081/oauth/token";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return "http://localhost:8081/oauth/authorize";
	}
	
    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenJsonExtractor.instance();
    }

    @Override
    public OAuth20Service createService(OAuthConfig config) {
        return new MyOAuth20Service(this, config);
    }
}
