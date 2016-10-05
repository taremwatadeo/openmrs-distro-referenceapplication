package org.openmrs.reference;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.reference.helper.PatientGenerator;
import org.openmrs.reference.helper.TestPatient;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.HeaderPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.RegistrationPage;
import org.openmrs.uitestframework.test.TestBase;

/**
 * Created by tomasz on 22.07.15.
 */
public class UnidentifiedPatientKeyboardTest extends TestBase {
    private HeaderPage headerPage;
    private RegistrationPage registrationPage;
    private HomePage homePage;
    private ClinicianFacingPatientDashboardPage patientDashboardPage;
    private TestPatient patient;

    @Before
    public void setUp() throws Exception {
        homePage = new HomePage(page);
        registrationPage = new RegistrationPage(page);
        patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
        assertPage(homePage);
    }

    @After
    public void tearDown() throws Exception {
        deletePatient(patient.uuid);
        waitForPatientDeletion(patient.uuid);
    }

    // Test for RA-472,
    @Ignore//ignored due to inability to check unindentified patient by keyboard
    @Test
    public void registerUnidentifiedPatient() throws InterruptedException {
        homePage.goToRegisterPatientApp();
        patient = PatientGenerator.generateTestPatient();

        assertTrue(registrationPage.getNameInConfirmationPage().contains("--"));
        assertTrue(registrationPage.getGenderInConfirmationPage().contains(patient.gender));

        patient.uuid = patientDashboardPage.getPatientUuidFromUrl();
        assertPage(patientDashboardPage);	// remember just-registered patient id, so it can be removed.
        assertTrue(driver.getPageSource().contains("UNKNOWN UNKNOWN"));
    }
}
