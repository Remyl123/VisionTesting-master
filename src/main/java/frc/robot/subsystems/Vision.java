/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.commands.Display;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  NetworkTable table1 = NetworkTableInstance.getDefault().getTable("limelight");
 
  public void initDefaultCommand() {
    setDefaultCommand(new Display());
  }
  public void blink(){

    table1.getEntry("ledMode").setNumber(3);

  }
  public void stopBlinking(){

    table1.getEntry("ledMode").setNumber(1);

  }
  public void display(){

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry ts = table.getEntry("ts");


    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double targetArea = ta.getDouble(0.0);
    double skew = ts.getDouble(0.0);

    //double x = tx.getNumber("tx",0)
    //double y = tx.getNumber("ty",0)
    //double validTarget = tx.getNumber("tv",0)
    
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("Target Area", targetArea);
    SmartDashboard.putNumber("Skew", skew);
    
  }

  public double getArea(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ta = table.getEntry("ta");

    return ta.getDouble(0);
  }

  public double getXOffset(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");

    return tx.getDouble(0);

  }
  
  public double getSkew(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ts = table.getEntry("ts");

    return ts.getDouble(0);


  }
  /*
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  @Override
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

  //read values periodically
  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);

  //post to smart dashboard periodically
  SmartDashboard.putNumber("LimelightX", x);
  SmartDashboard.putNumber("LimelightY", y);
  SmartDashboard.putNumber("LimelightArea", area);

  table.getEntry("pipeline").setNumber(1);
  table.getEntry("ledMode").setNumber(2);
  table.getEntry("snapshot").setNumber(1);
  table.getEntry("ledMode").setNumber(1);

  */
}