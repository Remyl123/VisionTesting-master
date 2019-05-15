/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;
//import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveByJoystick;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 * 
 * @param <WPI_TalonSRX>
 */
public class MecanumDriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_TalonSRX frontLeft = new WPI_TalonSRX(1);
  public WPI_TalonSRX rearLeft = new WPI_TalonSRX(6);
  public WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
  public WPI_TalonSRX rearRight = new WPI_TalonSRX(4);
  MecanumDrive mecDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new DriveByJoystick());
  }

  public void init() {
    frontLeft.setInverted(true);
    rearLeft.setInverted(true);
    frontRight.setInverted(true);
    rearRight.setInverted(true);
  }
  
  public void driveWithJoystick() {
    double forward = OI.joystick.getY() * 0.5; // don't go too fast! multiply by 0.2. forward = speed
    double turn = OI.joystick.getZ() * 0.5;
    double x = OI.joystick.getX() * 0.5;

    if(Math.abs(forward) < 0.20) forward = 0;

    if(Math.abs(turn) < .20) turn = 0; 

    if (Math.abs(x) < .20) turn = 0; 

    SmartDashboard.putNumber("Joystick Y:", forward);
    SmartDashboard.putNumber("Joystick Z:", turn);
    SmartDashboard.putNumber("Joystick X:", x);

    SmartDashboard.putNumber("Front Left Motor:", frontRight.get());
    //SmartDashboard.putNumber("Middle Left Motor:", RobotMap.middleLeft.get());
    SmartDashboard.putNumber("Rear Left Motor:", rearLeft.get());

    SmartDashboard.putNumber("Front Right Motor:", frontRight.get());
    //SmartDashboard.putNumber("Middle Right Motor:", RobotMap.middleRight.get());
    SmartDashboard.putNumber("Rear Right Motor:", rearRight.get());


    mecDrive.driveCartesian(x, forward, turn);
  }


  public void forward(){
    frontLeft.set(.5);
    rearLeft.set(.5);
    frontRight.set(.5);
    rearRight.set(.5);


  }

  public void stop(){
    frontLeft.set(0);
    rearLeft.set(0);
    frontRight.set(0);
    rearRight.set(0);
  }
    
  public void left(){
    mecDrive.driveCartesian(-0.2, 0, 0);
  }

  public void right(){
    mecDrive.driveCartesian(0.2, 0, 0);

    
  }
}
