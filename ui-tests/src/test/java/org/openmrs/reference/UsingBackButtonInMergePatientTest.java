package org.openmrs.reference;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.reference.helper.TestPatient;
import org.openmrs.reference.page.*;
import org.openmrs.uitestframework.test.TestBase;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by nata on 24.07.15.
 */

public class UsingBackButtonInMergePatientTest extends TestBase {
    private HomePage homePage;
    private HeaderPage headerPage;
    private TestPatient patient;
    private TestPatient patient1;
    private RegistrationPage registrationPage;
    private ClinicianFacingPatientDashboardPage patientDashboardPage;
    private DataManagementPage dataManagementPage;
    private String id;
    private String id2;

    @Before
    public void setUp() throws Exception {
        
        homePage = new HomePage(page);
        assertPage(homePage);
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(page);
        patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
        dataManagementPage = new DataManagementPage(page);
        patient = new TestPatient();
        patient1 = new TestPatient();


    }

    @Ignore //Ignored due to blocking validation
    @Test
    public void usingBackButtonInMergePatientTest() throws Exception {
        homePage.goToRegisterPatientApp();
        patient.familyName = "Potter";
        patient.givenName = "John";
        patient.gender = "Male";
        patient.estimatedYears = "45";
        patient.address1 = "address";
        registrationPage.enterMergePatient(patient);
        id = patientDashboardPage.findPatientId();
        patient.uuid =  patientDashboardPage.getPatientUuidFromUrl();
        headerPage.clickOnHomeIcon();
        homePage.goToRegisterPatientApp();
        patient1.familyName = "Smith";
        patient1.givenName = "Jane";
        patient1.gender = "Female";
        patient1.estimatedYears = "25";
        patient1.address1 = "address";
        registrationPage.enterMergePatient(patient1);
        id2 = patientDashboardPage.findPatientId();
        headerPage.clickOnHomeIcon();
        homePage.goToDataManagement();
        dataManagementPage.goToMergePatient();
        dataManagementPage.enterPatient1(id);
        dataManagementPage.enterPatient2(id2);
        dataManagementPage.searchId(id);
        dataManagementPage.clickOnContinue();
        dataManagementPage.clickOnNo();
        dataManagementPage.enterPatient1(id);
        assertNotNull(dataManagementPage.CONTINUE);
    }


    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        deletePatient(patient.uuid);
        waitForPatientDeletion(patient.uuid);
        headerPage.logOut();
    }

}
