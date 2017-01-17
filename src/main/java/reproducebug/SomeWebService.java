/*
 * -----------------------------------------------------------------------------
 * Application     : WM 6
 * Revision        : $Revision: 376095 $
 * Revision date   : $Date: 2016-10-22 03:25:29 +0200 (Sat, 22 Oct 2016) $
 * Last changed by : $Author: b2yeowo $
 * URL             : $URL: http://almscdc.swisslog.com/repo/SWPD/Development/WM6/branches/workbranches/nunoWorkBranches/wm6_16_12_2_1_branch2/wm6-components/wm6-framework/src/main/java/com/swisslog/wm6/framework/impl/timer/Wm6TimerRegistryWebService.java $
 *
 * -----------------------------------------------------------------------------
 * Copyright
 * This software module including the design and software principals used
 * is and remains the property of Swisslog and is submitted with the
 * understanding that it is not to be reproduced nor copied in whole or in
 * part, nor licensed or otherwise provided or communicated to any third
 * party without Swisslog's prior written consent.
 * It must not be used in any way detrimental to the interests of Swisslog.
 * Acceptance of this module will be construed as an agreement to the above.
 *
 * All rights of Swisslog remain reserved. Swisslog and WarehouseManager
 * are trademarks or registered trademarks of Swisslog. Other products
 * and company names mentioned herein may be trademarks or trade names of
 * their respective owners. Specifications are subject to change without
 * notice.
 * -----------------------------------------------------------------------------
 */
package reproducebug;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import service.BasicService;

/**
 * This class contains all methods of the WM timer registry which will be exposed via web service. It can be used for
 * testing purposes. It simply forwards all requests to the <code>WMTimerRegistry</code> singleton bean.
 *
 */
@Stateless
@WebService
public class SomeWebService {

    private static final Logger LOGGER = Logger.getLogger(SomeWebService.class.getCanonicalName());

    @Inject
    BasicService basicService;

    /**
     * Just a defult no arguments constructor.
     */
    public void SomeWebService() {

    }

    @PostConstruct
    public void postConstruct() {
        LOGGER.log(Level.INFO, "BasicWebService - PostConstruct - Being invoked");
    }

    /**
     * Just a basic web service api.
     */
    public void doWork() {
        LOGGER.log(Level.INFO, "BasicWebService - DoWork - Being invoked");
        basicService.doWork();
    }

}