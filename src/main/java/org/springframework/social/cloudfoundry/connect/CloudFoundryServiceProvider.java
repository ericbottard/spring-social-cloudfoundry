/**
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.cloudfoundry.connect;

import java.net.MalformedURLException;
import java.net.URL;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.CloudFoundryOperations;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * Spring Social Service Provider for any OAuth2 CloudFoundry compatible cloud.
 * 
 * @author Eric Bottard
 * 
 */
public class CloudFoundryServiceProvider extends
		AbstractOAuth2ServiceProvider<CloudFoundryOperations> {

	/**
	 * Where the CloudController REST API resides. Default is
	 * http://api.cloudfoundry.com.
	 */
	private final URL apiURL;

	private final static URL DEFAULT_API_URL;
	static {
		URL temp = null;
		try {
			temp = new URL("http://api.cloudfoundry.com");
		} catch (MalformedURLException wonthappen) {
			// Come on, I know how to write a URL...
		}
		DEFAULT_API_URL = temp;
	}

	/**
	 * Build a new ServiceProvider against VMware's CloudFoundry.com service.
	 * 
	 * @param clientId
	 *            OAuth2 clientId for your app
	 * @param clientSecret
	 *            OAuth2 clientSecret for your app
	 */
	public CloudFoundryServiceProvider(String clientId, String clientSecret) {
		super(new OAuth2Template(clientId, clientSecret,
				"https://uaa.cloudfoundry.com/oauth/authorize",
				"https://uaa.cloudfoundry.com/oauth/token"));
		this.apiURL = DEFAULT_API_URL;
	}

	/**
	 * Build a new ServiceProvider against a UAA located at {@code uaaRootURL},
	 * and CloudController REST API located at {@code apiURL}.
	 */
	public CloudFoundryServiceProvider(String clientId, String clientSecret,
			String uaaRootURL, URL apiURL) {
		super(new OAuth2Template(clientId, clientSecret, uaaRootURL
				+ "/oauth/authorize", uaaRootURL + "/oauth/token"));
		this.apiURL = apiURL;
	}

	/**
	 * Build a new ServiceProvider with full custom URL mapping.
	 */
	public CloudFoundryServiceProvider(String clientId, String clientSecret,
			String authURL, String tokenURL, URL apiURL) {
		super(new OAuth2Template(clientId, clientSecret, authURL, tokenURL));
		this.apiURL = apiURL;
	}

	@Override
	public CloudFoundryOperations getApi(String accessToken) {
		return new CloudFoundryClient(new CloudCredentials(accessToken), apiURL);
	}

}
