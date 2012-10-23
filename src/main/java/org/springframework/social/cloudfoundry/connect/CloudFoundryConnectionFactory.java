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
