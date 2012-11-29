public class MixtureElement {

	String name;
	float density;
	float volume;
	float particleDiameter;
	float percentageSeparated;
	float viscosity;

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

        public float getParticleDiameter() {
                return particleDiameter;
        }

        public void setParticleDiameter(float particleDiameter) {
                this.particleDiameter = particleDiameter;
        }

        public float getPercentageSeparated() {
                return percentageSeparated;
        }

        public void setPercentageSeparated(float percentageSeparated) {
                this.percentageSeparated = percentageSeparated;
        }

        public float getViscosity() {
                return viscosity;
        }

        public void setViscosity(float viscosity) {
                this.viscosity = viscosity;
        }

	public boolean equals(MixtureElement e) {
		return name.equals(e.getName());
	}
}
