package org.usfirst.frc.team3070.robot;

public interface Pronstants {
	public final int PORT_RF = 2; // Right master CIM port
	public final int PORT_RM = 5; // Right follower CIM port

	public final int PORT_LF = 0; // Left master CIM port
	public final int PORT_LM = 1; // Left follower CIM port

	public final int PORT_ENC_R1 = 1; // Right encoder first port
	public final int PORT_ENC_R2 = 2; // Right encoder second port
	
	public final int JOY_PORT = 0;
	
	public final int ENCTICKS = 4096;
	public final double FWD_SPEED = .3;
	public final double BCKWRD_SPEED = -.3;
	
	public final int PORT_ENC_L1 = 7; // Left encoder first port
	public final int PORT_ENC_L2 = 8; // Left encoder second port
	
	public final double PI = 3.1415926535897932; // Variable equal to pi
	public final double WHEEL_DIAMETER = 6; // Distance in inches of wheel diameter
	public final double WHEEL_CIRCUM = WHEEL_DIAMETER * PI; // Distance in inches of wheel circumference
	public final double FEET_TO_ENC = 12 * ENCTICKS / WHEEL_CIRCUM;
	public final double ENC_TO_FEET = 1/FEET_TO_ENC;
	public static double feetToEnc(double feet) {
			return feet*FEET_TO_ENC;
	}
	double Vel_100ms = .3 * 500.0 * 4096 / 600;
	//robot is 32 1/4 inches by 23 1/2 inches.
//	16.125 inches by 11.25 inches is center mass
//	18.86 inches is circumfrence wheel
//	168 - 16.125= 151.875 to the switch 
//	151.875/18.86 = 8.05 rots to the switch
//	8.05*4096 = 32984 enc ticks to the switch  
//	323.65- 16.125 = 307.525 to the scale
//	307.525/18=  17.1 rotations to the scale
//	71.57 is the dis from the wall to the scale.
//	85.25 is the dis from the wall to the switch.

}
