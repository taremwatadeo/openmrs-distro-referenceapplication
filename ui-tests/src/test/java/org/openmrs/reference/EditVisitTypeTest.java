/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.reference;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.reference.groups.BuildTests;
import org.openmrs.reference.page.AdministrationPage;
import org.openmrs.reference.page.VisitTypeListPage;
import org.openmrs.reference.page.VisitTypePage;
import org.openmrs.uitestframework.test.RestClient;
import org.openmrs.uitestframework.test.TestData;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 */
public class EditVisitTypeTest extends ReferenceApplicationTestBase{

    private String visitTypeName;
    private String visitTypeDesc;

    private String visitTypeUuid;

    @Before
    public void setup() {
        visitTypeName = RandomStringUtils.randomAlphanumeric(8);
        visitTypeDesc = RandomStringUtils.randomAlphanumeric(16);
        visitTypeUuid = new TestData.TestVisitType(visitTypeName, visitTypeDesc).create();
    }

    @Test
    @Category(BuildTests.class)
    public void editVisitTypeTest(){
        AdministrationPage administrationPage = homePage.goToAdministration();
        VisitTypeListPage visitTypeListPage = administrationPage.goToVisitTypePage();
        VisitTypePage visitTypePage = visitTypeListPage.goToVisitType(visitTypeName);
        visitTypeName = RandomStringUtils.randomAlphanumeric(8);
        visitTypePage.setName(visitTypeName);
        visitTypeListPage = visitTypePage.save();
        assertThat(visitTypeListPage.getVisitTypeList(), hasItem(visitTypeName));
    }

    @After
    public void teardown(){
        RestClient.delete("visittype/"+visitTypeUuid, true);
    }
}
