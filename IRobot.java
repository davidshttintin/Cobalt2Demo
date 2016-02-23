package framework.Cobalt.Cobalt2.api;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

import framework.Cobalt.Cobalt2.framework.hardware.motor.CobaltMotor;

/**
 * Created by David on 2016/1/26.
 * Documentation by David on 2016/1/26.
 */
public interface IRobot {
    /**
     * Get the robot's width
     *
     * @return Robot's width
     */
    double getWidth();

    /**
     * Set the robot's width
     *
     * @param width : the width of the robot (IN CENTIMETER)
     */
    void setWidth(double width);

    /**
     * Get the robot's length
     *
     * @return Robot's length
     */
    double getLength();

    /**
     * Set the robot's length
     *
     * @param length : the length of robot (IN CENTIMETER)
     */
    void setLength(double length);

    /**
     * Robot's Back Wheel Radius(For a robot with two wheels)
     *
     * @return Robot's back wheel radius
     */
    double getBackWheelRadius();

    /**
     * Set the robot's back wheel radius
     *
     * @param backWheelRadius : the radius of back wheels (IN CENTIMETER)
     */
    void setBackWheelRadius(double backWheelRadius);

    /**
     * Robot's left motors
     *
     * @return Robot's left motors
     */
    CobaltMotor[] getLeftMotors();

    /**
     * Set left motors for robot
     *
     * @param identifier identifiers to identify each of the motors
     */
    void setLeftMotors(String... identifier);

    /**
     * Robot's right motors
     *
     * @return Robot's right motors
     */
    CobaltMotor[] getRightMotors();

    /**
     * Set right motors for robot
     *
     * @param identifier identifiers to identify each of the motors
     */
    void setRightMotors(String... identifier);

    void setServos(String... identifier);

    /**
     * According to the key you provide, get a servo from servoMap
     *
     * @param identifier : the identifier of the servo (typically a easy-remembering name. Eg : LeftFrontServo)
     * @return the servo you want to find
     */
    Servo getServo(String identifier);

    /**
     * According to the key you provide, get an array of servos from servoMap
     *
     * @param identifier : the identifier
     * @return an array of servos
     */
    Servo[] getServos(String... identifier);

    /**
     * Robot's hardwareMap
     *
     * @return Robot's hardwareMap
     */
    HardwareMap getHardwareMap();

    /**
     * Robot's telemetry
     *
     * @return Robot's telemetry object
     */
    Telemetry getTelemetry();

}
