/*
 * Copyright (c) 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.mgt.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.mgt.IdentityMgtServiceException;
import org.wso2.carbon.identity.mgt.NotificationSendingModule;
import org.wso2.carbon.identity.mgt.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * email sending abstract module. This implements the runnable task for sending
 * email.
 * So implementation of this module does not want to worry about threads.
 */
public abstract class AbstractEmailSendingModule extends NotificationSendingModule {

    private static final Log log = LogFactory.getLog(AbstractEmailSendingModule.class);
    protected String NOTIFICATION_TYPE = "EMAIL";
    private Map<String, EmailConfig> emailConfigs = new HashMap<String, EmailConfig>();

    @Override
    public String getNotificationType() {
        return NOTIFICATION_TYPE;
    }

    @Override
    public void init() throws IdentityMgtServiceException {
        loadEmailConfigurations();
    }

    @Override
    public String getNotificationAddress(String userName, int tenantId) {
        return Utils.getEmailAddressForUser(userName, tenantId);
    }

    @Override
    public void notifyUser() {

        sendEmail();
    }

    public abstract void sendEmail();

    /**
     * method to load the adminManagementConfig
     */
    public void loadEmailConfigurations() {

        /* Load the configuration */
    }

}
