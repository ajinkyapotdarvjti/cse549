import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String args[]) throws NumberFormatException,
			IOException {

		BufferedReader reader = null;
		//reader = new BufferedReader(new InputStreamReader(System.in));
		reader = new BufferedReader(new FileReader(new File("input")));
		//System.out.println("Enter number of components in the mixture");
		int noOfInputs = Integer.parseInt(reader.readLine());
		List<MixtureElement> mixtureElements = new ArrayList<MixtureElement>();

		for (int i = 0; i < noOfInputs; i++) {

			//System.out.println("Enter the component details for component " + (i+1));
			//System.out.println("Name: ");
			String name = reader.readLine();
			//System.out.println("Volume: ");
			float volume = Float.parseFloat(reader.readLine());
			//System.out.println("Density: ");
			float density = Float.parseFloat(reader.readLine());
                        //System.out.println("Molecular Weight: ");
                        float particleDiameter = Float.parseFloat(reader.readLine());

			MixtureElement ele = new MixtureElement();
			ele.setName(name);
			ele.setVolume(volume);
			ele.setDensity(density);
			ele.setParticleDiameter(particleDiameter);
			ele.setPercentageSeparated(0);
			mixtureElements.add(ele);
		}

		//System.out.println("Enter the material name to be separated");
		String materialName = reader.readLine();

		//System.out.println("Enter Expected purity: ");
		float purityPercentage = Float.parseFloat(reader.readLine());

                //System.out.println("Enter maximum height of the centrifuge tube: ");
                int maxHeight = Integer.parseInt(reader.readLine());

                //System.out.println("Enter the Blow Limit: ");
                int blowLimit = Integer.parseInt(reader.readLine());

		CentrifugationInput input = new CentrifugationInput();
		input.setMixtureElements(mixtureElements);
		input.setPurityPercentage(purityPercentage);
		input.setMaxHeight(maxHeight);
		input.setBlowLimit(blowLimit);

		MixtureElement elementToSeparate = input.getMixtureElementByName(materialName);
		input.setElementToSeparate(elementToSeparate);

		//add gradient
                MixtureElement g = new MixtureElement();
                g.setName("CsCl");
                g.setVolume((float)0.1 * input.getTotalVolume());
                g.setDensity((float)3.99);
                g.setParticleDiameter(0);
                g.setViscosity((float)0.001);
                g.setPercentageSeparated(0);
                input.getMixtureElements().add(g);

		System.out.println("Cenrifugation Strategy..");
		List<CentrifugationStep> result = new ArrayList<CentrifugationStep>();
		CentrifugationStrategy.doCentrifugation(input, result);

		CentrifugationStrategy.display(result);

		//input.inputDisplay();
	}
}

