package framework.Cobalt.Cobalt2.framework.autonomous.task.motor.dualMotorTasks;

import com.qualcomm.robotcore.robocol.Telemetry;

import framework.Cobalt.Cobalt2.api.hardware.IMotor;
import framework.Cobalt.Cobalt2.util.hardware.motor.MotorSet;

/**
 * Created by David on 2016/2/18.
 * Imported from Cobalt(TaskSteerDualMotorMovement).
 * Original author: Codetector.
 * Updated by David on 2016/2/18.
 */
public class DualMotorSteerMovement extends DualMotorTask {
    Telemetry telemetry;
    private double wheelRadius;
    private double robotWidth;
    private int innerRadius;
    private int degrees;
    private TurnDirection direction;
    private float innerPower;
    private double outerArcLength;
    private double innerArcLength;

    private boolean firstRun = true;

    /**
     * @param power       Power
     * @param innerRadius InnerRadius
     * @param degrees     Degress to turn
     * @param direction   TurnDirection (LEFT / Right)
     */

    public DualMotorSteerMovement(IMotor leftMotor, IMotor rightMotor, int innerRadius, int degrees, double power, TurnDirection direction) {
        super(leftMotor, rightMotor, power);
        this.innerRadius = innerRadius;
        this.degrees = degrees;
        this.direction = direction;
    }

    @Override
    public void preTask(){
        if (direction == TurnDirection.RIGHT){
            IMotor temporaryMotor = leftMotor;
            leftMotor = rightMotor;
            rightMotor = temporaryMotor;
        }

    this.robotWidth = robot.getWidth();
    this.wheelRadius = robot.getBackWheelRadius();

    this.innerArcLength = innerRadius * Math.PI * 2 * (degrees / 360d);
    this.outerArcLength = (degrees / 360d) * (innerRadius + robotWidth) * Math.PI * 2;
    super.power = power / 100.0f;
    this.innerPower = (float) (super.power * (innerArcLength / outerArcLength));

    robot.getTelemetry().addData("Width", robotWidth);
    robot.getTelemetry().addData("Wheel Radius", wheelRadius);
    robot.getTelemetry().addData("Arc Length", innerArcLength);
    robot.getTelemetry().addData("Out Arc", outerArcLength);
    robot.getTelemetry().addData("Power", power);
    robot.getTelemetry().addData("Inner Power", innerPower);

    telemetry = robot.getTelemetry();
    }

    @Override
    public boolean execute() {

        if (this.firstRun) {
            firstRun = false;
            leftMotor.resetEncoder();
            rightMotor.resetEncoder();
        }

        telemetry.addData("_Inner Length", (MotorSet.encoderToDegree((int) leftMotor.getCurrentPosition(), MotorSet.EncoderRevolutionRatio.NeverRest40.getValue()) / 360d) * this.wheelRadius);
        telemetry.addData("_Inner Target", this.innerArcLength);
        telemetry.addData("_Inner Degree", MotorSet.encoderToDegree((int) this.leftMotor.getCurrentPosition(), MotorSet.EncoderRevolutionRatio.NeverRest40.getValue()));
        telemetry.addData("Outer Length", (MotorSet.encoderToDegree((int) rightMotor.getCurrentPosition(), MotorSet.EncoderRevolutionRatio.NeverRest40.getValue()) / 360d) * this.wheelRadius);
        telemetry.addData("Outer Target", this.outerArcLength);
        telemetry.addData("Outer Degree", MotorSet.encoderToDegree((int) this.rightMotor.getCurrentPosition(), MotorSet.EncoderRevolutionRatio.NeverRest40.getValue()));
        if ((Math.abs((MotorSet.encoderToDegree((int) leftMotor.getCurrentPosition(), MotorSet.EncoderRevolutionRatio.NeverRest40.getValue()) / 360d) * this.wheelRadius * Math.PI * 2) >= Math.abs(this.innerArcLength)) ||
                (Math.abs((MotorSet.encoderToDegree((int) rightMotor.getCurrentPosition(), MotorSet.EncoderRevolutionRatio.NeverRest40.getValue()) / 360.0d) * this.wheelRadius * Math.PI * 2) >= Math.abs(this.outerArcLength))) {
            return true;
        } else {
            leftMotor.setPower(innerPower);
            rightMotor.setPower(power);
            return false;
        }
    }

    public enum TurnDirection {
        LEFT, RIGHT
    }
}
