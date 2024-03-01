package frc.robot.drivetrain.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.drivetrain.MechanumDrive;

public class Kinematics extends Command {
    public final MechanumDrive m_mechanumDrive;
    public final double m_angle;

    public static Joystick driver;
    
    Translation2d m_frontLeftLocation = new Translation2d(0.381, 0.381);
    Translation2d m_frontRightLocation = new Translation2d(0.381, -0.381);
    Translation2d m_backLeftLocation = new Translation2d(-0.381, 0.381);
    Translation2d m_backRightLocation = new Translation2d(-0.381, -0.381);


    public Kinematics(MechanumDrive subsystem, double angle) {
        m_mechanumDrive = subsystem;
        m_angle = angle;

        addRequirements(subsystem);
    }

    public void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        driver = new Joystick(Constants.OperatorConstants.kDriverControllerPort);

        MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(
            m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation
        );

        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            0.01 * driver.getRawAxis(0), 0.01 * driver.getRawAxis(1), 0, Rotation2d.fromDegrees(0)
        );

        MecanumDriveWheelSpeeds wheelSpeeds = m_kinematics.toWheelSpeeds(speeds);
        // MecanumDriveWheelSpeeds wheelSpeeds = new MecanumDriveWheelSpeeds(0.1, 0.1, 0.1, 0.1);


        double frontLeft = wheelSpeeds.frontLeftMetersPerSecond;
        double frontRight = wheelSpeeds.frontRightMetersPerSecond;
        double backLeft = wheelSpeeds.rearLeftMetersPerSecond;
        double backRight = wheelSpeeds.rearRightMetersPerSecond;

        m_mechanumDrive.setDrive(frontLeft, frontRight, backLeft, backRight);
    }


     // Make this return true when this Command no longer needs to run execute()
     public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {
        m_mechanumDrive.setDrive(0.0, 0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
