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
