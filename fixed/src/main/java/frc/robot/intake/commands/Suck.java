package frc.robot.intake.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.intake.Intake;

public class Suck extends Command {
    public final Intake m_intake;
    public final double m_input;

    public Suck(Intake intake, double input) {
        m_intake = intake;
        m_input = input;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(intake);
    }

    // Called just before this Command runs the first time
    public void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        m_intake.setIntake(m_input);
    }

     // Make this return true when this Command no longer needs to run execute()
     public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {
        // sets all drive wheels to 0.0
        m_intake.setIntake(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
