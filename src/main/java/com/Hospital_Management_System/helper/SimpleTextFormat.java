package com.Hospital_Management_System.helper;

import com.Hospital_Management_System.model.Appointment;

public class SimpleTextFormat {

    public static String createAppointmentEmail(Appointment appointment) {

        return """
                <!DOCTYPE html>
                <html>
                <body style="font-family: Arial, sans-serif;">

                    <h2>Appointment Confirmation</h2>

                    <p>Hello <b>%s</b>,</p>

                    <p>Your appointment has been successfully scheduled.</p>

                    <h3>Appointment Details</h3>

                    <table border="1" cellpadding="8" cellspacing="0">
                        <tr>
                            <td><b>Appointment ID</b></td>
                            <td>%s</td>
                        </tr>

                        <tr>
                            <td><b>Status</b></td>
                            <td>%s</td>
                        </tr>

                        <tr>
                            <td><b>Reason</b></td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td><b>Appoitment Date</b></td>
                            <td>%s</td>
                        </tr>
                        <tr>
                            <td><b>Appoitment Time</b></td>
                            <td>%s</td>
                        </tr>
                    </table>

                    <h3>Patient Details</h3>

                    <table border="1" cellpadding="8" cellspacing="0">
                        <tr>
                            <td><b>Patient ID</b></td>
                            <td>%s</td>
                        </tr>

                        <tr>
                            <td><b>Name</b></td>
                            <td>%s</td>
                        </tr>

                        <tr>
                            <td><b>Email</b></td>
                            <td>%s</td>
                        </tr>

                        <tr>
                            <td><b>Phone</b></td>
                            <td>%s</td>
                        </tr>

                        <tr>
                            <td><b>Gender</b></td>
                            <td>%s</td>
                        </tr>

                        <tr>
                            <td><b>Birth Date</b></td>
                            <td>%s</td>
                        </tr>

                        <tr>
                            <td><b>Blood Group</b></td>
                            <td>%s</td>
                        </tr>
                    </table>

                    <h3>Doctor Details</h3>

                    <table border="1" cellpadding="8" cellspacing="0">
                        <tr>
                            <td><b>Doctor ID</b></td>
                            <td>%s</td>
                        </tr>

                        <tr>
                            <td><b>Doctor Name</b></td>
                            <td>%s</td>
                        </tr>
                    </table>

                    <br>

                    <p>
                        Thank you,<br>
                        <b>Hospital Management System</b>
                    </p>

                </body>
                </html>
                """.formatted(

                // Hello
                appointment.getPatient()
                        .getUser()
                        .getUsername(),

                // Appointment
                appointment.getId(),
                appointment.getStatus(),
                appointment.getReason(),
                appointment.getAppointment_date(),
                appointment.getAppointment_time(),

                // Patient
                appointment.getPatient().getId(),
                appointment.getPatient()
                        .getUser()
                        .getUsername(),
                appointment.getPatient().getEmail(),
                appointment.getPatient().getPhoneNumber(),
                appointment.getPatient().getGender(),
                appointment.getPatient().getBirthDate(),
                appointment.getPatient().getBloodGroup(),

                // Doctor
                appointment.getDoctor().getId(),
                appointment.getDoctor()
                        .getUser()
                        .getUsername()
        );
    }
}