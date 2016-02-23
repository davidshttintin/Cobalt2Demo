package framework.Cobalt.Cobalt2.framework.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.Cobalt.Cobalt2.api.IRobot;
import framework.Cobalt.Cobalt2.framework.hardware.motor.CobaltMotor;

/**
 * Created by David on 2016/1/27.
 *
 * @author David
 */
public class CobaltRobot implements IRobot {

    private final HardwareMap hardwareMap;
    private final Telemetry telemetry;
    private List<CobaltMotor> leftMotorList = new ArrayList<>();
    private List<CobaltMotor> rightMotorList = new ArrayList<>();
    private Map<String, Servo> servoMap = new HashMap<>();
    private double width;
    private double length;
    private double backWheelRadius;

    public CobaltRobot(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.telemetry = opMode.telemetry;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getLength() {
        return this.length;
    }

    @Override
    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getBackWheelRadius() {
        return this.backWheelRadius;
    }

    @Override
    public void setBackWheelRadius(double backWheelRadius) {
        this.backWheelRadius = backWheelRadius;
    }

    @Override
    public CobaltMotor[] getLeftMotors() {
        return leftMotorList.toArray(new CobaltMotor[leftMotorList.size()]);
    }

    @Override
    public void setLeftMotors(String... identifier) {
        for (String id : identifier) {
            leftMotorList.add(new CobaltMotor(hardwareMap.dcMotor.get(id)));
        }
    }

    @Override
    public CobaltMotor[] getRightMotors() {
        return rightMotorList.toArray(new CobaltMotor[rightMotorList.size()]);
    }

    @Override
    public void setRightMotors(String... identifier) {
        for (String id : identifier) {
            rightMotorList.add(new CobaltMotor(hardwareMap.dcMotor.get(id)));
        }
    }

    @Override
    public void setServos(String... identifier) {
        for (String id : identifier) {
            servoMap.put(id, hardwareMap.servo.get(id));
        }

    }

    @Override
    public Servo getServo(String identifier) {
        return servoMap.get(identifier);
    }

    @Override
    public Servo[] getServos(String... identifier) {
        ArrayList<Servo> servoList = new ArrayList<>();
        for (String id : identifier) {
            servoList.add(servoMap.get(id));
        }
        return servoList.toArray(new Servo[servoList.size()]);
    }

    @Override
    public HardwareMap getHardwareMap() {
        return this.hardwareMap;
    }

    @Override
    public Telemetry getTelemetry() {
        return this.telemetry;
    }
}
