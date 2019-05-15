/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveToTarget extends Command {
  public MoveToTarget() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.vision);
    requires(Robot.mecanumDrive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.vision.blink();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double offset = Robot.vision.getXOffset();
    if (offset < -.5){
      Robot.mecanumDrive.left();
    } else if(offset > .5){
      Robot.mecanumDrive.right();
    }else if(Robot.vision.getArea() < 50){
      Robot.mecanumDrive.forward();
    }else{

      Robot.mecanumDrive.stop();
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mecanumDrive.stop();
    Robot.vision.close();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.mecanumDrive.stop();
    Robot.vision.close();
  }
}
