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

import org.cloudfoundry.client.lib.CloudFoundryOperations;
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
public class CloudFoundryAdapter implements ApiAdapter<CloudFoundryOperations> {

	@Override
	public boolean test(CloudFoundryOperations api) {
		try {
			api.getCloudInfo();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void setConnectionValues(CloudFoundryOperations api,
			ConnectionValues values) {
		values.setProviderUserId(api.getCloudInfo().getUser());
	}

	@Override
	public UserProfile fetchUserProfile(CloudFoundryOperations api) {
		return new UserProfileBuilder().build();
	}

	@Override
	public void updateStatus(CloudFoundryOperations api, String message) {
		// NO-op
	}

}
