package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.drivetrain.MechanumDrive;

public class Turn extends Command {
    public final MechanumDrive m_mechanumDrive;
    public final int m_direction;

    public static Joystick driver;

    public Turn(MechanumDrive subsystem, int direction) {
        m_mechanumDrive = subsystem;
        m_direction = direction;

        addRequirements(subsystem);
    }

    public void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        driver = new Joystick(Constants.OperatorConstants.kDriverControllerPort);

        double frontLeft = 0.7 * m_direction;
        double frontRight = 0.7 * m_direction;
        double backLeft = 0.7 * m_direction;
        double backRight = 0.7 * m_direction;

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
