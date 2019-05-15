/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;

public class TankDrive extends PIDSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Command

  SpeedControllerGroup leftSide = new SpeedControllerGroup(RobotMap.topLeft, RobotMap.middleLeft, RobotMap.rearLeft);

  SpeedControllerGroup rightSide = new SpeedControllerGroup(RobotMap.topRight, RobotMap.middleRight, RobotMap.rearRight);

  DifferentialDrive difDrive = new DifferentialDrive(leftSide, rightSide);

  public TankDrive() {
    super("TankDrive", 1, 2, 3);
    setPercentTolerance(5);
    getPIDController().setContinuous(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new DriveCommand());
  }

  //where middle motor is the top motor

  public void init() { // middleLeft and middleRight motor must go in opposite directions from the rest of the motors.
    RobotMap.topLeft.follow(RobotMap.middleLeft);
    RobotMap.rearLeft.follow(RobotMap.middleLeft);
  
    RobotMap.topLeft.setInverted(true);
    RobotMap.rearLeft.setInverted(true);
    
    RobotMap.topRight.follow(RobotMap.middleRight);
    RobotMap.rearRight.follow(RobotMap.middleRight); 

    RobotMap.topRight.setInverted(true);
    RobotMap.rearRight.setInverted(true);
  }
  

  public void driveWithJoystick() {
    //ONE JOYSTICK
    //make sure throttle is at 1 or -1
    /*
    double forward = (OI.joyRight.getY()*0.8)*OI.joyRight.getThrottle();
    double turn = (OI.joyRight.getZ()*-0.8);
    */

    /*deadband*/
    /*
    if ((Math.abs(forward)<0.05) && (Math.abs(turn)<0.05))
    {
      stop();
    }

    else 
    {
      difDrive.arcadeDrive(forward, turn);
    }
    
    //difDrive.arcadeDrive(forward, turn);
    SmartDashboard.putNumber("tank drive front left", RobotMap.topLeft.get());
    SmartDashboard.putNumber("tank drive front right", RobotMap.topRight.get());
    SmartDashboard.putNumber("tank drive back left", RobotMap.rearLeft.get());
    SmartDashboard.putNumber("tank drive rear right", RobotMap.rearRight.get());


    //TWO JOYSTICKS
    
    double leftSpeed = OI.joyLeft.getY() * 0.8;
    double rightSpeed = OI.joyRight.getY() * -0.8; //check forwards/backwards
    double turn = OI.joyLeft.getZ() * 0.8;
    if(Math.abs(leftSpeed) < 0.2) 
      leftSpeed = 0;
    if (Math.abs(rightSpeed) < 0.2)
      rightSpeed = 0;
    if(Math.abs(leftSpeed) > 0.8)
      leftSpeed = 0.8;
    if(Math.abs(rightSpeed) > 0.8)
      rightSpeed = 0.8;
    SmartDashboard.putNumber("Front Left Motor:", RobotMap.topLeft.get());
    //SmartDashboard.putNumber("Middle Left Motor:", RobotMap.middleLeft.get());
    SmartDashboard.putNumber("Rear Left Motor:", RobotMap.rearLeft.get());
    SmartDashboard.putNumber("Front Right Motor:", RobotMap.topRight.get());
    //SmartDashboard.putNumber("Middle Right Motor:", RobotMap.middleRight.get());
    SmartDashboard.putNumber("Rear Right Motor:", RobotMap.rearRight.get());
    difDrive.tankDrive(leftSpeed, rightSpeed);
    //difDrive.arcadeDrive(leftSpeed, turn);
    */
  }
  public void turn(boolean isLeft){
    if(isLeft){
      difDrive.tankDrive(0, -.5);
    }else{
      difDrive.tankDrive(0, .5);
    }
  }
  //TO BE TESTED
  public void driveForward() { // to be utilized when button 3 is pressed on Left Joystick (joyLeft)
    double leftSpeed = 0.5;
    double rightSpeed = -0.5;
    difDrive.tankDrive(leftSpeed, rightSpeed);

    SmartDashboard.putNumber("Front Left Motor:", RobotMap.topLeft.get());
    //SmartDashboard.putNumber("Middle Left Motor:", RobotMap.middleLeft.get());
    SmartDashboard.putNumber("Rear Left Motor:", RobotMap.rearLeft.get());

    SmartDashboard.putNumber("Front Right Motor:", RobotMap.topRight.get());
    //SmartDashboard.putNumber("Middle Right Motor:", RobotMap.middleRight.get());
    SmartDashboard.putNumber("Rear Right Motor:", RobotMap.rearRight.get());
  }
/*
  public void testMotor(){
    RobotMap.
  }
  */


  public void stop() {
    RobotMap.topLeft.stopMotor();
    RobotMap.middleLeft.stopMotor();
    RobotMap.rearLeft.stopMotor();

    RobotMap.topRight.stopMotor();
    RobotMap.middleRight.stopMotor();
    RobotMap.rearRight.stopMotor();
  }

  @Override
  protected double returnPIDInput() {
    return 0;
  }

  @Override
  protected void usePIDOutput(double output) {

  }
}

  