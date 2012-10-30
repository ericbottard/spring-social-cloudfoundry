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
package org.springframework.social.cloudfoundry.api.impl;

import java.net.URL;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.CloudFoundryOperations;
import org.springframework.social.cloudfoundry.api.CloudFoundry;

public class CloudFoundryTemplate implements CloudFoundry {

	private CloudFoundryOperations cloudControllerOperations;

	public CloudFoundryTemplate(CloudCredentials cloudCredentials, URL apiURL) {
		cloudControllerOperations = new CloudFoundryClient(cloudCredentials, apiURL);
	}

	@Override
	public boolean isAuthorized() {
		return cloudControllerOperations.getCloudInfo().getUser() != null;
	}

	@Override
	public CloudFoundryOperations cloudControllerOperations() {
		return cloudControllerOperations;
	}
}