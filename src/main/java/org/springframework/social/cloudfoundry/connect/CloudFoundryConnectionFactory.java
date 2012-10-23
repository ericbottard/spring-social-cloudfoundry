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

import java.net.URL;

import org.cloudfoundry.client.lib.CloudFoundryOperations;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class CloudFoundryConnectionFactory extends
		OAuth2ConnectionFactory<CloudFoundryOperations> {

	public CloudFoundryConnectionFactory(String clientId, String clientSecret) {
        super("cloudfoundry", new CloudFoundryServiceProvider(clientId, clientSecret), new CloudFoundryAdapter());
    }

	public CloudFoundryConnectionFactory(String clientId, String clientSecret, String uaaRoot, URL apiURL) {
        super("cloudfoundry", new CloudFoundryServiceProvider(clientId, clientSecret, uaaRoot, apiURL), new CloudFoundryAdapter());
    }
}
