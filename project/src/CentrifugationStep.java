import java.util.List;

public class CentrifugationStep {

	private float radius;
	private float percentageSeparated;
	private String elementSeparated;
	private String message;

	private int time;
	private int speed;

	private List<MixtureElement> mixtureState;

	public String getElementSeparated() {
		return elementSeparated;
	}

	public void setElementSeparated(String elementSeparated) {
		this.elementSeparated = elementSeparated;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

        public float getPercentageSeparated() {
                return percentageSeparated;
        }

        public void setPercentageSeparated(float percentageSeparated) {
                this.percentageSeparated = percentageSeparated;
        }

        public List<MixtureElement> getMixtureState() {
                return mixtureState;
        }

	public void setMixtureState(List<MixtureElement> mixtureState) {
		this.mixtureState = mixtureState;
	}

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public int getTime() {
                return time;
        }

        public void setTime(int time) {
                this.time = time;
        }

        public int getSpeed() {
                return speed;
        }

        public void setSpeed(int speed) {
                this.speed = speed;
        }
}

