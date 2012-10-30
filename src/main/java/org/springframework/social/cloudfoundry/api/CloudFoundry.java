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
package org.springframework.social.cloudfoundry.api;

import org.cloudfoundry.client.lib.CloudFoundryOperations;
import org.springframework.social.ApiBinding;

/**
 * Main interface for interacting with CloudFoundry.
 * 
 * @author Eric Bottard
 * 
 */
public interface CloudFoundry extends ApiBinding {

	/**
	 * Returns the portion of the CloudFoundry API that allows interaction with
	 * apps, services and the like.
	 */
	public CloudFoundryOperations cloudControllerOperations();

	// Placeholder for future extension of the SpringSocial
	// CloudFoundry binding, e.g. registering new clients (in the OAuth sense)

}
