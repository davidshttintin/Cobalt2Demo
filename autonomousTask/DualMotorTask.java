package framework.Cobalt.Cobalt2.framework.autonomous.task.motor.dualMotorTasks;

import com.qualcomm.robotcore.robocol.Telemetry;

import framework.Cobalt.Cobalt2.api.IRobot;
import framework.Cobalt.Cobalt2.api.autonomous.CobaltTask;
import framework.Cobalt.Cobalt2.api.hardware.IMotor;

/**
 * Created by David on 2016/2/18.
 */
public abstract class DualMotorTask extends CobaltTask {

    protected IMotor leftMotor, rightMotor;
    protected double power;
    protected Telemetry telemetry;

    public DualMotorTask(IMotor leftMotor, IMotor rightMotor, double power){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.power = power;
    }

    public DualMotorTask(IMotor leftMotor, IMotor rightMotor, double power, Telemetry telemetry){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.power = power;
        this.telemetry = telemetry;
    }

    @Override
    public void preTask() {
        leftMotor.resetEncoder();
        rightMotor.resetEncoder();
    }

    @Override
    public void postTask() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    @Override
    public void terminate() {
        leftMotor.resetEncoder();
        rightMotor.resetEncoder();
    }
}
