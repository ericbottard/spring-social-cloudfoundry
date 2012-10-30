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

import org.cloudfoundry.client.lib.domain.CloudInfo;
import org.springframework.social.cloudfoundry.api.CloudFoundry;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

/**
 * ApiAdapter for CloudFoundry. CloudFoundry is not primarily a social platform,
 * so some of the features are no-ops.
 * 
 * @author Eric Bottard
 * 
 */
public class CloudFoundryAdapter implements ApiAdapter<CloudFoundry> {

	@Override
	public boolean test(CloudFoundry api) {
		try {
			api.cloudControllerOperations().getCloudInfo();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void setConnectionValues(CloudFoundry api, ConnectionValues values) {
		values.setProviderUserId(api.cloudControllerOperations().getCloudInfo()
				.getUser());
	}

	@Override
	public UserProfile fetchUserProfile(CloudFoundry api) {
		CloudInfo cloudInfo = api.cloudControllerOperations().getCloudInfo();
		return new UserProfileBuilder().setEmail(cloudInfo.getUser())
				.setEmail(cloudInfo.getUser()).build();
	}

	@Override
	public void updateStatus(CloudFoundry api, String message) {
		// NO-op
	}

}
