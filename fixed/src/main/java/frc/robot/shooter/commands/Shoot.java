package frc.robot.shooter.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.shooter.Shooter;

public class Shoot extends Command {
    public final Shooter m_shooter;
    public final double m_input;

    public Shoot(Shooter shooter, double input) {
        m_shooter = shooter;
        m_input = input;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(shooter);
    }

    // Called just before this Command runs the first time
    public void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        m_shooter.setShooter(m_input * -1);
    }

     // Make this return true when this Command no longer needs to run execute()
     public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {
        // sets all drive wheels to 0.0
        m_shooter.setShooter(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}