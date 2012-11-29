import java.util.List;
import java.util.ArrayList;

public class CentrifugationInput {

	private List<MixtureElement> mixtureElements;
	private MixtureElement elementToSeparate;
	private float purityPercentage;
	private int maxHeight;
	private int blowLimit;

	public List<MixtureElement> getMixtureElements() {
		return mixtureElements;
	}

	public void setMixtureElements(List<MixtureElement> mixtureElement) {
		this.mixtureElements = mixtureElement;
	}

	public MixtureElement getElementToSeparate() {
		return elementToSeparate;
	}

	public void setElementToSeparate(MixtureElement elementToSeparate) {
		this.elementToSeparate = elementToSeparate;
	}

	public float getPurityPercentage() {
		return purityPercentage;
	}

	public void setPurityPercentage(float purityPercentage) {
		this.purityPercentage = purityPercentage;
	}

        public int getMaxHeight() {
                return maxHeight;
        }

        public void setMaxHeight(int maxHeight) {
                this.maxHeight = maxHeight;
        }

        public int getBlowLimit() {
                return blowLimit;
        }

        public void setBlowLimit(int blowLimit) {
                this.blowLimit = blowLimit;
        }

        public MixtureElement getMixtureElementByName(String eName) {
                for (MixtureElement element : mixtureElements) {
                        if(element.getName().equals(eName)) {
                                return element;
                        }
                }
                return null;
        }

       public MixtureElement getMixtureElementWithMinDensity() {
                int size = getMixtureElements().size();
                int index = -1;
                float current = 1000000;
                for(int i=0; i<size-1; i++) {
                        if(current > getMixtureElements().get(i).getDensity()) {
                                current = getMixtureElements().get(i).getDensity();
                                index = i;
                        }
                }

                return getMixtureElements().get(index);

        }

        public float getTotalVolume() {

                float volume = 0;

                for(MixtureElement e: getMixtureElements()) {
                        volume += e.getVolume();
                }

                return volume;
        }

        public float getRatio(MixtureElement topElement) {
                float volume = getTotalVolume();
                return topElement.getVolume()/volume;
        }

        public void inputDisplay() {

                for(MixtureElement e: getMixtureElements()) {
                        System.out.print(e.getName() + ":" + e.getVolume() + "  ");
                }

                System.out.println();
        }

	public List<MixtureElement> getCurrentMixtureState() {
		List<MixtureElement> result = new ArrayList<MixtureElement>();

		for(MixtureElement e: getMixtureElements()) {
			MixtureElement temp = new MixtureElement();
			temp.setName(e.getName());
			temp.setVolume(e.getVolume());
			result.add(temp);
		}

		return result;
	}

	public double getTime() {

		double currentEfforts = 0;

		for (int i=0; i < getMixtureElements().size()-1; i++) {

			double e = calculateEfforts(i);
			if (e > currentEfforts) {
				currentEfforts = e;
			}
		}

		return currentEfforts;
	}

	private double calculateEfforts(int j) {

		MixtureElement e = getMixtureElements().get(j);
		MixtureElement gradient = getMixtureElements().get(getMixtureElements().size()-1);

		float dp = e.getParticleDiameter();
		float density = e.getDensity();
		float gradientDensity = gradient.getDensity();
		float viscosity = gradient.getViscosity();

		double d = ((gradientDensity-density)/1000);
		d = d / 18;
		d = d / viscosity;
		d = d * dp * dp;

		d = 1/d;

		return d;
	}
}
