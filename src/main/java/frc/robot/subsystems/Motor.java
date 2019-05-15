
package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Move;

/**
 *
 */
public class Motor extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
  private WPI_TalonSRX basicMotor = new WPI_TalonSRX(4);
  
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Move());

}
	public void drive()
	{
		basicMotor.set(0.5);
		
	}

    public void stop()
    {
        basicMotor.set(0);
    }
}