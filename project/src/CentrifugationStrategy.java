import java.util.*;

public class CentrifugationStrategy {

        private static int[] speeds = {500, 1000, 2000, 5000, 10000};

	public static void doCentrifugation(CentrifugationInput input, List<CentrifugationStep> result) {

		MixtureElement e =  null;

		if (result.size() != 0 ) {
			String eName = result.get(result.size()-1).getElementSeparated();
			e = input.getMixtureElementByName(eName);
		}

		if (e!= null && e.equals(input.getElementToSeparate()) && e.getPercentageSeparated() >= input.getPurityPercentage()) {
			return;
		} if (!input.getMixtureElements().contains(input.getElementToSeparate())) {
			return;
		} else {

			MixtureElement topElement = input.getMixtureElementWithMinDensity();

			float h = input.getMaxHeight();
			float volume = input.getTotalVolume();
			float r = (float)(volume * 1000/(Math.PI * h));
			r = (float)Math.sqrt(r);

			float ratio = input.getRatio(topElement);
			float separatedHeight = ratio*h - input.getBlowLimit();

			CentrifugationStep step = new CentrifugationStep();
			if (separatedHeight < 0.01) {
				// maximum separation of topElelment done
				// remove element from the input list
				input.getMixtureElements().remove(topElement);
				step.setMessage("The mixture element " + topElement.getName() + " cannot be separated further than this because of the constraints on the physical dimension of the centrifuging tube");
				step.setMixtureState(input.getCurrentMixtureState());
				result.add(step);
			} else {
				float volumeSeparated = (float)(Math.PI * r * r * separatedHeight);
				volumeSeparated /= 1000;

				float originalVolumeTop = topElement.getVolume()/(1 - topElement.getPercentageSeparated()/100);
				float percent = volumeSeparated/originalVolumeTop;
				topElement.setPercentageSeparated(topElement.getPercentageSeparated() + percent*100);
				topElement.setVolume(topElement.getVolume() - volumeSeparated);

				step.setRadius(r);
				step.setPercentageSeparated(topElement.getPercentageSeparated());
				step.setElementSeparated(topElement.getName());
				step.setMixtureState(input.getCurrentMixtureState());

				double efforts = input.getTime();
				double speed = Math.sqrt(efforts);
				speed = Math.sqrt(speed);

				for(int i=0; i<speeds.length; i++) {
					if(speeds[i] > speed) {
						speed = speeds[i];
						break;
					}
				}

				double time = efforts/(speed * speed);

				step.setSpeed((int)speed);
				step.setTime((int)time);

				result.add(step);

				if (topElement.getPercentageSeparated() >= 99) {
					input.getMixtureElements().remove(topElement);
				}

			}

			doCentrifugation(input, result);
		}
	}

	public static void display(List<CentrifugationStep> result) {

		for(CentrifugationStep step: result) {

                       	if(step.getMessage() != null) {
 	                	System.out.println(step.getMessage());
            	        } else {
				System.out.println("Radius of the centrifugation tube: " + step.getRadius());
				System.out.println("Mixture element " + step.getElementSeparated() + ", " + step.getPercentageSeparated() + " separated");
				System.out.println("Duration of rotation: " + step.getTime() + " sec");
				System.out.println("Speed of rotation: " + step.getSpeed() + " rpm");
			}

			System.out.println("Now, the mixture contains: ");
			for(MixtureElement e: step.getMixtureState()) {
				System.out.println(e.getName() + " : " + e.getVolume());
			}
			System.out.println("-----------------------------------");
		}
	}
}
