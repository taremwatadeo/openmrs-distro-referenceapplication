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

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.experimental.categories.Category;
import org.openmrs.reference.page.FindPatientPage;
import org.openmrs.uitestframework.test.TestData;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class FindPatientByNameTest extends ReferenceApplicationTestBase {
    private FindPatientPage findPatientPage;
    private TestData.PatientInfo patient;


    @Before
    public void setUp() throws Exception {
        patient = createTestPatient();
    }

    @Test
    @Category(org.openmrs.reference.groups.BuildTests.class)
    public void findPatientByIdAndNameTest() throws Exception {
        findPatientPage = homePage.goToFindPatientRecord();
        // Search by name
        findPatientPage.enterPatient(patient.givenName);
        assertThat(findPatientPage.getFirstPatientName(), containsString(patient.givenName));

        findPatientPage.enterPatient(patient.middleName);
        assertThat(findPatientPage.getFirstPatientName(), containsString(patient.middleName));
        
        findPatientPage.enterPatient(patient.familyName);
        assertThat(findPatientPage.getFirstPatientName(), containsString(patient.familyName));
    }

    @After
    public void tearDown() throws Exception {
        deletePatient(patient.uuid);
        waitForPatientDeletion(patient.uuid);
    }

}
