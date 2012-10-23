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
