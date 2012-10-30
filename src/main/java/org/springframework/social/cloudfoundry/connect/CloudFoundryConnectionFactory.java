/*
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

import java.net.URL;

import org.springframework.social.cloudfoundry.api.CloudFoundry;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * Factory dedicated to the creation of CloudFoundry related connections.
 * 
 * @author Eric Bottard
 * 
 */
public class CloudFoundryConnectionFactory extends
		OAuth2ConnectionFactory<CloudFoundry> {

	/**
	 * Create a new connection factory targeting VMware's CloudFoundry platform
	 * at http://api.cloudfoundry.com.
	 * 
	 * @param clientId
	 *            the app client id
	 * @param clientSecret
	 *            the app client secret
	 */
	public CloudFoundryConnectionFactory(String clientId, String clientSecret) {
		super("cloudfoundry", new CloudFoundryServiceProvider(clientId,
				clientSecret), new CloudFoundryAdapter());
	}

	/**
	 * Create a new connection factory targeting any compatible CloudFoundry
	 * platform, whose UAA is located at {@code uaaRoot} and cloudcontroller is
	 * at {@code apiURL}.
	 * 
	 * @param clientId
	 *            the app client id
	 * @param clientSecret
	 *            the app client secret
	 * @param uaaRoot
	 *            base url of the uaa
	 * @param apiURL
	 *            location of the "cloud controller" REST API
	 * 
	 */
	public CloudFoundryConnectionFactory(String clientId, String clientSecret,
			String uaaRoot, URL apiURL) {
		super("cloudfoundry", new CloudFoundryServiceProvider(clientId,
				clientSecret, uaaRoot, apiURL), new CloudFoundryAdapter());
	}

	/**
	 * Create a new connection factory targeting any compatible CloudFoundry
	 * platform, with full customization of OAuth2 URLs and cloudcontroller is
	 * at {@code apiURL}.
	 * 
	 * @param clientId
	 *            the app client id
	 * @param clientSecret
	 *            the app client secret
	 * @param authorizeURL
	 *            location of the authorize OAuth2 callback
	 * @param tokenURL
	 *            location of the token OAuth2 callback
	 * @param apiURL
	 *            location of the "cloud controller" REST API
	 * 
	 */
	public CloudFoundryConnectionFactory(String clientId, String clientSecret,
			String authorizeURL, String tokenURL, URL apiURL) {
		super("cloudfoundry", new CloudFoundryServiceProvider(clientId,
				clientSecret, authorizeURL, tokenURL, apiURL),
				new CloudFoundryAdapter());
	}
}
